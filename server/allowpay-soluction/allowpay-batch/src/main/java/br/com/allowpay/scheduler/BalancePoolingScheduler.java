package br.com.allowpay.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hazelcast.core.ITopic;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.rules.AgilitasBalanceConsumer;
import br.com.allowpay.rules.CompareBalanceValue;
import br.com.allowpay.rules.FindCardsIdentification;

@Service
public class BalancePoolingScheduler {

	@Autowired
	private FindCardsIdentification findCardsIdentification;
	
	@Autowired
	private AgilitasBalanceConsumer agilitasBalanceConsumer;
	
	@Autowired
	private CompareBalanceValue compareBalanceValue;
	
	@Autowired
	@Qualifier("balanceNotificationTopic")
	private ITopic<Balance> balanceNotificationTopic;
	
	@Scheduled(fixedRate=10000)
	public void execute(){
		findCardsIdentification.getCardsIdentification(pageCardRegister->{
			pageCardRegister.parallelStream().forEach(cardIdentification->{
				final Balance balance = agilitasBalanceConsumer.getBalanceDiferenceAgilitasAndAllowpay(cardIdentification);
				if(compareBalanceValue.balanceDiferenceZero(balance.getValue())){
					balanceNotificationTopic.publish(balance);
				}
			});
		});
	}
}
