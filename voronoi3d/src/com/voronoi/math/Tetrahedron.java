package com.voronoi.math;

public class Tetrahedron {

    private Point3D a;
    private Point3D b;
    private Point3D c;
    private Point3D d;

    public Tetrahedron() {
        this(new Point3D(), new Point3D(), new Point3D(), new Point3D());
    }

    public Tetrahedron(Point3D a, Point3D b, Point3D c, Point3D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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

    public void setD(Point3D d) {
        this.d = d;
    }

    public Point3D getD() {
        return d;
    }

    public Triangle[] getTriangles() {
        return new Triangle[] { new Triangle(a, b, c), new Triangle(a, c, d), new Triangle(a, d, b), new Triangle(b, c, d) };
    }

    public Point3D[] getPoints() {
        return new Point3D[] { a, b, c, d };
    }
    
    public Point3D getCircumcenter(){
    	Triangle[] triangles = getTriangles();
    	Point3D[] trianglesCircumcenter = new Point3D[triangles.length];
    	
    	// Obtem os circuncentros dos triangulos
    	for (int i = 0; i < triangles.length; i++) {
    		trianglesCircumcenter[i] = triangles[i].getCircumcenter();
		}
    	
    	// TODO não sei ainda como seguir daqui...
    	return null;
    }
    

}
