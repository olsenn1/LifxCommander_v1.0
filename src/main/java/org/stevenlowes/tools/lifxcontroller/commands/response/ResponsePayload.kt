package org.stevenlowes.tools.lifxcontroller.commands.response

import org.stevenlowes.tools.lifxcontroller.commands.Payload

abstract class ResponsePayload(code: Int) : Payload(code){
    override val byteArray = throw UnsupportedOperationException("Cannot read a Response Payload")
}