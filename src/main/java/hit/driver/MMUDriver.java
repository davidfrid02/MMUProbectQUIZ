package hit.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.AlgoCacheFactory;

import hit.memoryunits.MemoryManagementUnit;
import hit.processes.ProcessCycle;
import hit.processes.ProcessCycles;
import hit.processes.RunConfiguration;
import hit.processes.Process;

public class MMUDriver {

	public static String CONFIG_FILE_NAME = "config.json";

	public static void main(String[] args) {
		// need to check which throws to add (the api has 2)
		// TODO Auto-generated method stub

		CLI cli = new CLI(System.in, System.out);
		String[] configuration;
		while ((configuration = cli.getConfiguration()) != null) {
			// initialize algo and capacity
			
			int capacity = Integer.parseInt(configuration[1]);
			IAlgoCache<Long, Long> algo = AlgoCacheFactory.getAlgo(configuration[0],capacity);

			// build MMU and initial ram (content)

			MemoryManagementUnit mmu = new MemoryManagementUnit(capacity, algo);

			RunConfiguration runConfig = readConfigurationFile();

			List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
			List<Process> processes = createProcesses(processCycles, mmu);
			runProccesses(processes);

			// need to check that all processes finished before shutting down
			// the mmu
			//mmu.shutdown();
		}

	}

	public static void runProccesses(List<Process> processes) {
		Executor executor = Executors.newCachedThreadPool();
		for (Process proc : processes) {
			executor.execute(proc);
		}
		((ExecutorService) executor).shutdown();

	}

	public static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu) {
		List<Process> processList = new ArrayList<Process>(processCycles.size());
		int id = 1;
		for (ProcessCycles procCyc : processCycles) {
			processList.add(new Process(id, mmu, procCyc));
			id++;
		}

		return processList;
	}

	public static RunConfiguration readConfigurationFile() {
		RunConfiguration runConfiguration = null;
		try {
			runConfiguration = new Gson().fromJson(new JsonReader(new FileReader(CONFIG_FILE_NAME)),
					RunConfiguration.class);
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return runConfiguration;
	}

}
