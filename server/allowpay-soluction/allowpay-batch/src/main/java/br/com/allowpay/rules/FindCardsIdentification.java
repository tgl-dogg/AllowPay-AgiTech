package br.com.allowpay.rules;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.converter.CardRegisterToCardIdentificationConverter;
import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.repositories.CardRegisterRepository;

@Component
public class FindCardsIdentification {

	private static final int START_PAGE = 0;
	private static final int PAGE_SIZE = 1000;
	private static final int THREAD_POOL_SIZE = 10;

	@Autowired
	private CardRegisterRepository cardRegisterRepository;

	@Autowired
	private CardRegisterToCardIdentificationConverter cardRegisterToCardIdentificationConverter;

	public void getCardsIdentification(final Consumer<List<Balance>> consumerPageCardRegister) {

		final int page = START_PAGE;
		final int size = PAGE_SIZE;

		final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		final Pageable pageable = new PageRequest(page, size);

		getCardRegistersPage(pageable, consumerPageCardRegister, executorService);

	}

	private void getCardRegistersPage(final Pageable pageable, final Consumer<List<Balance>> consumerPageCardRegister,
			final ExecutorService executorService) {
		final Page<CardRegister> cardRegisters = cardRegisterRepository.findAll(pageable);

		executorService.submit(() -> {
			final List<Balance> cardIdentifications = cardRegisters.getContent().parallelStream()
					.map(cardRegister -> cardRegisterToCardIdentificationConverter.convert(cardRegister))
					.collect(Collectors.toList());
			consumerPageCardRegister.accept(cardIdentifications);
		});

		if (cardRegisters.hasNext()) {
			getCardRegistersPage(pageable, consumerPageCardRegister, executorService);
		}
	}
}
