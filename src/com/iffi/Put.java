package com.iffi;

import java.time.LocalDate;

/**
 * A basic data class that models a stock asset named Put. This class includes
 * the methods to get the value, cost basis, and the return percentage
 * 
 * @author akshitagoel
 *
 */

public class Put extends Option {


	/**
	 * this method calulates the value of the put asset
	 */
	public double getValueBasis() {
		double value = 0;
		if (getSharePrice() < getStrikePrice()) {
			value = (getPremiumPerShare() * getShareLimit());
		} else if (getSharePrice() > getStrikePrice()) {
			value = ((getStrikePrice() - getSharePrice()) * getShareLimit()) + (getPremiumPerShare() * getShareLimit());
		} else {
			value = getStrikePrice();
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
		if (this.getSharePrice() < getStrikePrice()) {
			gain = (getPremiumPerShare() * getShareLimit());
		} else if (getSharePrice() > getStrikePrice()) {
			gain = getValueBasis() - (getPremiumPerShare() * getShareLimit());
		}
		return gain;
	}

	/**
	 * this methhod gets the return percentage of the put asset
	 */
	public double getReturnPercentage() {
		double returnPercentage = 0;
		if (this.getSharePrice() < getStrikePrice()) {
			returnPercentage = +100;
		} else if (this.getSharePrice() > getStrikePrice()) {
			returnPercentage = -100;
		}
		return returnPercentage;
	}

	/**
	 * this method returns a string of the wanted data of the put asset
	 */
	public String toString() {
		String put = null;
		if (this.getSharePrice() < getStrikePrice()) {
			put = (getCode() + "   " + getLabel() + "   " + "Put\n" + " Sell upto " + getShareLimit() + " Shares at "
					+ getStrikePrice() + " til " + getStrikeDate() + "\n" + " Premium of : " + getPremiumPerShare() + "/share ("
					+ Math.round((getPremiumPerShare() * getShareLimit()) * 1000.0) / 1000.0 + ")\n" + " Share Price "
					+ getSharePrice() + "\n" + " Long Put Value: " + getShareLimit() + " shares at " + getPremiumPerShare()
					+ " = " + Math.round((getPremiumPerShare() * getShareLimit()) * 1000.0) / 1000.0 + "\n" + "\t\t\t"
					+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		} else {
			put = (getCode() + "   " + getLabel() + "   " + " Put\n" + " Sell upto " + getShareLimit() + " Shares at "
					+ getStrikePrice() + " til " + getStrikeDate() + "\n" + "Premium of : " + getPremiumPerShare() + "/share ("
					+ Math.round((getPremiumPerShare() * getShareLimit()) * 1000.0) / 1000.0 + ")\n" + " Share Price "
					+ getSharePrice() + "\n" + " Short Put Value: " + getShareLimit() + " shares at (" + getSharePrice()
					+ " - " + getStrikePrice() + " - " + getPremiumPerShare() + " = " + Math.round(getGain() * 1000.0) / 1000.0
					+ ")\n" + "\t\t\t" + Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
					+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		}
		return put;
	}

}
