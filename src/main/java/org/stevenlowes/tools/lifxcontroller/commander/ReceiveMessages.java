package org.stevenlowes.tools.lifxcontroller.commander;

import java.io.IOException;
import java.net.InetAddress;

class ReceiveMessages extends Thread {

    private final int port;

    public ReceiveMessages() {
        port = 56700;
    }

    public ReceiveMessages(int port) {
        this.port = port;
    }

    public void run() {
        try {
            System.out.printf("Listening on udp:%s:%d%n", InetAddress.getLocalHost().getHostAddress(), port);
            while (true) {
                byte[] byteArray = ControlMethods.INSTANCE.receiveUdpMessage(port);
                //TODO fix this mess
                /*
                Command command = new Command();
                command.setFromCommandByteArray(byteArray);
                //System.out.println("Type = " + command.getProtocol().getType() + "   Byte Array = " + Utils.getHexValueAsString(byteArray));
                if (command.getProtocol().getType() == 3) {
                    System.out.println("\nStateService------------------------------------------------");
                    System.out.println("     Service: " + ((StateService) command.getPayload()).getService());
                    System.out.println("     Port: " + ((StateService) command.getPayload()).getPort());
                }
                if (command.getProtocol().getType() == 13) {
                    System.out.println("\nStateHostInfo----------------------------------------------------");
                    System.out.println("     Signal Strength: " + ((StateHostInfo) command.getPayload()).getSignal() + "mW");
                    System.out.println("     tx: " + ((StateHostInfo) command.getPayload()).getTx() + " bytes");
                    System.out.println("     rx: " + ((StateHostInfo) command.getPayload()).getRx() + " bytes");
                }
                if (command.getProtocol().getType() == 15) {
                    System.out.println("\nStateHostFirmware------------------------------------------------");
                    System.out.println("     Build Date: " + Utils.getDateAsString(((StateHostFirmware) command.getPayload()).getBuild()));
                    //System.out.println("     Version: " + ((StateHostFirmware)command.getBytes()).getVersion());
                    int major = (int) (((((StateHostFirmware) command.getPayload()).getVersion()) & 0xffff0000) >> 16);
                    int minor = (int) (((StateHostFirmware) command.getPayload()).getVersion() & 0xffff);
                    System.out.println("Version: " + major + "." + minor);
                }
                if (command.getProtocol().getType() == 17) {
                    System.out.println("\nStateWifiInfo----------------------------------------------------");
                    System.out.println("     Signal Strength: " + ((StateWifiInfo) command.getPayload()).getSignal() + "mW");
                    System.out.println("     tx: " + ((StateWifiInfo) command.getPayload()).getTx() + " bytes");
                    System.out.println("     rx: " + ((StateWifiInfo) command.getPayload()).getRx() + " bytes");
                }
                if (command.getProtocol().getType() == 19) {
                    System.out.println("\nStateWifiFirmware------------------------------------------------");
                    System.out.println("     Build Date: " + Utils.getDateAsString(((StateWifiFirmware) command.getPayload()).getBuild()));
                    System.out.println("     Version: " + ((StateWifiFirmware) command.getPayload()).getVersion());
                }
                if (command.getProtocol().getType() == 22) {
                    System.out.println("\nStatePowerDevice------------------------------------------------");
                    System.out.println("     Level: " + ((StatePowerDevice) command.getPayload()).getLevel());
                }
                if (command.getProtocol().getType() == 25) {
                    System.out.println("\nStateLabel-------------------------------------------------------");
                    System.out.println("     Label: " + ((StateLabel) command.getPayload()).getLabel());
                }
                if (command.getProtocol().getType() == 33) {
                    System.out.println("\nStateVersion-----------------------------------------------------");
                    System.out.println("     Vendor: " + ((StateVersion) command.getPayload()).getVendor());
                    System.out.println("     Product: " + ((StateVersion) command.getPayload()).getProduct());
                    System.out.println("     Version: " + ((StateVersion) command.getPayload()).getVersion());
                }
                if (command.getProtocol().getType() == 35) {
                    System.out.println("\nStateInfo--------------------------------------------------------");
                    System.out.println("     Time: " + Utils.getDateAsString(((StateInfo) command.getPayload()).getTime()));
                    System.out.println("     Uptime: " + ((StateInfo) command.getPayload()).getUptime().divide(BigInteger.valueOf(1000000000L)) + "s");
                    System.out.println("     Downtime: " + ((StateInfo) command.getPayload()).getDowntime().divide(BigInteger.valueOf(1000000000L)) + "s");
                }
                if (command.getProtocol().getType() == 45) {
                    System.out.println("Acknowledged---------------------------------------------------");
                }
                if (command.getProtocol().getType() == 50) {
                    System.out.println("\nStateLocation----------------------------------------------------");
                    System.out.println("     Label: " + ((StateLocation) command.getPayload()).getLabel());
                    System.out.println("     Time: " + Utils.getDateAsString(((StateLocation) command.getPayload()).getUpdatedAtNanos()));
                }
                if (command.getProtocol().getType() == 53) {
                    System.out.println("\nStateGroup--------------------------------------------------------");
                    System.out.println("     Label: " + ((StateGroup) command.getPayload()).getLabel());
                    System.out.println("     Time: " + Utils.getDateAsString(((StateGroup) command.getPayload()).getUpdatedAtNanos()));
                }
                if (command.getProtocol().getType() == 59) {
                    System.out.println("\nEchoResponse------------------------------------------------------");
                    System.out.println("     Payload: " + Utils.getHexValueAsString(((EchoResponse) command.getPayload()).getBytes()));
                }
                if (command.getProtocol().getType() == 107) {
                    System.out.println("\nState------------------------------------------------------------");
                    System.out.println("     Hue: " + ((StateLight) command.getPayload()).getColor().getAsLevel());
                    System.out.println("     Saturation: " + ((StateLight) command.getPayload()).getColor().getSaturation());
                    System.out.println("     Brightness: " + ((StateLight) command.getPayload()).getColor().getBrightness());
                    System.out.println("     Temp: " + ((StateLight) command.getPayload()).getColor().getKelvin());
                    System.out.println("     Power: " + ((StateLight) command.getPayload()).getPower());
                    System.out.println("     Label: " + ((StateLight) command.getPayload()).getLabel());
                }

                if (command.getProtocol().getType() == 118) {
                    System.out.println("\nStatePowerLight------------------------------------------------");
                    System.out.println("     Level: " + ((StatePowerLight) command.getPayload()).getLevel());
                }

                if (command.getProtocol().getType() == 121) {
                    System.out.println("\nStateInfrared---------------------------------------------------");
                    System.out.println("     Brightness = " + ((StateInfrared) command.getPayload()).getBrightness());
                }
                */
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
