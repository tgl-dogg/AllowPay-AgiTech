package br.com.allowpay.builder;

import java.math.BigDecimal;

import br.com.allowpay.canonical.Balance;

public class BalanceBuilder {

	public static BalanceBuilder create() {
		return new BalanceBuilder();
	}
	
	private BalanceBuilder(){
		
	}
	
	private String cardId;
	private BigDecimal value;

	public BalanceBuilder withCardId(final String cardId) {
		this.cardId = cardId;
		return this;
	}
	
	public BalanceBuilder withValue(final BigDecimal value) {
		this.value = value;
		return this;
	}
	
	public Balance build(){
		return new Balance(cardId, value);
	}
}
