package shapes;

/**
 * Representa��o de um pol�gono simples.
 */
public interface Polygon extends Shape {

    /**
     * Obt�m os pontos deste pol�gono.
     * 
     * @return os pontos deste pol�gono.
     */
    Point2D[] getPoints();

    /**
     * Cria um novo ponto para este pol�gono.
     * 
     * @return o ponto criado.
     */
    Point2D addNewPoint();

    /**
     * Remove um ponto deste pol�gono.
     * 
     * @param point o ponto a ser removido.
     */
    boolean removePoint(Point2D point);

}
