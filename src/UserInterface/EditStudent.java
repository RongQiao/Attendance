/**
 * 
 */
package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Logical.ApplicationLogical.StudentInfo;
import Logical.ApplicationLogical.StudentManager;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class EditStudent extends AttdFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StudentManager studentManager;
	
	//swing objects
	private JScrollPane leftPanel;
	private JPanel rightPanel;
	private JPanel rTopPanel;
	private JPanel rBotPanel;
	private JTable studentTable;
	private JLabel studentIdLbl;
	private JTextField studentIdText;
	private JLabel studentFNamLbl;
	private JTextField studentFNamText;
	private JLabel studentLNamLbl;
	private JTextField studentLNamText;
	private JLabel studentMNamLbl;
	private JTextField studentMNamText;
	private JButton addBtn;
	private JButton modifyBtn;
	private JButton removeBtn;
		
	//constructor
	public EditStudent() {
		studentManager = StudentManager.getInstance();			
		initFrame();
		initTable();
		initDetailField();
		initBtn();
	}	
	
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Edit Student");	
	}
	
	private void initTable() {
		//left panel for student table
		String[] columnNames = {"studentId", "First Name", "Last Name"};
		String[][] tableStudent = {
				{"1", "AF", "AL"}
		};
		DefaultTableModel tableModel = new DefaultTableModel(tableStudent, columnNames);
		studentTable = new JTable(tableModel);
		ListSelectionModel selectionModel = studentTable.getSelectionModel();
		selectionModel.addListSelectionListener(new studentSelectionHandler());
		leftPanel = new JScrollPane(studentTable);
		mainPanel.add(leftPanel);
	}
	
	private void initDetailField() {
		//right panel for details and button
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(rightPanel);
		rTopPanel = new JPanel();	
		rBotPanel = new JPanel();
		rightPanel.add(rTopPanel);
		rightPanel.add(rBotPanel);

		JPanel rtPanel1 = new JPanel();
		JPanel rtPanel2 = new JPanel();
		JPanel rtPanel3 = new JPanel();
		JPanel rtPanel4 = new JPanel();
		rTopPanel.setLayout(new BoxLayout(rTopPanel, BoxLayout.PAGE_AXIS));
		rTopPanel.add(rtPanel1);
		rTopPanel.add(rtPanel2);
		rTopPanel.add(rtPanel3);
		rTopPanel.add(rtPanel4);
		studentIdLbl = new JLabel("Student ID");
		studentFNamLbl = new JLabel("First Name");
		studentLNamLbl = new JLabel("Last Name");
		studentMNamLbl = new JLabel("Middle Name");
		int txtFldLen = 10;
		studentIdText = new JTextField(txtFldLen);
		studentFNamText = new JTextField(txtFldLen);
		studentLNamText = new JTextField(txtFldLen);
		studentMNamText = new JTextField(txtFldLen);
		rtPanel1.add(studentIdLbl);
		rtPanel1.add(studentIdText);
		rtPanel2.add(studentFNamLbl);
		rtPanel2.add(studentFNamText);
		rtPanel3.add(studentLNamLbl);
		rtPanel3.add(studentLNamText);
		rtPanel4.add(studentMNamLbl);
		rtPanel4.add(studentMNamText);
	}
	
	private void initBtn() {
		addBtn = new JButton("Add Student");
		modifyBtn = new JButton("Modify Student");
		removeBtn = new JButton("Remove Student");
		rBotPanel.add(addBtn);
		rBotPanel.add(modifyBtn);
		rBotPanel.add(removeBtn);
		//set action listener
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent();
			}
		});
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyStudent();
			}
		});
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeStudent();
			}
		});
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addStudent() {
		StudentInfo info = new StudentInfo();
		getStudentDetail(info);		
		studentManager.addStudent(info);
		updateStudent();
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyStudent() {
		StudentInfo info = new StudentInfo();
		getStudentDetail(info);		
		studentManager.modifyStudent(info);
		updateStudent();
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeStudent() {
		StudentInfo info = new StudentInfo();
		getStudentDetail(info);
		if (info.getStudentId().length()>0) {
			studentManager.removeStudent(info);			
		}
		updateStudent();
		clearStudentDetail();
	}

	public void showStudentDetail() {
		int rowIdx = studentTable.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
		if (rowIdx < tableModel.getRowCount()) {
			String value = (String) tableModel.getValueAt(rowIdx, 0);
			StudentInfo info = studentManager.getStudent(value);
			if (info != null) {
				setStudentDetail(info);			
			}			
		}
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateStudent() {
		List<StudentInfo> listStd = studentManager.getStudent();
		DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		studentTable.updateUI();

		for (StudentInfo info: listStd) {
			Object[] row = new Object[]{info.getStudentId(), info.getFirstName(), info.getLastName()};
			tableModel.addRow(row);
		}
		studentTable.updateUI();
	}
	
	public void display() {
		//show exist student
		updateStudent();
		setVisible(true);
	}
	
	private void getStudentDetail(StudentInfo info) {
		info.setStudentId(studentIdText.getText());
		info.setFirstName(studentFNamText.getText());
		info.setLastName(studentLNamText.getText());
		info.setMiddleName(studentMNamText.getText());
	}
	
	private void clearStudentDetail() {
		StudentInfo emptyInfo = new StudentInfo();
		setStudentDetail(emptyInfo);
	}
	private void setStudentDetail(StudentInfo info) {
		studentIdText.setText(info.getStudentId());
		studentFNamText.setText(info.getFirstName());
		studentLNamText.setText(info.getLastName());
		studentMNamText.setText(info.getMiddleName());
	}
	
	class studentSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			showStudentDetail();
		}
	}

}