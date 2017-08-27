package br.com.allowpay.converter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import br.com.allowpay.entities.builder.ChildBuilder;

@RunWith(MockitoJUnitRunner.class)
public class ClientDadToDadConverterTest {

	private static final String DAD_NAME = "dad-name";
	private static final String DAD_CPF = "dad-cpf";
	private static final Long ID = 1L;
	private static final String CHILD_NAME = "child-name";

	@InjectMocks
	private ClientDadToDadConverter clientDadToDadConverter;

	@Mock
	private ClientChildToChildConverter clientChildToChildConverter;

	@Test
	public void testNullable() {
		final ClientDad clientDad = ClientDadBuilder.create().build();
		final Dad dad = clientDadToDadConverter.convert(clientDad);

		Assert.assertNull(dad.getCpf());
		Assert.assertNull(dad.getFullName());
		Assert.assertNull(dad.getChilds());
		Assert.assertNull(dad.getCreateDate());
	}

	@Test
	public void testWithChilds() {
		final Date createDate = new Date(System.currentTimeMillis());

		final ClientChild clientChild = ClientChildBuilder.create().withName(CHILD_NAME).withId(ID).build();
		
		final Child child = ChildBuilder.create().withFullName(CHILD_NAME).withId(ID).build();
		Mockito.when(clientChildToChildConverter.convert(clientChild)).thenReturn(child);
		
		final List<ClientChild> clientChilds = Arrays.asList(clientChild);

		final ClientDad clientDad = ClientDadBuilder.create().withCPF(DAD_CPF).withName(DAD_NAME)
				.withCreateDate(createDate).withClientChilds(clientChilds).build();

		final Dad dad = clientDadToDadConverter.convert(clientDad);

		Assert.assertEquals(DAD_CPF, dad.getCpf());
		Assert.assertEquals(DAD_NAME, dad.getFullName());
		Assert.assertEquals(clientChilds.get(0).getName(), dad.getChilds().get(0).getFullName());
		Assert.assertEquals(clientChilds.get(0).getId(), dad.getChilds().get(0).getId());
		Assert.assertEquals(createDate, dad.getCreateDate());
	}

	@Test
	public void test() {
		final Date createDate = new Date(System.currentTimeMillis());

		final ClientDad clientDad = ClientDadBuilder.create().withCPF(DAD_CPF).withName(DAD_NAME)
				.withCreateDate(createDate).build();
		final Dad dad = clientDadToDadConverter.convert(clientDad);

		Assert.assertEquals(DAD_CPF, dad.getCpf());
		Assert.assertEquals(DAD_NAME, dad.getFullName());
		Assert.assertEquals(createDate, dad.getCreateDate());
	}
}