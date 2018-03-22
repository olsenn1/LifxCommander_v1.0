package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetHostInfo extends Payload {
	int code = 12;
	
	public GetHostInfo() {}
	
	public int getCode() {
		return code;
	}

}
