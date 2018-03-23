package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.UpdatedAt.CustomReadUpdatedAtPayload

class SetGroup(var group: ByteArray = CommonMethods.randomBytes(16),
               var label: String = "N/A") : CustomReadUpdatedAtPayload(52) {

    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(56)

            for (i in 0..15) {
                byteArray[i] = group[15 - i]
            }

            var labelBytes = ByteArray(32)
            labelBytes = label.toByteArray()
            for (i in 16..47) {
                byteArray[i] = labelBytes[47 - i]
            }

            var updatedAtBytes: ByteArray? = ByteArray(8)
            val updatedAtBinStr = String.format("%64s",
                                                java.lang.Long.toBinaryString(updatedAt.toLong())).replace(' ', '0')
            updatedAtBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(updatedAtBinStr)
            System.arraycopy(updatedAtBytes!!, 0, byteArray, 48, 8)

            return byteArray
        }
}
