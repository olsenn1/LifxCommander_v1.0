package org.stevenlowes.tools.lifxcontroller.commands.header

//TODO make immutable

import org.stevenlowes.tools.lifxcontroller.Utils

data class FrameAddress(
        val target: Long = 0,                // 64-Bits
        val reserved1: Long = 0,                // 48-Bits
        val reserved2: Int = 0,                // 6-Bits
        val ackRequired: Boolean = false,        // 1-Bit
        val resRequired: Boolean = false,        // 1-Bit
        val sequence: Int = 0                // 8-Bits
                       ) {

    constructor(targetMacAddress: String = "",
                reserved1: Long = 0,
                reserved2: Int = 0,
                ackRequired: Boolean = false,
                resRequired: Boolean = false,
                sequence: Int = 0) : this(Utils.convertHexStringToLong(targetMacAddress),
                                          reserved1,
                                          reserved2,
                                          ackRequired,
                                          resRequired,
                                          sequence)

    val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(16)

            val targetBytes: ByteArray = Utils.toByteArray(8, target)
            System.arraycopy(targetBytes, 0, byteArray, 0, 8)

            val reserved1Bytes: ByteArray = Utils.toByteArray(6, reserved1)
            System.arraycopy(reserved1Bytes, 0, byteArray, 8, 6)

            var dataByte: ByteArray? = ByteArray(1)
            val reserved2BinStr = String.format("%6s", Integer.toBinaryString(reserved2)).replace(' ', '0')

            val ackRequiredBinStr: String = if (ackRequired)
                "1"
            else
                "0"

            val resRequiredBinStr: String = if (resRequired)
                "1"
            else
                "0"

            val dataBinStr = reserved2BinStr + ackRequiredBinStr + resRequiredBinStr
            dataByte = Utils.convertBinaryStringToLittleEndianByteArray(dataBinStr)
            byteArray[14] = dataByte[0]

            val sequenceByte: ByteArray = Utils.toByteArray(1, sequence)
            byteArray[15] = sequenceByte[0]

            return byteArray
        }

    companion object {
        fun loadFrom(byteArray: ByteArray): FrameAddress{
            var targetBinStr = ""
            for (i in 15 downTo 8) {
                targetBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val target = java.lang.Long.parseLong(targetBinStr, 2)

            var reserved1BinStr = ""
            for (i in 21 downTo 16) {
                reserved1BinStr = reserved1BinStr + Utils.convertByteToBinaryString(byteArray[i])
            }
            val reserved1 = java.lang.Long.parseLong(reserved1BinStr, 2)

            val dataBinStr = Utils.convertByteToBinaryString(byteArray[22])
            val reserved2BinStr = dataBinStr.substring(0, 6)
            val reserved2 = Integer.parseInt(reserved2BinStr, 2)
            val ackRequired = dataBinStr[6] == '1'
            val resRequired = dataBinStr[7] == '1'

            val sequenceBinStr = Utils.convertByteToBinaryString(byteArray[23])
            val sequence = Integer.parseInt(sequenceBinStr, 2)

            return FrameAddress(target, reserved1, reserved2, ackRequired, resRequired, sequence)
        }
    }
}
