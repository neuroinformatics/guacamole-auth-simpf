package jp.neuroinf.sim;

import java.util.HashMap;

import org.apache.guacamole.protocol.GuacamoleConfiguration;

public class SimPFVmNode {
	
	private String hostname;
	private String protocol;
	private int port;
	private String password;
	private HashMap<String, String> parameters;

	public SimPFVmNode(String hostname, String protocol, int port, String password) {
		this.hostname = hostname;
		this.protocol = protocol;
		this.port = port;
		this.password = password;
		this.parameters = new HashMap<String, String>();
	}

	public final void setParameter(String name, String value) {
		this.parameters.put(name, value);
	}

	public final String getProtocol() {
		return this.protocol;
	}

	public final String getHostname() {
		return this.hostname;
	}

	public final int getPort() {
		return this.port;
	}

	public final String getPassword() {
		return this.password;
	}

	public final GuacamoleConfiguration getConfiguration() {
		GuacamoleConfiguration config = new GuacamoleConfiguration();
		config.setProtocol(this.protocol);
		config.setParameter("hostname", this.hostname);
		config.setParameter("port", String.valueOf(this.port));
		config.setParameter("password", this.password);
		this.parameters.forEach((key, value) -> config.setParameter(key, value));
		return config;
	}
}
