package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

class StateVersion(var vendor: Long = 0, var product: Long = 0, var version: Long = 0) : ResponsePayload(33) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        var vendorBinStr = ""
        for (i in 39 downTo 36) {
            vendorBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        vendor = java.lang.Long.parseLong(vendorBinStr, 2)

        var productBinStr = ""
        for (i in 43 downTo 40) {
            productBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        product = java.lang.Long.parseLong(productBinStr, 2)

        var versionBinStr = ""
        for (i in 47 downTo 44) {
            versionBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        version = java.lang.Long.parseLong(versionBinStr, 2)
    }

}
