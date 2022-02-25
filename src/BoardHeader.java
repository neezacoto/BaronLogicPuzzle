/**
 * @author Arjun & Christian
 */
public class BoardHeader {
	private Collection data;

	BoardHeader(int size) {
		this.data = new Collection(size);
	}

	BoardHeader(Collection data) {
		this.data = data;
	}

	public Collection getData() {
		return this.data;
	}
}
