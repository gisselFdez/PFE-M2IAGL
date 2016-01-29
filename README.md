# PFE-M2IAGL
[![Build Status](https://travis-ci.org/gisselFdez/PFE-M2IAGL.svg?branch=master)](https://travis-ci.org/gisselFdez/PFE-M2IAGL)

###Overview
This project read Android applications crash scenarios from a Neo4j database and generates:
- The corresponding Robotium Blackbox test class to reproduce the crash.
- The textual description of the crash scenario.

###Usage
```
$ java Generator dataBasePath TestOutputFilePath [DescriptionOutputFilePath]
```
