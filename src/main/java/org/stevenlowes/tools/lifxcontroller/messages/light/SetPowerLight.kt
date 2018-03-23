package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class SetPowerLight(var level: Level = Level.MIN, var duration: Long = 0) : CustomReadPayload(117) {
    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(6)

            val levelBytes = level.byteArray
            byteArray[0] = levelBytes[0]
            byteArray[1] = levelBytes[1]

            var durationBytes: ByteArray? = ByteArray(4)
            val durationBinStr = String.format("%32s", java.lang.Long.toBinaryString(duration)).replace(' ', '0')
            durationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(durationBinStr)
            System.arraycopy(durationBytes!!, 0, byteArray, 2, 4)

            return byteArray
        }
}
