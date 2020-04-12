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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ship Name:");
		lblNewLabel.setBounds(129, 66, 71, 16);
		contentPane.add(lblNewLabel);
		
		JLabel restaurantNameLabel = new JLabel("Restaurant Name:");
		restaurantNameLabel.setBounds(89, 94, 111, 16);
		contentPane.add(restaurantNameLabel);
		
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
		RestaurantComboBox.setEnabled(false);
		restaurantNameLabel.setEnabled(false);
		
		ShipNameComboBox.addPopupMenuListener(new PopupMenuListener()
		{
		    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
		    {
		    	 ShipNameComboBox.removeAllItems();

				try {
					for (Object item : simpleJDBC.getInstance().GetShipNames()) {
						ShipNameComboBox.addItem(item);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }

		    public void popupMenuCanceled(PopupMenuEvent e) {}
		    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
		});
		
		ShipNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object o = ShipNameComboBox.getSelectedItem();
					if (o == null) return;
					ArrayList<String> restaurants = simpleJDBC.getInstance().GetRestaurants(o.toString());
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
		
		
		IngredientTypeTextField = new JTextField();
		IngredientTypeTextField.setBounds(212, 117, 130, 26);
		contentPane.add(IngredientTypeTextField);
		IngredientTypeTextField.setColumns(10);
		
		WeightTextField = new JTextField();
		WeightTextField.setBounds(212, 145, 130, 26);
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
		OrderButton.setBounds(169, 219, 117, 29);
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
		BackButton.setBounds(6, 243, 117, 29);
		contentPane.add(BackButton);
	}
}
