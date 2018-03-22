package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class StateWifiInfo extends Payload {
	int code = 17;
	float signal;			// 32-Bits
	long tx;				// 32-Bits (Unsigned)
	long rx;				// 32-Bits (Unsigned)
	int reserved;			// 16-Bits (Unsigned)
	
	public StateWifiInfo() {
		signal = 0F;
		tx = 0L;
		rx = 0L;
		reserved = 0;		// Always = 0
	}
	
	public StateWifiInfo(float signal, long tx, long rx, int reserved) {
		this.signal = signal;
		this.tx = tx;
		this.rx = rx;
		this.reserved = reserved;
	}
	
	public StateWifiInfo(float signal, long tx, long rx) {
		this.signal = signal;
		this.tx = tx;
		this.rx = rx;
		reserved = 0;
	}
	
	public StateWifiInfo(StateWifiInfo stateWifiInfo) {
		signal = stateWifiInfo.signal;
		tx = stateWifiInfo.tx;
		rx = stateWifiInfo.rx;
		reserved = stateWifiInfo.reserved;
	}
	
	public int getCode() {
		return code;
	}
	
	public float getSignal() {
		return signal;
	}
	
	public void setSignal(float signal) {
		this.signal = signal;
	}
	
	public long getTx() {
		return tx;
	}
	
	public void setTx(long tx) {
		this.tx = tx;
	}
	
	public long getRx() {
		return rx;
	}
	
	public void setRx(long rx) {
		this.rx = rx;
	}
	
	public int getReserved() {
		return reserved;
	}
	
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		String signalBinStr = "";
		for(int i=39; i>35; i--) signalBinStr = signalBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		int signalInt = Integer.parseInt(signalBinStr, 2);
		signal = Float.intBitsToFloat(signalInt);
		
		String txBinStr = "";
		for(int i=43; i>39; i--) txBinStr = txBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		tx = Long.parseLong(txBinStr, 2);
		
		String rxBinStr = "";
		for(int i=47; i>43; i--) rxBinStr = rxBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		rx = Long.parseLong(rxBinStr, 2);
		
		String reservedBinStr = "";
		for(int i=49; i>47; i--) reservedBinStr = reservedBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		reserved = Integer.parseInt(reservedBinStr, 2);
	}
	
	
}
