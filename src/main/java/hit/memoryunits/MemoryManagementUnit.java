package hit.memoryunits;

import java.io.IOException;

import com.hit.algorithm.IAlgoCache;

public class MemoryManagementUnit {

	private IAlgoCache<Long, Long> algo;
	private RAM ram;

	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo) {
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
	}

	public synchronized Page<byte[]>[] getPages(Long[] pageIds) throws IOException { // need to insert try/catch statements
		// iterating over the pageIds requested by a process
		Long moveToHdPageId;
		Page<byte[]> moveToHdPage;
		for (Long pageId : pageIds) {
			// if the page is not present in RAM
			if (algo.getElement(pageId) == null) {
				// if the RAM is not full
				moveToHdPageId = algo.putElement(pageId, pageId);
				if(moveToHdPageId == null){
					// adding the page to the RAM
					ram.addPage(HardDisk.getInstance().pageFault(pageId));				
				}
				// otherwise need to replace a page
				else{
					// retrieve the page itself from the ram and remove it from there
					moveToHdPage = ram.getPage(moveToHdPageId);
					ram.removePage(moveToHdPage);
					// make the actual replacement
					ram.addPage(HardDisk.getInstance().pageReplacement(moveToHdPage, pageId));
				}
			}
		}
		// after all the replacements have been done - return all the pages from the RAM
		return ram.getPages(pageIds);
	}

	public RAM getRam() {
		return ram;
	}

	public IAlgoCache<Long, Long> getAlgo() {
		return algo;
	}
	
	// this method will be called when the MMU is shutting down
	public void shutdown() {
		Page<byte[]>[] pages = ram.getAllPages();
		HardDisk.getInstance().insertMultiplePages(pages);
	}

//	public void setRam(RAM ram) {
//		this.ram = ram;
//	}

//	public void setAlgo(IAlgoCache<Long, Long> algo) {
//		this.algo = algo;
//	}

}
