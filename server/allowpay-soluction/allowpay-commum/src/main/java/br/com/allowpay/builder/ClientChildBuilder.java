package br.com.allowpay.builder;

import java.util.List;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.canonical.Register;

public class ClientChildBuilder {

	public static ClientChildBuilder create() {
		return new ClientChildBuilder();
	}

	private ClientChildBuilder() {
	}

	private String name;
	private Long id;
	private List<Register> registers;
	private ClientDad clientDad;

	public ClientChildBuilder withName(final String name) {
		this.name = name;
		return this;
	}

	public ClientChildBuilder withId(final Long id) {
		this.id = id;
		return this;
	}

	public ClientChildBuilder withRegisters(final List<Register> registers) {
		this.registers = registers;
		return this;
	}

	public ClientChildBuilder withClientDad(final ClientDad clientDad) {
		this.clientDad = clientDad;
		return this;
	}

	public ClientChild build() {
		return new ClientChild(name, id, registers, clientDad);
	}
}