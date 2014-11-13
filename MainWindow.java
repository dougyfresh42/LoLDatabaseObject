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
	
	private DefaultListModel championsModel = new DefaultListModel();
	private DefaultListModel itemsModel = new DefaultListModel();
	private DefaultListModel masteriesModel = new DefaultListModel();
	private DefaultListModel runesModel = new DefaultListModel();
	private DefaultListModel summonerSpellsModel = new DefaultListModel();

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JSplitPane championPane = new JSplitPane();
		tabbedPane.addTab("Champions", null, championPane, null);
		
		championsModel.addElement("One");
		championsModel.addElement("Two");
		championsModel.addElement("Three");
		
		JList championNameList = new JList(championsModel);
		championPane.setLeftComponent(championNameList);		
		
		championPane.setRightComponent(new ChampionPanel());
		
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
