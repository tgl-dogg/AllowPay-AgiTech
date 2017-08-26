package br.com.allowpay.converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.Dad;
import br.com.allowpay.entities.builder.DadBuilder;

public class ClientDadToDadConverter {

	@Autowired
	private ClientChildToChildConverter clientChildToChildConverter;

	public Dad convert(final ClientDad clientDad) {

		final String cpf = clientDad.getCpf();
		final String fullName = clientDad.getName();
		final Date createDate = clientDad.getCreateDate();
		final List<ClientChild> clientChilds = clientDad.getClientChilds();

		final List<Child> childs = clientChilds.parallelStream()
				.map(clientChild -> clientChildToChildConverter.convert(clientChild)).collect(Collectors.toList());

		return DadBuilder.create().withCpf(cpf).withFullName(fullName).withCreateDate(createDate).withChilds(childs)
				.build();
	}

}
