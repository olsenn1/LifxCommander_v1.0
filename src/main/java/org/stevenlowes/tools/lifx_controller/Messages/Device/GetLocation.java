package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetLocation extends Payload{
	int code = 48;
	
	public GetLocation() {}
	
	public int getCode() {
		return code;
	}

}
