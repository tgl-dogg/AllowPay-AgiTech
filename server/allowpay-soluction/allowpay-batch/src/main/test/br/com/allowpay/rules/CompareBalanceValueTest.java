package br.com.allowpay.rules;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompareBalanceValueTest {

	@InjectMocks
	private CompareBalanceValue compareBalanceValue;

	@Test
	public void testPositiveBalance() {
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(BigDecimal.TEN, BigDecimal.ONE);
		Assert.assertEquals(BigDecimal.valueOf(9), balanceDiference);
	}
	
	@Test
	public void testNegativeBalance() {
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(BigDecimal.ONE, BigDecimal.TEN);
		Assert.assertEquals(BigDecimal.valueOf(-9), balanceDiference);
	}
	
	@Test
	public void testBalanceAgilitarNull() {
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(null, BigDecimal.ONE);
		Assert.assertEquals(BigDecimal.ZERO, balanceDiference);
	}
	
	@Test
	public void testBalanceAllowPayNull() {
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(BigDecimal.TEN, null);
		Assert.assertEquals(BigDecimal.TEN, balanceDiference);
	}
	
	@Test
	public void testBalanceNullables() {
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(null, null);
		Assert.assertEquals(BigDecimal.ZERO, balanceDiference);
	}
	
	@Test
	public void balanceDiferenceZeroIsTrue() {
		Assert.assertTrue(compareBalanceValue.balanceDiferenceZero(BigDecimal.ZERO));
	}
	
	@Test
	public void balanceDiferenceZeroIsFalse() {
		Assert.assertFalse(compareBalanceValue.balanceDiferenceZero(BigDecimal.TEN));
	}
	
	@Test
	public void balanceDiferenceZeroNullable() {
		Assert.assertFalse(compareBalanceValue.balanceDiferenceZero(null));
	}
}