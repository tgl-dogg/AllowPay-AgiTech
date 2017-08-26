package br.com.allowpay.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hazelcast.core.ITopic;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.exception.IntegrationFailException;
import br.com.allowpay.rules.AgilitasBalanceConsumer;
import br.com.allowpay.rules.CompareBalanceValue;
import br.com.allowpay.rules.FindCardsIdentification;

@Service
public class MonitoringBalanceAgilitasFacade {

	@Autowired
	private FindCardsIdentification findCardsIdentification;

	@Autowired
	private AgilitasBalanceConsumer agilitasBalanceConsumer;

	@Autowired
	private CompareBalanceValue compareBalanceValue;

	@Autowired
	@Qualifier("balanceNotificationTopic")
	private ITopic<Balance> balanceNotificationTopic;

	public void startMonitor() {
		findCardsIdentification.getCardsIdentification(pageCardRegister -> {
			pageCardRegister.parallelStream().forEach(cardIdentification -> {
				try {
					final Balance balance = agilitasBalanceConsumer
							.getBalanceDiferenceAgilitasAndAllowpay(cardIdentification);

					if (compareBalanceValue.balanceDiferenceZero(balance.getValue())) {
						balanceNotificationTopic.publish(balance);
					}
				} catch (IntegrationFailException e) {
					throw new RuntimeException(e);
				}
			});
		});
	}
}
