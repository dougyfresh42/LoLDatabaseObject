import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;

import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JSplitPane infoPane = new JSplitPane();
		infoPane.setResizeWeight(.25);
		tabbedPane.addTab("Champions", null, infoPane, null);
		
		championsModel.addElement("One");
		championsModel.addElement("Two");
		championsModel.addElement("Three");
		
		JList<String> championNameList = new JList<String>(championsModel);
		infoPane.setLeftComponent(championNameList);		
		
		infoPane.setRightComponent(new ChampionPanel());
		
		JPanel itemPane = new JPanel();
		tabbedPane.addTab("Items", null, itemPane, null);
		
		JPanel masteriesPane = new JPanel();
		tabbedPane.addTab("Masteries", null, masteriesPane, null);
		
		JPanel runePane = new JPanel();
		tabbedPane.addTab("Rune", null, runePane, null);
		
		JPanel summonerSpellsPane = new JPanel();
		tabbedPane.addTab("Summoner Spells", null, summonerSpellsPane, null);
	}

}
