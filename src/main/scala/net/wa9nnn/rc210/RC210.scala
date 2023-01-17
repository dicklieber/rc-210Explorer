package net.wa9nnn.rc210

import java.nio.file.Paths

object RC210 extends App{

  private val datFile: DatFile = DatFileIo.read(Paths.get("data/01162023.dat"))
  datFile.dump()
}
