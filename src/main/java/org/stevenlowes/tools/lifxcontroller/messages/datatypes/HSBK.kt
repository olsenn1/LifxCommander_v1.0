package org.stevenlowes.tools.lifxcontroller.messages.datatypes

import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Levels
import org.stevenlowes.tools.lifxcontroller.values.Temps

data class HSBK(var hue: Hue = Hue.RED,
                var saturation: Int = Levels.MAX,
                var brightness: Int = Levels.MAX,
                var kelvin: Int = Temps.MEDIUM.kelvin)
