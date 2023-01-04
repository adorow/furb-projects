package shapes;

/**
 * Interface com a representação da Spline.
 */
public interface Spline extends Shape {

    /**
     * Obtém os pontos da Spline (sempre serão 4).
     * 
     * @return os 4 pontos da Spline.
     */
    Point2D[] getPoints();

    /**
     * Obtém um ponto específico da Spline.
     * 
     * @param index o índice onde se encontra o ponto, iniciando em 0.
     * @return o ponto no índice especificado.
     */
    Point2D getPoint(int index);

}
