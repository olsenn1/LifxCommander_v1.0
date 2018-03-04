package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class StateLabel extends Payload {
	int code = 25;
	String label;			// 32-Bytes
	
	public StateLabel() {
		label = "";
	}
	
	public StateLabel(String label) {
		this.label = label;
	}
	
	public StateLabel(StateLabel stateLabel) {
		label = stateLabel.label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		byte[] labelBytes = new byte[32];
		for(int i=36; i<68; i++) labelBytes[i-36] = byteArray[i];
		label = new String(labelBytes);
	}
}
