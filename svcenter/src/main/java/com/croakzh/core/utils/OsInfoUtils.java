package com.croakzh.core.utils;

/**
 * @author croakzh
 */
public class OsInfoUtils {

    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * 是否为 linux
     *
     * @return true/false
     */
    public static boolean isLinux() {
        return OS.indexOf("linux") >= 0;
    }

    /**
     * 是否为 window
     * @return true/false
     */
    public static boolean isWindows() {
        return OS.indexOf("windows") >= 0;
    }

}
