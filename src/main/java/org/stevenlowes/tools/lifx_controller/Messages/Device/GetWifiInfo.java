package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class GetWifiInfo extends Payload {
    private final int code = 16;

    public GetWifiInfo() {
    }

    public int getCode() {
        return code;
    }

}
