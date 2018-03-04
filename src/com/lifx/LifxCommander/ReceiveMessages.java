package com.lifx.LifxCommander;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.lifx.Messages.DataTypes.Command;
import com.lifx.Messages.Device.EchoResponse;
import com.lifx.Messages.Device.StateGroup;
import com.lifx.Messages.Device.StateHostFirmware;
import com.lifx.Messages.Device.StateHostInfo;
import com.lifx.Messages.Device.StateInfo;
import com.lifx.Messages.Device.StateLabel;
import com.lifx.Messages.Device.StateLocation;
import com.lifx.Messages.Device.StatePower_Device;
import com.lifx.Messages.Device.StateService;
import com.lifx.Messages.Device.StateVersion;
import com.lifx.Messages.Device.StateWifiFirmware;
import com.lifx.Messages.Device.StateWifiInfo;
import com.lifx.Messages.Light.StateInfrared;
import com.lifx.Messages.Light.StatePower_Light;
import com.lifx.Messages.Light.State_Light;

public class ReceiveMessages extends Thread{
	
	int port;
	
	public ReceiveMessages() {
		port = 56700;
	}
	
	public ReceiveMessages(int port) {
		this.port = port;
	}
	
	public void run() {
		try {
			System.out.printf("Listening on udp:%s:%d%n", InetAddress.getLocalHost().getHostAddress(), port);
			while(true) {
				byte[] byteArray = ControlMethods.receiveUdpMessage(port);
				Command command = new Command();
				command.setFromCommandByteArray(byteArray);
				//System.out.println("Type = " + command.getProtocol().getType() + "   Byte Array = " + CommonMethods.getHexValueAsString(byteArray));
				if(command.getProtocol().getType() == 3) {
					System.out.println("\nStateService------------------------------------------------");
					System.out.println("     Service: " + ((StateService)command.getPayload()).getService());
					System.out.println("     Port: " + ((StateService)command.getPayload()).getPort());
				}
				if(command.getProtocol().getType() == 13) {
					System.out.println("\nStateHostInfo----------------------------------------------------");
					System.out.println("     Signal Strength: " + ((StateHostInfo)command.getPayload()).getSignal() + "mW");
					System.out.println("     tx: " + ((StateHostInfo)command.getPayload()).getTx() + " bytes");
					System.out.println("     rx: " + ((StateHostInfo)command.getPayload()).getRx() + " bytes");
				}
				if(command.getProtocol().getType() == 15) {
					System.out.println("\nStateHostFirmware------------------------------------------------");
					System.out.println("     Build Date: " + CommonMethods.getDateAsString(((StateHostFirmware)command.getPayload()).getBuild()));
					//System.out.println("     Version: " + ((StateHostFirmware)command.getPayload()).getVersion());
					int major = (int) (((((StateHostFirmware)command.getPayload()).getVersion()) & 0xffff0000) >> 16);
					int minor = (int) (((StateHostFirmware)command.getPayload()).getVersion() & 0xffff);
					System.out.println("Version: " + major + "." + minor);
				}
				if(command.getProtocol().getType() == 17) {
					System.out.println("\nStateWifiInfo----------------------------------------------------");
					System.out.println("     Signal Strength: " + ((StateWifiInfo)command.getPayload()).getSignal() + "mW");
					System.out.println("     tx: " + ((StateWifiInfo)command.getPayload()).getTx() + " bytes");
					System.out.println("     rx: " + ((StateWifiInfo)command.getPayload()).getRx() + " bytes");
				}
				if(command.getProtocol().getType() == 19) {
					System.out.println("\nStateWifiFirmware------------------------------------------------");
					System.out.println("     Build Date: " + CommonMethods.getDateAsString(((StateWifiFirmware)command.getPayload()).getBuild()));
					System.out.println("     Version: " + ((StateWifiFirmware)command.getPayload()).getVersion());
				}
				if(command.getProtocol().getType() == 22) {
					System.out.println("\nStatePower_Device------------------------------------------------");
					System.out.println("     Level: " + ((StatePower_Device)command.getPayload()).getLevel());
				}
				if(command.getProtocol().getType() == 25) {
					System.out.println("\nStateLabel-------------------------------------------------------");
					System.out.println("     Label: " + ((StateLabel)command.getPayload()).getLabel());
				}
				if(command.getProtocol().getType() == 33) {
					System.out.println("\nStateVersion-----------------------------------------------------");
					System.out.println("     Vendor: " + ((StateVersion)command.getPayload()).getVendor());
					System.out.println("     Product: " + ((StateVersion)command.getPayload()).getProduct());
					System.out.println("     Version: " + ((StateVersion)command.getPayload()).getVersion());
				}
				if(command.getProtocol().getType() == 35) {
					System.out.println("\nStateInfo--------------------------------------------------------");
					System.out.println("     Time: " + CommonMethods.getDateAsString(((StateInfo)command.getPayload()).getTime()));
					System.out.println("     Uptime: " + ((StateInfo)command.getPayload()).getUptime().divide(BigInteger.valueOf(1000000000L)) + "s");
					System.out.println("     Downtime: " + ((StateInfo)command.getPayload()).getDowntime().divide(BigInteger.valueOf(1000000000L)) + "s");
				}
				if(command.getProtocol().getType() == 45) {
					//System.out.println("Acknowledged---------------------------------------------------");
				}
				if(command.getProtocol().getType() == 50) {
					System.out.println("\nStateLocation----------------------------------------------------");
					System.out.println("     Label: " + ((StateLocation)command.getPayload()).getLabel());
					System.out.println("     Time: " + CommonMethods.getDateAsString(((StateLocation)command.getPayload()).getUpdatedAt()));
				}
				if(command.getProtocol().getType() == 53) {
					System.out.println("\nStateGroup--------------------------------------------------------");
					System.out.println("     Label: " +((StateGroup)command.getPayload()).getLabel());
					System.out.println("     Time: " + CommonMethods.getDateAsString(((StateGroup)command.getPayload()).getUpdatedAt()));
				}
				if(command.getProtocol().getType() == 59) {
					System.out.println("\nEchoResponse------------------------------------------------------");
					System.out.println("     Payload: " + CommonMethods.getHexValueAsString(((EchoResponse)command.getPayload()).getPayload()));
				}
				if(command.getProtocol().getType() == 107) {
					System.out.println("\nState------------------------------------------------------------");
					System.out.println("     Hue: " + ((State_Light)command.getPayload()).getColor().getHue());
					System.out.println("     Saturation: " + ((State_Light)command.getPayload()).getColor().getSaturation());
					System.out.println("     Brightness: " + ((State_Light)command.getPayload()).getColor().getBrightness());
					System.out.println("     Kelvin: " + ((State_Light)command.getPayload()).getColor().getKelvin());
					System.out.println("     Power: " + ((State_Light)command.getPayload()).getPower());				
					System.out.println("     Label: " + ((State_Light)command.getPayload()).getLabel());
				}
				
				if(command.getProtocol().getType() == 118) {
					System.out.println("\nStatePower_Light------------------------------------------------");
					System.out.println("     Level: " + ((StatePower_Light)command.getPayload()).getLevel());
				}
				
				if(command.getProtocol().getType() == 121) {
					System.out.println("\nStateInfrared---------------------------------------------------");
					System.out.println("     Brightness = " + ((StateInfrared)command.getPayload()).getBrightness());
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
