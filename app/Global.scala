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

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.GlobalSettings
import play.api.http.HeaderNames
import play.api.mvc.{Filter, RequestHeader, Result, WithFilters}
import play.libs.Akka

import org.taranos.mc.CellDirector


object Global
    extends WithFilters(CorsFilter)
    with GlobalSettings
{
    private
    var _cellDirector = None: Option[akka.actor.ActorRef]

    override
    def onStart (app: play.api.Application): Unit =
    {
        {
            val cellDirector = Akka.system.actorOf(CellDirector.MakeProps, "cellDirector")
            _cellDirector = Some(cellDirector)

            // Tell cell director to start:
            cellDirector ! CellDirector.RequestMessages.Start
        }
    }

    override
    def onRouteRequest (request: play.api.mvc.RequestHeader): Option[play.api.mvc.Handler] =
    {
        var overrideMethod = request.method
        import scala.util.control.Breaks._
        breakable
        {
            for (header <- request.headers.toSimpleMap)
                if (header._1.toLowerCase == "x-http-method-override")
                {
                    overrideMethod = header._2
                    break()
                }
        }

        val finalRequest =
            if (overrideMethod == request.method)
                request else
            {
                request.copy(
                    request.id,
                    request.tags,
                    request.uri,
                    request.path,
                    overrideMethod,
                    request.version,
                    request.queryString,
                    request.headers,
                    request.remoteAddress,
                    request.secure)
            }

        super.onRouteRequest(finalRequest)
    }
}


// https://gist.github.com/mitchwongho/78cf2ae0276847c9d332
object CorsFilter
    extends Filter
{
    def apply (nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] =
    {
        nextFilter(requestHeader).map{ result =>
            result.withHeaders(HeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN -> "*",
                HeaderNames.ALLOW -> "*",
                HeaderNames.ACCESS_CONTROL_ALLOW_METHODS -> "POST, GET, PUT, DELETE, OPTIONS",
                HeaderNames.ACCESS_CONTROL_ALLOW_HEADERS -> "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent"
            )
        }
    }
}
