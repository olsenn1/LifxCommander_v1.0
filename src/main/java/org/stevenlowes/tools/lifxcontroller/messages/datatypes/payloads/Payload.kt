package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads

abstract class Payload(val code: Int) : PayloadReadAsBytes, PayloadSetFromBytes
