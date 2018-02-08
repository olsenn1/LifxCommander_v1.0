package Messages.Device;

import LifxCommander.CommonMethods;
import Messages.DataTypes.Payload;

public class StateService extends Payload {
	int code = 3;
	int service;		// 8-Bits (Unsigned)
	int port;			// 32-Bits (Unsigned)
	
	public StateService() {
		service = 0;
		port = 0;
	}
	
	public StateService(int service, int port) {
		this.service = service;
		this.port = port;
	}
	
	public StateService(StateService stateService) {
		service = stateService.service;
		port = stateService.port;
	}
	
	public int getCode() {
		return code;
	}
	
	public int getService() {
		return service;
	}
	
	public void setService(int service) {
		this.service = service;
	}
	
	public long getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		String serviceBinStr = CommonMethods.convertByteToBinaryString(byteArray[36]);
		service = Integer.parseInt(serviceBinStr, 2);
		
		String portBinStr = "";
		for(int i=40; i>36; i--) portBinStr = portBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
		port = Integer.parseInt(portBinStr, 2);
	}

}
