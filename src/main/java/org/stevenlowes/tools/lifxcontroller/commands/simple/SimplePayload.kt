package org.stevenlowes.tools.lifxcontroller.commands.simple

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

open class SimplePayload(code: Int): ResponsePayload(code){
    final override val byteArray = throw UnsupportedOperationException("Cannot read from default payload")
}