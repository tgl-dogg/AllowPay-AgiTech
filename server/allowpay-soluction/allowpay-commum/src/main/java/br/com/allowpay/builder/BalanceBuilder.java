package br.com.allowpay.builder;

import java.math.BigDecimal;

import br.com.allowpay.canonical.Balance;

public class BalanceBuilder {

	public static BalanceBuilder create() {
		return new BalanceBuilder();
	}

	private BalanceBuilder() {

	}

	private String cardId;
	private BigDecimal value;
	private String deviceNotificationDadId;
	private String deviceNotificationChildId;

	public BalanceBuilder withCardId(final String cardId) {
		this.cardId = cardId;
		return this;
	}

	public BalanceBuilder withValue(final BigDecimal value) {
		this.value = value;
		return this;
	}

	public BalanceBuilder withDeviceNotificationDadId(final String deviceNotificationDadId) {
		this.deviceNotificationDadId = deviceNotificationDadId;
		return this;
	}

	public BalanceBuilder withDeviceNotificationChildId(final String deviceNotificationChildId) {
		this.deviceNotificationChildId = deviceNotificationChildId;
		return this;
	}

	public Balance build() {
		return new Balance(cardId, value, deviceNotificationDadId, deviceNotificationChildId);
	}
}
