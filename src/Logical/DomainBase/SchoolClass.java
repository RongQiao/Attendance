/**
 * 
 */
package Logical.DomainBase;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SchoolClass {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String classNumber;

	/** 
	 * @return the classNumber
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getClassNumber() {
		// begin-user-code
		return classNumber;
		// end-user-code
	}

	/** 
	 * @param classNumber the classNumber to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassNumber(String classNumber) {
		// begin-user-code
		this.classNumber = classNumber;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String classSection;

	/** 
	 * @return the classSection
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getClassSection() {
		// begin-user-code
		return classSection;
		// end-user-code
	}

	/** 
	 * @param classSection the classSection to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassSection(String classSection) {
		// begin-user-code
		this.classSection = classSection;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ClassDays classDay;

	/** 
	 * @return the classDay
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ClassDays getClassDay() {
		// begin-user-code
		return classDay;
		// end-user-code
	}

	/** 
	 * @param classDay the classDay to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassDay(ClassDays classDay) {
		// begin-user-code
		this.classDay = classDay;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Time classBeginTime;

	/** 
	 * @return the classBeginTime
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Time getClassBeginTime() {
		// begin-user-code
		return classBeginTime;
		// end-user-code
	}

	/** 
	 * @param classBeginTime the classBeginTime to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassBeginTime(Time classBeginTime) {
		// begin-user-code
		this.classBeginTime = classBeginTime;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Time classEndTime;

	/** 
	 * @return the classEndTime
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Time getClassEndTime() {
		// begin-user-code
		return classEndTime;
		// end-user-code
	}

	/** 
	 * @param classEndTime the classEndTime to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassEndTime(Time classEndTime) {
		// begin-user-code
		this.classEndTime = classEndTime;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Course relatedCourse;

	/** 
	 * @return the relatedCourse
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Course getRelatedCourse() {
		// begin-user-code
		return relatedCourse;
		// end-user-code
	}

	/** 
	 * @param relatedCourse the relatedCourse to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setRelatedCourse(Course relatedCourse) {
		// begin-user-code
		this.relatedCourse = relatedCourse;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<Date> allClassDate;

	/** 
	 * @return the allClassDate
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<Date> getAllClassDate() {
		// begin-user-code
		return allClassDate;
		// end-user-code
	}

	/** 
	 * @param allClassDate the allClassDate to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAllClassDate(List<Date> allClassDate) {
		// begin-user-code
		this.allClassDate = allClassDate;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Course course;

	/** 
	 * @return the course
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Course getCourse() {
		// begin-user-code
		return course;
		// end-user-code
	}

	/** 
	 * @param course the course to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCourse(Course course) {
		// begin-user-code
		this.course = course;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ClassDays classDays;

	/** 
	 * @return the classDays
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ClassDays getClassDays() {
		// begin-user-code
		return classDays;
		// end-user-code
	}

	/** 
	 * @param classDays the classDays to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassDays(ClassDays classDays) {
		// begin-user-code
		this.classDays = classDays;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param dt
	 * @return
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean findSpecDate(Date dt) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Integer countEnrolledStudent() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void create() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}