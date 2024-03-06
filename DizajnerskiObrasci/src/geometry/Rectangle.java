package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {

	private Point upperLeftPoint= new Point() ;
	private int width;
	private int height;
	
	
	public Rectangle() {
		
	}
	public Rectangle(Point upperLeftPoint, int width,int height)
	{
		this.upperLeftPoint=upperLeftPoint;
		this.width=width;
		this.height=height;
		
	}
	public Rectangle(Point upperLeftPoint, int width,int height,boolean selected)
	{
		this(upperLeftPoint, width,height);
		this.setSelected(selected);
	}
	public Rectangle(Point upperLeftPoint, int width,int height,boolean selected,Color color)
	{
		this(upperLeftPoint, width,height,selected);
		this.setColor(color);
	}
	public Rectangle(Point upperLeftPoint,int width, int height,boolean selected,Color color,Color innerColor)
	{
		this(upperLeftPoint,width,height,selected,color);
		this.setInnerColor(innerColor);
	}
	public Rectangle(int x,int y,int width,int height)
	{
		this.upperLeftPoint.setX(x);
		this.upperLeftPoint.setY(y);
		this.width=width;
		this.height=height;
	}
	
	
	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
		
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle)
		{
			return (int)(this.area()-((Rectangle)o).area());
		}
		return 0;
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawRect(upperLeftPoint.getX(),upperLeftPoint.getY(), width, height);
		this.fill(g);
		
		if(isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX()-3, this.upperLeftPoint.getY()-3,6,6);
			g.drawRect(this.upperLeftPoint.getX()+width-3, this.upperLeftPoint.getY()-3,6,6);
			g.drawRect(this.upperLeftPoint.getX()-3,this.upperLeftPoint.getY()+height-3,6,6);
			g.drawRect(this.upperLeftPoint.getX()+width-3, this.upperLeftPoint.getY()+height-3,6,6);
		}
		
	}
	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.getUpperLeftPoint().getY()+1, width-1, height-1);
	}
	
	public double area() {
		return height * width;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle prosledjeni = (Rectangle) obj;
			if (this.upperLeftPoint.equals(prosledjeni.getUpperLeftPoint()) && 
					this.width == prosledjeni.getWidth() && 
					this.height == prosledjeni.getHeight()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public boolean contains(int x,int y)
	{
		if(this.upperLeftPoint.getX()+this.width>=x && this.upperLeftPoint.getY()+this.height>=y 
				&& this.upperLeftPoint.getX()<=x && this.upperLeftPoint.getY()<=y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString() {
    	return "Rectangle: x=" + upperLeftPoint.getX() + "; y=" + upperLeftPoint.getY() + "; width=" + width + "; height=" + height + "; area color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInnerColor().toString().substring(14).replace('=', '-');
    }
	
	@Override
	public Rectangle clone(Shape rr) {
		Rectangle r= (Rectangle) rr;

		r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		r.setHeight(this.getHeight());
		r.setWidth(this.getWidth());
		r.setColor(this.getColor());
		r.setInnerColor(this.getInnerColor());
		return r;
	}
	
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	

	

	
}