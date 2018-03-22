package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetInfrared extends Payload {
	int code = 120;
	
	public GetInfrared() {}
	
	public int getCode() {
		return code;
	}
}
