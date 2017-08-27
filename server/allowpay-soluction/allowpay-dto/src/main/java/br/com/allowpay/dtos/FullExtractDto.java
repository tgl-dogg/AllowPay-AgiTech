package br.com.allowpay.dtos;

public class FullExtract extends Extract {

	private static final long serialVersionUID = -2351200876485273954L;

	private String date;

	private String merchant;

	private String type;

	private Coordinate coordinate;

	public FullExtract() {
		super();
	}

	public FullExtract(final String cardId, final Integer value, final String date, final String merchant,
			final String type, final Coordinate coordinate) {
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

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

}