import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewModifyRestaurantOrders extends JFrame {

	private JPanel contentPane;
	
	public ViewModifyRestaurantOrders() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ShipNameLabel = new JLabel("Ship Name:");
		ShipNameLabel.setBounds(118, 38, 71, 16);
		contentPane.add(ShipNameLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(188, 34, 110, 27);
		contentPane.add(comboBox);
		
		JLabel RestaurantLabel = new JLabel("Restaurant Name:");
		RestaurantLabel.setBounds(78, 77, 111, 16);
		contentPane.add(RestaurantLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(188, 73, 110, 27);
		contentPane.add(comboBox_1);
		
		JList list = new JList();
		list.setBounds(46, 122, 347, 95);
		contentPane.add(list);
		
		JButton SelectButton = new JButton("Select");
		SelectButton.setBounds(181, 243, 117, 29);
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
		BackButton.setBounds(6, 243, 117, 29);
		contentPane.add(BackButton);
	}

}
