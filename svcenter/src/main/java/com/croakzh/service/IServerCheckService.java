package com.croakzh.service;

/**
 * 服务器检查
 *
 * @author croakzh
 */
public interface IServerCheckService {

    /**
     * 更新服务器状态
     */
    void updateServersStatus(Integer serverid);

    /**
     * 更新app应用状态
     */
    void updateApplicationsStatus(Integer applicationid);

}
