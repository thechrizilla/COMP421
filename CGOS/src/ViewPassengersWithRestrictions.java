import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ViewPassengersWithRestrictions extends JFrame {

	private JPanel contentPane;
	private String shipName;
	private DefaultListModel model;
	private ArrayList<String[]> passengerInfos;

	public ViewPassengersWithRestrictions() {
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
		
		JLabel restrictionLabel = new JLabel("Restriction Type:");
		restrictionLabel.setBounds(78, 77, 111, 16);
		contentPane.add(restrictionLabel);
		
		JComboBox restrictionComboBox = new JComboBox();
		restrictionComboBox.setBounds(188, 73, 170, 27);
		contentPane.add(restrictionComboBox);
		
		model = new DefaultListModel();
		JList list_orders = new JList(model);
		list_orders.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list_orders.setLayoutOrientation(JList.VERTICAL);
		list_orders.setVisibleRowCount(-1);
		
		JScrollPane budgetInfoListScroller = new JScrollPane(list_orders);
		budgetInfoListScroller.setBounds(46, 122, 347, 95);
		contentPane.add(budgetInfoListScroller);
		
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
					ArrayList<String> restrictionList = simpleJDBC.getInstance().GetListOfDietaryRestrictions(shipName);
					restrictionComboBox.removeAllItems();
					for (String r : restrictionList) {
						restrictionComboBox.addItem(r);
					}
					restrictionComboBox.setEnabled(true);
//					restaurantNameLabel.setEnabled(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		restrictionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IngredientsComboBox.removeAllItems();
//
//				IngredientsComboBox.setEnabled(true);
//				ingredientLabel.setEnabled(true);
//
//				restaurant.shipname = ShipNameComboBox.getSelectedItem().toString();

				Object o = restrictionComboBox.getSelectedItem();
				if (o == null)
					return;
				String restrictionType = o.toString();
				
				model.clear();
				
				try {
					// passengerid, dob, firstname, lastname, gender, ethnicity
					passengerInfos = simpleJDBC.getInstance().GetPassengersWithDietaryRestriction(restrictionType);
					String[] toDisplay = new String[passengerInfos.size()-1];
					for (int i = 1; i < passengerInfos.size(); ++i) {
						String[] passenger = passengerInfos.get(i);
						String row = passenger[0] + ", " + passenger[1] + ", " + passenger[2] + ", " + passenger[3] + ", " + passenger[4] + ", " + passenger[5];
						toDisplay[i-1] = row;
					}
					
					System.out.println(Arrays.toString(toDisplay));
					
					// Refresh the DefaultListModel
					model.clear();
					for (int i = 0; i < toDisplay.length; ++i) {
						model.add(i, toDisplay[i]);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error getting passenger info for " + shipName + " in ViewPassengersWithRestrctions");
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
		
		JList list = new JList();
		list.setBounds(10, 10, 100, 100);
		contentPane.add(list);
	}
	
	

}
