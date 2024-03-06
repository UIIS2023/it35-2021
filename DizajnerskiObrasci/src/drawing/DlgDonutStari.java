package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class DlgDonutStari extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInnerRadius;
	private JTextField txtOuterRadius;
	public boolean confirmation;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonutStari dialog = new DlgDonutStari();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonutStari() {
		setTitle("Draw donut");
		setBounds(100, 100, 450, 260);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			lblInnerRadius.setForeground(new Color(128, 0, 128));
			lblInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 1;
			gbc_lblInnerRadius.gridy = 1;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.gridx = 4;
			gbc_txtInnerRadius.gridy = 1;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			JLabel lblOuterRadius = new JLabel("Outer radius:");
			lblOuterRadius.setForeground(new Color(139, 0, 139));
			lblOuterRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblOuterRadius = new GridBagConstraints();
			gbc_lblOuterRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblOuterRadius.gridx = 1;
			gbc_lblOuterRadius.gridy = 3;
			contentPanel.add(lblOuterRadius, gbc_lblOuterRadius);
		}
		{
			txtOuterRadius = new JTextField();
			GridBagConstraints gbc_txtOuterRadius = new GridBagConstraints();
			gbc_txtOuterRadius.gridx = 4;
			gbc_txtOuterRadius.gridy = 3;
			contentPanel.add(txtOuterRadius, gbc_txtOuterRadius);
			txtOuterRadius.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(221, 160, 221));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int innerRadius;
						int outerRadius;
						try
						{
							innerRadius= Integer.parseInt(getTxtInnerRadius().getText());
							outerRadius= Integer.parseInt(getTxtOuterRadius().getText());
						}
						catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, "Both radius must be number!", "Error", JOptionPane.ERROR_MESSAGE, null);
							confirmation=false;
							return;
						}
						if(innerRadius<=0 || outerRadius<=0)
						{
							JOptionPane.showMessageDialog(null, "Both radius must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE, null);
							confirmation=false;
						}
						else if(innerRadius>= outerRadius)
						{
							JOptionPane.showMessageDialog(null, "Inner radius must be smaller then outer radius!", "Error", JOptionPane.ERROR_MESSAGE, null);
							confirmation=false;
						}else{
						
						confirmation=true;
						dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(221, 160, 221));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public JTextField getTxtOuterRadius() {
		return txtOuterRadius;
	}

	public void setTxtOuterRadius(JTextField txtRadius) {
		this.txtOuterRadius = txtRadius;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}


}
