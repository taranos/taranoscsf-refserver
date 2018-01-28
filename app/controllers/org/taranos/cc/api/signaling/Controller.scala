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

package controllers.org.taranos.cc.api.signaling

import controllers.org.taranos.cc.api
import controllers.org.taranos.sa.local


object Controller
    extends play.api.mvc.Controller
{
    val _serviceAdapter = local.ServiceAdapter

    //
    // Signal Trunks
    //

    def ReportTrunks = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportTrunks", args)
        }
    }

    def UpdateTrunks = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(), request, "ut")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateTrunks", args)
        }
    }

    def CreateTrunks = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(), request, "ct")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateTrunks", args)
        }
    }

    def DestroyTrunks = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(), request, "dt")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyTrunks", args)
        }
    }

    //
    // Signal Interfaces
    //

    def ReportSignalInterfaces (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalInterfaces", args)
        }
    }

    def UpdateSignalInterfaces (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usi")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalInterfaces", args)
        }
    }

    def CreateSignalInterfaces (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "csi")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalInterfaces", args)
        }
    }

    def DestroySignalInterfaces (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsi")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalInterfaces", args)
        }
    }

    //
    // Signal Ports
    //

    def LookupSignalPort (
        trunkKey: String,
        signalPortAlias: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey, signalPortAlias), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.LookupSignalPort", args)
        }
    }

    def ReportSignalPorts (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey, "~~si"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalPorts", args)
        }
    }

    def ReportSignalPortsOfInterface (
        trunkKey: String,
        interfaceKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey, interfaceKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalPorts", args)
        }
    }

    def UpdateSignalPorts (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usp")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalPorts", args)
        }
    }

    def CreateSignalPorts (
        trunkKey: String,
        signalInterfaceKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey, signalInterfaceKey), request, "csp")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalPorts", args)
        }
    }

    def DestroySignalPorts (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsp")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalPorts", args)
        }
    }

    //
    // Signal Sources
    //

    def ReportSignalSources (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalSources", args)
        }
    }

    def UpdateSignalSources (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "uss")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalSources", args)
        }
    }

    def CreateSignalSources (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "css")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalSources", args)
        }
    }

    def DestroySignalSources (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dss")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalSources", args)
        }
    }

    //
    // Signal Sinks
    //

    def ReportSignalSinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalSinks", args)
        }
    }

    def UpdateSignalSinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usk")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalSinks", args)
        }
    }

    def CreateSignalSinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "csk")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalSinks", args)
        }
    }

    def DestroySignalSinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsk")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalSinks", args)
        }
    }

    //
    // Signal Links
    //

    def ReportSignalLinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalLinks", args)
        }
    }

    def UpdateSignalLinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usl")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalLinks", args)
        }
    }

    def CreateSignalLinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "csl")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalLinks", args)
        }
    }

    def DestroySignalLinks (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsl")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalLinks", args)
        }
    }

    //
    // Signal Taps
    //

    def ReportSignalTaps (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalTaps", args)
        }
    }

    def UpdateSignalTaps (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "ust")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalTaps", args)
        }
    }

    def CreateSignalTaps (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "cst")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalTaps", args)
        }
    }

    def DestroySignalTaps (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dst")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalTaps", args)
        }
    }

    //
    // Signal Inputs
    //

    def ReportSignalInputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalInputs", args)
        }
    }

    def UpdateSignalInputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usmi")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalInputs", args)
        }
    }

    def CreateSignalInputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "csmi")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalInputs", args)
        }
    }

    def DestroySignalInputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsmi")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalInputs", args)
        }
    }

    //
    // Signal Bridges
    //

    def ReportSignalBridges (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalBridges", args)
        }
    }

    def UpdateSignalBridges (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usmb")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalBridges", args)
        }
    }

    def CreateSignalBridges (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "csmb")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalBridges", args)
        }
    }

    def DestroySignalBridges (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsmb")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalBridges", args)
        }
    }

    //
    // Signal Outputs
    //

    def ReportSignalOutputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(trunkKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSignalOutputs", args)
        }
    }

    def UpdateSignalOutputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(trunkKey), request, "usmo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSignalOutputs", args)
        }
    }

    def CreateSignalOutputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(trunkKey), request, "csmo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSignalOutputs", args)
        }
    }

    def DestroySignalOutputs (trunkKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(trunkKey), request, "dsmo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySignalOutputs", args)
        }
    }
}
