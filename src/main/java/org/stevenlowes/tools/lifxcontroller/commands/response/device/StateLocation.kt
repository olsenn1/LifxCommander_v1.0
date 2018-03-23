package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommandUpdateTime

import java.math.BigInteger

data class StateLocation(val location: ByteArray = Utils.randomBytes(16),
                    val label: String = "N/A") : ResponseCommandUpdateTime(50) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StateLocation{
            val location = ByteArray(16)
            for (i in 51 downTo 36) {
                location[-1 * i + 51] = byteArray[i]
            }

            val labelBytes = ByteArray(32)
            System.arraycopy(byteArray, 52, labelBytes, 0, 32)
            val label = String(labelBytes)

            //String updatedAtBinStr = "";
            //for(int i=91; i>83; i--) updatedAtBinStr = updatedAtBinStr.concat(Utils.convertByteToBinaryString(payloadBytes[i]));
            //updated_at = BigInteger.valueOf(Long.parseLong(updatedAtBinStr, 2));
            val updatedAtBytes = ByteArray(8)
            for (i in 91 downTo 84) {
                updatedAtBytes[-1 * i + 91] = byteArray[i]
            }
            val updatedAt = BigInteger(updatedAtBytes)

            val stateLocation = StateLocation(location, label)
            stateLocation.updatedAt = updatedAt
            return stateLocation
        }
    }
}
