package com.croakzh.webfront.service.impl;

import com.croakzh.service.common.BizCache;
import com.croakzh.service.common.BizErrorCode;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.mapper.NaturelogMapper;
import com.croakzh.webfront.po.NaturelogPo;
import com.croakzh.webfront.service.INaturelogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * naturelog
 *
 * @author croakzh
 * @since 2018-12-05
 */
@Service("naturelogService")
@Slf4j
public class NaturelogServiceImpl implements INaturelogService {

    /**
     * naturelog表持久层接口
     */
    @Autowired
    @Qualifier("naturelogMapper")
    private NaturelogMapper naturelogMapper;

    /**
     * 统计实体对象的数量
     *
     * @param naturelog 请求实体参数
     * @return 统计个数
     */
    @Override
    public Integer countNaturelogs(NaturelogPo naturelog) {
        log.debug("countNaturelogs starting...");
        int count;
        try {
            count = naturelogMapper.countNaturelogs(naturelog);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("countNaturelogs end.");
        return count;
    }

    /**
     * 统计实体对象的数量
     *
     * @param naturelog 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<NaturelogPo> findAllNaturelogs(NaturelogPo naturelog) {
        log.debug("findAllNaturelogs starting...");
        List<NaturelogPo> naturelogs;
        try {
            naturelogs = naturelogMapper.findAllNaturelogs(naturelog);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findAllNaturelogs end.");
        return naturelogs;
    }

    /**
     * 统计实体对象的数量
     *
     * @param naturelog 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<NaturelogPo> findNaturelogsByPage(NaturelogPo naturelog) {
        log.debug("findNaturelogsByPage starting...");
        List<NaturelogPo> naturelogs;
        try {
            naturelogs = naturelogMapper.findNaturelogsByPage(naturelog);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findNaturelogsByPage end.");
        return naturelogs;
    }

    /**
     * 获得具体的实体
     *
     * @param id 服务器性能记录
     * @return 表naturelog的具体实体
     */
    @Override
    public NaturelogPo getNaturelog(Integer id) {
        log.debug("getNaturelog starting...");
        NaturelogPo naturelog;
        try {
            naturelog = naturelogMapper.getNaturelog(id);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("getNaturelog end.");
        return naturelog;
    }

    /**
     * 新增实体
     *
     * @param naturelog 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer addNaturelog(NaturelogPo naturelog) {
        log.debug("addNaturelog starting...");
        Integer retval;
        try {
            naturelog.setAddtime(BizCache.getInstance().getNow());
            retval = naturelogMapper.addNaturelog(naturelog);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("addNaturelog end.");
        return retval;
    }

    /**
     * 更新实体
     *
     * @param naturelog 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer updateNaturelog(NaturelogPo naturelog) {
        log.debug("updateNaturelog starting...");
        Integer retval;
        try {
            retval = naturelogMapper.updateNaturelog(naturelog);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("updateNaturelog end.");
        return retval;
    }

    /**
     * 删除指定对象
     *
     * @param id 服务器性能记录
     * @return 操作成功数
     */
    @Override
    public Integer deleteNaturelog(Integer id) {
        log.debug("deleteNaturelog starting...");
        Integer retval;
        try {
            retval = naturelogMapper.deleteNaturelog(id);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("deleteNaturelog end.");
        return retval;
    }
}
