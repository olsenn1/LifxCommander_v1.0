package com.lifx.LifxCommander;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class ControlMethods {
	
	static public void sendBroadcastMessage(byte[] messageByteArray, int port) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);
		socket.setReuseAddress(true);

		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
			
			if(networkInterface.isLoopback() || !networkInterface.isUp()) {
				continue;
			}
			
			for(InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
				InetAddress broadcast = interfaceAddress.getBroadcast();
				if(broadcast == null) continue;
			
				DatagramPacket packet = new DatagramPacket(messageByteArray, messageByteArray.length, broadcast, port);
				socket.send(packet);
			}
		}
		socket.close();
	}
	
	static public void sendUdpMessage(String message, String ipAddress, int port) throws IOException {
		byte[] messageByteArray = CommonMethods.convertHexStringToByteArray(message);
		InetAddress address = InetAddress.getByName(ipAddress);
		DatagramPacket packet = new DatagramPacket(messageByteArray, messageByteArray.length, address, port);
		DatagramSocket socket = new DatagramSocket();
		socket.setReuseAddress(true);
		socket.send(packet);
		socket.close();
	}
	
	static public void sendUdpMessage(byte[] messageByteArray, String ipAddress, int port) throws IOException {
		InetAddress address = InetAddress.getByName(ipAddress);
		DatagramPacket packet = new DatagramPacket(messageByteArray, messageByteArray.length, address, port);
		DatagramSocket socket = new DatagramSocket();
		socket.setReuseAddress(true);
		socket.send(packet);
		socket.close();	
	}
	
	static public byte[] receiveUdpMessage(int port) throws IOException{
		DatagramSocket socket = new DatagramSocket(null);
		socket.setReuseAddress(true);
		socket.bind(new InetSocketAddress(port));
		byte[] data = new byte[1500];
			
		DatagramPacket packet = new DatagramPacket(data, data.length);
		while(true) {
			socket.receive(packet);
			socket.close();
			return packet.getData();
		}
	}
	
	
	
	
	
}
