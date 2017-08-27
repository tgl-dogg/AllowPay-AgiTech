package br.com.allowpay.facade;

import org.springframework.stereotype.Service;

import com.hazelcast.core.ITopic;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.dtos.ClientChildDto;
import br.com.allowpay.dtos.ClientDadDto;

@Service
public class RegisterClientNotificationFacade {

//	@Autowired
//	@Qualifier("registerNotificationTopic")
	private ITopic<Balance> registerNotificationTopic;

	public void register(final ClientChildDto clientChildDto) {
		try {
			// TODO: registrar no tópico
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void register(final ClientDadDto clientDadDto) {
		try {
			// TODO: registrar no tópico
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}