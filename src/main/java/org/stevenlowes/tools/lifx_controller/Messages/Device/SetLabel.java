package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class SetLabel extends Payload{
	int code = 24;
	String label;			// 32-Bytes
	
	public SetLabel() {
		label = "";
	}
	
	public SetLabel(String label) {
		this.label = label;
	}
	
	public SetLabel(SetLabel setLabel) {
		label = setLabel.label;
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
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[32];
		byteArray = label.getBytes();
		return byteArray;
	}
}
