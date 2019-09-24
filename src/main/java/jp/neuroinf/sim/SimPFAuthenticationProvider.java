package jp.neuroinf.sim;

import java.util.HashMap;
import java.util.Map;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.auth.Credentials;
import org.apache.guacamole.net.auth.simple.SimpleAuthenticationProvider;
import org.apache.guacamole.protocol.GuacamoleConfiguration;

public class SimPFAuthenticationProvider extends SimpleAuthenticationProvider {

	@Override
	public String getIdentifier() {
		return "simpf";
	}

	@Override
	public Map<String, GuacamoleConfiguration> getAuthorizedConfigurations(Credentials credentials)
			throws GuacamoleException {

		try {
			String session_id = credentials.getUsername();
			if (session_id == null || session_id.equals("")) {
				return null;
			}
			SimPFVmDatabase vmdatabase = new SimPFVmMySQLDatabase();
			SimPFVmNode vm = vmdatabase.getVmNode(session_id);
			GuacamoleConfiguration config = vm.getConfiguration();

			Map<String, GuacamoleConfiguration> configs = new HashMap<String, GuacamoleConfiguration>();
			configs.put(session_id, config);
			return configs;
		} catch (SimPFException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
