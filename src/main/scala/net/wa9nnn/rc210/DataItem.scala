package net.wa9nnn.rc210

import com.typesafe.scalalogging.LazyLogging

import scala.util.Try
import scala.util.matching.Regex

case class DataItem(name: String, number: Option[Int], value: String, line:String) extends LazyLogging{
  def dump() :Unit = {
    logger.info(s"\t$name\t${number.getOrElse("-")}\t$value")
  }
}

object DataItem {
  val dataItem: Regex = """([^(]*)(?:\((\d+)\))?=(.*)""".r

  def apply(dataLine:String): Try[DataItem] = {
    Try{
      val dataItem(name, data, value) = dataLine
      new DataItem(name, Option(data).map(_.toInt), value, dataLine)
    }
  }

}

