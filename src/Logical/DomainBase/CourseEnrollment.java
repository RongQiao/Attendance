package Logical.DomainBase;

public class CourseEnrollment {
	private SchoolClass erlClass;
	private Student erlStudent;
	public SchoolClass getErlClass() {
		return erlClass;
	}
	public void setErlClass(SchoolClass erlClass) {
		this.erlClass = erlClass;
	}
	public Student getErlStudent() {
		return erlStudent;
	}
	public void setErlStudent(Student erlStudent) {
		this.erlStudent = erlStudent;
	}
}
