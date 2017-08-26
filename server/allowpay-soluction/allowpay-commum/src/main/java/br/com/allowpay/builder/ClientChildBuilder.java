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

	public void withId(final Long id) {
		this.id = id;
	}

	public void withRegisters(final List<Register> registers) {
		this.registers = registers;
	}

	public void withClientDad(final ClientDad clientDad) {
		this.clientDad = clientDad;
	}

	public ClientChild build() {
		return new ClientChild(name, id, registers, clientDad);
	}
}