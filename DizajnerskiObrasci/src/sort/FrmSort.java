package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
import stack.DlgAdd;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FrmSort extends JFrame {
	private DefaultListModel<Rectangle> listDLM=new DefaultListModel<Rectangle>();
	private ArrayList<Rectangle> array =new ArrayList<Rectangle>();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setTitle("IT35/2018 Simona Bolehradsky ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setForeground(new Color(255, 204, 255));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblSort = new JLabel("Sort");
		lblSort.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSort.setForeground(new Color(204, 102, 153));
		pnlNorth.add(lblSort);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
//------------------------------ADD---------------------------------------------------------------------		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgAdd dlgAdd = new DlgAdd();
				dlgAdd.confirmation=false;
				dlgAdd.setVisible(true);
				
				if(DlgAdd.confirmation){
					
					try{
						int x= Integer.parseInt(dlgAdd.getTxtX().getText());
						int y= Integer.parseInt(dlgAdd.getTxtY().getText());
						int width=Integer.parseInt(dlgAdd.getTxtWidth().getText());
						int height=Integer.parseInt(dlgAdd.getTxtHeight().getText());
						
						Rectangle rec= new Rectangle(new Point(x,y),width, height);
						listDLM.add(0, rec);
					}
					catch(NumberFormatException exception){
						
						JOptionPane.showMessageDialog(null, "Please enter all data!");
						
					}
				}
			}
		});
		pnlSouth.add(btnAdd);
		
//-------------------------SORT---------------------------------------------------------------------------------		
		
JButton btnSort = new JButton("Sort");
btnSort.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		Rectangle rectangle;
		for(int i = 0; i<listDLM.getSize();i++){
			
			array.add(listDLM.getElementAt(i));
		}
		
		for(int j=array.size(); j>0; j--){
			
			for(int k=0; k<j-1; k++){
				if((array.get(k)).compareTo(array.get(k+1))>0){
					rectangle= array.get(k);
					array.set(k, array.get(k+1));
					array.set(k+1, rectangle);
				}
			}
		}
		
		listDLM.removeAllElements();
		for(int i=0; i<array.size(); i++){
			listDLM.addElement(array.get(i));
		}
		
		array.clear();
	}
});
		pnlSouth.add(btnSort);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		
		JScrollPane scrRectangle = new JScrollPane();
		pnlCenter.add(scrRectangle);
		
		JList list = new JList();
		
		scrRectangle.setViewportView(list);
		list.setModel(listDLM);
	}

}
