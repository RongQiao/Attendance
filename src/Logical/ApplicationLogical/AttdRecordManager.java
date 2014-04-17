/**
 * 
 */
package Logical.ApplicationLogical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Logical.DomainReport.AttdCnt;
import TechnicalService.PData_CSVFile;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class AttdRecordManager {
	private PDataAgent dataAgent;
	private static AttdRecordManager instance = null;
	
	private AttdRecordManager() {
		dataAgent = new PData_CSVFile();
	}
	
	public static AttdRecordManager getInsance() {
		if (instance == null) {
			instance = new AttdRecordManager();
		}
		return instance;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param infoList 
	 * @param currentCourse 
	 * @param arDate 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addAttdRecord(ClassInfo currentCourse, Date arDate, List<AttdRecordInfo> infoList) {
		CourseManager crsManager = CourseManager.getInstance();
		currentCourse = crsManager.getCourse(currentCourse.getClassNumber());
		dataAgent.addData(currentCourse, arDate, infoList);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param infoList 
	 * @param arDate 
	 * @param currentCourse 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyAttdRecord(ClassInfo currentCourse, Date arDate, List<AttdRecordInfo> infoList) {
		CourseManager crsManager = CourseManager.getInstance();
		currentCourse = crsManager.getCourse(currentCourse.getClassNumber());
		dataAgent.modifyData(currentCourse, arDate, infoList);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param arDate 
	 * @param currentCourse 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeAttdRecord(ClassInfo currentCourse, Date arDate) {
		CourseManager crsManager = CourseManager.getInstance();
		currentCourse = crsManager.getCourse(currentCourse.getClassNumber());
		dataAgent.removeData(currentCourse, arDate);
	}
	
	public void removeAttdRecord(ClassInfo currentCourse, StudentInfo std) {
		dataAgent.removeData(currentCourse, std);
	}
	
	public void removeAttdRecord(StudentInfo std) {
		CourseManager crsManager = CourseManager.getInstance();
		List<ClassInfo> allCls = crsManager.getCourse();
		for (ClassInfo cls: allCls) {
			removeAttdRecord(cls, std);
		}
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param arDate 
	 * @param currentCourse 
	 * @return 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<AttdRecordInfo> getAttdRecord(ClassInfo currentCourse, Date arDate) {
		List<AttdRecordInfo> infoList = new ArrayList<AttdRecordInfo>();
		dataAgent.getDataAttdRecord(currentCourse, arDate, infoList);
		return infoList;
	}

	public List<AttdRecordMuldaysInfo> getAttdRecord(ClassInfo currentCourse) {
		//init students info
		List<AttdRecordMuldaysInfo> infoList = new ArrayList<AttdRecordMuldaysInfo>();
		CourseEnrollManager enrollManager = CourseEnrollManager.getInstance();
		List<StudentInfo> stdList = enrollManager.getStudent(currentCourse);
		for (StudentInfo std: stdList) {
			AttdRecordMuldaysInfo arm = new AttdRecordMuldaysInfo();
			arm.setStdInfo(std);
			arm.setAttdList(new ArrayList<DateAttd>());
			infoList.add(arm);
		}
		//add records
		dataAgent.getDataAttdRecord(currentCourse, infoList);
		return infoList;
	}

	public void getAttdRecord(ClassInfo currentCourse, List<AttdCnt> acList) {
		dataAgent.getDataAttdReport(currentCourse, acList);
	}

}