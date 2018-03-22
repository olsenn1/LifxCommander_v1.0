package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Random;

public class StateLocation extends Payload {
    private final int code = 50;
    private byte[] location;            // 16-Bytes
    private String label;                // 32-Bytes
    private BigInteger updated_at;        // 64-Bits (Unsigned)

    public StateLocation() {
        location = new byte[16];
        new Random().nextBytes(location);

        label = "N/A";

        //BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
        //updated_at = millis.multiply(BigInteger.valueOf(1000000L));
        updated_at = BigInteger.valueOf(0L);
    }

    public StateLocation(String label) {
        location = new byte[16];
        new Random().nextBytes(location);

        this.label = label;

        //BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
        //updated_at = millis.multiply(BigInteger.valueOf(1000000L));
        updated_at = BigInteger.valueOf(0L);
    }

    public StateLocation(SetLocation setLocation) {
        location = setLocation.location;

        label = setLocation.label;

        BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
        updated_at = millis.multiply(BigInteger.valueOf(1000000L));
    }

    public int getCode() {
        return code;
    }

    public byte[] getLocation() {
        return location;
    }

    public void setLocation(byte[] location) {
        this.location = location;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigInteger getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(BigInteger updated_at) {
        this.updated_at = updated_at;
    }

    public void setUpdatedAtToNow() {
        BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
        updated_at = millis.multiply(BigInteger.valueOf(1000000L));
    }

    public void setFromCommandByteArray(byte[] byteArray) {
        for (int i = 51; i > 35; i--) {
            location[(-1 * i) + 51] = byteArray[i];
        }

        byte[] labelBytes = new byte[32];
        System.arraycopy(byteArray, 52, labelBytes, 0, 32);
        label = new String(labelBytes);

        //String updatedAtBinStr = "";
        //for(int i=91; i>83; i--) updatedAtBinStr = updatedAtBinStr.concat(CommonMethods.convertByteToBinaryString(byteArray[i]));
        //updated_at = BigInteger.valueOf(Long.parseLong(updatedAtBinStr, 2));
        byte[] updatedAtBytes = new byte[8];
        for (int i = 91; i > 83; i--) {
            updatedAtBytes[(-1 * i) + 91] = byteArray[i];
        }
        updated_at = new BigInteger(updatedAtBytes);
    }

}
