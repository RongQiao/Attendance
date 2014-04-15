/**
 * 
 */
package UserInterface;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseEnrollManager;
import Logical.ApplicationLogical.CourseManager;
import Logical.ApplicationLogical.StudentInfo;
import Logical.ApplicationLogical.StudentManager;
import Logical.DomainBase.SchoolClass;
import Logical.DomainBase.Student;
import Logical.DomainBase.AttendenceRecord;
import UserInterface.EditAttdRecord.courseSelectionHandler;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ShowClassAttdRecord extends AttdFrame{
	protected CourseManager crsManager;
	protected StudentManager studentManager;
	protected CourseEnrollManager enrollManager;
	protected ClassInfo currentCourse;
	
	private JScrollPane topPanel;
	private JScrollPane botPanel;
	private JTable courseTable;
	private JTable studentTable;
	
	ShowClassAttdRecord() {
		crsManager = CourseManager.getInstance();
		studentManager = StudentManager.getInstance();
		enrollManager = CourseEnrollManager.getInstance();		
		
		initFrame();
		initTable();
	}
	
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Show Class Attendance Records");	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	}
	
	protected void initTable() {
		//top panel for course table
		String[] crsColumnNames = {"classNumber", "courseName", "enrolledNumber"};
		Object[][] tableCourse = {
				{"1","Course1", new Integer(0)}
		};
		DefaultTableModel tableModel = new DefaultTableModel(tableCourse, crsColumnNames);
		courseTable = new JTable(tableModel);		
		topPanel = new JScrollPane(courseTable);		
		mainPanel.add(topPanel);
		ListSelectionModel selectionModel = courseTable.getSelectionModel();
		selectionModel.addListSelectionListener(new courseSelectionHandler());
		//bottom panel for student attendance record table
		String[] stdColumnNames = {"StudentID", "LastName", "FirstName"};
		Object[][] tableStd = {
				{"1","aa", "aa"}
		};
		StudentTableModel stdTableModel = new StudentTableModel(tableStd, stdColumnNames);
		studentTable = new JTable(stdTableModel);		
		botPanel = new JScrollPane(studentTable);		
		mainPanel.add(botPanel);
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
	
	private void updateStudent(ClassInfo currentCourse) {
		StudentTableModel tableModel = (StudentTableModel) studentTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		studentTable.updateUI();

		List<StudentInfo> listStd = enrollManager.getStudent(currentCourse);
		for (StudentInfo info: listStd) {
			Object[] row = new Object[]{info.getStudentId(), info.getFirstName(), info.getLastName()};
			tableModel.addRow(row);
		}
		studentTable.updateUI();
	}
	
	public void selectCourse() {
		int rowIdx = courseTable.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) courseTable.getModel();
		if (rowIdx < tableModel.getRowCount()) {
			String value = (String) tableModel.getValueAt(rowIdx, 0);
			ClassInfo classInfo = crsManager.getCourse(value);
			currentCourse = classInfo;	
			updateStudent(currentCourse);	
		}
	}

	@Override
	public void display() {
		setVisible(true);
		updateCourse();
	}
	
	class courseSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			selectCourse();
		}
	}
}