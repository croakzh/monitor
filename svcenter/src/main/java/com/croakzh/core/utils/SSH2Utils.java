package com.croakzh.core.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author croakzh
 */
public class SSH2Utils {

    public static Connection getOpenedConnection(String host, Integer port, String username, String password) throws IOException {

        Connection conn = new Connection(host, port);
        conn.connect(); // make sure the connection is opened
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);

        if (!isAuthenticated) {
            throw new IOException("Authentication failed.");
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = SSH2Utils.getOpenedConnection("47.99.37.210", 22, "root", "Tc5Xa0Sk");

            if (null != conn) {
                System.out.println("连接服务器成功！");
            }
            String string = shellScript("sh /root/quickworkbackend/generatortool/a.sh &", conn);
            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    public static String shellScript(String cmd, Connection connection) {
        Session sess = null;
        InputStream stdout = null;
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer("exec result:");
        buffer.append(System.getProperty("line.separator"));
        try {
            sess = connection.openSession();
            sess.execCommand(cmd);
            stdout = new StreamGobbler(sess.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                buffer.append(line);
                buffer.append(System.getProperty("line.separator"));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            sess.close();
        }
        return buffer.toString();
    }

}
