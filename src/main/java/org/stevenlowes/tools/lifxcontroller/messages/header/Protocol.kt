package org.stevenlowes.tools.lifxcontroller.messages.header


import org.stevenlowes.tools.lifxcontroller.Utils

//TODO make immutable

data class Protocol(var reserved1: Long = 0,                // 64-Bits
                    var type: Int = 0,                    // 16-Bits
                    var reserved2: Int = 0                // 16-Bits
                   ) {


    val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(12)

            val reserved1Bytes: ByteArray = Utils.toByteArray(8, reserved1)
            System.arraycopy(reserved1Bytes, 0, byteArray, 0, 8)

            val typeBytes: ByteArray = Utils.toByteArray(2, type)
            byteArray[8] = typeBytes[0]
            byteArray[9] = typeBytes[1]

            val reserved2Bytes: ByteArray = Utils.toByteArray(2, reserved2)
            byteArray[10] = reserved2Bytes[0]
            byteArray[11] = reserved2Bytes[1]

            return byteArray
        }

    fun setFromCommandByteArray(byteArray: ByteArray) {
        var reserved1BinStr = ""
        for (i in 31 downTo 24) {
            reserved1BinStr = reserved1BinStr + Utils.convertByteToBinaryString(byteArray[i])
        }
        reserved1 = java.lang.Long.parseLong(reserved1BinStr, 2)

        val typeBinStr = Utils.convertByteToBinaryString(byteArray[33]) + Utils.convertByteToBinaryString(
                byteArray[32])
        type = Integer.parseInt(typeBinStr, 2)

        val reserved2BinStr = Utils.convertByteToBinaryString(byteArray[35]) + Utils.convertByteToBinaryString(
                byteArray[34])
        reserved2 = Integer.parseInt(reserved2BinStr, 2)
    }

}
