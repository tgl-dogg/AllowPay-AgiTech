package br.com.allowpay.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.Extract;
import br.com.allowpay.dtos.CoordinateDto;
import br.com.allowpay.dtos.FullExtractDto;

@Component
public class ExtractToFullExtractDtoConverter {

	public FullExtractDto convert(final Extract extract) {
		final String cardId = null;
		final Integer value = extract.getAmount();
		final String date = extract.getDate();
		final String merchant = extract.getMerchant();
		final String type = extract.getType();
		// FIXME: Localização fixa pois a api da agilitas não retorna
		// localização. Ajustar com a evolução da solução.
		final CoordinateDto coordinateDefault = new CoordinateDto(-22.8174189, -47.0408599);

		final FullExtractDto fullExtractDto = new FullExtractDto(cardId, value, date, merchant, type,
				coordinateDefault);
		return fullExtractDto;
	}
}
