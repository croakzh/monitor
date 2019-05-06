package com.croakzh.webfront.mapper;

import com.croakzh.webfront.po.GroupPo;

import java.sql.SQLException;
import java.util.List;

/**
 * Mapper code for the table : group
 *
 * @author croakzh
 * @since 2018-11-23
 */
public interface GroupMapper extends ISqlMapper {

    /**
     * 统计实体对象的数量
     *
     * @param group 操作持久层参数实例
     * @return 统计个数
     */
    Integer countGroups(GroupPo group) throws SQLException;

    /**
     * 获取所有实体的列表
     *
     * @param group 操作持久层参数实例
     * @return 表group的所有的查询列表
     */
    List<GroupPo> findAllGroups(GroupPo group) throws SQLException;

    /**
     * 获取实体列表
     *
     * @param group 操作持久层参数实例
     * @return 表group的查询列表
     */
    List<GroupPo> findGroupsByPage(GroupPo group) throws SQLException;

    /**
     * 获得具体的实体
     *
     * @param groupid 权限组id
     * @return 表group的具体实体
     */
    GroupPo getGroup(Integer groupid) throws SQLException;

    /**
     * 新增数据库实例
     *
     * @param group 操作持久层参数实例
     * @return 操作成功数
     */
    Integer addGroup(GroupPo group) throws SQLException;

    /**
     * 更新数据库实例
     *
     * @param group 操作持久层参数实例
     * @return 操作成功数
     */
    Integer updateGroup(GroupPo group) throws SQLException;

    /**
     * 删除数据库实体
     *
     * @param groupid 权限组id
     * @return 操作成功数
     */
    Integer deleteGroup(Integer groupid) throws SQLException;
}
