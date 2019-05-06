package com.croakzh.webfront.service.impl;

import com.croakzh.service.common.BizErrorCode;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.mapper.LogMapper;
import com.croakzh.webfront.po.LogPo;
import com.croakzh.webfront.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ServiceImpl code for the table : log
 *
 * @author croakzh
 */
@Service("logService")
public class LogServiceImpl implements ILogService {

    private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    /**
     * log表持久层接口
     */
    @Autowired
    @Qualifier("logMapper")
    private LogMapper logMapper;

    /**
     * 统计实体对象的数量
     *
     * @param log 请求实体参数
     * @return 统计个数
     */
    @Override
    public Integer countLogs(LogPo log) {
        logger.debug("countLogs starting...");
        int count;
        try {
            count = logMapper.countLogs(log);
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("countLogs end.");
        return count;
    }

    /**
     * 统计实体对象的数量
     *
     * @param log 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<LogPo> findAllLogs(LogPo log) {
        logger.debug("findAllLogs starting...");
        List<LogPo> logs;
        try {
            logs = logMapper.findAllLogs(log);
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("findAllLogs end.");
        return logs;
    }

    /**
     * 统计实体对象的数量
     *
     * @param log 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<LogPo> findLogsByPage(LogPo log) {
        logger.debug("findLogsByPage starting...");
        List<LogPo> logs;
        try {
            logs = logMapper.findLogsByPage(log);
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("findLogsByPage end.");
        return logs;
    }

    /**
     * 获得具体的实体
     *
     * @param id 日志存储id
     * @return 表log的具体实体
     */
    @Override
    public LogPo getLog(Integer id) {
        logger.debug("getLog starting...");
        LogPo log;
        try {
            log = logMapper.getLog(id);
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("getLog end.");
        return log;
    }

    /**
     * 新增实体
     *
     * @param log 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer addLog(LogPo log) {
        logger.debug("addLog starting...");
        Integer retval;
        try {
            retval = logMapper.addLog(log);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("addLog end.");
        return retval;
    }

    /**
     * 更新实体
     *
     * @param log 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer updateLog(LogPo log) {
        logger.debug("updateLog starting...");
        Integer retval;
        try {
            retval = logMapper.updateLog(log);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("updateLog end.");
        return retval;
    }

    /**
     * 删除指定对象
     *
     * @param id 日志存储id
     * @return 操作成功数
     */
    @Override
    public Integer deleteLog(Integer id) {
        logger.debug("deleteLog starting...");
        Integer retval;
        try {
            retval = logMapper.deleteLog(id);
        } catch (SQLException ex) {
            logger.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        logger.debug("deleteLog end.");
        return retval;
    }
}
