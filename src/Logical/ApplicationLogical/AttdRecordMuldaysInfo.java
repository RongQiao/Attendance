package Logical.ApplicationLogical;

import java.util.List;

public class AttdRecordMuldaysInfo {
	private StudentInfo stdInfo;
	private List<DateAttd> attdList;
	
	public StudentInfo getStdInfo() {
		return stdInfo;
	}
	public void setStdInfo(StudentInfo stdInfo) {
		this.stdInfo = stdInfo;
	}
	public List<DateAttd> getAttdList() {
		return attdList;
	}
	public void setAttdList(List<DateAttd> attdList) {
		this.attdList = attdList;
	}
}
