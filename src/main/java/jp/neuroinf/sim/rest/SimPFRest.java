package jp.neuroinf.sim.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.environment.Environment;
import org.apache.guacamole.environment.LocalEnvironment;

import jp.neuroinf.sim.SimPFProperties;

@Produces(MediaType.APPLICATION_JSON)
public class SimPFRest {

    private final String hostname;
    private final String pingCommand;

    public SimPFRest(String host) throws GuacamoleException {
        Environment environment = new LocalEnvironment();
        this.hostname = host;
        this.pingCommand = environment.getRequiredProperty(SimPFProperties.PING_COMMAND);
    }

    @GET
    @Path("ping")
    public Map<String, String> ping() {
        final Map<String, String> ret = new HashMap<>();
        boolean response = pingCommand();
        ret.put("host", this.hostname);
        ret.put("result", response ? "SUCCESS" : "FAILED");
        return ret;
    }

    private boolean pingCommand() {
        try {
            String cmd = this.pingCommand + " " + this.hostname;
            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
            return (proc.exitValue() == 0);
        } catch (Exception e) {
            return false;
        }
    }

}
