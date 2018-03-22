package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetInfo extends Payload{
	private final int code = 34;
	
	public GetInfo() {}
	
	public int getCode() {
		return code;
	}

}
