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
import org.stevenlowes.tools.lifxcontroller.messages.device.SetPowerDevice
import org.stevenlowes.tools.lifxcontroller.messages.light.*
import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Waveform

fun main(args: Array<String>) {
    val port = 56700
    val ip1 = "192.168.1.106"
    val ip2 = "192.168.1.107"
    val ip3 = "192.168.1.108"
    val ip4 = "192.168.1.109"
    val ip5 = "192.168.1.110"
    val ip6 = "192.168.1.111"

    val ips = listOf(ip1, ip2, ip3, ip4, ip5, ip6)

    // Start Receiving Incoming messages
    val receiveMessages = ReceiveMessages(port)
    receiveMessages.start()

    for(i in 0..10){
        ips.forEach {
            ControlMethods.sendUdpMessage(Command(SetPowerLight(Level.MAX)).byteArray, it, port)
        }

        Thread.sleep(1000)

        ips.forEach {
            ControlMethods.sendUdpMessage(Command(SetPowerLight(Level.MIN)).byteArray, it, port)
        }

        ips.forEach {
            ControlMethods.sendUdpMessage(Command(SetColor(color = HSBK(saturation = Level.MIN))).byteArray, it, port)
        }

        Thread.sleep(1000)

        ips.forEach {
            ControlMethods.sendUdpMessage(Command(SetPowerLight(Level.MAX)).byteArray, it, port)
        }

        Thread.sleep(1000)
        println("Sending")

        ips.forEach {
            ControlMethods.sendUdpMessage(Command(SetWaveform(color = HSBK(Hue.random), isTransient = true, period = 100, cycles = 100f)).byteArray, it, port)
        }

        Thread.sleep(10000)
    }
}
