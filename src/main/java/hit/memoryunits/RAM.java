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

	public void addPage(Page<byte[]> addPage) {
		if(addPage != null)
			pages.put(addPage.getPageId(), addPage);
	}

	public void addPages(Page<byte[]>[] addPages) {
		for (Page<byte[]> page : addPages) {
			this.addPage(page);
		}
	}

	public Page<byte[]> getPage(Long pageId) {
		return pages.get(pageId);
	}

	@SuppressWarnings("unchecked")
	public Page<byte[]>[] getPages(Long[] pageIds) {
		List<Page<byte[]>> returnPages = new LinkedList<Page<byte[]>>();
		for (long id : pageIds) {
			returnPages.add(pages.get(id));
		}
		return returnPages.toArray((Page<byte[]>[]) new Page<?>[pageIds.length]);
	}
	
	@SuppressWarnings("unchecked")
	public Page<byte[]>[] getAllPages() {
		List<Page<byte[]>> returnPages = new LinkedList<Page<byte[]>>();
		for (long id : pages.keySet()) {
			returnPages.add(pages.get(id));
		}
		return returnPages.toArray((Page<byte[]>[]) new Page<?>[pages.size()]);
	}

	public void removePage(Page<byte[]> removePage) {
		pages.remove(removePage.getPageId());
	}

	public void removePages(Page<byte[]>[] removePages) {
		for (Page<byte[]> page : removePages) {
			this.removePage(page);
		}
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
}
