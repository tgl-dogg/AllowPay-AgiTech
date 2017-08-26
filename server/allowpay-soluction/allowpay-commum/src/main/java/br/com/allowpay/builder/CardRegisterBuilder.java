package br.com.allowpay.builder;

import br.com.allowpay.canonical.CardRegister;

public class CardRegisterBuilder {

	public static CardRegisterBuilder create() {
		return new CardRegisterBuilder();
	}

	private CardRegisterBuilder() {
	}

	private String id;

	public CardRegisterBuilder withId(final String id) {
		this.id = id;
		return this;
	}

	public CardRegister build() {
		return new CardRegister(id);
	}

}
