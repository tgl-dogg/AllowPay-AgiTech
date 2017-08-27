package br.com.allowpay.integrator.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.builder.ExtractBuilder;
import br.com.allowpay.canonical.Extract;
import br.com.allowpay.integrator.dtos.ExtractValuesDto;

@Component
public class ExtractDtoToFullExtractConverter {
	
	public Extract convert(final ExtractValuesDto extractDto){
		final Integer value = extractDto.getValor();
		final String date = extractDto.getDataHora();
		final String merchant = extractDto.getEstabelecimento();
		final String type = extractDto.getTipo();

		return ExtractBuilder.create() //
			.withAmount(value) //
			.withDate(date) //
			.withMerchant(merchant) //
			.withType(type) //
			.build();
			
	}

}
