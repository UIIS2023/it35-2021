package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

@SuppressWarnings("serial")
public class DlgModifyCircleStari extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Circle modifyCircle;
	private JTextField txtX;
	private JTextField txtY;
	private Color outlineColor;
	private Color fillColor;
	private JButton btnColor;
	private JButton btnFillColor;
	private JTextField txtRadius;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgModifyCircleStari dialog = new DlgModifyCircleStari();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyCircleStari() {
		setTitle("Modify circle");
		setModal(true);
		setBounds(100, 100, 343, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		JLabel lblCenterX = new JLabel("Center X coordinate:");
		lblCenterX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCenterX.setForeground(new Color(128, 0, 128));
		lblCenterX.setBackground(new Color(255, 255, 255));
		JLabel lblCenterY = new JLabel("Center Y coordinate:");
		lblCenterY.setForeground(new Color(128, 0, 128));
		lblCenterY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtX = new JTextField();
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setColumns(10);
		btnColor = new JButton("Outline color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outlineColor = JColorChooser.showDialog(null, "Choose the outline color", modifyCircle.getColor());
				if(outlineColor == null) outlineColor = modifyCircle.getColor();
				btnColor.setBackground(outlineColor);
			}
		});
		btnFillColor = new JButton("Fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillColor = JColorChooser.showDialog(null, "Choose the fill color", modifyCircle.getInnerColor());
				if(fillColor == null) fillColor = modifyCircle.getColor();
				btnFillColor.setBackground(fillColor);
			}
		});
		
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRadius.setForeground(new Color(128, 0, 128));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCenterX)
							.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCenterY)
								.addComponent(lblRadius)
								.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(221, 160, 221));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int radius;
						int x; 
						int y;
						try
						{
							 x = Integer.parseInt(txtX.getText());
							y = Integer.parseInt(txtY.getText());
							radius= Integer.parseInt(getTxtRadius().getText());
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(null, "All fields must be fill and number!!", "Error", JOptionPane.ERROR_MESSAGE, null);
							
							return;
						}
						if(radius<=0){
							JOptionPane.showMessageDialog(null, "Radius cannot be negative or 0", "Error", JOptionPane.ERROR_MESSAGE, null);
							
						}
						else
						{
							modifyCircle.getCenter().setX(x);
							modifyCircle.getCenter().setY(y);
							modifyCircle.setRadius(radius);
							modifyCircle.setColor(outlineColor);
							modifyCircle.setInnerColor(fillColor);
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
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void fillFields(Circle circleModifier) {
		this.modifyCircle = circleModifier;
		txtX.setText(String.valueOf(modifyCircle.getCenter().getX()));
		txtY.setText(String.valueOf(modifyCircle.getCenter().getY()));
		txtRadius.setText(String.valueOf(modifyCircle.getRadius()));
		btnColor.setBackground(modifyCircle.getColor());
		btnFillColor.setBackground(modifyCircle.getInnerColor());
		outlineColor = modifyCircle.getColor();
		fillColor = modifyCircle.getInnerColor();
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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}
	
	
	
	

}
