package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetHostFirmware extends Payload{
	int code = 14;
	
	public GetHostFirmware() {}
	
	public int getCode() {
		return code;
	}
}
