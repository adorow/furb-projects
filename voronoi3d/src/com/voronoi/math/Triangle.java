package com.voronoi.math;

public class Triangle {

    private Point3D a;
    private Point3D b;
    private Point3D c;

    public Triangle() {
        this(new Point3D(), new Point3D(), new Point3D());
    }

    public Triangle(Point3D a, Point3D b, Point3D c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(Point3D a) {
        this.a = a;
    }

    public Point3D getA() {
        return a;
    }

    public void setB(Point3D b) {
        this.b = b;
    }

    public Point3D getB() {
        return b;
    }

    public void setC(Point3D c) {
        this.c = c;
    }

    public Point3D getC() {
        return c;
    }

    
    public Point3D[] getPoints() {
        return new Point3D[] {a,b,c };
    }

}
