package UserInterface;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor{  
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;  
      
    CustomTableCellEditor(JTable table){  
        this.table=table;  
        this.table.setOpaque(true);  
        this.table.setAlignmentY(JTable.LEFT_ALIGNMENT);  
    }  
      
    @Override  
    public Component getTableCellEditorComponent(JTable table,  
            Object value, boolean isSelected, int row, int column) {  
        this.table=(JTable)value;  
        return new JScrollPane(this.table);  
    }  

    @Override  
    public Object getCellEditorValue() {  
        return this.table;  
    }  
      
}  
