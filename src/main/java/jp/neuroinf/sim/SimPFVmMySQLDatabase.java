package jp.neuroinf.sim;

import org.apache.guacamole.environment.Environment;
import org.apache.guacamole.environment.LocalEnvironment;

public class SimPFVmMySQLDatabase extends SimPFVmJdbcDatabase {

    public SimPFVmMySQLDatabase() throws SimPFException {
        try {
            Environment environment = LocalEnvironment.getInstance();
            String hostname = environment.getRequiredProperty(SimPFProperties.MYSQL_HOSTNAME);
            String port = environment.getRequiredProperty(SimPFProperties.MYSQL_PORT);
            String database = environment.getRequiredProperty(SimPFProperties.MYSQL_DATABASE);
            String username = environment.getRequiredProperty(SimPFProperties.MYSQL_USERNAME);
            String password = environment.getRequiredProperty(SimPFProperties.MYSQL_PASSWORD);
            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
            initilizeConnection("com.mysql.jdbc.Driver", url, username, password);
        } catch (Exception e) {
            throw new SimPFException(e);
        }
    }
}