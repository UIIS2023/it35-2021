package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgHexagon extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textRadius;
	public boolean confirmation;
    public boolean innerConfirmation;
    public boolean borderConfirmation;
    private JTextField textX;
    private JTextField textY;
    private JButton btnInnerColor, btnBorderColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		getContentPane().setForeground(new Color(255, 255, 255));
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{35, 87, 76, 95, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 35, 14, 0, 27, 14, 35, 23, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblHexagon = new JLabel("Hexagon");
			lblHexagon.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblHexagon.setForeground(new Color(153, 51, 153));
			GridBagConstraints gbc_lblHexagon = new GridBagConstraints();
			gbc_lblHexagon.insets = new Insets(0, 0, 5, 5);
			gbc_lblHexagon.gridx = 2;
			gbc_lblHexagon.gridy = 1;
			contentPanel.add(lblHexagon, gbc_lblHexagon);
		}
		{
			JLabel lblX = new JLabel("Coordinates  X:");
			lblX.setForeground(new Color(153, 51, 153));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 1;
			gbc_lblX.gridy = 2;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			textX = new JTextField();
			GridBagConstraints gbc_textX = new GridBagConstraints();
			gbc_textX.insets = new Insets(0, 0, 5, 0);
			gbc_textX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textX.gridx = 3;
			gbc_textX.gridy = 2;
			contentPanel.add(textX, gbc_textX);
			textX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Coordinates Y:");
			lblY.setForeground(new Color(153, 51, 153));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 1;
			gbc_lblY.gridy = 3;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			textY = new JTextField();
			GridBagConstraints gbc_textY = new GridBagConstraints();
			gbc_textY.insets = new Insets(0, 0, 5, 0);
			gbc_textY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textY.gridx = 3;
			gbc_textY.gridy = 3;
			contentPanel.add(textY, gbc_textY);
			textY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setForeground(new Color(153, 51, 153));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			textRadius = new JTextField();
			GridBagConstraints gbc_textRadius = new GridBagConstraints();
			gbc_textRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textRadius.gridx = 3;
			gbc_textRadius.gridy = 5;
			contentPanel.add(textRadius, gbc_textRadius);
			textRadius.setColumns(10);
		}
		{
		    btnInnerColor = new JButton("Inner Color");
			btnInnerColor.setForeground(new Color(0, 0, 0));
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerConfirmation=true;
					btnInnerColor.setBackground(JColorChooser.showDialog(null, "Choose  inner color ", Color.RED));
				}
			});
			GridBagConstraints gbc_btnIcolor = new GridBagConstraints();
			gbc_btnIcolor.insets = new Insets(0, 0, 5, 5);
			gbc_btnIcolor.gridx = 1;
			gbc_btnIcolor.gridy = 7;
			contentPanel.add(btnInnerColor, gbc_btnIcolor);
		}
		{
			btnBorderColor = new JButton("Border Color");
			btnBorderColor.setForeground(new Color(255, 255, 255));
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderConfirmation=true;
					btnBorderColor.setBackground(JColorChooser.showDialog(null, "Choose  border color ", Color.RED));
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnBorderColor.gridx = 3;
			gbc_btnBorderColor.gridy = 7;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(204, 255, 204));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmation=true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 153, 153));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	

	public JTextField getTextRadius() {
		return textRadius;
	}

	public void setTextRadius(JTextField textRadius) {
		this.textRadius = textRadius;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isInnerConfirmation() {
		return innerConfirmation;
	}

	public void setInnerConfirmation(boolean innerConfirmation) {
		this.innerConfirmation = innerConfirmation;
	}

	public boolean isBorderConfirmation() {
		return borderConfirmation;
	}

	public void setBorderConfirmation(boolean borderConfirmation) {
		this.borderConfirmation = borderConfirmation;
	}

	public JTextField getTextX() {
		return textX;
	}

	public void setTextX(JTextField textX) {
		this.textX = textX;
	}

	public JTextField getTextY() {
		return textY;
	}

	public void setTextY(JTextField textY) {
		this.textY = textY;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnIcolor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}
	


}
