import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
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
	 * @throws SQLException 
	 */
	public MainMenu() throws SQLException {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		simpleJDBC.getInstance();
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
				OrderNewIngredient ONI;
				try {
					ONI = new OrderNewIngredient();
					ONI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		QuitButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent evt){
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
	                    dispose();
	                }else{
	                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	                }
	            }
		});
	}

}
