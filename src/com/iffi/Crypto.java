package com.iffi;

import java.time.LocalDate;

/*
 * A basic data class that models  a Cryptocurrency
 * asset and the eqations to get the value, cost basis,
 * and return percentage for a Cryptocurrency asset
 * 
 */
public class Crypto extends Asset {

	private final double exchangeRate;
	private final double exchangeFee;
	private LocalDate purchaseDate;
	private double purchaseExchangeRate;
	private double numCoins;

	public Crypto(String code, String type, String label, double exchangeRate, double exchangeFee) {
		super(code, type, label);
		this.exchangeRate = exchangeRate;
		this.exchangeFee = exchangeFee;
	}

	public Crypto(Crypto crypto, LocalDate purchaseDate, double purchaseExchangeRate, double numCoins) {
		this(crypto.getCode(), crypto.getType(), crypto.getLabel(), crypto.getExchangeRate(), crypto.getExchangeFee());
		this.purchaseDate = purchaseDate;
		this.purchaseExchangeRate = purchaseExchangeRate;
		this.numCoins = numCoins;

	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public double getExchangeFee() {
		return exchangeFee;
	}

	/**
	 * this method returns the cost basis of a Cryptocurrency asset
	 */
	public double getCostBasis() {
		return this.numCoins * this.purchaseExchangeRate;
	}

	/**
	 * this method returns the value of a cryptocurrency asset
	 */
	public double getValueBasis() {
		return this.numCoins * this.exchangeRate * (1 - (this.exchangeFee / 100));
	}

	/**
	 * this method returns the gain of a cryptocurrency asset
	 */
	public double getGain() {
		return getValueBasis() - getCostBasis();
	}

	/**
	 * this method returns the return percentage of a cryptocurrency asset
	 */
	public double getReturnPercentage() {
		return Math.round((getGain() / getCostBasis()) * 100);
	}

	/**
	 * this method returns a string of the calculated data of a cryptocurrency asset
	 */
	public String toString() {
		String crypto = (getCode() + "   " + getLabel() + "   " + "Crypto\n" + "Cost Basis:  Number of Coins "
				+ numCoins + " at " + purchaseExchangeRate + " on " + purchaseDate + "\n"
				+ "Value Basis :  Number of Coins " + numCoins + " at " + exchangeRate + " Less " + exchangeFee
				+ "         " + Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   "
				+ Math.round(getValueBasis() * 1000.0) / 1000.0);

		return crypto;

	}
	
	public double getFee() {
		double fee = 10;
		return fee;
	}

}
