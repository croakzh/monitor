package com.croakzh.webfront.service;


import com.croakzh.webfront.po.ApplicationPo;

import java.util.List;

/**
 * Service code for the table : application
 *
 * @author croakzh
 */
public interface IApplicationService {

    /**
     * 统计实体对象的数量
     *
     * @param application 请求实体参数
     * @return 统计个数
     */
    Integer countApplications(ApplicationPo application);

    /**
     * 获取所有实体的列表
     *
     * @param application 请求实体参数
     * @return 表application的所有的查询列表
     */
    List<ApplicationPo> findAllApplications(ApplicationPo application);

    /**
     * 获取实体列表
     *
     * @param application 请求实体参数
     * @return 表application的查询列表
     */
    List<ApplicationPo> findApplicationsByPage(ApplicationPo application);

    /**
     * 获得具体的实体
     *
     * @param applicationid 应用id
     * @return 表application的具体实体
     */
    ApplicationPo getApplication(Integer applicationid);

    /**
     * 新增数据库实例
     *
     * @param application 请求实体参数
     * @return 操作成功数
     */
    Integer addApplication(ApplicationPo application);

    /**
     * 更新数据库实例
     *
     * @param application 请求实体参数
     * @return 操作成功数
     */
    Integer updateApplication(ApplicationPo application);

    /**
     * 删除数据库实体
     *
     * @param applicationid 应用id
     * @return 操作成功数
     */
    Integer deleteApplication(Integer applicationid);

    String testApplication(ApplicationPo cond);
}
