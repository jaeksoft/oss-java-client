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
git clone https://github.com/jaeksoft/oss-java-client.git
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

License Apache 2
----------------

Copyright 2015 [OpenSearchServer Inc.](http://www.opensearchserver.com)


Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
