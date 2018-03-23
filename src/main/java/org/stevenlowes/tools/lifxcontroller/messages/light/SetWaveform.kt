package org.stevenlowes.tools.lifxcontroller.messages.light


import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.HSBK
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Waveform

class SetWaveform(var reserved: Int = 0,
                  var isTransient: Boolean = false,
                  var color: HSBK = HSBK(),
                  var period: Long = 0,
                  var cycles: Float = 0f,
                  var skewRatio: Level = Level.MIN,
                  var waveform: Waveform = Waveform.SAWTOOTH) :
        CustomReadPayload(103) {

    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(21)

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

            val skewRatioBytes: ByteArray = skewRatio.byteArray
            byteArray[18] = skewRatioBytes[0]
            byteArray[19] = skewRatioBytes[1]

            val waveformBytes: ByteArray = waveform.byteArray
            byteArray[20] = waveformBytes[0]

            return byteArray
        }
}
