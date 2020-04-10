import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateGrocery extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Create the frame.
	 */
	public CreateGrocery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Perishable?");
		lblNewLabel.setBounds(65, 34, 78, 16);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Yes");
		rdbtnNewRadioButton.setBounds(141, 30, 54, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("No");
		rdbtnNewRadioButton_1.setBounds(207, 30, 50, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Expiry Date:");
		lblNewLabel_1.setBounds(63, 62, 87, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Storage Temperature:");
		lblNewLabel_2.setBounds(6, 90, 144, 16);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(141, 57, 116, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 85, 116, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Produce?");
		lblNewLabel_3.setBounds(269, 34, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Yes");
		rdbtnNewRadioButton_2.setBounds(330, 30, 54, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("No");
		rdbtnNewRadioButton_3.setBounds(390, 30, 54, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(319, 57, 125, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Season:");
		lblNewLabel_4.setBounds(269, 62, 50, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Type:");
		lblNewLabel_5.setBounds(63, 128, 34, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Weight:");
		lblNewLabel_6.setBounds(168, 128, 54, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Price:");
		lblNewLabel_7.setBounds(296, 128, 34, 16);
		contentPane.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setBounds(98, 123, 69, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(218, 123, 78, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(330, 123, 78, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Height:");
		lblNewLabel_8.setBounds(43, 190, 61, 16);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Width:");
		lblNewLabel_9.setBounds(168, 190, 61, 16);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Length:");
		lblNewLabel_10.setBounds(286, 190, 61, 16);
		contentPane.add(lblNewLabel_10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(89, 185, 78, 26);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(207, 185, 78, 26);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(330, 185, 78, 26);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Dimensions");
		lblNewLabel_11.setBounds(178, 162, 89, 16);
		contentPane.add(lblNewLabel_11);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setBounds(168, 243, 117, 29);
		contentPane.add(btnNewButton);
	}
}
