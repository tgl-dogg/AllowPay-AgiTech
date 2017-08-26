package br.com.allowpay.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.converter.ClientDadToDadConverter;
import br.com.allowpay.entities.Dad;
import br.com.allowpay.repositories.DadRepository;

@Component
public class DadNotificationEventListener implements MessageListener<ClientDad> {

	@Autowired
	private DadRepository dadRepository;

	@Autowired
	private ClientDadToDadConverter clientDadToDadConverter;

	@Override
	@Transactional
	public void onMessage(final Message<ClientDad> message) {
		final ClientDad clientDad = message.getMessageObject();
		final Dad dad = clientDadToDadConverter.convert(clientDad);
		dadRepository.save(dad);
	}
}