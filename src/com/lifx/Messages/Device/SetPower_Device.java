package com.lifx.Messages.Device;

import com.lifx.LifxCommander.CommonMethods;
import com.lifx.Messages.DataTypes.Payload;

public class SetPower_Device extends Payload {
	int code = 21;
	int level;				// 16-Bits (Unsigned)
	
	public SetPower_Device() {
		level = 0;
	}
	
	public SetPower_Device(int level) {
		this.level = level;
	}
	
	public SetPower_Device(SetPower_Device setPower) {
		level = setPower.level;
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
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[2];
		
		String levelBinStr = String.format("%16s", Integer.toBinaryString(level)).replace(' ', '0');
		byteArray = CommonMethods.convertBinaryStringToLittleEndianByteArray(levelBinStr);
		
		return byteArray;
	}
	
}
