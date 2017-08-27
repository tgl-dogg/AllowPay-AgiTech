package br.com.allowpay.builder;

import java.util.Date;
import java.util.List;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;

public class ClientDadBuilder {

	public static ClientDadBuilder create() {
		return new ClientDadBuilder();
	}

	private ClientDadBuilder() {}

	private String cpf;
	private String name;
	private Date createDate;
	private List<ClientChild> clientChilds;

	public ClientDadBuilder withCPF(final String cpf) {
		this.cpf = cpf;
		return this;
	}

	public ClientDadBuilder withName(final String name) {
		this.name = name;
		return this;
	}
	
	public ClientDadBuilder withCreateDate(final Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public ClientDadBuilder withClientChilds(final List<ClientChild> clientChilds) {
		this.clientChilds = clientChilds;
		return this;
	}

	public ClientDad build() {
		return new ClientDad(cpf, name, createDate, clientChilds);
	}
}
