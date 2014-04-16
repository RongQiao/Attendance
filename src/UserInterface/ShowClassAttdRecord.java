/**
 * 
 */
package UserInterface;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Logical.ApplicationLogical.AttdRecordManager;
import Logical.ApplicationLogical.AttdRecordMuldaysInfo;
import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseEnrollManager;
import Logical.ApplicationLogical.CourseManager;
import Logical.ApplicationLogical.DateAttd;
import Logical.ApplicationLogical.StudentInfo;
import Logical.ApplicationLogical.StudentManager;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ShowClassAttdRecord extends AttdFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CourseManager crsManager;
	protected StudentManager studentManager;
	protected CourseEnrollManager enrollManager;
	protected AttdRecordManager attdManager;
	protected ClassInfo currentCourse;
	
	private JScrollPane topPanel;
	private JScrollPane botPanel;
	private JTable courseTable;
	private JTable studentTable;
	
	ShowClassAttdRecord() {
		crsManager = CourseManager.getInstance();
		studentManager = StudentManager.getInstance();
		enrollManager = CourseEnrollManager.getInstance();		
		attdManager = AttdRecordManager.getInsance();
		
		initFrame();
		initTable();
	}
	
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Show Class Attendance Records");	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		//mainPanel.setLayout(new BorderLayout());
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
		topPanel.setMaximumSize(new Dimension((int) this.getMaximumSize().getWidth(), 20));
		mainPanel.add(topPanel);
		ListSelectionModel selectionModel = courseTable.getSelectionModel();
		selectionModel.addListSelectionListener(new courseSelectionHandler());
		//show all day's attendance as a cell table
		List<DateAttd> days = new ArrayList<DateAttd>();
		days.add(new DateAttd(new Date(), false));
		JTable cellTable = new JTable(new AttdTableModel(days));
		String[] stdColumnNames = {"StudentID", "LastName", "FirstName", "AttendanceRecord"};
		Object[][] tableStd = {
				{"","", "", cellTable}
		};
		StudentTableModel stdTableModel = new StudentTableModel(tableStd, stdColumnNames);
		studentTable = new JTable(stdTableModel);		
		botPanel = new JScrollPane(studentTable);		
		mainPanel.add(botPanel);
		//this is to show the cell table
		TableColumn tc = studentTable.getColumnModel().getColumn(3);  
        tc.setCellRenderer(new CustomTableCellRenderer(cellTable));  
        tc.setCellEditor(new CustomTableCellEditor(cellTable));   
        studentTable.setRowHeight(cellTable.getPreferredSize().height+cellTable.getTableHeader().getPreferredSize().height+4); 
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

		List<AttdRecordMuldaysInfo> arList = attdManager.getAttdRecord(currentCourse);
		for (AttdRecordMuldaysInfo info: arList) {
			StudentInfo std = info.getStdInfo();
			JTable cellTable = new JTable(new AttdTableModel(info.getAttdList()));
			Object[] row = new Object[]{std.getStudentId(), std.getFirstName(), std.getLastName(), cellTable};
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