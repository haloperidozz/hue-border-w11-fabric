package com.github.haloperidozz.hueborder.win32.dwm;

import com.github.haloperidozz.hueborder.win32.color.ColorRef;
import com.github.haloperidozz.hueborder.win32.dll.Dwmapi;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import java.util.function.Function;

public final class DwmWindow {
    private DwmWindow() {
    }

    public static <T> void setAttribute(Pointer hwnd, Attribute<T> attribute, T value) {
        NativeLong result = Dwmapi.INSTANCE.DwmSetWindowAttribute(
                hwnd,
                attribute.getDwAttribute(),
                attribute.makePvAttribute(value),
                attribute.getCbAttribute());

        if (result.longValue() != 0L) {
            throw new IllegalStateException("failed to set attribute (" + attribute.dwAttribute + ")");
        }
    }

    public static <T> void setAttribute(long hwnd, Attribute<T> attribute, T value) {
        setAttribute(Pointer.createConstant(hwnd), attribute, value);
    }

    public static class Attribute<T> {
        public static final Attribute<ColorRef> BORDER_COLOR;
        public static final Attribute<ColorRef> CAPTION_COLOR;
        public static final Attribute<ColorRef> TEXT_COLOR;

        static {
            Function<ColorRef, Pointer> pvColorRef = (colorRef) -> {
                IntByReference reference = new IntByReference(colorRef.getValue());
                return reference.getPointer();
            };

            BORDER_COLOR = new Attribute<>(Dwmapi.DWMWA_BORDER_COLOR, 4, pvColorRef);
            CAPTION_COLOR = new Attribute<>(Dwmapi.DWMWA_CAPTION_COLOR, 4, pvColorRef);
            TEXT_COLOR = new Attribute<>(Dwmapi.DWMWA_TEXT_COLOR, 4, pvColorRef);
        }

        private final int dwAttribute;
        private final int cbAttribute;
        private final Function<T, Pointer> pvAttribute;

        private Attribute(int dwAttr, int cbAttr, Function<T, Pointer> pvAttr) {
            this.dwAttribute = dwAttr;
            this.cbAttribute = cbAttr;
            this.pvAttribute = pvAttr;
        }

        public int getDwAttribute() {
            return dwAttribute;
        }

        public int getCbAttribute() {
            return cbAttribute;
        }

        public Pointer makePvAttribute(T value) {
            return pvAttribute.apply(value);
        }
    }
}
