package br.com.allowpay.entities.builder;

import java.math.BigDecimal;

import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.entities.Child;

public class CardRegisterBuilder {
	
	public static CardRegisterBuilder create(){ 
		return new CardRegisterBuilder();
	}
	
	private String cardId;
	
	private BigDecimal balance;

	private String deviceNotificationDadId;

	private String deviceNotificationChildId;
	
	private Child child;
	
	private CardRegisterBuilder(){
		
	}

	public CardRegisterBuilder withCardId(final String cardId) {
		this.cardId = cardId;
		return this;
	}

	public CardRegisterBuilder withBalance(final BigDecimal balance) {
		this.balance = balance;
		return this;
	}

	public CardRegisterBuilder withDeviceNotificationDadId(final String deviceNotificationDadId) {
		this.deviceNotificationDadId = deviceNotificationDadId;
		return this;
	}

	public CardRegisterBuilder withDeviceNotificationChildId(final String deviceNotificationChildId) {
		this.deviceNotificationChildId = deviceNotificationChildId;
		return this;
	}
	
	public CardRegisterBuilder withChild(final Child child){
		this.child = child;
		return this;
	}
	
	public CardRegister build(){
		return new CardRegister(cardId, balance, deviceNotificationDadId, deviceNotificationChildId, child);
	}
}