package com.github.haloperidozz.hueborder;

import com.github.haloperidozz.hueborder.win32.color.ColorRef;
import com.github.haloperidozz.hueborder.win32.color.ColorRefEffect;
import com.github.haloperidozz.hueborder.win32.dwm.DwmWindow;
import com.github.haloperidozz.hueborder.win32.version.WinVersion;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFWNativeWin32;

public final class HueBorderAnimator {
    private static final ColorRef BASE_COLOR = new ColorRef(0x0000FF); // red

    private final long hwnd;
    private float hue;

    public HueBorderAnimator(long hwnd) {
        this.hwnd = hwnd;
        this.hue = 0.0f;

        if (!WinVersion.isWindows11Build22000OrGreater()) {
            throw new IllegalStateException("unsupported Windows version");
        }
    }

    public HueBorderAnimator(Window window) {
        this(GLFWNativeWin32.glfwGetWin32Window(window.getHandle()));
    }

    public void tick() {
        ColorRef color = ColorRefEffect.hueRotate(BASE_COLOR, hue);
        ColorRef colorInverted = ColorRefEffect.invert(color);

        DwmWindow.setAttribute(hwnd, DwmWindow.Attribute.BORDER_COLOR, color);
        DwmWindow.setAttribute(hwnd, DwmWindow.Attribute.CAPTION_COLOR, color);
        DwmWindow.setAttribute(hwnd, DwmWindow.Attribute.TEXT_COLOR, colorInverted);

        hue = (hue + 1.0f) % 360.0f;
    }
}