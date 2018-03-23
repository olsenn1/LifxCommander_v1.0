/*
 * Lifx Commander
 * Author: Nicholas Olsen
 * Email: olsenn@gmail.com
 * Version: v1.0
 * Date: February 08, 2018
 */

package org.stevenlowes.tools.lifxcontroller.controller

import org.stevenlowes.tools.lifxcontroller.commands.request.light.SetColor
import org.stevenlowes.tools.lifxcontroller.commands.request.light.SetPowerLight
import org.stevenlowes.tools.lifxcontroller.commands.request.light.SetWaveform
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Level

fun main(args: Array<String>) {
    val port = 56700
    val ip1 = "192.168.1.106"
    val ip2 = "192.168.1.107"
    val ip3 = "192.168.1.108"
    val ip4 = "192.168.1.109"
    val ip5 = "192.168.1.110"
    val ip6 = "192.168.1.111"

    val ips = listOf(ip1, ip2, ip3, ip4, ip5, ip6)

    // Start Receiving Incoming commands
    ReceiveMessages(port).start()

    for (i in 1..3) {
        ips.forEach {
            ControlMethods.sendUdpMessage(SetPowerLight(Level.MAX).commandByteArray, it, port)
        }

        Thread.sleep(1000)

        ips.forEach {
            ControlMethods.sendUdpMessage(SetPowerLight(Level.MIN).commandByteArray, it, port)
        }

        ips.forEach {
            ControlMethods.sendUdpMessage(SetColor(color = Color(
                    saturation = Level.MIN)).commandByteArray, it, port)
        }

        Thread.sleep(1000)

        ips.forEach {
            ControlMethods.sendUdpMessage(SetPowerLight(Level.MAX).commandByteArray, it, port)
        }

        Thread.sleep(1000)
        println("Sending")

        ips.forEach {
            ControlMethods.sendUdpMessage(SetWaveform(color = Color(Hue.random),
                                                      isTransient = true,
                                                      period = 100,
                                                      cycles = 10f).commandByteArray, it, port)
        }

        Thread.sleep(1000)
    }

    System.exit(0)
}
