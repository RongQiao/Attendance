package Logical.DomainReport;

import java.util.Date;

public class AttdCnt {
	private Date date;
	private int expectedNumbers;
	private int attdNumbers;
	
	public AttdCnt(Date dt, int numShouldBe, int numActualBe) {
		setDate(dt);
		setExpectedNumbers(numShouldBe);
		setAttdNumbers(numActualBe);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getExpectedNumbers() {
		return expectedNumbers;
	}
	public void setExpectedNumbers(int expectedNumbers) {
		this.expectedNumbers = expectedNumbers;
	}
	public int getAttdNumbers() {
		return attdNumbers;
	}
	public void setAttdNumbers(int attdNumbers) {
		this.attdNumbers = attdNumbers;
	}
}
