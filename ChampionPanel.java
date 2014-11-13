import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ChampionPanel extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public ChampionPanel() {
		
		this.setLayout(new BorderLayout());
		
		tableModel.setColumnCount(2);
		
		tableModel.addRow(new String[]{"Name:"});
		tableModel.addRow(new String[]{"HP:"});
		tableModel.addRow(new String[]{"MP:"});
		tableModel.addRow(new String[]{"HP Regen:"});
		tableModel.addRow(new String[]{"MP Regen:"});
		tableModel.addRow(new String[]{"Attack Damage:"});
		tableModel.addRow(new String[]{"Attack Speed:"});
		tableModel.addRow(new String[]{"Range:"});
		tableModel.addRow(new String[]{"Defense:"});
		tableModel.addRow(new String[]{"Magic Defense:"});
		tableModel.addRow(new String[]{"Movement Speed:"});
		tableModel.addRow(new String[]{"Q:"});
		tableModel.addRow(new String[]{"W:"});
		tableModel.addRow(new String[]{"E:"});
		tableModel.addRow(new String[]{"R:"});
		
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
		table.setRowHeight(11,50);
		table.setRowHeight(12,50);
		table.setRowHeight(13,50);
		table.setRowHeight(14,50);
		
				
		this.add(table, BorderLayout.WEST);

	}

}
