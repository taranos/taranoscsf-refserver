# *Taranos Reference Server* #

### What is Taranos Reference Server? ###

This is the API reference microservice component of [_Taranos:CSF_](https://github.com/taranos/taranoscsf).  It is a basic Play Framework implementation intended to serve as a Taranos web API functional reference for education, demonstration and continuing development purposes.  **Beware:  It is *not* a production-ready server**.  By design it is as unencumbered as possible.  However, given the rich set of capabilities that Play provides it could easily serve as the foundation for one.

As a web API wrapper for the drop-in [*Taranos Core*](https://github.com/taranos/taranoscsf-core) service core component, the reference server requires that the core component be built or otherwise obtained first.  Refer to that project for specific instructions.

### Getting Started ###

Step 1:  The Taranos Project uses [_SBT_](http://www.scala-sbt.org/) for Scala build management.  It can be downloaded from [here](http://www.scala-sbt.org/download.html).

Step 2:  Ensure that the [*Taranos Core*](https://github.com/taranos/taranoscsf-core) artifact (``taranos-core_*.jar``) is built and ready.

Step 3:  Build and run the server as follows:

```
$ git clone https://github.com/taranos/taranoscsf-refserver.git
$ cd taranoscsf-refserver
$ mkdir lib
$ cp <taranos-core_*.jar> lib/
$ sbt run
```

Finally, to make a stand-alone deployable package for the server run:

```
$ sbt universal:packageBin
```

This will create an [_SBT Native Packager_](https://github.com/sbt/sbt-native-packager) universal .zip deployable named ``target/universal/taranos-refserver-*.zip`` ready for Linux, Windows and OSX use.  Note that SBT Native Packager provides many other target options as well.

### Next Steps ###

- Explore project [code documentation](http://rawgit.com/taranos/taranoscsf-refserver/master/docs/api/index.html)

- Learn [_Taranos:CSF_ concepts](https://github.com/taranos/taranoscsf/wiki/Domain-Model-Concepts)

- Take the Pseudo-API tutorial

- Read FAQs

- Go to the [Taranos Project](https://github.com/taranos/taranoscsf) site

