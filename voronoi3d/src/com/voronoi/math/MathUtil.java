package com.voronoi.math;

public class MathUtil {

    private MathUtil() {
        // 'private' para que não se crie instancias deste 
    }
    
	/**
	 * Calcula a distância entre dois pontos
	 */
	public static double distance(Point3D a, Point3D b){
		double x = Math.pow(a.getX() - b.getX(), 2);
		double y = Math.pow(a.getY() - b.getY(), 2);
		double z = Math.pow(a.getZ() - b.getZ(), 2);
		return Math.sqrt(x + y + z);
	}
	
	/**
	 * Obtém o ponto no central da reta AB.
	 */
	public static Point3D midpoint(Point3D a, Point3D b){
		double x = a.getX() + ((b.getX() - a.getX()) / 2);
		double y = a.getY() + ((b.getY() - a.getY()) / 2);
		double z = a.getZ() + ((b.getZ() - a.getZ()) / 2);
		return new Point3D(x, y, z);
	}
	
	/**
     * Calcula o ponto que possui a mesma distância para com todos os outros.
     * 
     * @param points os pontos de onde se baseará o cálculo.
     * @return o ponto equidistante a todos os informados.
     * @deprecated NÃO SEI SE FUNCIONA. tem que fazer direito.
     */
	public static Point3D getCircuncenter(Tetrahedron tetra) {
	    Triangle[] triangles = tetra.getTriangles();
	    int len = triangles.length;
	    Point3D[] circuncenters = new Point3D[len];
	    
	    // obtem o circuncentro de cada triangulo
	    for (int i = 0; i < len; i++) {
            circuncenters[i] = getCircuncenter(triangles[i]);
        }
	    
	    // o circuncentro dos circuncentros é o centro do tetraedro
	    return getCircuncenter(circuncenters);
	}
	
	/**
	 * Calcula o ponto que possui a mesma distância para com todos os outros.
	 * 
	 * @param points os pontos de onde se baseará o cálculo.
	 * @return o ponto equidistante a todos os informados.
	 * @deprecated não funciona. tem que fazer direito.
	 */
	public static Point3D getCircuncenter(Triangle t) {
	    return getCircuncenter(t.getPoints());
	}
	
	/**
	 * Calcula o ponto que possui a mesma distância para com todos os outros.
	 * 
	 * @param points os pontos de onde se baseará o cálculo.
	 * @return o ponto equidistante a todos os informados.
	 * @deprecated não funciona. tem que fazer direito.
	 */
	public static Point3D getCircuncenter(Point3D... points) {
	    int nums = points.length;
	    double x = 0, y = 0, z = 0;
	    
	    for (int i = 0; i < nums; i++) {
            x += points[i].getX();
            y += points[i].getY();
            z += points[i].getZ();
        }
	    
	    return new Point3D(x/nums, y/nums, z/nums);
	}
	
}
