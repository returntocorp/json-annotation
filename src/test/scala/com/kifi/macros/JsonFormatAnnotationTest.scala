package com.kifi.macros

import org.specs2.mutable.Specification
import play.api.libs.json._
import ai.x.play.json.SingletonEncoder.simpleName
import ai.x.play.json.implicits.formatSingleton

@jsonInline case class City(name: String)
@jsonInline case class Person(name: String, age: Int)

@json case class City2(name: String)
@json case class Person2(name: String, age: Int)

@jsonInline final case class FinalCaseClass(name: String)

@jsonSealed sealed trait Parent
@jsonInline final case class C1(x: String, _type: C1.type = C1) extends Parent
@jsonInline final case class C2(y: String, _type: C2.type = C2) extends Parent
class JsonFormatAnnotationTest extends Specification {
  "@sealedJson annotation" should {
    "create correct formatter for sealed traits" in {
      val c1 = C1("hello")
      val c2 = C2("goodbye")
      val json = Json.toJson(List(c1, c2))
      Json.fromJson[List[Parent]](json).asOpt must beSome(List(c1, c2))
    }
  }

  "@json annotation" should {

    "create correct formatter for final case classes" in {
      val finalCaseClass = FinalCaseClass("test")
      val json = Json.toJson(finalCaseClass)
      json === JsString("test")
      Json.fromJson[FinalCaseClass](json).asOpt must beSome(finalCaseClass)
    }

    "create correct formatter for case class with 1 field" in {

      val city = City("San Francisco")
      val json = Json.toJson(city)
      json === JsString("San Francisco")
      Json.fromJson[City](json).asOpt must beSome(city)
    }

    "create correct formatter for case class with >= 2 fields" in {

      val person = Person("Victor Hugo", 46)
      val json = Json.toJson(person)
      json === Json.obj(
        "name" -> "Victor Hugo",
        "age" -> 46
      )
      Json.fromJson[Person](json).asOpt must beSome(person)
    }
  }

  "@jsonstrict annotation" should {

    "create correct formatter for case class with 1 field" in {

      val city = City2("San Francisco")
      val json = Json.toJson(city)
      json === Json.obj("name" -> "San Francisco")
      Json.fromJson[City2](json).asOpt must beSome(city)
    }

    "create correct formatter for case class with >= 2 fields" in {

      val person = Person2("Victor Hugo", 46)
      val json = Json.toJson(person)
      json === Json.obj(
        "name" -> "Victor Hugo",
        "age" -> 46
      )
      Json.fromJson[Person2](json).asOpt must beSome(person)
    }
  }
}
