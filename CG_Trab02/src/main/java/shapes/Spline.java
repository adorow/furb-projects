package shapes;

/**
 * Interface com a representa��o da Spline.
 */
public interface Spline extends Shape {

    /**
     * Obt�m os pontos da Spline (sempre ser�o 4).
     * 
     * @return os 4 pontos da Spline.
     */
    Point2D[] getPoints();

    /**
     * Obt�m um ponto espec�fico da Spline.
     * 
     * @param index o �ndice onde se encontra o ponto, iniciando em 0.
     * @return o ponto no �ndice especificado.
     */
    Point2D getPoint(int index);

}
