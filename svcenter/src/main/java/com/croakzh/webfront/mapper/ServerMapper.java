package com.croakzh.webfront.mapper;

import com.croakzh.webfront.po.ServerPo;

import java.sql.SQLException;
import java.util.List;

/**
 * Mapper code for the table : server
 *
 * @author croakzh
 * @since 2018-11-23
 */
public interface ServerMapper extends ISqlMapper {

	/**
	 * 统计实体对象的数量
	 *
	 * @param server 操作持久层参数实例
	 * @return 统计个数
	 */
	Integer countServers(ServerPo server) throws SQLException;

	/**
	 * 获取所有实体的列表
	 *
	 * @param server 操作持久层参数实例
	 * @return 表server的所有的查询列表
	 */
	List<ServerPo> findAllServers(ServerPo server) throws SQLException;

	/**
	 * 获取实体列表
	 *
	 * @param server 操作持久层参数实例
	 * @return 表server的查询列表
	 */
	List<ServerPo> findServersByPage(ServerPo server) throws SQLException;

	/**
	 * 获得具体的实体
	 *
	 * @param serverid 服务器id
	 * @return 表server的具体实体
	 */
	ServerPo getServer(Integer serverid) throws SQLException;

	/**
	 * 新增数据库实例
	 *
	 * @param server 操作持久层参数实例
	 * @return 操作成功数
	 */
	Integer addServer(ServerPo server) throws SQLException;

	/**
	 * 更新数据库实例
	 *
	 * @param server 操作持久层参数实例
	 * @return 操作成功数
	 */
	Integer updateServer(ServerPo server) throws SQLException;

	/**
	 * 删除数据库实体
	 *
	 * @param serverid 服务器id
	 * @return 操作成功数
	 */
	Integer deleteServer(Integer serverid) throws SQLException;
}
