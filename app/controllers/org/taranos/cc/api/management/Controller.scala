//
// Taranos Cloud Sonification Framework: Reference Server
// Copyright (C) 2017 David Hinson, Netrogen Blue LLC (dhinson@netrogenblue.com)
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

package controllers.org.taranos.cc.api.management

import controllers.org.taranos.cc.api
import controllers.org.taranos.sa.local


object Controller
    extends play.api.mvc.Controller
{
    val _serviceAdapter = local.ServiceAdapter

    //
    // Cells
    //

    def DestroyCell = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyCell", args)
        }
    }

    def ReportCell = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportCell", args)
        }
    }
}
