package org.stevenlowes.tools.lifx_controller.Messages.Header


import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods

data class FrameAddress(
    var target: Long = 0,                // 64-Bits
    var reserved1: Long = 0,                // 48-Bits
    var reserved2: Int = 0,                // 6-Bits
    var ackRequired: Boolean = false,        // 1-Bit
    var resRequired: Boolean = false,        // 1-Bit
    var sequence: Int = 0                // 8-Bits
                       ){

    val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(16)

            var targetBytes: ByteArray? = ByteArray(8)
            val targetBinStr = String.format("%64s", java.lang.Long.toBinaryString(target)).replace(' ', '0')
            targetBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(targetBinStr)
            System.arraycopy(targetBytes!!, 0, byteArray, 0, 8)

            var reserved1Bytes: ByteArray? = ByteArray(6)
            val reserved1BinStr = String.format("%48s", java.lang.Long.toBinaryString(reserved1)).replace(' ', '0')
            reserved1Bytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(reserved1BinStr)
            System.arraycopy(reserved1Bytes!!, 0, byteArray, 8, 6)

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
            dataByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(dataBinStr)
            byteArray[14] = dataByte!![0]

            var sequenceByte: ByteArray? = ByteArray(1)
            val sequenceBinStr = String.format("%8s", Integer.toBinaryString(sequence)).replace(' ', '0')
            sequenceByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(sequenceBinStr)
            byteArray[15] = sequenceByte!![0]

            return byteArray
        }

    fun setTargetByMacAddress(macAddress: String) {
        target = CommonMethods.convertHexStringToLong(macAddress)
    }

    fun setFromCommandByteArray(byteArray: ByteArray) {
        var targetBinStr = ""
        for (i in 15 downTo 8) {
            targetBinStr += CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        target = java.lang.Long.parseLong(targetBinStr, 2)

        var reserved1BinStr = ""
        for (i in 21 downTo 16) {
            reserved1BinStr = reserved1BinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        reserved1 = java.lang.Long.parseLong(reserved1BinStr, 2)

        val dataBinStr = CommonMethods.convertByteToBinaryString(byteArray[22])
        val reserved2BinStr = dataBinStr.substring(0, 6)
        reserved2 = Integer.parseInt(reserved2BinStr, 2)
        ackRequired = dataBinStr[6] == '1'
        resRequired = dataBinStr[7] == '1'

        val sequenceBinStr = CommonMethods.convertByteToBinaryString(byteArray[23])
        sequence = Integer.parseInt(sequenceBinStr, 2)
    }
}
