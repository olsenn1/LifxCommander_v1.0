package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetService extends Payload {
	private final int code = 2;
	
	public GetService() {}
	
	public int getCode() {
		return code;
	}
}
