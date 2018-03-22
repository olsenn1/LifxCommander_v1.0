package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class StateInfrared extends Payload {
    private final int code = 121;
    private int brightness;            // 16-Bits (Unsigned)

    public StateInfrared() {
        brightness = 0;
    }

    public StateInfrared(int brightness) {
        this.brightness = brightness;
    }

    public StateInfrared(StateInfrared stateInfrared) {
        brightness = stateInfrared.brightness;
    }

    public int getCode() {
        return code;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void setFromCommandByteArray(byte[] byteArray) {
        String brightnessBinStr = CommonMethods.convertByteToBinaryString(byteArray[36]);
        brightness = Integer.parseInt(brightnessBinStr, 2);
    }
}
