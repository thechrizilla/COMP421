import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class CreateGroceryConfirm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGroceryConfirm frame = new CreateGroceryConfirm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CreateGroceryConfirm() throws SQLException {
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton EnterButton = new JButton("Enter");
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGroceryDisplay CGD = new CreateGroceryDisplay();
				CGD.setVisible(true);
				dispose();
			}
		});
		EnterButton.setBounds(167, 223, 117, 29);
		contentPane.add(EnterButton);
		
		JLabel lblNewLabel = new JLabel("Ship Name:");
		lblNewLabel.setBounds(126, 74, 71, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox ShipNameComboBox = new JComboBox(simpleJDBC.getInstance().GetShipNames().toArray());
		ShipNameComboBox.setBounds(196, 70, 117, 27);
		contentPane.add(ShipNameComboBox);
		
		JLabel restaurantNameLabel = new JLabel("Restaurant Name:");
		restaurantNameLabel.setBounds(96, 102, 101, 16);
		contentPane.add(restaurantNameLabel);
		
		JComboBox RestaurantComboBox = new JComboBox();
		RestaurantComboBox.setBounds(196, 98, 117, 27);
		contentPane.add(RestaurantComboBox);
		RestaurantComboBox.setEnabled(false);
		restaurantNameLabel.setEnabled(false);
		
		JComboBox IngredientsComboBox = new JComboBox();
		IngredientsComboBox.setBounds(196, 126, 117, 29);
		contentPane.add(IngredientsComboBox);
		
		JLabel ingredientLabel = new JLabel("Ingredient:");
		ingredientLabel.setBounds(126, 133, 71, 14);
		contentPane.add(ingredientLabel);
		IngredientsComboBox.setEnabled(false);
		ingredientLabel.setEnabled(false);
		
		
		ShipNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RestaurantComboBox.setEnabled(false);
					restaurantNameLabel.setEnabled(false);
					IngredientsComboBox.setEnabled(false);
					ingredientLabel.setEnabled(false);
					ArrayList<String> restaurants = simpleJDBC.getInstance().GetRestaurants(ShipNameComboBox.getSelectedItem().toString());
					RestaurantComboBox.removeAllItems();
					for (String r : restaurants) {
						RestaurantComboBox.addItem(r);
					}
					RestaurantComboBox.setEnabled(true);
					restaurantNameLabel.setEnabled(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		RestaurantComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngredientsComboBox.setEnabled(true);
				ingredientLabel.setEnabled(true);
				
				RestaurantInfo restaurant = new RestaurantInfo();
				restaurant.shipname = ShipNameComboBox.getSelectedItem().toString();
				
				String restaurantWithRoom = RestaurantComboBox.getSelectedItem().toString();
				
				StringBuilder roomNo = new StringBuilder();
				StringBuilder restaurantName = new StringBuilder();
				
				int idx = 0;
				
				for (int i = restaurantWithRoom.length() - 1; i != 0; i--) {
					if (restaurantWithRoom.charAt(i) == ')') continue;
					if (restaurantWithRoom.charAt(i) == '(') {
						idx = i - 1;
						break;
					}
					roomNo.append(restaurantWithRoom.charAt(i));
				}
				
				for (int i = idx; i >= 0; i--) {			
					restaurantName.append(restaurantWithRoom.charAt(i));
				}
				
				restaurant.restaurantName = restaurantName.reverse().toString().trim();
				restaurant.roomNo = roomNo.reverse().toString();
				
				System.out.println(restaurant.restaurantName);
				System.out.println(restaurant.roomNo);
				
				try {
					ArrayList<String[]> rInfo = simpleJDBC.getInstance().GetRestaurantOrders(restaurant);
					
					for (int i = 0; i < rInfo.size(); i++) {
						for (int j = 0; j < rInfo.get(i).length; j++) {
							
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
