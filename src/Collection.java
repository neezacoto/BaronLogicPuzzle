public class Collection {
	private String header;
	private String[] fields;

	Collection (int size) {

	}

	Collection (String collectionName) {
		this.header = collectionName;
		this.fields = CSVReader.readFile("../src/" + collectionName + "csv");
	}

	public String getHeader() {
		return this.header;
	}

	public String[] getFields() {
		return this.fields;
	}
}
