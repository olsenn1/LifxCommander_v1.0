package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

import java.math.BigInteger
import java.time.Instant

class StateLocation(var location: ByteArray = CommonMethods.randomBytes(16), var label: String = "N/A") : SetOnlyPayload(50) {
    var updatedAt: BigInteger? = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))

    fun setUpdatedAtToNow() {
        updatedAt = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))
    }

    override fun setFromCommandByteArray(byteArray: ByteArray) {
        for (i in 51 downTo 36) {
            location[-1 * i + 51] = byteArray[i]
        }

        val labelBytes = ByteArray(32)
        System.arraycopy(byteArray, 52, labelBytes, 0, 32)
        label = String(labelBytes)

        //String updatedAtBinStr = "";
        //for(int i=91; i>83; i--) updatedAtBinStr = updatedAtBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
        //updated_at = BigInteger.valueOf(Long.parseLong(updatedAtBinStr, 2));
        val updatedAtBytes = ByteArray(8)
        for (i in 91 downTo 84) {
            updatedAtBytes[-1 * i + 91] = byteArray[i]
        }
        updatedAt = BigInteger(updatedAtBytes)
    }

}
