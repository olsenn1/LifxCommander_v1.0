package org.stevenlowes.tools.lifxcontroller.commands.header

import org.stevenlowes.tools.lifxcontroller.Utils

data class Frame(
        val size: Int = 0,                // 16-Bits
        val origin: Int = 0,                // 2-Bits
        val tagged: Boolean = false,            // 1-Bit
        val addressable: Boolean = true,    // 1-Bit
        val protocol: Int = 1024,            // 12-Bits
        val source: Long = 0            // 32-Bits
                ) {

    val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(8)

            val sizeBinStr = Integer.toBinaryString(0x10000 or size).substring(1)
            val sizeBytes: ByteArray = Utils.convertBinaryStringToLittleEndianByteArray(sizeBinStr)
            byteArray[0] = sizeBytes[0]
            byteArray[1] = sizeBytes[1]

            val originBinStr = Integer.toBinaryString(0x04 or origin).substring(1)

            val taggedBinStr: String = if (tagged)
                "1"
            else
                "0"

            val addressableBinStr: String = if (addressable)
                "1"
            else
                "0"

            val protocolBinStr = Integer.toBinaryString(0x1000 or protocol).substring(1)
            val dataBinStr = originBinStr + taggedBinStr + addressableBinStr + protocolBinStr

            val dataBytes = Utils.convertBinaryStringToLittleEndianByteArray(dataBinStr)
            byteArray[2] = dataBytes[0]
            byteArray[3] = dataBytes[1]

            val sourceBinStr = java.lang.Long.toBinaryString(0x100000000L or source).substring(1)
            val sourceBytes = Utils.convertBinaryStringToLittleEndianByteArray(sourceBinStr)
            byteArray[4] = sourceBytes[0]
            byteArray[5] = sourceBytes[1]
            byteArray[6] = sourceBytes[2]
            byteArray[7] = sourceBytes[3]

            return byteArray
        }

    companion object {
        fun loadFrom(byteArray: ByteArray): Frame {
            val sizeBinStr = Utils.convertByteToBinaryString(byteArray[1]) + Utils.convertByteToBinaryString(
                    byteArray[0])
            val size = Integer.parseInt(sizeBinStr, 2)

            val dataBinStr = Utils.convertByteToBinaryString(byteArray[3]) + Utils.convertByteToBinaryString(
                    byteArray[2])
            val originBinStr = dataBinStr.substring(0, 2)
            val origin = Integer.parseInt(originBinStr, 2)

            val tagged = dataBinStr[2] == '1'
            val addressable = dataBinStr[3] == '1'
            val protocolBinStr = dataBinStr.substring(4, 16)
            val protocol = Integer.parseInt(protocolBinStr, 2)

            val sourceBinStr = (7.downTo(4)).map { Utils.convertByteToBinaryString(byteArray[it]) }.joinToString("")
            val source = java.lang.Long.parseLong(sourceBinStr, 2)

            return Frame(size, origin, tagged, addressable, protocol, source)
        }
    }
}
