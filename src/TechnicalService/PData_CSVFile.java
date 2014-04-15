/**
 * 
 */
package TechnicalService;

import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Logical.ApplicationLogical.AttdRecordInfo;
import Logical.ApplicationLogical.PDataAgent;
import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.StudentInfo;
import Logical.DomainBase.Course;
import Logical.DomainBase.CourseEnrollment;
import Logical.DomainBase.SchoolClass;
import Logical.DomainBase.Student;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class PData_CSVFile extends PDataAgent {
	private static final String FN_COURSE = "course.csv";
	private static final String FN_CLASS = "class.csv";
	private static final String FN_STUDENT = "student.csv";
	private static final String FN_ENROLL = "enroll.csv";

	@Override
	public void addData(Course crs) {
		String data = CSVFile.format(crs);		
		addData(FN_COURSE, data);
	}

	@Override
	public void addData(SchoolClass cls) {
		String data = CSVFile.format(cls);
		addData(FN_CLASS, data);
	}
	
	@Override
	public void addData(Student std) {
		String data = CSVFile.format(std);
		addData(FN_STUDENT, data);
	}
	
	@Override
	public void addData(List<CourseEnrollment> erlList) {
		String data = "";
		int index = 0;
		for (CourseEnrollment item: erlList) {
			String erlStr = CSVFile.format(item);
			data += erlStr;
			if (index != erlList.size()-1) {
				data += "\n";
			}
			index++;
		}
		addData(FN_ENROLL, data);
	}
	
	/*
	 * save every new attendance record on a new csv file with the date in the file name
	 * and all records of one class is saved in the same directory which is named with the course name + class number
	 */
	@Override
	public void addData(ClassInfo currentCourse, Date arDate, List<AttdRecordInfo> infoList) {
		//directory
		String crsDirName = getDirName(currentCourse);
		File dir = new File(crsDirName);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdir();
		}
		//file
		String arFileName = getArFileName(crsDirName, currentCourse, arDate);
		String data = "";
		int i = 0;
		for (AttdRecordInfo ar: infoList) {
		    String line = CSVFile.format(ar);
		    data += line;
		    if (i < infoList.size()-1) {
		 	   data += "\n";
		    }
	    }
	    addData(arFileName, data);
	}
		
	private String getArFileName(String crsDirName, ClassInfo crs, Date arDate) {
		String name = crsDirName + "/";
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		name += shortName(crs);
		name += "_";
		name += dateFormat.format(arDate);
		name += ".csv";
		return name;
	}

	private String getDirName(ClassInfo crs) {
		String name = shortName(crs);
		name += crs.getClassNumber();
		return name;
	}
	
	private String shortName(ClassInfo crs) {
		String name = "";
		String crsName = crs.getCourseName();
		while (crsName.length()>0) {
			name += crsName.charAt(0);
			int idxSpace = crsName.indexOf(' ');
			if ((idxSpace > 0) && (idxSpace < crsName.length()-1)) {
				crsName = crsName.substring(idxSpace+1);
			}
			else {
				break;
			}
		}
		return name;
	}
	
	private void addData(String fileName, String data) {
		CSVFile file = new CSVFile(fileName);
		boolean isAppend = false;
		if (file.exists()) {
			List<String> buf = new ArrayList<String>();
			file.read(buf);
			if (buf.size()>0) {
				boolean dataExist = false;
				for (String tmpStr: buf) {
					if (data.indexOf(tmpStr)>=0) {
						dataExist = true;
						break;
					}
				}
				if (!dataExist) {
					isAppend = true;
				}						
			}
		}
		if (isAppend) {
			file.append(data);
		}
		else {
			file.write(data);
		}
	}

	@Override
	public void getDataCourse(List<ClassInfo> listCrs) {
		if (listCrs != null) {
			listCrs.clear();
			//get all of classes
			CSVFile clsFile = new CSVFile(FN_CLASS);
			List<String> clsLines = new ArrayList<String>();
			clsFile.read(clsLines);
			CSVFile crsFile = new CSVFile(FN_COURSE);
			List<String> crsLines = new ArrayList<String>();
			crsFile.read(crsLines);
			for (String clsStr: clsLines) {
				SchoolClass cls = new SchoolClass();
				CSVFile.format(clsStr, cls);
				Course crs = cls.getCourse();
				for (String crsStr: crsLines) {
					if (crsStr.indexOf(crs.getCourseID()) >= 0) {
						CSVFile.format(crsStr, crs);	
					}				
				}
	
				ClassInfo cInfo = new ClassInfo();
				cInfo.setClassNumber(cls.getClassNumber());
				cInfo.setCourseNumber(crs.getCourseID());
				cInfo.setCourseSection(cls.getClassSection());
				cInfo.setCourseName(crs.getCourseName());
				listCrs.add(cInfo);
			}			
		}
	}

	@Override
	public void getDataStudent(List<StudentInfo> listStd) {
		if (listStd != null) {
			listStd.clear();
			//get all of students
			CSVFile file = new CSVFile(FN_STUDENT);
			List<String> lines = new ArrayList<String>();
			file.read(lines);
			for (String stdStr: lines) {
				Student std = new Student();
				CSVFile.format(stdStr, std);
	
				StudentInfo info = new StudentInfo();
				info.setStudentId(std.getStudentID());
				info.setFirstName(std.getFirstName());
				info.setLastName(std.getLastName());
				info.setMiddleName(std.getMiddleName());
				listStd.add(info);
			}
		}
	}

	@Override
	public void getDataEnroll(List<CourseEnrollment> erlList) {
		if (erlList != null) {
			erlList.clear();
			//get all of students
			CSVFile file = new CSVFile(FN_ENROLL);
			List<String> lines = new ArrayList<String>();
			file.read(lines);
			for (String erlStr: lines) {
				CourseEnrollment erl = new CourseEnrollment();
				CSVFile.format(erlStr, erl);	
				erlList.add(erl);
			}
		}
	}

	@Override
	public void getDataAttdRecord(ClassInfo currentCourse, Date arDate,
			List<AttdRecordInfo> infoList) {
		if (infoList != null) {
			infoList.clear();
			//directory
			String crsDirName = getDirName(currentCourse);
			File dir = new File(crsDirName);
			if (dir.exists() && dir.isDirectory()) {
				//file
				String arFileName = getArFileName(crsDirName, currentCourse, arDate);
				CSVFile file = new CSVFile(arFileName);
				List<String> lines = new ArrayList<String>();
				file.read(lines);
				for (String arStr: lines) {
					AttdRecordInfo ar = new AttdRecordInfo();
					CSVFile.format(arStr, ar);
					infoList.add(ar);
				}
			}
		}
	}
	
	@Override
	public void removeData(SchoolClass cls) {
		//get all of classes
		CSVFile clsFile = new CSVFile(FN_CLASS);
		List<String> clsLines = new ArrayList<String>();
		clsFile.read(clsLines);
		boolean found = false;
		boolean needRemoveCourse = true;
		for (String tmpStr: clsLines) {
			if (tmpStr.indexOf(cls.getClassNumber()) >= 0) {
				clsLines.remove(tmpStr);
				found = true;
				break;
			}
		}
		if (found) {
			String data = "";
			for (String tmpStr: clsLines) {
				data += tmpStr;
				if (!tmpStr.equals(clsLines.get(clsLines.size()-1))) {
					data += "\n";
				}		
				//check if there is class which has same course with the removed one, if yes, then don't remove course
				if (tmpStr.indexOf(cls.getCourse().getCourseID()) >= 0) {
					needRemoveCourse = false;
				}
			}
			clsFile.write(data);
		}
		if (needRemoveCourse) {
			removeData(cls.getCourse());
		}
	}

	private String lineList2String(List<String> lines) {
		String data = "";
		for (String tmpStr: lines) {
			data += tmpStr;
			if (!tmpStr.equals(lines.get(lines.size()-1))) {
				data += "\n";
			}		
		}
		return data;
	}
	
	public void removeData(Course crs) {
		//get all of courses
		CSVFile crsFile = new CSVFile(FN_COURSE);
		List<String> crsLines = new ArrayList<String>();
		crsFile.read(crsLines);
		boolean found = false;
		for (String tmpStr: crsLines) {
			if (tmpStr.indexOf(crs.getCourseID()) >= 0) {
				crsLines.remove(tmpStr);
				found = true;
				break;
			}
		}
		if (found) {
			String data = lineList2String(crsLines);
			crsFile.write(data);
		}
	}

	@Override
	public void removeData(StudentInfo info) {
		//get all of student
		CSVFile file = new CSVFile(FN_STUDENT);
		List<String> lines = new ArrayList<String>();
		file.read(lines);
		boolean found = false;
		for (String tmpStr: lines) {
			if (tmpStr.indexOf(info.getStudentId()) >= 0) {
				lines.remove(tmpStr);
				found = true;
				break;
			}
		}
		if (found) {
			String data = lineList2String(lines);
			file.write(data);
		}
	}
	
	@Override
	public void removeData(List<CourseEnrollment> erlList) {
		//get all data from file
		CSVFile file = new CSVFile(FN_ENROLL);
		List<String> lines = new ArrayList<String>();
		file.read(lines);
		//convert to string from object
		List<String> erlStrList = new ArrayList<String>();
		for (CourseEnrollment erl: erlList) {
			String erlStr = CSVFile.format(erl);
			erlStrList.add(erlStr);
		}
		//search, if found, remove it
		boolean found = false;
		Iterator<String> iter = lines.iterator();
		while(iter.hasNext()) {
			String strFromFile = iter.next();
			for (String strTarget: erlStrList) {
				if (strFromFile.equalsIgnoreCase(strTarget)) {
					iter.remove();
					found = true;
				}				
			}			
		}

		if (found) {
			String data = lineList2String(lines);
			file.write(data);
		}
	}

	@Override
	public void removeData(ClassInfo currentCourse, Date arDate) {
		//directory
		String crsDirName = getDirName(currentCourse);
		File dir = new File(crsDirName);
		if (dir.exists() && dir.isDirectory()) {
			//file
			String arFileName = getArFileName(crsDirName, currentCourse, arDate);
			CSVFile file = new CSVFile(arFileName);
			file.delete();
		}
	}
	
	@Override
	public void modifyData(Course crs) {
		//get all of courses
		CSVFile crsFile = new CSVFile(FN_COURSE);
		List<String> crsLines = new ArrayList<String>();
		crsFile.read(crsLines);
		boolean found = false;
		int index = 0;
		for (String tmpStr: crsLines) {
			if (tmpStr.indexOf(crs.getCourseID()) >= 0) {
				String newValue = CSVFile.format(crs);
				crsLines.set(index, newValue);
				found = true;
				break;
			}
			index++;
		}
		if (found) {
			String data = lineList2String(crsLines);
			crsFile.write(data);
		}
	}

	@Override
	public void modifyData(SchoolClass cls) {
		//get all of classes
		CSVFile clsFile = new CSVFile(FN_CLASS);
		List<String> clsLines = new ArrayList<String>();
		clsFile.read(clsLines);
		boolean found = false;
		int index = 0;
		for (String tmpStr: clsLines) {
			if (tmpStr.indexOf(cls.getClassNumber()) >= 0) {
				String newValue = CSVFile.format(cls);
				clsLines.set(index, newValue);
				found = true;
				break;
			}
		}
		if (found) {
			String data = lineList2String(clsLines);
			clsFile.write(data);
		}
	}

	@Override
	public void modifyData(Student std) {
		//get all of student
		CSVFile file = new CSVFile(FN_STUDENT);
		List<String> lines = new ArrayList<String>();
		file.read(lines);
		boolean found = false;
		int index = 0;
		for (String tmpStr: lines) {
			if (tmpStr.indexOf(std.getStudentID()) >= 0) {
				String newValue = CSVFile.format(std);
				lines.set(index, newValue);
				found = true;
				break;
			}
		}
		if (found) {
			String data = lineList2String(lines);
			file.write(data);
		}
	}

	@Override
	public void modifyData(ClassInfo currentCourse, Date arDate, List<AttdRecordInfo> infoList) {
		addData(currentCourse, arDate, infoList);
	}





}