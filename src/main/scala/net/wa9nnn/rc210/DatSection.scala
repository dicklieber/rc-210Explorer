package net.wa9nnn.rc210

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success}

case class DatSection(sectionName: String, values: Seq[DataItem]) extends LazyLogging {
  def dump(): Unit = {
    logger.info(sectionName)
    values.foreach {
      _.dump()
    }
  }
}

class SectionBuilder(name: String) extends LazyLogging {
  private val entriesBuilder = Seq.newBuilder[DataItem]

  def appendLine(dataLine: String): Unit = {
    DataItem(dataLine) match {
      case Failure(exception) =>
        logger.error("Parsing line: {}", exception, dataLine)
      case Success(di) =>
        entriesBuilder += di
    }
  }

  def result: DatSection = DatSection(name, entriesBuilder.result())

}
