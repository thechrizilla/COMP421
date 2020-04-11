import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateGroceryConfirm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGroceryConfirm frame = new CreateGroceryConfirm();
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
	public CreateGroceryConfirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton EnterButton = new JButton("Enter");
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGroceryDisplay CGD = new CreateGroceryDisplay();
				CGD.setVisible(true);
				dispose();
			}
		});
		EnterButton.setBounds(167, 223, 117, 29);
		contentPane.add(EnterButton);
		
		JLabel lblNewLabel = new JLabel("Ship Name:");
		lblNewLabel.setBounds(126, 74, 71, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox ShipNameComboBox = new JComboBox();
		ShipNameComboBox.setBounds(196, 70, 117, 27);
		contentPane.add(ShipNameComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Restaurant Name:");
		lblNewLabel_1.setBounds(86, 102, 111, 16);
		contentPane.add(lblNewLabel_1);
		
		JComboBox RestaurantNameComboBox = new JComboBox();
		RestaurantNameComboBox.setBounds(196, 98, 117, 27);
		contentPane.add(RestaurantNameComboBox);
	}
}
