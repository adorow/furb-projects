package shapes;

/**
 * Interface com a representacao da Spline.
 */
public interface Spline extends Shape {

    /**
     * Obtem os pontos da Spline (sempre serao 4).
     * 
     * @return os 4 pontos da Spline.
     */
    Point2D[] getPoints();

    /**
     * Obtem um ponto especifico da Spline.
     * 
     * @param index o indice onde se encontra o ponto, iniciando em 0.
     * @return o ponto no indice especificado.
     */
    Point2D getPoint(int index);

}
