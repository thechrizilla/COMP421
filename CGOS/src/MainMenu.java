import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton CreateGroceryButton = new JButton("Create Grocery");
		CreateGroceryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGrocery CG = new CreateGrocery();
				CG.setVisible(true);
				dispose();
			}
		});
		CreateGroceryButton.setBounds(162, 57, 117, 29);
		contentPane.add(CreateGroceryButton);
		
		JButton OrderNewIngredientButton = new JButton("Order New Ingredient");
		OrderNewIngredientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderNewIngredient ONI = new OrderNewIngredient();
				ONI.setVisible(true);
				dispose();
			}
		});
		OrderNewIngredientButton.setBounds(134, 21, 171, 29);
		contentPane.add(OrderNewIngredientButton);
		
		JButton ViewModifyBudgets = new JButton("View/Modify Restaurant Budgets");
		ViewModifyBudgets.setBounds(101, 95, 240, 29);
		contentPane.add(ViewModifyBudgets);
		
		JButton AutoAdjustBudgets = new JButton("Auto Adjust Budgets");
		AutoAdjustBudgets.setBounds(134, 132, 160, 29);
		contentPane.add(AutoAdjustBudgets);
		
		JButton ViewModifyRestaurantOrdersButton = new JButton("View/Modify Restaurant Orders");
		ViewModifyRestaurantOrdersButton.setBounds(101, 173, 229, 29);
		contentPane.add(ViewModifyRestaurantOrdersButton);
		
		JButton QuitButton = new JButton("Quit");
		QuitButton.setBounds(162, 214, 117, 29);
		contentPane.add(QuitButton);
	}

}
