package org.stevenlowes.tools.lifx_controller.Messages.Device;

import java.math.BigInteger;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class StateWifiFirmware extends Payload{
	private final int code = 19;
	private BigInteger build;				// 64-Bits (Unsigned)
	private BigInteger reserved;			// 64-Bits (Unsigned)
	private long version;					// 32-Bits
	
	public StateWifiFirmware() {
		build = BigInteger.valueOf(0L);
		reserved = BigInteger.valueOf(0L);		// Always = 0
		version = 0L;
	}
	
	public StateWifiFirmware(BigInteger build, BigInteger reserved, long version) {
		this.build = build;
		this.reserved = reserved;
		this.version = version;
	}
	
	public StateWifiFirmware(BigInteger build, long version) {
		this.build = build;
		reserved = BigInteger.valueOf(0L);
		this.version = version;
	}
	
	public int getCode() {
		return code;
	}
	
	public BigInteger getBuild() {
		return build;
	}
	
	public void setBuild(BigInteger build) {
		this.build = build;
	}
	
	public BigInteger getRreserved() {
		return reserved;
	}
	
	public void setReserved(BigInteger reserved) {
		this.reserved = reserved;
	}
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		//String buildBinStr = "";
		//for(int i=43; i>35; i--) buildBinStr = buildBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		//build = BigInteger.valueOf(Long.parseLong(buildBinStr, 2));
		byte[] buildBytes = new byte[8];
		for(int i=43; i>35; i--) buildBytes[(-1*i)+43] = byteArray[i];
		build = new BigInteger(buildBytes);
		
		//String reservedBinStr = "";
		//for(int i=51; i>43; i--) reservedBinStr = reservedBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		//reserved = BigInteger.valueOf(Long.parseLong(reservedBinStr, 2));
		byte[] reservedBytes = new byte[8];
		for(int i=51; i>43; i--) reservedBytes[(-1*i)+51] = byteArray[i];
		reserved = new BigInteger(reservedBytes);
		
		String versionBinStr = "";
		for(int i=55; i>51; i--) versionBinStr = versionBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		version = Long.parseLong(versionBinStr, 2);
	}

}
