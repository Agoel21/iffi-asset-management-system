package com.iffi;

import java.time.LocalDate;

/**
 * A basic data class that models a stock asset named Put. This class includes
 * the methods to get the value, cost basis, and the return percentage
 * 
 * @author akshitagoel
 *
 */

public class Put extends Stock {

	private double strikePrice;
	private double shareLimit;
	private double premiumPerShare;
	private LocalDate strikeDate;

	public Put(Stock s, LocalDate purchaseDate, double strikePrice, double shareLimit, double premiumPerShare,
			LocalDate strikeDate) {
		super(s.getCode(), s.getType(), s.getLabel(), s.getSymbol(), s.getSharePrice());
		this.strikePrice = strikePrice;
		this.shareLimit = shareLimit;
		this.premiumPerShare = premiumPerShare;
		this.strikeDate = strikeDate;
	}

	public double getStrikePrice() {
		return strikePrice;
	}

	public double getShareLimit() {
		return shareLimit;
	}

	public double getPremiumPerShare() {
		return premiumPerShare;
	}

	public LocalDate getStrikeDate() {
		return strikeDate;
	}

	/**
	 * this method calulates the value of the put asset
	 */
	public double getValueBasis() {
		double value = 0;
		if (getSharePrice() < this.strikePrice) {
			value = (this.premiumPerShare * getShareLimit());
		} else if (getSharePrice() > this.strikePrice) {
			value = ((this.strikePrice - getSharePrice()) * getShareLimit()) + (this.premiumPerShare * getShareLimit());
		} else {
			value = this.strikePrice;
		}
		return value;
	}

	/**
	 * this method returns a zero cost basis for a put stock
	 */
	public double getCostBasis() {
		return 0;

	}

	/**
	 * this method returns the gain of the put asset
	 */
	public double getGain() {
		double gain = 0;
		if (this.getSharePrice() < this.strikePrice) {
			gain = (this.premiumPerShare * getShareLimit());
		} else if (getSharePrice() > strikePrice) {
			gain = getValueBasis() - (this.premiumPerShare * getShareLimit());
		}
		return gain;
	}

	/**
	 * this methhod gets the return percentage of the put asset
	 */
	public double getReturnPercentage() {
		double returnPercentage = 0;
		if (this.getSharePrice() < this.strikePrice) {
			returnPercentage = +100;
		} else if (this.getSharePrice() > this.strikePrice) {
			returnPercentage = -100;
		}
		return returnPercentage;
	}

	/**
	 * this method returns a string of the wanted data of the put asset
	 */
	public String toString() {
		String put = null;
		if (this.getSharePrice() < this.strikePrice) {
			put = (getCode() + "   " + getLabel() + "   " + "Put\n" + " Sell upto " + shareLimit + " Shares at "
					+ strikePrice + " til " + strikeDate + "\n" + " Premium of : " + premiumPerShare + "/share ("
					+ Math.round((premiumPerShare * shareLimit) * 1000.0) / 1000.0 + ")\n" + " Share Price "
					+ getSharePrice() + "\n" + " Long Put Value: " + shareLimit + " shares at " + premiumPerShare
					+ " = " + Math.round((premiumPerShare * shareLimit) * 1000.0) / 1000.0 + "\n" + "\t\t\t"
					+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		} else {
			put = (getCode() + "   " + getLabel() + "   " + " Put\n" + " Sell upto " + shareLimit + " Shares at "
					+ strikePrice + " til " + strikeDate + "\n" + "Premium of : " + premiumPerShare + "/share ("
					+ Math.round((premiumPerShare * shareLimit) * 1000.0) / 1000.0 + ")\n" + " Share Price "
					+ getSharePrice() + "\n" + " Short Put Value: " + shareLimit + " shares at (" + getSharePrice()
					+ " - " + strikePrice + " - " + premiumPerShare + " = " + Math.round(getGain() * 1000.0) / 1000.0
					+ ")\n" + "\t\t\t" + Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		}
		return put;
	}

}
