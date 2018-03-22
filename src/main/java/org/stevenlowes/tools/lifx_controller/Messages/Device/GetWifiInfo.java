package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetWifiInfo extends Payload {
	int code = 16;
	
	public GetWifiInfo() {}
	
	public int getCode() {
		return code;
	}

}
