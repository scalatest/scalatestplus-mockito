# ScalaTest + Mockito
ScalaTest + Mockito provides integration support between ScalaTest and Mockito.

**Usage**

To use it for ScalaTest 3.2.19 and Mockito 5.14.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "mockito-5-14" % "3.2.19.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>mockito-5-14_3</artifactId>
  <version>3.2.19.0</version>
  <scope>test</scope>
</dependency>
```

**Note**
Mockito 5.x requires JDK11+, if you are on JDK8, please use `mockito-4-11` instead.

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```