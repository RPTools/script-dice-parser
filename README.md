MapTool Script and Dice Roll Parser
===================================
|Branch|Status|
|:---|:---:|
|Master | ![Master Branch](https://github.com/RPTools/script-dice-parser/workflows/Build%20Verification/badge.svg) |
|Develop | ![Develop Branch](https://github.com/RPTools/script-dice-parser/workflows/Build%20Verification/badge.svg?branch=develop) |

Script and Dice Parser
======================
Welcome to the repository that contains the replacement parser for the MapTool Macro Script and Dice rolls. 


Requirements
------------
- Building the Script and Dice Parser requires [Java 14](https://adoptopenjdk.net/?variant=openjdk14&jvmVariant=hotspot) 

Resources
---------

 - **Website:** http://rptools.net/ 
 - **Forums:**  http://forums.rptools.net 
 - **Wiki:**    http://lmwcs.com/rptools/wiki/Main_Page 
 - **Discord:** https://discord.gg/gevEtpC
 
Configuration Steps Prior to Building the parser
-----------------------------------------------

First, install the JDK [Java 14](https://adoptopenjdk.net/?variant=openjdk14&jvmVariant=hotspot) 


Second, clone the GitHub repository (this one or one that you have forked) to your local system.  If you are cloning your own fork, change the URL as appropriate.

```
git clone git@github.com:RPTools/script-dice-parser.git
```

From here on, it is expected that you are running these commands from within the directory that was created when you cloned the repository (referred to as the _working directory_ in Git-speak).

[Gradle](http://gradle.org/) is used to build the parser. You do not need Gradle installed to perform the build as the repository has a small wrapper that will download and install it in a subdirectory for you. This means that the first time you run Gradle, you will need to be connected to the Internet and it will download and cache the version of gradle our project is currently using as well as any dependencies.

Building the parser from Terminal*
---------------------------------
```
./gradlew clean build
```

*On Windows, depending on the terminal you use, you may need to use `\` instead of `/`. We recommend using the new Windows Terminal and Powershell 7 or WSL 2.*

Contributors
------------
Please follow our [Code Style and Guidelines](doc/Code_Style_and_Guidelines.md) when committing your code and submitting pull requests. We enforce code style using spotless, to insure your build passes our automatic checks, you can run the following gradle command before committing:
```
./gradlew spotlessApply
```

Recommended IDE
----------------
We currently recommend [IntelliJ IDEA](https://www.jetbrains.com/idea/) as our editor of choice although Eclipse and other IDE's should work just fine as well. For IntelliJ IDEA, simply open the project folder and it will detect it as a Gradle project and you should be ready to go with minimal effort.


Code Commits and Pull Requests
--------------------------------
We follow GitFlow process for the most part so please work all issues off of our `develop` branch. If you code changes are substantial, me may repoint the pull request to it's own feature branch for testing and further development.

We prefer all pull requests to be preceded and reference an Issue before we accept and merge. If there is not currently an open issue, please create one and leave a comment if you plan to work on the issue. When you commit your code, please reference the issue, e.g. `fixes #1234` in addition to any other comments.

Please include or update junit tests for changes or new functionality.



Testing the Parser
------------------

Even though this is not intended to be executed directly there is a class with a main method that
can be used to set up and run the parser for testing purposes.

Use the following command line to execute the test parser.

```
java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -jar build/libs
/parser2-1.0-SNAPSHOT-all.jar  src/test/resources/scriptsamples/hello_world.mts2
```

you can use the -s flag (after the jar in the command line) to get a dump of the symbol table that is
generated during parse.
