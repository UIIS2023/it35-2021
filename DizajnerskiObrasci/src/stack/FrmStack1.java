package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack1 extends JFrame {

	private DefaultListModel<Rectangle> listM= new DefaultListModel<Rectangle>();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack1 frame = new FrmStack1();
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
	public FrmStack1() {
		setTitle("IT35/2018 Simona Bolehradsky ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblStack = new JLabel("Stack");
		lblStack.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStack.setForeground(new Color(204, 102, 153));
		pnlNorth.add(lblStack);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgAdd dlgAdd = new DlgAdd();
				dlgAdd.setVisible(true);
				if(dlgAdd.confirmation)
				{
					try{
						int x= Integer.parseInt(dlgAdd.getTxtX().getText());
						int y= Integer.parseInt(dlgAdd.getTxtY().getText());
						int width= Integer.parseInt(dlgAdd.getTxtWidth().getText());
						int height= Integer.parseInt(dlgAdd.getTxtHeight().getText());
						
						Rectangle rec= new Rectangle(new Point(x,y), width, height);
						listM.add(0, rec);
					}
					catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(null,"Please enter all data!");
					}
				}
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listM.isEmpty()){
					JOptionPane.showMessageDialog(null, "List is empty");
				}
				else
				{
					DlgAdd dlgAdd= new DlgAdd();
					Point p= listM.getElementAt(0).getUpperLeftPoint();
					int height= listM.getElementAt(0).getHeight();
					int width= listM.getElementAt(0).getWidth();
					
					dlgAdd.getTxtX().setText(Integer.toString(p.getX()));
					dlgAdd.getTxtY().setText(Integer.toString(p.getX()));
					dlgAdd.getTxtHeight().setText(Integer.toString(height));
					dlgAdd.getTxtWidth().setText(Integer.toString(width));
					dlgAdd.setVisible(true);
					if(dlgAdd.confirmation){
						listM.removeElementAt(0);
					}
				}
			}
		});
		pnlSouth.add(btnDelete);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		pnlCenter.add(scrollPane);
		
		JList listRectangle = new JList();
		scrollPane.setViewportView(listRectangle);
		listRectangle.setModel(listM);
	}

}
