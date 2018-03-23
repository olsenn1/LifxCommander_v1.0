package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

import java.math.BigInteger

class StateHostFirmware(var build: BigInteger = BigInteger.ZERO, var reserved: BigInteger = BigInteger.ZERO, var version: Long = 0L) : SetOnlyPayload(15) {

    override fun setFromCommandByteArray(byteArray: ByteArray) {
        //String buildBinStr = "";
        //for(int i=43; i>35; i--) buildBinStr = buildBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
        //build = BigInteger.valueOf(Long.parseLong(buildBinStr, 2));
        val buildBytes = ByteArray(8)
        for (i in 43 downTo 36) {
            buildBytes[-1 * i + 43] = byteArray[i]
        }
        build = BigInteger(buildBytes)

        //String reservedBinStr = "";
        //for(int i=51; i>43; i--) reservedBinStr = reservedBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
        //reserved = BigInteger.valueOf(Long.parseLong(reservedBinStr, 2));
        val reservedBytes = ByteArray(8)
        for (i in 51 downTo 44) {
            reservedBytes[-1 * i + 51] = byteArray[i]
        }
        reserved = BigInteger(reservedBytes)

        var versionBinStr = ""
        for (i in 55 downTo 52) {
            versionBinStr = versionBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        version = java.lang.Long.parseLong(versionBinStr, 2)
    }

}
