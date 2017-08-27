package br.com.allowpay.builder;

import java.math.BigDecimal;
import java.util.List;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.Register;

public class RegisterBuilder {

	public static RegisterBuilder create() {
		return new RegisterBuilder();
	}

	private RegisterBuilder() {
	}

	private String id;
	private BigDecimal balanceValue;
	private String deviceNotificationDadId;
	private String deviceNotificationChildId;
	private ClientChild clientChild;
	private List<ClientChild> othersChilds;

	public RegisterBuilder withId(final String id) {
		this.id = id;
		return this;
	}

	public RegisterBuilder withBalanceValue(final BigDecimal balanceValue) {
		this.balanceValue = balanceValue;
		return this;
	}

	public RegisterBuilder withDeviceNotificationDadId(final String deviceNotificationDadId) {
		this.deviceNotificationDadId = deviceNotificationDadId;
		return this;
	}

	public RegisterBuilder withDeviceNotificationChildId(final String deviceNotificationChildId) {
		this.deviceNotificationChildId = deviceNotificationChildId;
		return this;
	}

	public RegisterBuilder withClientChild(final ClientChild clientChild) {
		this.clientChild = clientChild;
		return this;
	}

	public RegisterBuilder withOthersChilds(final List<ClientChild> othersChilds) {
		this.othersChilds = othersChilds;
		return this;
	}

	public Register build() {
		return new Register(id, balanceValue, deviceNotificationDadId, deviceNotificationChildId, clientChild,
				othersChilds);
	}
}