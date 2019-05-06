package com.croakzh.webfront.controller;

import com.croakzh.controller.base.BaseCtrl;
import com.croakzh.controller.model.RSResult;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.po.LogPo;
import com.croakzh.webfront.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller code for the table : log
 *
 * @author croakzh
 * @since 2018-11-23
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseCtrl {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    /**
     * log表业务层接口
     */
    private final ILogService logService;

    @Autowired
    public LogController(ILogService logService) {
        this.logService = logService;
    }

    /**
     * 查询所有实体对象的数量
     *
     * @param log 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/count")
    @ResponseBody
    public RSResult count(@RequestBody LogPo log) {
        logger.info("Start get the count of the table : log, the request is : {}", log);
        RSResult result = new RSResult();
        try {
            int count = logService.countLogs(log);
            result.setResult(0);
            result.setData(count);
        } catch (BizException ex) {
            logger.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        logger.info("Get the count of the table : log, success.");
        return result;
    }

    /**
     * 查询所有实体对象
     *
     * @param log 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public RSResult list(@RequestBody LogPo log) {
        logger.info("Start get list of the table : log, the request is : {}", log);
        RSResult result = new RSResult();
        try {
            int count = logService.countLogs(log);
            List<LogPo> list = new ArrayList<>();
            if (count > 0) {
                log.setPagestart((log.getCurpage() - 1) * log.getPagesize());
                list = logService.findLogsByPage(log);
            }
            result.setResult(0);
            result.setTotal(count);
            result.setData(list);
        } catch (BizException ex) {
            logger.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        logger.info("Get list of the table : log, success.");
        return result;
    }

    /**
     * 查看具体的实体
     *
     * @param log 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/detail")
    @ResponseBody
    public RSResult info(@RequestBody LogPo log) {
        logger.info("Get detail object of the table : log, the request is : {}", log);
        RSResult result = new RSResult();
        try {
            LogPo obj = logService.getLog(log.getId());
            result.setResult(0);
            result.setData(obj);
        } catch (BizException ex) {
            logger.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        logger.info("Get detail object of the table : log, success.");
        return result;
    }

    /**
     * 新增对象实体
     *
     * @param log 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/new")
    @ResponseBody
    public RSResult add(@RequestBody LogPo log) {
        logger.info("Add object to the table : log, the request is : {}", log);
        RSResult result = new RSResult();
        try {
            Integer id = logService.addLog(log);
            result.setResult(0);
            result.setData(id);
        } catch (BizException ex) {
            logger.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        logger.info("Add object to the table : log, success.");
        return result;
    }

    /**
     * 更新对象实体
     *
     * @param log 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public RSResult update(@RequestBody LogPo log) {
        logger.info("Update object from the table : log, the request is : {}", log);
        RSResult result = new RSResult();
        try {
            logService.updateLog(log);
            result.setResult(0);
        } catch (BizException ex) {
            logger.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        logger.info("Update object from the table : log, success.");
        return result;
    }

    /**
     * 删除对象实体
     *
     * @param log 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public RSResult delete(@RequestBody LogPo log) {
        logger.info("Delete object from the table : log, the request is : {}", log);
        RSResult result = new RSResult();
        try {
            logService.deleteLog(log.getId());
            result.setResult(0);
        } catch (BizException ex) {
            logger.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        logger.info("Delete object from the table : log, success.");
        return result;
    }
}
