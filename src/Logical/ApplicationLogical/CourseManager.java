/**
 * 
 */
package Logical.ApplicationLogical;

import java.util.ArrayList;
import java.util.List;

import Logical.DomainBase.SchoolClass;
import Logical.DomainBase.Course;
import TechnicalService.PData_CSVFile;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CourseManager {
	private static CourseManager instance = null;
	private CourseManager() {
		dataAgent = new PData_CSVFile();
	}
	public static CourseManager getInstance() {
		if (instance == null) {
			instance = new CourseManager();
		}
		return instance;
	}
	
	private PDataAgent dataAgent;

	/** 
	 * @return the course
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<ClassInfo> getCourse() {
		List<ClassInfo> listCrs = new ArrayList<ClassInfo> ();
		dataAgent.getData(listCrs);
		return listCrs;
	}
	
	public ClassInfo getCourse(String clsNumber) {
		ClassInfo ret = null;
		List<ClassInfo> cInfo = getCourse();
		for (ClassInfo item: cInfo) {
			if (clsNumber.equalsIgnoreCase(item.getClassNumber())) {
				ret = item;
			}
		}
		return ret;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param classInfo 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addCourse(ClassInfo classInfo) {
		Course crs = new Course();
		crs.setCourseID(classInfo.getCourseNumber());
		crs.setCourseName(classInfo.getCourseName());
		
		SchoolClass cls = new SchoolClass();
		cls.setClassNumber(classInfo.getClassNumber());
		cls.setClassSection(classInfo.getCourseSection());
		cls.setCourse(crs);
		
		saveDataAdd(crs);
		saveDataAdd(cls);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param classInfo 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyCourse(ClassInfo classInfo) {
		ClassInfo cInfoData = getCourse(classInfo.getClassNumber());
		if (cInfoData != null) {
			if (!classInfo.getCourseName().equalsIgnoreCase(cInfoData.getCourseName())) {
				//course name is different, modify course
				Course crs = new Course();
				crs.setCourseID(classInfo.getCourseNumber());
				crs.setCourseName(classInfo.getCourseName());
				saveDataModify(crs);
			}
			if (!classInfo.getCourseSection().equalsIgnoreCase(cInfoData.getCourseSection())) {
				//course section is different, modify class
				SchoolClass cls = new SchoolClass();
				cls.setClassNumber(classInfo.getClassNumber());
				cls.setClassSection(classInfo.getCourseSection());
				saveDataModify(cls);
			}
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cInfo 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeCourse(ClassInfo cInfo) {
		SchoolClass cls = new SchoolClass();
		cls.setClassNumber(cInfo.getClassNumber());
		Course crs = new Course();
		crs.setCourseID(cInfo.getCourseNumber());
		cls.setCourse(crs);
		saveDataRemove(cls);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param crs 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private void saveDataAdd(Course crs) {
		dataAgent.addData(crs);
	}
	
	private void saveDataAdd(SchoolClass cls) {
		dataAgent.addData(cls);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cls 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private void saveDataModify(SchoolClass cls) {
		dataAgent.modifyData(cls);
	}

	private void saveDataModify(Course crs) {
		dataAgent.modifyData(crs);
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param cls 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private void saveDataRemove(SchoolClass cls) {
		dataAgent.removeData(cls);
	}

}