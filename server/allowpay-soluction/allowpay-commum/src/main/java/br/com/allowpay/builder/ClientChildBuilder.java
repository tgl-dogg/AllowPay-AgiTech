package br.com.allowpay.builder;

import br.com.allowpay.canonical.ClientChild;

public class ClientChildBuilder {

	public static ClientChildBuilder create() {
		return new ClientChildBuilder();
	}

	private ClientChildBuilder() {
	}

	private String name;

	public ClientChildBuilder withName(final String name) {
		this.name = name;
		return this;
	}

	public ClientChild build() {
		return new ClientChild(name);
	}

}
