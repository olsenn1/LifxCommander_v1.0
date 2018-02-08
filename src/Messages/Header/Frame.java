package Messages.Header;

import LifxCommander.CommonMethods;

public class Frame {
	int size;				// 16-Bits
	int origin;				// 2-Bits
	boolean tagged;			// 1-Bit
	boolean addressable;	// 1-Bit
	int protocol;			// 12-Bits
	long source;			// 32-Bits
	
	public Frame(){
		size = 0;
		origin = 0;				// Always = 0
		tagged = false;
		addressable = true;		// Always = true
		protocol = 1024;		// Always = 1024
		source = 0;
	}
	
	public Frame(int size, int origin, boolean tagged, boolean addressable, int protocol, long source){
		this.size = size;
		this.origin = origin;
		this.tagged = tagged;
		this.addressable = addressable;
		this.protocol = protocol;
		this.source = source;
	}
	
	public Frame(int size, boolean tagged, long source) {
		this.size = size;
		origin = 0;
		this.tagged = tagged;
		addressable = true;
		protocol = 1024;
		this.source = source;
	}
	
	public Frame(Frame frame){
		size = frame.size;
		origin = frame.origin;
		tagged = frame.tagged;
		addressable = frame.addressable;
		protocol = frame.protocol;
		source = frame.source;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getOrigin() {
		return origin;
	}
	
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	public boolean getTagged() {
		return tagged;
	}
	
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}
	
	public boolean getAddressable() {
		return addressable;
	}
	
	public void setAddressable(boolean addressable) {
		this.addressable = addressable;
	}
	
	public int getProtocol() {
		return protocol;
	}
	
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	
	public long getSource() {
		return source;
	}
	
	public void setSource(long source) {
		this.source = source;
	}
	
	public byte[] getByteArray() {
		
		byte[] byteArray = new byte[8];
		
		byte[] sizeBytes = new byte[2];
		String sizeBinStr = Integer.toBinaryString(0x10000 | size).substring(1);
		sizeBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(sizeBinStr);
		byteArray[0] = sizeBytes[0];
		byteArray[1] = sizeBytes[1];
		
		byte[] dataBytes = new byte[2];
		String originBinStr = Integer.toBinaryString(0x04 | origin).substring(1);
		String taggedBinStr; 
		if(tagged == true)taggedBinStr = "1"; else taggedBinStr = "0";
		String addressableBinStr;
		if(addressable == true)addressableBinStr = "1"; else addressableBinStr = "0";
		String protocolBinStr = Integer.toBinaryString(0x1000 | protocol).substring(1);
		String dataBinStr = originBinStr.concat(taggedBinStr).concat(addressableBinStr).concat(protocolBinStr);
		dataBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(dataBinStr);
		byteArray[2] = dataBytes[0];
		byteArray[3] = dataBytes[1];
		
		byte[] sourceBytes = new byte[4];
		String sourceBinStr = Long.toBinaryString(0x100000000L | source).substring(1);
		sourceBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(sourceBinStr);
		byteArray[4] = sourceBytes[0];
		byteArray[5] = sourceBytes[1];
		byteArray[6] = sourceBytes[2];
		byteArray[7] = sourceBytes[3];
		
		return byteArray;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		String sizeBinStr = CommonMethods.convertByteToBinaryString(byteArray[1]).concat(CommonMethods.convertByteToBinaryString(byteArray[0]));
		size = Integer.parseInt(sizeBinStr,2);
		
		String dataBinStr = CommonMethods.convertByteToBinaryString(byteArray[3]).concat(CommonMethods.convertByteToBinaryString(byteArray[2]));
		String originBinStr = dataBinStr.substring(0, 2);
		origin = Integer.parseInt(originBinStr, 2);
		
		if(dataBinStr.charAt(2) == '1') tagged = true; else tagged = false;
		if(dataBinStr.charAt(3) == '1') addressable = true; else addressable = false;
		String protocolBinStr = dataBinStr.substring(4, 16);
		protocol = Integer.parseInt(protocolBinStr, 2);
		
		String sourceBinStr = "";
		for(int i=7; i>3; i--) {
			sourceBinStr = sourceBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		}
		source = Long.parseLong(sourceBinStr, 2);
		
	}
	
}
