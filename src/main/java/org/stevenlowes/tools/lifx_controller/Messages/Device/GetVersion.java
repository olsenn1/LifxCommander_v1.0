package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetVersion extends Payload {
	private final int code = 32;
	
	public GetVersion() {}
	
	public int getCode() {
		return code;
	}
}
