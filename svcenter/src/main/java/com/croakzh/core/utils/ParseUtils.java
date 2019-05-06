package com.croakzh.core.utils;

import java.util.regex.Pattern;

/**
 * @author croakzh
 */
public class ParseUtils {

    public static final Pattern whitespacesColonWhitespace = Pattern.compile("\\s+:\\s");

    public static final Pattern whitespacesColonLastWhitespace = Pattern.compile(":\\s");

    public static final Pattern whitespaces = Pattern.compile(":\\s+");

    /**
     * 格式化文件路径 Linux
     *
     * @param path 文件路径
     * @return 格式化后的文件路径
     */
    public static String formatFilePath(String path) {
        return path.replace("//", "/");
    }

}
