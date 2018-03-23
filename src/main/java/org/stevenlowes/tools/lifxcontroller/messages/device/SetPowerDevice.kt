package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload

class SetPowerDevice(var level: Int = 0) : CustomReadPayload(21) {

    override val byteArray: ByteArray
        get() {
            val levelBinStr = String.format("%16s", Integer.toBinaryString(level)).replace(' ', '0')
            return CommonMethods.convertBinaryStringToLittleEndianByteArray(levelBinStr)
        }

    constructor(setPower: SetPowerDevice) : this(setPower.level)
}
