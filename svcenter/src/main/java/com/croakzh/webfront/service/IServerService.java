package com.croakzh.webfront.service;


import com.croakzh.webfront.po.ServerPo;

import java.util.List;

/**
 * Service code for the table : server
 *
 * @author croakzh
 */
public interface IServerService {

    /**
     * 统计实体对象的数量
     *
     * @param server 请求实体参数
     * @return 统计个数
     */
    Integer countServers(ServerPo server);

    /**
     * 获取所有实体的列表
     *
     * @param server 请求实体参数
     * @return 表server的所有的查询列表
     */
    List<ServerPo> findAllServers(ServerPo server);

    /**
     * 获取实体列表
     *
     * @param server 请求实体参数
     * @return 表server的查询列表
     */
    List<ServerPo> findServersByPage(ServerPo server);

    /**
     * 获得具体的实体
     *
     * @param serverid 服务器id
     * @return 表server的具体实体
     */
    ServerPo getServer(Integer serverid);

    /**
     * 新增数据库实例
     *
     * @param server 请求实体参数
     * @return 操作成功数
     */
    Integer addServer(ServerPo server);

    /**
     * 更新数据库实例
     *
     * @param server 请求实体参数
     * @return 操作成功数
     */
    Integer updateServer(ServerPo server);

    /**
     * 删除数据库实体
     *
     * @param serverid 服务器id
     * @return 操作成功数
     */
    Integer deleteServer(Integer serverid);
}
