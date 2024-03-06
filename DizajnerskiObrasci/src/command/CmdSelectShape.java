package command;

import geometry.Shape;
import mvc.DrawingController;

public class CmdSelectShape implements Command {

	private Shape shape;
	private DrawingController controller;

	public CmdSelectShape(DrawingController controller, Shape shape) {
		this.shape = shape;
		this.controller = controller;
	}

	@Override
	public void execute() {

		shape.setSelected(true);
		controller.getSelectedShapes().add(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		controller.getSelectedShapes().remove(shape);

	}

	@Override
	public String toString() {
		return "Selected->" + shape.toString();
	}
}
