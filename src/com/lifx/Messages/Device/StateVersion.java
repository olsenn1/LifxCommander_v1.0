package com.lifx.Messages.Device;

import com.lifx.LifxCommander.CommonMethods;
import com.lifx.Messages.DataTypes.Payload;

public class StateVersion extends Payload {
	int code = 33;
	long vendor;			// 32-Bits (Unsigned)
	long product;			// 32-Bits (Unsigned)
	long version;			// 32-Bits (Unsigned)
	
	public StateVersion() {
		vendor = 0L;
		product = 0L;
		version = 0L;
	}
	
	public StateVersion(long vendor, long product, long version) {
		this.vendor = vendor;
		this.product = product;
		this.version = version;
	}
	
	public StateVersion(StateVersion stateVersion) {
		vendor = stateVersion.vendor;
		product = stateVersion.product;
		version = stateVersion.version;
	}
	
	public int getCode() {
		return code;
	}
	
	public long getVendor() {
		return vendor;
	}
	
	public void setVendor(long vendor) {
		this.vendor = vendor;
	}
	
	public long getProduct() {
		return product;
	}
	
	public void setProduct(long product) {
		this.product = product;
	}
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		String vendorBinStr = "";
		for(int i=39; i>35; i--) vendorBinStr = vendorBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		vendor = Long.parseLong(vendorBinStr, 2);
		
		String productBinStr = "";
		for(int i=43; i>39; i--) productBinStr = productBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		product = Long.parseLong(productBinStr, 2);
		
		String versionBinStr = "";
		for(int i=47; i>43; i--) versionBinStr = versionBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		version = Long.parseLong(versionBinStr, 2);
	}
	
}
