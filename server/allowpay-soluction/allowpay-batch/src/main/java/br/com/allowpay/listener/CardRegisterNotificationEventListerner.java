package br.com.allowpay.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import br.com.allowpay.canonical.Register;
import br.com.allowpay.converter.RegisterToCardRegisterConverter;
import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.repositories.CardRegisterRepository;

@Component
public class CardRegisterNotificationEventListerner implements MessageListener<Register> {

	@Autowired
	private CardRegisterRepository cardRegisterRepository;

	@Autowired
	private RegisterToCardRegisterConverter registerToCardRegisterConverter;

	@Override
	@Transactional
	public void onMessage(final Message<Register> message) {
		final Register register = message.getMessageObject();

		final CardRegister cardRegister = registerToCardRegisterConverter.convert(register);

		cardRegisterRepository.save(cardRegister);
	}
}