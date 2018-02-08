package Messages.Device;

import LifxCommander.CommonMethods;
import Messages.DataTypes.Payload;

public class StatePower_Device extends Payload {
	int code = 22;
	int level;				// 16-Bits (Unsigned)
	
	public StatePower_Device() {
		level = Values.Power.OFF;
	}
	
	public StatePower_Device(int level) {
		this.level = level;
	}
	
	public StatePower_Device(StatePower_Device statePower) {
		level = statePower.level;
	}
	
	public int getCode() {
		return code;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		String levelBinStr = CommonMethods.convertByteToBinaryString(byteArray[37]).concat(CommonMethods.convertByteToBinaryString(byteArray[36]));
		level = Integer.parseInt(levelBinStr, 2);
	}
	
}
