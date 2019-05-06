package com.croakzh.webfront.controller;

import com.croakzh.controller.base.BaseCtrl;
import com.croakzh.controller.model.RSResult;
import com.croakzh.core.Constants;
import com.croakzh.core.context.ActionContext;
import com.croakzh.core.utils.ParseUtils;
import com.croakzh.core.utils.ShellUtils;
import com.croakzh.core.utils.ValidUtils;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.po.ApplicationPo;
import com.croakzh.webfront.po.ServerPo;
import com.croakzh.webfront.service.IApplicationService;
import com.croakzh.webfront.service.IServerService;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 应用控制层代码
 *
 * @author croakzh
 * @since 2018-11-23
 */
@Controller
@RequestMapping("/application")
@Slf4j
public class ApplicationController extends BaseCtrl {

    /**
     * application表业务层接口
     */
    private final IApplicationService applicationService;

    /**
     * server表业务层接口
     */
    private final IServerService serverService;

    @Autowired
    public ApplicationController(IApplicationService applicationService, IServerService serverService) {
        this.applicationService = applicationService;
        this.serverService = serverService;
    }

    /**
     * 查询所有实体对象的数量
     *
     * @param application 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/count")
    @ResponseBody
    public RSResult count(@RequestBody ApplicationPo application) {
        log.info("Start get the count of the table : application, the request is : {}", application);
        RSResult result = new RSResult();
        try {
            int count = applicationService.countApplications(application);
            result.setResult(0);
            result.setData(count);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get the count of the table : application, success.");
        return result;
    }

    /**
     * 查询所有实体对象
     *
     * @param application 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public RSResult list(@RequestBody ApplicationPo application) {
        log.info("Start get list of the table : application, the request is : {}", application);
        RSResult result = new RSResult();
        try {
            int count = applicationService.countApplications(application);
            List<ApplicationPo> list = new ArrayList<>();
            if (count > 0) {
                application.setPagestart((application.getCurpage() - 1) * application.getPagesize());
                list = applicationService.findApplicationsByPage(application);
            }
            result.setResult(0);
            result.setTotal(count);
            result.setData(list);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get list of the table : application, success.");
        return result;
    }

    /**
     * 查看具体的实体
     *
     * @param application 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/detail")
    @ResponseBody
    public RSResult info(@RequestBody ApplicationPo application) {
        log.info("Get detail object of the table : application, the request is : {}", application);
        RSResult result = new RSResult();
        try {
            ApplicationPo obj = applicationService.getApplication(application.getApplicationid());
            result.setResult(0);
            result.setData(obj);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get detail object of the table : application, success.");
        return result;
    }

    /**
     * 新增对象实体
     *
     * @param application 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/new")
    @ResponseBody
    public RSResult add(@RequestBody ApplicationPo application) {
        log.info("Add object to the table : application, the request is : {}", application);
        RSResult result = new RSResult();
        try {
            Integer id = applicationService.addApplication(application);
            result.setResult(0);
            result.setData(id);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Add object to the table : application, success.");
        return result;
    }

    /**
     * 更新对象实体
     *
     * @param application 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public RSResult update(@RequestBody ApplicationPo application) {
        log.info("Update object from the table : application, the request is : {}", application);
        RSResult result = new RSResult();
        try {
            applicationService.updateApplication(application);
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Update object from the table : application, success.");
        return result;
    }

    /**
     * 删除对象实体
     *
     * @param application 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public RSResult delete(@RequestBody ApplicationPo application) {
        log.info("Delete object from the table : application, the request is : {}", application);
        RSResult result = new RSResult();
        try {
            applicationService.deleteApplication(application.getApplicationid());
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Delete object from the table : application, success.");
        return result;
    }

    /**
     * 测试部署的应用的状态
     *
     * @param cond 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping("/test")
    public @ResponseBody
    RSResult testApplication(@RequestBody ApplicationPo cond) {
        log.debug("Start test server's application. params : {}", cond);
        RSResult result = new RSResult(-1);
        Integer host = cond.getServerid();
        // 校验部署路径
        String message = ValidUtils.verifyDevelopPath(cond.getDeveloppath());
        if (StringUtils.isNotEmpty(message)) {
            result.setMessage(message);
            return result;
        }
        if (host == null) {
            result.setMessage("请选择相应服务器！");
            return result;
        }
        ServerPo server = serverService.getServer(host);
        // 校验检索出的服务器
        String message2 = ValidUtils.verifyServer(server);
        if (StringUtils.isNotEmpty(message2)) {
            result.setMessage(message2);
            return result;
        }
        try {
            result.setResult(0);
            result.setData(1);
            result.setMessage(applicationService.testApplication(cond));
            return result;
        } catch (Exception ex) {
            result.setResult(-2);
            result.setMessage("应用测试失败！");
        }
        log.debug("End test server application.");
        return result;
    }

    /**
     * 启动应用
     *
     * @param cond 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping("/start")
    public @ResponseBody
    RSResult startApplication(@RequestBody ApplicationPo cond) {
        log.debug("Start start application,params is {}", cond);
        RSResult result = new RSResult(-1);

        ApplicationPo application = applicationService.getApplication(cond.getApplicationid());
        if (application.getAppstatus() != 1) {
            result.setMessage("应用运行中，无效操作！");
            return result;
        }
        Session session = ActionContext.getConnections().get(cond.getHost());
        if (session == null) {
            result.setMessage("服务器连接失败！");
            return result;
        }
        String message = ValidUtils.verifyDevelopPath(session, cond.getDeveloppath(),
                cond.getDeveloppath().concat(Constants.LINUX_SEPARATOR).concat(Constants.SHELL_START));
        if (StringUtils.isNotEmpty(message)) {
            result.setMessage(message);
            return result;
        }
        try {
            List<String> list = ShellUtils.execCmd(session,
                    "cd " + cond.getDeveloppath() + " && . " + Constants.SHELL_START);
            System.out.println("cd " + cond.getDeveloppath() + " && . " + Constants.SHELL_START);
            list.forEach(System.out::println);
            ApplicationPo applicationPo = new ApplicationPo();
            applicationPo.setApplicationid(cond.getApplicationid());
            applicationPo.setAppstatus(Byte.valueOf("0"));
            applicationService.updateApplication(applicationPo);
            result.setResult(0);
            result.setMessage("应用启动成功");
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setResult(-2);
            result.setMessage("应用启动失败！");
        }
        log.debug("End start application.");
        return result;
    }

    /**
     * 停止应用
     *
     * @param cond 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping("/stop")
    public @ResponseBody
    RSResult stopApplication(@RequestBody ApplicationPo cond) {
        log.debug("Start stop application,params is {}", cond);
        RSResult result = new RSResult(-1);

        ApplicationPo application = applicationService.getApplication(cond.getApplicationid());
        if (application.getAppstatus() != 0) {
            result.setMessage("应用未运行，无效操作！");
            return result;
        }
        Session session = ActionContext.getConnections().get(cond.getHost());
        if (session == null) {
            result.setMessage("服务器连接失败！");
            return result;
        }
        String message = ValidUtils.verifyDevelopPath(session, cond.getDeveloppath(),
                cond.getDeveloppath().concat(Constants.LINUX_SEPARATOR).concat(Constants.SHELL_STOP));
        if (StringUtils.isNotEmpty(message)) {
            result.setMessage(message);
            return result;
        }
        try {
            ShellUtils.execCmd(session, "cd " + cond.getDeveloppath() + " && . " + Constants.SHELL_STOP);
            ApplicationPo applicationPo = new ApplicationPo();
            applicationPo.setApplicationid(cond.getApplicationid());
            applicationPo.setAppstatus(Byte.valueOf("1"));
            applicationService.updateApplication(applicationPo);
            result.setResult(0);
            result.setMessage("应用停止成功");
        } catch (Exception ex) {
            result.setResult(-2);
            result.setMessage("应用停止失败！");
        }
        log.debug("End stop application.");
        return result;
    }


    /**
     * 发布应用
     *
     * @return {@link RSResult} rest返回值
     */
    @PostMapping("/release")
    public @ResponseBody
    RSResult releaseApplication(@RequestBody ApplicationPo cond) {
        log.debug("Start release application, param is {}", cond);
        RSResult result = new RSResult(-1);
        Integer applicationid = cond.getApplicationid();
        if (applicationid == null) {
            result.setMessage("请求参数有误！");
        }
        ApplicationPo application = applicationService.getApplication(applicationid);
        if (application == null || StringUtils.isEmpty(application.getDeveloppath())) {
            result.setMessage("应用部署路径有误！");
            return result;
        }
        String name = cond.getName();
        String uid = cond.getUid();
        String fileName = uid + "-" + name;
        String developpath = ParseUtils.formatFilePath(application.getDeveloppath() + Constants.FILE_SEPARATOR + name);
        String savePath =
                getUploadPath() + Constants.FILE_SEPARATOR + fileName;
        // 上传至对应服务器。

        try {
            // TODO备份
            ShellUtils.uploadFile(ActionContext.getSession(application.getHost()), savePath, developpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setResult(0);
        result.setMessage("应用发布成功！");
        return result;
    }

}
