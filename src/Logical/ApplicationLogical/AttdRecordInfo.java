package Logical.ApplicationLogical;

public class AttdRecordInfo {
	private StudentInfo stdInfo;
	private boolean isAttendant;
	public StudentInfo getStdInfo() {
		return stdInfo;
	}
	public void setStdInfo(StudentInfo stdInfo) {
		this.stdInfo = stdInfo;
	}
	public boolean isAttendant() {
		return isAttendant;
	}
	public void setAttendant(boolean isAttendant) {
		this.isAttendant = isAttendant;
	}
}
