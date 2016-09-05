package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class HardDisk {
	
	static String DEFAULT_FILE_NAME = "HardDisk.txt";	
	static int _SIZE = 1000;
	private static HardDisk instance = new HardDisk();	
	private Map<Long, Page<byte[]>> pages;
	
	private HardDisk() {
		File f = new File(DEFAULT_FILE_NAME);
		// if the file exists - read the hard disk from it
		if (f.exists()) {
			try {
				FileInputStream hardDiskFile = new FileInputStream(DEFAULT_FILE_NAME);
				ObjectInputStream objIn = new ObjectInputStream(new BufferedInputStream(hardDiskFile));
				pages = (Map<Long, Page<byte[]>>) objIn.readObject();
				objIn.close();
				hardDiskFile.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO implement exception
				e.printStackTrace();
			}
		}
		// else - create and initialize a new hard disk with some default values
		else {
			pages = new HashMap<>(_SIZE);
			for (long i = 1; i < _SIZE; i++) {
				pages.put(i, new Page<byte[]>(i, new byte[] { (byte) i }));
			}
			try {
				writeHd();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static HardDisk getInstance() {
		return instance;
	}

	Page<byte[]> pageFault(Long pageId) throws FileNotFoundException, IOException {
		return pages.get(pageId);
	}

	Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId)
			throws FileNotFoundException, IOException {
		Page<byte[]> pageToReplace = pages.get(moveToRamId);
		// updating the content of the page that is inserted into the HD
		pages.put(moveToHdPage.getPageId(), moveToHdPage);
		
		// writing the map to the HD (file)
		writeHd();
		return pageToReplace;
	}

	private void writeHd() throws FileNotFoundException,IOException {
		try {
			FileOutputStream hardDiskFile = new FileOutputStream(DEFAULT_FILE_NAME);
			ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(hardDiskFile));
			objOut.writeObject(pages);
			objOut.close();
			hardDiskFile.close();
		} catch (IOException e) {
			//TODO implement exception
			throw e;
		}
	}
	
	public void insertMultiplePages(Page<byte[]>[] ramPages) {
		// updates the pages in HD with the updated page from RAM
		for(Page<byte[]> page : ramPages) {
			pages.put(page.getPageId(), page);
		}
		try {
			writeHd();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}