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

name := "taranos-refserver"
version := "0.5"

scalaVersion := "2.11.8"

//libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

unmanagedResourceDirectories in Test += baseDirectory.value / "target/web/public/test"

lazy val refserver = (project in file(".")).enablePlugins(PlayScala, LauncherJarPlugin)

