
package jp.neuroinf.sim;

import org.apache.guacamole.properties.StringGuacamoleProperty;

public class SimPFProperties {

	/**
	 * this class should not be instantiated
	 */
	private SimPFProperties() {
	}

	/**
	 * mysql hostname
	 */
	public static final StringGuacamoleProperty MYSQL_HOSTNAME = new StringGuacamoleProperty() {
		@Override
		public String getName() {
			return "simpf-mysql-hostname";
		}
	};

	/**
	 * mysql port
	 */
	public static final StringGuacamoleProperty MYSQL_PORT = new StringGuacamoleProperty() {
		@Override
		public String getName() {
			return "simpf-mysql-port";
		}
	};
	/**
	 * mysql database
	 */
	public static final StringGuacamoleProperty MYSQL_DATABASE = new StringGuacamoleProperty() {
		@Override
		public String getName() {
			return "simpf-mysql-database";
		}
	};
	/**
	 * mysql username
	 */
	public static final StringGuacamoleProperty MYSQL_USERNAME = new StringGuacamoleProperty() {
		@Override
		public String getName() {
			return "simpf-mysql-username";
		}
	};

	/**
	 * mysql password
	 */
	public static final StringGuacamoleProperty MYSQL_PASSWORD = new StringGuacamoleProperty() {
		@Override
		public String getName() {
			return "simpf-mysql-password";
		}
	};

}
