import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewModifyRestaurantBudgets extends JFrame {

	private JPanel contentPane;


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
		
		JComboBox ShipNameComboxBox = new JComboBox();
		ShipNameComboxBox.setBounds(222, 14, 134, 27);
		contentPane.add(ShipNameComboxBox);
		
		JLabel ShipNameLabel = new JLabel("Ship Name:");
		ShipNameLabel.setBounds(141, 18, 71, 16);
		contentPane.add(ShipNameLabel);
		
		JLabel RestaurantNameLabel = new JLabel("Restaurant Name and Budget:");
		RestaurantNameLabel.setBounds(126, 46, 185, 16);
		contentPane.add(RestaurantNameLabel);
		
		JButton SelectButton = new JButton("Select");
		SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SelectButton.setBounds(160, 226, 117, 29);
		contentPane.add(SelectButton);
		
		JList list = new JList();
		list.setBounds(58, 221, 329, -137);
		contentPane.add(list);
		
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
