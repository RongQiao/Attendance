/**
 * 
 */
package Logical.DomainReport;

import Logical.DomainBase.Instructor;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class InstructorSummAttdReport extends GeneralReport {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Instructor instructor;

	/** 
	 * @return the instructor
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Instructor getInstructor() {
		// begin-user-code
		return instructor;
		// end-user-code
	}

	/** 
	 * @param instructor the instructor to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setInstructor(Instructor instructor) {
		// begin-user-code
		this.instructor = instructor;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private float totalAttendanceRate;

	/** 
	 * @return the totalAttendanceRate
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public float getTotalAttendanceRate() {
		// begin-user-code
		return totalAttendanceRate;
		// end-user-code
	}

	/** 
	 * @param totalAttendanceRate the totalAttendanceRate to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTotalAttendanceRate(float totalAttendanceRate) {
		// begin-user-code
		this.totalAttendanceRate = totalAttendanceRate;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ISAR_DetailLine iSAR_DetailLine;

	/** 
	 * @return the iSAR_DetailLine
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ISAR_DetailLine getiSAR_DetailLine() {
		// begin-user-code
		return iSAR_DetailLine;
		// end-user-code
	}

	/** 
	 * @param iSAR_DetailLine the iSAR_DetailLine to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setiSAR_DetailLine(ISAR_DetailLine iSAR_DetailLine) {
		// begin-user-code
		this.iSAR_DetailLine = iSAR_DetailLine;
		// end-user-code
	}
}