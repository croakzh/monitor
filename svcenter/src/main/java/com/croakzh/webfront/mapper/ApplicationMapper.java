package com.croakzh.webfront.mapper;


import com.croakzh.webfront.po.ApplicationPo;

import java.sql.SQLException;
import java.util.List;

/**
 * Mapper code for the table : application
 *
 * @author croakzh
 * @since 2018-11-23
 */
public interface ApplicationMapper extends ISqlMapper {

    /**
     * 统计实体对象的数量
     *
     * @param application 操作持久层参数实例
     * @return 统计个数
     */
    Integer countApplications(ApplicationPo application) throws SQLException;

    /**
     * 获取所有实体的列表
     *
     * @param application 操作持久层参数实例
     * @return 表application的所有的查询列表
     */
    List<ApplicationPo> findAllApplications(ApplicationPo application) throws SQLException;

    /**
     * 获取实体列表
     *
     * @param application 操作持久层参数实例
     * @return 表application的查询列表
     */
    List<ApplicationPo> findApplicationsByPage(ApplicationPo application) throws SQLException;

    /**
     * 获得具体的实体
     *
     * @param applicationid 应用id
     * @return 表application的具体实体
     */
    ApplicationPo getApplication(Integer applicationid) throws SQLException;

    /**
     * 新增数据库实例
     *
     * @param application 操作持久层参数实例
     * @return 操作成功数
     */
    Integer addApplication(ApplicationPo application) throws SQLException;

    /**
     * 更新数据库实例
     *
     * @param application 操作持久层参数实例
     * @return 操作成功数
     */
    Integer updateApplication(ApplicationPo application) throws SQLException;

    /**
     * 删除数据库实体
     *
     * @param applicationid 应用id
     * @return 操作成功数
     */
    Integer deleteApplication(Integer applicationid) throws SQLException;
}
