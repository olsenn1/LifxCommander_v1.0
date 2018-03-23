package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommandUpdateTime

import java.math.BigInteger

//TODO remove default args from responses

data class StateGroup(val group: ByteArray = Utils.randomBytes(16), val label: String = "N/A") : ResponseCommandUpdateTime(53) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StateGroup{
            val group = ByteArray(16)
            for (i in 51 downTo 36) {
                group[-1 * i + 51] = byteArray[i]
            }

            val labelBytes = ByteArray(32)
            System.arraycopy(byteArray, 52, labelBytes, 0, 32)
            val label = String(labelBytes)

            val updatedAtBytes = ByteArray(8)
            for (i in 91 downTo 84) {
                updatedAtBytes[-1 * i + 91] = byteArray[i]
            }
            val updatedAt = BigInteger(updatedAtBytes)

            val stateGroup = StateGroup(group, label)
            stateGroup.updatedAt = updatedAt
            return stateGroup
        }
    }
}
