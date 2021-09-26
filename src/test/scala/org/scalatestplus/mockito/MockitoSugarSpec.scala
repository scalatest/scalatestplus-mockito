/*
 * Copyright 2001-2020 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalatestplus.mockito

import org.scalatest.funsuite.AnyFunSuite
import org.mockito.Mockito.{verify, times}

class MockitoSugarSpec extends AnyFunSuite with MockitoSugar {

  trait Collaborator {
    def documentAdded(name: String): Unit
    def documentChanged(name: String): Unit
  }

  class ClassUnderTest {

    private val docs = new scala.collection.mutable.ListBuffer[String]
    private val listeners = new scala.collection.mutable.ListBuffer[Collaborator]

    def addListener(listener: Collaborator): Unit = {
      listeners += listener  
    }

    def addDocument(name: String, bytes: Array[Byte]): Unit = {
      if (docs.contains(name))
        listeners.foreach(_.documentChanged(name))
      else {
        docs += name  
        listeners.foreach(_.documentAdded(name))
      } 
    }

  }

  test("MockitoSugar should mock object for testing") {
    // First, create the mock object
    val mockCollaborator = mock[Collaborator]

    // Create the class under test and pass the mock to it
    val classUnderTest = new ClassUnderTest
    classUnderTest.addListener(mockCollaborator)

    // Use the class under test
    classUnderTest.addDocument("Document", new Array[Byte](0))
    classUnderTest.addDocument("Document", new Array[Byte](0))
    classUnderTest.addDocument("Document", new Array[Byte](0))
    classUnderTest.addDocument("Document", new Array[Byte](0))

    // Then verify the class under test used the mock object as expected
    verify(mockCollaborator).documentAdded("Document")
    verify(mockCollaborator, times(3)).documentChanged("Document")
  }

  test("MockitoSugar should capture argument for testing") {
    // First, create the mock object
    val mockCollaborator = mock[Collaborator]

    // Create the class under test and pass the mock to it
    val classUnderTest = new ClassUnderTest
    classUnderTest.addListener(mockCollaborator)

    // Use the class under test
    classUnderTest.addDocument("Document", new Array[Byte](0))

    // Create the captor
    val strCaptor = capture[String]
    val strCaptor2 = capture[String]

    // Then verify the class under test used the mock object as expected
    verify(mockCollaborator).documentAdded(strCaptor.capture())
    assert(strCaptor.getValue === "Document")
  }

}