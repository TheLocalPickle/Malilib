malilib
==============
malilib is a library mod used by masa's LiteLoader mods. It contains some common code previously
duplicated in most of the mods, such as multi-key capable keybinds, configuration GUIs etc.

Compiling
=========
* Clone the repository
* Open a command prompt/terminal to the repository directory
* run 'gradlew build'
* The built jar file will be in build/libs/

Installing In Maven Local
=========================

Allows you to test new versions of this library with mods that depend on it
* Clone the repository
* Open a command prompt/terminal to the repository directory
* run 'gradlew publishMavenJavaPublicationToMavenLocal'
* The built jar file will be installed in your local maven repository, accessible to other projects.