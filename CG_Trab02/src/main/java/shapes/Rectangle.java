package shapes;

/**
 * Interface com a representa��o de um ret�ngulo.
 */
public interface Rectangle extends Shape {

    /**
     * Obt�m o ponto superior esquerdo do ret�ngulo.
     * 
     * @return o ponto superior esquerdo do ret�ngulo.
     */
    Point2D getTopLeft();

    /**
     * Obt�m o ponto inferior direito do ret�ngulo.
     * 
     * @return o ponto inferior direito do ret�ngulo.
     */
    Point2D getBottomRight();

    /**
     * Obt�m a altura do ret�ngulo.
     * 
     * @return a altura do ret�ngulo.
     */
    double height();

    /**
     * Obt�m a largura do ret�ngulo.
     * 
     * @return a largura do ret�ngulo.
     */
    double width();

}
