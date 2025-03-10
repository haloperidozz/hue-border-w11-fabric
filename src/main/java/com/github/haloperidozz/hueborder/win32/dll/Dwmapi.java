package com.github.haloperidozz.hueborder.win32.dll;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public interface Dwmapi extends StdCallLibrary {
    Dwmapi INSTANCE = Native.load("Dwmapi", Dwmapi.class);

    // https://learn.microsoft.com/en-us/windows/win32/api/dwmapi/ne-dwmapi-dwmwindowattribute
    int DWMWA_BORDER_COLOR = 34;
    int DWMWA_CAPTION_COLOR = 35;
    int DWMWA_TEXT_COLOR = 36;

    // https://learn.microsoft.com/en-us/windows/win32/api/dwmapi/nf-dwmapi-dwmsetwindowattribute
    NativeLong DwmSetWindowAttribute(Pointer hwnd, int dwAttribute, Pointer pvAttribute, int cbAttribute);
}
