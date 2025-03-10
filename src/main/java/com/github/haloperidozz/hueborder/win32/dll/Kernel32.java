package com.github.haloperidozz.hueborder.win32.dll;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public interface Kernel32 extends StdCallLibrary {
    Kernel32 INSTANCE = Native.load("Kernel32", Kernel32.class);

    // https://learn.microsoft.com/ru-ru/windows/win32/api/winnt/ns-winnt-osversioninfoexa
    @Structure.FieldOrder({
            "dwOSVersionInfoSize",
            "dwMajorVersion",
            "dwMinorVersion",
            "dwBuildNumber",
            "dwPlatformId",
            "szCSDVersion",
            "wServicePackMajor",
            "wServicePackMinor",
            "wSuiteMask",
            "wProductType",
            "wReserved"
    })
    class OSVERSIONINFOEX extends Structure {
        public int dwOSVersionInfoSize = 0;
        public int dwMajorVersion = 0;
        public int dwMinorVersion = 0;
        public int dwBuildNumber = 0;
        public int dwPlatformId = 0;
        public char[] szCSDVersion = new char[128];
        public short wServicePackMajor = 0;
        public short wServicePackMinor = 0;
        public short wSuiteMask = 0;
        public byte wProductType = 0;
        public byte wReserved = 0;

        public static class ByReference extends OSVERSIONINFOEX implements Structure.ByReference {
        }
    }

    // https://learn.microsoft.com/ru-ru/windows/win32/api/winbase/nf-winbase-verifyversioninfoa
    int VER_BUILDNUMBER = 0x0000004;
    int VER_MAJORVERSION = 0x0000002;
    int VER_MINORVERSION = 0x0000001;

    // https://learn.microsoft.com/ru-ru/windows/win32/api/winnt/nf-winnt-versetconditionmask
    byte VER_GREATER_EQUAL = 3;

    // https://learn.microsoft.com/ru-ru/windows/win32/api/winnt/nf-winnt-versetconditionmask
    long VerSetConditionMask(long ConditionMask, int TypeMask, byte Condition);

    // https://learn.microsoft.com/ru-ru/windows/win32/api/winbase/nf-winbase-verifyversioninfoa
    boolean VerifyVersionInfoA(OSVERSIONINFOEX.ByReference lpVersionInformation, int dwTypeMask, long dwlConditionMask);
}
