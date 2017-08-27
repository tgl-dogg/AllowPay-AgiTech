package br.com.allowpay.builder;

import java.math.BigDecimal;

import br.com.allowpay.canonical.Bonus;

public class BonusBuilder {

	public static BonusBuilder create() {
		return new BonusBuilder();
	}

	private BonusBuilder() {

	}

	private String cardId;
	private BigDecimal value;

	public BonusBuilder withCardId(final String cardId) {
		this.cardId = cardId;
		return this;
	}

	public BonusBuilder withValue(final BigDecimal value) {
		this.value = value;
		return this;
	}

	public Bonus build() {
		return new Bonus(cardId, value);
	}
}
