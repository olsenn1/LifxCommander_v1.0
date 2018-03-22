package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetWifiFirmware extends Payload{
	private final int code = 18;
	
	public GetWifiFirmware() {}
	
	public int getCode() {
		return code;
	}
}
