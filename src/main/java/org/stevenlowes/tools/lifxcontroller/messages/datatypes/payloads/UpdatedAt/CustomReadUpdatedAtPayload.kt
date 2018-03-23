package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.UpdatedAt

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import java.math.BigInteger
import java.time.Instant

abstract class CustomReadUpdatedAtPayload(code: Int): CustomReadPayload(code){
    lateinit var updatedAtNanos: BigInteger

    init {
        setUpdatedAtToNow()
    }

    fun setUpdatedAtToNow() {
        updatedAtNanos = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))
    }
}