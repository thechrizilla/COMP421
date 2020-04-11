import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class CreateGroceryDisplay extends JFrame {

	private JPanel contentPane;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGroceryDisplay frame = new CreateGroceryDisplay();
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
	public CreateGroceryDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_1 = new JTable();
		table_1.setForeground(Color.BLACK);
		table_1.setToolTipText("sss");
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"sstrj g", new Integer(2), new Float(3.0f)},
				{"string2", new Integer(2), null},
				{"sstring4", new Integer(5), new Float(3.0f)},
			},
			new String[] {
				"New column", "New column2", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.setBounds(10, 0, 811, 441);
		contentPane.add(table_1);
	}

}
