#
# Taranos Cloud Sonification Framework Tutorial #1:  Switched-On Taranos
# Copyright 2017 David Hinson, Netrogen Blue LLC (dhinson@netrogenblue.com)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# This is the first of a series of living tutorials intended to demonstrate the capabilities and usage of the Taranos
# Cloud Sonification Framework.  They are alive in the sense that they contain functioning code ready to be run and then
# poked, prodded and dissected on the student's lab table.  The user is encouraged to experiment with the code as
# desired to accelerate the learning process.
#
# The tutorial samples take advantage of the Taranos Python "Pseudo-API", or PAPI, to demonstrate the higher level
# semantics of the operations supported by the Taranos server.  This spares the user from having to dismember the HTTP
# request and response bodies of the actual Taranos web API service calls in order to obtain the same information.  It
# should be stressed however that the web API is the canonical interface for interacting with Taranos and that the PAPI
# is simply a supporting tool.  The PAPI is not guaranteed to support all of the web API's capabilities.
#
# To use the PAPI ensure you have the location of the Python package named taranoscsfpapi in your PYTHONPATH.

# The PAPI namespace is organized into a number of different concerns, including services for signaling, rendering, and
# general accessor management.  Different accessors will require some but not others depending on their roles.  For
# instance, an accessor that serves only as a signal provider would not require the rendering namespace.  Likewise a
# dedicated sonification rendering device may have no use for the signaling namespace.  This tutorial will make use of
# all of those mentioned so we'll import all of their modules:
from taranoscsfpapi.management import *
from taranoscsfpapi.rendering import *
from taranoscsfpapi.signaling import *

# To get started we need to initialize the PAPI itself.  This will communicate with the designated Taranos server
# to fetch some initial configuration information used to simplify the PAPI interactions.  Setting the
# is_verbose argument to True will enable printing of the HTTP request and response data for every service call.
# This will help you understand the nature of the actual Taranos web API calls performed underneath.
#
# You will see that all request and response data is encapsulated within JSON objects.  JSON is the standard
# format for Taranos messaging.  You will also see that the JSON name strings are highly compacted using a minimalist
# approach to symbol naming.  This is by design with consideration to the memory and bandwidth limitations of
# resource-constrained devices.  It is a goal of the Taranos project to enable sonification sharing among as
# many diverse classes of devices as possible from the cloud-hosted to the wearable.  Thus it is a general
# philosophy of the project that when it comes to issues of data communication the limitations of devices will
# be accommodated over those of humans.
papi_init(server_url='http://localhost:9000', is_verbose=True)

# For tutorial purposes our next task is to reset the Taranos simulation cell associated with our accessor so
# that we are starting with a clean configuration.  This will destroy any prior model elements owned by our
# accessor and then allocate and initialize new default signal trunk and wavefield models for it.  [Note:  As of
# [February 2017] the Taranos Reference Server supports only a single implied accessor.]
destroy_cell()

# The newly created cell now contains a default wavefield instance.  Here we will fetch a report of its configuration.
# All reports contain sections which are aligned to model element propertysets.  Every report will contain the meta
# propertyset since it applies to every model element.  Below we are also requesting the refs ("r") propertyset which
# will contain all of the keys of the other elements that the field knows.
rf = report_field(sections='r')
print(rf)

# Notice that within the refs section there is a reference to the field's default field emitter.
assert(rf['r']['_fe'])
# We know this because within the Taranos API "_" is shorthand for "default" and "fe" is shorthand for "field emitter".
#
# Emitters are the sources of the waveforms modeled by Taranos.  They own collections of oscillators which determine the
# qualities of the waveforms produced.  They may be attached to wavefield bodies, known as subjects and probes, or they
# may be associated with the wavefield itself as is the case with field emitters.

# Next we will extract the default field emitter's key from the field report.  All Taranos model elements are referenced
# by an immutable key string.  This key will be used in subsequent service calls to refer to this emitter.
kfe = rf['r']['_fe']

assert(kfe[-3:] == '~fe')
# Notice that the key is a UUID followed by the substring "~fe".  Taranos uses the concept of typed-keys where every
# model element key is appended with a suffix that indicates its model element type.  This makes it a bit easier for
# humans to reason about and work with API service calls involving multiple keys.

# From a modeling perspective, emitters do not produce waveforms until the wavefield is sampled by a waveform collector.
# The simplest way to query a collector is by evoking a field "sampler".  Samplers are ephemeral collectors which exist
# only for the duration of the querying service call.  While convenient, they have no persistence so they cannot be
# shared by multiple service accessors.  For that you would use the other kind of collector, the probe collector, which
# will be described in a later tutorial.
#
# Being field entities like probes and subjects, samplers have geometrical coordinates within the field.  A field
# entity's default coordinates are always the origin position of the field, which in the case of a 2-dimensional field
# is {0, 0}.  They also have other properties which affect their waveform detection performance.  The default values for
# those properties are acceptable for now.
#
# Here we will sample the field for waveforms using a standard sampler which is configured by default to be located at
# the field origin position.
rw = report_waveforms()
print(rw)

# Notice however that the report is empty:
assert(not rw)
# This means the collector detected no waveforms to report.  Why is this?  The answer can be found by diving a little
# deeper into the emitter's oscillators.

# First, let's fetch another field emitter report but this time one that includes a section ("r") that contains the
# emitter's references to other elements:
rfe = report_field_emitter(key=kfe, sections='r')
print(rfe)

# Notice that, just as the default field is associated with a default field emitter, that emitter is associated with a
# default field oscillator:
assert(rfe['r']['_fo'])

# Next, let's get a field oscillator report that also includes its references.
kfo = rfe['r']['_fo']
rfo = report_field_oscillator(key=kfo, sections='r')
print(rfo)

# Notice that the oscillator is associated with an oscillator patch:
assert(rfo['r']['smpo'])

# Every oscillator has a patch which determines how the energy channeled to it by its parent emitter is mapped into
# various wavefield properties.  Since an oscillator will always have an associated patch and never more than one it is
# simply referred to by the shorthand "smpo" (no underscore).
#
# The oscillator patch is our introduction to a kind of model element known as a signal modulator.  Signal modulators
# provide the rendering model's access to the underlying signaling model.  Signaling will be explained in more detail
# in a later tutorial.  For now we will enjoy the fact that the signaling model does not have to be well-understood to
# make practical use of rendering model elements.
#
# Let's fetch an oscillator patch report that includes its references:
ksmpo = rfo['r']['smpo']
rsmpo = report_oscillator_patch(key=ksmpo, sections='r')
print(rsmpo)

# Notice that the oscillator patch has 5 signal output modulators ("smo") associated with it.  They are actually
# considered to be child elements of the patch, meaning the patch controls their model lifetimes.
assert(rsmpo['r']['smo']['l'])
assert(rsmpo['r']['smo']['p'])
assert(rsmpo['r']['smo']['r'])
assert(rsmpo['r']['smo']['s'])
assert(rsmpo['r']['smo']['t'])

# Signal output modulators provide the final stage of signal processing to the overlying rendering model elements.
# That is, the rendering model algorithms operate directly on the signal values provided by output modulators.  Recall
# that Taranos models five separate waveform qualities:  loudness ("l"), pitch ("p"), period ("r"), shape ("s") and
# tone ("t").  There is a signal output modulator associated with each of these qualities for each oscillator of the
# rendering model.
#
# The answer to the question of why our sampler detected no waveforms can be found within the output modulators.

# Let's get another oscillator patch report but this time one that includes child elements ("c" section) and
# their states ("s" section):
rsmpo = report_oscillator_patch(key=ksmpo, sections='cs')
print(rsmpo)

# Notice that the patch report now contains a report for each of the five child signal output modulators.  Within their
# state propertysets ("s") there is a property that represents the modulators current signal value ("s").  As you can
# see they are empty:
assert(not rsmpo['rsmo']['l']['s']['s'])
assert(not rsmpo['rsmo']['p']['s']['s'])
assert(not rsmpo['rsmo']['r']['s']['s'])
assert(not rsmpo['rsmo']['s']['s']['s'])
assert(not rsmpo['rsmo']['t']['s']['s'])

# As you may have guessed by now, the sampler reported no detectable waveforms because the oscillators associated with
# the only emitter present in the field -- the default field emitter -- have not yet been provided with energy.  This
# is a result of their underlying signal output modulators not being provided with any signals from their parent
# oscillator patch.
#
# So how are signals provided, or propagated, to oscillator patches?  It's surprisingly easy.
#
# Just as signal output modulators occupy the tail ends of the signal modulation chains, model elements known as
# signal input modulators occupy the heads.  We won't go into detail about signal input modulators now -- again,
# we don't have to.  It's sufficient to know that every oscillator patch has an associated emitter patch parent,
# and every emitter patch has an associated signal input modulator.  Furthermore, every signal input modulator
# that is associated with an emitter also has an associated signal port.  It is these signal ports which are
# exposed to the outside world awaiting signal input from accessors like this script!

# To energize an emitter's oscillators we must send a signal to the emitter's signal port.  In order to do that,
# we must first know the signal port's key since it is a model element like any other.
#
# How does one acquire the key of a signal port associated with a given emitter?  In addition to being assigned an
# immutable key, as is the case with every over model element, signal ports are special in that they can also be
# assigned a mutable key alias.  Given an alias, a prospective accessor can then call the signal port lookup service to
# exchange it for the port's actual key.  Aliases allow a user to publish a meaningful or guessable reference to a model
# element instead of its key.
#
# In the case of an emitter, the signal port associated with the emitter's automatically-assigned signal input modulator
# is created with its alias set to the meta-tag value of the emitter.  Thus, one only needs to know the value of the
# emitter's tag to lookup the signal port's key.

# So to fetch the key of the default field emitter's signal port we do the following ...
# First, determine the emitter's tag value from its meta propertyset:
tfe = rfe['m']['t']
# Next, request a signal port lookup using the derived tag:
lsp = lookup_signal_port(alias=tfe)
# Finally, extract the signal port key from the lookup report.  Since a lookup request can be for multiple ports at once
# the keys in the response are indexed by the tag values.
ksp = lsp[tfe]

# Now we have the information we need to provide the signal port with a signal that will propagate through the signaling
# model to the emitter and ultimately to the emitter's oscillator.
#
# Signals are represented by a single scalar value.  They come in two kinds, continuous and discrete.  We'll
# provide a continuous signal which is constrained to having a real number magnitude in the range of 0 to 1.0.
# (Note: Currently [February 2017] only continuous signals are supported as input to signal ports, however
# full support for discrete is not far behind.)
update_signal_port(key=ksp, signal='0.5')

# Now let's sample the wavefield again:
rw2 = report_waveforms()
print(rw2)

# This time we get a report!
assert(len(rw2) == 1)
assert(rw2 == [{'q': {'t': '0', 'w': 'default.tws', 'l': '82.3', 'p': '6272.0', 'r': '1', 's': '0'}}])

# What does this mean?  A waveform report is a list of JSON objects where each object represents a waveform detected by
# the sampling collector and where the waveforms are ordered by distance from the collector to the emitter of the
# waveform.  The waveform from the closest emitter will always be first in the list.  (This feature allows
# low-capability clients to easily perform proximity-concern filtering.)
#
# Within each waveform report object is an object named "q" which contains the waveform qualities.  In this case, we
# have a waveform with an apparent loudness ("l") of 82.3 decibels, a pitch ("p") of 6272 hertz, and a cyclic period
# ("r") of 1 second.  The shape ("s") and tone ("t") of the waveform are both "0", which are integer identities
# meaningful to the waveset ("w") named "default.tws".
#
# You may be wondering how the loudness and pitch qualities in particular are derived.  The loudness quality is
# approximately 2/3 of the maximum reportable loudness which is approximately 130 decibels.  This is due to the default
# loudness quality envelope assigned to oscillators.  We're not ready to discuss oscillator envelopes just yet, just
# know that it is a fixed quantity due to how the field emitter is configured and the fact that the implied distance
# from all field emitters is 1 meter.
#
# The observed pitch quality happens to be 50% of the maximum pitch which is 12,544 hertz.  Can you guess why?  Correct!
# It is because the magnitude of the signal we sent to the emitter's port was 0.5.  Again, this is due to the default
# envelope assigned to the oscillator for the pitch quality which happens to be a unity gain function.  We can confirm
# this by sending another signal to the port and fetching the new pitch:
update_signal_port(key=ksp, signal='0.6')
rw3 = report_waveforms()
print(rw3)
assert(rw3[0]['q']['p'] == '7526.4')
# Bingo.  7526.4 is 60% of 12544.

# Now before we move on, let's sample the wavefield once more, but rather than using the default wavefield position at
# the origin ({0, 0}) let's specify a different position at {0.5, 0}.  Since the default field is configured as a
# 2-dimensional sphere (the "2DS" geometry), this means the position will be at 0.5 north latitude where the northern
# pole is at latitude 1.0.
rw4 = report_waveforms(position=[0.5, 0])
print(rw4)

# Notice that the new waveform report is identical to the previous one even though we have made a significant change to
# the position of the sampler within the wavefield:
assert(rw3[0] == rw4[0])

# This is what we expect because a field emitter effectively produces field-wide ambient waveforms.  No point in the
# field is any closer to or farther away from a field emitter than any other point.  That is not the case with subject
# or probe emitters, which we'll explore in the next tutorial.
#
# Using samplers and field emitters is the simplest way of producing useful results with Taranos.  But the introduction
# would not be complete without an explanation of envelopes, the secret sauce of the framework's flexibility.
#
# Conceptually, an envelope is a function that maps a single input scalar value into a single output scalar value and
# where the only argument is the input scalar.  Internally, the envelope stores a 16 by 16 X/Y grid and points on that
# grid called poles.  There are two implicit poles, at X = 0 (min X) and X = 15 (max X).  There can also be up to three
# additional poles with user-defined X values, however they cannot share X values with any other poles.  Thus, an
# envelope can support up to five poles as long as they all have unique X values.  The Y value of both the implicit and
# any additional poles is user-defined in the range of 0 to 15 inclusive.
#
# When an envelope is invoked by a model during simulation its poles define slopes which are used to interpolate the
# input scalar value to an output scalar value.  For example, the default envelope configuration, also known as the
# unity gain envelope, has two poles at grid positions {0, 0} and {15, 15} effectively defining a single slope with the
# function Y = X.  For a continuous scalar input of 0.3 the output scalar would be 0.3.  On the other hand, the inverse
# unity gain envelope with poles at grid positions {0, 15} and {15, 0} defines a slope with the function Y = 1 - X.
# For a continuous scalar input of 0.3 the output scalar would be 0.7.  An envelope with poles at grid positions {0, 10}
# and {15, 5} would define a slope with the function Y = (2 - x) / 3, where a scalar input of 0.3 would produce the
# output 0.57.
#
# As you can see, even with the single slope of a two-pole envelope there is a large space of potential mapping
# functions.  Taranos supports envelopes of up to five poles for up to four contiguous slopes and an even greater
# variety of mapping functions.  (The astute user may even recognize this four-slope pattern as one commonly used by
# electronic music synthesizers to define ADSR filter envelopes!)
#
# Now let's see what this means in practical terms.  Within the rendering and signal models there are four kinds of
# envelopes in use.  They are:
# - Emitter channel envelopes
# - Oscillator patch envelopes
# - Collector distance lobe envelopes
# - Collector bearing lobe envelopes
#
# Of those, oscillator patch envelopes are the ones most likely to be modified so we'll take a quick look at them now.
#
# There are two basic ways to directly modify an oscillator patch envelope:
#
# The first method is to make an update service call to the envelope itself using the
# update_oscillator_patch_envelope() service.  Recall that every signal oscillator has associated with it five signal
# output modulators, one for each waveform quality.  Likewise, an oscillator patch contains five corresponding
# envelopes, one for each of the output modulators.  The service accepts three arguments:  the key of the oscillator
# patch to update, the key of the contained envelope to modify, and an envelope definition JSON object.  The envelope
# definition contains an ordered list of pole definition JSON objects which specify the X and Y values of the envelope's
# poles.  The ordering of the list must be from min X to max X.
#
# Let's modify the field oscillator's pitch envelope to change it from unity gain to inverse unity gain.  The easiest
# (albeit not the most efficient) way to provide a modified patch envelope is to fetch an existing one and use it as a
# template for the changes.  To do that we call the appropriate reporting service providing the oscillator patch's key
# and the pitch envelope's key:
ksmpo = rsmpo['m']['k']
env = report_oscillator_patch_envelope(patch_key=ksmpo, key='p')

assert(env == {'dp': [{'x': 'min', 'y': 'min'}, {'x': 'max', 'y': 'max'}]})
# As you can see the envelope has a single poles definition ("dp") element defining two poles.  The string "min"
# is a symbolic representation for the number 0 and "max" for 15.  Either the symbolic strings or the numbers
# can be used.
#
# To invert the slope defined by the poles we simply have to flip the Y values and update the envelope:
env['dp'][0]['y'] = 'max'
env['dp'][1]['y'] = 'min'
update_oscillator_patch_envelope(patch_key=ksmpo, key='p', envelope_def=env)

# Now when we sample the wavefield again,
rw5 = report_waveforms()
print(rw5)
# As expected we will get a different result because the slope used to compute the quality's pitch has been changed:
assert(rw5 != rw4)
# Specifically,
assert((rw5[0]['q']['p'] != rw4[0]['q']['p']) and rw5[0]['q']['p'] == '5017.6')

# Increasing the signal magnitude should now decrease the pitch instead of increasing it as was the case with the
# default unity gain envelope:
update_signal_port(key=ksp, signal='0.7')
rw6 = report_waveforms()
print(rw6)
assert(rw6[0]['q']['p'] == '3763.2' and (float(rw6[0]['q']['p']) < float(rw5[0]['q']['p'])))

# While this method of updating an envelope's poles is direct, it is rather cumbersome as it involves manipulating a
# multi-layered JSON object.  It also suffers from the lack of a convenient pole definition representation (other than
# JSON serialization itself which can be problematic to some applications) which is important should we want to
# export an envelope's configuration to or import it from some other application.
#
# To address these concerns, there is a second method for updating an oscillator patch envelope which involves making a
# macro service call to the envelope's parent oscillator.  Macros are high-level operations that exist to simplify the
# web API semantics by performing functions that would otherwise require multiple service calls.  They are recognizable
# in the PAPI as having function names beginning with the prefix "call_".
#
# To demonstrate this let's return the oscillator's pitch quality envelope back to unity gain:
call_field_oscillator(key=kfo, macro=set_pitch_poles('0FF'))

# And then take another sample:
rw7 = report_waveforms()
print(rw7)
assert((rw7 != rw6) and rw7[0]['q']['p'] == '8780.8')
# As you can see, 8780.8 is 70% of 12544, exactly what we expect from the pitch quality with unity gain and a signal
# magnitude of 0.7.  Pretty easy, huh?
#
# But what is "0FF"?  Note that is not the word "off" in upper case!  It is in fact a short hexadecimal string
# representing the X/Y values of the two envelope poles.  This is known as the "packed poles" notation and taking a bit
# of time to become familiar with it will definitely be worth the effort and the author has even occasionally found them
# to be fun to work with.
#
# The format is simple.  Recall that an envelope can be defined with up to 5 poles.  A pole is defined by two integer
# values each in the range 0 to 15 and both representing the X and Y of that pole's envelope grid point.  Both can
# therefore be represented by two hexadecimal digits.  For pole #1 let's represent the two digits like this:
#
#   X1 Y1
#
# To add a second pole definition we can append the first digits with the next two digits like so:
#
#   X1 Y1 X2 Y2
#
# This is complete, however there is a bit of redundancy.  Recall that every envelope has an implicit pole at
# X = 0.  So the X1 specification is unnecessary.  Its reduced form would therefore be this:
#
#   Y1 X2 Y2
#
# Which for the two poles of the unity gain envelope -- {{0, 0}, {15, 15}} -- equates to:
#
#   0 F F
#
# How far can this notation be taken?  There can be at most 5 poles so a full non-reduced digit representation would
# look like this:
#
#   X1 Y1 X2 Y2 X3 Y3 X4 Y4 X5 Y5
#
# Eliminating the redundant X1 digit would reduce it to this:
#
#   Y1 X2 Y2 X3 Y3 X4 Y4 X5 Y5
#
# But now we've introduced another redundancy.  Can you spot it?  If we're supplying definitions for all 5 poles then we
# know the definitions include the implicit pole at X = 15.  Therefore X5 is also implied.  So the digit representation
# for a full set of poles would be expected to be:
#
#   Y1 X2 Y2 X3 Y3 X4 Y4 Y5
#
# This happens to be an 8 hexadecimal digit sequence which can be very conveniently -- and not coincidentally --
# represented by a 4-byte quantity.  A storage-friendly quantity which can be easily and efficiently represented by most
# applications and runtime architectures.
#
# Note however that a cost of this convenience is that it does depend on a few usage rules.  They are:
#   1.  The first digit is always the Y value of the implicit X = 0 pole.
#   2.  There can be no duplicate X values and the pole definitions must be listed in increasing X value order.
#   3.  Any missing Y value is assumed to be 0.
#
# So what does the inverse unity gain slope look like in packed poles format?
call_field_oscillator(key=kfo, macro=set_pitch_poles('FF'))
# Note rule #3 above!

# Now when we take another sample it should be the same as the last one taken during the previous inverse unity
# gain configuration:
rw8 = report_waveforms()
print(rw8)
assert(rw8 == rw6)

# As you can see this can be quite a bit more convenient than the previously described method, but either can
# be used as desired.  There are other macros for manipulating oscillators as well.  The list includes:
# - set_loudness_ceiling
# - set_loudness_floor
# - set_loudness_poles
# - set_period_poles
# - set_pitch_ceiling
# - set_pitch_floor
# - set_pitch_poles
# - set_shape_poles
# - set_tone_poles
# - set_waveset_id

# We started this tutorial by fetching a report of the default field that included its references section.  We then
# examined it's default field emitter -- the reference identified as "_fe" in the section, in more detail.  Let's
# refer to that report once more.
print(rf)

# Notice that in addition to the default field reference there are a few others which are lists:
assert(type(rf['r']['fe']) is list)
assert(type(rf['r']['p']) is list)
assert(type(rf['r']['s']) is list)
# These refer to the child elements of the field which are, in order, field emitters, probes and subjects.

# It should come as no surprise that the default field emitter reference we worked with earlier is duplicated in the
# field emitter children list:
assert(rf['r']['_fe'] == rf['r']['fe'][0])
# We can also see that it is the only other field emitter in the list:
assert(len(rf['r']['fe']) == 1)
# This is the case because we have not explicitly created any others since we re-initialized the cell in the beginning.
# The probes and subjects lists are empty for the same reason:
assert(not rf['r']['p'])
assert(not rf['r']['s'])
# There is also a reference to an element named "t":
print(rf['r']['t'])
# This refers to the field's assigned signal trunk, and happens to be a peer of the field rather than a child of it.

# Probes and subjects add a geometrical dimension to Taranos rendering and signal trunks encapsulate the hidden world
# of Taranos signaling.  Both are topics we'll cover in detail in later tutorials.

# One final point, currently [February 2017], there is no fixed limit to the number of field emitters that a field can
# support.  As with other kinds of simulation applications the theoretical limit to entity counts is a function of many
# factors including host architecture and physical capacities.  But there are practical considerations when it comes to
# the size of reporting responses and the ability of clients, especially those on limited-capability devices, to handle
# them.  Taranos uses a number of techniques to help manage the sizes of wavefield and configuration reporting responses
# including report sectioning as we've seen and field geometry.
#
# For example, the minimal field report of our default field is quite modest in size:
print(report_field())
# However we can easily request a broader set of report sections that explode the size of the report considerably:
print(report_field(sections='arsgc'))
# In this case we've requested additional information about all attribute ("a"), reference ("r"), state ("s"), and
# geometry ("g") properties as well as those same report sections from all of the element's children recursively ("c").
# In the course of ordinary usage it is unlikely that all of those properties would be needed by a client at once but
# the option exists if desired.
#
# Likewise, the size of waveform reports is a direct function of the number of active oscillators within a collector's
# scope of detection, so oscillator density should be of some consideration in application design.

#
# Congratulations, you've completed the first Taranos:CSF tutorial!
#
