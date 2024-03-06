package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBringToFront implements Command {

	private DrawingModel model;
	private Shape shape;
	private int indexShape;

	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		indexShape = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		for (int i = indexShape; i < model.getShapes().size() - 1; i++) {
			Collections.swap(model.getShapes(), i, i + 1);
		}
	}

	@Override
	public void unexecute() {
		for (int i = model.getShapes().size() - 1; i > indexShape; i--) {
			Collections.swap(model.getShapes(), i, i - 1);
		}
	}

	@Override
	public String toString() {
		return "Bringed to front->" + shape.toString();
	}

}
