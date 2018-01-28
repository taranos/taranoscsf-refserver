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

package controllers.org.taranos.cc.api.common

import play.api.libs.json._


object Controller
{
    import scala.collection.mutable

    def ExtractDeleteArgs (
        orderedArgs: Seq[String],
        request: play.api.mvc.Request[play.api.mvc.AnyContent],
        formKey: String): Seq[String] =
        ExtractPutArgs(orderedArgs, request, formKey)

    def ExtractGetArgs (
        orderedArgs: Seq[String],
        fieldName: String,
        request: play.api.mvc.Request[play.api.mvc.AnyContent]): Seq[String] =
    {
        val queryString = request.queryString
        if (queryString contains fieldName)
        {
            val fieldValuesOpt = queryString get fieldName
            orderedArgs ++ {for {fieldValue <- fieldValuesOpt.get} yield fieldValue.toString}
        }
        else
            orderedArgs
    }

    def ExtractGetArgs (
        orderedArgs: Seq[String],
        request: play.api.mvc.Request[play.api.mvc.AnyContent]): Seq[String] =
    {
        val queryString = request.queryString
        orderedArgs ++ Seq(Json.stringify(Json.toJson(queryString)))
    }

    def ExtractPostArgs (
        orderedArgs: Seq[String],
        request: play.api.mvc.Request[play.api.mvc.AnyContent],
        formKey: String): Seq[String] =
        ExtractPutArgs(orderedArgs, request, formKey)

    def ExtractPutArgs (
        orderedArgs: Seq[String],
        request: play.api.mvc.Request[play.api.mvc.AnyContent],
        formKey: String): Seq[String] =
    {
        val contentType = request.headers("Content-Type")
        contentType match
        {
            case "application/json" =>
                val jsonOpt = request.body.asJson
                jsonOpt match
                {
                    case Some(topValue: JsValue) =>
                        (topValue \ formKey).validate[JsArray] match
                        {
                            case JsSuccess(subObjects, _) =>
                                var args = new mutable.ArrayBuffer[String]
                                for (subValue <- subObjects.value)
                                {
                                    subValue.validate[JsObject] match
                                    {
                                        case JsSuccess(subObject, _) =>
                                            args += Json.stringify(subObject)

                                        case JsError(errors) => None
                                    }
                                }
                                orderedArgs ++ args

                            case JsError(errors) =>
                                orderedArgs
                        }

                    case None =>
                        orderedArgs
                }

            case "application/x-www-form-urlencoded" =>
                val formMap = request.body.asFormUrlEncoded.getOrElse(Map.empty)
                if (formMap.contains(formKey))
                {
                    val updatesOpt = formMap.get(formKey)
                    val updates = updatesOpt.getOrElse(List.empty)
                    orderedArgs ++ {for {update <- updates} yield update.toString}
                }
                else
                    orderedArgs

            case _ =>
                orderedArgs
        }
    }
}
