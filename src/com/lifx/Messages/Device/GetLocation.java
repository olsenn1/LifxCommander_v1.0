package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class GetLocation extends Payload {
	int code = 48;
	
	public GetLocation() {}
	
	public int getCode() {
		return code;
	}

}
