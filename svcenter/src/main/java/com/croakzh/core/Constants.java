package com.croakzh.core;

import java.io.File;

/**
 * @author croakzh
 */
public class Constants {

    /**
     * SFTP 22 端口
     */
    public static final Integer SFTP_PORT = 22;

    public static final Integer DEFAULT_TIMEOUT = 60000;

    public static final Long INTERVAL = 1000L;

    public static final String CHARSET_UTF8 = "UTF-8";

    public static final String FILE_SEPARATOR = File.separator;

    public static final String LINUX_SEPARATOR = "/";

    // shell name
    public static final String SHELL_VSH = "./a.sh status";
    public static final String SHELL_START = "./a.sh start &";
    public static final String SHELL_STOP = "./a.sh stop &";

    public static final String SHELL_ASH = "a.sh";

    public static final String RUNNING_STRING = "is running";
    public static final String STOP_STRING = "is stopped";


    // time formatter
    public static final String DEFAULT_TIMEFORMATTER = "yyyyMMddHHmmss";

    // mark
    public static final String MARK_DOT = ".";

}
