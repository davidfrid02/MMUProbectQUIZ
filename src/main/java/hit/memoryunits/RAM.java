package hit.memoryunits;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RAM {

	private int initialCapacity;
	private Map<Long, Page<byte[]>> pages;

	public RAM(int initialCapacity) {
		this.initialCapacity = initialCapacity;
		pages = new HashMap<>(this.initialCapacity);
	}

	void addPage(Page<byte[]> addPage) {
		// need to check what to do with addPage - is actually an object so
		// putting it will reference it
		// what if we delete it from the main program?
		pages.put(addPage.getPageId(), addPage);
	}

	void addPages(Page<byte[]>[] addPages) {
		for (Page<byte[]> page : addPages) {
			this.addPage(page);
		}
	}

	Page<byte[]> getPage(Long pageId) {
		return pages.get(pageId);
	}

	Page<byte[]>[] getPages(Long[] pageIds) {
		List<Page<byte[]>> returnPages = new LinkedList<Page<byte[]>>();
		for (long id : pageIds) {
			returnPages.add(pages.get(id));
		}
		return returnPages.toArray((Page<byte[]>[]) new Page<?>[pageIds.length]);
	}

	void removePage(Page<byte[]> removePage) {
		pages.remove(removePage.getPageId());
	}

	void removePages(Page<byte[]>[] removePages) {
		for (Page<byte[]> page : removePages) {
			this.removePage(page);
		}
	}

	void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
}
