package com.math;

public class MathUtil {

	public static Point getMiddlePoint(Point a, Point b){
		double x = a.getX() + (b.getX() - a.getX());
		double y = a.getY() + (b.getY() - a.getY());
		return new Point(x, y);
	}
	
	public static double determinant3x3(double[][] determinant){
		double r1 =  determinant[0][0] * determinant[1][1] * determinant[2][2];
		double r2 =  determinant[0][1] * determinant[1][2] * determinant[2][0];
		double r3 =  determinant[0][2] * determinant[1][0] * determinant[2][1];
		double r4 =  determinant[0][0] * determinant[1][2] * determinant[2][1];
		double r5 =  determinant[0][1] * determinant[1][0] * determinant[2][2];
		double r6 =  determinant[0][2] * determinant[1][1] * determinant[2][0];
		return r1 + r2 + r3 - r4 - r5 - r6;
	}
	
}
