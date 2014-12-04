import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class ItemPanel extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	
	private SQLHelper sql;

	public ItemPanel() 
	{
		this.setLayout(new BorderLayout());
		
		tableModel.setColumnCount(2);
		
		tableModel.addRow(new String[]{"Name:"});
		tableModel.addRow(new String[]{"Price:"});
		tableModel.addRow(new String[]{"Components:"});
		
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
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		table.setRowHeight(3, 40);
		
		this.add(table, BorderLayout.NORTH);
		
		// Description
		JTextArea descriptionArea = new JTextArea();
		descriptionArea.setSize(200, 300);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setEditable(false);
		
		StringBuilder ss = new StringBuilder();
		ss.append("Description:\n");
		
		descriptionArea.setText(ss.toString());
		
		this.add(descriptionArea, BorderLayout.CENTER);
	}

	public ItemPanel(String name, SQLHelper sql) 
	{
		this.sql = sql;
		
		try {
			ResultSet itemInfo = sql.selectItemByName(name);
			itemInfo.next();

			this.setLayout(new BorderLayout());
			
			tableModel.setColumnCount(2);
			
			tableModel.addRow(new String[]{"Name:", itemInfo.getString("ItemName")});
			tableModel.addRow(new String[]{"Price:", itemInfo.getString("Price")});
			
			// Combine all component attributes into single string
			String components = "";
			if(itemInfo.getString("Component1") != null)
			{
				components = components + itemInfo.getString("Component1") + ",";
			}
			if(itemInfo.getString("Component2") != null)
			{
				components = components + itemInfo.getString("Component2") + ",";
			}
			if(itemInfo.getString("Component3") != null)
			{
				components = components + itemInfo.getString("Component3") + ",";
			}
			if(itemInfo.getString("Component4") != null)
			{
				components = components + itemInfo.getString("Component4") + ",";
			}
			
			if(components != "")
			{
				components = components.substring(0,components.length() - 1);
			}
			else
			{
				components = "N/A";
			}
			
			tableModel.addRow(new String[]{"Components:", components});
			
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
			
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			
			table.setRowHeight(3, 40);
			
			this.add(table, BorderLayout.NORTH);
			
			// Description
			JTextArea descriptionArea = new JTextArea();
			descriptionArea.setSize(200, 300);
			descriptionArea.setLineWrap(true);
			descriptionArea.setWrapStyleWord(true);
			descriptionArea.setEditable(false);
			
			StringBuilder ss = new StringBuilder();
			ss.append("Description:\n");
			ss.append(itemInfo.getString("Description"));
			
			descriptionArea.setText(ss.toString());
			
			this.add(descriptionArea, BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
