package com.github.haloperidozz.hueborder.win32.color;

public final class ColorRef {
    private final int value; // 0x00bbggrr

    public ColorRef(int value) {
        this.value = value;

        // See: https://learn.microsoft.com/en-us/windows/win32/gdi/colorref
        if ((value >>> 24) != 0) {
            throw new IllegalArgumentException("the high-order byte must be zero");
        }
    }

    public ColorRef(byte r, byte g, byte b) {
        this((r & 255) | ((g & 255) << 8) | ((b & 255) << 16));
    }

    public ColorRef(float r, float g, float b) {
        this(
                (byte) ((int) (Math.max(0.0f, Math.min(1.0f, r)) * 255.0f)),
                (byte) ((int) (Math.max(0.0f, Math.min(1.0f, g)) * 255.0f)),
                (byte) ((int) (Math.max(0.0f, Math.min(1.0f, b)) * 255.0f))
        );
    }

    public int getValue() {
        return value;
    }

    public byte getRed() {
        return (byte) (this.value & 255);
    }

    public byte getGreen() {
        return (byte) ((this.value >>> 8) & 255);
    }

    public byte getBlue() {
        return (byte) ((this.value >>> 16) & 255);
    }

    public float[] normalize() {
        return new float[]{
                (float) (this.getRed() & 255) / 255.0f,
                (float) (this.getGreen() & 255) / 255.0f,
                (float) (this.getBlue() & 255) / 255.0f
        };
    }

    @Override
    public String toString() {
        return String.format("ColorRef(value=0x%08x)", value);
    }
}
