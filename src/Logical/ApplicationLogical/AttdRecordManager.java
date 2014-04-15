/**
 * 
 */
package Logical.ApplicationLogical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Logical.DomainBase.AttdClass;
import Logical.DomainBase.Student;
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
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param arDate 
	 * @param currentCourse 
	 * @return 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<AttdRecordInfo> getAttdRecord(ClassInfo currentCourse, Date arDate) {
		CourseManager crsManager = CourseManager.getInstance();
		currentCourse = crsManager.getCourse(currentCourse.getClassNumber());
		List<AttdRecordInfo> infoList = new ArrayList<AttdRecordInfo>();
		dataAgent.getDataAttdRecord(currentCourse, arDate, infoList);
		return infoList;
	}

}