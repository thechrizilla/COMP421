import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ModifyRestaurantOrders extends JFrame {

	private JPanel contentPane;
	private JTextField typeInput;
	private JTextField weightInput;

	public ModifyRestaurantOrders(String[] orderInfo) {
		// orderid, shipname, rname, rno, type, numgro, orderweight, totalweight
		String orderid = orderInfo[0];
		String type = orderInfo[4];
		String curWeight = orderInfo[6];
		String totalWeight = orderInfo[7];
		
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
		
		JLabel orderLabel = new JLabel("Order ID: " + orderid);
		orderLabel.setBounds(315, 104, 200, 16);
		contentPane.add(orderLabel);
		
		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setBounds(272, 143, 34, 16);
		contentPane.add(typeLabel);
		
		typeInput = new JTextField();
		typeInput.setBounds(315, 138, 200, 26);
		contentPane.add(typeInput);
		typeInput.setColumns(10);
		typeInput.setText(type);
		
		JLabel weightLabel = new JLabel("Weight:");
		weightLabel.setBounds(261, 181, 47, 16);
		contentPane.add(weightLabel);
		
		JLabel totalWeightLabel = new JLabel("Total Weight: " + totalWeight);
		totalWeightLabel.setBounds(315, 214, 200, 16);
		contentPane.add(totalWeightLabel);
		
		weightInput = new JTextField();
		weightInput.setBounds(315, 176, 200, 26);
		contentPane.add(weightInput);
		weightInput.setColumns(10);
		weightInput.setText(curWeight);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(363, 363, 117, 29);
		contentPane.add(confirmBtn);
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Finish here
				IngredientUpdateInfo updateInfo = new IngredientUpdateInfo();
				updateInfo.orderid = orderid;
				updateInfo.type = typeInput.getText();
				updateInfo.weight = weightInput.getText();
						
				try {
					float f = Float.parseFloat(updateInfo.weight);
					if (f < Float.parseFloat(totalWeight)) {
						JOptionPane.showMessageDialog(null, "Modified weight cannot be less than " + totalWeight + "!");
						return;
					}
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "Weight must be a number!");
					return;	
				}
				
				try {
					simpleJDBC.getInstance().UpdateIngredient(updateInfo);
					Success S = new Success();
					S.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					System.out.println("Error updating ingredient info for " + orderid);
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
		


		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewModifyRestaurantOrders VMRO;
				try {
					VMRO = new ViewModifyRestaurantOrders();
					VMRO.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		Back.setBounds(37, 414, 117, 29);
		contentPane.add(Back);
	}

}
