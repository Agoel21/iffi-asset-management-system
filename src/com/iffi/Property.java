package com.iffi;

import java.time.LocalDate;

/*
 * A basic data class that models a property
 * asset and the methods for getting the 
 * Value, cost Basis, and return percentage
 */
public class Property extends Asset {

	private final double appraisalValue;
	private LocalDate purchaseDate;
	private double purchasePrice;

	public Property(String code, String type, String label, double appraisalValue) {
		super(code, type, label);
		this.appraisalValue = appraisalValue;
	}

	public Property(Property p, LocalDate purchaseDate, double purchasePrice) {
		this(p.getCode(), p.getType(), p.getLabel(), p.getAppraisalValue());
		this.purchaseDate = purchaseDate;
		this.purchasePrice = purchasePrice;
	}

	public double getAppraisalValue() {
		return this.appraisalValue;
	}

	/**
	 * this method returns the cost basis of a property asset
	 */
	public double getCostBasis() {
		return this.purchasePrice;
	}

	/**
	 * this method returns the value of a property asset
	 */
	public double getValueBasis() {
		return this.appraisalValue;
	}

	/**
	 * this mthod returns the gain of a property asset
	 */
	public double getGain() {
		return (this.appraisalValue - this.purchasePrice);
	}

	/**
	 * this method returns the return percentage of this asset
	 */
	public double getReturnPercentage() {
		double returnPercentage = (getGain() / getCostBasis() * 100);
		return returnPercentage;
	}

	/**
	 * this method returns a string of the calculated data for a property asset
	 */
	public String toString() {
		String property = (getCode() + "   " + getLabel() + "   " + "Property\n" + "Cost Basis: Purchased at $"
				+ purchasePrice + "on " + purchaseDate + "\n" + "Value Basis : Appraised at $" + appraisalValue
				+ "\t\t\t" + Math.round(getReturnPercentage() * 1000.0) / 1000.0 + "   " + appraisalValue + "\n");

		return property;

	}

}
