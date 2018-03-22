package org.stevenlowes.tools.lifx_controller.Messages.Device;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Random;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class SetGroup extends Payload{
	int code = 52;
	byte[] group;				// 16-Bytes
	String label;				// 32-Bytes
	BigInteger updated_at;		// 64-Bits (Unsigned)
	
	public SetGroup() {
		group = new byte[16];
		new Random().nextBytes(group);
		
		label = "N/A";
		
		BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
		updated_at = millis.multiply(BigInteger.valueOf(1000000L));
	}
	
	public SetGroup(String label) {
		group = new byte[16];
		new Random().nextBytes(group);
		
		this.label = label;
		
		BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
		updated_at = millis.multiply(BigInteger.valueOf(1000000L));
	}
	
	public SetGroup(SetGroup setGroup) {
		group = setGroup.group;
		
		label = setGroup.label;
		
		BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
		updated_at = millis.multiply(BigInteger.valueOf(1000000L));
	}
	
	public int getCode() {
		return code;
	}
	
	public byte[] getGroup() {
		return group;
	}
	
	public void setGroup(byte[] group) {
		this.group = group;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public BigInteger getUpdatedAt() {
		return updated_at;
	}
	
	public void setUpdatedAt(BigInteger updated_at) {
		this.updated_at = updated_at;
	}
	
	public void setUpdatedAtToNow() {
		BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
		updated_at = millis.multiply(BigInteger.valueOf(1000000L));
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[56];
		
		
		for(int i=0; i<16; i++) byteArray[i] = group[15 - i];
		
		byte[] labelBytes = new byte[32];
		labelBytes = label.getBytes();
		for(int i=16; i<48; i++) byteArray[i] = labelBytes[47 - i];
		
		byte[] updatedAtBytes = new byte[8];
		String updatedAtBinStr = String.format("%64s", Long.toBinaryString(updated_at.longValue())).replace(' ', '0');
		updatedAtBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(updatedAtBinStr);
		for(int i=48; i<56; i++) byteArray[i] = updatedAtBytes[i - 48];
		
		return byteArray;
	}

}
