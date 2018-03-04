package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class GetGroup extends Payload {
	int code = 51;
	
	public GetGroup() {}
	
	public int getCode() {
		return code;
	}

}
