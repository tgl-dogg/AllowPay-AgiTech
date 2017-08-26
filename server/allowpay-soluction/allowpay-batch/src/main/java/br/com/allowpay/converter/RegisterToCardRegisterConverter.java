package br.com.allowpay.converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.Register;
import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.builder.CardRegisterBuilder;

@Component
public class RegisterToCardRegisterConverter {

	@Autowired
	private ClientChildToChildConverter clientChildToChildConverter;

	public CardRegister convert(final Register register) {

		final String cardId = register.getId();
		final BigDecimal balance = register.getBalanceValue();

		final ClientChild clientChild = register.getClientChild();
		final Child child = clientChildToChildConverter.convert(clientChild);

		final String deviceNotificationChildId = register.getDeviceNotificationChildId();
		final String deviceNotificationDadId = register.getDeviceNotificationDadId();

		return CardRegisterBuilder.create().withCardId(cardId).withBalance(balance).withChild(child)
				.withDeviceNotificationChildId(deviceNotificationChildId)
				.withDeviceNotificationDadId(deviceNotificationDadId).build();
	}
}
