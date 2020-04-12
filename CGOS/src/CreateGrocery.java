import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

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
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                int x = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to quit?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);

                if (x == JOptionPane.YES_OPTION) {
                	try {
						simpleJDBC.getInstance().Close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
		setBounds(100, 100, 482, 329);
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

		
		PerishableYes.setBounds(141, 30, 54, 23);
		PerishableYes.setActionCommand("Yes");
		contentPane.add(PerishableYes);
	
		
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
		

		
		JLabel expiryDateLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
		expiryDateLabel.setBounds(17, 62, 178, 16);
		contentPane.add(expiryDateLabel);
		
		JLabel storageTempLabel = new JLabel("Storage Temperature:");
		storageTempLabel.setBounds(113, 90, 144, 16);
		contentPane.add(storageTempLabel);
		
		ExpiryDateTextField = new JTextField();
		ExpiryDateTextField.setBounds(198, 57, 97, 26);
		contentPane.add(ExpiryDateTextField);
		ExpiryDateTextField.setColumns(10);
		
		StorageTemperatureTextField = new JTextField();
		StorageTemperatureTextField.setBounds(248, 85, 109, 26);
		contentPane.add(StorageTemperatureTextField);
		StorageTemperatureTextField.setColumns(10);
		
		JLabel produceLabel = new JLabel("Produce?");
		produceLabel.setBounds(269, 34, 61, 16);
		contentPane.add(produceLabel);
		
		SeasonTextField = new JTextField();
		SeasonTextField.setBounds(356, 57, 109, 26);
		contentPane.add(SeasonTextField);
		SeasonTextField.setColumns(10);
		
		JLabel seasonLabel = new JLabel("Season:");
		seasonLabel.setBounds(307, 62, 50, 16);
		contentPane.add(seasonLabel);
		
		ProduceYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seasonLabel.setEnabled(true);
				SeasonTextField.setEnabled(true);
			}
		});
		
		ProduceNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seasonLabel.setEnabled(false);
				SeasonTextField.setEnabled(false);
			}
		});
		
		//Disabling User from Clicking Yes or No for Produce
		PerishableNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduceGroup.clearSelection();
				ProduceYes.setEnabled(false);
				ProduceNo.setEnabled(false);
				produceLabel.setEnabled(false);
				seasonLabel.setEnabled(false);
				SeasonTextField.setEnabled(false);
				SeasonTextField.setText("");
				expiryDateLabel.setEnabled(false);
				ExpiryDateTextField.setEnabled(false);
				ExpiryDateTextField.setText("");
				storageTempLabel.setEnabled(false);
				StorageTemperatureTextField.setEnabled(false);
				StorageTemperatureTextField.setText("");
			}
		});
		
		PerishableYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduceYes.setEnabled(true);
				ProduceNo.setEnabled(true);
				produceLabel.setEnabled(true);
				seasonLabel.setEnabled(true);
				SeasonTextField.setEnabled(true);
				expiryDateLabel.setEnabled(true);
				ExpiryDateTextField.setEnabled(true);
				storageTempLabel.setEnabled(true);
				StorageTemperatureTextField.setEnabled(true);
			}
		});
		
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
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu MM;
				try {
					MM = new MainMenu();
					MM.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		BackButton.setBounds(6, 272, 117, 29);
		contentPane.add(BackButton);
		
		
		
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroceryInstanceInfo g = new GroceryInstanceInfo();
				
				if(PerishableGroup.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "Select \"Yes\" or \"No\" for Perishable");
					return;
				}
				if(ProduceGroup.getSelection() == null && PerishableGroup.getSelection().getActionCommand().equals("Yes")) {
					JOptionPane.showMessageDialog(null, "Select \"Yes\" or \"No\" for Produce");
					return;
				}

							
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
				else if(PerishableGroup.getSelection().getActionCommand().equals("No")) {
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
				g.dimensions = "(" + 
						HeightTextField.getText() + ", " + 
						WidthTextField.getText() + ", " + 
						LengthTextField.getText() +
						")";
				
				if(!ExpiryDateTextField.getText().matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
					JOptionPane.showMessageDialog(null, "Date has to respest this format: YYYY-MM-DD");
					return;
				}

				try {
					Integer.parseInt(g.weight);
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Weight has to be an integer!");
					return;
				}
				
				try {
					Integer.parseInt(g.price);
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Price has to be an integer!");
					return;	
				}
				
				try {
					Integer.parseInt(HeightTextField.getText());
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Height has to be an integer!");
					return;	
				}
				
				try {
					Integer.parseInt(WidthTextField.getText());
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Width has to be an integer!");
					return;	
				}
				
				try {
					Integer.parseInt(LengthTextField.getText());
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Length has to be an integer!");
					return;	
				}
				
				
				CreateGroceryConfirm CGC = new CreateGroceryConfirm();
				CGC.setVisible(true);
				setVisible(false);
				
				try {
					
					IngredientInfo ing1 = new IngredientInfo();
					ing1.shipName = "Titanic";
					ing1.roomNo = "212";
					ing1.type = "Test";
					ing1.weight = "100";
					int id = simpleJDBC.getInstance().CreateIngredient(ing1);
					g.orderID = String.valueOf(id);
					simpleJDBC.getInstance().CreateGrocery(g);
					
					System.out.println("Grocery created????");
				} catch (SQLException e1) {
					System.out.println("Couldnt add");
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}

			}
		});
	}
}
