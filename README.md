# ScalaTest + Mockito
ScalaTest + Mockito provides integration support between ScalaTest and Mockito.

**Usage**

To use it for ScalaTest 3.2.2 and Mockito 3.4.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.3.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>mockito-3-4</artifactId>
  <version>3.2.3.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```
