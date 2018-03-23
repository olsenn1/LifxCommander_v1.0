package org.stevenlowes.tools.lifxcontroller.values

data class Color(var hue: Hue = Hue.RED,
                 var saturation: Level = Level.MAX,
                 var brightness: Level = Level.MAX,
                 var temp: Temp = Temp.MEDIUM)
