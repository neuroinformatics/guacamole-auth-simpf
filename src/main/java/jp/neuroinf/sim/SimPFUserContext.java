package jp.neuroinf.sim;

import java.util.Map;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.auth.AuthenticationProvider;
import org.apache.guacamole.net.auth.simple.SimpleUserContext;
import org.apache.guacamole.protocol.GuacamoleConfiguration;

import jp.neuroinf.sim.rest.SimPFRest;

public class SimPFUserContext extends SimpleUserContext {

    private final String hostname;

    public SimPFUserContext(AuthenticationProvider authProvider, String username,
            Map<String, GuacamoleConfiguration> configs, boolean interpretTokens) {
        super(authProvider, username, configs, interpretTokens);
        GuacamoleConfiguration config = configs.get(username);
        this.hostname = config.getParameter("hostname");
    }

    @Override
    public Object getResource() throws GuacamoleException {
        return new SimPFRest(this.hostname);
    }
}
