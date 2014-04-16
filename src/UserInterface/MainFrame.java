package UserInterface;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame {
	private EditCourse ECFrame;
	private EditStudent ESFrame;
	private EditCourseEnroll CEFrame;
	private EditAttdRecord ARFrame;
	private ShowClassAttdRecord ARDFrame;
	private ShowClassAttdReport ARRPFrame;
	
	private AttdFrame currentFrame;

	private static MainFrame instance = null;
	private MainFrame() {
		ECFrame = new EditCourse();
		ESFrame = new EditStudent();
		CEFrame = new EditCourseEnroll();
		ARFrame = new EditAttdRecord();
		ARDFrame = new ShowClassAttdRecord();
		ARRPFrame = new ShowClassAttdReport();				
	}
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();		
		}
		return instance;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame mFrame = MainFrame.getInstance();
				mFrame.setCurrentFrame(mFrame.getARDFrame());
				mFrame.getCurrentFrame().display();
			}
		});
	}

	public EditCourse getECFrame() {
		return ECFrame;
	}

	public EditStudent getESFrame() {
		return ESFrame;
	}

	public EditCourseEnroll getCEFrame() {
		return CEFrame;
	}
	
	public EditAttdRecord getARFrame() {
		return ARFrame;
	}
	
	public ShowClassAttdRecord getARDFrame() {
		return ARDFrame;
	}
	
	public ShowClassAttdReport getARRPFrame() {
		return ARRPFrame;
	}

	public AttdFrame getCurrentFrame() {
		return currentFrame;
	}
	
	public void setCurrentFrame(AttdFrame currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	public void setCurrentFrameEditCourse() {
		setCurrentFrame(ECFrame);
	}
	public void setCurrentFrameEditStudent() {
		setCurrentFrame(ESFrame);
	}
	public void setCurrentFrameCourseEnroll() {
		setCurrentFrame(CEFrame);
	}
	public void setCurrentFrameAttendance() {
		setCurrentFrame(ARFrame);
	}
	public void setCurrentFrameShowClassAR() {
		setCurrentFrame(ARDFrame);
	}
	public void setCurrentFrameShowClassRP() {
		setCurrentFrame(ARRPFrame);
	}

}
