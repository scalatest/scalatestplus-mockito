# ScalaTest + Mockito
ScalaTest + Mockito provides integration support between ScalaTest and Mockito.

**Usage**

To use it for ScalaTest 3.1.0 and Mockito 1.10.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "mockito-1-10" % "3.1.0.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>mockito-1-10</artifactId>
  <version>3.1.0.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```
