package com.croakzh.webfront.service;

import com.croakzh.webfront.po.NaturelogPo;

import java.util.List;

/**
 * naturelog
 *
 * @author croakzh
 * @since 2018-12-05
 */
public interface INaturelogService {

    /**
     * 统计实体对象的数量
     *
     * @param naturelog 请求实体参数
     * @return 统计个数
     */
    Integer countNaturelogs(NaturelogPo naturelog);

    /**
     * 获取所有实体的列表
     *
     * @param naturelog 请求实体参数
     * @return 表naturelog的所有的查询列表
     */
    List<NaturelogPo> findAllNaturelogs(NaturelogPo naturelog);

    /**
     * 获取实体列表
     *
     * @param naturelog 请求实体参数
     * @return 表naturelog的查询列表
     */
    List<NaturelogPo> findNaturelogsByPage(NaturelogPo naturelog);

    /**
     * 获得具体的实体
     *
     * @param id 服务器性能记录
     * @return 表naturelog的具体实体
     */
    NaturelogPo getNaturelog(Integer id);

    /**
     * 新增数据库实例
     *
     * @param naturelog 请求实体参数
     * @return 操作成功数
     */
    Integer addNaturelog(NaturelogPo naturelog);

    /**
     * 更新数据库实例
     *
     * @param naturelog 请求实体参数
     * @return 操作成功数
     */
    Integer updateNaturelog(NaturelogPo naturelog);

    /**
     * 删除数据库实体
     *
     * @param id 服务器性能记录
     * @return 操作成功数
     */
    Integer deleteNaturelog(Integer id);
}
