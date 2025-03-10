package com.github.haloperidozz.hueborder.win32.version;

import com.github.haloperidozz.hueborder.win32.dll.Kernel32;
import com.sun.jna.Platform;

public final class WinVersion {
    private static final boolean isWindows11Build22000OrGreater;

    static {
        isWindows11Build22000OrGreater = isVersionOrGreater(10, 0, 22000);
    }

    private WinVersion() {
    }

    public static boolean isWindows11Build22000OrGreater() {
        return isWindows11Build22000OrGreater;
    }

    public static boolean isVersionOrGreater(int major, int minor, int buildNumber) {
        if (!Platform.isWindows()) {
            return false;
        }

        Kernel32.OSVERSIONINFOEX.ByReference versionInfo;

        versionInfo = new Kernel32.OSVERSIONINFOEX.ByReference();
        versionInfo.dwOSVersionInfoSize = versionInfo.size();
        versionInfo.dwMajorVersion = major;
        versionInfo.dwMinorVersion = minor;
        versionInfo.dwBuildNumber = buildNumber;

        long conditions = 0;

        conditions = Kernel32.INSTANCE.VerSetConditionMask(
                conditions, Kernel32.VER_MAJORVERSION, Kernel32.VER_GREATER_EQUAL);
        conditions = Kernel32.INSTANCE.VerSetConditionMask(
                conditions, Kernel32.VER_MINORVERSION, Kernel32.VER_GREATER_EQUAL);
        conditions = Kernel32.INSTANCE.VerSetConditionMask(
                conditions, Kernel32.VER_BUILDNUMBER, Kernel32.VER_GREATER_EQUAL);

        int typeMask = Kernel32.VER_MAJORVERSION | Kernel32.VER_MINORVERSION | Kernel32.VER_BUILDNUMBER;

        return Kernel32.INSTANCE.VerifyVersionInfoA(versionInfo, typeMask, conditions);
    }
}
