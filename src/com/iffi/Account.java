

 package com.iffi;

import java.util.List;

/**
 * this class represents an account and the equations to return the totals of an
 * account
 * 
 * @author akshitagoel
 *
 */

public abstract class Account implements Comparable<Account> {

	private final String accountNumber;
	//private final String type;
	private final Person owner;
	private final Person manager;
	private final Person beneficiary;
	private final List<Asset> assetList;

	public Account(String accountNumber, Person owner, Person manager, Person beneficiary,
			List<Asset> assetList) {
		super();
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.assetList = assetList;
	}

	public String getAccountNumber() {
		return accountNumber;
	}


	public Person getOwner() {
		return owner;
	}

	public Person getManager() {
		return manager;
	}

	public Person getBeneficiary() {
		return beneficiary;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	/**
	 * this method returns the total value of and an accounts assets
	 * 
	 * @return
	 */
	public double getValueTotal() {
		double totalValue = 0;
		for (Asset a : assetList) {
			totalValue += a.getValueBasis();
		}
		return totalValue;
	}

	/**
	 * this method returns the total fees of an accounts assets
	 * 
	 * @return
	 */
	public double getTotalFees() {
		double totalFees = 0;
		for (Asset a : assetList) {
			totalFees += a.getFee();
		}
		return totalFees;
	}

	/**
	 * this method returns the final fees of an accounts assets
	 * 
	 * @return
	 */
	public abstract double getFinalFees();

	/**
	 * this method returns the total cost of the assets of an account
	 * 
	 * @return
	 */
	public double getTotalCost() {
		double totalCost = 0;
		for (Asset a : assetList) {
			totalCost += a.getCostBasis();
		}
		return totalCost;
	}

	/**
	 * this method returns the total gain of all assets of an account
	 * 
	 * @return
	 */
	public double getTotalReturn() {
		return getValueTotal() - getTotalCost();
	}

	/**
	 * this method returns the total return percentage of an account
	 * 
	 * @return
	 */
	public double getTotalPercentage() {
		return (getTotalReturn() / getTotalCost()) * 100;
	}

	/**
	 * A method to produce a string for Accounts
	 */
	public String toString() {
		StringBuilder sblr = new StringBuilder();
		sblr.append("=============================\n");
		if (this.type.equals("P")) {
			sblr.append("      PRO ACCOUNT " + " ");
		} else {
			sblr.append("       NOOB ACCOUNT" + "  ");
		}
		sblr.append(this.accountNumber + "\n");
		sblr.append("=============================\n");

		sblr.append("OWNER \n");
		sblr.append(this.owner + "\n");
		sblr.append("MANAGER \n");
		sblr.append(this.manager + "\n");
		sblr.append("BENEFICIARY \n");
		if (this.beneficiary != null) {
			sblr.append(this.beneficiary + "\n");
		} else {
			sblr.append("none");
		}
		for (Asset a : this.assetList) {
			sblr.append(a + "\n\n");
		}
		return sblr.toString();
	}

	/**
	 * Comparator method to sort Owner Last Name lexiographically
	 */
	@Override
	public int compareTo(Account otherAccount) {
		return owner.getLastName().compareTo(otherAccount.getOwner().getLastName());

	}

}
