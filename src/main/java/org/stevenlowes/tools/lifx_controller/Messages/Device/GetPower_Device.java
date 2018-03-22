package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetPower_Device extends Payload {
    private final int code = 20;

    public GetPower_Device() {
    }

    public int getCode() {
        return code;
    }
}
