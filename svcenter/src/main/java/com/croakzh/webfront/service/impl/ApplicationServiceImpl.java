package com.croakzh.webfront.service.impl;

import com.croakzh.core.Constants;
import com.croakzh.core.context.ActionContext;
import com.croakzh.core.utils.ShellUtils;
import com.croakzh.core.utils.ValidUtils;
import com.croakzh.service.common.BizCache;
import com.croakzh.service.common.BizErrorCode;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.mapper.ApplicationMapper;
import com.croakzh.webfront.po.ApplicationPo;
import com.croakzh.webfront.service.IApplicationService;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ServiceImpl code for the table : application
 *
 * @author croakzh
 * @since 2018-11-23
 */
@Service("applicationService")
@Slf4j
public class ApplicationServiceImpl implements IApplicationService {

    /**
     * application表持久层接口
     */
    @Autowired
    @Qualifier("applicationMapper")
    private ApplicationMapper applicationMapper;

    /**
     * 统计实体对象的数量
     *
     * @param application 请求实体参数
     * @return 统计个数
     */
    @Override
    public Integer countApplications(ApplicationPo application) {
        log.debug("countApplications starting...");
        int count;
        try {
            count = applicationMapper.countApplications(application);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("countApplications end.");
        return count;
    }

    /**
     * 统计实体对象的数量
     *
     * @param application 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<ApplicationPo> findAllApplications(ApplicationPo application) {
        log.debug("findAllApplications starting...");
        List<ApplicationPo> applications;
        try {
            applications = applicationMapper.findAllApplications(application);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findAllApplications end.");
        return applications;
    }

    /**
     * 统计实体对象的数量
     *
     * @param application 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<ApplicationPo> findApplicationsByPage(ApplicationPo application) {
        log.debug("findApplicationsByPage starting...");
        List<ApplicationPo> applications;
        try {
            applications = applicationMapper.findApplicationsByPage(application);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findApplicationsByPage end.");
        return applications;
    }

    /**
     * 获得具体的实体
     *
     * @param applicationid 应用id
     * @return 表application的具体实体
     */
    @Override
    public ApplicationPo getApplication(Integer applicationid) {
        log.debug("getApplication starting...");
        ApplicationPo application;
        try {
            application = applicationMapper.getApplication(applicationid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("getApplication end.");
        return application;
    }

    /**
     * 新增实体
     *
     * @param application 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer addApplication(ApplicationPo application) {
        log.debug("addApplication starting...");
        Integer retval = 1;
        try {
            application.setAppstatus(getAppStatus(application));
            application.setAddtime(BizCache.getInstance().getNow());
            retval = applicationMapper.addApplication(application);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("addApplication end.");
        return retval;
    }

    private Byte getAppStatus(ApplicationPo application) {
        Session session = ActionContext.getConnections().get(application.getHost());
        String message3 = ValidUtils.verifyDevelopPath(session, application.getDeveloppath(),
                application.getDeveloppath().concat(Constants.LINUX_SEPARATOR).concat(Constants.SHELL_VSH));
        if (StringUtils.isNotEmpty(message3)) {
            return Byte.valueOf("3");
        }
        try {
            List<String> res2 = ShellUtils.execCmd(session,
                    "cd " + application.getDeveloppath() + " && " + Constants.SHELL_VSH);
            for (String line : res2) {
                if (line.contains(Constants.RUNNING_STRING)) {
                    return Byte.valueOf("0");
                }
                if (line.contains(Constants.STOP_STRING)) {
                    return Byte.valueOf("1");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Byte.valueOf("3");
    }

    /**
     * 更新实体
     *
     * @param application 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer updateApplication(ApplicationPo application) {
        log.debug("updateApplication starting...");
        Integer retval = 1;
        try {
            application.setAppstatus(getAppStatus(application));

            application.setUpdatetime(BizCache.getInstance().getNow());
            retval = applicationMapper.updateApplication(application);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("updateApplication end.");
        return retval;
    }

    /**
     * 删除指定对象
     *
     * @param applicationid 应用id
     * @return 操作成功数
     */
    @Override
    public Integer deleteApplication(Integer applicationid) {
        log.debug("deleteApplication starting...");
        Integer retval;
        try {
            retval = applicationMapper.deleteApplication(applicationid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("deleteApplication end.");
        return retval;
    }

    @Override
    public String testApplication(ApplicationPo cond) {
        Byte status = getAppStatus(cond);
        if (status.equals(Byte.valueOf("3"))) {
            return "应用异常";
        } else if (status.equals(Byte.valueOf("0"))) {
            return "运行中";
        } else if (status.equals(Byte.valueOf("1"))) {
            return "停止中";
        }
        return "应用异常";
    }
}
