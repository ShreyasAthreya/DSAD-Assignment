package assignment.bits.ds;

public class PoliceNode {
	private int policeId;
	private int amount;
	PoliceNode left, right;
	
	public PoliceNode(int policeId, int amount) {
		this.policeId = policeId;
		this.amount = amount;
		right = null;
		left = null;		
	}
	
	public int getPoliceId() {
		return policeId;
	}
	public void setPoliceId(int policeId) {
		this.policeId = policeId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void updateAmount(int amount) {
		this.amount = this.amount+amount;
	}

}
