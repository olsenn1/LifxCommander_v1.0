package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetGroup extends Payload{
	int code = 51;
	
	public GetGroup() {}
	
	public int getCode() {
		return code;
	}

}
