package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class GetHostInfo extends Payload {
	int code = 12;
	
	public GetHostInfo() {}
	
	public int getCode() {
		return code;
	}

}
