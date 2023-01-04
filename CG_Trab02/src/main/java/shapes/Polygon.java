package shapes;

/**
 * Representação de um polígono simples.
 */
public interface Polygon extends Shape {

    /**
     * Obtém os pontos deste polígono.
     * 
     * @return os pontos deste polígono.
     */
    Point2D[] getPoints();

    /**
     * Cria um novo ponto para este polígono.
     * 
     * @return o ponto criado.
     */
    Point2D addNewPoint();

    /**
     * Remove um ponto deste polígono.
     * 
     * @param point o ponto a ser removido.
     */
    boolean removePoint(Point2D point);

}
