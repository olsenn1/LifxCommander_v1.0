package LifxCommander;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CommonMethods {

	static public byte[] convertBinaryStringToLittleEndianByteArray(String binValueAsString) {
		if((binValueAsString.length() % 8) == 0) {
			int arrayLength = binValueAsString.length() / 8;
			byte[] byteArray = new byte[arrayLength];
			long binaryToLong = Long.parseLong(binValueAsString, 2);
			ByteBuffer byteBuffer = ByteBuffer.allocate(8);
			byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
			byteBuffer.putLong(binaryToLong);
			
			for(int i=0; i<arrayLength; i++){
				byteArray[i] = byteBuffer.array()[i];	
			}
			
			return byteArray;
		}
		else {
			System.out.println("Error: Binary number does not fit into an even number of bytes");
			return null;
		}
	}
	
	static public String convertByteToBinaryString(byte b) {
		StringBuilder stringBuilder = new StringBuilder("00000000");
		for(int bit=0; bit<8; bit++) {
			if(((b >> bit) & 1)>0) {
				stringBuilder.setCharAt(7-bit, '1');
			}
		}
		return stringBuilder.toString();
	}
	
	static public byte[] convertHexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	static public long convertHexStringToLong(String s) {
		return Long.parseLong(s, 16);
	}
	
	static public String getHexValueAsString(byte[] byteArray) {
		StringBuilder stringBuilder = new StringBuilder();
	    for (byte b : byteArray) {
	        stringBuilder.append(String.format("%02X ", b));
	    }
		return stringBuilder.toString();
	}
	
	static public String getDateAsString(BigInteger nanoseconds) {
		BigInteger milliseconds = nanoseconds.divide(BigInteger.valueOf(1000000L));
		long duration = milliseconds.longValue();
		Date date = new Date(duration);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy @h:mma", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formattedDate = sdf.format(date);
		
		return formattedDate;
	}
	
	
}
