package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBackward implements Command {

	private DrawingModel model;
	private Shape shape;

	public CmdBackward(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;

	}

	@Override
	public void execute() {

		if (model.getShapes().indexOf(shape) != 0) {
			Collections.swap(model.getShapes(), model.getShapes().indexOf(shape), model.getShapes().indexOf(shape) - 1);
		}
	}

	@Override
	public void unexecute() {

		if (model.getShapes().indexOf(shape) < model.getShapes().size() - 1) {
			Collections.swap(model.getShapes(), model.getShapes().indexOf(shape), model.getShapes().indexOf(shape) + 1);
		}
	}

	@Override
	public String toString() {
		return "To back->" + shape.toString();
	}

}
