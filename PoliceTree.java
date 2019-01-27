package assignment.bits.ds;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class PoliceTree {

	PoliceNode root = null;
	PoliceNode amountRoot = null;
	int maxFine = 0;

	private PoliceNode addRecursiveById(PoliceNode current, int policeId, int amount) {
		//If Empty
		if (current == null) {
			return new PoliceNode(policeId, amount);
		}
        //Left entry
		if (policeId < current.getPoliceId()) {
			current.left = addRecursiveById(current.left, policeId, amount);
		//Right entry
		} else if (policeId > current.getPoliceId()) {
			current.right = addRecursiveById(current.right, policeId, amount);
		} else {
			// value already exists add amount
			current.updateAmount(amount);
			return current;
		}
		return current;
	}

	public void insertByPoliceId(int policeId, int amount) {
		//System.out.println("Inside the insertByPoliceId ");
		//Adding police nodes one by one
		root = addRecursiveById(root, policeId, amount);
	}

	private PoliceNode addRecursiveByAmount(PoliceNode current, int policeId, int amount) {

		// Save maxFine
		if (amount > this.maxFine)
			this.maxFine = amount;
        //new node
		if (current == null) {
			return new PoliceNode(policeId, amount);
		}
        //Left Entry
		if (amount <= current.getAmount()) {
			current.left = addRecursiveByAmount(current.left, policeId, amount);
		//Right Entry
		} else if (amount > current.getAmount()) {
			current.right = addRecursiveByAmount(current.right, policeId, amount);
		}

		return current;
	}

	private PoliceNode createAmountPoliceTree(PoliceNode node) {
		if (node != null) {
			amountRoot = addRecursiveByAmount(amountRoot, node.getPoliceId(), node.getAmount());
			if (node.left != null)
				createAmountPoliceTree(node.left);
			if (node.right != null)
				createAmountPoliceTree(node.right);
		}
		return amountRoot;
	}

	public void reorderByFineAmount() {
		amountRoot = createAmountPoliceTree(root);
		destroyPoliceTree(root);
		root = amountRoot;
	}

	public void printPoliceTree() {
		printInOrder(root);
	}
	
	public void printInOrder(PoliceNode node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(" " + node.getPoliceId() + " " + node.getAmount() + "\n");
			printInOrder(node.right);
		}	
	}

	public int maxFineCollectedByPoliceman() {
		//System.out.println("\n the maximum fine collected by policeman \n " + maxFine);
		return maxFine;
	}

	public void findBonusPolicemen(PoliceNode node, BufferedWriter bonusFile) {
		if (node != null) {
			if (node.getAmount() >= maxFineCollectedByPoliceman() * 0.9f) {
				try {
					bonusFile.write(node.getPoliceId() + "\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			findBonusPolicemen(node.left, bonusFile);
			findBonusPolicemen(node.right, bonusFile);
		}
	}

	public void printPolicemenWithBonus() {
		try {
			BufferedWriter bonusFile = new BufferedWriter(new FileWriter("bonus.txt"));
	
			findBonusPolicemen(root, bonusFile);
	
			bonusFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

   //method to destroy the tree
	public void destroyPoliceTree(PoliceNode node) {
		if (node.left != null)
			destroyPoliceTree(node.left);
		if (node.right != null)
			destroyPoliceTree(node.right);
		root = null;
	}


}
