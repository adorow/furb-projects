package com.voronoi.math;

public class MathUtil {

	/**
	 * Calcula a distância entre dois pontos
	 */
	public double distance(Point3D a, Point3D b){
		double x = Math.pow(a.getX() - b.getX(), 2);
		double y = Math.pow(a.getY() - b.getY(), 2);
		double z = Math.pow(a.getZ() - b.getZ(), 2);
		return Math.sqrt(x + y + z);
	}
	
	/**
	 * Obtém o ponto no central da reta AB.
	 */
	public Point3D midpoint(Point3D a, Point3D b){
		double x = a.getX() + ((b.getX() - a.getX()) / 2);
		double y = a.getY() + ((b.getY() - a.getY()) / 2);
		double z = a.getZ() + ((b.getZ() - a.getZ()) / 2);
		return new Point3D(x, y, z);
	}
	
	/**
	 * 
	 */
	public 
	
}
