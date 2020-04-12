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
import javax.swing.JComboBox;

public class CreateGrocery extends JFrame {

	private JPanel contentPane;
	private JTextField ExpiryDateTextField;
	private JTextField StorageTemperatureTextField;
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
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Is the grocery perishable?");
		lblNewLabel.setBounds(113, 34, 168, 16);
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

		
		PerishableYes.setBounds(277, 30, 54, 23);
		PerishableYes.setActionCommand("Yes");
		contentPane.add(PerishableYes);
	
		
		PerishableNo.setBounds(343, 30, 50, 23);
		PerishableNo.setActionCommand("No");
		contentPane.add(PerishableNo);
		
		
		// Radio Buttons for Produce

		ProduceYes.setBounds(602, 30, 54, 23);
		ProduceYes.setActionCommand("Yes");
		contentPane.add(ProduceYes);
		

		ProduceNo.setBounds(668, 30, 54, 23);
		ProduceNo.setActionCommand("No");
		contentPane.add(ProduceNo);
		

		
		JLabel expiryDateLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
		expiryDateLabel.setBounds(203, 100, 154, 16);
		contentPane.add(expiryDateLabel);
		
		JLabel storageTempLabel = new JLabel("Storage Temperature:");
		storageTempLabel.setBounds(224, 133, 144, 16);
		contentPane.add(storageTempLabel);
		
		ExpiryDateTextField = new JTextField();
		ExpiryDateTextField.setBounds(360, 95, 136, 26);
		contentPane.add(ExpiryDateTextField);
		ExpiryDateTextField.setColumns(10);
		
		StorageTemperatureTextField = new JTextField();
		StorageTemperatureTextField.setBounds(360, 128, 136, 26);
		contentPane.add(StorageTemperatureTextField);
		StorageTemperatureTextField.setColumns(10);
		
		JLabel produceLabel = new JLabel("Is the grocery a produce?");
		produceLabel.setBounds(440, 34, 168, 16);
		contentPane.add(produceLabel);
		
		JLabel seasonLabel = new JLabel("Season:");
		seasonLabel.setBounds(308, 166, 50, 16);
		contentPane.add(seasonLabel);
		
		JComboBox SeasonComboBox = new JComboBox(new String[] {"Winter", "Spring", "Summer", "Autumn"});
		SeasonComboBox.setBounds(360, 165, 136, 26);
		contentPane.add(SeasonComboBox);
		SeasonComboBox.setSelectedIndex(-1);
		
		ProduceYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seasonLabel.setEnabled(true);
				SeasonComboBox.setEnabled(true);
			}
		});
		
		ProduceNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seasonLabel.setEnabled(false);
				SeasonComboBox.setEnabled(false);
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
				SeasonComboBox.setEnabled(false);
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
				SeasonComboBox.setEnabled(true);
				expiryDateLabel.setEnabled(true);
				ExpiryDateTextField.setEnabled(true);
				storageTempLabel.setEnabled(true);
				StorageTemperatureTextField.setEnabled(true);
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("Type:");
		lblNewLabel_5.setBounds(70, 221, 34, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Weight:");
		lblNewLabel_6.setBounds(314, 221, 54, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Price:");
		lblNewLabel_7.setBounds(607, 221, 34, 16);
		contentPane.add(lblNewLabel_7);
		
		TypeTextField = new JTextField();
		TypeTextField.setBounds(102, 216, 136, 26);
		contentPane.add(TypeTextField);
		TypeTextField.setColumns(10);
		
		WeightTextField = new JTextField();
		WeightTextField.setBounds(360, 216, 136, 26);
		contentPane.add(WeightTextField);
		WeightTextField.setColumns(10);
		
		PriceTextField = new JTextField();
		PriceTextField.setBounds(640, 216, 136, 26);
		contentPane.add(PriceTextField);
		PriceTextField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Height:");
		lblNewLabel_8.setBounds(56, 313, 61, 16);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Width:");
		lblNewLabel_9.setBounds(320, 313, 61, 16);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Length:");
		lblNewLabel_10.setBounds(595, 313, 61, 16);
		contentPane.add(lblNewLabel_10);
		
		HeightTextField = new JTextField();
		HeightTextField.setBounds(102, 308, 136, 26);
		contentPane.add(HeightTextField);
		HeightTextField.setColumns(10);
		
		WidthTextField = new JTextField();
		WidthTextField.setBounds(360, 308, 136, 26);
		contentPane.add(WidthTextField);
		WidthTextField.setColumns(10);
		
		LengthTextField = new JTextField();
		LengthTextField.setBounds(640, 308, 136, 26);
		contentPane.add(LengthTextField);
		LengthTextField.setColumns(10);
		
		JButton EnterButton = new JButton("Enter");
		EnterButton.setBounds(362, 372, 117, 29);
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
		BackButton.setBounds(26, 420, 117, 29);
		contentPane.add(BackButton);
		
		JLabel DimensionLabel = new JLabel("Dimensions");
		DimensionLabel.setBounds(382, 280, 78, 16);
		contentPane.add(DimensionLabel);
		
		
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroceryInstanceInfo g = new GroceryInstanceInfo();
				
				if (PerishableGroup.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "Select \"Yes\" or \"No\" for Perishable");
					return;
				}
				if (ProduceGroup.getSelection() == null && PerishableGroup.getSelection().getActionCommand().equals("Yes")) {
					JOptionPane.showMessageDialog(null, "Select \"Yes\" or \"No\" for Produce");
					return;
				}

							
				if (PerishableGroup.getSelection().getActionCommand().equals("Yes")) {
					g.isPerishable = true;
					ProduceNo.setEnabled(true);
					ProduceYes.setEnabled(true);	
					
					if (ProduceGroup.getSelection().getActionCommand().equals("Yes")) {
						g.isProduce = true;
						if (SeasonComboBox.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Select a season!");
							return;
						}
						g.pr_season = SeasonComboBox.getSelectedItem().toString();
					}
					else {
						g.isProduce = false;
					}
					
					if(!ExpiryDateTextField.getText().matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
						JOptionPane.showMessageDialog(null, "Date has to respect this format: YYYY-MM-DD");
						return;
					}
					try {
						if (Float.parseFloat(StorageTemperatureTextField.getText()) < 0) {
							JOptionPane.showMessageDialog(null, "Temperature cannot be negative!");
							return;	
						}
					}
					catch(NumberFormatException e2){
						JOptionPane.showMessageDialog(null, "Temperature has to be a number!");
						return;
					}
				}
				else if(PerishableGroup.getSelection().getActionCommand().equals("No")) {
					g.isPerishable = false;
					
					ProduceNo.setEnabled(false);
					ProduceYes.setEnabled(false);
				}

				if (TypeTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter a type!");
					return;	
				}
				
				g.pe_storageTemp = StorageTemperatureTextField.getText();
				g.type = TypeTextField.getText();
				g.price = PriceTextField.getText();
				g.pe_expiryDate = ExpiryDateTextField.getText();
				g.weight = WeightTextField.getText();
				g.dimensions = "(" + 
						HeightTextField.getText() + ", " + 
						WidthTextField.getText() + ", " + 
						LengthTextField.getText() +
						")";
				
				try {
					if (Float.parseFloat(WeightTextField.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Weight cannot be negative!");
						return;	
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Weight has to be a number!");
					return;
				}
				
				try {
					if (Float.parseFloat(PriceTextField.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Price cannot be negative!");
						return;	
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Price has to be a number!");
					return;	
				}
				
				try {
					if (Float.parseFloat(HeightTextField.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Height cannot be negative!");
						return;	
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Height has to be a number!");
					return;	
				}
				
				try {
					if (Float.parseFloat(WidthTextField.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Width cannot be negative!");
						return;	
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Width has to be a number!");
					return;	
				}
				
				try {
					if (Float.parseFloat(LengthTextField.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Length cannot be negative!");
						return;	
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Length has to be a number!");
					return;	
				}
				
				
				CreateGroceryConfirm CGC;
				try {
					CGC = new CreateGroceryConfirm(g);
					CGC.setVisible(true);

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				setVisible(false);
			}
		});
	}
}
