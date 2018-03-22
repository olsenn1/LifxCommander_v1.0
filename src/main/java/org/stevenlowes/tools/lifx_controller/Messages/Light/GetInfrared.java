package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetInfrared extends Payload {
	private final int code = 120;
	
	public GetInfrared() {}
	
	public int getCode() {
		return code;
	}
}
