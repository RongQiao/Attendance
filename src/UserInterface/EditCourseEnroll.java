package UserInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseEnrollManager;
import Logical.ApplicationLogical.CourseManager;
import Logical.ApplicationLogical.StudentInfo;
import Logical.ApplicationLogical.StudentManager;
import Logical.DomainBase.CourseEnrollment;
import UserInterface.EditCourse.courseSelectionHandler;
import UserInterface.EditStudent.studentSelectionHandler;

public class EditCourseEnroll extends AttdFrame{

	private CourseManager crsManager;
	private StudentManager studentManager;
	private CourseEnrollManager enrollManager;
	private ClassInfo currentCourse;
	private StudentInfo currentStudent;
	
	//swing objects
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JScrollPane tLeftPanel;
	private JScrollPane tRightPanel;
	private JTable courseTable;
	private JTable studentTable;
	private JButton addBtn;
	private JButton removeBtn;
	
	//constructor
	public EditCourseEnroll() {
		crsManager = CourseManager.getInstance();			
		studentManager = StudentManager.getInstance();
		enrollManager = CourseEnrollManager.getInstance();
		
		initFrame();
		initTable();
		initBtn();
	}	
	
	public void display() {
		setVisible(true);
		updateCourse();
		updateStudent();
	}
	
	@Override
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Enroll Course");	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	}
	
	private void initTable() {
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
		ListSelectionModel selectionModel = courseTable.getSelectionModel();
		selectionModel.addListSelectionListener(new courseSelectionHandler());
		tLeftPanel = new JScrollPane(courseTable);		
		topPanel.add(tLeftPanel);
		
		//right panel for student table
		String[] StdColumnNames = {"isEnrolled", "studentId", "First Name", "Last Name"};
		Object[][] tableStudent = {
				{new Boolean(false), "1", "AF", "AL"}
		};
		StudentTableModel stdTableModel = new StudentTableModel(tableStudent, StdColumnNames);
		studentTable = new JTable(stdTableModel);
		//studentTable.setPreferredScrollableViewportSize(new Dimension(300,70));
		studentTable.setFillsViewportHeight(true);
		selectionModel = studentTable.getSelectionModel();
		selectionModel.addListSelectionListener(new studentSelectionHandler());
		tRightPanel = new JScrollPane(studentTable);
		topPanel.add(tRightPanel);
	}
	
	private void initBtn() {
		bottomPanel = new JPanel();
		mainPanel.add(bottomPanel);
		addBtn = new JButton("Update Enrollment");
		removeBtn = new JButton("Remove Enrollment");
		bottomPanel.add(addBtn);
		bottomPanel.add(removeBtn);
		//set action listener
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEnroll();
			}
		});
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEnroll();
			}
		});
	}
	
	protected void removeEnroll() {
		if ((currentCourse != null) && (currentStudent != null))  {
			int cnt = studentTable.getRowCount();
			List<StudentInfo> infoList = new ArrayList<StudentInfo>();
			infoList.add(currentStudent);
			if (infoList.size() > 0) {
				enrollManager.removeCourseEnrollment(currentCourse, infoList);
				countCourseEnroll();
				markStudentEnroll();
			}
		}
	}

	protected void addEnroll() {
		if (currentCourse != null) {
			int cnt = studentTable.getRowCount();
			List<StudentInfo> infoList = new ArrayList<StudentInfo>();
			for (int i = 0; i < cnt; i++) {
				boolean isEnroll = (boolean) studentTable.getValueAt(i, 0);
				if (isEnroll) {
					StudentInfo info = new StudentInfo();
					info.setStudentId((String) studentTable.getValueAt(i, 1));
					infoList.add(info);
				}				
			}
			if (infoList.size() > 0) {
				enrollManager.addCourseEnrollment(currentCourse, infoList);
				countCourseEnroll();
				markStudentEnroll();
			}
		}
	}
	
	private void countCourseEnroll() {
		if (currentCourse != null) {
			int rowIdx = courseTable.getSelectedRow();
			if (rowIdx < courseTable.getRowCount()) {
				int cnt = enrollManager.countCourseEnrollment(currentCourse);
				courseTable.setValueAt(new Integer(cnt), rowIdx, 2);
			}
		}
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
	
	private void markStudentEnroll() {
		if (currentCourse != null) {
			List<CourseEnrollment> erlList = new ArrayList<CourseEnrollment>();
			enrollManager.getCourseEnrollment(erlList);
			for (int i = 0; i < studentTable.getRowCount(); i++) {
				String stdID = (String) studentTable.getValueAt(i, 1);
				String clsNum = currentCourse.getClassNumber();
				boolean stdEnrolled = false;
				for (CourseEnrollment erl: erlList) {					
					if (erl.getErlStudent().getStudentID().equalsIgnoreCase(stdID)
							&& erl.getErlClass().getClassNumber().equalsIgnoreCase(clsNum)) {
						stdEnrolled = true;
						break;
					}
				}
				if (stdEnrolled) {
					studentTable.setValueAt(new Boolean(true), i, 0);
				}
				else {
					studentTable.setValueAt(new Boolean(false), i, 0);
				}
			}			
		}
	}

	public void selectCourse() {
		int rowIdx = courseTable.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) courseTable.getModel();
		if (rowIdx < tableModel.getRowCount()) {
			String value = (String) tableModel.getValueAt(rowIdx, 0);
			ClassInfo classInfo = crsManager.getCourse(value);
			currentCourse = classInfo;			
			//mark if student enrolled the course
			markStudentEnroll();
		}
	}

	public void selectStudent() {
		int rowIdx = studentTable.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
		if (rowIdx < tableModel.getRowCount()) {
			String value = (String) tableModel.getValueAt(rowIdx, 1);
			currentStudent = studentManager.getStudent(value);
		}
	}	
	
	class courseSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			selectCourse();
		}
	}
	
	class studentSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			selectStudent();
		}
	}

}
