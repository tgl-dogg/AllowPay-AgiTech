package br.com.allowpay.rules;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class CompareBalanceValue {

	public BigDecimal balanceDiference(final BigDecimal balanceAgilitar, final BigDecimal balanceAllowPay) {
		if (balanceAgilitar != null) {
			return balanceAgilitar.subtract(balanceAllowPay);
		} else {
			return BigDecimal.ZERO;
		}
	}

	public Boolean balanceDiferenceZero(final BigDecimal bigDecimal) {
		if (bigDecimal != null) {
			return bigDecimal.compareTo(BigDecimal.ZERO) != 0;
		} else {
			return Boolean.FALSE;
		}
	}
}