import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateGrocery extends JFrame {

	private JPanel contentPane;
	private JTextField ExpiryDateTextField;
	private JTextField StorageTemperatureTextField;
	private JTextField SeasonTextField;
	private JTextField TypeTextField;
	private JTextField WeightTextField;
	private JTextField PriceTextField;
	private JTextField HeightTextField;
	private JTextField WidthTextField;
	private JTextField LengthTextField;

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
		
		
		// Radio Buttons
		JRadioButton PerishableYes = new JRadioButton("Yes");
		JRadioButton PerishableNo = new JRadioButton("No");
		JRadioButton ProduceYes = new JRadioButton("Yes");
		JRadioButton ProduceNo = new JRadioButton("No");
		
		// Button Group
		ButtonGroup PerishableGroup = new ButtonGroup();
		PerishableGroup.add(PerishableYes);
		PerishableGroup.add(PerishableNo);
		
		ButtonGroup ProduceGroup = new ButtonGroup();
		ProduceGroup.add(ProduceYes);
		ProduceGroup.add(ProduceNo);
		
		//Radio Buttons for Perishable
		PerishableYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduceYes.setEnabled(true);
				ProduceNo.setEnabled(true);
			}
		});
		
		PerishableYes.setBounds(141, 30, 54, 23);
		PerishableYes.setActionCommand("Yes");
		contentPane.add(PerishableYes);
		
		//Disabling User from Clicking Yes or No for Produce
		PerishableNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduceGroup.clearSelection();
				ProduceYes.setEnabled(false);
				ProduceNo.setEnabled(false);
			}
		});
		
		PerishableNo.setBounds(207, 30, 50, 23);
		PerishableNo.setActionCommand("No");
		contentPane.add(PerishableNo);
		

		
		// Radio Buttons for Produce

		ProduceYes.setBounds(330, 30, 54, 23);
		ProduceYes.setActionCommand("Yes");
		contentPane.add(ProduceYes);
		

		ProduceNo.setBounds(390, 30, 54, 23);
		ProduceNo.setActionCommand("No");
		contentPane.add(ProduceNo);
		

		
		JLabel lblNewLabel_1 = new JLabel("Expiry Date:");
		lblNewLabel_1.setBounds(63, 62, 87, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Storage Temperature:");
		lblNewLabel_2.setBounds(6, 90, 144, 16);
		contentPane.add(lblNewLabel_2);
		
		ExpiryDateTextField = new JTextField();
		ExpiryDateTextField.setBounds(141, 57, 116, 26);
		contentPane.add(ExpiryDateTextField);
		ExpiryDateTextField.setColumns(10);
		
		StorageTemperatureTextField = new JTextField();
		StorageTemperatureTextField.setBounds(141, 85, 116, 26);
		contentPane.add(StorageTemperatureTextField);
		StorageTemperatureTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Produce?");
		lblNewLabel_3.setBounds(269, 34, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		SeasonTextField = new JTextField();
		SeasonTextField.setBounds(319, 57, 125, 26);
		contentPane.add(SeasonTextField);
		SeasonTextField.setColumns(10);
		
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
		
		TypeTextField = new JTextField();
		TypeTextField.setBounds(98, 123, 69, 26);
		contentPane.add(TypeTextField);
		TypeTextField.setColumns(10);
		
		WeightTextField = new JTextField();
		WeightTextField.setBounds(218, 123, 78, 26);
		contentPane.add(WeightTextField);
		WeightTextField.setColumns(10);
		
		PriceTextField = new JTextField();
		PriceTextField.setBounds(330, 123, 78, 26);
		contentPane.add(PriceTextField);
		PriceTextField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Height:");
		lblNewLabel_8.setBounds(43, 190, 61, 16);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Width:");
		lblNewLabel_9.setBounds(168, 190, 61, 16);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Length:");
		lblNewLabel_10.setBounds(286, 190, 61, 16);
		contentPane.add(lblNewLabel_10);
		
		HeightTextField = new JTextField();
		HeightTextField.setBounds(89, 185, 78, 26);
		contentPane.add(HeightTextField);
		HeightTextField.setColumns(10);
		
		WidthTextField = new JTextField();
		WidthTextField.setBounds(207, 185, 78, 26);
		contentPane.add(WidthTextField);
		WidthTextField.setColumns(10);
		
		LengthTextField = new JTextField();
		LengthTextField.setBounds(330, 185, 78, 26);
		contentPane.add(LengthTextField);
		LengthTextField.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Dimensions");
		lblNewLabel_11.setBounds(178, 162, 89, 16);
		contentPane.add(lblNewLabel_11);
		
		JButton EnterButton = new JButton("Enter");
		EnterButton.setBounds(168, 243, 117, 29);
		contentPane.add(EnterButton);
		
		JButton BackButton = new JButton("Back");
		BackButton.setBounds(6, 243, 117, 29);
		contentPane.add(BackButton);
		
		
		
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroceryInstanceInfo g = new GroceryInstanceInfo();
							
				if (PerishableGroup.getSelection().getActionCommand().equals("Yes")) {
					g.isPerishable = true;
					ProduceNo.setEnabled(true);
					ProduceYes.setEnabled(true);	
					
					if (ProduceGroup.getSelection().getActionCommand().equals("Yes")) {
						g.isProduce = true;
					}
					else {
						g.isProduce = false;
					}
				}
				else {
					g.isPerishable = false;
					ProduceNo.setEnabled(false);
					ProduceYes.setEnabled(false);
				}
				
				g.pe_storageTemp = StorageTemperatureTextField.getText();
				g.type = TypeTextField.getText();
				g.pr_season = SeasonTextField.getText();
				g.price = PriceTextField.getText();
				g.pe_expiryDate = ExpiryDateTextField.getText();
				g.weight = WeightTextField.getText();
				g.dimensions = "(" + LengthTextField.getText() + ", " + WidthTextField.getText() + ", " + HeightTextField.getText() + ")";
				
				try {
					simpleJDBC user = new simpleJDBC();
					IngredientInfo ing1 = new IngredientInfo();
					ing1.shipName = "Titanic";
					ing1.roomNo = "212";
					ing1.type = "Test";
					ing1.weight = "100";
					int id = user.CreateIngredient(ing1);
					g.orderID = String.valueOf(id);
					user.CreateGrocery(g);
					
					System.out.println("Grocery created????");
					user.Close();
				} catch (SQLException e1) {
					System.out.println("Couldnt add");
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}

			}
		});
	}
}
