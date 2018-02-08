package Messages.Light;

import Messages.DataTypes.Payload;

public class Get extends Payload{
	int code = 101;
	
	public Get() {}
	
	public int getCode() {
		return code;
	}
}
