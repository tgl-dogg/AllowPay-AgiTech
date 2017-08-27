package br.com.allowpay.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.entities.builder.CardRegisterBuilder;

@RunWith(MockitoJUnitRunner.class)
public class CardRegisterToCardIdentificationConverterTest {

	private static final BigDecimal VALUE_OF = BigDecimal.valueOf(10.0d);
	private static final String DEVICE_DAD = "device-dad";
	private static final String DEVICE_CHILD = "device-child";
	private static final String TESTE_CARD_ID = "TesteCardId";
	
	@InjectMocks
	private CardRegisterToCardIdentificationConverter cardRegisterToCardIdentificationConverter;

	@Test
	public void testNullable() {
		final CardRegister cardRegister = CardRegisterBuilder.create().build();
		final Balance balance = cardRegisterToCardIdentificationConverter.convert(cardRegister);

		Assert.assertNull(balance.getCardId());
		Assert.assertNull(balance.getDeviceNotificationChildId());
		Assert.assertNull(balance.getDeviceNotificationDadId());
		Assert.assertNull(balance.getValue());
	}

	@Test
	public void test() {
		final CardRegister cardRegister = CardRegisterBuilder.create().withBalance(VALUE_OF)
				.withCardId(TESTE_CARD_ID).withDeviceNotificationChildId(DEVICE_CHILD)
				.withDeviceNotificationDadId(DEVICE_DAD).build();

		final Balance balance = cardRegisterToCardIdentificationConverter.convert(cardRegister);

		Assert.assertEquals(TESTE_CARD_ID, balance.getCardId());
		Assert.assertEquals(DEVICE_CHILD, balance.getDeviceNotificationChildId());
		Assert.assertEquals(DEVICE_DAD, balance.getDeviceNotificationDadId());
		Assert.assertEquals(VALUE_OF, balance.getValue());
	}
}