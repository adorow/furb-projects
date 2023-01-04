package math;

import shapes.Point2D;

/**
 * Classe utilitaria para formulas geom�tricas.
 */
public class GeometryMath {

    /**
     * Calcula a distancia entre 2 pontos.
     */
    public static double distance(Point2D a, Point2D b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

}
