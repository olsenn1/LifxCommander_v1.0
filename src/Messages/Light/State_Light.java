package Messages.Light;

import java.math.BigInteger;

import LifxCommander.CommonMethods;
import Messages.DataTypes.HSBK;
import Messages.DataTypes.Payload;

public class State_Light extends Payload{
	int code = 107;
	HSBK color;
	int reserved1;			// 16-Bits
	int power;				// 16-Bits (Unsigned)
	String label;			// 32-Bytes
	BigInteger reserved2;	// 64-Bits (Unsigned)
	
	public State_Light() {
		color = new HSBK();
		reserved1 = 0;							// Always = 0
		power = Values.Power.OFF;
		label = new String();
		reserved2 = BigInteger.valueOf(0L);		// Always = 0
	}
	
	public State_Light(HSBK color, int reserved1, int power, String label, BigInteger reserved2) {
		this.color = color;
		this.reserved1 = reserved1;
		this.power = power;
		this.label = label;
		this.reserved2 = reserved2;
	}
	
	public State_Light(HSBK color, int power, String label) {
		this.color = color;
		reserved1 = 0;
		this.power = power;
		this.label = label;
		reserved2 = BigInteger.valueOf(0L);
	}
	
	public State_Light(State_Light state) {
		color = state.color;
		reserved1 = state.reserved1;
		power = state.power;
		label = state.label;
		reserved2 = state.reserved2;
	}
	
	public int getCode() {
		return code;
	}
	
	public HSBK getColor() {
		return color;
	}
	
	public void setColor(HSBK color) {
		this.color = color;
	}
	
	public int getReserved1() {
		return reserved1;
	}
	
	public void setReserved1(int reserved1) {
		this.reserved1 = reserved1;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public BigInteger getReserved2() {
		return reserved2;
	}
	
	public void setReserved2(BigInteger reserved2) {
		this.reserved2 = reserved2;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		String hueBinStr = "";
		for(int i=37; i>35; i--) hueBinStr = hueBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		color.setHue(Integer.parseInt(hueBinStr, 2));
		String saturationBinStr = "";
		for(int i=39; i>37; i--) saturationBinStr = saturationBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		color.setSaturation(Integer.parseInt(saturationBinStr, 2));
		String brightnessBinStr = "";
		for(int i=41; i>39; i--) brightnessBinStr = brightnessBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		color.setBrightness(Integer.parseInt(brightnessBinStr, 2));
		String kelvinBinStr = "";
		for(int i=43; i>41; i--) kelvinBinStr = kelvinBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		color.setKelvin(Integer.parseInt(kelvinBinStr, 2));
		
		String reserved1BinStr = "";
		for(int i=45; i>43; i--) reserved1BinStr = reserved1BinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		reserved1 = Integer.parseInt(reserved1BinStr, 2);
		
		String powerBinStr = "";
		for(int i=47; i>45; i--) powerBinStr = powerBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		power = Integer.parseInt(powerBinStr, 2);
		
		byte[] labelBytes = new byte[32];
		for(int i=48; i<80; i++) labelBytes[i-48] = byteArray[i];
		label = new String(labelBytes);
		
		byte[] reserved2Bytes = new byte[8];
		for(int i=87; i>79; i--) reserved2Bytes[(-1*i)+87] = byteArray[i];
		reserved2 = new BigInteger(reserved2Bytes);
	}
	
}
