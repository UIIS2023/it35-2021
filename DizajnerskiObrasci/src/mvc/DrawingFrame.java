package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import geometry.Shape;
import machine.MachineDrawing;
import strategy.ReadFileLog;

import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JMenu;

public class DrawingFrame extends JFrame {
	private String click = null;
	private JPanel contentPane;

	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private MachineDrawing machine;
	private JPanel pnlSouth;
	private JScrollPane scrollPane;
	private DefaultListModel<String> dlmList;
	private DefaultListModel<String> dlmLog;
	private JPanel panel;
	private JButton btnOutColor;
	private JButton btnInnerColor;
	private JRadioButton rdbDonut;
	private JRadioButton rdbHexagon;
	private JToggleButton tglbtnSelect;
	private JRadioButton rdbtnPoint;
	private JRadioButton rdbtnLine;
	private JRadioButton rdbtnRectangle;
	private JRadioButton rdbtnCircle;
	private JRadioButton rdbtnDonut;
	private JToggleButton tglbtnModify;
	private JToggleButton tglbtnDelete;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JButton btnRedo;
	private JButton btnUndo;
	private JTextField textFieldInnerColor;
	private JTextField textFieldOuterColor;
	private JScrollPane scrollPane_1;
	private JList list;
	private JButton btnLoadNextCommand;
	private ReadFileLog readFileLog;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;

	/**
	 * Create the frame.
	 */
	public DrawingFrame() {
		setBackground(new Color(255, 192, 203));
		

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		setTitle("Bolehradsky Simona IT35-2018");
		view.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(300, 300, 3000, 3000);
		//Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//setBounds(0, 0,screen.width,screen.height - 30);
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(100,100);
		setSize(DimMax);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.open();
				repaint();
			}
		});
		mnNewMenu.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
		mnNewMenu.add(mntmSave);

		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ButtonGroup group = new ButtonGroup();

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 192, 203));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[] { 81, 69, 0, 0, 74, 85, 14, 0, 72, 0, 85, 91, 31, 0, 0, 0, 0 };
		gbl_pnlNorth.rowHeights = new int[] { 30, 0 };
		gbl_pnlNorth.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlNorth.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlNorth.setLayout(gbl_pnlNorth);

		rdbtnPoint = new JRadioButton("Point");
		rdbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click = "point";
			}
		});
		GridBagConstraints gbc_rdbtnPoint = new GridBagConstraints();
		gbc_rdbtnPoint.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnPoint.gridx = 0;
		gbc_rdbtnPoint.gridy = 0;
		pnlNorth.add(rdbtnPoint, gbc_rdbtnPoint);
		rdbtnPoint.setForeground(new Color(139, 0, 139));
		group.add(rdbtnPoint);

		rdbtnLine = new JRadioButton("Line");
		rdbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click = "line";
			}
		});
		GridBagConstraints gbc_rdbtnLine = new GridBagConstraints();
		gbc_rdbtnLine.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnLine.gridx = 1;
		gbc_rdbtnLine.gridy = 0;
		pnlNorth.add(rdbtnLine, gbc_rdbtnLine);
		rdbtnLine.setForeground(new Color(139, 0, 139));
		group.add(rdbtnLine);

		rdbtnRectangle = new JRadioButton("Rectangle");
		rdbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click = "rectangle";
			}
		});
		GridBagConstraints gbc_rdbtnRectangle = new GridBagConstraints();
		gbc_rdbtnRectangle.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnRectangle.gridx = 2;
		gbc_rdbtnRectangle.gridy = 0;
		pnlNorth.add(rdbtnRectangle, gbc_rdbtnRectangle);
		rdbtnRectangle.setForeground(new Color(139, 0, 139));
		group.add(rdbtnRectangle);

		rdbtnCircle = new JRadioButton("Circle");
		rdbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click = "circle";

			}
		});
		GridBagConstraints gbc_rdbtnCircle = new GridBagConstraints();
		gbc_rdbtnCircle.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnCircle.gridx = 3;
		gbc_rdbtnCircle.gridy = 0;
		pnlNorth.add(rdbtnCircle, gbc_rdbtnCircle);
		rdbtnCircle.setForeground(new Color(139, 0, 139));
		group.add(rdbtnCircle);

		rdbtnDonut = new JRadioButton("Donut");
		rdbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click = "donut";
			}
		});
		GridBagConstraints gbc_rdbtnDonut = new GridBagConstraints();
		gbc_rdbtnDonut.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnDonut.gridx = 4;
		gbc_rdbtnDonut.gridy = 0;
		pnlNorth.add(rdbtnDonut, gbc_rdbtnDonut);
		rdbtnDonut.setForeground(new Color(139, 0, 139));
		group.add(rdbtnDonut);

		rdbHexagon = new JRadioButton("Hexagon");
		rdbHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click = "hexagon";
			}
		});
		GridBagConstraints gbc_rdbHexagon = new GridBagConstraints();
		gbc_rdbHexagon.insets = new Insets(0, 0, 0, 5);
		gbc_rdbHexagon.gridx = 5;
		gbc_rdbHexagon.gridy = 0;
		pnlNorth.add(rdbHexagon, gbc_rdbHexagon);
		rdbHexagon.setForeground(new Color(139, 0, 139));
		group.add(rdbHexagon);

		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldInnerColor.setBackground(JColorChooser.showDialog(null, "Chose inner color", Color.RED));
			}
		});
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnInnerColor.gridx = 7;
		gbc_btnInnerColor.gridy = 0;
		pnlNorth.add(btnInnerColor, gbc_btnInnerColor);

		textFieldInnerColor = new JTextField();
		textFieldInnerColor.setBackground(Color.WHITE);
		textFieldInnerColor.setForeground(Color.BLACK);
		GridBagConstraints gbc_textFieldInnerColor = new GridBagConstraints();
		gbc_textFieldInnerColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInnerColor.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldInnerColor.gridx = 8;
		gbc_textFieldInnerColor.gridy = 0;
		pnlNorth.add(textFieldInnerColor, gbc_textFieldInnerColor);
		textFieldInnerColor.setColumns(10);

		btnOutColor = new JButton("Outline color");
		btnOutColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldOuterColor.setBackground(JColorChooser.showDialog(null, "Chose outline color", Color.RED));
			}
		});
		GridBagConstraints gbc_btnOutColor = new GridBagConstraints();
		gbc_btnOutColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutColor.gridx = 10;
		gbc_btnOutColor.gridy = 0;
		pnlNorth.add(btnOutColor, gbc_btnOutColor);

		textFieldOuterColor = new JTextField();
		textFieldOuterColor.setBackground(Color.BLACK);
		textFieldOuterColor.setForeground(Color.WHITE);
		GridBagConstraints gbc_textFieldOuterColor = new GridBagConstraints();
		gbc_textFieldOuterColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOuterColor.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldOuterColor.gridx = 11;
		gbc_textFieldOuterColor.gridy = 0;
		pnlNorth.add(textFieldOuterColor, gbc_textFieldOuterColor);
		textFieldOuterColor.setColumns(10);

		JPanel pnlCentar = new JPanel();
		contentPane.add(view, BorderLayout.CENTER);

		view.setSize(new Dimension(20, 40));
		view.setPreferredSize(new Dimension(200, 400));
		contentPane.add(view);

		pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		dlmList = new DefaultListModel<String>();
		GridBagLayout gbl_pnlSouth = new GridBagLayout();
		gbl_pnlSouth.columnWidths = new int[] { 103, 0, 61, 63, 2, 61, 71, 69, 99, 95, 55, 55, 0 };
		gbl_pnlSouth.rowHeights = new int[] { 21, 0 };
		gbl_pnlSouth.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_pnlSouth.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlSouth.setLayout(gbl_pnlSouth);
		/*scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.WEST;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 0;
		pnlSouth.add(scrollPane, gbc_scrollPane);*/

		

		panel = new JPanel();
		panel.setBackground(new Color(255, 192, 203));
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 139, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 37, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSelect.gridx = 0;
		gbc_tglbtnSelect.gridy = 1;
		panel.add(tglbtnSelect, gbc_tglbtnSelect);
		tglbtnSelect.setForeground(new Color(139, 0, 139));
		tglbtnSelect.setBackground(UIManager.getColor("Button.shadow"));
		tglbtnSelect.setEnabled(true);

		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setEnabled(false);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
			}
		});
		GridBagConstraints gbc_tglbtnModify = new GridBagConstraints();
		gbc_tglbtnModify.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnModify.gridx = 1;
		gbc_tglbtnModify.gridy = 1;
		panel.add(tglbtnModify, gbc_tglbtnModify);
		tglbtnModify.setForeground(new Color(139, 0, 139));
		tglbtnModify.setBackground(SystemColor.inactiveCaption);
		

		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		GridBagConstraints gbc_tglbtnDelete = new GridBagConstraints();
		gbc_tglbtnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDelete.gridx = 2;
		gbc_tglbtnDelete.gridy = 1;
		panel.add(tglbtnDelete, gbc_tglbtnDelete);
		tglbtnDelete.setForeground(new Color(255, 255, 255));
		tglbtnDelete.setBackground(new Color(219, 112, 147));
		tglbtnDelete.setEnabled(false);
		
				btnBringToBack = new JButton("Bring To Back");
				btnBringToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToBack();
					}
				});
				GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
				gbc_btnBringToBack.insets = new Insets(0, 0, 5, 5);
				gbc_btnBringToBack.gridx = 5;
				gbc_btnBringToBack.gridy = 1;
				panel.add(btnBringToBack, gbc_btnBringToBack);
				btnBringToBack.setForeground(new Color(139, 0, 139));
				btnBringToBack.setEnabled(false);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridwidth = 8;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 13;
		gbc_scrollPane_1.gridy = 0;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		
		dlmList = new DefaultListModel<String>();
		scrollPane = new JScrollPane();
		pnlSouth.add(scrollPane);

		list = new JList<String>();
		list.setBackground(new Color(255, 240, 245));
		list.setEnabled(false);
		list.setModel(dlmList);
		scrollPane_1.setViewportView(list);
				
						btnToFront = new JButton("To Front");
						btnToFront.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.forward();
							}
						});
						GridBagConstraints gbc_btnToFront = new GridBagConstraints();
						gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
						gbc_btnToFront.gridx = 6;
						gbc_btnToFront.gridy = 1;
						panel.add(btnToFront, gbc_btnToFront);
						btnToFront.setForeground(new Color(139, 0, 139));
						btnToFront.setEnabled(false);
		
		btnLoadNextCommand = new JButton("Load next command");
		btnLoadNextCommand.setEnabled(false);
		btnLoadNextCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(readFileLog != null) {
					readFileLog.readLine();
					view.repaint();

					}
				}
			
				
		});
		
				btnUndo = new JButton("Undo");
				btnUndo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.undo();
					}
				});
				GridBagConstraints gbc_btnUndo = new GridBagConstraints();
				gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
				gbc_btnUndo.gridx = 8;
				gbc_btnUndo.gridy = 1;
				panel.add(btnUndo, gbc_btnUndo);
				btnUndo.setForeground(new Color(139, 0, 139));
				btnUndo.setBackground(UIManager.getColor("Button.light"));
				btnUndo.setEnabled(false);
		GridBagConstraints gbc_btnLoadNextCommand = new GridBagConstraints();
		gbc_btnLoadNextCommand.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadNextCommand.gridx = 21;
		gbc_btnLoadNextCommand.gridy = 1;
		panel.add(btnLoadNextCommand, gbc_btnLoadNextCommand);
		
				btnBringToFront = new JButton("Bring To Front");
				btnBringToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToFront();
					}
				});
				GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
				gbc_btnBringToFront.insets = new Insets(0, 0, 0, 5);
				gbc_btnBringToFront.gridx = 5;
				gbc_btnBringToFront.gridy = 2;
				panel.add(btnBringToFront, gbc_btnBringToFront);
				btnBringToFront.setForeground(new Color(139, 0, 139));
				btnBringToFront.setEnabled(false);
				
						btnToBack = new JButton("To Back");
						btnToBack.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.backward();
							}
						});
						GridBagConstraints gbc_btnToBack = new GridBagConstraints();
						gbc_btnToBack.insets = new Insets(0, 0, 0, 5);
						gbc_btnToBack.gridx = 6;
						gbc_btnToBack.gridy = 2;
						panel.add(btnToBack, gbc_btnToBack);
						btnToBack.setForeground(new Color(139, 0, 139));
						btnToBack.setEnabled(false);
				
						btnRedo = new JButton("Redo");
						btnRedo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.redo();
							}
						});
						GridBagConstraints gbc_btnRedo = new GridBagConstraints();
						gbc_btnRedo.insets = new Insets(0, 0, 0, 5);
						gbc_btnRedo.gridx = 8;
						gbc_btnRedo.gridy = 2;
						panel.add(btnRedo, gbc_btnRedo);
						btnRedo.setForeground(new Color(139, 0, 139));
						btnRedo.setBackground(UIManager.getColor("Button.light"));
						btnRedo.setEnabled(false);
	}
	
	

	public JButton getBtnLoadNextCommand() {
		return btnLoadNextCommand;
	}



	public void setBtnLoadNextCommand(JButton btnLoadNextCommand) {
		this.btnLoadNextCommand = btnLoadNextCommand;
	}



	public ReadFileLog getReadFileLog() {
		return readFileLog;
	}



	public void setReadFileLog(ReadFileLog readFileLog) {
		this.readFileLog = readFileLog;
	}



	public MachineDrawing getMachine() {
		return machine;
	}

	public void setMachine(MachineDrawing machine) {
		this.machine = machine;
	}

	public JTextField getTextFieldInnerColor() {
		return textFieldInnerColor;
	}

	public void setTextFieldInnerColor(JTextField textFieldInnerColor) {
		this.textFieldInnerColor = textFieldInnerColor;
	}

	public JTextField getTextFieldOuterColor() {
		return textFieldOuterColor;
	}

	public void setTextFieldOuterColor(JTextField textFieldOuterColor) {
		this.textFieldOuterColor = textFieldOuterColor;
	}

	public void addCommand(String lista) {
		dlmList.addElement(lista);
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JRadioButton getRdbtnPoint() {
		return rdbtnPoint;
	}

	public void setRdbtnPoint(JRadioButton rdbtnPoint) {
		this.rdbtnPoint = rdbtnPoint;
	}

	public JRadioButton getRdbtnLine() {
		return rdbtnLine;
	}

	public void setRdbtnLine(JRadioButton rdbtnLine) {
		this.rdbtnLine = rdbtnLine;
	}

	public JRadioButton getRdbtnRectangle() {
		return rdbtnRectangle;
	}

	public void setRdbtnRectangle(JRadioButton rdbtnRectangle) {
		this.rdbtnRectangle = rdbtnRectangle;
	}

	public JRadioButton getRdbtnCircle() {
		return rdbtnCircle;
	}

	public void setRdbtnCircle(JRadioButton rdbtnCircle) {
		this.rdbtnCircle = rdbtnCircle;
	}

	public JRadioButton getRdbtnDonut() {
		return rdbtnDonut;
	}

	public void setRdbtnDonut(JRadioButton rdbtnDonut) {
		this.rdbtnDonut = rdbtnDonut;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public void setTglbtnModify(JToggleButton tglbtnModify) {
		this.tglbtnModify = tglbtnModify;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public void setTglbtnDelete(JToggleButton tglbtnDelete) {
		this.tglbtnDelete = tglbtnDelete;
	}

	public JButton getBtnOutColor() {
		return btnOutColor;
	}

	public void setBtnOutColor(JButton btnOutColor) {
		this.btnOutColor = btnOutColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JRadioButton getRdbDonut() {
		return rdbDonut;
	}

	public void setRdbDonut(JRadioButton rdbDonut) {
		this.rdbDonut = rdbDonut;
	}

	public JRadioButton getRdbHexagon() {
		return rdbHexagon;
	}

	public void setRdbHexagon(JRadioButton rdbHexagon) {
		this.rdbHexagon = rdbHexagon;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public DefaultListModel<String> getDlmList() {
		return dlmList;
	}

	public void setDlmList(DefaultListModel<String> dlmList) {
		this.dlmList = dlmList;
	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	

}
