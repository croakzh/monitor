package com.croakzh.webfront.controller;

import com.croakzh.controller.base.BaseCtrl;
import com.croakzh.controller.model.RSResult;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.po.GroupPo;
import com.croakzh.webfront.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器组控制层
 *
 * @author croakzh
 * @since 2018-11-23
 */
@Controller
@RequestMapping("/group")
@Slf4j
public class GroupController extends BaseCtrl {

    /**
     * group表业务层接口
     */
    private final IGroupService groupService;

    @Autowired
    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 查询所有实体对象的数量
     *
     * @param group 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/count")
    @ResponseBody
    public RSResult count(@RequestBody GroupPo group) {
        log.info("Start get the count of the table : group, the request is : {}", group);
        RSResult result = new RSResult();
        try {
            int count = groupService.countGroups(group);
            result.setResult(0);
            result.setData(count);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get the count of the table : group, success.");
        return result;
    }

    /**
     * 查询所有实体对象
     *
     * @param group 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public RSResult list(@RequestBody GroupPo group) {
        log.info("Start get list of the table : group, the request is : {}", group);
        RSResult result = new RSResult();
        try {
            int count = groupService.countGroups(group);
            List<GroupPo> list = new ArrayList<>();
            if (count > 0) {
                group.setPagestart((group.getCurpage() - 1) * group.getPagesize());
                list = groupService.findGroupsByPage(group);
            }
            result.setResult(0);
            result.setTotal(count);
            result.setData(list);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get list of the table : group, success.");
        return result;
    }

    /**
     * 查看具体的实体
     *
     * @param group 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/detail")
    @ResponseBody
    public RSResult info(@RequestBody GroupPo group) {
        log.info("Get detail object of the table : group, the request is : {}", group);
        RSResult result = new RSResult();
        try {
            GroupPo obj = groupService.getGroup(group.getGroupid());
            result.setResult(0);
            result.setData(obj);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get detail object of the table : group, success.");
        return result;
    }

    /**
     * 新增对象实体
     *
     * @param group 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/new")
    @ResponseBody
    public RSResult add(@RequestBody GroupPo group) {
        log.info("Add object to the table : group, the request is : {}", group);
        RSResult result = new RSResult();
        try {
            Integer id = groupService.addGroup(group);
            result.setResult(0);
            result.setData(id);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Add object to the table : group, success.");
        return result;
    }

    /**
     * 更新对象实体
     *
     * @param group 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public RSResult update(@RequestBody GroupPo group) {
        log.info("Update object from the table : group, the request is : {}", group);
        RSResult result = new RSResult();
        try {
            groupService.updateGroup(group);
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Update object from the table : group, success.");
        return result;
    }

    /**
     * 删除对象实体
     *
     * @param group 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public RSResult delete(@RequestBody GroupPo group) {
        log.info("Delete object from the table : group, the request is : {}", group);
        RSResult result = new RSResult();
        try {
            groupService.deleteGroup(group.getGroupid());
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Delete object from the table : group, success.");
        return result;
    }
}
