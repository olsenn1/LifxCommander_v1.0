package Messages.Device;

import Messages.DataTypes.Payload;

public class GetHostFirmware extends Payload{
	int code = 14;
	
	public GetHostFirmware() {}
	
	public int getCode() {
		return code;
	}
}
