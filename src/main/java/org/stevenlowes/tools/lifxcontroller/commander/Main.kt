/*
 * Lifx Commander
 * Author: Nicholas Olsen
 * Email: olsenn@gmail.com
 * Version: v1.0
 * Date: February 08, 2018
 */

package org.stevenlowes.tools.lifxcontroller.commander

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.Command
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.HSBK
import org.stevenlowes.tools.lifxcontroller.messages.device.GetHostFirmware
import org.stevenlowes.tools.lifxcontroller.messages.light.*
import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Levels
import org.stevenlowes.tools.lifxcontroller.values.Waveform

fun main(args: Array<String>) {
    val port = 56700

    // Start Receiving Incoming messages
    val receiveMessages = ReceiveMessages(port)
    receiveMessages.start()

    //Turn On All Lights
    val setPower = SetPowerLight(Levels.MAX)
    val powerOn = Command(setPower)
    ControlMethods.sendBroadcastMessage(powerOn.byteArray, port)

    //Make Lights White
    val hsbk1 = HSBK()
    hsbk1.saturation = Levels.MIN
    val setColor1 = SetColor(hsbk = hsbk1)
    val makeWhite = Command(setColor1)
    ControlMethods.sendBroadcastMessage(makeWhite.byteArray, port)

    //Make light Blue and 50% Brightness (Only light w/ IP = 192.168.2.35)
    val hsbk2 = HSBK()
    hsbk2.hue = Hue.BLUE
    hsbk2.brightness = Levels.MAX / 2
    val setColor2 = SetColor(hsbk = hsbk2)
    val makeBlue = Command(setColor2)
    ControlMethods.sendUdpMessage(makeBlue.byteArray, "192.168.2.35", port)

    //Turn Off Infrared (All Lights)
    val setInfrared = SetInfrared(Levels.MIN)
    val turnOffIr = Command(setInfrared)
    ControlMethods.sendBroadcastMessage(turnOffIr.byteArray, port)

    // Transition Color
    val newColor = HSBK()
    newColor.hue = Hue.RED
    newColor.saturation = Levels.MAX
    newColor.brightness = Levels.MAX
    val setWaveform = SetWaveform()
    setWaveform.color = newColor
    setWaveform.cycles = 2f
    setWaveform.isTransient = true
    setWaveform.period = 4000
    setWaveform.waveform = Waveform.SINUSOID
    val changeColor = Command(setWaveform)
    ControlMethods.sendBroadcastMessage(changeColor.byteArray, port)

    // Print Firmware Version
    val getHostFirmware = GetHostFirmware()
    val firmwareCommand = Command(getHostFirmware)
    firmwareCommand.frameAddress.resRequired = true
    ControlMethods.sendBroadcastMessage(firmwareCommand.byteArray, port)

    // Print Current State
    val get = Get()
    val getCommand = Command(get)
    getCommand.frameAddress.resRequired = true
    ControlMethods.sendBroadcastMessage(getCommand.byteArray, port)

}
