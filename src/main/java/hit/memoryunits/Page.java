package hit.memoryunits;

public class Page<T> implements java.io.Serializable {

	private T content;
	private Long pageId;

	public Page(Long id, T content) {
		this.pageId = id;
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	@Override
	public String toString() {
		return pageId.toString() + " " + content.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (int)(pageId^(pageId >>> 32));
		return result;
	}
}
