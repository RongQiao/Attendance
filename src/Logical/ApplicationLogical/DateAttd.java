package Logical.ApplicationLogical;

import java.util.Date;

public class DateAttd {
	private Date dt;
	private boolean isAttendant;
	
	public DateAttd() {
		
	}
	
	public DateAttd(Date dt, boolean at) {
		setDt(dt);
		setAttendant(at);
	}
	
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public boolean isAttendant() {
		return isAttendant;
	}
	public void setAttendant(boolean isAttendant) {
		this.isAttendant = isAttendant;
	}
}
