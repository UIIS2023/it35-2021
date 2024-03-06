package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.Shape;
import geometry.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape {

	private Hexagon hexagon;
	private Point center= new Point();
	private int radius;

	public HexagonAdapter() {

	}

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Point center, boolean selected, int radius, Color innerColor, Color outerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(outerColor);
		this.hexagon.setSelected(selected);
	}

	public HexagonAdapter(Point center, int r, Color innerColor, Color outerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), r);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(outerColor);
	}

	public HexagonAdapter(Point startPoint, int radius) {

		this.hexagon = new Hexagon(startPoint.getX(), startPoint.getY(), radius);

	}
	
	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof Hexagon) {
			Hexagon hexagonNew = (Hexagon) arg0;
			return (int) (hexagon.getR() - hexagonNew.getR());

		} else {
			return 0;
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);

	}

	

	@Override
	public void fill(Graphics g) {

	}
	
	@Override
	public boolean contains(int x, int y) {

		return hexagon.doesContain(x, y);
	}
	
	@Override
	public void draw(Graphics g) {

		hexagon.paint(g);
	}

	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter hexAdapter = (HexagonAdapter) obj;
			Point p1 = new Point(hexagon.getX(), hexagon.getY());
			Point p2 = new Point(hexAdapter.hexagon.getX(), hexAdapter.hexagon.getY());
			if (p1.equals(p2) && hexagon.getR() == hexAdapter.getHexagon().getR())
				return true;
			else
				return false;

		} else
			return false;
	}


	public double area() {
		return (((3 * Math.sqrt(3)) / 2) * this.hexagon.getR() * this.hexagon.getR());

	}

	

	@Override
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY()
				+ "; border color=" + getOutsideColor().toString().substring(14).replace('=', '-') + "; inner color="
				+ getInsideColor().toString().substring(14).replace('=', '-');
	}
	
	

	public HexagonAdapter clone() {
		HexagonAdapter h = new HexagonAdapter(new Point(-1, -1), -1, Color.black, Color.black);

		h.getHexagon().setX(this.getHexagon().getX());
		h.getHexagon().setY(this.getHexagon().getY());
		h.getHexagon().setR(this.getHexagon().getR());
		h.getHexagon().setBorderColor(this.getHexagon().getBorderColor());
		h.getHexagon().setAreaColor(this.getHexagon().getAreaColor());

		return h;
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Point center, int r, Color insideColor, Color outsideColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), r);
		this.hexagon.setAreaColor(insideColor);
		this.hexagon.setBorderColor(outsideColor);
		hexagon.setSelected(true);
	}
	
	
	
	public Color getInsideColor() {
		return hexagon.getAreaColor();
	}

	public void setInsideColor(Color insideColor) {
		hexagon.setAreaColor(insideColor);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	public Point getCenter() {
		return new Point(hexagon.getX(), hexagon.getY());
	}

	public void setCenter(Point center) {
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
	}

	

	public Color getOutsideColor() {
		return hexagon.getBorderColor();
	}

	public void setOutsideColor(Color outsideColor) {
		hexagon.setBorderColor(outsideColor);
	}
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();

	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	@Override
	public Shape clone(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}