package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedOutputStream;

public class HardDisk {
	
	static String DEFAULT_FILE_NAME = "HardDisk.txt";	
	static int _SIZE = 1000;
	private static HardDisk instance = new HardDisk();	
	private Map<Long, Page<byte[]>> pages;
	
	private HardDisk() {
		pages = new HashMap<>(_SIZE);
		for(long i=1;i<_SIZE;i++) {
			pages.put(i, new Page<byte[]>(i,new byte[]{(byte)i}));
		}
		try {
			writeHd();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			throw e;
		}
	}

}