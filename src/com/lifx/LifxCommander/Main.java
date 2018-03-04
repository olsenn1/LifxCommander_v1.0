/**
 * Lifx Commander
 * Author: Nicholas Olsen
 * Email: olsenn@gmail.com
 * Version: v1.0
 * Date: February 08, 2018
 */

package com.lifx.LifxCommander;

import java.io.IOException;

import com.lifx.Messages.DataTypes.Command;
import com.lifx.Messages.DataTypes.HSBK;
import com.lifx.Messages.Device.GetHostFirmware;
import com.lifx.Messages.Light.Get;
import com.lifx.Messages.Light.SetColor;
import com.lifx.Messages.Light.SetInfrared;
import com.lifx.Messages.Light.SetPower_Light;
import com.lifx.Messages.Light.SetWaveform;
import com.lifx.Values.Hue;
import com.lifx.Values.Levels;
import com.lifx.Values.Power;
import com.lifx.Values.Waveforms;

public class Main {

	public static void main(String[] args) throws IOException {
		int port = 56700;
		
		// Start Receiving Incoming Messages
		ReceiveMessages receiveMessages = new ReceiveMessages(port);
		receiveMessages.start();
		
		//Turn On All Lights
		SetPower_Light setPower = new SetPower_Light(Power.ON);
		Command powerOn = new Command(setPower);
		ControlMethods.sendBroadcastMessage(powerOn.getByteArray(), port);
		
		//Make Lights White
		HSBK hsbk1 = new HSBK();
		hsbk1.setSaturation(Levels.MIN);
		SetColor setColor1 = new SetColor(hsbk1);
		Command makeWhite = new Command(setColor1);
		ControlMethods.sendBroadcastMessage(makeWhite.getByteArray(), port);
		
		//Make Light Blue and 50% Brightness (Only Light w/ IP = 192.168.2.35)
		HSBK hsbk2 = new HSBK();
		hsbk2.setHue(Hue.BLUE);
		hsbk2.setBrightness(Levels.MAX / 2);
		SetColor setColor2 = new SetColor(hsbk2);
		Command makeBlue = new Command(setColor2);
		ControlMethods.sendUdpMessage(makeBlue.getByteArray(), "192.168.2.35", port);
		
		//Turn Off Infrared (All Lights)
		SetInfrared setInfrared = new SetInfrared(Levels.MIN);
		Command turnOffIr = new Command(setInfrared);
		ControlMethods.sendBroadcastMessage(turnOffIr.getByteArray(), port);
		
		// Transition Color
		HSBK newColor = new HSBK();
		newColor.setHue(Hue.RED);
		newColor.setSaturation(Levels.MAX);
		newColor.setBrightness(Levels.MAX);
		SetWaveform setWaveform = new SetWaveform();
		setWaveform.setColor(newColor);
		setWaveform.setCycles(2);
		setWaveform.setIsTransient(true);
		setWaveform.setPeriod(4000);
		setWaveform.setWaveform(Waveforms.SINUSOID);
		Command changeColor = new Command(setWaveform);
		ControlMethods.sendBroadcastMessage(changeColor.getByteArray(), port);

		// Print Firmware Version
		GetHostFirmware getHostFirmware = new GetHostFirmware();
		Command firmwareCommand = new Command(getHostFirmware);
		firmwareCommand.getFrameAddress().setResRequired(true);
		ControlMethods.sendBroadcastMessage(firmwareCommand.getByteArray(), port);
		
		// Print Current State
		Get get = new Get();
		Command getCommand = new Command(get);
		getCommand.getFrameAddress().setResRequired(true);
		ControlMethods.sendBroadcastMessage(getCommand.getByteArray(), port);
		
	}
}
