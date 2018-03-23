package org.stevenlowes.tools.lifxcontroller.messages.datatypes

import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Temp

data class HSBK(var hue: Hue = Hue.RED,
                var saturation: Level = Level.MAX,
                var brightness: Level = Level.MAX,
                var temp: Temp = Temp.MEDIUM)
