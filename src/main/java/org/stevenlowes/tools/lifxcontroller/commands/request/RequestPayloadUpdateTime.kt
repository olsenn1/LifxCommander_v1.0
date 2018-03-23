package org.stevenlowes.tools.lifxcontroller.commands.request

import java.math.BigInteger
import java.time.Instant

abstract class RequestPayloadUpdateTime(code: Int): RequestPayload(code){
    lateinit var updatedAtNanos: BigInteger

    init {
        setUpdatedAtToNow()
    }

    fun setUpdatedAtToNow() {
        updatedAtNanos = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))
    }
}