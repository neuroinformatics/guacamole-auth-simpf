package jp.neuroinf.sim;

abstract public class SimPFVmDatabase {

	abstract public SimPFVmNode getVmNode(String session_id) throws SimPFException;

}
