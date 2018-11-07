package dialogEDV.Skill;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class Gui {

	public static JFrame frame = new JFrame("Skill-Level");
	public static String header[] = { "Name", "Skills", "Beschreibung", "Lust" };
	public static Object skills[][] = { { "TestName", "Java, C++, HTML", "Java", "Anfänger" } };
	public static Object skills2[][] = { { "", "", "", "" } };
	public static DefaultTableModel model = new DefaultTableModel(null, header);
	public static DefaultTableModel model2 = new DefaultTableModel(skills2, header);
	public static JTable tabel = new JTable(model);
	public static JTable user = new JTable(model2);
	public static JScrollPane sp = new JScrollPane(tabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	public static JScrollPane sp2 = new JScrollPane(user, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public static JButton send = new JButton(" Senden ");
	static String[] dropdown = { "Keine Lust", "Wenig Lust", "Lust geht so", "Viel Lust", "Sehr viel Lust" };
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static JComboBox comboBox = new JComboBox(dropdown);
	static TableCellEditor editor = new DefaultCellEditor(comboBox);
	static JTextField search = new JTextField("Search");
	static JPanel panel = new JPanel();

	public static void main(String[] args) {

		GridBagConstraints c = new GridBagConstraints();

		DatenbankInfo.inPut();

		frame.setVisible(true);
		frame.setSize(700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setLocationRelativeTo(null);

		search.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Font schrift1 = (search.getFont().deriveFont(Font.BOLD, 15));
		search.setFont(schrift1);
		search.addActionListener(new Search());

		send.setFocusable(false);
		send.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Font schrift = (send.getFont().deriveFont(Font.BOLD, 20));
		send.setFont(schrift);
		send.addActionListener(new btnAction());

		user.getColumnModel().getColumn(3).setCellEditor(editor);
		tabel.setVisible(true);
		tabel.setEnabled(false);

		c.weightx = 0.5;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		frame.getContentPane().add(search, c);
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 2;
		frame.getContentPane().add(sp, c);
		c.gridy = 2;
		c.gridx = 0;
		c.weighty = 0.1;
		c.gridwidth = 1;
		frame.getContentPane().add(sp2, c);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridwidth = 1;
		c.weightx = 0.1;
		frame.getContentPane().add(send, c);

	}

}
