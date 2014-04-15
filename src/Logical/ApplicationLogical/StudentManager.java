/**
 * 
 */
package Logical.ApplicationLogical;

import java.util.ArrayList;
import java.util.List;

import Logical.DomainBase.Student;
import TechnicalService.PData_CSVFile;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class StudentManager {
	private PDataAgent dataAgent;
	
	private static StudentManager instance = null;
	
	private StudentManager() {
		dataAgent = new PData_CSVFile();
	}

	public static StudentManager getInstance() {
		if (instance == null) {
			instance = new StudentManager();
		}
		return instance;
	}	

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param info 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addStudent(StudentInfo info) {
		Student std = new Student();
		std.setStudentID(info.getStudentId());
		std.setFirstName(info.getFirstName());
		std.setLastName(info.getLastName());
		std.setMiddleName(info.getMiddleName());
		
		dataAgent.addData(std);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param info 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyStudent(StudentInfo info) {
		StudentInfo infoData = getStudent(info.getStudentId());
		if (infoData != null) {
			Student std = new Student();
			std.setStudentID(info.getStudentId());
			std.setFirstName(info.getFirstName());
			std.setLastName(info.getLastName());
			std.setMiddleName(info.getMiddleName());
			dataAgent.modifyData(std);
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */

	public void removeStudent(StudentInfo info) {
		dataAgent.removeData(info);
	}

	public List<StudentInfo> getStudent() {
		List<StudentInfo> listStd = new ArrayList<StudentInfo> ();
		dataAgent.getDataStudent(listStd);
		return listStd;
	}

	public StudentInfo getStudent(String studentId) {
		StudentInfo ret = null;
		List<StudentInfo> info = getStudent();
		for (StudentInfo item: info) {
			if (studentId.equalsIgnoreCase(item.getStudentId())) {
				ret = item;
			}
		}
		return ret;
	}

}