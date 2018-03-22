package org.stevenlowes.tools.lifx_controller.Messages.DataTypes;

import org.stevenlowes.tools.lifx_controller.Messages.Device.*;
import org.stevenlowes.tools.lifx_controller.Messages.Header.Frame;
import org.stevenlowes.tools.lifx_controller.Messages.Header.FrameAddress;
import org.stevenlowes.tools.lifx_controller.Messages.Header.Protocol;
import org.stevenlowes.tools.lifx_controller.Messages.Light.StateInfrared;
import org.stevenlowes.tools.lifx_controller.Messages.Light.StatePower_Light;
import org.stevenlowes.tools.lifx_controller.Messages.Light.State_Light;

public class Command {
	private Frame frame;
	private FrameAddress frameAddress;
	private Protocol protocol;
	private Payload payload;
	
	public Command() {
		frame = new Frame();
		frameAddress = new FrameAddress();
		protocol = new Protocol();
		payload = new Payload();
	}
	
	public Command(Frame frame, FrameAddress frameAddress, Protocol protocol, Payload payload){
		this.frame = frame;
		this.frameAddress = frameAddress;
		this.protocol = protocol;
		this.payload = payload;
		setSize();
	}
	
	public Command(Payload payload){
		frame = new Frame();
		frameAddress = new FrameAddress();
		protocol = new Protocol();
		this.payload = payload;
		protocol.setType(payload.getCode());
		
		setSize();
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	public FrameAddress getFrameAddress() {
		return frameAddress;
	}
	
	public void setFrameAddress(FrameAddress frameAddress) {
		this.frameAddress = frameAddress;
	}
	
	public Protocol getProtocol() {
		return protocol;
	}
	
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
	
	public Payload getPayload() {
		return payload;
	}
	
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	private void setSize() {
		int size;
		if(payload.getByteArray() != null) size = frame.getByteArray().length + frameAddress.getByteArray().length + protocol.getByteArray().length + payload.getByteArray().length;
		else size = frame.getByteArray().length + frameAddress.getByteArray().length + protocol.getByteArray().length;
		frame.setSize(size);
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[frame.getSize()];
		int frameEnd = frame.getByteArray().length;
		int frameAddressEnd = frame.getByteArray().length + frameAddress.getByteArray().length;
		int protocolEnd = frame.getByteArray().length + frameAddress.getByteArray().length + protocol.getByteArray().length;
		
		for(int i=0; i<frameEnd; i++) {
			byteArray[i] = frame.getByteArray()[i];
		}
		
		for(int i=frameEnd; i<frameAddressEnd; i++) {
			byteArray[i] = frameAddress.getByteArray()[i - frameEnd];
		}
		
		for(int i=frameAddressEnd; i<protocolEnd; i++) {
			byteArray[i] = protocol.getByteArray()[i - frameAddressEnd];
		}
		
		for(int i=protocolEnd; i<frame.getSize(); i++) {
			 byteArray[i] = payload.getByteArray()[i - protocolEnd];
		}
		
		return byteArray;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		frame.setFromCommandByteArray(byteArray);
		frameAddress.setFromCommandByteArray(byteArray);
		protocol.setFromCommandByteArray(byteArray);
		
		if(protocol.getType() == 3) payload = new StateService();
		if(protocol.getType() == 13) payload = new StateHostInfo();
		if(protocol.getType() == 15) payload = new StateHostFirmware();
		if(protocol.getType() == 17) payload = new StateWifiInfo();
		if(protocol.getType() == 19) payload = new StateWifiFirmware();
		if(protocol.getType() == 22) payload = new StatePower_Device();
		if(protocol.getType() == 25) payload = new StateLabel();
		if(protocol.getType() == 33) payload = new StateVersion();
		if(protocol.getType() == 35) payload = new StateInfo();
		if(protocol.getType() == 45) payload = new Acknowledgement();
		if(protocol.getType() == 50) payload = new StateLocation();
		if(protocol.getType() == 53) payload = new StateGroup();
		if(protocol.getType() == 59) payload = new EchoResponse();
		if(protocol.getType() == 107) payload = new State_Light();
		if(protocol.getType() == 118) payload = new StatePower_Light();
		if(protocol.getType() == 121) payload = new StateInfrared();
		
		
		payload.setFromCommandByteArray(byteArray);

	}
}
