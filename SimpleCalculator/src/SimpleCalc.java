import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SimpleCalc {

	private JFrame frmCalculator;
	private JTextField edtNum1;
	private JTextField edtNum2;
	private JLabel lblHolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleCalc window = new SimpleCalc();
					window.frmCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimpleCalc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalculator = new JFrame();
		frmCalculator.setTitle("(A very bad) Calculator");
		frmCalculator.setBounds(100, 100, 450, 245);
		frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 103, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{51, 0, 0, 0, 0, 49, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmCalculator.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel("Enter 2 numbers");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 3;
		gbc_lblTitle.gridy = 0;
		frmCalculator.getContentPane().add(lblTitle, gbc_lblTitle);
		
		edtNum1 = new JTextField();
		GridBagConstraints gbc_edtNum1 = new GridBagConstraints();
		gbc_edtNum1.insets = new Insets(0, 0, 5, 5);
		gbc_edtNum1.fill = GridBagConstraints.HORIZONTAL;
		gbc_edtNum1.gridx = 1;
		gbc_edtNum1.gridy = 1;
		frmCalculator.getContentPane().add(edtNum1, gbc_edtNum1);
		edtNum1.setColumns(10);
		
		JComboBox cmbSign = new JComboBox();
		cmbSign.setModel(new DefaultComboBoxModel(new String[] {"Sign", "+", "-", "*", "/"}));
		GridBagConstraints gbc_cmbSign = new GridBagConstraints();
		gbc_cmbSign.insets = new Insets(0, 0, 5, 5);
		gbc_cmbSign.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSign.gridx = 3;
		gbc_cmbSign.gridy = 1;
		frmCalculator.getContentPane().add(cmbSign, gbc_cmbSign);
		
		edtNum2 = new JTextField();
		GridBagConstraints gbc_edtNum2 = new GridBagConstraints();
		gbc_edtNum2.insets = new Insets(0, 0, 5, 5);
		gbc_edtNum2.fill = GridBagConstraints.HORIZONTAL;
		gbc_edtNum2.gridx = 5;
		gbc_edtNum2.gridy = 1;
		frmCalculator.getContentPane().add(edtNum2, gbc_edtNum2);
		edtNum2.setColumns(10);
		
		JButton btnEquals = new JButton("=");
		btnEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// btnEquals
				// Variables
				String sOutput;
				Float rNum1, rNum2, rAnswer = null;
				int iSignSelect;
				boolean bChecks;
				
				// Validation
				bChecks = true;
				
				// V - Checks if there is input
				if (edtNum1.getText().isBlank() || edtNum2.getText().isBlank()) {
					JOptionPane.showMessageDialog(edtNum1, "Please make sure that there is input", "Error", 0);
					bChecks = false; // works
				};
				
				// V - Checks if input is a number
				
				try {
					rNum1 = Float.parseFloat(edtNum1.getText());
					rNum2 = Float.parseFloat(edtNum2.getText());
				} catch(Exception e1) {
					bChecks = false; //works
					JOptionPane.showMessageDialog(null, "One of the values are not an integer of real value", "Error", 0);
				}
			
				
				// V - Checks if they have selected a mathematics sign
				switch (cmbSign.getSelectedIndex()){
				case -1: bChecks = false; 
				case  0: bChecks = false;
				default: iSignSelect = cmbSign.getSelectedIndex();
				}
				
				if (bChecks == false) {
					JOptionPane.showMessageDialog(null, "An input in incorrect, please ensure you have numbers and selected a symbol", "Error", 0);
				}
				
				// Validation All DONE!!  ---------------------------------------------------
				// Input because of the Try .. catch
				rNum1 = Float.parseFloat(edtNum1.getText());
				rNum2 = Float.parseFloat(edtNum2.getText());
				
				// V - only after the bChecks = false, since all input can be correct
				if ((cmbSign.getSelectedIndex() == 4) && (rNum2 == 0)) {
					bChecks = false;
					JOptionPane.showMessageDialog(null, "Cannot divide a number 0", "Error", 0);
				}
				
				// Processing
				if (bChecks == true) {
					switch (iSignSelect) {
						case 1: rAnswer = rNum1 + rNum2; break; // Lesson learned, breaks are VERY important
						case 2: rAnswer = rNum1 - rNum2; break; // otherwise they give you answers such as:
						case 3: rAnswer = rNum1 * rNum2; break; // 0.5, 0.9191919191 after the first calculation
						case 4: rAnswer = rNum1 / rNum2; break;
					}
					sOutput = String.format("%.2f",rAnswer);
				} else {
					sOutput = "Something went wrong!";
				}
				lblHolder.setText(sOutput);
				
			}
		});
		GridBagConstraints gbc_btnEquals = new GridBagConstraints();
		gbc_btnEquals.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEquals.insets = new Insets(0, 0, 5, 5);
		gbc_btnEquals.gridx = 3;
		gbc_btnEquals.gridy = 3;
		frmCalculator.getContentPane().add(btnEquals, gbc_btnEquals);
		
		lblHolder = new JLabel("New label");
		GridBagConstraints gbc_lblHolder = new GridBagConstraints();
		gbc_lblHolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblHolder.gridx = 3;
		gbc_lblHolder.gridy = 5;
		frmCalculator.getContentPane().add(lblHolder, gbc_lblHolder);
	}
	public String getLblHolderText() {
		return lblHolder.getText();
	}
	public void setLblHolderText(String text) {
		lblHolder.setText(text);
	}
}
