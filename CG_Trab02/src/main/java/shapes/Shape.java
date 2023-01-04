package shapes;

import color.Color;

/**
 * Interface que representa uma forma geométrica.
 */
public interface Shape extends HasBoundingBox {

    /**
     * Atribui uma nova cor a esta forma.
     * 
     * @param color a cor que será atribuída a esta forma.
     */
    void setColor(Color color);

    /**
     * Obtém a cor atual desta forma.
     * 
     * @return a cor atual desta forma.
     */
    Color color();

    /**
     * Indica se um determinado ponto está dentro da área ocupada por esta forma geométrica.
     * 
     * @param point o ponto que se deseja saber se está dentro.
     * @return <code>true</code> se o ponto está dentro da área delimitada por esta forma geométrica, caso contrário <code>false</code>.
     */
    boolean contains(Point2D point);

}
