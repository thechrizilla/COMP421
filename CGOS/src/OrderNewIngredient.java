import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class OrderNewIngredient extends JFrame {

	private JPanel contentPane;
	private JTextField IngredientTypeTextField;
	private JTextField WeightTextField;
	MainMenu MM;

	public OrderNewIngredient() throws SQLException {
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
		
		JLabel lblNewLabel = new JLabel("Ship Name:");
		lblNewLabel.setBounds(257, 66, 71, 16);
		contentPane.add(lblNewLabel);
		
		JLabel restaurantNameLabel = new JLabel("Restaurant Name:");
		restaurantNameLabel.setBounds(217, 111, 111, 16);
		contentPane.add(restaurantNameLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Ingredient Type:");
		lblNewLabel_2.setBounds(226, 150, 102, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Weight:");
		lblNewLabel_3.setBounds(281, 188, 47, 16);
		contentPane.add(lblNewLabel_3);
		
		JComboBox ShipNameComboBox = new JComboBox(simpleJDBC.getInstance().GetShipNames().toArray());
		ShipNameComboBox.setBounds(327, 62, 163, 27);
		contentPane.add(ShipNameComboBox);

		JComboBox RestaurantComboBox = new JComboBox();
		RestaurantComboBox.setBounds(327, 107, 163, 27);
		contentPane.add(RestaurantComboBox);
		RestaurantComboBox.setEnabled(false);
		restaurantNameLabel.setEnabled(false);
		
		ShipNameComboBox.setSelectedIndex(-1);
		ShipNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object o = ShipNameComboBox.getSelectedItem();
					if (o == null) return;
					ArrayList<String> restaurants = simpleJDBC.getInstance().GetRestaurants(o.toString());
					RestaurantComboBox.removeAllItems();
					
					for (String r : restaurants) {
						RestaurantComboBox.insertItemAt(r, 0);
					}
					RestaurantComboBox.setEnabled(true);
					restaurantNameLabel.setEnabled(true);
					RestaurantComboBox.setSelectedIndex(-1);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		IngredientTypeTextField = new JTextField();
		IngredientTypeTextField.setBounds(333, 145, 157, 26);
		contentPane.add(IngredientTypeTextField);
		IngredientTypeTextField.setColumns(10);
		
		WeightTextField = new JTextField();
		WeightTextField.setBounds(333, 183, 157, 26);
		contentPane.add(WeightTextField);
		WeightTextField.setColumns(10);
		
		JButton OrderButton = new JButton("Order");
		OrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngredientInfo ing = new IngredientInfo();
				ing.shipName = ShipNameComboBox.getSelectedItem().toString();
				ing.type = IngredientTypeTextField.getText();
				ing.weight = WeightTextField.getText();
				
				String restString = RestaurantComboBox.getSelectedItem().toString();
				StringBuilder roomNo = new StringBuilder();
				
				for (int i = restString.length() - 1; i != 0; i--) {
					if (restString.charAt(i) == ')') continue;
					if (restString.charAt(i) == '(') break;
					roomNo.append(restString.charAt(i));
				}
				
				ing.roomNo = roomNo.reverse().toString();
				
				try {
					simpleJDBC.getInstance().CreateIngredient(ing);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		OrderButton.setBounds(355, 380, 117, 29);
		contentPane.add(OrderButton);
		
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
		BackButton.setBounds(34, 409, 117, 29);
		contentPane.add(BackButton);
	}
}
