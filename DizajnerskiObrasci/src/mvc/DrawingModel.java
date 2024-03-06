package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public void add(Shape shape) {
		shapes.add(shape);
	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public Shape get(int i) {
		return shapes.get(i);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public ArrayList<Shape> getAll() {
		return (ArrayList<Shape>) shapes;
	}

	public void addMultiple(ArrayList<Shape> arrayList) {
		this.shapes.addAll(arrayList);
	}

	public void removeAll() {
		shapes.clear();
	}

	public Shape getByIndex(int index) {
		return shapes.get(index);
	}
	
	

}
