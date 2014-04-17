/**
 * 
 */
package Logical.ApplicationLogical;

import java.util.ArrayList;
import java.util.List;

import Logical.DomainBase.CourseEnrollment;
import Logical.DomainBase.SchoolClass;
import Logical.DomainBase.Student;
import TechnicalService.PData_CSVFile;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CourseEnrollManager {
	private PDataAgent dataAgent;
	private static CourseEnrollManager instance = null;
	private CourseEnrollManager() {
		dataAgent = new PData_CSVFile();
	}

	public static CourseEnrollManager getInstance() {
		if (instance == null) {
			instance = new CourseEnrollManager();
		}
		return instance;
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param infoList 
	 * @param currentCourse 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addCourseEnrollment(ClassInfo currentCourse, List<StudentInfo> stdList) {
		List<CourseEnrollment> erlList = organizeEnrollList(currentCourse, stdList);
		dataAgent.addData(erlList);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeCourseEnrollment(ClassInfo currentCourse, List<StudentInfo> stdList) {
		AttdRecordManager attdManager = AttdRecordManager.getInsance();
		for (StudentInfo std: stdList) {
			attdManager.removeAttdRecord(currentCourse, std);			
		}
		List<CourseEnrollment> erlList = organizeEnrollList(currentCourse, stdList);
		dataAgent.removeData(erlList);
	}
	
	public void removeCourseEnrollment(StudentInfo std) {
		List<StudentInfo> stdList = new ArrayList<StudentInfo>();
		stdList.add(std);
		CourseManager crsManager = CourseManager.getInstance();
		List<ClassInfo> clsList = crsManager.getCourse();
		for (ClassInfo cls: clsList) {
			removeCourseEnrollment(cls, stdList);
		}
	}
	
	private List<CourseEnrollment> organizeEnrollList(ClassInfo currentCourse, List<StudentInfo> infoList) {
		List<CourseEnrollment> erlList = new ArrayList<CourseEnrollment>();
		for (StudentInfo std: infoList) {
			CourseEnrollment crsErl = new CourseEnrollment();
			crsErl.setErlClass(new SchoolClass(currentCourse.getClassNumber()));
			crsErl.setErlStudent(new Student(std.getStudentId()));
			erlList.add(crsErl);
		}
		return erlList;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void getCourseEnrollment(List<CourseEnrollment> erlList) {
		dataAgent.getDataEnroll(erlList);
	}

	private List<CourseEnrollment> getCourseEnrollment(ClassInfo cInfo) {
		List<CourseEnrollment> erlListAll = new ArrayList<CourseEnrollment>();
		List<CourseEnrollment> erlList = new ArrayList<CourseEnrollment>();
		getCourseEnrollment(erlListAll);
		for (CourseEnrollment erl: erlListAll) {
			String clsStr = erl.getErlClass().getClassNumber();
			if (clsStr.equalsIgnoreCase(cInfo.getClassNumber())) {
				erlList.add(erl);
			}
		}
		return erlList;
	}
	
	public int countCourseEnrollment(ClassInfo cInfo) {
		return getCourseEnrollment(cInfo).size();
	}

	public List<StudentInfo> getStudent(ClassInfo cls) {
		List<CourseEnrollment> erlList = getCourseEnrollment(cls);
		StudentManager studentManager = StudentManager.getInstance();
		List<StudentInfo> allStd = studentManager.getStudent();
		List<StudentInfo> clsStd = new ArrayList<StudentInfo>();
		if ((allStd != null) && (allStd.size()>0)) {
			for(CourseEnrollment erl: erlList) {
				String crtStd = erl.getErlStudent().getStudentID();
				for (StudentInfo std: allStd) {
					if (crtStd.equalsIgnoreCase(std.getStudentId())) {
						clsStd.add(std);
						break;
					}
				}
			}
		}
		return clsStd;
	}


}