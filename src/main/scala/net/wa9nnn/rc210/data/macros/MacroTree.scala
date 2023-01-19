package net.wa9nnn.rc210.data.macros

import com.typesafe.scalalogging.LazyLogging
import net.wa9nnn.rc210.{DatSection, DataItem}

import scala.util.Try

class MacroTree(datSection: DatSection) {
  assert(datSection.sectionName == "Macros")
}


case class Schedule(setPoStringNumber: Int, macroToRun: Int, week: Int, dayOfWeek: Int, monthToRun: Int, monthly: Boolean, hours: Int, minutes: Int)

object Schedule extends LazyLogging {
  def buildSchdule(setPoint: Int, items: Seq[DataItem]): Try[Schedule] = {
    {
      val valueMap: Map[String, DataItem] = items.map { dataItem =>
        dataItem.name -> dataItem
      }.toMap

      def v(name: String): Int = {
        val dataItem: DataItem = valueMap(name)
        dataItem.value.toInt
      }

      Try {
        new Schedule(setPoint,
          v("MacroToRun"),
          v("Week"),
          v("DOW"),
          v("MonthToRun"),
          v("Monthly") != 0,
          v("Hours"),
          v("Minutes"))
      }
    }
  }
}