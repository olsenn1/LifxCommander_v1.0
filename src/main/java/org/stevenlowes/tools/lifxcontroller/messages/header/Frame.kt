package org.stevenlowes.tools.lifxcontroller.messages.header

import org.stevenlowes.tools.lifxcontroller.CommonMethods

//TODO make immutable

data class Frame(
        var size: Int = 0,                // 16-Bits
        var origin: Int = 0,                // 2-Bits
        var tagged: Boolean = false,            // 1-Bit
        var addressable: Boolean = true,    // 1-Bit
        var protocol: Int = 1024,            // 12-Bits
        var source: Long = 0            // 32-Bits
                ) {

    val byteArray: ByteArray
        get() {

            val byteArray = ByteArray(8)

            var sizeBytes: ByteArray? = ByteArray(2)
            val sizeBinStr = Integer.toBinaryString(0x10000 or size).substring(1)
            sizeBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(sizeBinStr)
            byteArray[0] = sizeBytes!![0]
            byteArray[1] = sizeBytes[1]

            var dataBytes: ByteArray? = ByteArray(2)
            val originBinStr = Integer.toBinaryString(0x04 or origin).substring(1)
            val taggedBinStr: String
            if (tagged)
                taggedBinStr = "1"
            else
                taggedBinStr = "0"
            val addressableBinStr: String
            if (addressable)
                addressableBinStr = "1"
            else
                addressableBinStr = "0"
            val protocolBinStr = Integer.toBinaryString(0x1000 or protocol).substring(1)
            val dataBinStr = originBinStr + taggedBinStr + addressableBinStr + protocolBinStr
            dataBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(dataBinStr)
            byteArray[2] = dataBytes!![0]
            byteArray[3] = dataBytes[1]

            var sourceBytes: ByteArray? = ByteArray(4)
            val sourceBinStr = java.lang.Long.toBinaryString(0x100000000L or source).substring(1)
            sourceBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(sourceBinStr)
            byteArray[4] = sourceBytes!![0]
            byteArray[5] = sourceBytes[1]
            byteArray[6] = sourceBytes[2]
            byteArray[7] = sourceBytes[3]

            return byteArray
        }

    fun setFromCommandByteArray(byteArray: ByteArray) {
        val sizeBinStr = CommonMethods.convertByteToBinaryString(byteArray[1]) + CommonMethods.convertByteToBinaryString(
                byteArray[0])
        size = Integer.parseInt(sizeBinStr, 2)

        val dataBinStr = CommonMethods.convertByteToBinaryString(byteArray[3]) + CommonMethods.convertByteToBinaryString(
                byteArray[2])
        val originBinStr = dataBinStr.substring(0, 2)
        origin = Integer.parseInt(originBinStr, 2)

        tagged = dataBinStr[2] == '1'
        addressable = dataBinStr[3] == '1'
        val protocolBinStr = dataBinStr.substring(4, 16)
        protocol = Integer.parseInt(protocolBinStr, 2)

        var sourceBinStr = ""
        for (i in 7 downTo 4) {
            sourceBinStr = sourceBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        source = java.lang.Long.parseLong(sourceBinStr, 2)

    }

}
