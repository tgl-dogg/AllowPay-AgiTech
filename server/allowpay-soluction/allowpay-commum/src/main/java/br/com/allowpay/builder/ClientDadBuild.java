package br.com.allowpay.builder;

import java.util.Date;
import java.util.List;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;

public class ClientDadBuild {

	public static ClientDadBuild create() {
		return new ClientDadBuild();
	}

	private ClientDadBuild() {}

	private String cpf;
	private String name;
	private Date createDate;
	private List<ClientChild> clientChilds;

	public ClientDadBuild withCPF(final String cpf) {
		this.cpf = cpf;
		return this;
	}

	public ClientDadBuild withName(final String name) {
		this.name = name;
		return this;
	}
	
	public void withCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public void withClientChilds(final List<ClientChild> clientChilds) {
		this.clientChilds = clientChilds;
	}

	public ClientDad build() {
		return new ClientDad(cpf, name, createDate, clientChilds);
	}
}
