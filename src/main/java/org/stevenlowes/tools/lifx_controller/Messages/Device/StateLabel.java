package org.stevenlowes.tools.lifx_controller.Messages.Device;

import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class StateLabel extends Payload {
    private final int code = 25;
    private String label;            // 32-Bytes

    public StateLabel() {
        label = "";
    }

    public StateLabel(String label) {
        this.label = label;
    }

    public StateLabel(StateLabel stateLabel) {
        label = stateLabel.label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFromCommandByteArray(byte[] byteArray) {
        byte[] labelBytes = new byte[32];
        System.arraycopy(byteArray, 36, labelBytes, 0, 32);
        label = new String(labelBytes);
    }
}
