package org.stevenlowes.tools.lifxcontroller

import java.math.BigInteger
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val rand = Random()

    fun convertBinaryStringToLittleEndianByteArray(binValueAsString: String): ByteArray {
        if (binValueAsString.length % 8 == 0) {
            val arrayLength = binValueAsString.length / 8
            val byteArray = ByteArray(arrayLength)
            val binaryToLong = java.lang.Long.parseLong(binValueAsString, 2)
            val byteBuffer = ByteBuffer.allocate(8)
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN)
            byteBuffer.putLong(binaryToLong)

            System.arraycopy(byteBuffer.array(), 0, byteArray, 0, arrayLength)

            return byteArray
        }
        else {
            throw RuntimeException("Binary number does not fit into an even number of bytes")
        }
    }

    fun convertByteToBinaryString(b: Byte): String {
        return String.format("%8s", Integer.toBinaryString(b.toInt())).replace(' ', '0')
    }

    fun convertHexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    fun convertHexStringToLong(s: String): Long {
        return java.lang.Long.parseLong(s, 16)
    }

    fun getHexValueAsString(byteArray: ByteArray): String {
        val stringBuilder = StringBuilder()
        for (b in byteArray) {
            stringBuilder.append(String.format("%02X ", b))
        }
        return stringBuilder.toString()
    }

    fun getDateAsString(nanoseconds: BigInteger): String {
        val milliseconds = nanoseconds.divide(BigInteger.valueOf(1000000L))
        val duration = milliseconds.toLong()
        val date = Date(duration)
        val sdf = SimpleDateFormat("EEEE,MMMM d,yyyy @h:mma", Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        return sdf.format(date)
    }

    fun randomBytes(count: Int): ByteArray {
        val bytes = ByteArray(count)
        rand.nextBytes(bytes)
        return bytes
    }

    fun toByteArray(bytes: Int, number: Long): ByteArray {
        return Utils.convertBinaryStringToLittleEndianByteArray(String.format("%${bytes * 8}s",
                                                                              java.lang.Long.toBinaryString(
                                                                                      number)).replace(' ',
                                                                                                       '0'))
    }

    fun toByteArray(bytes: Int, number: Int): ByteArray {
        return toByteArray(bytes, number.toLong())
    }

    fun boolToByteArray(bool: Boolean): ByteArray {
        return toByteArray(1, (if (bool) 1 else 0).toLong())
    }

    fun toByteArray(bytes: Int, number: Float): ByteArray {
        return toByteArray(bytes, java.lang.Float.floatToRawIntBits(number).toLong())
    }
}
