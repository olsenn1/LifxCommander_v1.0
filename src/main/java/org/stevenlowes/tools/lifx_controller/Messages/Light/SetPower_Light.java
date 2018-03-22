package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class SetPower_Light extends Payload {
	int code = 117;
	int level;				// 16-Bits
	long duration;			// 32-Bits (Unsigned)
	
	public SetPower_Light() {
		level = 0;
		duration = 0;
	}
	
	public SetPower_Light(int level, long duration) {
		this.level = level;
		this.duration = duration;
	}
	
	public SetPower_Light(int level) {
		this.level = level;
		duration = 0;
	}
	
	public SetPower_Light(SetPower_Light setPower) {
		level = setPower.level;
		duration = setPower.duration;
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
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[6];
		
		byte[] levelBytes = new byte[2];
		String levelBinStr = String.format("%16s", Integer.toBinaryString(level)).replace(' ', '0');
		levelBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(levelBinStr);
		byteArray[0] = levelBytes[0];
		byteArray[1] = levelBytes[1];
		
		byte[] durationBytes = new byte[4];
		String durationBinStr = String.format("%32s", Long.toBinaryString(duration)).replace(' ', '0');
		durationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(durationBinStr);
		for(int i=2; i<6; i++) byteArray[i] = durationBytes[i - 2];
		
		return byteArray;
	}
	
}
