package br.com.allowpay.builder;

import java.util.Collection;

import br.com.allowpay.canonical.CardRegister;
import br.com.allowpay.canonical.ClientChild;

public class ClientChildBuilder {

	public static ClientChildBuilder create() {
		return new ClientChildBuilder();
	}

	private ClientChildBuilder() {
	}

	private String name;

	private Collection<CardRegister> registers;

	public ClientChildBuilder withName(final String name) {
		this.name = name;
		return this;
	}

	public ClientChildBuilder withRegisters(final Collection<CardRegister> registers) {
		this.registers = registers;
		return this;
	}

	public ClientChild build() {
		return new ClientChild(name, registers);
	}

}
