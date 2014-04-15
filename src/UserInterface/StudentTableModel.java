package UserInterface;

import javax.swing.table.DefaultTableModel;

public class StudentTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*private String[] columnNames;
	private Object[][] data;
	*/
	public StudentTableModel(Object[][] data,
			String[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public int getColumnCount() {
		return super.getColumnCount();
	}

	@Override
	public int getRowCount() {
		return super.getRowCount();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return super.getValueAt(row, col);
	}
	
	//implementing this method is to let the boolean column showed as a check box
	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}
	
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}