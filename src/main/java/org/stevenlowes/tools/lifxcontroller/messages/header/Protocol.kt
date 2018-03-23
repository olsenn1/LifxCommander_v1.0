package org.stevenlowes.tools.lifxcontroller.messages.header


import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods

//TODO make immutable

data class Protocol(var reserved1: Long = 0,                // 64-Bits
                    var type: Int = 0,                    // 16-Bits
                    var reserved2: Int = 0                // 16-Bits
                   ) {


    val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(12)

            var reserved1Bytes: ByteArray? = ByteArray(8)
            val reserved1BinStr = String.format("%64s", java.lang.Long.toBinaryString(reserved1)).replace(' ', '0')
            reserved1Bytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(reserved1BinStr)
            System.arraycopy(reserved1Bytes!!, 0, byteArray, 0, 8)

            var typeBytes: ByteArray? = ByteArray(2)
            val typeBinStr = String.format("%16s", Integer.toBinaryString(type)).replace(' ', '0')
            typeBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(typeBinStr)
            byteArray[8] = typeBytes!![0]
            byteArray[9] = typeBytes[1]

            var reserved2Bytes: ByteArray? = ByteArray(2)
            val reserved2BinStr = String.format("%16s", Integer.toBinaryString(reserved2)).replace(' ', '0')
            reserved2Bytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(reserved2BinStr)
            byteArray[10] = reserved2Bytes!![0]
            byteArray[11] = reserved2Bytes[1]

            return byteArray
        }

    constructor(protocol: Protocol) : this(protocol.reserved1, protocol.type, protocol.reserved2)

    fun setFromCommandByteArray(byteArray: ByteArray) {
        var reserved1BinStr = ""
        for (i in 31 downTo 24) {
            reserved1BinStr = reserved1BinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        reserved1 = java.lang.Long.parseLong(reserved1BinStr, 2)

        val typeBinStr = CommonMethods.convertByteToBinaryString(byteArray[33]) + CommonMethods.convertByteToBinaryString(
                byteArray[32])
        type = Integer.parseInt(typeBinStr, 2)

        val reserved2BinStr = CommonMethods.convertByteToBinaryString(byteArray[35]) + CommonMethods.convertByteToBinaryString(
                byteArray[34])
        reserved2 = Integer.parseInt(reserved2BinStr, 2)
    }

}
