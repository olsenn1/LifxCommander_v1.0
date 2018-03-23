package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload

import java.math.BigInteger
import java.time.Instant

class SetLocation(var location: ByteArray = CommonMethods.randomBytes(16), var label: String = "N/A") : GetOnlyPayload(49) {
    var updatedAt = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))

    override val byteArray: ByteArray?
        get() {
            val byteArray = ByteArray(56)


            for (i in 0..15) {
                byteArray[i] = location[15 - i]
            }

            var labelBytes = ByteArray(32)
            labelBytes = label.toByteArray()
            for (i in 16..47) {
                byteArray[i] = labelBytes[47 - i]
            }

            var updatedAtBytes: ByteArray? = ByteArray(8)
            val updatedAtBinStr = String.format("%64s",
                                                java.lang.Long.toBinaryString(updatedAt!!.toLong())).replace(' ', '0')
            updatedAtBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(updatedAtBinStr)
            System.arraycopy(updatedAtBytes!!, 0, byteArray, 48, 8)

            return byteArray
        }

    fun setUpdatedAtToNow() {
        val millis = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))
    }

}
