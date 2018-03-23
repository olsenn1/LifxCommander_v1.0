package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

data class StateVersion(val vendor: Long = 0, val product: Long = 0, val version: Long = 0) : ResponsePayload(33) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StateVersion{
            var vendorBinStr = ""
            for (i in 39 downTo 36) {
                vendorBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val vendor = java.lang.Long.parseLong(vendorBinStr, 2)

            var productBinStr = ""
            for (i in 43 downTo 40) {
                productBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val product = java.lang.Long.parseLong(productBinStr, 2)

            var versionBinStr = ""
            for (i in 47 downTo 44) {
                versionBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val version = java.lang.Long.parseLong(versionBinStr, 2)

            return StateVersion(vendor, product, version)
        }
    }

}
