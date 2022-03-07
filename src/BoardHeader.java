/**
 * @author Arjun & Christian
 */
public class BoardHeader {
	private Collection data;

	public BoardHeader(int size) {
		this.data = new Collection(size);
	}

	BoardHeader(Collection data) {
		this.data = data;
	}

	public Collection getData() {
		return this.data;
	}
}
