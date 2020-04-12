import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ModifyRestaurantOrders extends JFrame {

	private JPanel contentPane;
	private JTextField TypeTextField;
	private JTextField WeightTextField;

	public ModifyRestaurantOrders() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NewTypeLabel = new JLabel("Type:");
		NewTypeLabel.setBounds(159, 95, 34, 16);
		contentPane.add(NewTypeLabel);
		
		TypeTextField = new JTextField();
		TypeTextField.setBounds(193, 90, 130, 26);
		contentPane.add(TypeTextField);
		TypeTextField.setColumns(10);
		
		JLabel NewWeightLabel = new JLabel("Weight:");
		NewWeightLabel.setBounds(146, 123, 47, 16);
		contentPane.add(NewWeightLabel);
		
		WeightTextField = new JTextField();
		WeightTextField.setBounds(193, 118, 130, 26);
		contentPane.add(WeightTextField);
		WeightTextField.setColumns(10);
		
		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.setBounds(159, 232, 117, 29);
		contentPane.add(ConfirmButton);
		
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
