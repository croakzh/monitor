package com.croakzh.service.common;

/**
 * @author croakzh
 */
public class BizValue {
    public final static int SYSTEM_DEFAULT_KEY_START = 1000;

    // 用户相关
    public final static String INIT_PASSWORD = "123456";
    // 校验码有效时间5分钟
    public final static int CHECKCODE_VALID_DURATION = 5;

    // 超级管理员
    public final static int USERID_ADMIN = 1;
    public final static int ROLEID_ADMIN = 1;

    //后台用户用户类型
    public final static int USERTYPE_ADMIN = 1;

    // 账号停用、激活状态
    public final static int USER_STATUS_NOACTIVE = 1;
    public final static int USER_STATUS_ACTIVE = 2;
    public final static int USER_STATUS_BLOCK = 3;

    // 用户登录登出状态
    public final static int USER_LOGIN_NO = 0;
    public final static int USER_LOGIN_YES = 1;

    // 系统版本类型
    public final static int VERSION_OSTYPE_WINDOWS = 1;
    public final static int VERSION_OSTYPE_ANDROID = 2;
    public final static int VERSION_OSTYPE_IOS = 3;

    // 新增的版本
    public final static int VERSION_ADD = 0;
    // 当前在用的版本
    public final static int VERSION_INUSE = 1;
    public final static int VERSION_DELETE = 2;


    /**
     * 组织名称, token算法需要
     */
    public static final String GROUP_NAME = "www.xxx.group";
}
