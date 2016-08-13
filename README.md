[![Dependency Status](https://www.versioneye.com/user/projects/57af1de9b56d6b001694ab24/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/57af1de9b56d6b001694ab24)
[![Build Status](https://travis-ci.org/lreimer/secure-programming-101.svg?branch=master)](https://travis-ci.org/lreimer/secure-programming-101)

# Secure JEE Architecture and Programming 101

The source code for the JavaOne 2015 session on "Secure JEE Architecture and Programming 101".


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
