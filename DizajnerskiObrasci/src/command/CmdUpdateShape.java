package command;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class CmdUpdateShape implements Command {

	private Shape oldState;
	private Shape newState;
	private Shape original;

	public CmdUpdateShape(Shape oldS, Shape newS) {

		if (oldS instanceof Line) {
			this.oldState = (Line) oldS;
			this.newState = (Line) newS;
			this.original = new Line();

		} else if (oldS instanceof Donut) {
			this.oldState = (Donut) oldS;
			this.newState = (Donut) newS;
			this.original = new Donut();

		} else if (oldS instanceof Circle) {
			this.oldState = (Circle) oldS;
			this.newState = (Circle) newS;
			this.original = new Circle();
		} else if (oldS instanceof Rectangle) {
			this.oldState = (Rectangle) oldS;
			this.newState = (Rectangle) newS;
			this.original = new Rectangle();
		} else if (oldS instanceof Point) {
			this.oldState = (Point) oldS;
			this.newState = (Point) newS;
			this.original = new Point();
		} 
	}

	@Override
	public void execute() {
		original = oldState.clone(original);
		oldState = newState.clone(oldState);

	}

	@Override
	public void unexecute() {
		oldState = original.clone(oldState);

	}

	@Override
	public String toString() {
		return "Updated->" + original.toString() + "->" + newState.toString();
	}

}
