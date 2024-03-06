package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdForward implements Command {

	private DrawingModel model;
	private Shape shape;
	

	public CmdForward(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		

	}

	@Override
	public void execute() {
		System.out.println("ja sam tu usao ");

		if (model.getShapes().indexOf(shape) < model.getShapes().size() - 1) {
			Collections.swap(model.getShapes(), model.getShapes().indexOf(shape), model.getShapes().indexOf(shape) + 1);
		}
	}

	@Override
	public void unexecute() {
		System.out.println("tuu saaaam ispred");
		if (  model.getShapes().indexOf(shape) != 0) {
			System.out.println("tuu saaaam");
			Collections.swap(model.getShapes(),  model.getShapes().indexOf(shape),  model.getShapes().indexOf(shape) - 1);
		}
	}

	@Override
	public String toString() {
		return "To front->" + shape.toString();
	}
}
