package br.com.allowpay.converter;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.Dad;
import br.com.allowpay.entities.builder.DadBuilder;

@Component
public class ClientDadToDadConverter {

	@Autowired
	private ClientChildToChildConverter clientChildToChildConverter;

	public Dad convert(final ClientDad clientDad) {

		final DadBuilder dadBuilder = DadBuilder.create();

		final String cpf = clientDad.getCpf();
		final String fullName = clientDad.getName();
		final Date createDate = clientDad.getCreateDate();
		final List<ClientChild> clientChilds = clientDad.getClientChilds();

		Optional.ofNullable(clientChilds).ifPresent(consumer -> {
			final List<Child> childs = consumer.parallelStream()
					.map(clientChild -> clientChildToChildConverter.convert(clientChild)).collect(Collectors.toList());
			dadBuilder.withChilds(childs);
		});

		return dadBuilder.withCpf(cpf).withFullName(fullName).withCreateDate(createDate).build();
	}

}
