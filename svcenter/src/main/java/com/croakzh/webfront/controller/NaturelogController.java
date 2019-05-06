package com.croakzh.webfront.controller;

import com.croakzh.controller.base.BaseCtrl;
import com.croakzh.controller.model.RSResult;
import com.croakzh.core.context.ActionContext;
import com.croakzh.core.utils.ShellUtils;
import com.croakzh.service.common.BizException;
import com.croakzh.webfront.po.NaturelogPo;
import com.croakzh.webfront.po.ServerPo;
import com.croakzh.webfront.service.INaturelogService;
import com.croakzh.webfront.service.IServerService;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * naturelog
 *
 * @author croakzh
 * @since 2018-12-05
 */
@Controller
@RequestMapping("/naturelog")
@Slf4j
public class NaturelogController extends BaseCtrl {

    /**
     * naturelog表业务层接口
     */
    private final INaturelogService naturelogService;

    private final IServerService serverService;

    @Autowired
    public NaturelogController(INaturelogService naturelogService, IServerService serverService) {
        this.naturelogService = naturelogService;
        this.serverService = serverService;
    }

    /**
     * 查询所有实体对象的数量
     *
     * @param naturelog 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/count")
    @ResponseBody
    public RSResult count(@RequestBody NaturelogPo naturelog) {
        log.info("Start get the count of the table : naturelog, the request is : {}", naturelog);
        RSResult result = new RSResult();
        try {
            int count = naturelogService.countNaturelogs(naturelog);
            result.setResult(0);
            result.setData(count);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get the count of the table : naturelog, success.");
        return result;
    }

    /**
     * 查询所有实体对象
     *
     * @param naturelog 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public RSResult list(@RequestBody NaturelogPo naturelog) {
        log.info("Start get list of the table : naturelog, the request is : {}", naturelog);
        RSResult result = new RSResult();
        try {
            int count = naturelogService.countNaturelogs(naturelog);
            List<NaturelogPo> list = new ArrayList<>();
            if (count > 0) {
                naturelog.setPagestart((naturelog.getCurpage() - 1) * naturelog.getPagesize());
                list = naturelogService.findNaturelogsByPage(naturelog);
            }
            result.setResult(0);
            result.setTotal(count);
            result.setData(list);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Get list of the table : naturelog, success.");
        return result;
    }

    /**
     * 重写对应的查看方法
     * 1. （分批）执行脚本
     * 2. 读取log.txt文件
     * 3. 文件内容入库
     * 4. 文件内容存入data中
     *
     * @param naturelog 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/detail")
    @ResponseBody
    public RSResult info(@RequestBody NaturelogPo naturelog) {
        log.info("Get detail object of the table : naturelog, the request is : {}", naturelog);
        RSResult result = new RSResult();
        try {
            ServerPo server = serverService.getServer(naturelog.getServerid());
            Session session = ActionContext.getConnections().get(server.getHost());
            ShellUtils.execCmd(session, ". zhangshengze.sh");

            // 处理自定义脚本
            String shells = server.getShells();
            if (StringUtils.isNotEmpty(shells)) {
                String[] ushells = shells.split(",");
                for (String shell : ushells) {
                    ShellUtils.execCmd(session, "echo '===========自定义脚本执行==========\n" +
                            "\n用户自定义脚本：" + shell + "，执行结果:' >> log.txt");
                    ShellUtils.execCmd(session, ". " + shell + " >> log.txt");
                }
            }
            List<String> lines = ShellUtils.execCmd(session, "cat log.txt");
            NaturelogPo naturelogPo = new NaturelogPo();
            naturelogPo.setServerid(naturelog.getServerid());
            String detail = String.join("\n", lines);
            naturelog.setDetail(detail);
            naturelogService.addNaturelog(naturelog);
            result.setResult(0);
            result.setData(lines);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Get detail object of the table : naturelog, success.");
        return result;
    }

    /**
     * 新增对象实体
     *
     * @param naturelog 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/new")
    @ResponseBody
    public RSResult add(@RequestBody NaturelogPo naturelog) {
        log.info("Add object to the table : naturelog, the request is : {}", naturelog);
        RSResult result = new RSResult();
        try {
            Integer id = naturelogService.addNaturelog(naturelog);
            result.setResult(0);
            result.setData(id);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Add object to the table : naturelog, success.");
        return result;
    }

    /**
     * 更新对象实体
     *
     * @param naturelog 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public RSResult update(@RequestBody NaturelogPo naturelog) {
        log.info("Update object from the table : naturelog, the request is : {}", naturelog);
        RSResult result = new RSResult();
        try {
            naturelogService.updateNaturelog(naturelog);
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Update object from the table : naturelog, success.");
        return result;
    }

    /**
     * 删除对象实体
     *
     * @param naturelog 请求实体参数
     * @return {@link RSResult} rest返回值
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public RSResult delete(@RequestBody NaturelogPo naturelog) {
        log.info("Delete object from the table : naturelog, the request is : {}", naturelog);
        RSResult result = new RSResult();
        try {
            naturelogService.deleteNaturelog(naturelog.getId());
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription());
        }
        log.info("Delete object from the table : naturelog, success.");
        return result;
    }
}
