package br.com.allowpay.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.allowpay.builder.ClientChildBuilder;
import br.com.allowpay.builder.RegisterBuilder;
import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.Register;
import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.builder.ChildBuilder;

@RunWith(MockitoJUnitRunner.class)
public class RegisterToCardRegisterConverterTest {
	
	private static final long CLIENT_ID = 1L;
	private static final String DEVICE_DAD = "device-dad";
	private static final String DEVICE_CHILD = "device-child";
	private static final double BALANCE_VALUE = 10.0d;
	private static final String CARD_ID = "card-id";
	private static final String CHILD_NAME = "child-name";

	@InjectMocks
	private RegisterToCardRegisterConverter registerToCardRegisterConverter;
	
	@Mock
	private ClientChildToChildConverter clientChildToChildConverter;

	@Test
	public void testNullable() {
		final Register register = RegisterBuilder.create().build();
		final CardRegister cardRegister = registerToCardRegisterConverter.convert(register);
		
		Assert.assertNull(cardRegister.getCardId());
		Assert.assertNull(cardRegister.getDeviceNotificationChildId());
		Assert.assertNull(cardRegister.getDeviceNotificationDadId());
		Assert.assertNull(cardRegister.getBalance());
		Assert.assertNull(cardRegister.getChild());
	}
	
	@Test
	public void test() {
		final ClientChild clientChild = ClientChildBuilder.create().withName(CHILD_NAME).withId(CLIENT_ID).build();
		
		final Child child = ChildBuilder.create().withFullName(CHILD_NAME).withId(CLIENT_ID).build();
		Mockito.when(clientChildToChildConverter.convert(clientChild)).thenReturn(child);
		
		final Register register = RegisterBuilder
				.create()
				.withId(CARD_ID)
				.withBalanceValue(BigDecimal.valueOf(BALANCE_VALUE))
				.withDeviceNotificationChildId(DEVICE_CHILD)
				.withDeviceNotificationDadId(DEVICE_DAD)
				.withClientChild(clientChild)
				.build();
				
		final CardRegister cardRegister = registerToCardRegisterConverter.convert(register);
		
		Assert.assertEquals(CARD_ID, cardRegister.getCardId());
		Assert.assertEquals(DEVICE_CHILD, cardRegister.getDeviceNotificationChildId());
		Assert.assertEquals(DEVICE_DAD, cardRegister.getDeviceNotificationDadId());
		Assert.assertEquals(BigDecimal.valueOf(BALANCE_VALUE), cardRegister.getBalance());
		Assert.assertEquals(child, cardRegister.getChild());
	}
}