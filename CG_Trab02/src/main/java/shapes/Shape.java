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
     * Obt�m a cor atual desta forma.
     * 
     * @return a cor atual desta forma.
     */
    Color color();

    /**
     * Indica se um determinado ponto est� dentro da �rea ocupada por esta forma geom�trica.
     * 
     * @param point o ponto que se deseja saber se est� dentro.
     * @return <code>true</code> se o ponto est� dentro da �rea delimitada por esta forma geom�trica, caso contr�rio <code>false</code>.
     */
    boolean contains(Point2D point);

}
