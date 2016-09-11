package hit.processes;

import hit.memoryunits.MemoryManagementUnit;

public class UpdateProcess implements Runnable{

	private MemoryManagementUnit mmu;
	private int id;
	
	/***
	 * get the mmu
	 * @param mmu
	 */
	public UpdateProcess(int id, MemoryManagementUnit mmu) {
		this.mmu = mmu;
		this.id = id;
	}

	@Override
	public void run() {
		//update the hardDisk with the content of the ram
		synchronized (mmu) {
			mmu.shutdown();
			
		}
		
		
	}
}
