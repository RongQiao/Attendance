package UserInterface;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseEnrollManager;
import Logical.ApplicationLogical.CourseManager;
import Logical.ApplicationLogical.StudentInfo;
import Logical.ApplicationLogical.StudentManager;

public abstract class CourseStudentFrame extends AttdFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CourseManager crsManager;
	protected StudentManager studentManager;
	protected CourseEnrollManager enrollManager;
	protected ClassInfo currentCourse;
	
	//swing objects
	protected JPanel topPanel;
	protected JPanel bottomPanel;
	protected JPanel tLeftPanel0;
	protected JScrollPane tLeftPanel;
	protected JScrollPane tRightPanel;
	protected JTable courseTable;
	protected JTable studentTable;
	
	//constructor
	public CourseStudentFrame() {
		crsManager = CourseManager.getInstance();			
		studentManager = StudentManager.getInstance();
		enrollManager = CourseEnrollManager.getInstance();
		
		initFrame();
		initTable();
	}	
	
	protected void initTable() {
		topPanel = new JPanel();
		mainPanel.add(topPanel);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		//left panel for course table
		String[] crsColumnNames = {"classNumber", "courseName", "enrolledNumber"};
		Object[][] tableCourse = {
				{"1","Course1", new Integer(0)}
		};
		DefaultTableModel tableModel = new DefaultTableModel(tableCourse, crsColumnNames);
		courseTable = new JTable(tableModel);		
		tLeftPanel = new JScrollPane(courseTable);		
		tLeftPanel0 = new JPanel();
		tLeftPanel0.setLayout(new BoxLayout(tLeftPanel0, BoxLayout.PAGE_AXIS));
		tLeftPanel0.add(tLeftPanel);
		topPanel.add(tLeftPanel0);
		
		//right panel for student table
		String[] StdColumnNames = {"isChecked", "studentId", "First Name", "Last Name"};
		Object[][] tableStudent = {
				{new Boolean(false), "1", "AF", "AL"}
		};
		StudentTableModel stdTableModel = new StudentTableModel(tableStudent, StdColumnNames);
		studentTable = new JTable(stdTableModel);
		//studentTable.setPreferredScrollableViewportSize(new Dimension(300,70));
		studentTable.setFillsViewportHeight(true);
		tRightPanel = new JScrollPane(studentTable);
		topPanel.add(tRightPanel);
	}

	public void updateCourse() {
		List<ClassInfo> listCrs = crsManager.getCourse();
		DefaultTableModel tableModel = (DefaultTableModel) courseTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		courseTable.updateUI();

		for (ClassInfo cInfo: listCrs) {
			int cnt = enrollManager.countCourseEnrollment(cInfo);
			Object[] row = new Object[]{cInfo.getClassNumber(), cInfo.getCourseName(), new Integer(cnt)};
			tableModel.addRow(row);
		}
		courseTable.updateUI();
	}
	
	public void updateStudent() {
		List<StudentInfo> listStd = studentManager.getStudent();
		StudentTableModel tableModel = (StudentTableModel) studentTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		studentTable.updateUI();

		for (StudentInfo info: listStd) {
			Object[] row = new Object[]{new Boolean(false), info.getStudentId(), info.getFirstName(), info.getLastName()};
			tableModel.addRow(row);
		}
		studentTable.updateUI();
	}
}
