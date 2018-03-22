package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;
import org.stevenlowes.tools.lifx_controller.Values.Power;

public class StatePower_Device extends Payload {
    private final int code = 22;
    private int level;                // 16-Bits (Unsigned)

    public StatePower_Device() {
        level = Power.OFF;
    }

    public StatePower_Device(int level) {
        this.level = level;
    }

    public StatePower_Device(StatePower_Device statePower) {
        level = statePower.level;
    }

    public int getCode() {
        return code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setFromCommandByteArray(byte[] byteArray) {
        String levelBinStr = CommonMethods.convertByteToBinaryString(byteArray[37]).concat(CommonMethods.convertByteToBinaryString(byteArray[36]));
        level = Integer.parseInt(levelBinStr, 2);
    }

}
