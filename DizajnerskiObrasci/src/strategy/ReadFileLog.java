package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBackward;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeselectShape;
import command.CmdForward;
import command.CmdSelectShape;
import command.CmdUpdateHexagon;
import command.CmdUpdateShape;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class ReadFileLog implements SaveOpenFile {
	
	private DrawingController controller;
	private DrawingModel model;
	private DrawingFrame frame;
	private BufferedWriter writer;
	private BufferedReader reader;
	private int counter = 0;
	private DefaultListModel<String> tempList = new DefaultListModel<String>();

	public ReadFileLog(DrawingController controller, DrawingModel model, DrawingFrame frame) {
		this.controller = controller;
		this.model = model;
		this.frame = frame;
	}
	
	@Override
	public void open(File file) {
		try {

			String line;
			reader = new BufferedReader(new FileReader(file));
			frame.setReadFileLog(this);

			while ((line = reader.readLine()) != null) {

				System.out.println(line);
				tempList.addElement(line);


			}

			frame.getBtnLoadNextCommand().setEnabled(true);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void save(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel<String> list = frame.getDlmList();
			System.out.println(list.toString());
			for (int i = 0; i < frame.getDlmList().size(); i++) {
				writer.write(list.getElementAt(i));
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	

	public void readLine() {
		try {

			if (counter < tempList.size()) {
				String command = tempList.get(counter);
				String[] commands1 = command.split("->");
				switch (commands1[0]) {
				case "Undo":
					controller.undo();
					break;
				case "Redo":
					System.out.println("juhuu tuu u case");
					controller.redo();
					break;
				case "Added":
					Shape shape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);

					if (commands1[1].split(":")[0].equals("Donut")) {
						controller.execute(new CmdAddShape(model, (Donut) shape));
					} else if (commands1[1].split(":")[0].equals("Circle")) {
						controller.execute(new CmdAddShape(model, (Circle) shape));
					} else if (commands1[1].split(":")[0].equals("Hexagon")) {
						controller.execute(new CmdAddShape(model, (HexagonAdapter) shape));
					} else if (commands1[1].split(":")[0].equals("Line")) {
						controller.execute(new CmdAddShape(model, (Line) shape));
					} else if (commands1[1].split(":")[0].equals("Point")) {
						controller.execute(new CmdAddShape(model, (Point) shape));
					}
					else if (commands1[1].split(":")[0].equals("Rectangle")) {
						controller.execute(new CmdAddShape(model, (Rectangle) shape));
					}
					frame.getDlmList().addElement("Added->" + shape.toString());
					break;
				case "Updated":
					Shape oldShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index = controller.getIndexOfShape(oldShape);
					if (oldShape instanceof Point) {
						Point newPoint = parsePoint(commands1[2].split(":")[1]);
						controller.execute(new CmdUpdateShape((Point) model.getByIndex(index), newPoint));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newPoint.toString());
					}else if (oldShape instanceof Donut) {
						Donut newDonut = parseDonut(commands1[2].split(":")[1]);
						controller.execute(new CmdUpdateShape((Donut) model.getByIndex(index), newDonut));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newDonut.toString());
					} else if (oldShape instanceof Line) {
						Line newLine = parseLine(commands1[2].split(":")[1]);
						controller.execute(new CmdUpdateShape((Line) model.getByIndex(index), newLine));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newLine.toString());
					} else if (oldShape instanceof Rectangle) {
						Rectangle newRectangle = parseRectangle(commands1[2].split(":")[1]);
						controller.execute(new CmdUpdateShape((Rectangle) model.getByIndex(index), newRectangle));
						frame.getDlmList()
								.addElement("Updated->" + oldShape.toString() + "->" + newRectangle.toString());
					} else if (oldShape instanceof Circle) {
						Circle newCircle = parseCircle(commands1[2].split(":")[1]);
						controller.execute(new CmdUpdateShape((Circle) model.getByIndex(index), newCircle));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newCircle.toString());
					} 

					else if (oldShape instanceof HexagonAdapter) {
						HexagonAdapter newHexagon = parseHexagon(commands1[2].split(":")[1]);
						controller.execute(new CmdUpdateHexagon((HexagonAdapter) model.getByIndex(index), newHexagon));
						frame.getDlmList().addElement("Updated->" + oldShape.toString() + "->" + newHexagon.toString());
					}
					break;
				case "Removed shape":
					controller.delete();
					break;
				case "Bringed to front":
					Shape shapeMovedToFront = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.execute(new CmdBringToFront(model, shapeMovedToFront));
					frame.getDlmList().addElement("Bringed to front->" + shapeMovedToFront.toString());
					break;
				case "Bringed to back":
					Shape shapeMovedToBack = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.execute(new CmdBringToBack(model, shapeMovedToBack));
					frame.getDlmList().addElement("Bringed to back->" + shapeMovedToBack.toString());
					break;
				case "To front":
					Shape shapeBringedToFront = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.execute(new CmdForward(model, shapeBringedToFront));
					frame.getDlmList().addElement("To front->" + shapeBringedToFront.toString());
					break;
				case "To back":
					Shape shapeBringedToBack = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.execute(new CmdBackward(model, shapeBringedToBack));
					frame.getDlmList().addElement("To back->" + shapeBringedToBack.toString());
					break;
				case "Selected":
					Shape selectedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index1 = controller.getIndexOfShape(selectedShape);
					controller.execute(new CmdSelectShape(controller, model.getByIndex(index1)));
					frame.getDlmList().addElement("Selected->" + selectedShape.toString());
					controller.activitybtnEditUpdate();

					break;
				case "Unselected":
					Shape unselectedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index2 = controller.getIndexOfShape(unselectedShape);
					controller.execute(new CmdDeselectShape(controller, model.getByIndex(index2)));
					frame.getDlmList().addElement("Unselected->" + unselectedShape.toString());
					controller.activitybtnEditUpdate();
					break;
					
				}

				counter++;
			} else {
				frame.getBtnLoadNextCommand().setEnabled(false);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Shape parseShape(String shape, String shapeParameters) {
		if (shape.equals("Point")) {
			return parsePoint(shapeParameters);
		} else if (shape.equals("Line")) {
			return parseLine(shapeParameters);
		} else if (shape.equals("Rectangle")) {
			return parseRectangle(shapeParameters);
		} else if (shape.equals("Circle")) {
			return parseCircle(shapeParameters);
		} else if (shape.equals("Hexagon")) {
			return parseHexagon(shapeParameters);
		} else if (shape.equals("Donut")) {
			return parseDonut(shapeParameters);
		} else {
			return null;
		}
	}

	private Point parsePoint(String string) {
		String[] pointParts = string.split(";");
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String[] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]),
				new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]),
						Integer.parseInt(colors[2].split("-")[1])));
	}

	private Line parseLine(String string) {
		String[] lineParts = string.split(";");
		int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
		int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
		String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
		String[] edgeColors = s.split(",");
		return new Line(new Point(xStart, yStart), new Point(xEnd, yEnd),
				new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]),
						Integer.parseInt(edgeColors[2].split("-")[1])));
	}

	private Rectangle parseRectangle(String string) {
		String[] rectangleParts = string.split(";");
		int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[2].split("=")[1]);
		System.out.println(width);
		int height = Integer.parseInt(rectangleParts[3].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String[] edgeColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String[] interiorColors = s1.split(",");
		Color edge = new Color(Integer.parseInt(edgeColors[0].split("-")[1]),
				Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1]));
		System.out.println(edge.toString());
		return new Rectangle(new Point(x, y), width, height, false, edge,
				new Color(Integer.parseInt(interiorColors[0].split("-")[1]),
						Integer.parseInt(interiorColors[1].split("-")[1]),
						Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Circle parseCircle(String string) {
		String[] circleParts = string.split(";");
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String[] edgeColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String[] interiorColors = s1.split(",");
		return new Circle(new Point(x, y), radius, false,
				new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]),
						Integer.parseInt(edgeColors[2].split("-")[1])),
				new Color(Integer.parseInt(interiorColors[0].split("-")[1]),
						Integer.parseInt(interiorColors[1].split("-")[1]),
						Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Donut parseDonut(String string) {
		String[] donutParts = string.split(";");
		int radius = Integer.parseInt(donutParts[0].split("=")[1]);
		int x = Integer.parseInt(donutParts[1].split("=")[1]);
		int y = Integer.parseInt(donutParts[2].split("=")[1]);
		String s = donutParts[3].split("=")[1].substring(1, donutParts[3].split("=")[1].length() - 1);
		String[] edgeColors = s.split(",");
		String s1 = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
		String[] interiorColors = s1.split(",");
		int innerRadius = Integer.parseInt(donutParts[5].split("=")[1]);
		return new Donut(new Point(x, y), radius, innerRadius, false,
				new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]),
						Integer.parseInt(edgeColors[2].split("-")[1])),
				new Color(Integer.parseInt(interiorColors[0].split("-")[1]),
						Integer.parseInt(interiorColors[1].split("-")[1]),
						Integer.parseInt(interiorColors[2].split("-")[1])));

	}

	private HexagonAdapter parseHexagon(String string) {
		String[] hexagonParts = string.split(";");
		int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
		String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
		String[] edgeColors = s.split(",");
		String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
		String[] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]),
				Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]),
				Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new HexagonAdapter(h);
	}

}
