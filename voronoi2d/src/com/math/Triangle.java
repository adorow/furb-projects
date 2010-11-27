package com.math;

public class Triangle {

	private Point a;
	private Point b;
	private Point c;
	
	public Triangle(){
		this(new Point(), new Point(), new Point());
	}
	
	public Triangle(Point a, Point b, Point c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public Point getC() {
		return c;
	}

	public void setC(Point c) {
		this.c = c;
	}
	
	/**
	 * Calcula o circumcentro do triângulo a partir de seus 3 pontos. 
	 * @return circucenter
	 */
	public Point getCircumcenter(){
		// Determinante de A
		double[][] detA = new double[][]{ {a.getX(), a.getY(), 1},
										  {b.getX(), b.getY(), 1},
										  {c.getX(), c.getY(), 1}};
		double A = MathUtil.determinant3x3(detA);
		
		// Determinante de Bx
		double[][] detBx = new double[][]{ {Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2), a.getY(), 1},
										   {Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2), b.getY(), 1},
										   {Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2), c.getY(), 1}};
		double Bx = - MathUtil.determinant3x3(detBx);
		
		// Determinante de By
		double[][] detBy = new double[][]{ {Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2), a.getX(), 1},
				   						   {Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2), b.getX(), 1},
				   						   {Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2), c.getX(), 1}};
		double By = MathUtil.determinant3x3(detBy);

		// Obtém o valor do x
		double x = - (Bx/(2*A));
		// Obtém o valor do y
		double y = - (By/(2*A));
		
		return new Point(x, y);
	}
	
	public static void main(String[] args) {
		Triangle tr = new Triangle(new Point(1, 0), new Point(-3, 0), new Point(-1, 2));
		Point circumcenter = tr.getCircumcenter();
		System.out.println("X = " + circumcenter.getX() + ", Y = " + circumcenter.getY());
	}
	
}
