package com.iffi;

/*
 * a super class that models an asset.
 * this class contains three subclsases.
 */
public abstract class Asset {

	private final String code;
	private final String type;
	private final String label;

	public Asset(String code, String type, String label) {
		this.code = code;
		this.type = type;
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}

	public abstract double getCostBasis();

	public abstract double getValueBasis();

	public abstract double getGain();

	public abstract double getReturnPercentage();

	public abstract String toString();

}
