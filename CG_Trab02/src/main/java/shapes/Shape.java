package shapes;

import color.Color;

/**
 * Interface que representa uma forma geom�trica.
 */
public interface Shape extends HasBoundingBox {

    /**
     * Atribui uma nova cor a esta forma.
     * 
     * @param color a cor que ser� atribu�da a esta forma.
     */
    void setColor(Color color);

    /**
     * Obtem a cor atual desta forma.
     * 
     * @return a cor atual desta forma.
     */
    Color color();

    /**
     * Indica se um determinado ponto esta dentro da area ocupada por esta forma geometrica.
     * 
     * @param point o ponto que se deseja saber se esta dentro.
     * @return <code>true</code> se o ponto esta dentro da area delimitada por esta forma geometrica, caso contrario <code>false</code>.
     */
    boolean contains(Point2D point);

}
