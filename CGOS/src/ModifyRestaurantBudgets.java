import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ModifyRestaurantBudgets extends JFrame {

	private JPanel contentPane;
	private JTextField NewBudgetTextField;

	public ModifyRestaurantBudgets(String[] restaurantInfo, String shipName) {
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                int x = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to quit?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);

                if (x == JOptionPane.YES_OPTION) {
                	try {
						JDBCUser.getInstance().Close();
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
		
//		String querySQL = "SELECT roomnumber, restaurantname, capacity, restaurantbudget, usedBudget";
		String rName = restaurantInfo[1];
		String rNo = restaurantInfo[0];
		String restaurantName = rName + " (" + rNo + ")";
		String budgetValues = "$" + restaurantInfo[4] + "/" + restaurantInfo[3];
		
		JLabel ShipNameLabel = new JLabel("Ship: " + shipName);
		ShipNameLabel.setBounds(289, 142, 250, 16);
		contentPane.add(ShipNameLabel);
		
		JLabel RestaurantNameLabel = new JLabel("Restaurant: " + restaurantName);
		RestaurantNameLabel.setBounds(289, 193, 250, 16);
		contentPane.add(RestaurantNameLabel);

		JLabel budgetLabel = new JLabel("Budget: " + budgetValues);
		budgetLabel.setBounds(289, 238, 250, 16);
		contentPane.add(budgetLabel);
		
		JLabel NewBudgetLabel = new JLabel("New Budget:");
		NewBudgetLabel.setBounds(289, 276, 78, 16);
		contentPane.add(NewBudgetLabel);
		
		NewBudgetTextField = new JTextField();
		NewBudgetTextField.setBounds(369, 271, 157, 26);
		contentPane.add(NewBudgetTextField);
		NewBudgetTextField.setColumns(10);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(369, 365, 117, 29);
		contentPane.add(confirmBtn);
		
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewModifyRestaurantBudgets VMRB;
				try {
					VMRB = new ViewModifyRestaurantBudgets();
					VMRB.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		BackButton.setBounds(41, 414, 117, 29);
		contentPane.add(BackButton);
		
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Finish here
				BudgetUpdateInfo newInfo = new BudgetUpdateInfo();
				newInfo.newBudget = NewBudgetTextField.getText();
				newInfo.roomNo = rNo;
				newInfo.shipname = shipName;
				
				try {
					float f = Float.parseFloat(newInfo.newBudget);
					String currentValue = budgetValues;
					
					currentValue = currentValue.substring(currentValue.indexOf("$") + 1);
					currentValue = currentValue.substring(0, currentValue.indexOf("/"));
					
					if (f < Float.parseFloat(currentValue)) {
						JOptionPane.showMessageDialog(null, "New budget cannot be less than " + currentValue + "!");
						return;	
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Budget must be a number!");
					return;	
				}
				
				try {
					JDBCUser.getInstance().UpdateBudget(newInfo);
					Success S = new Success();
					S.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					System.out.println("Error updating budget info for " + rName + ", " + rNo);
					e1.printStackTrace();
					return;
				}
				
				System.out.println("Success");
				
//				int index = list_restaurants.getSelectedIndex();
//				String[] selectedRestaurant = budgetInfos.get(index);
//				System.out.println("Selected index: " + index);
//				
//				ModifyRestaurantBudgets modifyBudgetPopup = new ModifyRestaurantBudgets(selectedRestaurant, selectedShip);
//				modifyBudgetPopup.setVisible(true);
//				dispose();
			}
		});
	}
}
