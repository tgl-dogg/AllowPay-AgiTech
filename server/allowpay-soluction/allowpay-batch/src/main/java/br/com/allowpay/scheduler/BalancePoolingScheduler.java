package br.com.allowpay.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.allowpay.facade.MonitoringBalanceAgilitasFacade;

@Service
public class BalancePoolingScheduler {
	
	@Autowired
	private MonitoringBalanceAgilitasFacade monitoringBalanceAgilitasFacade;

	@Scheduled(fixedRate = 10000)
	public void execute() {		
		monitoringBalanceAgilitasFacade.startMonitor();
	}
}