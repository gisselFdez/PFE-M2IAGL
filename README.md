# PFE-M2IAGL
[![Build Status](https://travis-ci.org/gisselFdez/PFE-M2IAGL.svg?branch=master)](https://travis-ci.org/gisselFdez/PFE-M2IAGL)

###Overview
This project read Android applications crash scenarios from a Neo4j database and generates:
- The corresponding Robotium Blackbox test class to reproduce the crash.
- The textual description of the crash scenario.

###Getting started
To compile using maven: 
```
$ mvn clean install
```

###Usage
```
$ java Generator DataBasePath TestOutputFilePath [DescriptionOutputFilePath]

  [DataBasePath]
    Define the location of the Neo4j database.
    
  [TestOutputFilePath]
    Define the location where the Robotium test class will be generated.
    
  [DescriptionOutputFilePath]
    Define the location where the textual description will be generated. (default: TestOutputFilePath)
```
