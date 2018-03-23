package org.stevenlowes.tools.lifxcontroller.commands.request

import java.math.BigInteger
import java.time.Instant

abstract class RequestCommandUpdateTime(code: Int) : RequestCommand(code) {
    var updatedAtNanos: BigInteger = BigInteger.valueOf(Instant.now().toEpochMilli()).multiply(BigInteger.valueOf(
            1000 * 1000L))
}