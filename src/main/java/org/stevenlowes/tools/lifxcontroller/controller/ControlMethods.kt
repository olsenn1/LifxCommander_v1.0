package org.stevenlowes.tools.lifxcontroller.controller

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface

internal object ControlMethods {
    fun sendBroadcastMessage(messageByteArray: ByteArray, port: Int) {
        val socket = DatagramSocket()
        socket.broadcast = true

        val interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement()

            if (networkInterface.isLoopback || !networkInterface.isUp) {
                continue
            }

            for (interfaceAddress in networkInterface.interfaceAddresses) {
                val broadcast = interfaceAddress.broadcast ?: continue

                val packet = DatagramPacket(messageByteArray, messageByteArray.size, broadcast, port)
                socket.send(packet)
            }
        }
        socket.close()
    }

    fun sendUdpMessage(messageByteArray: ByteArray, ipAddress: String, port: Int) {
        val address = InetAddress.getByName(ipAddress)
        val packet = DatagramPacket(messageByteArray, messageByteArray.size, address, port)
        val socket = DatagramSocket()
        socket.send(packet)
        socket.close()
    }

    fun receiveUdpMessage(port: Int): ByteArray {
        val socket = DatagramSocket(port)
        val data = ByteArray(1500)

        val packet = DatagramPacket(data, data.size)
        socket.receive(packet)
        socket.close()
        return packet.data
    }
}
