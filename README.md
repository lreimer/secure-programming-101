[![Dependency Status](https://www.versioneye.com/user/projects/57af1de9b56d6b001694ab24/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/57af1de9b56d6b001694ab24)
[![Build Status](https://travis-ci.org/lreimer/secure-programming-101.svg?branch=master)](https://travis-ci.org/lreimer/secure-programming-101)

# Secure Architecture and Programming 101

The source code for my session "Secure Architecture and Programming 101". So far this topics had been 
presented at the following occasions and conferences:
- JavaOne 2015 
(https://speakerdeck.com/lreimer/secure-jee-architecture-and-programming-101)
- IT Security Summer School 2015 
(http://www.fh-rosenheim.de/die-hochschule/fakultaeten-institute/fakultaet-fuer-informatik/unsere-fakultaet/it-security-summer-school-2015/) 
- O'Reilly Software Architecture Conference
(To be presented ...)


## Usage instructions and warnings

**WARNING:** this code is for illustration and educational purpose only!!! The examples
 contain some good but also a lot of insecure coding examples. Please do not use these
 in your production code!!!

In order to compile and run the examples you do not need much. A recent JDK8 and a local
Glassfish4 installation. Use the provided Gradle Wrapper to compile.
```shell
$ gradlew build
```


## Glassfish password alias

In order to run the example web application you need to create a password alias.
```shell
asadmin> create-password-alias
Enter the value for the aliasname operand> secpro_password_alias
Enter the alias password> 1qay2wsx
Enter the alias password again> 1qay2wsx
Command create-password-alias executed successfully.
```

Then you need to update your Glassfish domain server configuration an add the following
system property definition to your JVM options:
```shell
-Dde.qaware.campus.secpro.password=${ALIAS=secpro_password_alias}
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

The software and documentation is provided under the MIT open source license,
read the `LICENSE` file for details.
