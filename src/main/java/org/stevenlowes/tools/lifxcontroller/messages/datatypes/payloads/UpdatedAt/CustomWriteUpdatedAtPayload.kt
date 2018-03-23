package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.UpdatedAt

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload
import java.math.BigInteger
import java.time.Instant

abstract class CustomWriteUpdatedAtPayload(code: Int): CustomWritePayload(code){
    lateinit var updatedAt: BigInteger

    init {
        setUpdatedAtToNow()
    }

    fun setUpdatedAtToNow() {
        updatedAt = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(1000000L))
    }
}