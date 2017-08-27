package br.com.allowpay.dtos;

public class FullExtractDto extends ExtractDto {

	private static final long serialVersionUID = -2351200876485273954L;

	private String date;

	private String merchant;

	private String type;

	private CoordinateDto coordinate;

	public FullExtractDto() {
		super();
	}

	public FullExtractDto(final String cardId, final Integer value, final String date, final String merchant,
			final String type, final CoordinateDto coordinate) {
		super(cardId, value);
		this.date = date;
		this.merchant = merchant;
		this.type = type;
		this.coordinate = coordinate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CoordinateDto getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(CoordinateDto coordinate) {
		this.coordinate = coordinate;
	}

}