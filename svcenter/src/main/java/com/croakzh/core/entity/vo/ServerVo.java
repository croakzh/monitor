package com.croakzh.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务器登录信息
 *
 * @author croakzh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerVo implements Serializable {


    private static final long serialVersionUID = 3088255982460037073L;

    /**
     * 服务器ip
     */
    private String host;

    /**
     * 端口号，可不填，默认为 SFTP 22 端口
     */
    private String port;

    /**
     * 服务器登录用户名
     */
    private String username;

    /**
     * 服务器登录密码
     */
    private String password;

}
