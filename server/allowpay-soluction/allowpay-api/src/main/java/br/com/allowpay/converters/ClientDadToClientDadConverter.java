package br.com.allowpay.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.ClientDad;

@Component
public class ClientDadToClientDadConverter {

	public ClientDad convert(final br.com.allowpay.dtos.ClientDadDto dad) {
		final String cpf = dad.getCpf();
		final String name = dad.getName();

		//TODO: Preencher
		final ClientDad clientDad = new ClientDad(cpf, name, null, null);
		return clientDad;
	}
}
