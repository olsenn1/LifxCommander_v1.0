package Messages.Device;

import Messages.DataTypes.Payload;

public class GetVersion extends Payload {
	int code = 32;
	
	public GetVersion() {}
	
	public int getCode() {
		return code;
	}
}
