/**
 * 
 */
package Logical.DomainReport;

import java.util.Date;
import java.util.List;
import java.util.Set;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SelectedClassAttdReport extends ClassAttendanceReport {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date classDate;

	/** 
	 * @return the classDate
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Date getClassDate() {
		// begin-user-code
		return classDate;
		// end-user-code
	}

	/** 
	 * @param classDate the classDate to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClassDate(Date classDate) {
		// begin-user-code
		this.classDate = classDate;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer expectedAttdAmount;

	/** 
	 * @return the expectedAttdAmount
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Integer getExpectedAttdAmount() {
		// begin-user-code
		return expectedAttdAmount;
		// end-user-code
	}

	/** 
	 * @param expectedAttdAmount the expectedAttdAmount to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setExpectedAttdAmount(Integer expectedAttdAmount) {
		// begin-user-code
		this.expectedAttdAmount = expectedAttdAmount;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer attdAmount;

	/** 
	 * @return the attdAmount
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Integer getAttdAmount() {
		// begin-user-code
		return attdAmount;
		// end-user-code
	}

	/** 
	 * @param attdAmount the attdAmount to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAttdAmount(Integer attdAmount) {
		// begin-user-code
		this.attdAmount = attdAmount;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<SCAR_DetailLine> detailLines;

	/** 
	 * @return the detailLines
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<SCAR_DetailLine> getDetailLines() {
		// begin-user-code
		return detailLines;
		// end-user-code
	}

	/** 
	 * @param detailLines the detailLines to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDetailLines(List<SCAR_DetailLine> detailLines) {
		// begin-user-code
		this.detailLines = detailLines;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<SCAR_DetailLine> sCAR_DetailLine;

	/** 
	 * @return the sCAR_DetailLine
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<SCAR_DetailLine> getsCAR_DetailLine() {
		// begin-user-code
		return sCAR_DetailLine;
		// end-user-code
	}

	/** 
	 * @param sCAR_DetailLine the sCAR_DetailLine to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setsCAR_DetailLine(Set<SCAR_DetailLine> sCAR_DetailLine) {
		// begin-user-code
		this.sCAR_DetailLine = sCAR_DetailLine;
		// end-user-code
	}
}