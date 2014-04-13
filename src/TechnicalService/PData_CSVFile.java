/**
 * 
 */
package TechnicalService;

import java.util.ArrayList;
import java.util.List;

import Logical.ApplicationLogical.PDataAgent;
import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.StudentInfo;
import Logical.DomainBase.Course;
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
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void replaceData() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

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
	
	private void addData(String fileName, String data) {
		CSVFile crsFile = new CSVFile(fileName);
		if (!crsFile.exists()) {
			crsFile.write(data);
		}
		else {
			List<String> buf = new ArrayList<String>();
			crsFile.read(buf);
			boolean dataExist = false;
			for (String tmpStr: buf) {
				if (tmpStr.equalsIgnoreCase(data)) {
					dataExist = true;
					break;
				}
			}
			if (!dataExist) {
				crsFile.append(data);
			}			
		}

	}

	@Override
	public void getData(List<ClassInfo> listCrs) {
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

	@Override
	public void getDataStudent(List<StudentInfo> listStd) {
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



}