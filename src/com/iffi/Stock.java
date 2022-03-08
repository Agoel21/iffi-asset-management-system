package com.iffi;

import java.time.LocalDate;

/*
 * A basic data class that models a stock
 * asset and the equations used to getp value,
 * Cost Basis, and return percentage. This class
 * has two subclasses; put and call.
 */
public class Stock extends Asset {

	private final String symbol;
	private final double sharePrice;
	private LocalDate purchaseDate;
	private double originalPrice;
	private double sharesOwned;

	public double getSharesOwned() {
		return sharesOwned;
	}

	public void setSharesOwned(double sharesOwned) {
		this.sharesOwned = sharesOwned;
	}

	private double dividend;

	public Stock(String code, String type, String label, String symbol, double sharePrice) {
		super(code, type, label);
		this.symbol = symbol;
		this.sharePrice = sharePrice;

	}

	public Stock(Stock s, LocalDate purchaseDate, double originalPrice, double sharesOwned, double dividend) {
		this(s.getCode(), s.getType(), s.getLabel(), s.getSymbol(), s.getSharePrice());
		this.purchaseDate = purchaseDate;
		this.originalPrice = originalPrice;
		this.sharesOwned = sharesOwned;
		this.dividend = dividend;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public double getCostBasis() {
		return this.originalPrice * this.sharesOwned;
	}

	/**
	 * this class calculates the value of a stock
	 */
	public double getValueBasis() {
		return (this.sharePrice * this.sharesOwned) + this.dividend;

	}

	/**
	 * this method returns the gain of a stock asset
	 */
	public double getGain() {
		return getValueBasis() - getCostBasis();
	}

	/**
	 * this method calculates the return percentage of a stock asset
	 */
	public double getReturnPercentage() {
		return ((getValueBasis() / getCostBasis()) * 100) - 100;

	}

	/**
	 * this method creats a string to represent all of the wanted data
	 */
	public String toString() {

		String stock = (getCode() + "   " + getLabel() + "   " + "Stock\n" + "Cost Basis: " + sharesOwned
				+ " Shares at " + originalPrice + " " + purchaseDate + "\n" + "Value Basis : " + sharesOwned
				+ " Shares at $" + Math.round(getSharePrice() * 1000.0) / 1000.0 + "\t\t\t"
				+ Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
				+ Math.round(getValueBasis() * 1000.0) / 1000.0);
		return stock;

	}

}
