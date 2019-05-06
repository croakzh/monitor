package com.croakzh.service.common;

/**
 * @author croakzh
 */
public class BizErrorCode {

    public final static Integer EX_TRANSACTION_FAIL = -1;

    // 查询失败
    public final static Integer EX_RECORD_NOTEXIST = -100;

    // 增删改失败
    public final static Integer EX_ADD_FAIL = -111;
    public final static Integer EX_UPDATE_FAIL = -112;
    public final static Integer EX_DELETE_FAIL = -113;
    // 名称已经存在
    public final static Integer EX_NAME_EXIST = -114;
    // 关联主键正在使用
    public final static Integer EX_KEY_INUSE = -115;
    // 名字冲突
    public final static Integer EX_NAME_CONFLICT = -116;
    // 关联主键失效
    public final static Integer EX_KEY_INVALID = -117;
    // 上传失败
    public final static Integer EX_UPLOAD_FAIL = -118;
    // 已被使用
    public final static Integer EX_USED = -119;

    // 请求参数
    public final static Integer EX_REQUEST_INVALID_PARAM = -200;

    // 密码错误
    public final static Integer EX_USER_WRONGPASSWORD = -502;
    // 被注销
    public final static Integer EX_USER_BYLOGOUT = -503;
    // 已经登录
    public final static Integer EX_USER_BYLOGIN = -504;
    // 用户未注册
    public final static Integer EX_USER_NOREGISTER = -505;
    // 验证码错误
    public final static Integer EX_USER_CHECKCODEINVALID = -506;
    // 验证码过期
    public final static Integer EX_USER_CHECKCODEINLIMIT = -507;
    // 停用
    public final static Integer EX_USER_BLOCKED = -508;
    // 未激活
    public final static Integer EX_USER_NOACTIVE = -509;
    // 登陆失败
    public final static Integer EX_USER_LOGINFAIL = -511;
    // 激活失败
    public final static Integer EX_USER_ACTIVEFAIL = -512;
    // 不允许的操作
    public final static Integer EX_USER_NOTALLOWED = -521;
    // 注册环信失败
    public final static Integer EX_USER_EASEMOB = -522;
    // 用户余额不足
    public final static Integer EX_USER_BALANCE = -523;
    // 不能重复操作
    public final static Integer EX_USER_REPEAT = -524;

    public final static Integer EX_PUSH_RUNNING = -801;

}
