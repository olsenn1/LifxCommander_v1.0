package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Waveform

class SetWaveformOptional(var reserved: Int = 0,
                          var isTransient: Boolean = false,
                          var color: Color = Color(),
                          var period: Long = 0,
                          var cycles: Float = 0f,
                          var skewRatio: Level = Level.MIN,
                          var waveform: Waveform = Waveform.SAWTOOTH,
                          var setHue: Boolean = true,
                          var setSaturation: Boolean = true,
                          var setBrightness: Boolean = true,
                          var setKelvin: Boolean = true) : RequestPayload(119) {
    override val byteArray: ByteArray
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
