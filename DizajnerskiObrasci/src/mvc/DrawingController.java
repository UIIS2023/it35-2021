package mvc;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.CmdBackward;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeselectShape;
import command.CmdForward;
import command.CmdSelectShape;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import machine.MachineDrawing;
import observer.Observable;
import observer.Observer;
import strategy.DrawSaving;
import strategy.FileManager;
import strategy.FileSerializable;
import strategy.ReadFileLog;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private Shape nowSelected, tempShape;

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	private Observable observable = new Observable();
	private Observer observerUpdateState;

	private Stack<Command> stackOfRedo = new Stack<Command>();
	private Stack<Command> stackOfUndo = new Stack<Command>();
	private PropertyChangeSupport propertyChangeSupport;
	private FileManager fileManager;
	private DefaultListModel<String> logList;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		observerUpdateState = new Observer(frame);
		observable.addPropertyChangeListener(observerUpdateState);
		propertyChangeSupport = new PropertyChangeSupport(this);
		logList = frame.getDlmList();

	}

	private MachineDrawing machine = new MachineDrawing(this);

	public void mouseClicked(MouseEvent event) {
		if (frame.getTglbtnSelect().isSelected()) {
			selectOrDeselectShape(event);

		}

		if (frame.getClick() == "point") {
			String cmdAdd = machine.pointDraw(event, frame, model);
			addCommandInLog(cmdAdd);
		} else if (frame.getClick() == "line") {
			String cmdAdd = machine.lineDraw(event, frame, model);
			addCommandInLog(cmdAdd);
		} else if (frame.getClick() == "circle") {
			String cmdAdd = machine.circleDraw(event, frame, model);
			addCommandInLog(cmdAdd);
		} else if (frame.getClick() == "rectangle") {
			String cmdAdd = machine.rectangleDraw(event, frame, model);
			addCommandInLog(cmdAdd);
		} else if (frame.getClick() == "donut") {
			String cmdAdd = machine.donutDraw(event, frame, model);
			addCommandInLog(cmdAdd);
		} else if (frame.getClick() == "hexagon") {
			String cmdAdd = machine.hexagonDraw(event, frame, model);
			addCommandInLog(cmdAdd);
		}

		frame.getView().repaint();

	}

	public void selectOrDeselectShape(MouseEvent event) {

		frame.setClick(null);
		nowSelected = null;
		tempShape = null;

		Iterator<Shape> itShapes = model.getShapes().iterator();

		while (itShapes.hasNext()) {

			tempShape = itShapes.next();
			if (tempShape.contains(event.getX(), event.getY())) {
				nowSelected = tempShape;
			}

		}
		if (nowSelected != null) {

			if (!nowSelected.isSelected()) {
				System.out.println("U selectu sam");
				CmdSelectShape cmdSelectShape = new CmdSelectShape(this, nowSelected);
				cmdSelectShape.execute();
				newCommandExecuted(cmdSelectShape);
				System.out.println(selectedShapes.size());
				addCommandInLog(cmdSelectShape.toString());

			} else {

				CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(this, nowSelected);
				cmdDeselectShape.execute();
				newCommandExecuted(cmdDeselectShape);
				System.out.println(selectedShapes.size());
				addCommandInLog(cmdDeselectShape.toString());

			}
		} else {
			ListIterator<Shape> iteratorSelectedShapes = selectedShapes.listIterator();
			while (iteratorSelectedShapes.hasNext()) {
				tempShape = iteratorSelectedShapes.next();
				CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(this, tempShape);
				tempShape.setSelected(false);
				newCommandExecuted(cmdDeselectShape);
				addCommandInLog(cmdDeselectShape.toString());
			}
			selectedShapes.clear();
		}
		activitybtnEditUpdate();
		frame.getView().repaint();
	}

	public void bringToFront() {

		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, selectedShapes.get(0));
		cmdBringToFront.execute();
		newCommandExecuted(cmdBringToFront);
		addCommandInLog(cmdBringToFront.toString());
		frame.getView().repaint();
		activityBtnFrontBack();

	}

	public void bringToBack() {

		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, selectedShapes.get(0));
		cmdBringToBack.execute();
		newCommandExecuted(cmdBringToBack);
		addCommandInLog(cmdBringToBack.toString());
		frame.getView().repaint();
		activityBtnFrontBack();

	}

	public void forward() {
		CmdForward cmdForward = new CmdForward(model, selectedShapes.get(0));
		cmdForward.execute();
		newCommandExecuted(cmdForward);
		addCommandInLog(cmdForward.toString());
		frame.getView().repaint();
		activityBtnFrontBack();

	}

	public void backward() {
		CmdBackward cmdBackward = new CmdBackward(model, selectedShapes.get(0));
		cmdBackward.execute();
		newCommandExecuted(cmdBackward);
		addCommandInLog(cmdBackward.toString());
		frame.getView().repaint();
		activityBtnFrontBack();
	}

	public void modifyShape() {

		if (selectedShapes.get(0) instanceof Point) {
			String cmdUpdate = machine.modifyPoint((Point) selectedShapes.get(0), frame, model);
			addCommandInLog(cmdUpdate);
		}

		else if (selectedShapes.get(0) instanceof Line) {
			String cmdUpdate = machine.modifyLine((Line) selectedShapes.get(0), frame, model);
			addCommandInLog(cmdUpdate);
		}

		else if (selectedShapes.get(0) instanceof Rectangle) {

			String cmdUpdate = machine.modifyRectangle((Rectangle) selectedShapes.get(0), frame, model);
			addCommandInLog(cmdUpdate);

		} else if (selectedShapes.get(0) instanceof Donut) {

			String cmdUpdate = machine.modifyDonut((Donut) selectedShapes.get(0), frame, model);
			addCommandInLog(cmdUpdate);

		} else if (selectedShapes.get(0) instanceof Circle) {

			String cmdUpdate = machine.modifyCircle((Circle) selectedShapes.get(0), frame, model);
			addCommandInLog(cmdUpdate);
		}

		else if (selectedShapes.get(0) instanceof HexagonAdapter) {
			String cmdUpdate = machine.modifyHexagon((HexagonAdapter) selectedShapes.get(0), frame, model);
			addCommandInLog(cmdUpdate);
		}

		frame.getView().repaint();

	}

	public void delete() {
		selectedShapes = machine.deleteShape(selectedShapes, frame, model);
		activitybtnEditUpdate();
		frame.getView().repaint();
	}

	public void addCommandInLog(String cmd) {
		if(cmd!=null) {
		logList.addElement(cmd);
		stackOfRedo.removeAllElements();
		}
	}

	private void activityBtnFrontBack() {

		ListIterator<Shape> itShapes = model.getShapes().listIterator();
		while (itShapes.hasNext()) {
			nowSelected = itShapes.next();
			if (nowSelected.isSelected()) {

				if (model.getShapes().size() != 1) {
					if (nowSelected.equals(model.get(0))) {

						observable.setBtnActivatedToFront(true);
						observable.setBtnActivatedForward(true);
						observable.setBtnActivatedToBack(false);
						observable.setBtnActivatedBackward(false);

					} else if (nowSelected.equals(model.get(model.getShapes().size() - 1))) {

						observable.setBtnActivatedToFront(false);
						observable.setBtnActivatedForward(false);
						observable.setBtnActivatedToBack(true);
						observable.setBtnActivatedBackward(true);

					} else {

						observable.setBtnActivatedToFront(true);
						observable.setBtnActivatedForward(true);
						observable.setBtnActivatedToBack(true);
						observable.setBtnActivatedBackward(true);

					}
				}
			}
		}

	}

	public void activitybtnEditUpdate() {
		if (selectedShapes.size() == 0) {
			observable.setModifyBtnActivated(false);
			observable.setDeleteBtnActivated(false);
			observable.setBtnActivatedToFront(false);
			observable.setBtnActivatedForward(false);
			observable.setBtnActivatedToBack(false);
			observable.setBtnActivatedBackward(false);

		} else {
			if (selectedShapes.size() == 1) {
				observable.setModifyBtnActivated(true);
				observable.setDeleteBtnActivated(true);
				activityBtnFrontBack();
			} else {
				observable.setModifyBtnActivated(false);
				observable.setDeleteBtnActivated(false);
				observable.setBtnActivatedToFront(false);
				observable.setBtnActivatedForward(false);
				observable.setBtnActivatedToBack(false);
				observable.setBtnActivatedBackward(false);
			}

		}
	}

	public void activitybtnUndoRedo() {
		if (!stackOfRedo.isEmpty() && !stackOfUndo.isEmpty()) {
			frame.getBtnRedo().setEnabled(true);
			frame.getBtnUndo().setEnabled(true);
		}

		else if (!stackOfUndo.isEmpty() && stackOfRedo.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
			frame.getBtnUndo().setEnabled(true);
		}

		else if (!stackOfRedo.isEmpty() && stackOfUndo.isEmpty()) {
			frame.getBtnRedo().setEnabled(true);
			frame.getBtnUndo().setEnabled(false);
		}

	}

	public void newCommandExecuted(Command command) {
		stackOfUndo.push(command);
		if (!stackOfUndo.isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(false);
		}
	}

	public void redo() {
		System.out.println("U redo sam");
		if (frame.getBtnRedo().isEnabled()) {
			System.out.println("U redo iffff sam");
			logList.addElement("Redo->" + stackOfRedo.peek().toString());
			if (!stackOfRedo.isEmpty()) {
				Command command = stackOfRedo.pop();
				command.execute();
				System.out.println(command.toString());
				stackOfUndo.push(command);
			}
			frame.getView().repaint();
			activitybtnUndoRedo();
			activitybtnEditUpdate();

		}

	}

	public void undo() {
		logList.addElement("Undo->" + stackOfUndo.peek().toString());
		if (!stackOfUndo.isEmpty()) {
			Command command = stackOfUndo.pop();
			command.unexecute();
			System.out.println(command.toString());
			stackOfRedo.push(command);
		}

		frame.getView().repaint();
		activitybtnUndoRedo();
		activitybtnEditUpdate();

	}

	public void execute(Command cmd) {
		cmd.execute();
		stackOfUndo.push(cmd);
		if (!stackOfRedo.isEmpty()) {
			stackOfRedo.removeAllElements();
			frame.getBtnRedo().setEnabled(false);
			propertyChangeSupport.firePropertyChange("turn off redo", false, true);
		}
		if (model.getAll().size() == 1) {
			propertyChangeSupport.firePropertyChange("file exist", false, true);
		} else if (model.getAll().isEmpty()) {
			propertyChangeSupport.firePropertyChange("file don't exist", false, true);
		}
		if (stackOfUndo.size() == 1) {
			propertyChangeSupport.firePropertyChange("draw is not empty", false, true);
		} else if (stackOfUndo.isEmpty()) {
			propertyChangeSupport.firePropertyChange("draw is empty", false, true);
		}

		frame.getView().repaint();
	}

	public int getIndexOfShape(Shape shape) {
		int listSize = model.getShapes().size() - 1;

		for (int i = 0; i <= listSize; i++) {
			if (model.getShapes().get(i).equals(shape)) {

				return i;
			}
		}
		return -1;
	}

	public void open() {
		JFileChooser chooser = new JFileChooser();
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			model.removeAll();
			logList.removeAllElements();
			stackOfRedo.clear();
			stackOfUndo.clear();
			frame.getView().repaint();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				fileManager = new FileManager(new FileSerializable(model));
				propertyChangeSupport.firePropertyChange("serialized draw opened", false, true);
			} else if (chooser.getFileFilter().getDescription().equals("Commands log")) {
				fileManager = new FileManager(new ReadFileLog(this, model, frame));
			}
			fileManager.open(chooser.getSelectedFile());
			frame.getView().repaint();
		}
		chooser.setVisible(false);
	}

	public void save() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save");
		chooser.setAcceptAllFileFilterUsed(false);
		if (!model.getAll().isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		}
		if (!stackOfUndo.isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		}

		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				fileManager = new FileManager(new FileSerializable(model));
			} else if (chooser.getFileFilter().getDescription().equals("Commands log")) {
				fileManager = new FileManager(new ReadFileLog(this, model, frame));
			} else {
				fileManager = new FileManager(new DrawSaving(frame));
			}
			fileManager.save(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public Stack<Command> getStackOfRedo() {
		return stackOfRedo;
	}

	public void setStackOfRedo(Stack<Command> stackOfRedo) {
		this.stackOfRedo = stackOfRedo;
	}

	public Stack<Command> getStackOfUndo() {
		return stackOfUndo;
	}

	public void setStackOfUndo(Stack<Command> stackOfUndo) {
		this.stackOfUndo = stackOfUndo;
	}

	public DefaultListModel<String> getLogList() {
		return logList;
	}

	public void setLogList(DefaultListModel<String> logList) {
		this.logList = logList;
	}

}
