package com.croakzh.core.connection;

import com.croakzh.core.Constants;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

/**
 * @author croakzh
 */
public class ServerConnection {

    private String host;
    private Integer port;
    private String username;
    private String password;

    public ServerConnection(String host, String username, String password) {
        this(host, Constants.SFTP_PORT, username, password);
    }

    public ServerConnection(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    private Session session;

    public Session getSession() throws JSchException {
        if (session == null) {
            establish();
        }
        return session;
    }

    private void establish() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(this.username, this.host, this.port);
        session.setPassword(this.password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setTimeout(Constants.DEFAULT_TIMEOUT);
        session.connect();

    }

}
