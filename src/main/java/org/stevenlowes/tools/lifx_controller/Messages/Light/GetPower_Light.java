package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetPower_Light extends Payload{
	private final int code = 116;
	
	public GetPower_Light() {}
	
	public int getCode() {
		return code;
	}
}
