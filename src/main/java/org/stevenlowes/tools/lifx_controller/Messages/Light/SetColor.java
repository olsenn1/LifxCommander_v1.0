package org.stevenlowes.tools.lifx_controller.Messages.Light;

import org.stevenlowes.tools.lifx_controller.LifxCommander.CommonMethods;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.HSBK;
import org.stevenlowes.tools.lifx_controller.Messages.DataTypes.Payload;

public class SetColor extends Payload {
    private final int code = 102;
    private int reserved;            // 8-Bits
    private HSBK hsbk;
    private long duration;            // 32-Bits (Unsigned)

    public SetColor() {
        reserved = 0;
        hsbk = new HSBK();
        duration = 0;
    }

    public SetColor(int reserved, HSBK hsbk, long duration) {
        this.reserved = reserved;
        this.hsbk = hsbk;
        this.duration = duration;
    }

    public SetColor(HSBK hsbk, long duration) {
        reserved = 0;
        this.hsbk = hsbk;
        this.duration = duration;
    }

    public SetColor(HSBK hsbk) {
        reserved = 0;
        this.hsbk = hsbk;
        duration = 0;
    }

    public int getCode() {
        return code;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public HSBK getHsbk() {
        return hsbk;
    }

    public void setHsbk(HSBK hsbk) {
        this.hsbk = hsbk;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public byte[] getByteArray() {
        byte[] byteArray = new byte[13];

        byte[] reservedByte = new byte[1];
        String reservedBinStr = String.format("%8s", Integer.toBinaryString(reserved)).replace(' ', '0');
        reservedByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(reservedBinStr);
        byteArray[0] = reservedByte[0];

        byte[] hueBytes = new byte[2];
        String hueBinStr = String.format("%16s", Integer.toBinaryString(hsbk.getHue())).replace(' ', '0');
        hueBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(hueBinStr);
        byteArray[1] = hueBytes[0];
        byteArray[2] = hueBytes[1];

        byte[] saturationBytes = new byte[2];
        String saturationBinStr = String.format("%16s", Integer.toBinaryString(hsbk.getSaturation())).replace(' ', '0');
        saturationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(saturationBinStr);
        byteArray[3] = saturationBytes[0];
        byteArray[4] = saturationBytes[1];

        byte[] brightnessBytes = new byte[2];
        String brightnessBinStr = String.format("%16s", Integer.toBinaryString(hsbk.getBrightness())).replace(' ', '0');
        brightnessBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr);
        byteArray[5] = brightnessBytes[0];
        byteArray[6] = brightnessBytes[1];

        byte[] kelvinBytes = new byte[2];
        String kelvinBinStr = String.format("%16s", Integer.toBinaryString(hsbk.getKelvin())).replace(' ', '0');
        kelvinBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(kelvinBinStr);
        byteArray[7] = kelvinBytes[0];
        byteArray[8] = kelvinBytes[1];

        byte[] durationBytes = new byte[4];
        String durationBinStr = String.format("%32s", Long.toBinaryString(duration)).replace(' ', '0');
        durationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(durationBinStr);
        byteArray[9] = durationBytes[0];
        byteArray[10] = durationBytes[1];
        byteArray[11] = durationBytes[2];
        byteArray[12] = durationBytes[3];

        return byteArray;
    }

}
