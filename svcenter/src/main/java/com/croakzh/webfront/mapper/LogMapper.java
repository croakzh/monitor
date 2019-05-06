package com.croakzh.webfront.mapper;

import com.croakzh.webfront.po.LogPo;

import java.sql.SQLException;
import java.util.List;

/**
 * Mapper code for the table : log
 *
 * @author croakzh
 * @since 2018-11-23
 */
public interface LogMapper extends ISqlMapper {

    /**
     * 统计实体对象的数量
     *
     * @param log 操作持久层参数实例
     * @return 统计个数
     */
    Integer countLogs(LogPo log) throws SQLException;

    /**
     * 获取所有实体的列表
     *
     * @param log 操作持久层参数实例
     * @return 表log的所有的查询列表
     */
    List<LogPo> findAllLogs(LogPo log) throws SQLException;

    /**
     * 获取实体列表
     *
     * @param log 操作持久层参数实例
     * @return 表log的查询列表
     */
    List<LogPo> findLogsByPage(LogPo log) throws SQLException;

    /**
     * 获得具体的实体
     *
     * @param id 日志存储id
     * @return 表log的具体实体
     */
    LogPo getLog(Integer id) throws SQLException;

    /**
     * 新增数据库实例
     *
     * @param log 操作持久层参数实例
     * @return 操作成功数
     */
    Integer addLog(LogPo log) throws SQLException;

    /**
     * 更新数据库实例
     *
     * @param log 操作持久层参数实例
     * @return 操作成功数
     */
    Integer updateLog(LogPo log) throws SQLException;

    /**
     * 删除数据库实体
     *
     * @param id 日志存储id
     * @return 操作成功数
     */
    Integer deleteLog(Integer id) throws SQLException;
}
