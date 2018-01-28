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

package controllers

import play.api._
import play.api.http.HeaderNames._
import play.api.mvc._

object Application extends Controller
{

    def index = Action {
        Ok(views.html.index("Your new application is ready."))
    }

    def options (url: String) = Action {
        request =>
            def MakeHeaders = List(
                "Access-Control-Allow-Origin" -> "*",
                "Access-Control-Allow-Methods" -> "GET, POST, OPTIONS, DELETE, PUT",
                "Access-Control-Max-Age" -> "3600",
                "Access-Control-Allow-Headers" -> "Origin, Content-Type, Accept, Authorization",
                "Access-Control-Allow-Credentials" -> "true"
            )

            NoContent.withHeaders(MakeHeaders : _*)
    }

}