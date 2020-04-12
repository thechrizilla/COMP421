import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ViewModifyRestaurantBudgets extends JFrame {

	private JPanel contentPane;
	private DefaultListModel model;
	private ArrayList<String[]> budgetInfos;
	private String selectedShip;

	public ViewModifyRestaurantBudgets() {
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
		ShipNameLabel.setBounds(141, 18, 71, 16);
		contentPane.add(ShipNameLabel);

		JLabel RestaurantNameLabel = new JLabel("Restaurant Name and Budget:");
		RestaurantNameLabel.setBounds(126, 46, 185, 16);
		contentPane.add(RestaurantNameLabel);

		model = new DefaultListModel();
		JList list_restaurants = new JList(model);
		list_restaurants.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list_restaurants.setLayoutOrientation(JList.VERTICAL);
		list_restaurants.setVisibleRowCount(-1);
		
		JScrollPane budgetInfoListScroller = new JScrollPane(list_restaurants);
		budgetInfoListScroller.setBounds(65, 75, 300, 125);
		contentPane.add(budgetInfoListScroller);

		JComboBox ShipNameComboBox = new JComboBox();
		ShipNameComboBox.setBounds(222, 14, 134, 27);
		contentPane.add(ShipNameComboBox);
		
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
					model.clear();
					
					Object o = ShipNameComboBox.getSelectedItem();
					if (o == null) return;
					selectedShip = o.toString();
					budgetInfos = simpleJDBC.getInstance().GetBudgetInfo(o.toString());
					
					ArrayList<String> toDisplay = new ArrayList<String>();
					for (int i = 1; i < budgetInfos.size(); ++i) {
						String row = budgetInfos.get(i)[1] + " (" + budgetInfos.get(i)[0] + ") - $" + budgetInfos.get(i)[4] + "/" + budgetInfos.get(i)[3];
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
					e1.printStackTrace();
				}
			}
		});
		
		JButton SelectButton = new JButton("Modify Budget");
		SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_restaurants.getSelectedIndex();
				
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Please select a restaurant.");
					return;	
				}
				
				String[] selectedRestaurant = budgetInfos.get(index+1);
				System.out.println("Selected index: " + index);
				
				ModifyRestaurantBudgets modifyBudgetPopup = new ModifyRestaurantBudgets(selectedRestaurant, selectedShip);
				modifyBudgetPopup.setVisible(true);
				dispose();
			}
		});
		
		SelectButton.setBounds(160, 215, 117, 29);
		contentPane.add(SelectButton);


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
		BackButton.setBounds(6, 243, 117, 29);
		contentPane.add(BackButton);




		}
	}
