package com.lifx.Messages.Device;

import com.lifx.Messages.DataTypes.Payload;

public class EchoResponse extends Payload {
	int code = 59;
	byte[] payload;			// 64-Bytes
	
	public EchoResponse() {
		payload = new byte[64];
	}
	
	public EchoResponse(byte[] payload) {
		this.payload = payload;
	}
	
	public EchoResponse(EchoResponse echoResponse) {
		payload = echoResponse.payload;
	}
	
	public int getCode() {
		return code;
	}
	
	public byte[] getPayload() {
		return payload;
	}
	
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		for(int i=99; i>35; i--) payload[(-1*i)+99] = byteArray[i];
	}

}
