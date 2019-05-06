package com.croakzh.webfront.service.impl;

import com.croakzh.service.common.BizCache;
import com.croakzh.service.common.BizErrorCode;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.mapper.ServerMapper;
import com.croakzh.webfront.po.ServerPo;
import com.croakzh.webfront.service.IServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ServiceImpl code for the table : server
 *
 * @author croakzh
 */
@Service("serverService")
@Slf4j
public class ServerServiceImpl implements IServerService {

    /**
     * server表持久层接口
     */
    @Autowired
    @Qualifier("serverMapper")
    private ServerMapper serverMapper;

    /**
     * 统计实体对象的数量
     *
     * @param server 请求实体参数
     * @return 统计个数
     */
    @Override
    public Integer countServers(ServerPo server) {
        log.debug("countServers starting...");
        int count;
        try {
            count = serverMapper.countServers(server);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("countServers end.");
        return count;
    }

    /**
     * 统计实体对象的数量
     *
     * @param server 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<ServerPo> findAllServers(ServerPo server) {
        log.debug("findAllServers starting...");
        List<ServerPo> servers;
        try {
            servers = serverMapper.findAllServers(server);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findAllServers end.");
        return servers;
    }

    /**
     * 统计实体对象的数量
     *
     * @param server 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<ServerPo> findServersByPage(ServerPo server) {
        log.debug("findServersByPage starting...");
        List<ServerPo> servers;
        try {
            servers = serverMapper.findServersByPage(server);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findServersByPage end.");
        return servers;
    }

    /**
     * 获得具体的实体
     *
     * @param serverid 服务器id
     * @return 表server的具体实体
     */
    @Override
    public ServerPo getServer(Integer serverid) {
        log.debug("getServer starting...");
        ServerPo server;
        try {
            server = serverMapper.getServer(serverid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("getServer end.");
        return server;
    }

    /**
     * 新增实体
     *
     * @param server 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer addServer(ServerPo server) {
        log.debug("addServer starting...");
        Integer retval;
        try {
            server.setAddtime(BizCache.getInstance().getNow());
            retval = serverMapper.addServer(server);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("addServer end.");
        return retval;
    }

    /**
     * 更新实体
     *
     * @param server 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer updateServer(ServerPo server) {
        log.debug("updateServer starting...");
        Integer retval;
        try {
            server.setUpdatetime(BizCache.getInstance().getNow());
            retval = serverMapper.updateServer(server);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("updateServer end.");
        return retval;
    }

    /**
     * 删除指定对象
     *
     * @param serverid 服务器id
     * @return 操作成功数
     */
    @Override
    public Integer deleteServer(Integer serverid) {
        log.debug("deleteServer starting...");
        Integer retval;
        try {
            retval = serverMapper.deleteServer(serverid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("deleteServer end.");
        return retval;
    }
}
