package com.croakzh.webfront.service;


import com.croakzh.webfront.po.GroupPo;

import java.util.List;

/**
 * Service code for the table : group
 *
 * @author croakzh
 */
public interface IGroupService {

	/**
	 * 统计实体对象的数量
	 *
	 * @param group 请求实体参数
	 * @return 统计个数
	 */
	Integer countGroups(GroupPo group);

	/**
	 * 获取所有实体的列表
	 *
	 * @param group 请求实体参数
	 * @return 表group的所有的查询列表
	 */
	List<GroupPo> findAllGroups(GroupPo group);

	/**
	 * 获取实体列表
	 *
	 * @param group 请求实体参数
	 * @return 表group的查询列表
	 */
	List<GroupPo> findGroupsByPage(GroupPo group);

	/**
	 * 获得具体的实体
	 *
	 * @param groupid 权限组id
	 * @return 表group的具体实体
	 */
	GroupPo getGroup(Integer groupid);

	/**
	 * 新增数据库实例
	 *
	 * @param group 请求实体参数
	 * @return 操作成功数
	 */
	Integer addGroup(GroupPo group);

	/**
	 * 更新数据库实例
	 *
	 * @param group 请求实体参数
	 * @return 操作成功数
	 */
	Integer updateGroup(GroupPo group);

	/**
	 * 删除数据库实体
	 *
	 * @param groupid 权限组id
	 * @return 操作成功数
	 */
	Integer deleteGroup(Integer groupid);
}
