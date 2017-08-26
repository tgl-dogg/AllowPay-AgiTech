package br.com.allowpay.builder;

import br.com.allowpay.canonical.ClientDad;

public class ClientDadBuild {

	public static ClientDadBuild create() {
		return new ClientDadBuild();
	}

	private ClientDadBuild() {}

	private String name;

	private String cpf;

	public ClientDadBuild withCPF(final String cpf) {
		this.cpf = cpf;
		return this;
	}

	public ClientDadBuild withName(final String name) {
		this.name = name;
		return this;
	}

	public ClientDad build() {
		return new ClientDad(cpf, name);
	}
}
