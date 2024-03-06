package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textHeight;
	private JTextField textWidth;
	public boolean confirmation;
    public boolean innerConfirmation;
    public boolean borderConfirmation;
    private JTextField txtX;
    private JTextField txtY;
    private JButton btnIcolor,  btnBcolor;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setModal(true);
		setBounds(100, 100, 492, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 98, 110, -39, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Rectangle");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblNewLabel.setForeground(new Color(147, 112, 219));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 3;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblX = new JLabel("Cordinates X:");
			lblX.setForeground(new Color(147, 112, 219));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 1;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 5);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 3;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			{
				JLabel lblY = new JLabel("Coordinates Y:");
				lblY.setForeground(new Color(147, 112, 219));
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 1;
				gbc_lblY.gridy = 2;
				contentPanel.add(lblY, gbc_lblY);
			}
			{
				txtY = new JTextField();
				txtY.setText("");
				GridBagConstraints gbc_txtY = new GridBagConstraints();
				gbc_txtY.insets = new Insets(0, 0, 5, 5);
				gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtY.gridx = 3;
				gbc_txtY.gridy = 2;
				contentPanel.add(txtY, gbc_txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblWidth = new JLabel("Width:");
				lblWidth.setForeground(new Color(147, 112, 219));
				GridBagConstraints gbc_lblWidth = new GridBagConstraints();
				gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
				gbc_lblWidth.gridx = 1;
				gbc_lblWidth.gridy = 4;
				contentPanel.add(lblWidth, gbc_lblWidth);
			}
			{
				textWidth = new JTextField();
				GridBagConstraints gbc_textWidth = new GridBagConstraints();
				gbc_textWidth.insets = new Insets(0, 0, 5, 5);
				gbc_textWidth.fill = GridBagConstraints.HORIZONTAL;
				gbc_textWidth.gridx = 3;
				gbc_textWidth.gridy = 4;
				contentPanel.add(textWidth, gbc_textWidth);
				textWidth.setColumns(10);
			}
			{
				JLabel lblHeight = new JLabel("Height:");
				lblHeight.setForeground(new Color(147, 112, 219));
				GridBagConstraints gbc_lblHeight = new GridBagConstraints();
				gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
				gbc_lblHeight.gridx = 1;
				gbc_lblHeight.gridy = 5;
				contentPanel.add(lblHeight, gbc_lblHeight);
			}
			{
				textHeight = new JTextField();
				GridBagConstraints gbc_textHeight = new GridBagConstraints();
				gbc_textHeight.insets = new Insets(0, 0, 5, 5);
				gbc_textHeight.fill = GridBagConstraints.HORIZONTAL;
				gbc_textHeight.gridx = 3;
				gbc_textHeight.gridy = 5;
				contentPanel.add(textHeight, gbc_textHeight);
				textHeight.setColumns(10);
			}
		}
	    btnIcolor = new JButton("Inner color");
		btnIcolor.setForeground(new Color(0, 0, 0));
		btnIcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerConfirmation=true;
				btnIcolor.setBackground(JColorChooser.showDialog(null, "Choose  inner color ", Color.RED));
			}
		});
		GridBagConstraints gbc_btnIcolor = new GridBagConstraints();
		gbc_btnIcolor.insets = new Insets(0, 0, 5, 5);
		gbc_btnIcolor.gridx = 1;
		gbc_btnIcolor.gridy = 8;
		contentPanel.add(btnIcolor, gbc_btnIcolor);
	    btnBcolor = new JButton("Border color");
		btnBcolor.setForeground(new Color(255, 255, 255));
		btnBcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderConfirmation=true;
				btnBcolor.setBackground(JColorChooser.showDialog(null, "Choose  border color ", Color.RED));
			}
		});
		GridBagConstraints gbc_btnBcolor = new GridBagConstraints();
		gbc_btnBcolor.insets = new Insets(0, 0, 5, 5);
		gbc_btnBcolor.gridx = 3;
		gbc_btnBcolor.gridy = 8;
		contentPanel.add(btnBcolor, gbc_btnBcolor);
		{
			JPanel buttonPane = new JPanel();
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
	
	public JButton getBtnIcolor() {
		return btnIcolor;
	}

	public void setBtnIcolor(JButton btnIcolor) {
		this.btnIcolor = btnIcolor;
	}

	public JButton getBtnBcolor() {
		return btnBcolor;
	}

	public void setBtnBcolor(JButton btnBcolor) {
		this.btnBcolor = btnBcolor;
	}

	public JTextField getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(JTextField textHeight) {
		this.textHeight = textHeight;
	}

	public JTextField getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(JTextField textWidth) {
		this.textWidth = textWidth;
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

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	

}
