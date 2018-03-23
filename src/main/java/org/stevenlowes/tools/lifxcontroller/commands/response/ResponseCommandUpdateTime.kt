package org.stevenlowes.tools.lifxcontroller.commands.response

import java.math.BigInteger
import java.time.Instant

abstract class ResponseCommandUpdateTime(code: Int): ResponseCommand(code){
    lateinit var updatedAt: BigInteger

    init {
        setUpdatedAtToNow()
    }

    fun setUpdatedAtToNow() {
        updatedAt = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))
    }
}