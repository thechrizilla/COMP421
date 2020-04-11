import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderNewIngredient extends JFrame {

	private JPanel contentPane;
	private JTextField IngredientTypeTextField;
	private JTextField WeightTextfield;
	MainMenu MM = new MainMenu();

	public OrderNewIngredient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ship Name:");
		lblNewLabel.setBounds(129, 66, 71, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Restaurant Name:");
		lblNewLabel_1.setBounds(89, 94, 111, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ingredient Type:");
		lblNewLabel_2.setBounds(98, 122, 102, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Weight:");
		lblNewLabel_3.setBounds(153, 150, 47, 16);
		contentPane.add(lblNewLabel_3);
		
		JComboBox ShipNameComboBox = new JComboBox();
		ShipNameComboBox.setBounds(212, 62, 130, 27);
		contentPane.add(ShipNameComboBox);
		
		JComboBox RestaurantComboBox = new JComboBox();
		RestaurantComboBox.setBounds(212, 90, 130, 27);
		contentPane.add(RestaurantComboBox);
		
		IngredientTypeTextField = new JTextField();
		IngredientTypeTextField.setBounds(212, 117, 130, 26);
		contentPane.add(IngredientTypeTextField);
		IngredientTypeTextField.setColumns(10);
		
		WeightTextfield = new JTextField();
		WeightTextfield.setBounds(212, 145, 130, 26);
		contentPane.add(WeightTextfield);
		WeightTextfield.setColumns(10);
		
		JButton OrderButton = new JButton("Order");
		OrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		OrderButton.setBounds(169, 219, 117, 29);
		contentPane.add(OrderButton);
		
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu MM = new MainMenu();
				MM.setVisible(true);

			}
		});
		BackButton.setBounds(6, 243, 117, 29);
		contentPane.add(BackButton);
	}
}
