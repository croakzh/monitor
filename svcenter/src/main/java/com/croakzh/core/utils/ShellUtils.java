package com.croakzh.core.utils;

import com.croakzh.core.Constants;
import com.croakzh.core.connection.ServerConnection;
import com.croakzh.core.context.ActionContext;
import com.jcraft.jsch.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author corakzh
 */
public class ShellUtils {

    /**
     * 执行单条cmd指令
     *
     * @param session {@link Session}连接
     * @param command cmd指令
     * @return 结果串
     * @throws Exception 异常
     */
    public static List<String> execCmd(Session session, String command) throws Exception {
        BufferedReader reader;
        Channel channel;
        ArrayList<String> list = new ArrayList<>();

        channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        channel.connect();
        InputStream inputStream = channel.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(Constants.CHARSET_UTF8)));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        channel.disconnect();
        return list;
    }

    /**
     * 执行shell？
     *
     * @param session        会话
     * @param cmd            cmd指令
     * @param outputFileName 内容输出文件位置
     * @return 执行时长
     * @throws Exception 异常
     */
    public static long shell(Session session, String cmd, String outputFileName)
            throws Exception {
        long start = System.currentTimeMillis();
        ChannelShell channelShell = (ChannelShell) session.openChannel("shell");
        PipedInputStream pipeIn = new PipedInputStream();
        PipedOutputStream pipeOut = new PipedOutputStream(pipeIn);
        FileOutputStream fileOut = new FileOutputStream(outputFileName);
        channelShell.setInputStream(pipeIn);
        channelShell.setOutputStream(fileOut);
        channelShell.connect(Constants.DEFAULT_TIMEOUT);
        pipeOut.write(cmd.getBytes());
        Thread.sleep(Constants.INTERVAL);
        pipeOut.close();
        pipeIn.close();
        fileOut.close();
        channelShell.disconnect();
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        try {
            Session session = new ServerConnection("47.99.37.210", 22, "root", "Tc5Xa0Sk").getSession();
            uploadFile(session, "/Users/croakzh/Documents/git/github/svcenter/upload/20181206110933-monitortool.war",
                    "/root/quickworkbackend/20181206110933-monitortool.war");
////            List<String> list = execCmd(session, ". /root/quickworkbackend/generatortool/a.sh");
//            List<String> list = execCmd(session, "cat log.txt");
//            list.forEach(System.out::println);
//            session.disconnect();
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param session {@link Session}连接
     * @param local   本地文件地址
     * @param remote  服务器文件地址
     * @throws Exception 异常
     */
    public static void uploadFile(Session session, String local, String remote) throws Exception {
        ChannelSftp channel = null;
        InputStream inputStream = null;

        try {
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect(Constants.DEFAULT_TIMEOUT);
            inputStream = new FileInputStream(new File(local));
            channel.setInputStream(inputStream);
            channel.put(inputStream, remote);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    /**
     * 判断文件是否存在
     *
     * @param session  会话连接
     * @param filePath 文件路径
     * @return true/false
     * @throws Exception 异常
     */
    public static Boolean exists(Session session, String filePath) {
        ChannelSftp sftp;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(Constants.DEFAULT_TIMEOUT);
            sftp.ls(filePath);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

//    /**
//     * 下载文件
//     *
//     * @param session {@link Session}连接
//     * @param downloadFile 需下载的文件路径
//     * @param saveFile 保存的本地文件路径
//     */
//    public void downloadFile(Session session, String downloadFile, String saveFile) {
//        if(StringUtils.isNotEmpty(downloadFile)) {
//
//        }
//    }

}
