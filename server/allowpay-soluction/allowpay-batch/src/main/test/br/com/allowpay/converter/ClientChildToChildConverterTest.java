package br.com.allowpay.converter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.allowpay.builder.ClientChildBuilder;
import br.com.allowpay.builder.ClientDadBuilder;
import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.Dad;
import br.com.allowpay.entities.builder.DadBuilder;

@RunWith(MockitoJUnitRunner.class)
public class ClientChildToChildConverterTest {

	private static final String DAD_NAME = "dad-name";
	private static final String DAD_CPF = "dad-cpf";
	private static final Long ID = 1L;
	private static final String CHILD_NAME = "child-name";

	@InjectMocks
	private ClientChildToChildConverter clientChildToChildConverter;

	@Mock
	private RegisterToCardRegisterConverter registerToCardRegisterConverter;

	@Mock
	private ClientDadToDadConverter clientDadToDadConverter;

	@Test
	public void testNullable() {

		final ClientChild clientChild = ClientChildBuilder.create().build();
		final Child child = clientChildToChildConverter.convert(clientChild);

		Assert.assertNull(child.getCardRegisters());
		Assert.assertNull(child.getDad());
		Assert.assertNull(child.getFullName());
		Assert.assertNull(child.getId());
	}

	@Test
	public void testOrphanChild() {

		final ClientChild clientChild = ClientChildBuilder.create().withName(CHILD_NAME).withId(ID).build();

		final Child child = clientChildToChildConverter.convert(clientChild);

		Assert.assertEquals(CHILD_NAME, child.getFullName());
		Assert.assertEquals(ID, child.getId());
	}

	@Test
	public void testChildWithDad() {

		final ClientDad clientDad = ClientDadBuilder.create().withCPF(DAD_CPF).withName(DAD_NAME).build();

		final Date createDate = new Date(System.currentTimeMillis());
		final Dad dad = DadBuilder.create().withCpf(DAD_CPF).withFullName(DAD_NAME).withCreateDate(createDate).build();
		Mockito.when(clientDadToDadConverter.convert(clientDad)).thenReturn(dad);

		final ClientChild clientChild = ClientChildBuilder.create().withName(CHILD_NAME).withId(ID)
				.withClientDad(clientDad).build();

		final Child child = clientChildToChildConverter.convert(clientChild);

		Assert.assertNotNull(child.getDad());
		Assert.assertEquals(DAD_CPF, child.getDad().getCpf());
		Assert.assertEquals(DAD_NAME, child.getDad().getFullName());
		Assert.assertEquals(createDate, child.getDad().getCreateDate());

		Assert.assertEquals(CHILD_NAME, child.getFullName());
		Assert.assertEquals(ID, child.getId());
	}
}