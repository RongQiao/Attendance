package UserInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Logical.ApplicationLogical.DateAttd;

public class AttdTableModel extends AbstractTableModel{

	private String[] columnDays;
	private Object[][] data = {
			{new Boolean(true)}
	};
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AttdTableModel(List<DateAttd> daList) {
		columnDays = new String[daList.size()];
		data = new Object[1][daList.size()];
		int idx = 0;
		for (DateAttd da:daList) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			columnDays[idx] = dateFormat.format(da.getDt());
			data[0][idx] = da.isAttendant();
			idx++;
		}
	}

	public Class<? extends Object> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}
	
	@Override
	public int getColumnCount() {
		return columnDays.length;
	}

	public String getColumnName(int col) {
        return columnDays[col];
    }
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}
