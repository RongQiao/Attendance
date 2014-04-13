/**
 * 
 */
package Logical.DomainBase;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TeachAssistant extends Student {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SchoolClass taClass;

	/** 
	 * @return the taClass
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public SchoolClass getTaClass() {
		// begin-user-code
		return taClass;
		// end-user-code
	}

	/** 
	 * @param taClass the taClass to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTaClass(SchoolClass taClass) {
		// begin-user-code
		this.taClass = taClass;
		// end-user-code
	}
}