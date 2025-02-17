import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
	 
	public CreateGroceryConfirm(GroceryInstanceInfo g) throws SQLException {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirmation",
						JOptionPane.YES_NO_OPTION);

				if (x == JOptionPane.YES_OPTION) {
					try {
						JDBCUser.getInstance().Close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton CreateButton = new JButton("Create");
		
		CreateButton.setBounds(361, 399, 117, 29);
		contentPane.add(CreateButton);

		JLabel lblNewLabel = new JLabel("Ship Name:");
		lblNewLabel.setBounds(292, 154, 71, 16);
		contentPane.add(lblNewLabel);

		JComboBox ShipNameComboBox = new JComboBox(JDBCUser.getInstance().GetShipNames().toArray());
		ShipNameComboBox.setBounds(361, 150, 175, 27);
		contentPane.add(ShipNameComboBox);

		JLabel restaurantNameLabel = new JLabel("Restaurant Name:");
		restaurantNameLabel.setBounds(253, 182, 117, 16);
		contentPane.add(restaurantNameLabel);

		JComboBox RestaurantComboBox = new JComboBox();
		RestaurantComboBox.setBounds(361, 178, 175, 27);
		contentPane.add(RestaurantComboBox);
		RestaurantComboBox.setEnabled(false);
		restaurantNameLabel.setEnabled(false);

		JComboBox IngredientsComboBox = new JComboBox();
		IngredientsComboBox.setBounds(361, 209, 175, 29);
		contentPane.add(IngredientsComboBox);

		JLabel ingredientLabel = new JLabel("Ingredient Name:");
		ingredientLabel.setBounds(253, 215, 117, 14);
		contentPane.add(ingredientLabel);
		IngredientsComboBox.setEnabled(false);
		ingredientLabel.setEnabled(false);
		
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGrocery CG = new CreateGrocery();
				CG.setVisible(true);
				dispose();
			}
		});
		BackButton.setBounds(45, 414, 117, 29);
		contentPane.add(BackButton);

		ShipNameComboBox.setSelectedIndex(-1);
		ShipNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object o = ShipNameComboBox.getSelectedItem();
					if (o == null)
						return;
					ArrayList<String> restaurants = JDBCUser.getInstance().GetRestaurants(o.toString());
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

		RestaurantInfo restaurant = new RestaurantInfo();

		RestaurantComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngredientsComboBox.removeAllItems();

				IngredientsComboBox.setEnabled(true);
				ingredientLabel.setEnabled(true);

				restaurant.shipname = ShipNameComboBox.getSelectedItem().toString();

				Object o = RestaurantComboBox.getSelectedItem();
				if (o == null)
					return;
				String restaurantWithRoom = o.toString();

				StringBuilder roomNo = new StringBuilder();
				StringBuilder restaurantName = new StringBuilder();

				int idx = 0;

				for (int i = restaurantWithRoom.length() - 1; i != 0; i--) {
					if (restaurantWithRoom.charAt(i) == ')')
						continue;
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

			}
		});

		IngredientsComboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

				ArrayList<String[]> rInfo;
				try {
					IngredientsComboBox.removeAllItems();
					rInfo = JDBCUser.getInstance().GetRestaurantOrders(restaurant);
					for (int i = 1; i < rInfo.size(); i++) {
						IngredientsComboBox.insertItemAt(rInfo.get(i)[4] + " (" + rInfo.get(i)[0] + ")", 0);
					}
					IngredientsComboBox.setSelectedIndex(-1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		CreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ingredWithID = IngredientsComboBox.getSelectedItem().toString();
				StringBuilder orderID = new StringBuilder();
				
				for (int i = ingredWithID.length() - 1; i != 0; i--) {
					if (ingredWithID.charAt(i) == ')') {
						continue;
					}
					if (ingredWithID.charAt(i) == '(') {
						break;
					}
					orderID.append(ingredWithID.charAt(i));
				}
				
				g.orderID = orderID.reverse().toString();
				
				try {
					JDBCUser.getInstance().CreateGrocery(g);
					Success S = new Success();
					S.setVisible(true);
					dispose();
				} catch (IllegalArgumentException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Restaurant does not have enough budget for this grocery!");
					return;	
				}
			}
		});

	}
}
