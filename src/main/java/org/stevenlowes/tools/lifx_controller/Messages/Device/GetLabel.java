package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetLabel extends Payload{
	int code = 23;
	
	public GetLabel() {}
	
	public int getCode() {
		return code;
	}
}
