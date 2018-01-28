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

package controllers.org.taranos.cc.api.rendering

import controllers.org.taranos.cc.api
import controllers.org.taranos.sa.local


object Controller
    extends play.api.mvc.Controller
{
    val _serviceAdapter = local.ServiceAdapter

    //
    // Fields
    //

    def ReportFields = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportFields", args)
        }
    }

    def UpdateFields = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(), request, "uf")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateFields", args)
        }
    }

    def CreateFields = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(), request, "cf")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateFields", args)
        }
    }

    def DestroyFields = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(), request, "df")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyFields", args)
        }
    }

    //
    // Field Emitters
    //

    def ReportFieldEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportFieldEmitters", args)
        }
    }

    def UpdateFieldEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "ufe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateFieldEmitters", args)
        }
    }

    def CallFieldEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "mfe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CallFieldEmitters", args)
        }
    }

    def CreateFieldEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey), request, "cfe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateFieldEmitters", args)
        }
    }

    def DestroyFieldEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dfe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyFieldEmitters", args)
        }
    }

    //
    // Field Oscillators
    //

    def ReportFieldOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~fe"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportFieldOscillators", args)
        }
    }

    def ReportOscillatorsOfEmitter (
        fieldKey: String,
        fieldEmitterKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, fieldEmitterKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportFieldOscillators", args)
        }
    }

    def UpdateFieldOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "ufo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateFieldOscillators", args)
        }
    }

    def CallFieldOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "mfo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CallFieldOscillators", args)
        }
    }

    //    def CreateFieldOscillators (
    //        fieldKey: String,
    //        fieldEmitterKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey, fieldEmitterKey), request, "cfo")
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.CreateFieldOscillators", args)
    //        }
    //    }

    //    def DestroyFieldOscillators (fieldKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dfo")
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.DestroyFieldOscillators", args)
    //        }
    //    }

    //
    // Subjects
    //

    def ReportSubjects (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSubjects", args)
        }
    }

    def UpdateSubjects (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "us")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSubjects", args)
        }
    }

    def CreateSubjects (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey), request, "cs")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSubjects", args)
        }
    }

    def DestroySubjects (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "ds")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySubjects", args)
        }
    }

    //
    // Subject Emitters
    //

    def ReportSubjectEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~s"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSubjectEmitters", args)
        }
    }

    def ReportEmittersOfSubject (
        fieldKey: String,
        subjectKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, subjectKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSubjectEmitters", args)
        }
    }

    def UpdateSubjectEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "use")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSubjectEmitters", args)
        }
    }

    def CallSubjectEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "mse")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CallSubjectEmitters", args)
        }
    }

    def CreateSubjectEmitters (
        fieldKey: String,
        subjectKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey, subjectKey), request, "cse")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateSubjectEmitters", args)
        }
    }

    def DestroySubjectEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dse")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroySubjectEmitters", args)
        }
    }

    //
    // Subject Oscillators
    //

    def ReportSubjectOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~se"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSubjectOscillators", args)
        }
    }

    def ReportOscillatorsOfSubjectEmitter (
        fieldKey: String,
        subjectEmitterKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, subjectEmitterKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportSubjectOscillators", args)
        }
    }

    def UpdateSubjectOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "uso")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateSubjectOscillators", args)
        }
    }

    def CallSubjectOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "mso")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CallSubjectOscillators", args)
        }
    }

    //    def CreateSubjectOscillators (
    //        fieldKey: String,
    //        subjectEmitterKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey, subjectEmitterKey), request, "cso")
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.CreateSubjectOscillators", args)
    //        }
    //    }

    //    def DestroySubjectOscillators (fieldKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dso")
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.DestroySubjectOscillators", args)
    //        }
    //    }

    //
    // Probes
    //

    def ReportProbes (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbes", args)
        }
    }

    def UpdateProbes (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "up")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateProbes", args)
        }
    }

    def CreateProbes (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey), request, "cp")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateProbes", args)
        }
    }

    def DestroyProbes (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dp")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyProbes", args)
        }
    }

    //
    // Probe Emitters
    //

    def ReportProbeEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~p"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbeEmitters", args)
        }
    }

    def ReportEmittersOfProbe (
        fieldKey: String,
        probeKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbeEmitters", args)
        }
    }

    def UpdateProbeEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "upe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateProbeEmitters", args)
        }
    }

    def CallProbeEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "mpe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CallProbeEmitters", args)
        }
    }

    def CreateProbeEmitters (
        fieldKey: String,
        probeKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey, probeKey), request, "cpe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateProbeEmitters", args)
        }
    }

    def DestroyProbeEmitters (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dpe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyProbeEmitters", args)
        }
    }

    //
    // Probe Oscillators
    //

    def ReportProbeOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~pe"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbeOscillators", args)
        }
    }

    def ReportOscillatorsOfProbeEmitter (
        fieldKey: String,
        probeEmitterKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeEmitterKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbeOscillators", args)
        }
    }

    def UpdateProbeOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "upo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateProbeOscillators", args)
        }
    }

    def CallProbeOscillators (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "mpo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CallProbeOscillators", args)
        }
    }

    //    def CreateProbeOscillators (
    //        fieldKey: String,
    //        probeEmitterKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey, probeEmitterKey), request, "cpo")
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.CreateProbeOscillators", args)
    //        }
    //    }

    //    def DestroyProbeOscillators (fieldKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dpo")
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.DestroyProbeOscillators", args)
    //        }
    //    }

    //
    // Probe Collectors
    //

    def LookupProbeCollector (
        fieldKey: String,
        probeCollectorAlias: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeCollectorAlias), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.LookupProbeCollector", args)
        }
    }

    def ReportProbeCollectors (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~p"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbeCollectors", args)
        }
    }

    def ReportCollectorsOfProbe (
        fieldKey: String,
        probeKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportProbeCollectors", args)
        }
    }

    def UpdateProbeCollectors (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "upc")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateProbeCollectors", args)
        }
    }

    def CreateProbeCollectors (
        fieldKey: String,
        probeKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPostArgs(Seq(fieldKey, probeKey), request, "cpc")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.CreateProbeCollectors", args)
        }
    }

    def DestroyProbeCollectors (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractDeleteArgs(Seq(fieldKey), request, "dpc")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.DestroyProbeCollectors", args)
        }
    }

    //
    // Waveforms
    //

    //    def ReportWaveformsAtProbe (
    //        fieldKey: String,
    //        probeKey: String) = play.api.mvc.Action.async
    //    {
    //        request =>
    //        {
    //            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeKey), request)
    //            if (args.isEmpty)
    //                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
    //            else
    //                Controller._serviceAdapter.Request("Taranos.ReportWaveformsAtProbe", args)
    //        }
    //    }

    def ReportWaveformsAtProbeCollector (
        fieldKey: String,
        probeCollectorKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeCollectorKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportWaveformsAtProbeCollector", args)
        }
    }

    def ReportWaveformsAtSampler (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportWaveformsAtSampler", args)
        }
    }

    //
    // Emitter Patches
    //

    def ReportEmitterPatches (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportEmitterPatches", args)
        }
    }

    def ReportPatchOfFieldEmitter (
        fieldKey: String,
        fieldEmitterKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, fieldEmitterKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportPatchOfFieldEmitter", args)
        }
    }

    def ReportPatchOfSubjectEmitter (
        fieldKey: String,
        subjectEmitterKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, subjectEmitterKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportPatchOfSubjectEmitter", args)
        }
    }

    def ReportPatchOfProbeEmitter (
        fieldKey: String,
        probeEmitterKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeEmitterKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportPatchOfProbeEmitter", args)
        }
    }

    def UpdateEmitterPatches (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "usmpe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateEmitterPatches", args)
        }
    }

    //
    // Oscillator Patches
    //

    def ReportOscillatorPatches (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, "~~smpe"), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportOscillatorPatches", args)
        }
    }

    def ReportOscillatorPatchesOfEmitterPatch (
        fieldKey: String,
        emitterPatchKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, emitterPatchKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportOscillatorPatches", args)
        }
    }

    def ReportPatchOfFieldOscillator (
        fieldKey: String,
        fieldOscillatorKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, fieldOscillatorKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportPatchOfFieldOscillator", args)
        }
    }

    def ReportPatchOfSubjectOscillator (
        fieldKey: String,
        subjectOscillatorKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, subjectOscillatorKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportPatchOfSubjectOscillator", args)
        }
    }

    def ReportPatchOfProbeOscillator (
        fieldKey: String,
        probeOscillatorKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, probeOscillatorKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportPatchOfProbeOscillator", args)
        }
    }

    def UpdateOscillatorPatches (fieldKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey), request, "usmpo")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateOscillatorPatches", args)
        }
    }

    //
    // Oscillator Patch Envelopes
    //

    def ReportOscillatorPatchEnvelopes (
        fieldKey: String,
        oscillatorPatchKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractGetArgs(Seq(fieldKey, oscillatorPatchKey), request)
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.ReportOscillatorPatchEnvelopes", args)
        }
    }

    def UpdateOscillatorPatchEnvelopes (
        fieldKey: String,
        oscillatorPatchKey: String) = play.api.mvc.Action.async
    {
        request =>
        {
            val args = api.common.Controller.ExtractPutArgs(Seq(fieldKey, oscillatorPatchKey), request, "usmpoe")
            if (args.isEmpty)
                scala.concurrent.Future(BadRequest)(scala.concurrent.ExecutionContext.Implicits.global)
            else
                Controller._serviceAdapter.Request("Taranos.UpdateOscillatorPatchEnvelopes", args)
        }
    }
}
