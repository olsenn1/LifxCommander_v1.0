package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

import java.math.BigInteger

class StateWifiFirmware(var build: BigInteger = BigInteger.ZERO,
                        var reserved: BigInteger = BigInteger.ZERO,
                        var version: Long = 0) : ResponsePayload(19) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        //String buildBinStr = "";
        //for(int i=43; i>35; i--) buildBinStr = buildBinStr.concat(Utils.convertByteToBinaryString(byteArray[i]));
        //build = BigInteger.valueOf(Long.parseLong(buildBinStr, 2));
        val buildBytes = ByteArray(8)
        for (i in 43 downTo 36) {
            buildBytes[-1 * i + 43] = byteArray[i]
        }
        build = BigInteger(buildBytes)

        //String reservedBinStr = "";
        //for(int i=51; i>43; i--) reservedBinStr = reservedBinStr.concat(Utils.convertByteToBinaryString(byteArray[i]));
        //reserved = BigInteger.valueOf(Long.parseLong(reservedBinStr, 2));
        val reservedBytes = ByteArray(8)
        for (i in 51 downTo 44) {
            reservedBytes[-1 * i + 51] = byteArray[i]
        }
        reserved = BigInteger(reservedBytes)

        var versionBinStr = ""
        for (i in 55 downTo 52) {
            versionBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        version = java.lang.Long.parseLong(versionBinStr, 2)
    }

}
