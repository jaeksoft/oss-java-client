OpenSearchServer Java Client
============================

A fluent java client for OpenSearchServer 1.5.x and newer

Requirement
-----------

- Java Runtime 7
- OpenSearchServer v1.5.x or newer
- Maven 3.2.1 or newer (for building only)

How to build
------------

The compilation and packaging requires [Maven 3.2.1 or newer](http://maven.apache.org/)

Clone the source code:

```shell
git clone https://github.com/opensearchserver/oss-java-client.git
```

Compile and package (the jar will located in the target directory):

```shell
mvn clean package
```

Maven dependency
----------------

```xml
<dependency>
    <groupId>com.opensearchserver</groupId>
    <artifactId>oss-java-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

JavaDoc
-------

The nightly build JavaDoc is here:

[Javadoc](http://opensearchserver.github.io/oss-java-client/)