package com.croakzh.core.utils;

import com.croakzh.webfront.po.ServerPo;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 校验
 *
 * @author croakzh
 */
public class ValidUtils {

    public static final Pattern DEVELOP_REGEX_PATH = Pattern.compile("^\\/(\\w+\\/?)+$");

    /**
     * 基础校验方法
     *
     * @param pattern 正则
     * @param target  校验串
     * @return true、false
     */
    public static Boolean valid(Pattern pattern, String target) {
        return pattern.matcher(target).matches();
    }

    /**
     * 校验路径
     *
     * @param developPath 部署路径
     * @return true、false
     */
    public static Boolean validDevelopPath(String developPath) {
        return valid(DEVELOP_REGEX_PATH, developPath);
    }

    /**
     * 校验路径
     *
     * @param developPath 部署路径
     * @return Message
     */
    public static String verifyDevelopPath(String developPath) {
        if (StringUtils.isEmpty(developPath)) {
            return "部署路径不能为空！";
        }
        if (!validDevelopPath(developPath)) {
            return "部署路径有误！";
        }
        return "";
    }

    /**
     * 校验服务器
     *
     * @param server 检索出的服务器
     * @return Message
     */
    public static String verifyServer(ServerPo server) {
        if (server == null) {
            return "选择服务器有误，参数有误！";
        }
        if (server.getStatus() == 1) {
            return "服务器未连接，应用测试失败！";
        }
        return "";
    }

    /**
     * 校验会话执行
     *
     * @param session     会话
     * @param developPath 部署路径
     * @param shellPath   脚本路径
     * @return Message
     */
    public static String verifyDevelopPath(Session session, String developPath, String shellPath) {
        if (!ShellUtils.exists(session, developPath)) {
            return "部署路径不存在，请检查后测试";
        }
        if (!ShellUtils.exists(session, ParseUtils.formatFilePath(shellPath))) {
            return "部署路径有误，缺失对应应用执行脚本！";
        }
        return "";
    }

}
