package com.croakzh.controller;

import com.croakzh.controller.model.RSResult;
import com.croakzh.core.connection.ServerConnection;
import com.croakzh.core.context.ActionContext;
import com.croakzh.core.entity.vo.ServerVo;
import com.croakzh.webfront.po.ServerPo;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 服务器连接
 *
 * @author croakzh
 */
@Controller
@RequestMapping(value = "/connect")
@Log4j2
public class ConnectController {

    /**
     * 服务器上线
     *
     * @param cond 具体请求
     * @return {@link RSResult}
     */
    @PostMapping("/online")
    public @ResponseBody
    RSResult online(@RequestBody ServerVo cond) {
        log.debug("Start login the server, params: ", cond);
        RSResult result = new RSResult();
        String host = cond.getHost();
        if (StringUtils.isEmpty(host)) {
            result.setResult(-1);
            result.setMessage("服务器IP不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(cond.getUsername()) && StringUtils.isEmpty(cond.getPassword())) {
            result.setResult(-1);
            result.setMessage("登录用户名和密码不能为空！");
            return result;
        }
        try {
//            Map<String, Session> connections = ActionContext.getConnections();
//            if (connections.containsKey(host)) {
//                result.setResult(-1);
//                result.setMessage("服务器已登录，请勿重复登录！");
//            }
            ActionContext.addConnection(host,
                    new ServerConnection(host, Integer.valueOf(cond.getPort()), cond.getUsername(),
                            cond.getPassword()).getSession());
            result.setResult(0);
            result.setMessage("服务器登录成功！");
        } catch (JSchException e) {
            log.error("服务器登录失败！", e);
            result.setResult(-1);
            result.setMessage("服务器登录失败！");
        }
        log.debug("Login success, params: ", cond);
        return result;
    }

    /**
     * 服务器下线
     *
     * @param cond 具体请求
     * @return {@link RSResult}
     */
    @PostMapping("/outline")
    public @ResponseBody
    RSResult outline(@RequestBody ServerVo cond) {
        log.debug("Start logout the server, params: ", cond);
        RSResult result = new RSResult();
        String host = cond.getHost();
        if (StringUtils.isEmpty(host)) {
            result.setResult(-1);
            result.setMessage("服务器IP不能为空！");
            return result;
        }
        Map<String, Session> connections = ActionContext.getConnections();

        if (!connections.containsKey(host)) {
            result.setResult(-1);
            result.setMessage("系统中服务器未登录！");
            return result;
        }
        ActionContext.removeConnection(host);
        result.setResult(0);
        result.setMessage("服务器登出成功！");
        log.debug("Logout success, params: ", cond);
        return result;
    }

    /**
     * 展示当前所有的服务器连接
     *
     * @return {@link RSResult}
     */
    @PostMapping("/connections")
    public @ResponseBody
    RSResult listConnections() {
        log.debug("Start list server connections.");
        RSResult result = new RSResult();
        Map<String, Boolean> connects = new LinkedHashMap<>();
        Map<String, Session> connections = ActionContext.getConnections();
        connections.forEach((k, v) -> connects.put(k, v.isConnected()));
        result.setData(connects);
        result.setResult(0);
        log.debug("Get connections success.");
        return result;
    }

    /**
     * 测试服务器连接
     *
     * @param cond 服务器参数
     * @return {@link RSResult}
     */
    @PostMapping("/test")
    public @ResponseBody
    RSResult testConnect(@RequestBody ServerPo cond) {
        log.debug("Start test server connection. params : {}", cond);
        RSResult result = new RSResult();
        String host = cond.getHost();
        if (StringUtils.isEmpty(host)) {
            result.setResult(-1);
            result.setMessage("服务器IP不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(cond.getSshname()) && StringUtils.isEmpty(cond.getSshname())) {
            result.setResult(-1);
            result.setMessage("服务器用户名和密码不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(cond.getSshport())) {
            result.setResult(-1);
            result.setMessage("服务器端口号不能为空！");
        }
        try {
            ActionContext.addConnection(host, new ServerConnection(host, Integer.valueOf(cond.getSshport()), cond.getSshname(),
                    cond.getSshpwd()).getSession());
            result.setResult(0);
            result.setMessage("服务器连接成功！");
        } catch (JSchException ex) {
            result.setResult(-2);
            result.setMessage("服务器连接失败！");
        }
        log.debug("End test server connection.");
        return result;
    }

}
