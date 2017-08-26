package br.com.allowpay.canonical;

public class Client {

	private final String identify;

	private final String name;

	public Client(String identify, String name) {
		super();
		this.identify = identify;
		this.name = name;
	}

	public String getIdentify() {
		return identify;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identify == null) ? 0 : identify.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (identify == null) {
			if (other.identify != null)
				return false;
		} else if (!identify.equals(other.identify))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}