public class Collection {
	private String header;
	private String[] fields;

	public Collection (int size) {

	}

	public Collection (String collectionName, int size) {
		this.header = collectionName;
		this.fields = new String[size];


		CSVReader.readFile("../src/" + collectionName + "csv");
	}

	public String getHeader() {
		return this.header;
	}

	public String[] getFields() {
		return this.fields;
	}
}
