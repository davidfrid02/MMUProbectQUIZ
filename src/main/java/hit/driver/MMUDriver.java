package hit.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.AlgoCacheFactory;

import hit.memoryunits.MemoryManagementUnit;

import hit.processes.ProcessCycles;
import hit.processes.RunConfiguration;
import hit.processes.UpdateProcess;
import hit.processes.Process;

public class MMUDriver {

	// public static String CONFIG_FILE_NAME = " ";

	public static void main(String[] args) {

		// =============QUIZ=============

		OutputStream output = null;
		InputStream in = null;
		try {
			output = new FileOutputStream("src/main/resources/configuration/out.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			File initialFile = new File("src/main/resources/configuration/in.txt");
			in = new FileInputStream(initialFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// =====================================================================================

		// CLI cli = new CLI(System.in, System.out);
		CLI cli = new CLI(in, output);
		String[] configuration;

		while ((configuration = cli.getConfiguration()) != null) {

			int capacity = Integer.parseInt(configuration[1]);
			IAlgoCache<Long, Long> algo = AlgoCacheFactory.getAlgo(configuration[0], capacity);

			// build MMU and initial ram (content)

			MemoryManagementUnit mmu = new MemoryManagementUnit(capacity, algo);
			RunConfiguration runConfig = readConfigurationFile(configuration[2]);

			List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
			List<Process> processes = createProcesses(processCycles, mmu);
			runProccesses(processes, mmu);

			// runProcesses will return only after all processes have finished
			// and then we can safely shutdown MMU
			mmu.shutdown();
		}

	}

	public static void runProccesses(List<Process> processes, MemoryManagementUnit mmu) {
		Executor executor = Executors.newCachedThreadPool();
		//QUIZ
		//every three processes i am updating the hardDisk with the content of the ram
		int i = 0;
		for (Process proc : processes) {
			if ((i % 4) == 0) {
				executor.execute(new UpdateProcess(i,mmu));
			} else {
				executor.execute(proc);
			}
			i++;
		}
		((ExecutorService) executor).shutdown();
		try {
			((ExecutorService) executor).awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public static RunConfiguration readConfigurationFile(String configFile) {
		RunConfiguration runConfiguration = null;
		try {
			runConfiguration = new Gson().fromJson(new JsonReader(new FileReader(configFile)), RunConfiguration.class);
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return runConfiguration;
	}

}
