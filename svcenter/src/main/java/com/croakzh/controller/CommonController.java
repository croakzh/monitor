package com.croakzh.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * demo编写 => 可以进行Domain的改造
 *
 * @author croakzh
 */
@Controller
@EnableAutoConfiguration
public class CommonController {


    /**
     * / => /login
     *
     * @return
     */
    @RequestMapping("/")
    public String tologin() {
        return "login";
    }

    /**
     * 登录跳转
     *
     * @param name 名字
     * @param pwd  密码
     * @return {@link Map}
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map login(String name, String pwd) {
        Map<String, String> result = new HashMap();
        result.put("success", "a");
        return result;
    }

    /**
     * 主页跳转
     *
     * @return view
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * demo页
     *
     * @return view
     */
    @RequestMapping("/demo")
    public String demo() {
        return "demo";
    }

    /**
     * test页
     *
     * @return view
     */
    @RequestMapping("/test")
    public String test() {
        return "test";
    }




}
