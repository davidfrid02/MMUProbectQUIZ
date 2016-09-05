package hit.memoryunits;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.hit.algorithm.*;

public class MemoryManagementUnitTest {

	public static MemoryManagementUnit mmu;
	
	// First test case
	@Test
	public void testForExistence() {
		mmu = new MemoryManagementUnit(25, new LRUAlgoCacheImpl<Long,Long>(25));
		try {
			Page<byte[]>[] pages = mmu.getPages(new Long[]{new Long(1)});
			for(Page<byte[]> page : pages) {
				//System.out.println(Arrays.toString(page.getContent()));
				assertTrue(page.getContent() != null);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testForNull() {
		mmu = new MemoryManagementUnit(70, new MRUAlgoCacheImpl<Long,Long>(70));
		try {
			Page<byte[]>[] pages = mmu.getPages(new Long[]{new Long(1001), new Long(1002), new Long(1003)});
			for(Page<byte[]> page : pages) {
				assertTrue(page == null);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	   public static void main(String[] args) {
		      Result result = JUnitCore.runClasses(MemoryManagementUnitTest.class);
		      for (Failure failure : result.getFailures()) {
		          System.out.println(failure.toString());
		       }
		 		
		       System.out.println(result.wasSuccessful());
	   }

}
