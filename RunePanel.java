import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class RunePanel extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	
	/**
	 * Create the panel.
	 */
	public RunePanel() 
	{
		this.setLayout(new BorderLayout());
		
		tableModel.setColumnCount(2);
		
		tableModel.addRow(new String[]{"Name:"});
		tableModel.addRow(new String[]{"Tier:"});
		tableModel.addRow(new String[]{"Modified Stat:"});
		tableModel.addRow(new String[]{"Description:"});
		
		table = new JTable(tableModel)
		{
		      public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setCellSelectionEnabled(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		table.setRowHeight(3, 40);
		
		this.add(table, BorderLayout.WEST);
	}

}
