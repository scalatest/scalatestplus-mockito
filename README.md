# ScalaTest + Mockito
ScalaTest + Mockito provides integration support between ScalaTest and Mockito.

**Usage**

To use it for ScalaTest 3.2.10 and Mockito 3.12.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "mockito-3-12" % "3.2.10.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>mockito-3-12_2.13</artifactId>
  <version>3.2.10.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```