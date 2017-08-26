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

	public void withBalanceValue(final BigDecimal balanceValue) {
		this.balanceValue = balanceValue;
	}

	public void withDeviceNotificationDadId(final String deviceNotificationDadId) {
		this.deviceNotificationDadId = deviceNotificationDadId;
	}

	public void withDeviceNotificationChildId(final String deviceNotificationChildId) {
		this.deviceNotificationChildId = deviceNotificationChildId;
	}

	public void withClientChild(final ClientChild clientChild) {
		this.clientChild = clientChild;
	}

	public void withOthersChilds(final List<ClientChild> othersChilds) {
		this.othersChilds = othersChilds;
	}

	public Register build() {
		return new Register(id, balanceValue, deviceNotificationDadId, deviceNotificationChildId, clientChild,
				othersChilds);
	}
}