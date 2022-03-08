package com.iffi;

import java.time.LocalDate;

/**
 * a data class that extends from stock this class represents a Call asset
 * 
 * @author akshitagoel
 *
 */

public class Call extends Stock {

	private double strikePrice;
	private double shareLimit;
	private double premiumPerShare;
	private LocalDate strikeDate;

	public Call(Stock s, LocalDate purchaseDate, double strikePrice, double shareLimit, double premiumPerShare,
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
	 * this method calculates the Value basis of this class depending on if the call
	 * is long or short
	 */
	public double getValueBasis() {
		double value = 0;
		if (getSharePrice() < this.strikePrice) {
			value = 0;
		} else if (getSharePrice() > this.strikePrice) {
			value = (getSharePrice() - this.strikePrice) * getShareLimit();
		} else {
			value = this.strikePrice;
		}
		return value;
	}

	/**
	 * this method returns the cost basis of a call stock
	 */
	public double getCostBasis() {
		double cost = this.premiumPerShare * getShareLimit();
		return cost;

	}

	/**
	 * this method returns the gain of a call stock depending on if it is long or
	 * short
	 */
	public double getGain() {
		double gain = 0;
		if (this.getSharePrice() < this.strikePrice) {
			gain = 0 - (getShareLimit() * premiumPerShare);
		} else if (getSharePrice() > strikePrice) {
			gain = getValueBasis() - (this.premiumPerShare * getShareLimit());
		}
		return gain;
	}

	/**
	 * this method returns the Return percentage depedning on if the call is long or
	 * short
	 */
	public double getReturnPercentage() {
		double returnPercentage = 0;
		if (this.getSharePrice() < this.strikePrice) {
			returnPercentage = -100;
		} else if (this.getSharePrice() > this.strikePrice) {
			returnPercentage = (getGain() / (this.premiumPerShare * getShareLimit())) * 100;
		}
		return returnPercentage;
	}

	/**
	 * this method returns a string of all the calculated data
	 */
	public String toString() {
		String call = null;
		if (this.getSharePrice() < this.strikePrice) {
			call = (getCode() + "   " + getLabel() + "   " + " Call\n" + " Buy upto " + shareLimit + " Shares at "
					+ strikePrice + " til " + strikeDate + "\n" + " Premium of : " + premiumPerShare + "/share ("
					+ premiumPerShare * shareLimit + ")\n" + " Share Price " + getSharePrice() + "\n"
					+ " Long Call Value: " + shareLimit + " shares at " + premiumPerShare + " = "
					+ premiumPerShare * shareLimit + "\n" + "\t\t\t"
					+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getGain() * 1000.0) / 1000.0);
		} else {
			call = (getCode() + "   " + getLabel() + "   " + " Call\n" + "Buy upto " + shareLimit + " Shares at "
					+ strikePrice + " til " + strikeDate + "\n" + " Premium of : " + premiumPerShare + "/share ("
					+ premiumPerShare * shareLimit + ")\n" + " Share Price " + getSharePrice() + "\n"
					+ " Short Call Value: " + shareLimit + " shares at (" + getSharePrice() + " - " + strikePrice
					+ " - " + premiumPerShare + " = " + Math.round(getGain() * 1000.0) / 1000.0 + ")\n" + "\t\t\t"
					+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		}
		return call;
	}

}
