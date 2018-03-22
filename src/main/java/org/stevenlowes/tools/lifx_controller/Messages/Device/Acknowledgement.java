package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class Acknowledgement extends Payload {
    private final int code = 45;

    public Acknowledgement() {
    }

    public int getCode() {
        return code;
    }
}
