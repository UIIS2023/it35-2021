package command;

import java.util.ArrayList;


import geometry.Shape;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command {

	private DrawingModel model;
	private Shape shape;
	private ArrayList<Shape> list;

	public CmdRemoveShape(DrawingModel model, Shape shape, ArrayList<Shape> list) {

		this.model = model;
		this.shape = shape;
		this.list=list;

	}

	@Override
	public void execute() {
		list.remove(shape);
		model.remove(shape);
	}

	@Override
	public void unexecute() {
		list.add(shape);
		model.add(shape);
	}

	@Override
	public String toString() {
		return "Removed shape->" + shape.toString();

	}
}
