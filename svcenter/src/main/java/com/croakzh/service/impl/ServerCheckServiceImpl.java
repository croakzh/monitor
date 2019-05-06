package com.croakzh.service.impl;

import com.croakzh.core.Constants;
import com.croakzh.core.connection.ServerConnection;
import com.croakzh.core.context.ActionContext;
import com.croakzh.core.utils.ShellUtils;
import com.croakzh.core.utils.ValidUtils;
import com.croakzh.service.IServerCheckService;
import com.croakzh.webfront.po.ApplicationPo;
import com.croakzh.webfront.po.ServerPo;
import com.croakzh.webfront.service.IApplicationService;
import com.croakzh.webfront.service.IServerService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务器检查
 *
 * @author croakzh
 */
@Service("serverCheckService")
@Slf4j
public class ServerCheckServiceImpl implements IServerCheckService {

    /**
     * application表业务层接口
     */
    @Autowired
    @Qualifier("applicationService")
    private IApplicationService applicationService;

    /**
     * server表业务层接口
     */
    @Autowired
    @Qualifier("serverService")
    private IServerService serverService;

    @Override
    public void updateServersStatus(Integer serverid) {
        log.debug("Start to update servers status...");
        if (serverid != null) {
            ServerPo server = serverService.getServer(serverid);
            refreshServerStatus(server);
        } else {
            List<ServerPo> servers = serverService.findAllServers(new ServerPo());
            for (ServerPo server : servers) {
                refreshServerStatus(server);
            }
        }
        log.debug("End to update servers status.");
    }

    private void refreshServerStatus(ServerPo server) {
        String host = server.getHost();
        // 连接列表中没有对应的连接，或者连接为空
        ServerPo newer = new ServerPo();
        newer.setServerid(server.getServerid());
        try {
            // 重新建立连接并写入到连接列表
            ActionContext.addConnection(host,
                    new ServerConnection(host, Integer.valueOf(server.getSshport()), server.getSshname(),
                            server.getSshpwd()).getSession());
            System.out.println("success start");
            newer.setStatus(0);
            serverService.updateServer(newer);

        } catch (JSchException e) {
            // 连接执行报错，则重置状态
            newer.setStatus(1);
            serverService.updateServer(newer);
        }
    }


    @Override
    public void updateApplicationsStatus(Integer applicationid) {
        log.debug("Start to update applications status...");
        if (applicationid != null) {
            ApplicationPo application = applicationService.getApplication(applicationid);
            refreshApplicationStatus(application);
        } else {
            List<ApplicationPo> applications = applicationService.findAllApplications(new ApplicationPo());
            for (ApplicationPo application : applications) {
                refreshApplicationStatus(application);
            }
        }
        log.debug("End to update applications status.");
    }

    private void refreshApplicationStatus(ApplicationPo application) {
        ApplicationPo applicationPo = new ApplicationPo();
        applicationPo.setApplicationid(application.getApplicationid());
        try {
            Session session = ActionContext.getConnections().get(application.getHost());
            String message3 = ValidUtils.verifyDevelopPath(session, application.getDeveloppath(),
                    application.getDeveloppath().concat(Constants.LINUX_SEPARATOR).concat(Constants.SHELL_VSH));
            if (StringUtils.isNotEmpty(message3)) {
                application.setAppstatus(Byte.valueOf("3"));
            }
            List<String> res2 = ShellUtils.execCmd(session, "cd " + application.getDeveloppath() + " && " + Constants.SHELL_VSH);
            for (String line : res2) {
                if (line.contains(Constants.RUNNING_STRING)) {
                    applicationPo.setAppstatus(Byte.valueOf("0"));
                }
                if (line.contains(Constants.STOP_STRING)) {
                    applicationPo.setAppstatus(Byte.valueOf("1"));
                }
            }
            applicationService.updateApplication(applicationPo);
        } catch (Exception e) {
            // 连接执行报错，则重置状态
            applicationPo.setAppstatus(Byte.valueOf("3"));
            applicationService.updateApplication(applicationPo);
        }
    }

}
