package net.wa9nnn.rc210

import net.wa9nnn.rc210.data.macros.Schedule

import java.nio.file.Paths

object RC210 extends App {

  private val datFile: DatFile = DatFileIo.read(Paths.get("data/01162023.dat"))
  datFile.dump()


  def extractSchedules(): Seq[Schedule] = datFile.section("Scheduler")
    .dataItems
    .filter(_.maybeInt.nonEmpty) // schedules have numbers
    .filter(di => {
      // remove disabled.
      try {
        val int = di.value.toInt
        int >= 0 && int < 25
      } catch {
        case _: Exception =>
          false // Some have "0A" to indicate disabled which causes toInt to throw
      }
    }
    )
    .groupBy(_.number)
    .flatMap { case (number, items) =>
      Schedule.buildSchdule(number, items).toOption
    }
    .toSeq

  val schedules: Seq[Schedule] = extractSchedules()
  println(schedules)

}
