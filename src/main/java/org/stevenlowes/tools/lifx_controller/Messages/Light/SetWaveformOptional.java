package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.HSBK;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;
import org.stevenlowes.tools.lifx_controller.Values.Waveforms;

public class SetWaveformOptional extends Payload {
	int code = 119;
	int reserved;					// 8-Bits (Unsigned)
	boolean isTransient;			// 8-Bits 
	HSBK color;
	long period;					// 32-Bits (Unsigned)
	float cycles;					// 32-Bits
	int scew_ratio;					// 16-Bits
	int waveform;					// 8-Bits (Unsigned)
	boolean set_hue;				// 8-Bits
	boolean set_saturation;			// 8-Bits
	boolean set_brightness;			// 8-Bits
	boolean set_kelvin;				// 8-Bits
	
	public SetWaveformOptional() {
		reserved = 0;				// Always = 0
		isTransient = false;
		color = new HSBK();
		period = 0;
		cycles = 0;
		scew_ratio = 0;
		waveform = Waveforms.SAWTOOTH;
		set_hue = true;
		set_saturation = true;
		set_brightness = true;
		set_kelvin = true;
	}
	
	public SetWaveformOptional(int reserved, boolean isTransient, HSBK color, long period, float cycles, int scew_ratio, int waveform, boolean set_hue, boolean set_saturation, boolean set_brightness, boolean set_kelvin) {
		this.reserved = reserved;
		this.isTransient = isTransient;
		this.color = color;
		this.period = period;
		this.cycles = cycles;
		this.scew_ratio = scew_ratio;
		this.waveform = waveform;
		this.set_hue = set_hue;
		this.set_saturation = set_saturation;
		this.set_brightness = set_brightness;
		this.set_kelvin = set_kelvin;
	}
	
	public SetWaveformOptional(boolean isTransient, HSBK color, long period, float cycles, int scew_ratio, int waveform, boolean set_hue, boolean set_saturation, boolean set_brightness, boolean set_kelvin) {
		reserved  = 0;
		this.isTransient = isTransient;
		this.color = color;
		this.period = period;
		this.cycles = cycles;
		this.scew_ratio = scew_ratio;
		this.waveform = waveform;
		this.set_hue = set_hue;
		this.set_saturation = set_saturation;
		this.set_brightness = set_brightness;
		this.set_kelvin = set_kelvin;
	}
	
	public SetWaveformOptional(SetWaveformOptional setWaveformOptional) {
		reserved = setWaveformOptional.reserved;
		isTransient = setWaveformOptional.isTransient;
		color = setWaveformOptional.color;
		period = setWaveformOptional.period;
		cycles = setWaveformOptional.cycles;
		scew_ratio = setWaveformOptional.scew_ratio;
		waveform = setWaveformOptional.waveform;
		set_hue = setWaveformOptional.set_hue;
		set_saturation = setWaveformOptional.set_saturation;
		set_brightness = setWaveformOptional.set_brightness;
		set_kelvin = setWaveformOptional.set_kelvin;
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
	
	public boolean getSetHue() {
		return set_hue;
	}
	
	public void setSetHue(boolean set_hue) {
		this.set_hue = set_hue;
	}
	
	public boolean getSetSaturation() {
		return set_saturation;
	}
	
	public void setSetSaturation(boolean set_saturation) {
		this.set_saturation = set_saturation;
	}
	
	public boolean getSetBrightness() {
		return set_brightness;
	}
	
	public void setSetBrightness(boolean set_brightness) {
		this.set_brightness = set_brightness;
	}
	
	public boolean getSetKelvin() {
		return set_kelvin;
	}
	
	public void setSetKelvin(boolean set_kelvin) {
		this.set_kelvin = set_kelvin;
	}
	
	public byte[] getByteArray() {
		byte[] byteArray = new byte[25];
		
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
		
		byte[] setHueByte = new byte[1];
		String setHueBinStr;
		if(set_hue == true) setHueBinStr = "00000001"; else setHueBinStr = "00000000";
		setHueByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(setHueBinStr);
		byteArray[21] = setHueByte[0];
		
		byte[] setSaturationByte = new byte[1];
		String setSaturationBinStr;
		if(set_saturation == true) setSaturationBinStr = "00000001"; else setSaturationBinStr = "00000000";
		setSaturationByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(setSaturationBinStr);
		byteArray[22] = setSaturationByte[0];
		
		byte[] setBrightnessByte = new byte[1];
		String setBrightnessBinStr;
		if(set_brightness == true) setBrightnessBinStr = "00000001"; else setBrightnessBinStr = "00000000";
		setBrightnessByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(setBrightnessBinStr);
		byteArray[23] = setBrightnessByte[0];
		
		byte[] setKelvinByte = new byte[1];
		String setKelvinBinStr;
		if(set_kelvin == true) setKelvinBinStr = "00000001"; else setKelvinBinStr = "00000000";
		setKelvinByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(setKelvinBinStr);
		byteArray[24] = setKelvinByte[0];
		
		return byteArray;
	}
	
}
