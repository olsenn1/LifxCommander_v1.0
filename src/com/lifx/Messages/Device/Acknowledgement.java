package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class Acknowledgement extends Payload {
	int code = 45;
	
	public Acknowledgement() {}
	
	public int getCode() {
		return code;
	}
}
