import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class order_new_ingredient {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order_new_ingredient window = new order_new_ingredient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public order_new_ingredient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Order");
		btnNewButton.setBounds(170, 204, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Ingredient Type:");
		lblNewLabel.setBounds(105, 126, 102, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ship Name:");
		lblNewLabel_1.setBounds(133, 70, 75, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Weight:");
		lblNewLabel_2.setBounds(161, 154, 47, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(210, 121, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(210, 149, 130, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(6, 243, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(210, 66, 127, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Restaurant Name:");
		lblNewLabel_3.setBounds(96, 98, 111, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(210, 96, 127, 27);
		frame.getContentPane().add(comboBox_1);
		
	}
}
