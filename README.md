# ScalaTest + Mockito
ScalaTest + Mockito provides integration support between ScalaTest and Mockito.

**Usage**

To use it for ScalaTest 3.2.17 and Mockito 4.11.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "mockito-4-11" % "3.2.17.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>mockito-4-11_2.13</artifactId>
  <version>3.2.17.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```