package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetHostFirmware extends Payload {
    private final int code = 14;

    public GetHostFirmware() {
    }

    public int getCode() {
        return code;
    }
}
