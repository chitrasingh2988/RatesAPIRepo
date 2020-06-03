package resource;

public enum APIResources {

	getRateAPI("/latest"), getWrongURL("/wonrgEndPoint");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;

	}

	public String getResource() {

		return resource;
	}

}
