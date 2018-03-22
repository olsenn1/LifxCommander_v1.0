package org.stevenlowes.tools.lifx_controller.Messages.Light;


import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.HSBK;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;
import org.stevenlowes.tools.lifx_controller.Values.Waveforms;

public class SetWaveform extends Payload {
	int code = 103;
	int reserved;					// 8-Bits (Unsigned)
	boolean isTransient;			// 8-Bits 
	HSBK color;
	long period;					// 32-Bits (Unsigned)
	float cycles;					// 32-Bits
	int scew_ratio;					// 16-Bits
	int waveform;					// 8-Bits (Unsigned)
	
	public SetWaveform() {
		reserved = 0;				// Always = 0
		isTransient = false;
		color = new HSBK();
		period = 0;
		cycles = 0;
		scew_ratio = 0;
		waveform = Waveforms.SAWTOOTH;
	}
	
	public SetWaveform(int reserved, boolean isTransient, HSBK color, long period, float cycles, int scew_ratio, int waveform) {
		this.reserved = reserved;
		this.isTransient = isTransient;
		this.color = color;
		this.period = period;
		this.cycles = cycles;
		this.scew_ratio = scew_ratio;
		this.waveform = waveform;
	}
	
	public SetWaveform(boolean isTransient, HSBK color, long period, float cycles, int scew_ratio, int waveform) {
		reserved  = 0;
		this.isTransient = isTransient;
		this.color = color;
		this.period = period;
		this.cycles = cycles;
		this.scew_ratio = scew_ratio;
		this.waveform = waveform;
	}
	
	public SetWaveform(SetWaveform setWaveform) {
		reserved = setWaveform.reserved;
		isTransient = setWaveform.isTransient;
		color = setWaveform.color;
		period = setWaveform.period;
		cycles = setWaveform.cycles;
		scew_ratio = setWaveform.scew_ratio;
		waveform = setWaveform.waveform;
	}
	
	public int getCode() {
		return code;
	}
	
	public int getReserved() {
		return reserved;
	}
	
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	
	public boolean getIsTransient() {
		return isTransient;
	}
	
	public void setIsTransient(boolean isTransient) {
		this.isTransient = isTransient;
	}
	
	public HSBK getColor() {
		return color;
	}
	
	public void setColor(HSBK color) {
		this.color = color;
	}
	
	public long getPeriod() {
		return period;
	}
	
	public void setPeriod(long period) {
		this.period = period;
	}
	
	public float getCycles() {
		return cycles;
	}
	
	public void setCycles(float cycles) {
		this.cycles = cycles;
	}
	
	public int getScewRatio() {
		return scew_ratio;
	}
	
	public void setScewRatio(int scew_ratio) {
		this.scew_ratio = scew_ratio;
	}
	
	public int getWaveform() {
		return waveform;
	}
	
	public void setWaveform(int waveform) {
		this.waveform = waveform;
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[21];
		
		byte[] reservedByte = new byte[1];
		String reservedBinStr = String.format("%8s", Integer.toBinaryString(reserved)).replace(' ', '0');
		reservedByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(reservedBinStr);
		byteArray[0] = reservedByte[0];
		
		byte[] transientByte = new byte[1];
		String transientBinStr;
		if(isTransient == true) transientBinStr = "00000001";
		else transientBinStr = "00000000";
		transientByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(transientBinStr);
		byteArray[1] = transientByte[0];
		
		byte[] hueBytes = new byte[2];
		String hueBinStr = String.format("%16s", Integer.toBinaryString(color.getHue())).replace(' ', '0');
		hueBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(hueBinStr);
		byteArray[2] = hueBytes[0];
		byteArray[3] = hueBytes[1];
		
		byte[] saturationBytes = new byte[2];
		String saturationBinStr = String.format("%16s", Integer.toBinaryString(color.getSaturation())).replace(' ', '0');
		saturationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(saturationBinStr);
		byteArray[4] = saturationBytes[0];
		byteArray[5] = saturationBytes[1];
		
		byte[] brightnessBytes = new byte[2];
		String brightnessBinStr = String.format("%16s", Integer.toBinaryString(color.getBrightness())).replace(' ', '0');
		brightnessBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr);
		byteArray[6] = brightnessBytes[0];
		byteArray[7] = brightnessBytes[1];
		
		byte[] kelvinBytes = new byte[2];
		String kelvinBinStr = String.format("%16s", Integer.toBinaryString(color.getKelvin())).replace(' ', '0');
		kelvinBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(kelvinBinStr);
		byteArray[8] = kelvinBytes[0];
		byteArray[9] = kelvinBytes[1];
		
		byte[] periodBytes = new byte[4];
		String periodBinStr = String.format("%32s", Long.toBinaryString(period)).replace(' ', '0');
		periodBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(periodBinStr);
		for(int i=10; i<14; i++) byteArray[i] = periodBytes[i - 10];
		
		byte[] cyclesBytes = new byte[4];
		String cyclesBinStr = String.format("%32s", Integer.toBinaryString(Float.floatToRawIntBits(cycles))).replace(' ', '0');
		cyclesBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(cyclesBinStr);
		for(int i=14; i<18; i++) byteArray[i] = cyclesBytes[i - 14];
		
		byte[] scewRatioBytes = new byte[2];
		String scewRatioBinStr = String.format("%16s", Integer.toBinaryString(scew_ratio)).replace(' ', '0');
		scewRatioBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(scewRatioBinStr);
		byteArray[18] = scewRatioBytes[0];
		byteArray[19] = scewRatioBytes[1];
		
		byte[] waveformBytes = new byte[1];
		String waveformBinStr = String.format("%8s", Integer.toBinaryString(waveform)).replace(' ', '0');
		waveformBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(waveformBinStr);
		byteArray[20] = waveformBytes[0];
		
		return byteArray;
	}
	
}
