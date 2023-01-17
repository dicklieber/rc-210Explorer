
package net.wa9nnn.rc210

import java.nio.file.{Files, Path}
import scala.io.BufferedSource




case class DatFile(sections: Seq[DatSection]) {
  def dump():Unit = {
    sections.foreach(_.dump())
  }
}

object DatFileIo {
  private val header = """\[(.+)\]""".r

  def read(file: Path): DatFile = {
    val inputStream = Files.newInputStream(file)
    val source = new BufferedSource(inputStream)
    val sectionsBuilder = Seq.newBuilder[DatSection]
    var currentSectionBuilder: Option[SectionBuilder] = None

    source.getLines().foreach {
      case header(sectName) ⇒
        currentSectionBuilder.foreach { sb =>
          sectionsBuilder += sb.result
        }
        currentSectionBuilder = Option(new SectionBuilder(sectName))

      case "" =>
      // ignore empty line

      case dataLine: String =>
        currentSectionBuilder.get.appendLine(dataLine)
    }
    val datSections: Seq[DatSection] = sectionsBuilder.result()
    DatFile(datSections)
  }
}



