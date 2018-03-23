package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StateVersion(var vendor: Long = 0, var product: Long = 0, var version: Long = 0) : CustomWritePayload(33) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        var vendorBinStr = ""
        for (i in 39 downTo 36) {
            vendorBinStr = vendorBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        vendor = java.lang.Long.parseLong(vendorBinStr, 2)

        var productBinStr = ""
        for (i in 43 downTo 40) {
            productBinStr = productBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        product = java.lang.Long.parseLong(productBinStr, 2)

        var versionBinStr = ""
        for (i in 47 downTo 44) {
            versionBinStr = versionBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        version = java.lang.Long.parseLong(versionBinStr, 2)
    }

}
