/**
 * 
 */
package UserInterface;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import Logical.ApplicationLogical.AttdRecordManager;
import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseEnrollManager;
import Logical.ApplicationLogical.CourseManager;
import Logical.ApplicationLogical.StudentManager;
import Logical.DomainReport.AttdCnt;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ShowClassAttdReport extends AttdFrame{
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
	private HistogramPanel higsogram;
	private JTable courseTable;
	
	ShowClassAttdReport() {
		crsManager = CourseManager.getInstance();
		studentManager = StudentManager.getInstance();
		enrollManager = CourseEnrollManager.getInstance();		
		attdManager = AttdRecordManager.getInsance();
		
		initFrame();
		initTable();
		initHistogram();
	}
	
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Show Class Attendance Report");	
		mainPanel.setLayout((LayoutManager) new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
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
	}
	
	private void initHistogram() {
		higsogram = new HistogramPanel();
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
	
	public void selectCourse() {
		int rowIdx = courseTable.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) courseTable.getModel();
		if (rowIdx < tableModel.getRowCount()) {
			String value = (String) tableModel.getValueAt(rowIdx, 0);
			ClassInfo classInfo = crsManager.getCourse(value);
			currentCourse = classInfo;		
			
			List<AttdCnt> acList = new ArrayList<AttdCnt>();
			attdManager.getAttdRecord(currentCourse, acList);
			higsogram.setData(acList);	
			higsogram.display(currentCourse.getCourseName());
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