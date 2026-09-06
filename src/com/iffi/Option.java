package com.iffi;

import java.time.LocalDate;

/**
 * An abstract base class for stock options (Call and Put). It extends Stock
 * so options share a stock's code/label/symbol/share price, and adds the
 * strike price, share limit, premium per share, and strike date used by both
 * option types.
 *
 * @author akshitagoel
 *
 */
public abstract class Option extends Stock {

	private final double strikePrice;
	private final double shareLimit;
	private final double premiumPerShare;
	private final LocalDate strikeDate;

	public Option(Stock s, double strikePrice, double shareLimit, double premiumPerShare, LocalDate strikeDate) {
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

}
