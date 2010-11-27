package com.math;

import java.awt.geom.Point2D;

public class Line {

	private double a;
	private double b;
	
	public Line(Point p1, Point p2){
		a = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		b = p1.getY() - ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX())) * p1.getX();
	}
	
	private Line(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	public Line rotate90(){
		return new Line(-a, b); 
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public boolean containsPoint(Point p){
		return a * p.getX() + b - p.getY() == 0;
	}
	
	public static void main(String[] args) {
		Line line = new Line(new Point(2, 2), new Point(4, 4));
		System.out.println(line.getA());
		System.out.println(line.getB());
		System.out.println(line.containsPoint(new Point(2, 2)));
		System.out.println(line.containsPoint(new Point(4, 4)));
		
		Line line2 = line.rotate90();
		System.out.println(line2.getA());
		System.out.println(line2.getB());
		System.out.println(line2.containsPoint(new Point(2, 2)));
		System.out.println(line2.containsPoint(new Point(4, 4)));
		System.out.println(line2.containsPoint(new Point(-2, 2)));
		System.out.println(line2.containsPoint(new Point(-4, 4)));
		System.out.println(line2.containsPoint(new Point(2, -2)));
		System.out.println(line2.containsPoint(new Point(4, -4)));
	}
	
}
