package com.voronoi.math;

/**
 * Representa um plano contido na dimensão 3D.
 * @author Felipe
 */
public class Plane {

	private double a;
	private double b;
	private double c;
	private double d;

	public Plane() {
		this(new Point3D(), new Point3D(), new Point3D());
	}

	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		//A = y1 (z2 - z3) + y2 (z3 - z1) + y3 (z1 - z2) 
		a = p1.getY() * (p2.getZ() - p3.getZ()) + 
			p2.getY() * (p3.getZ() - p1.getZ()) +
			p3.getY() * (p1.getZ() - p2.getZ());
		//B = z1 (x2 - x3) + z2 (x3 - x1) + z3 (x1 - x2)
		b = p1.getZ() * (p2.getX() - p3.getX()) +
			p2.getZ() * (p3.getX() - p1.getX()) +
			p3.getZ() * (p1.getX() - p2.getX());
		//C = x1 (y2 - y3) + x2 (y3 - y1) + x3 (y1 - y2)
		c = p1.getX() * (p2.getY() - p3.getY()) +
			p2.getX() * (p3.getY() - p1.getY()) +
			p3.getX() * (p1.getY() - p2.getY());
		//- D = x1 (y2 z3 - y3 z2) + x2 (y3 z1 - y1 z3) + x3 (y1 z2 - y2 z1)
		d = -(p1.getX() * ((p2.getY() * p3.getZ()) - (p3.getY() * p2.getZ())) +
			p2.getX() * ((p3.getY() * p1.getZ()) - (p1.getY() * p3.getZ())) +
			p3.getX() * ((p1.getY() * p2.getZ()) - (p2.getY() * p1.getZ())));
	}
	
	/**
	 * Retorna se o ponto está no plano.
	 * O ponto está no plano se e somente se, Ax + By + Cz + D == 0.
	 */
	public boolean isInPlane(Point3D p){
		return ((a * p.getX()) + (b * p.getY()) + (c * p.getZ()) + (d)) == 0;
	}
	
}
