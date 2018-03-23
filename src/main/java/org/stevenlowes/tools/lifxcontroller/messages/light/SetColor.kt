package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.HSBK

class SetColor(var reserved: Int = 0, var hsbk: HSBK = HSBK(), var duration: Long = 0) : CustomReadPayload(102) {
    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(13)

            var reservedByte: ByteArray? = ByteArray(1)
            val reservedBinStr = String.format("%8s", Integer.toBinaryString(reserved)).replace(' ', '0')
            reservedByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(reservedBinStr)
            byteArray[0] = reservedByte!![0]

            var hueBytes: ByteArray? = ByteArray(2)
            val hueBinStr = String.format("%16s", hsbk.hue.binaryString).replace(' ', '0')
            hueBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(hueBinStr)
            byteArray[1] = hueBytes!![0]
            byteArray[2] = hueBytes[1]

            var saturationBytes: ByteArray? = ByteArray(2)
            val saturationBinStr = String.format("%16s", Integer.toBinaryString(hsbk.saturation)).replace(' ', '0')
            saturationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(saturationBinStr)
            byteArray[3] = saturationBytes!![0]
            byteArray[4] = saturationBytes[1]

            var brightnessBytes: ByteArray? = ByteArray(2)
            val brightnessBinStr = String.format("%16s", Integer.toBinaryString(hsbk.brightness)).replace(' ', '0')
            brightnessBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr)
            byteArray[5] = brightnessBytes!![0]
            byteArray[6] = brightnessBytes[1]

            var kelvinBytes: ByteArray? = ByteArray(2)
            val kelvinBinStr = String.format("%16s", Integer.toBinaryString(hsbk.kelvin)).replace(' ', '0')
            kelvinBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(kelvinBinStr)
            byteArray[7] = kelvinBytes!![0]
            byteArray[8] = kelvinBytes[1]

            var durationBytes: ByteArray? = ByteArray(4)
            val durationBinStr = String.format("%32s", java.lang.Long.toBinaryString(duration)).replace(' ', '0')
            durationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(durationBinStr)
            byteArray[9] = durationBytes!![0]
            byteArray[10] = durationBytes[1]
            byteArray[11] = durationBytes[2]
            byteArray[12] = durationBytes[3]

            return byteArray
        }
}
