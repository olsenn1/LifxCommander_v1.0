package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload

class SetPowerLight(var level: Int = 0, var duration: Long = 0) : GetOnlyPayload(117) {
    override val byteArray: ByteArray?
        get() {
            val byteArray = ByteArray(6)

            var levelBytes: ByteArray? = ByteArray(2)
            val levelBinStr = String.format("%16s", Integer.toBinaryString(level)).replace(' ', '0')
            levelBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(levelBinStr)
            byteArray[0] = levelBytes!![0]
            byteArray[1] = levelBytes[1]

            var durationBytes: ByteArray? = ByteArray(4)
            val durationBinStr = String.format("%32s", java.lang.Long.toBinaryString(duration)).replace(' ', '0')
            durationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(durationBinStr)
            System.arraycopy(durationBytes!!, 0, byteArray, 2, 4)

            return byteArray
        }
}
