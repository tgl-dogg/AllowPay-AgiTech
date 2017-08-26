package br.com.allowpay.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.converter.ClientChildToChildConverter;
import br.com.allowpay.entities.Child;
import br.com.allowpay.repositories.ChildRepository;

@Component
public class ChildNotificationEventListener implements MessageListener<ClientChild>{

	@Autowired
	private ChildRepository childRepository;
	
	@Autowired
	private ClientChildToChildConverter clientChildToChildConverter;
	
	@Override
	@Transactional
	public void onMessage(final Message<ClientChild> message) {
		final ClientChild clientChild = message.getMessageObject();
		final Child child = clientChildToChildConverter.convert(clientChild);
		childRepository.save(child);
	}
}