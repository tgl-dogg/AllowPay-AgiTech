package br.com.allowpay.builder;

import java.math.BigDecimal;

import br.com.allowpay.canonical.CardIdentification;

public class CardIdentificationBuilder {

	public static CardIdentificationBuilder create() {
		return new CardIdentificationBuilder();
	}

	private CardIdentificationBuilder() {

	}

	private String cardId;
	private BigDecimal balanceValue;

	public CardIdentificationBuilder withCardId(final String cardId) {
		this.cardId = cardId;
		return this;
	}

	public CardIdentificationBuilder withBalance(final BigDecimal balanceValue) {
		this.balanceValue = balanceValue;
		return this;
	}

	public CardIdentification build() {
		return new CardIdentification(cardId, balanceValue);
	}
}