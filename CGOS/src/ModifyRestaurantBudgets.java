import java.awt.BorderLayout;
import java.awt.EventQueue;
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


	public ModifyRestaurantBudgets() {
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
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(157, 227, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel NewBudgetLabel = new JLabel("New Budget:");
		NewBudgetLabel.setBounds(125, 121, 78, 16);
		contentPane.add(NewBudgetLabel);
		
		NewBudgetTextField = new JTextField();
		NewBudgetTextField.setBounds(203, 116, 130, 26);
		contentPane.add(NewBudgetTextField);
		NewBudgetTextField.setColumns(10);
	}
}
