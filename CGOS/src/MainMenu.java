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
import javax.swing.JLabel;
import java.awt.Font;

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
		
		setBounds(100, 100, 850, 500);
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
		CreateGroceryButton.setBounds(366, 255, 117, 29);
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
		OrderNewIngredientButton.setBounds(338, 214, 171, 29);
		contentPane.add(OrderNewIngredientButton);
		
		JButton ViewModifyBudgets = new JButton("View/Modify Restaurant Budgets");
		ViewModifyBudgets.addActionListener(new ActionListener() {
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
		ViewModifyBudgets.setBounds(302, 296, 240, 29);
		contentPane.add(ViewModifyBudgets);
		
		JButton AutoAdjustBudgets = new JButton("View/Modify Restaurant Orders");
		AutoAdjustBudgets.addActionListener(new ActionListener() {
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
		AutoAdjustBudgets.setBounds(302, 337, 240, 29);
		contentPane.add(AutoAdjustBudgets);
		
		JButton ViewModifyRestaurantOrdersButton = new JButton("View Passengers With Dietary Restrictions");
		ViewModifyRestaurantOrdersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPassengersWithRestrictions VPWR;
				try {
					VPWR = new ViewPassengersWithRestrictions();
					VPWR.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		ViewModifyRestaurantOrdersButton.setBounds(276, 378, 296, 29);
		contentPane.add(ViewModifyRestaurantOrdersButton);
		
		JButton QuitButton = new JButton("Quit");
		QuitButton.setBounds(366, 419, 117, 29);
		contentPane.add(QuitButton);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>WELCOME to the CGOS Application! <br/> Please select one of the options below! <br/> If you want to quit the application, simply click the \"Quit\" button.</html>");
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
		lblNewLabel.setBounds(213, 41, 459, 91);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel.setVerticalAlignment(JLabel.CENTER);
		contentPane.add(lblNewLabel);
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
