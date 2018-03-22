package org.stevenlowes.tools.lifx_controller.Messages.DataTypes;

import org.stevenlowes.tools.lifx_controller.Values.Kelvin;
import org.stevenlowes.tools.lifx_controller.Values.Levels;

public class HSBK {
	int hue;			// 16-Bits
	int saturation;		// 16-Bits
	int brightness;		// 16-Bits
	int kelvin;			// 16-Bits
	
	public HSBK() {
		hue = 0;
		saturation = Levels.MAX;
		brightness = Levels.MAX;
		kelvin = Kelvin.MEDIUM;
	}
	
	public HSBK(int hue, int saturation, int brightness, int kelvin) {
		this.hue = hue;
		this.saturation = saturation;
		this.brightness = brightness;
		this.kelvin = kelvin;
	}
	
	public HSBK(HSBK hsbk) {
		hue = hsbk.hue;
		saturation = hsbk.saturation;
		brightness = hsbk.brightness;
		kelvin = hsbk.kelvin;
	}
	
	public int getHue() {
		return hue;
	}
	
	public void setHue(int hue) {
		this.hue = hue;
	}
	
	public int getSaturation() {
		return saturation;
	}
	
	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}
	
	public int getBrightness() {
		return brightness;
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	public int getKelvin() {
		return kelvin;
	}
	
	public void setKelvin(int kelvin) {
		this.kelvin = kelvin;
	}
	
}
