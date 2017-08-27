package br.com.allowpay.listener;

import org.springframework.stereotype.Component;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import br.com.allowpay.canonical.Balance;

@Component
public class BalanceNotificationEventListener implements MessageListener<Balance>{

	@Override
	public void onMessage(final Message<Balance> message) {
		//TODO: Enviar notificação para os apps
	}
}