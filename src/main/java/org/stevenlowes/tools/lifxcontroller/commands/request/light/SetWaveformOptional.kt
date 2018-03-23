package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Waveform

//TODO make immutable
//TODO make data classes
//TODO remove redundant constructors
//TODO calculate payloadBytes on instantiation

data class SetWaveformOptional(val reserved: Int = 0,
                          val isTransient: Boolean = false,
                          val color: Color = Color(),
                          val period: Long = 0,
                          val cycles: Float = 0f,
                          val skewRatio: Level = Level.MIN,
                          val waveform: Waveform = Waveform.SAWTOOTH,
                          val setHue: Boolean = true,
                          val setSaturation: Boolean = true,
                          val setBrightness: Boolean = true,
                          val setKelvin: Boolean = true) : RequestCommand(119) {
    override val payloadBytes: ByteArray
        get() {
            val byteArray = ByteArray(25)

            val reservedByte: ByteArray = Utils.toByteArray(1, reserved)
            byteArray[0] = reservedByte[0]

            val transientByte: ByteArray = Utils.boolToByteArray(isTransient)
            byteArray[1] = transientByte[0]

            val hueBytes: ByteArray = color.hue.byteArray
            byteArray[2] = hueBytes[0]
            byteArray[3] = hueBytes[1]

            val saturationBytes: ByteArray = color.saturation.byteArray
            byteArray[4] = saturationBytes[0]
            byteArray[5] = saturationBytes[1]

            val brightnessBytes: ByteArray = color.brightness.byteArray
            byteArray[6] = brightnessBytes[0]
            byteArray[7] = brightnessBytes[1]

            val tempBytes: ByteArray = color.temp.byteArray
            byteArray[8] = tempBytes[0]
            byteArray[9] = tempBytes[1]

            val periodBytes: ByteArray = Utils.toByteArray(4, period)
            System.arraycopy(periodBytes, 0, byteArray, 10, 4)

            val cyclesBytes: ByteArray = Utils.toByteArray(4, cycles)
            System.arraycopy(cyclesBytes, 0, byteArray, 14, 4)

            val scewRatioBytes: ByteArray = skewRatio.byteArray
            byteArray[18] = scewRatioBytes[0]
            byteArray[19] = scewRatioBytes[1]

            val waveformBytes: ByteArray = waveform.byteArray
            byteArray[20] = waveformBytes[0]

            val setHueByte: ByteArray = Utils.boolToByteArray(setHue)
            byteArray[21] = setHueByte[0]

            val setSaturationByte: ByteArray = Utils.boolToByteArray(setSaturation)
            byteArray[22] = setSaturationByte[0]

            val setBrightnessByte: ByteArray = Utils.boolToByteArray(setBrightness)
            byteArray[23] = setBrightnessByte[0]

            val setKelvinByte: ByteArray = Utils.boolToByteArray(setKelvin)
            byteArray[24] = setKelvinByte[0]

            return byteArray
        }
}
