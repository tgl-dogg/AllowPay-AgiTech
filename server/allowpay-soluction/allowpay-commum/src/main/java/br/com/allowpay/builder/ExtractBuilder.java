package br.com.allowpay.builder;

import java.math.BigDecimal;
import java.util.Date;

import br.com.allowpay.canonical.Extract;

public class ExtractBuilder {

	public static ExtractBuilder create() {
		return new ExtractBuilder();
	}

	private ExtractBuilder() {}

	private Date date;

	private String merchant;

	private String type;

	private BigDecimal amount;

	public ExtractBuilder withDate(final Date date) {
		this.date = date;
		return this;
	}

	public ExtractBuilder withMerchant(final String merchant) {
		this.merchant = merchant;
		return this;
	}

	public ExtractBuilder withType(final String type) {
		this.type = type;
		return this;
	}

	public ExtractBuilder withAmount(final BigDecimal amount) {
		this.amount = amount;
		return this;
	}

	public Extract build() {
		return new Extract(date, merchant, type, amount);
	}

}
