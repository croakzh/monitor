package com.croakzh.webfront.service.impl;

import com.croakzh.service.common.BizCache;
import com.croakzh.service.common.BizErrorCode;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.mapper.GroupMapper;
import com.croakzh.webfront.po.GroupPo;
import com.croakzh.webfront.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ServiceImpl code for the table : group
 *
 * @author croakzh
 */
@Service("groupService")
@Slf4j
public class GroupServiceImpl implements IGroupService {

    /**
     * group表持久层接口
     */
    @Autowired
    @Qualifier("groupMapper")
    private GroupMapper groupMapper;

    /**
     * 统计实体对象的数量
     *
     * @param group 请求实体参数
     * @return 统计个数
     */
    @Override
    public Integer countGroups(GroupPo group) {
        log.debug("countGroups starting...");
        int count;
        try {
            count = groupMapper.countGroups(group);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("countGroups end.");
        return count;
    }

    /**
     * 统计实体对象的数量
     *
     * @param group 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<GroupPo> findAllGroups(GroupPo group) {
        log.debug("findAllGroups starting...");
        List<GroupPo> groups;
        try {
            groups = groupMapper.findAllGroups(group);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findAllGroups end.");
        return groups;
    }

    /**
     * 统计实体对象的数量
     *
     * @param group 请求实体参数
     * @return 统计个数
     */
    @Override
    public List<GroupPo> findGroupsByPage(GroupPo group) {
        log.debug("findGroupsByPage starting...");
        List<GroupPo> groups;
        try {
            groups = groupMapper.findGroupsByPage(group);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("findGroupsByPage end.");
        return groups;
    }

    /**
     * 获得具体的实体
     *
     * @param groupid 权限组id
     * @return 表group的具体实体
     */
    @Override
    public GroupPo getGroup(Integer groupid) {
        log.debug("getGroup starting...");
        GroupPo group;
        try {
            group = groupMapper.getGroup(groupid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("getGroup end.");
        return group;
    }

    /**
     * 新增实体
     *
     * @param group 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer addGroup(GroupPo group) {
        log.debug("addGroup starting...");
        Integer retval;
        try {
            group.setAddtime(BizCache.getInstance().getNow());
            retval = groupMapper.addGroup(group);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("addGroup end.");
        return retval;
    }

    /**
     * 更新实体
     *
     * @param group 请求实体参数
     * @return 操作成功数
     */
    @Override
    public Integer updateGroup(GroupPo group) {
        log.debug("updateGroup starting...");
        Integer retval;
        try {
            group.setUpdatetime(BizCache.getInstance().getNow());
            retval = groupMapper.updateGroup(group);
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("updateGroup end.");
        return retval;
    }

    /**
     * 删除指定对象
     *
     * @param groupid 权限组id
     * @return 操作成功数
     */
    @Override
    public Integer deleteGroup(Integer groupid) {
        log.debug("deleteGroup starting...");
        Integer retval;
        try {
            retval = groupMapper.deleteGroup(groupid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_TRANSACTION_FAIL);
        }
        log.debug("deleteGroup end.");
        return retval;
    }
}
