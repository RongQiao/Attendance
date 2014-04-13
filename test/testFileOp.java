import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import TechnicalService.CSVFile;


public class testFileOp {
	private String fileName = "course.csv";
	
	@Test
	public void testFileReadWrite() {
		CSVFile csvFile = new CSVFile(fileName);
		List<String> buf = new ArrayList<String>();
		if (csvFile.read(buf) == 0) {
			csvFile.write("1");
			assertTrue(csvFile.read(buf) == 1);
		}
	}

}

