package jp.neuroinf.sim;

public class SimPFException extends Exception {

	private static final long serialVersionUID = 3845043046998945591L;

	public SimPFException(String message, Throwable cause) {
		super(message, cause);
	}

	public SimPFException(String message) {
		super(message);
	}

	public SimPFException(Throwable cause) {
		super(cause);
	}

}
