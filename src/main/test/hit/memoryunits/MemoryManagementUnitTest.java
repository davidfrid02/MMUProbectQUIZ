package hit.memoryunits;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.hit.algorithm.LRUAlgoCacheImpl;

public class MemoryManagementUnitTest {

	public static MemoryManagementUnit mmu;
	
	// Initializing MMU prior to all tests
	@BeforeClass
	public static void beforeClass() {
		mmu = new MemoryManagementUnit(25, new LRUAlgoCacheImpl<Long,Long>(25));
	}
	
	// First test case
	@Test
	public void testCase1() {
		try {
			Page<byte[]>[] pages = mmu.getPages(new Long[]{new Long(1)});
			for(Page<byte[]> page : pages) {
				System.out.println(Arrays.toString(page.getContent()));
				assertEquals(page.getPageId(), new Long(1));
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCase2() {
		try {
			Page<byte[]>[] pages = mmu.getPages(new Long[]{new Long(2)});
			for(Page<byte[]> page : pages) {
				assertEquals(Arrays.toString(page.getContent()), "[2]");
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
