package Messages.Device;

import Messages.DataTypes.Payload;

public class Acknowledgement extends Payload{
	int code = 45;
	
	public Acknowledgement() {}
	
	public int getCode() {
		return code;
	}
}
