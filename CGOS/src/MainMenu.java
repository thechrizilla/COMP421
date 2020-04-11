import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainMenu {

		private JFrame frame;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainMenu window = new MainMenu();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public MainMenu() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JButton btnNewButton = new JButton("Ingredient Counter");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IngredientCounter IC = new IngredientCounter();
					IC.setVisible(true);
					frame.dispose();
					
				}
			});
			btnNewButton.setBounds(158, 91, 177, 29);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Create Grocery");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreateGrocery CG = new CreateGrocery();
					CG.setVisible(true);
					frame.dispose();
				}
			});
			btnNewButton_1.setBounds(6, 91, 152, 29);
			frame.getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Order New Ingredients");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderNewIngredient ONI = new OrderNewIngredient();
					ONI.setVisible(true);
					frame.dispose();
				}
			});
			btnNewButton_2.setBounds(57, 132, 196, 29);
			frame.getContentPane().add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("View and Modify Budgets");
			btnNewButton_3.setBounds(251, 132, 152, 29);
			frame.getContentPane().add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("Auto Adjust Budgets");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_4.setBounds(327, 91, 117, 29);
			frame.getContentPane().add(btnNewButton_4);
			
			JButton btnNewButton_5 = new JButton("Chris is the Best");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_5.setBounds(131, 184, 202, 29);
			frame.getContentPane().add(btnNewButton_5);
		}

	}
