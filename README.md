# ScalaTest + Mockito
ScalaTest + Mockito provides integration support between ScalaTest and Mockito.

**Usage**

To use it for ScalaTest 3.3.0-SNAP3 and Mockito 3.3.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "mockito-3-3" % "3.3.0.0-SNAP3" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>mockito-3-3</artifactId>
  <version>3.3.0.0-SNAP3</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```