package TechnicalService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Logical.ApplicationLogical.ClassInfo;
import Logical.DomainBase.Course;
import Logical.DomainBase.SchoolClass;
import Logical.DomainBase.Student;

public class CSVFile extends File{
	public CSVFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int[] getCommaIndexs(String content, int size) {
		int[] indexs = new int[size];
		indexs[0] = 0;
		int i = 1;
		int indexComma = content.indexOf(',');
		while (indexComma >= 0) {			
			indexs[i++] = indexComma;
			indexComma = content.indexOf(',', indexComma+1);
		}
		return indexs;
	}

	public static String format(Course crs) {
		String crsStr = "";
		crsStr += crs.getCourseID();
		crsStr += ",";
		crsStr += crs.getCourseName();
		crsStr += ",";
		crsStr += crs.getDescription();
		
		return crsStr;
	}

	public static void format(String line, Course crs) {
		int[] indexs = new int[3];
		indexs[0] = 0;
		int i = 1;
		int indexComma = line.indexOf(',');
		while (indexComma >= 0) {			
			indexs[i++] = indexComma;
			indexComma = line.indexOf(',', indexComma+1);
		}
		crs.setCourseID(line.substring(indexs[0], indexs[1]));
		crs.setCourseName(line.substring(indexs[1]+1, indexs[2]));
		crs.setDescription(line.substring(indexs[2]+1));
	}

	public static String format(SchoolClass cls) {
		String clsStr = "";
		clsStr += cls.getClassNumber();
		clsStr += ",";
		clsStr += cls.getCourse().getCourseID();
		clsStr += ",";
		clsStr += cls.getClassSection();
		
		return clsStr;
	}
	
	public static void format(String line, SchoolClass cls) {
		int[] indexs = new int[3];
		indexs[0] = 0;
		int i = 1;
		int indexComma = line.indexOf(',');
		while (indexComma >= 0) {			
			indexs[i++] = indexComma;
			indexComma = line.indexOf(',', indexComma+1);
		}
		cls.setClassNumber(line.substring(indexs[0], indexs[1]));
		Course crs = new Course();
		crs.setCourseID(line.substring(indexs[1]+1, indexs[2]));
		cls.setCourse(crs);
		cls.setClassSection(line.substring(indexs[2]+1));
	}
	
	public static String format(Student std) {
		String stdStr = "";
		stdStr += std.getStudentID();
		stdStr += ",";
		stdStr += std.getFirstName();
		stdStr += ",";
		stdStr += std.getLastName();
		stdStr += ",";
		stdStr += std.getMiddleName();
		
		return stdStr;
	}
	
	public static void format(String stdStr, Student std) {
		int[] indexs = getCommaIndexs(stdStr, 4);
		std.setStudentID(stdStr.substring(indexs[0], indexs[1]));
		std.setFirstName(stdStr.substring(indexs[1]+1, indexs[2]));
		std.setLastName(stdStr.substring(indexs[2]+1, indexs[3]));
		std.setMiddleName(stdStr.substring(indexs[3]+1));
	}

	
	public void write(String data) {
		writeFile(data, false);
	}
	
	public void append(String data) {
		writeFile(data, true);
	}
	
	private void writeFile(String data, boolean isAppend) {
		if (!exists()) {
			try {
				createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedWriter bfWriter = null;
		try {
			FileWriter flWriter = null;
			if (isAppend) {
				flWriter = new FileWriter(this, true);
			}
			else {
				flWriter = new FileWriter(this);
			}
			bfWriter = new BufferedWriter(flWriter);
			if (isAppend) {
				bfWriter.newLine();
			}
			bfWriter.write(data);
			bfWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bfWriter != null) bfWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public int read(List<String> buf) {	
		if (buf == null) {
			return 0;
		}
		buf.clear();
		if (exists()) {
			BufferedReader bfReader = null;
			try {
				FileReader flReader = new FileReader(this);
				bfReader = new BufferedReader(flReader);
				String line = null;
				while ((line = bfReader.readLine()) != null) {
					buf.add(line);
				}
				bfReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (bfReader != null) bfReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return buf.size();
	}



}
