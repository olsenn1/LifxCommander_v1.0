package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

import java.math.BigInteger

class StateInfo(var time: BigInteger = BigInteger.ZERO, var uptime: BigInteger = BigInteger.ZERO, var downtime: BigInteger = BigInteger.ZERO) : SetOnlyPayload(35) {

    constructor(stateInfo: StateInfo): this(stateInfo.time, stateInfo.uptime, stateInfo.downtime)

    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val timeBytes = ByteArray(8)
        for (i in 43 downTo 36) {
            timeBytes[-1 * i + 43] = byteArray[i]
        }
        time = BigInteger(timeBytes)

        val uptimeBytes = ByteArray(8)
        for (i in 51 downTo 44) {
            uptimeBytes[-1 * i + 51] = byteArray[i]
        }
        uptime = BigInteger(uptimeBytes)

        val downtimeBytes = ByteArray(8)
        for (i in 59 downTo 52) {
            downtimeBytes[-1 * i + 59] = byteArray[i]
        }
        downtime = BigInteger(downtimeBytes)

    }

}
