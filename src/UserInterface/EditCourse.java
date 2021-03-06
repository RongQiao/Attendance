/**
 * 
 */
package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Logical.ApplicationLogical.ClassInfo;
import Logical.ApplicationLogical.CourseManager;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author qiao
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class EditCourse extends AttdFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CourseManager crsManager;
	
	//swing objects
	private JScrollPane leftPanel;
	private JPanel rightPanel;
	private JPanel rTopPanel;
	private JPanel rBotPanel;
	private JTable courseTable;
	private JLabel classNumLbl;
	private JTextField classNumText;
	private JLabel courseNumLbl;
	private JTextField courseNumText;
	private JLabel courseSecLbl;
	private JTextField courseSecText;
	private JLabel courseNamLbl;
	private JTextField courseNamText;
	private JButton addBtn;
	private JButton modifyBtn;
	private JButton removeBtn;
	private JButton enrollBtn;
	
	//constructor
	public EditCourse() {
		crsManager = CourseManager.getInstance();			
		initFrame();
		initTable();
		initDetailField();
		initBtn();
	}	
		
	public void display() {
		setVisible(true);
		//show exist course
		updateCourse();
	}
	
	@Override
	public void initFrame() {
		super.initFrame();
		//frame
		setTitle("Edit Course");	
	}
	
	private void initTable() {
		//left panel for course table		
		String[] columnNames = {"classNumber", "courseName"};
		String[][] tableCourse = {
				{"1","Course1"}
		};
		DefaultTableModel tableModel = new DefaultTableModel(tableCourse, columnNames);
		courseTable = new JTable(tableModel);
		ListSelectionModel selectionModel = courseTable.getSelectionModel();
		selectionModel.addListSelectionListener(new courseSelectionHandler());
		leftPanel = new JScrollPane(courseTable);
		mainPanel.add(leftPanel);
	}
	
	private void initDetailField() {
		//right panel for details and button
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(rightPanel);
		rTopPanel = new JPanel();
		//rTopPanel.setAlignmentX(CENTER_ALIGNMENT);		
		rBotPanel = new JPanel();
		//rBotPanel.setAlignmentX(CENTER_ALIGNMENT);
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
		classNumLbl = new JLabel("Class Number");
		courseNumLbl = new JLabel("Course Number");
		courseSecLbl = new JLabel("Course Section");
		courseNamLbl = new JLabel("Course Name");
		int txtFldLen = 10;
		classNumText = new JTextField(txtFldLen);
		courseNumText = new JTextField(txtFldLen);
		courseSecText = new JTextField(txtFldLen);
		courseNamText = new JTextField(txtFldLen);
		rtPanel1.add(classNumLbl);
		rtPanel1.add(classNumText);
		rtPanel2.add(courseNumLbl);
		rtPanel2.add(courseNumText);
		rtPanel3.add(courseSecLbl);
		rtPanel3.add(courseSecText);
		rtPanel4.add(courseNamLbl);
		rtPanel4.add(courseNamText);
	}
	
	private void initBtn() {
		addBtn = new JButton("Add Course");
		modifyBtn = new JButton("Modify Course");
		removeBtn = new JButton("Remove Course");
		enrollBtn = new JButton("Enroll Student");
		rBotPanel.add(addBtn);
		rBotPanel.add(modifyBtn);
		rBotPanel.add(removeBtn);
		//rBotPanel.add(enrollBtn);
		//set action listener
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourse();
			}
		});
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyCourse();
			}
		});
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCourse();
			}
		});
		enrollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//toEnrollFrame();
			}
		});
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addCourse() {
		ClassInfo classInfo = new ClassInfo();
		getCourseDetail(classInfo);		
		crsManager.addCourse(classInfo);
		updateCourse();
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyCourse() {
		ClassInfo classInfo = new ClassInfo();
		getCourseDetail(classInfo);		
		crsManager.modifyCourse(classInfo);
		updateCourse();
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeCourse() {
		ClassInfo cInfo = new ClassInfo();
		getCourseDetail(cInfo);
		if (cInfo.getClassNumber().length()>0) {
			crsManager.removeCourse(cInfo);			
		}
		updateCourse();
		clearCourseDetail();
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateCourse() {
		List<ClassInfo> listCrs = crsManager.getCourse();
		DefaultTableModel tableModel = (DefaultTableModel) courseTable.getModel();
		tableModel.getDataVector().removeAllElements();		
		courseTable.updateUI();

		for (ClassInfo cInfo: listCrs) {
			Object[] row = new Object[]{cInfo.getClassNumber(), cInfo.getCourseName()};
			tableModel.addRow(row);
		}
		courseTable.updateUI();
	}
	
	public void showCourseDetail() {
		int rowIdx = courseTable.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) courseTable.getModel();
		if (rowIdx < tableModel.getRowCount()) {
			String value = (String) tableModel.getValueAt(rowIdx, 0);
			ClassInfo classInfo = crsManager.getCourse(value);
			if (classInfo != null) {
				setCourseDetail(classInfo);			
			}			
		}
	}
	
	private void getCourseDetail(ClassInfo cInfo) {
		cInfo.setClassNumber(classNumText.getText());
		cInfo.setCourseNumber(courseNumText.getText());
		cInfo.setCourseSection(courseSecText.getText());
		cInfo.setCourseName(courseNamText.getText());
	}
	
	private void setCourseDetail(ClassInfo cInfo) {
		classNumText.setText(cInfo.getClassNumber());
		courseNumText.setText(cInfo.getCourseNumber());
		courseSecText.setText(cInfo.getCourseSection());
		courseNamText.setText(cInfo.getCourseName());
	}

	private void clearCourseDetail() {
		ClassInfo emptyInfo = new ClassInfo();
		setCourseDetail(emptyInfo);
	}

	
	class courseSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			showCourseDetail();
		}
	}

}