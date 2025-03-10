package com.github.haloperidozz.hueborder.border;

import com.github.haloperidozz.hueborder.win32.color.ColorRef;
import com.github.haloperidozz.hueborder.win32.color.ColorRefEffect;
import com.github.haloperidozz.hueborder.win32.dwm.DwmWindow;
import com.github.haloperidozz.hueborder.win32.version.WinVersion;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFWNativeWin32;

public final class BorderColorAnimator {
    private static final ColorRef BASE_COLOR = new ColorRef(0x0000FF); // red

    private final long hwnd;
    private float hue;

    public BorderColorAnimator(Window window) {
        this.hwnd = GLFWNativeWin32.glfwGetWin32Window(window.getHandle());
        this.hue = 0.0f;
    }

    public void tick() {
        if (!WinVersion.isWindows11Build22000OrGreater()) {
            return;
        }

        ColorRef color = ColorRefEffect.hueRotate(BASE_COLOR, hue);
        ColorRef colorInverted = ColorRefEffect.invert(color);

        DwmWindow.setAttribute(hwnd, DwmWindow.Attribute.BORDER_COLOR, color);
        DwmWindow.setAttribute(hwnd, DwmWindow.Attribute.CAPTION_COLOR, color);
        DwmWindow.setAttribute(hwnd, DwmWindow.Attribute.TEXT_COLOR, colorInverted);

        hue = (hue + 1.0f) % 360.0f;
    }
}