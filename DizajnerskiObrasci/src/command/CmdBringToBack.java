package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBringToBack implements Command {

	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;

	public CmdBringToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		indexOfShape = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		for (int i = indexOfShape; i > 0; i--) {
			Collections.swap(model.getShapes(), i, i - 1);
		}
	}

	@Override
	public void unexecute() {
		for (int i = 0; i < indexOfShape; i++) {
			Collections.swap(model.getShapes(), i, i + 1);
		}
	}

	@Override
	public String toString() {
		return "Bringed to back->" + shape.toString();
	}


}
