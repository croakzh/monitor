package com.croakzh.webfront.controller;

import com.croakzh.controller.base.BaseCtrl;
import com.croakzh.controller.model.RSResult;
import com.croakzh.core.context.ActionContext;
import com.croakzh.service.IServerCheckService;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.po.ServerPo;
import com.croakzh.webfront.service.IServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller code for the table : server
 *
 * @author croakzh
 * @since 2018-11-23
 */
@Controller
@RequestMapping("/server")
@Slf4j
public class ServerController extends BaseCtrl {

    /**
     * server表业务层接口
     */
    private final IServerService serverService;

    private final IServerCheckService serverCheckService;

    @Autowired
    public ServerController(IServerService serverService, IServerCheckService serverCheckService) {
        this.serverService = serverService;
        this.serverCheckService = serverCheckService;
    }

    /**
     * 查询所有实体对象的数量
     *
     * @param server 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/count")
    @ResponseBody
    public RSResult count(@RequestBody ServerPo server) {
        log.info("Start get the count of the table : server, the request is : {}", server);
        RSResult result = new RSResult();
        try {
            int count = serverService.countServers(server);
            result.setResult(0);
            result.setData(count);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get the count of the table : server, success.");
        return result;
    }

    /**
     * 查询所有实体对象
     *
     * @param server 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public RSResult list(@RequestBody ServerPo server) {
        log.info("Start get list of the table : server, the request is : {}", server);
        RSResult result = new RSResult();
        try {
            int count = serverService.countServers(server);
            List<ServerPo> list = new ArrayList<>();
            if (count > 0) {
                server.setPagestart((server.getCurpage() - 1) * server.getPagesize());
                list = serverService.findServersByPage(server);
            }
            result.setResult(0);
            result.setTotal(count);
            result.setData(list);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get list of the table : server, success.");
        return result;
    }

    /**
     * 查看具体的实体
     *
     * @param server 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/detail")
    @ResponseBody
    public RSResult info(@RequestBody ServerPo server) {
        log.info("Get detail object of the table : server, the request is : {}", server);
        RSResult result = new RSResult();
        try {
            ServerPo obj = serverService.getServer(server.getServerid());
            result.setResult(0);
            result.setData(obj);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get detail object of the table : server, success.");
        return result;
    }

    /**
     * 新增对象实体
     *
     * @param server 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/new")
    @ResponseBody
    public RSResult add(@RequestBody ServerPo server) {
        log.info("Add object to the table : server, the request is : {}", server);
        RSResult result = new RSResult();
        try {
            if (ActionContext.getConnections().containsKey(server.getHost())) {
                server.setStatus(0);
            } else {
                server.setStatus(1);
            }
            Integer id = serverService.addServer(server);
            result.setResult(0);
            result.setData(id);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Add object to the table : server, success.");
        return result;
    }

    /**
     * 更新对象实体
     *
     * @param server 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public RSResult update(@RequestBody ServerPo server) {
        log.info("Update object from the table : server, the request is : {}", server);
        RSResult result = new RSResult();
        try {
            if (ActionContext.getConnections().containsKey(server.getHost())) {
                server.setStatus(0);
            } else {
                server.setStatus(1);
            }
            serverService.updateServer(server);
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Update object from the table : server, success.");
        return result;
    }

    /**
     * 删除对象实体
     *
     * @param server 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public RSResult delete(@RequestBody ServerPo server) {
        log.info("Delete object from the table : server, the request is : {}", server);
        RSResult result = new RSResult();
        try {
            serverService.deleteServer(server.getServerid());
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Delete object from the table : server, success.");
        return result;
    }

    /**
     * 状态刷新
     *
     * @param server 请求实体
     * @return {@link RSResult}
     */
    @PostMapping(value = "/refresh")
    @ResponseBody
    public RSResult refresh(@RequestBody ServerPo server) {
        log.info("Start refresh server login status, params is {}", server);
        RSResult result = new RSResult();
        if (server.getServerid() != null) {
            serverCheckService.updateServersStatus(server.getServerid());
        } else {
            serverCheckService.updateServersStatus(null);
        }
        result.setResult(0);
        result.setMessage("状态刷新成功！");
        log.info("End refresh server login status.");
        return result;
    }
}
