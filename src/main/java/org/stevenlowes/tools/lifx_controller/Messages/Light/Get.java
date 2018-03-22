package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class Get extends Payload{
	private final int code = 101;
	
	public Get() {}
	
	public int getCode() {
		return code;
	}
}
