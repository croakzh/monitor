package com.croakzh.webfront.service;


import com.croakzh.webfront.po.LogPo;

import java.util.List;

/**
 * Service code for the table : log
 *
 * @author croakzh
 */
public interface ILogService {

	/**
	 * 统计实体对象的数量
	 *
	 * @param log 请求实体参数
	 * @return 统计个数
	 */
	Integer countLogs(LogPo log);

	/**
	 * 获取所有实体的列表
	 *
	 * @param log 请求实体参数
	 * @return 表log的所有的查询列表
	 */
	List<LogPo> findAllLogs(LogPo log);

	/**
	 * 获取实体列表
	 *
	 * @param log 请求实体参数
	 * @return 表log的查询列表
	 */
	List<LogPo> findLogsByPage(LogPo log);

	/**
	 * 获得具体的实体
	 *
	 * @param id 日志存储id
	 * @return 表log的具体实体
	 */
	LogPo getLog(Integer id);

	/**
	 * 新增数据库实例
	 *
	 * @param log 请求实体参数
	 * @return 操作成功数
	 */
	Integer addLog(LogPo log);

	/**
	 * 更新数据库实例
	 *
	 * @param log 请求实体参数
	 * @return 操作成功数
	 */
	Integer updateLog(LogPo log);

	/**
	 * 删除数据库实体
	 *
	 * @param id 日志存储id
	 * @return 操作成功数
	 */
	Integer deleteLog(Integer id);
}
