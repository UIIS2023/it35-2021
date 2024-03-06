package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		super(center, radius);
		this.innerRadius = innerRadius;
		setColor(color);
		setInnerColor(innerColor);

	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}

	public void draw(Graphics g) {

		Ellipse2D bigCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		Ellipse2D smallCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		Area bigArea = new Area(bigCircle);
		bigArea.subtract(new Area(smallCircle));

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getInnerColor());
		g2d.fill(bigArea);
		g2d.setColor(getColor());
		g2d.draw(bigArea);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(super.getCenter().getX() - 3, super.getCenter().getY() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() - radius - 3, super.getCenter().getY() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() + radius - 3, super.getCenter().getY() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() - 3, super.getCenter().getY() - radius - 3, 6, 6);
			g.drawRect(super.getCenter().getX() - 3, super.getCenter().getY() + radius - 3, 6, 6);
		}
	}

	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2,
				this.innerRadius * 2);
	}

	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius()
					&& this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	

	@Override
	public Donut clone(Shape cc) {
		Donut donut =  (Donut)cc;

		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		try {
			donut.setRadius(this.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		donut.setInnerRadius(this.getInnerRadius());
		System.out.println(this.getInnerRadius()+"  iz klonaaaa");
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());

		return donut;
	}

	

	public String toString() {
		return "Donut: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color="
				+ getColor().toString().substring(14).replace('=', '-') + "; area color="
				+ getInnerColor().toString().substring(14).replace('=', '-') + "; inner radius=" + innerRadius;

	}

	public int getInnerRadius() {
		return this.innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

}