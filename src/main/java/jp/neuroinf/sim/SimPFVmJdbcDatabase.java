package jp.neuroinf.sim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

abstract public class SimPFVmJdbcDatabase extends SimPFVmDatabase {

    private String driver;
    private String url;
    private String username;
    private String password;

    protected void initilizeConnection(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public SimPFVmNode getVmNode(String session_id) throws SimPFException {
        SimPFVmNode vm = null;
        if (session_id.equals("")) {
            throw new SimPFException("Invalid Sesion ID: (empty)");
        }
        try {
            boolean success = false;
            String protocol = null;
            String hostname = null;
            int port = 0;
            String password = null;
            Class.forName(this.driver).newInstance();
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            // get basic information
            String sql = "SELECT hostname, protocol, port, password FROM connection WHERE sid=? AND status='READY'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, session_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                hostname = rs.getString(1);
                protocol = rs.getString(2);
                port = rs.getInt(3);
                password = rs.getString(4);
                success = !rs.next();
            }
            rs.close();
            pstmt.close();
            if (!success) {
                throw new SimPFException("Invalid Sesion ID: " + session_id);
            }
            vm = new SimPFVmNode(hostname, protocol, port, password);
            // get specific parameters
            sql = "SELECT name, value FROM parameter WHERE sid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, session_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                String value = rs.getString(2);
                vm.setParameter(name, value);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            throw new SimPFException(e);
        }
        return vm;
    }
}
