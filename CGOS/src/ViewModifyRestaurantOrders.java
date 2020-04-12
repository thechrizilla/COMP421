import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ViewModifyRestaurantOrders extends JFrame {

	private JPanel contentPane;
	private DefaultListModel model;
	private String shipName;
	private ArrayList<String[]> orderInfos;
	
	public ViewModifyRestaurantOrders() {
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
		
		JLabel ShipNameLabel = new JLabel("Ship Name:");
		ShipNameLabel.setBounds(118, 38, 71, 16);
		contentPane.add(ShipNameLabel);
		
		JComboBox ShipNameComboBox = new JComboBox();
		ShipNameComboBox.setBounds(188, 34, 170, 27);
		contentPane.add(ShipNameComboBox);
		
		JLabel RestaurantLabel = new JLabel("Restaurant Name:");
		RestaurantLabel.setBounds(78, 77, 111, 16);
		contentPane.add(RestaurantLabel);
		
		JComboBox RestaurantComboBox = new JComboBox();
		RestaurantComboBox.setBounds(188, 73, 170, 27);
		contentPane.add(RestaurantComboBox);
		
		model = new DefaultListModel();
		JList list_orders = new JList(model);
		list_orders.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list_orders.setLayoutOrientation(JList.VERTICAL);
		list_orders.setVisibleRowCount(-1);
		
		JScrollPane budgetInfoListScroller = new JScrollPane(list_orders);
		budgetInfoListScroller.setBounds(46, 122, 347, 95);
		contentPane.add(budgetInfoListScroller);
	
		JButton modifyOrderBtn = new JButton("Modify Order");
		modifyOrderBtn.setBounds(181, 243, 117, 29);
		contentPane.add(modifyOrderBtn);
		modifyOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_orders.getSelectedIndex();
				
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Please select an order.");
					return;	
				}
				
				// orderid, shipname, rname, rno, type, numgro, orderweight, totalweight
				String[] selectedOrder = orderInfos.get(index+1);
				System.out.println("Selected index: " + index);
				ModifyRestaurantOrders modifyBudgetPopup = new ModifyRestaurantOrders(selectedOrder);
				modifyBudgetPopup.setVisible(true);
				dispose();
			}
		});
		
		ShipNameComboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
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

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
		});

		ShipNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object o = ShipNameComboBox.getSelectedItem();
					if (o == null)
						return;
					
					shipName = o.toString();
					ArrayList<String> restaurants = simpleJDBC.getInstance().GetRestaurants(shipName);
					RestaurantComboBox.removeAllItems();
					for (String r : restaurants) {
						RestaurantComboBox.addItem(r);
					}
					RestaurantComboBox.setEnabled(true);
//					restaurantNameLabel.setEnabled(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		RestaurantComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IngredientsComboBox.removeAllItems();
//
//				IngredientsComboBox.setEnabled(true);
//				ingredientLabel.setEnabled(true);
//
//				restaurant.shipname = ShipNameComboBox.getSelectedItem().toString();

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
				
				model.clear();
				RestaurantInfo rInfo = new RestaurantInfo();
				rInfo.restaurantName = restaurantName.reverse().toString().trim();
				rInfo.roomNo = roomNo.reverse().toString();
				rInfo.shipname = shipName;
				
				// orderid, shipname, rname, rno, type, numgro, orderweight, totalweight
				try {
					orderInfos = simpleJDBC.getInstance().GetRestaurantOrders(rInfo);
					ArrayList<String> toDisplay = new ArrayList<String>();
					for (int i = 1; i < orderInfos.size(); ++i) {
						String[] order = orderInfos.get(i);
						String row = order[0] + " - " + order[4] + " - " + order[7] + "/" + order[6];
						toDisplay.add(row);
					}
					
					Object[] listToDisplay = toDisplay.toArray();
					System.out.println(Arrays.toString(listToDisplay));
					
					// Refresh the DefaultListModel
					model.clear();
					for (int i = 0; i < listToDisplay.length; ++i) {
						model.add(i, listToDisplay[i]);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error getting restaurant orders in view/modify restaurant orders when selecting restaurant");
					e1.printStackTrace();
				}
			}
		});
		
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
