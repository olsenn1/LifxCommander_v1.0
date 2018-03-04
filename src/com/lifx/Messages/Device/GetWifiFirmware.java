package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class GetWifiFirmware extends Payload {
	int code = 18;
	
	public GetWifiFirmware() {}
	
	public int getCode() {
		return code;
	}
}
