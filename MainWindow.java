import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	
	private SQLHelper sql;
	
	private DefaultListModel<String> championsModel = new DefaultListModel<String>();
	private DefaultListModel<String> itemsModel = new DefaultListModel<String>();
	private DefaultListModel<String> masteriesModel = new DefaultListModel<String>();
	private DefaultListModel<String> runesModel = new DefaultListModel<String>();
	private DefaultListModel<String> summonerSpellsModel = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 850, 700);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			sql = new SQLHelper();
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabbedPane, BorderLayout.CENTER);
			
			// Champion Tab
			JSplitPane championPane = new JSplitPane();
			championPane.setDividerLocation(150);
			championPane.setResizeWeight(.25);
			tabbedPane.addTab("Champions", null, championPane, null);
			
			ResultSet champs = sql.selectAllChampion();
			while(champs.next())
			{
				championsModel.addElement(champs.getString("ChampName"));
			}
			
			JList<String> championNameList = new JList<String>(championsModel);
			championNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = championNameList.getSelectedValue();
					championPane.setRightComponent(new ChampionPanel(name, sql));
					championPane.setDividerLocation(150);
				}
			});
			JScrollPane champScrollPane = new JScrollPane();
			champScrollPane.setViewportView(championNameList);
			championPane.setLeftComponent(champScrollPane);	
			
			championPane.setRightComponent(new ChampionPanel());
				
			
			// Item Tab
			JSplitPane itemPane = new JSplitPane();
			itemPane.setDividerLocation(200);
			itemPane.setResizeWeight(.25);
			tabbedPane.addTab("Items", null, itemPane, null);
			
			ResultSet items = sql.selectAllItem();
			while(items.next())
			{
				itemsModel.addElement(items.getString("ItemName"));
			}
			
			JList<String> itemNameList = new JList<String>(itemsModel);
			itemNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = itemNameList.getSelectedValue();
					itemPane.setRightComponent(new ItemPanel(name, sql));
					itemPane.setDividerLocation(200);
				}
			});
			JScrollPane itemScrollPane = new JScrollPane();
			itemScrollPane.setViewportView(itemNameList);
			itemPane.setLeftComponent(itemScrollPane);	
			
			itemPane.setRightComponent(new ItemPanel());
			
			// Masteries Tab
			JSplitPane masteriesPane = new JSplitPane();
			masteriesPane.setDividerLocation(150);
			masteriesPane.setResizeWeight(.25);
			tabbedPane.addTab("Masteries", null, masteriesPane, null);
			
			ResultSet masteries = sql.selectAllMastery();
			while(masteries.next())
			{
				masteriesModel.addElement(masteries.getString("MasteryName"));
			}
			
			JList<String> masteryNameList = new JList<String>(masteriesModel);
			masteryNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = masteryNameList.getSelectedValue();
					masteriesPane.setRightComponent(new MasteryPanel(name, sql));
					masteriesPane.setDividerLocation(150);
				}
			});
			JScrollPane masteryScrollPane = new JScrollPane();
			masteryScrollPane.setViewportView(masteryNameList);
			masteriesPane.setLeftComponent(masteryScrollPane);	
			
			masteriesPane.setRightComponent(new MasteryPanel());
			
			// Runes Tab
			JSplitPane runePane = new JSplitPane();
			runePane.setDividerLocation(250);
			runePane.setResizeWeight(.25);
			tabbedPane.addTab("Rune", null, runePane, null);
			
			ResultSet runes = sql.selectAllRune();
			while(runes.next())
			{
				runesModel.addElement(runes.getString("RuneName"));
			}
			
			JList<String> runeNameList = new JList<String>(runesModel);
			runeNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = runeNameList.getSelectedValue();
					runePane.setRightComponent(new RunePanel(name, sql));
					runePane.setDividerLocation(250);
				}
			});
			JScrollPane runeScrollPane = new JScrollPane();
			runeScrollPane.setViewportView(runeNameList);
			runePane.setLeftComponent(runeScrollPane);	
			
			runePane.setRightComponent(new RunePanel());
			
			// Summoner Spells Tab
			JSplitPane summonerSpellsPane = new JSplitPane();
			summonerSpellsPane.setDividerLocation(150);
			summonerSpellsPane.setResizeWeight(.25);
			tabbedPane.addTab("Summoner Spells", null, summonerSpellsPane, null);
			
			ResultSet summonerSpells = sql.selectAllSummonerSpell();
			while(summonerSpells.next())
			{
				summonerSpellsModel.addElement(summonerSpells.getString("SummonerSpellName"));
			}
			
			JList<String> summonerSpellNameList = new JList<String>(summonerSpellsModel);
			summonerSpellNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = summonerSpellNameList.getSelectedValue();
					summonerSpellsPane.setRightComponent(new SummonerSpellPanel(name, sql));
					summonerSpellsPane.setDividerLocation(150);
				}
			});
			JScrollPane ssScrollPane = new JScrollPane();
			ssScrollPane.setViewportView(summonerSpellNameList);
			summonerSpellsPane.setLeftComponent(ssScrollPane);	
			
			summonerSpellsPane.setRightComponent(new SummonerSpellPanel());
			
			// Players
			JSplitPane playerPane = new JSplitPane();
			playerPane.setDividerLocation(150);
			playerPane.setResizeWeight(.25);
			tabbedPane.addTab("Players", null, playerPane, null);
			
			JTextField searchBox = new JTextField(20);
			searchBox.setSize(20, 100);
			JButton searchBtn = new JButton("Search");
			searchBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					String playerName = searchBox.getText();
					JScrollPane playerScrollPane = new JScrollPane();
					playerScrollPane.setViewportView(new PlayerPanel(sql, playerName));
					playerPane.setRightComponent(playerScrollPane);
					playerPane.setDividerLocation(150);
				}
			});
			
			JPanel searchPanel = new JPanel();
			searchPanel.setLayout(new BorderLayout());
			searchPanel.add(searchBox, BorderLayout.NORTH);
			searchPanel.add(searchBtn, BorderLayout.SOUTH);
			
			playerPane.setLeftComponent(searchPanel);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
