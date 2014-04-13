/**
 * 
 */
package Logical.ApplicationLogical;

import java.util.List;

import Logical.DomainBase.Course;
import Logical.DomainBase.SchoolClass;
import Logical.DomainBase.Student;
import Logical.ApplicationLogical.ClassInfo;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class PDataAgent {
	public abstract void addData(Course crs);
	
	public abstract void addData(SchoolClass cls);

	public abstract void addData(Student std);
	
	public abstract void modifyData(Course crs);

	public abstract void modifyData(SchoolClass cls);

	public abstract void removeData(SchoolClass cls);

	public abstract void getData(List<ClassInfo> listCrs);

	public abstract void getDataStudent(List<StudentInfo> listStd);

	public abstract void removeData(StudentInfo info);

	public abstract void modifyData(Student std);
}