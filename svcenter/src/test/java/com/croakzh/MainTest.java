package com.croakzh;

import com.croakzh.webfront.po.LogPo;
import org.junit.Test;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 简单的测试方法
 *
 * @author croakzh
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now1 = LocalDateTime.now();
        String time = now1.format(formatter);
        System.out.println(time);
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void test02() {
        List<LogPo> logs = Arrays.asList(new LogPo(1, "1", 1, 1, "1", "1", "1", "1"),
                new LogPo(1, "1", 1, 1, "2", "1", "1", "1"),
                new LogPo(1, "1", 1, 1, "3", "1", "1", "1"),
                new LogPo(1, "1", 1, 1, "4", "1", "1", "1"),
                new LogPo(1, "1", 1, 1, "5", "1", "1", "1"));
        List<String> collect = logs.stream().map((LogPo::getLogtime)).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println(collect.contains("1"));
    }

    @Test
    public void test01() {
        String str = "[18:15:32:546] [ERROR] - org.apache.juli.logging.DirectJDKLog.log(DirectJDKLog.java:182) - Servlet.service() for servlet [dispatcherServlet] in context with path [/bugonline] threw exception [Request processing failed; nested exception is org.springframework.dao.DataIntegrityViolationException: \n";
        String input = "[18:1:32" +
                ":546] [ERROR] ";
        Pattern pattern = Pattern.compile("\\[\\d{2}:\\d{2}:\\d{2}:\\d{3}\\] \\[ERROR\\][\\s\\S]*");
        boolean matches = pattern.matcher(str).matches();
        System.out.println(matches);
        System.out.println(str.substring(0, str.indexOf("]") + 1).trim());


        System.out.println(Pattern.compile("\\[\\d{2}:\\d{2}:\\d{2}:\\d{3}\\] \\[ERROR\\][\\s\\S]*").matcher(input).matches());

    }

}
