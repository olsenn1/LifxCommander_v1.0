package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload

class SetPowerDevice(var level: Int = 0) : GetOnlyPayload(21) {

    override val byteArray: ByteArray?
        get() {
            var byteArray: ByteArray? = ByteArray(2)

            val levelBinStr = String.format("%16s", Integer.toBinaryString(level)).replace(' ', '0')
            byteArray = CommonMethods.convertBinaryStringToLittleEndianByteArray(levelBinStr)

            return byteArray
        }

    constructor(setPower: SetPowerDevice) : this(setPower.level)
}
