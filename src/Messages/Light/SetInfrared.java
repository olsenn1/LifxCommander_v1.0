package Messages.Light;

import LifxCommander.CommonMethods;
import Messages.DataTypes.Payload;

public class SetInfrared extends Payload {
	int code = 122;
	int brightness;		// 16-Bits (Unsigned)
	
	public SetInfrared() {
		brightness = 0;
	}
	
	public SetInfrared(int brightness) {
		this.brightness = brightness;
	}
	
	public SetInfrared(SetInfrared setInfrared) {
		brightness = setInfrared.brightness;
	}
	
	public int getCode() {
		return code;
	}
	
	public int getBrightness() {
		return brightness;
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[2];
		
		String brightnessBinStr = String.format("%16s", Integer.toBinaryString(brightness)).replace(' ', '0');
		byteArray = CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr);
		
		return byteArray;
	}
	
}
