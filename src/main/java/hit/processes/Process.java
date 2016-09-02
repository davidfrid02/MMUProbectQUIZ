package hit.processes;

import java.io.IOException;
import java.util.List;

import hit.memoryunits.MemoryManagementUnit;
import hit.memoryunits.Page;

public class Process implements Runnable {

	private int id;
	private MemoryManagementUnit mmu;
	ProcessCycles processCycles;

	public Process(int id, MemoryManagementUnit mmu, ProcessCycles processCycles) {
		this.id = id;
		this.mmu = mmu;
		this.processCycles = processCycles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		List<ProcessCycle> processCyclesList = processCycles.getProcessCycles();
		Page<byte[]>[] pages;
		for (ProcessCycle procCyc : processCyclesList) {
			// retrieve the pages relevant to this process cycle
			try {
				pages = mmu.getPages(procCyc.getPages().toArray(new Long[procCyc.getPages().size()]));
				// iterate over the pages and update the data according to the
				// data list
				for (int i = 0; i < pages.length; i++) {
					pages[i].setContent(procCyc.getData().get(i));
				}
				// go to sleep until next process cycle
				try {
					Thread.sleep(procCyc.getSleepMs());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
