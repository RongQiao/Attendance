package UserInterface;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {  
	
    private static final long serialVersionUID = 4415155875184525824L;  
    JTable table;  
      
    CustomTableCellRenderer(JTable table){  
        this.table=table;  
        this.table.setOpaque(true);  
        this.table.setAlignmentY(JTable.LEFT_ALIGNMENT);  
    }  
      
    @Override  
    public Component getTableCellRendererComponent(JTable table, Object value,  
            boolean isSelected, boolean hasFocus, int row, int column) {  
        this.table=(JTable)value;  
        return new JScrollPane(this.table);   
    }  
}