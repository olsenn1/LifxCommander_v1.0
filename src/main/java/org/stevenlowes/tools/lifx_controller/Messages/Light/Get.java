package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class Get extends Payload{
	int code = 101;
	
	public Get() {}
	
	public int getCode() {
		return code;
	}
}
