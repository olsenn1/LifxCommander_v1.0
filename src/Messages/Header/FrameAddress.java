package Messages.Header;

import LifxCommander.CommonMethods;

public class FrameAddress {
	long target;				// 64-Bits
	long reserved1;				// 48-Bits
	int reserved2;				// 6-Bits
	boolean ack_required;		// 1-Bit
	boolean res_required;		// 1-Bit
	int sequence;				// 8-Bits
	
	public FrameAddress() {
		target = 0;
		reserved1 = 0;				// Always = 0
		reserved2 = 0;				// Always = 0
		ack_required = false;
		res_required = false;
		sequence = 0;
	}
	
	public FrameAddress(long target, long reserved1, int reserved2, boolean ack_required, boolean res_required, int sequence) {
		this.target = target;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.ack_required = ack_required;
		this.res_required = res_required;
		this.sequence = sequence;
	}
	
	public FrameAddress(long target, boolean ack_required, boolean res_required, int sequence) {
		this.target = target;
		this.reserved1 = 0;
		this.reserved2 = 0;
		this.ack_required = ack_required;
		this.res_required = res_required;
		this.sequence = sequence;
	}
	
	public FrameAddress(FrameAddress frameAddress) {
		target = frameAddress.target;
		reserved1 = frameAddress.reserved1;
		reserved2 = frameAddress.reserved2;
		ack_required = frameAddress.ack_required;
		res_required = frameAddress.res_required;
		sequence = frameAddress.sequence;
	}
	
	public long getTarget() {
		return target;
	}
	
	public void setTarget(long target) {
		this.target = target;
	}
	
	public void setTargetByMacAddress(String macAddress) {
		target = CommonMethods.convertHexStringToLong(macAddress);
	}
	
	public long getReserved1() {
		return reserved1;
	}
	
	public void setReserved1(long reserved1) {
		this.reserved1 = reserved1;
	}
	
	public int getReserved2() {
		return reserved2;
	}
	
	public void setReserved2(int reserved2) {
		this.reserved2 = reserved2;
	}
	
	public boolean getAckRequired() {
		return ack_required;
	}
	
	public void setAckRequired(boolean ack_required) {
		this.ack_required = ack_required;
	}
	
	public boolean getResRequired() {
		return res_required;
	}
	
	public void setResRequired(boolean res_required) {
		this.res_required = res_required;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public byte[] getByteArray() {
		
		byte[] byteArray = new byte[16];
		
		byte[] targetBytes = new byte[8];
		String targetBinStr = String.format("%64s", Long.toBinaryString(target)).replace(' ', '0');
		targetBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(targetBinStr);
		for(int i=0; i<8; i++) {
			byteArray[i] = targetBytes[i];
		}
		
		byte[] reserved1Bytes = new byte[6];
		String reserved1BinStr = String.format("%48s", Long.toBinaryString(reserved1)).replace(' ', '0');
		reserved1Bytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(reserved1BinStr);
		for(int i=8; i<14; i++) {
			byteArray[i] = reserved1Bytes[i-8];
		}
		
		byte[] dataByte = new byte[1];
		String reserved2BinStr = String.format("%6s", Integer.toBinaryString(reserved2)).replace(' ', '0');
		String ackRequiredBinStr;
		if(ack_required == true) ackRequiredBinStr = "1"; else ackRequiredBinStr = "0";
		String resRequiredBinStr;
		if(res_required == true) resRequiredBinStr = "1"; else resRequiredBinStr = "0";
		String dataBinStr = reserved2BinStr.concat(ackRequiredBinStr).concat(resRequiredBinStr);
		dataByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(dataBinStr);
		byteArray[14] = dataByte[0];
		
		byte[] sequenceByte = new byte[1];
		String sequenceBinStr = String.format("%8s", Integer.toBinaryString(sequence)).replace(' ', '0');
		sequenceByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(sequenceBinStr);
		byteArray[15] = sequenceByte[0];
		
		return byteArray;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		
		String targetBinStr = "";
		for(int i=15; i>7; i--) targetBinStr = targetBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		target = Long.parseLong(targetBinStr, 2);
		
		String reserved1BinStr = "";
		for(int i=21; i>15; i--) reserved1BinStr = reserved1BinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		reserved1 = Long.parseLong(reserved1BinStr, 2);
		
		String dataBinStr = CommonMethods.convertByteToBinaryString(byteArray[22]);
		String reserved2BinStr = dataBinStr.substring(0, 6);
		reserved2 = Integer.parseInt(reserved2BinStr, 2);
		if(dataBinStr.charAt(6) == '1') ack_required = true; else ack_required = false;
		if(dataBinStr.charAt(7) == '1') res_required = true; else res_required = false;
		
		String sequenceBinStr = CommonMethods.convertByteToBinaryString(byteArray[23]);
		sequence = Integer.parseInt(sequenceBinStr, 2);
		
	}
	
}
