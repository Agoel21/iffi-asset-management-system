package com.iffi;

import java.time.LocalDate;

/**
 * a data class that extends from stock this class represents a Call asset
 * 
 * @author akshitagoel
 *
 */

public class Call extends Option {

	public Call(Stock s, double strikePrice, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
		super(s, strikePrice, shareLimit, premiumPerShare, strikeDate);
	}

	/**
	 * this method calculates the Value basis of this class depending on if the call
	 * is long or short
	 */
	public double getValueBasis() {
		double value = 0;
		if (getSharePrice() < getStrikePrice()) {
			value = 0;
		} else if (getSharePrice() > getStrikePrice()) {
			value = (getSharePrice() - getStrikePrice()) * getShareLimit();
		} else {
			value = getStrikePrice();
		}
		return value;
	}

	/**
	 * this method returns the cost basis of a call stock
	 */
	public double getCostBasis() {
		double cost = getPremiumPerShare() * getShareLimit();
		return cost;

	}

	/**
	 * this method returns the gain of a call stock depending on if it is long or
	 * short
	 */
	public double getGain() {
		double gain = 0;
		if (this.getSharePrice() < getStrikePrice()) {
			gain = 0 - (getShareLimit() * getPremiumPerShare());
		} else if (getSharePrice() > getStrikePrice()) {
			gain = getValueBasis() - (this.getPremiumPerShare() * getShareLimit());
		}
		return gain;
	}

	/**
	 * this method returns the Return percentage depedning on if the call is long or
	 * short
	 */
	public double getReturnPercentage() {
		double returnPercentage = 0;
		if (this.getSharePrice() < getStrikePrice()) {
			returnPercentage = -100;
		} else if (this.getSharePrice() > getStrikePrice()) {
			returnPercentage = (getGain() / (this.getPremiumPerShare() * getShareLimit())) * 100;
		}
		return returnPercentage;
	}

	/**
	 * this method returns a string of all the calculated data
	 */
	public String toString() {
		String call = null;
		if (this.getSharePrice() < getStrikePrice()) {
			call = (getCode() + "   " + getLabel() + "   " + " Call\n" + " Buy upto " + getShareLimit() + " Shares at "
					+ getStrikePrice() + " til " + getStrikeDate() + "\n" + " Premium of : " + getPremiumPerShare() + "/share ("
					+ getPremiumPerShare() * getShareLimit() + ")\n" + " Share Price " + getSharePrice() + "\n"
					+ " Long Call Value: " + getShareLimit() + " shares at " + getPremiumPerShare() + " = "
					+ getPremiumPerShare() * getShareLimit() + "\n" + "\t\t\t"
					+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getGain() * 1000.0) / 1000.0);
		} else {
			call = (getCode() + "   " + getLabel() + "   " + " Call\n" + "Buy upto " + getShareLimit() + " Shares at "
					+ getStrikePrice() + " til " + getStrikeDate() + "\n" + " Premium of : " + getPremiumPerShare() + "/share ("
					+ getPremiumPerShare() * getShareLimit() + ")\n" + " Share Price " + getSharePrice() + "\n"
					+ " Short Call Value: " + getShareLimit() + " shares at (" + getSharePrice() + " - " + getStrikePrice()
					+ " - " + getPremiumPerShare() + " = " + Math.round(getGain() * 1000.0) / 1000.0 + ")\n" + "\t\t\t"
					+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		}
		return call;
	}

}
