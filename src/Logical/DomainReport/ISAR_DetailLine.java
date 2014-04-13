/**
 * 
 */
package Logical.DomainReport;

import java.util.Date;

import Logical.DomainBase.SchoolClass;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ISAR_DetailLine {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SchoolClass _class;

	/** 
	 * @return the _class
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public SchoolClass get_class() {
		// begin-user-code
		return _class;
		// end-user-code
	}

	/** 
	 * @param _class the _class to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void set_class(SchoolClass _class) {
		// begin-user-code
		this._class = _class;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date periodBeginDate;

	/** 
	 * @return the periodBeginDate
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Date getPeriodBeginDate() {
		// begin-user-code
		return periodBeginDate;
		// end-user-code
	}

	/** 
	 * @param periodBeginDate the periodBeginDate to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPeriodBeginDate(Date periodBeginDate) {
		// begin-user-code
		this.periodBeginDate = periodBeginDate;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date periodEndDate;

	/** 
	 * @return the periodEndDate
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Date getPeriodEndDate() {
		// begin-user-code
		return periodEndDate;
		// end-user-code
	}

	/** 
	 * @param periodEndDate the periodEndDate to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPeriodEndDate(Date periodEndDate) {
		// begin-user-code
		this.periodEndDate = periodEndDate;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private float attendanceRate;

	/** 
	 * @return the attendanceRate
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public float getAttendanceRate() {
		// begin-user-code
		return attendanceRate;
		// end-user-code
	}

	/** 
	 * @param attendanceRate the attendanceRate to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAttendanceRate(float attendanceRate) {
		// begin-user-code
		this.attendanceRate = attendanceRate;
		// end-user-code
	}
}