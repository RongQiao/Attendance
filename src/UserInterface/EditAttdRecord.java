/**
 * 
 */
package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Logical.ApplicationLogical.AttdRecordInfo;
import Logical.ApplicationLogical.AttdRecordManager;
import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseEnrollManager;
import Logical.ApplicationLogical.CourseManager;
import Logical.ApplicationLogical.StudentInfo;
import Logical.ApplicationLogical.StudentManager;
import Logical.DomainBase.AttdClass;
import Logical.DomainBase.CourseEnrollment;
import Logical.DomainBase.Student;
import Logical.DomainBase.AttendenceRecord;
import UserInterface.EditCourseEnroll.courseSelectionHandler;
import UserInterface.EditCourseEnroll.studentSelectionHandler;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class EditAttdRecord extends CourseStudentFrame{
	private AttdRecordManager arManager;
	//swing objects
	private JButton newBtn;
	private JButton addBtn;
	private JButton modifyBtn;
	private JButton removeBtn;	
	private JButton queryBtn;
	private JLabel dateLbl;
	private JTextField dateTxt;
	private JLabel msgLbl;
	
	//constructor
	public EditAttdRecord() {
		super();
		arManager = AttdRecordManager.getInsance();
		initBtn();
		initDateField();
	}	

	@Override
	public void display() {
		setVisible(true);
		updateCourse();
		updateStudent();
	}
	
	@Override
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Edit Attendance Record");	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	}
	
	@Override
	protected void initTable() {
		super.initTable();
		ListSelectionModel selectionModel = courseTable.getSelectionModel();
		selectionModel.addListSelectionListener(new courseSelectionHandler());

		selectionModel = studentTable.getSelectionModel();
		selectionModel.addListSelectionListener(new studentSelectionHandler());
		studentTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("isAttendant");
	}
	
	private void initDateField() {
		JPanel pnl = new JPanel();
		dateLbl = new JLabel("Attendance Record Date:");
		dateTxt = new JTextField(10);
		pnl.add(dateLbl);
		pnl.add(dateTxt);
		queryBtn = new JButton("Query");
		pnl.add(queryBtn);
		queryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryCourseAttdRecord();
			}
		});
		tLeftPanel0.add(pnl);
		pnl = new JPanel();
		msgLbl = new JLabel();
		pnl.add(msgLbl);
		tLeftPanel0.add(pnl);
	}
	
	private void initBtn() {
		bottomPanel = new JPanel();
		mainPanel.add(bottomPanel);
		newBtn = new JButton("new Records");
		addBtn = new JButton("Save Records");
		modifyBtn = new JButton("Modify Records");
		removeBtn = new JButton("Remove Records");
		bottomPanel.add(newBtn);
		bottomPanel.add(addBtn);
		bottomPanel.add(modifyBtn);
		bottomPanel.add(removeBtn);
		//set action listener
		newBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newRecord();
			}
		});
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecord();
			}
		});
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyRecord();
			}
		});
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRecord();
			}
		});
	}
	
	private void markStudentAttendance(List<AttdRecordInfo> infoList) {
		if (currentCourse != null) {
			updateStudent(currentCourse);	
			for (int i = 0; i < studentTable.getRowCount(); i++) {
				for (AttdRecordInfo ar: infoList) {
					String idFromTable = (String) studentTable.getValueAt(i, 1);
					String idFromRecord = ar.getStdInfo().getStudentId();
					if (idFromTable.equalsIgnoreCase(idFromRecord)) {
						studentTable.setValueAt(ar.isAttendant(), i, 0);
						break;
					}
				}
			}
		}
	}
	
	private void updateStudent(ClassInfo currentCourse) {
		StudentTableModel tableModel = (StudentTableModel) studentTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		studentTable.updateUI();

		List<StudentInfo> listStd = enrollManager.getStudent(currentCourse);
		for (StudentInfo info: listStd) {
			Object[] row = new Object[]{new Boolean(false), info.getStudentId(), info.getFirstName(), info.getLastName()};
			tableModel.addRow(row);
		}
		studentTable.updateUI();
	}
	
	private void clearStudent() {
		StudentTableModel tableModel = (StudentTableModel) studentTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		studentTable.updateUI();
	}

	public void newRecord() {
		//show record date as today
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateTxt.setText(dateFormat.format(new Date()));
		//mark all students enrolled as attendant
		if (courseTable.getSelectedRow() != -1) {
			setWarningMsg("");
			updateStudent(currentCourse);
			for (int i = 0; i < studentTable.getRowCount(); i++) {
				studentTable.setValueAt(new Boolean(true), i, 0);
			}			
		}
		else {
			//show message: choose course first
			setWarningMsg("please select course first");
		}
	}
	
	private void setWarningMsg(String msg) {
		msgLbl.setText(msg);
		msgLbl.setForeground(Color.red);
	}
	
	private List<AttdRecordInfo> getAllRecord() {
		int cnt = studentTable.getRowCount();
		List<AttdRecordInfo> infoList = new ArrayList<AttdRecordInfo>();
		for (int i = 0; i < cnt; i++) {
			AttdRecordInfo arInfo = new AttdRecordInfo();
			StudentInfo stdInfo = new StudentInfo();
			stdInfo.setStudentId((String) studentTable.getValueAt(i, 1));
			arInfo.setStdInfo(stdInfo);
			arInfo.setAttendant((boolean) studentTable.getValueAt(i, 0));
			infoList.add(arInfo);		
		}	
		return infoList;
	}
	
	private Date getRecordDate() {
		Date arDate = null;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			arDate = dateFormat.parse(dateTxt.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arDate;
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addRecord() {
		if (currentCourse != null) {
			List<AttdRecordInfo> infoList = getAllRecord();	
			Date arDate = getRecordDate();					
			if ((infoList.size() > 0) 
					&& (arDate != null)) {
				arManager.addAttdRecord(currentCourse, arDate, infoList);
			}
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyRecord() {
		if (currentCourse != null) {
			List<AttdRecordInfo> infoList = getAllRecord();	
			Date arDate = getRecordDate();					
			if ((infoList.size() > 0) 
					&& (arDate != null)) {
				arManager.modifyAttdRecord(currentCourse, arDate, infoList);
			}
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeRecord() {
		if (currentCourse != null) {
			Date arDate = getRecordDate();	
			if (arDate != null) {
				arManager.removeAttdRecord(currentCourse, arDate);
				clearStudent();
			}
			else {
				setWarningMsg("please give available date.");
			}
		}
		else {
			setWarningMsg("please choose a course.");
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateAttdRecord() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void queryCourseAttdRecord() {
		if (currentCourse != null) {
			Date arDate = getRecordDate();	
			if (arDate != null) {
				List<AttdRecordInfo> infoList = arManager.getAttdRecord(currentCourse, arDate);
				markStudentAttendance(infoList);
			}
			else {
				setWarningMsg("please give available date.");
			}
		}
		else {
			setWarningMsg("please choose a course.");
		}
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
	
	class courseSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			selectCourse();
		}
	}
	
	class studentSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
		}
	}
}