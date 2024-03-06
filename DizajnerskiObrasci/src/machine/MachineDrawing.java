package machine;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdRemoveShape;
import command.CmdUpdateHexagon;
import command.CmdUpdateShape;
import command.Command;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class MachineDrawing {

	Point startPoint;
	private DrawingController controller;

	public MachineDrawing(DrawingController controller) {
		this.controller = controller;

	}

	public String pointDraw(MouseEvent me, DrawingFrame frame, DrawingModel model) {

		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.getTxtX().setText(Integer.toString(me.getX()));
		dlgPoint.getTxtY().setText(Integer.toString(me.getY()));
		dlgPoint.getBtnColor().setForeground(frame.getBtnInnerColor().getBackground());
		dlgPoint.getBtnColor().setBackground(frame.getTextFieldOuterColor().getBackground());
		dlgPoint.setVisible(true);
		if (dlgPoint.confirmation) {
			Point point = new Point(me.getX(), me.getY(), false, dlgPoint.getBtnColor().getBackground());
			CmdAddShape cmdAdd = new CmdAddShape(model, point);
			cmdAdd.execute();
			controller.newCommandExecuted(cmdAdd);
			return cmdAdd.toString();

		}
		return null;
	}

	public String lineDraw(MouseEvent me, DrawingFrame frame, DrawingModel model) {

		if (startPoint == null) {
			startPoint = new Point(me.getX(), me.getY());
		}else  {
			DlgLine dlgLine = new DlgLine();
			dlgLine.getTextStartX().setText(Integer.toString(startPoint.getX()));
			dlgLine.getTextStartY().setText(Integer.toString(startPoint.getY()));
			dlgLine.getTextEndX().setText(Integer.toString(me.getX()));
			dlgLine.getTextEndY().setText(Integer.toString(me.getY()));
			dlgLine.getBtnColor().setBackground(frame.getTextFieldOuterColor().getBackground());
			dlgLine.setVisible(true);

			if (dlgLine.confirmation) {
				Line line = new Line(startPoint, new Point(me.getX(), me.getY()), false,
						dlgLine.getBtnColor().getBackground());
				CmdAddShape cmdAdd = new CmdAddShape(model, line);
				cmdAdd.execute();
				controller.newCommandExecuted(cmdAdd);
				return cmdAdd.toString();

			}
			startPoint = null;
		}
		return null;

	}

	public String circleDraw(MouseEvent me, DrawingFrame frame, DrawingModel model) {
		Point center = new Point(me.getX(), me.getY());
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.getTxtX().setText(Integer.toString(me.getX()));
		dlgCircle.getTxtY().setText(Integer.toString(me.getY()));
		dlgCircle.getBtnInnerColor().setBackground(frame.getTextFieldInnerColor().getBackground());
		dlgCircle.getBtnBorderColor().setBackground(frame.getTextFieldOuterColor().getBackground());
		dlgCircle.setVisible(true);

		if (dlgCircle.confirmation) {
			try {

				int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText().toString());

				if (radius <= 0) {
					JOptionPane.showMessageDialog(null, "Radius must be positive and greater than 0!", "Error!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Circle circle = new Circle(center, radius, false, dlgCircle.getBtnBorderColor().getBackground(),
							dlgCircle.getBtnInnerColor().getBackground());
					CmdAddShape cmdAdd = new CmdAddShape(model, circle);
					cmdAdd.execute();
					controller.newCommandExecuted(cmdAdd);
					return cmdAdd.toString();

				}

			} catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You have not entered a number");
			}
		}
		return null;

	}

	public String rectangleDraw(MouseEvent me, DrawingFrame frame, DrawingModel model) {

		Point upperLeftP = new Point(me.getX(), me.getY());
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.getTxtX().setText(Integer.toString(me.getX()));
		dlgRectangle.getTxtY().setText(Integer.toString(me.getY()));
		dlgRectangle.getBtnIcolor().setBackground(frame.getTextFieldInnerColor().getBackground());
		dlgRectangle.getBtnBcolor().setBackground(frame.getTextFieldOuterColor().getBackground());
		dlgRectangle.setVisible(true);

		if (dlgRectangle.confirmation) {
			try {
				int width = Integer.parseInt(dlgRectangle.getTextWidth().getText().toString());
				int height = Integer.parseInt(dlgRectangle.getTextHeight().getText().toString());

				if (height <= 0 && width <= 0) {
					JOptionPane.showMessageDialog(null, "Width and hight must be positive and greater than 0!",
							"Error!", JOptionPane.WARNING_MESSAGE);

				} else {
					Rectangle rectangle = new Rectangle(upperLeftP, width, height, false,
							dlgRectangle.getBtnBcolor().getBackground(), dlgRectangle.getBtnIcolor().getBackground());
					CmdAddShape cmdAdd = new CmdAddShape(model, rectangle);
					cmdAdd.execute();
					controller.newCommandExecuted(cmdAdd);
					return cmdAdd.toString();

				}
			}

			catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You must enter a number!");
			}
		}
		return null;
	}

	public String hexagonDraw(MouseEvent me, DrawingFrame frame, DrawingModel model) {
		Point center = new Point(me.getX(), me.getY());
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.getTextX().setText(Integer.toString(me.getX()));
		dlgHexagon.getTextY().setText(Integer.toString(me.getY()));
		dlgHexagon.getBtnInnerColor().setBackground(frame.getTextFieldInnerColor().getBackground());
		dlgHexagon.getBtnBorderColor().setBackground(frame.getTextFieldOuterColor().getBackground());
		int hRadius = 0;

		dlgHexagon.setVisible(true);

		if (dlgHexagon.confirmation) {
			try {
				hRadius = Integer.parseInt(dlgHexagon.getTextRadius().getText().toString());

				if (hRadius < 0) {
					JOptionPane.showMessageDialog(null, "Radius must be positive and greater than 0!", "Error!",
							JOptionPane.WARNING_MESSAGE);

				} else {

					HexagonAdapter hexagon = new HexagonAdapter(center, false, hRadius,
							dlgHexagon.getBtnInnerColor().getBackground(),
							dlgHexagon.getBtnBorderColor().getBackground());
					CmdAddShape cmdAdd = new CmdAddShape(model, hexagon);
					cmdAdd.execute();
					controller.newCommandExecuted(cmdAdd);
					return cmdAdd.toString();

				}
			} catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You have not entered a number", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return null;

	}

	public String donutDraw(MouseEvent me, DrawingFrame frame, DrawingModel model) {
		Point center = new Point(me.getX(), me.getY());
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.getTxtX().setText(Integer.toString(me.getX()));
		dlgDonut.getTxtY().setText(Integer.toString(me.getY()));
		dlgDonut.getBtnInnerColor().setBackground(frame.getTextFieldInnerColor().getBackground());
		dlgDonut.getBtnBorderColor().setBackground(frame.getTextFieldOuterColor().getBackground());
		dlgDonut.setVisible(true);

		if (dlgDonut.confirmation) {
			try {
				int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText().toString());
				int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText().toString());

				if (radius < innerRadius) {
					JOptionPane.showMessageDialog(null, "Radius must be greater that inner radius", "Error!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Donut donut = new Donut(center, radius, innerRadius, false,
							dlgDonut.getBtnBorderColor().getBackground(), dlgDonut.getBtnInnerColor().getBackground());
					CmdAddShape cmdAdd = new CmdAddShape(model, donut);
					cmdAdd.execute();
					controller.newCommandExecuted(cmdAdd);
					return cmdAdd.toString();

				}

			} catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You have not entered a number", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return null;

	}

//MODIFY

	public String modifyPoint(Point oldPoint, DrawingFrame frame, DrawingModel model) {
		DlgPoint dlgPoint = new DlgPoint();

		dlgPoint.getTxtX().setText(Integer.toString(oldPoint.getX()));
		dlgPoint.getTxtY().setText(Integer.toString(oldPoint.getY()));
		dlgPoint.getBtnColor().setBackground(oldPoint.getColor());
		dlgPoint.setVisible(true);
		if (dlgPoint.confirmation) {
			int x = Integer.parseInt(dlgPoint.getTxtX().getText());
			int y = Integer.parseInt(dlgPoint.getTxtY().getText());
			Point newPoint = new Point(x, y, true, dlgPoint.getBtnColor().getBackground());
			CmdUpdateShape cmdUpdate = new CmdUpdateShape(oldPoint, newPoint);
			cmdUpdate.execute();
			controller.newCommandExecuted(cmdUpdate);
			return cmdUpdate.toString();

		}
		return null;
	}

	public String modifyLine(Line oldLine, DrawingFrame frame, DrawingModel model) {
		DlgLine dlgLine = new DlgLine();

		dlgLine.getTextStartX().setText(Integer.toString(oldLine.getStartPoint().getX()));
		dlgLine.getTextStartY().setText(Integer.toString(oldLine.getStartPoint().getY()));
		dlgLine.getTextEndX().setText(Integer.toString(oldLine.getEndPoint().getX()));
		dlgLine.getTextEndY().setText(Integer.toString(oldLine.getEndPoint().getY()));
		dlgLine.getBtnColor().setBackground(oldLine.getColor());
		dlgLine.setVisible(true);

		if (dlgLine.confirmation) {
			int startX = Integer.parseInt(dlgLine.getTextStartX().getText());
			int startY = Integer.parseInt(dlgLine.getTextStartY().getText());
			int endX = Integer.parseInt(dlgLine.getTextEndX().getText());
			int endY = Integer.parseInt(dlgLine.getTextEndY().getText());
			System.out.println("tu");
			Line newLine = new Line(new Point(startX, startY), new Point(endX, endY),
					dlgLine.getBtnColor().getBackground());
			CmdUpdateShape cmdUpdate = new CmdUpdateShape(oldLine, newLine);
			cmdUpdate.execute();
			controller.newCommandExecuted(cmdUpdate);
			System.out.println(newLine.getColor() + "new line");
			return cmdUpdate.toString();

		}
		return null;
	}

	public String modifyRectangle(Rectangle oldRectangle, DrawingFrame frame, DrawingModel model) {

		DlgRectangle dlgRectangle = new DlgRectangle();

		dlgRectangle.getTxtX().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getX()));
		dlgRectangle.getTxtY().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getY()));
		dlgRectangle.getTextWidth().setText(Integer.toString(oldRectangle.getWidth()));
		dlgRectangle.getTextHeight().setText(Integer.toString(oldRectangle.getHeight()));
		dlgRectangle.getBtnIcolor().setBackground(oldRectangle.getInnerColor());
		dlgRectangle.getBtnBcolor().setBackground(oldRectangle.getColor());

		dlgRectangle.setVisible(true);
		if (dlgRectangle.confirmation) {
			try {
				int x = Integer.parseInt(dlgRectangle.getTxtX().getText());
				int y = Integer.parseInt(dlgRectangle.getTxtY().getText());
				Point upperLeft = new Point(x, y);
				int height = Integer.parseInt(dlgRectangle.getTextHeight().getText());
				int width = Integer.parseInt(dlgRectangle.getTextWidth().getText());

				if (height <= 0 && width <= 0) {
					JOptionPane.showMessageDialog(null, "The width and height must be greater than 0");

				}

				else {

					Rectangle newrectangle = new Rectangle(upperLeft, width, height, true,
							dlgRectangle.getBtnBcolor().getBackground(), dlgRectangle.getBtnIcolor().getBackground());
					CmdUpdateShape cmdUpdate = new CmdUpdateShape(oldRectangle, newrectangle);
					cmdUpdate.execute();
					controller.newCommandExecuted(cmdUpdate);
					return cmdUpdate.toString();

				}
			} catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You have not entered a number", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		return null;

	}

	public String modifyCircle(Circle oldCircle, DrawingFrame frame, DrawingModel model) {

		DlgCircle dlgCircle = new DlgCircle();

		dlgCircle.getTxtX().setText(Integer.toString(oldCircle.getCenter().getX()));
		dlgCircle.getTxtY().setText(Integer.toString(oldCircle.getCenter().getY()));

		dlgCircle.getTxtRadius().setText(Integer.toString(oldCircle.getRadius()));
		dlgCircle.getBtnBorderColor().setBackground(oldCircle.getColor());
		dlgCircle.getBtnInnerColor().setBackground(oldCircle.getInnerColor());

		dlgCircle.setVisible(true);

		if (dlgCircle.confirmation) {
			try {
				int x = Integer.parseInt(dlgCircle.getTxtX().getText());
				int y = Integer.parseInt(dlgCircle.getTxtY().getText());
				int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText().toString());

				if (radius > 0) {
					Circle newCircle = new Circle(new Point(x, y), radius,
							dlgCircle.getBtnBorderColor().getBackground(),
							dlgCircle.getBtnInnerColor().getBackground());
					CmdUpdateShape cmdUpdate = new CmdUpdateShape(oldCircle, newCircle);
					cmdUpdate.execute();
					controller.newCommandExecuted(cmdUpdate);
					return cmdUpdate.toString();

				} else {

					JOptionPane.showMessageDialog(null, "Radius must be greater than 0");
				}

			} catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You have not entered a number", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return null;

	}

	public String modifyDonut(Donut oldDonut, DrawingFrame frame, DrawingModel model) {
		DlgDonut dlgDonut = new DlgDonut();

		dlgDonut.getTxtX().setText(Integer.toString(oldDonut.getCenter().getX()));
		dlgDonut.getTxtY().setText(Integer.toString(oldDonut.getCenter().getY()));

		dlgDonut.getTxtInnerRadius().setText(Integer.toString(oldDonut.getInnerRadius()));
		dlgDonut.getTxtRadius().setText(Integer.toString(oldDonut.getRadius()));


		dlgDonut.getBtnBorderColor().setBackground(oldDonut.getColor());
		dlgDonut.getBtnInnerColor().setBackground(oldDonut.getInnerColor());
		dlgDonut.setVisible(true);

		if (dlgDonut.isConfirmation()) {
			try {
				int x = Integer.parseInt(dlgDonut.getTxtX().getText());
				int y = Integer.parseInt(dlgDonut.getTxtY().getText());
				int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
				int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
				System.out.println(innerRadius);

				if (radius < innerRadius) {
					JOptionPane.showMessageDialog(null, "Radius must be greater than inner radius", "Error!",
							JOptionPane.WARNING_MESSAGE);

				} else {

					Donut newDonut = new Donut(new Point(x, y), radius, innerRadius,
							dlgDonut.getBtnBorderColor().getBackground(), dlgDonut.getBtnInnerColor().getBackground());
					CmdUpdateShape cmdUpdate = new CmdUpdateShape(oldDonut, newDonut);
					System.out.println(newDonut.getInnerRadius());
					cmdUpdate.execute();
					controller.newCommandExecuted(cmdUpdate);
					return cmdUpdate.toString();

				}

			} catch (Exception NumberFormatException) {
				JOptionPane.showMessageDialog(null, "You have not entered a number", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return null;

	}

	public String modifyHexagon(HexagonAdapter oldHexagon, DrawingFrame frame, DrawingModel model) {

		DlgHexagon dlgHexagon = new DlgHexagon();

		dlgHexagon.getBtnBorderColor().setBackground(oldHexagon.getOutsideColor());
		dlgHexagon.getBtnInnerColor().setBackground(oldHexagon.getInsideColor());
		System.out.println(oldHexagon.getColor());
		dlgHexagon.getTextX().setText(Integer.toString(oldHexagon.getCenter().getX()));
		
		dlgHexagon.getTextY().setText(Integer.toString(oldHexagon.getCenter().getY()));
		dlgHexagon.getTextRadius().setText(Integer.toString(oldHexagon.getRadius()));

		dlgHexagon.setVisible(true);

		if (dlgHexagon.confirmation) {
			int hRadius = Integer.parseInt(dlgHexagon.getTextRadius().getText());
			int x = Integer.parseInt(dlgHexagon.getTextX().getText());
			int y = Integer.parseInt(dlgHexagon.getTextY().getText());

			HexagonAdapter newHexagon = new HexagonAdapter(new Point(x, y), true, hRadius,
					dlgHexagon.getBtnInnerColor().getBackground(), dlgHexagon.getBtnBorderColor().getBackground());
			CmdUpdateHexagon cmdUpdate = new CmdUpdateHexagon(oldHexagon, newHexagon);
			cmdUpdate.execute();
			controller.newCommandExecuted(cmdUpdate);
			return cmdUpdate.toString();

		}
		return null;
	}

	public ArrayList<Shape> deleteShape(ArrayList<Shape> selectedShapes, DrawingFrame frame, DrawingModel model) {

		for (int i = selectedShapes.size() - 1; i >= 0; i--) {
			int dialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the shape?", "Warning",
					JOptionPane.YES_NO_OPTION);
			if (dialog == JOptionPane.YES_OPTION) {
				if (selectedShapes.get(i) instanceof Point) {
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model, (Point) selectedShapes.get(i), selectedShapes);
					cmdRemove.execute();
					controller.newCommandExecuted(cmdRemove);
					controller.addCommandInLog(cmdRemove.toString());
				} else if (selectedShapes.get(i) instanceof Line) {
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model, (Line) selectedShapes.get(i),selectedShapes);
					cmdRemove.execute();
					controller.newCommandExecuted(cmdRemove);
					controller.addCommandInLog(cmdRemove.toString());
				} else if (selectedShapes.get(i) instanceof Rectangle) {
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model, (Rectangle) selectedShapes.get(i),selectedShapes);
					cmdRemove.execute();
					controller.newCommandExecuted(cmdRemove);
					controller.addCommandInLog(cmdRemove.toString());
				} else if (selectedShapes.get(i) instanceof Donut) {
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model, (Donut) selectedShapes.get(i),selectedShapes);
					cmdRemove.execute();
					controller.newCommandExecuted(cmdRemove);
					controller.addCommandInLog(cmdRemove.toString());
				} else if (selectedShapes.get(i) instanceof Circle) {
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model, (Circle) selectedShapes.get(i),selectedShapes);
					cmdRemove.execute();
					controller.newCommandExecuted(cmdRemove);
					controller.addCommandInLog(cmdRemove.toString());
				} else if (selectedShapes.get(i) instanceof HexagonAdapter) {
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model, (HexagonAdapter) selectedShapes.get(i),selectedShapes);
					cmdRemove.execute();
					controller.newCommandExecuted(cmdRemove);
					controller.addCommandInLog(cmdRemove.toString());
				}
			}
		}

		frame.getView().repaint();
		return selectedShapes;

	}

}
