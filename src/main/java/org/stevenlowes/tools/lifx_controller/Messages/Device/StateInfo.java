package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

import java.math.BigInteger;

public class StateInfo extends Payload {
    private final int code = 35;
    private BigInteger time;                // 64-Bits (Unsigned)
    private BigInteger uptime;                // 64-Bits (Unsigned)
    private BigInteger downtime;            // 64-Bits (Unsigned)

    public StateInfo() {
        time = BigInteger.valueOf(0L);
        uptime = BigInteger.valueOf(0L);
        downtime = BigInteger.valueOf(0L);
    }

    public StateInfo(BigInteger time, BigInteger uptime, BigInteger downtime) {
        this.time = time;
        this.uptime = uptime;
        this.downtime = downtime;
    }

    public StateInfo(StateInfo stateInfo) {
        time = stateInfo.time;
        uptime = stateInfo.uptime;
        downtime = stateInfo.downtime;
    }

    public int getCode() {
        return code;
    }

    public BigInteger getTime() {
        return time;
    }

    public void setTime(BigInteger time) {
        this.time = time;
    }

    public BigInteger getUptime() {
        return uptime;
    }

    public void setUptime(BigInteger uptime) {
        this.uptime = uptime;
    }

    public BigInteger getDowntime() {
        return downtime;
    }

    public void setDowntime(BigInteger downtime) {
        this.downtime = downtime;
    }

    public void setFromCommandByteArray(byte[] byteArray) {
        byte[] timeBytes = new byte[8];
        for (int i = 43; i > 35; i--) {
            timeBytes[(-1 * i) + 43] = byteArray[i];
        }
        time = new BigInteger(timeBytes);

        byte[] uptimeBytes = new byte[8];
        for (int i = 51; i > 43; i--) {
            uptimeBytes[(-1 * i) + 51] = byteArray[i];
        }
        uptime = new BigInteger(uptimeBytes);

        byte[] downtimeBytes = new byte[8];
        for (int i = 59; i > 51; i--) {
            downtimeBytes[(-1 * i) + 59] = byteArray[i];
        }
        downtime = new BigInteger(downtimeBytes);

    }

}
