//
// Taranos Cloud Sonification Framework: Reference Server
// Copyright (C) 2018 David Hinson, Netrogen Blue LLC (dhinson@netrogenblue.com)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package controllers.org.taranos.sa.local

import org.taranos.common.ServiceCall
import org.taranos.mc.Cell.ResponseMessages.ServiceResult
import org.taranos.mc.CellDirector
import play.api.http.HeaderNames._
import play.api.libs.json._
import play.libs.Akka

import scala.concurrent.ExecutionContext.Implicits.global


object ServiceAdapter
{
    private
    def EncodeReplyString (
        serviceResult: org.taranos.common.ServiceResult,
        kind: String,
        status: Int): String =
    {
        var replyObject = play.api.libs.json.Json.obj()
        replyObject = replyObject + ("k", Json.toJson(kind))
        replyObject = replyObject + ("s", Json.toJson(status))
        replyObject = replyObject + ("r", Json.toJson(serviceResult._status))
        if (serviceResult._results.nonEmpty)
        {
            val topValue = Json.parse(serviceResult._results.head)
            topValue.validate[JsObject] match
            {
                case JsSuccess(topObject, path) =>
                    replyObject = replyObject.deepMerge(topObject)

                case JsError(_) =>
                    throw new Exception()
            }
        }
        play.api.libs.json.Json.stringify(replyObject)
    }

    private
    def SendLocal (serviceName: String, serviceArgs: Seq[String]): scala.concurrent.Future[String] =
    {
        import scala.concurrent.duration._

        val actorSelection = Akka.system.actorSelection("akka://application/user/cellDirector")
        val actorSelectionFut = actorSelection.resolveOne()(akka.util.Timeout.intToTimeout(1000))
        val cellDirectorRef: akka.actor.ActorRef = scala.concurrent.Await.result(actorSelectionFut, 2.seconds)

        // Setup service call future:
        val responseFut = scala.concurrent.Future[String]
        {
            // Strip Taranos. prefix:
            val fixedServiceName = serviceName.replace("Taranos.", "")

            // Create inbox for this service call:
            val inbox = akka.actor.Inbox.create(Akka.system)

            // Send service call to cell director:
            inbox.send(cellDirectorRef,
                CellDirector.RequestMessages.ServiceCall(ServiceCall(fixedServiceName, serviceArgs.toVector)))

            // Wait for response from assigned cell:
            val message = inbox.receive(10.minutes)
            message match
            {
                case serviceResult: ServiceResult =>
                    EncodeReplyString(serviceResult._serviceResult, "", 0)

                case _ =>
                    EncodeReplyString(org.taranos.common.ServiceResult(), "", 0)
            }
        }

        // Setup timeout future:
        val timeoutFut = play.api.libs.concurrent.Promise.timeout('Timeout, 5.minutes)

        // Invoke futures:
        scala.concurrent.Future.firstCompletedOf(Seq(responseFut, timeoutFut)).map
        {
            case downstreamMessage: String =>
                downstreamMessage

            case timeout: Symbol =>
                timeout.toString()
        }
    }

    def Request (
        serviceName: String,
        serviceArgs: Seq[String]): scala.concurrent.Future[play.api.mvc.Result] =
    {
        val responseFut: scala.concurrent.Future[String] = SendLocal(serviceName, serviceArgs)

        responseFut.map
        {
            case response: String =>
                if (response.nonEmpty)
                {
                    val json = Json.parse(response)
                    play.api.mvc.Results.Ok(json)
                }
                else
                    play.api.mvc.Results.GatewayTimeout("")

            case _ => play.api.mvc.Results.BadGateway
        }
    }
}
