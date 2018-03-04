package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class GetWifiInfo extends Payload {
	int code = 16;
	
	public GetWifiInfo() {}
	
	public int getCode() {
		return code;
	}

}
