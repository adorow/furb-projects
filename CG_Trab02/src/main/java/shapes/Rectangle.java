package shapes;

/**
 * Interface com a representação de um retângulo.
 */
public interface Rectangle extends Shape {

    /**
     * Obtém o ponto superior esquerdo do retângulo.
     * 
     * @return o ponto superior esquerdo do retângulo.
     */
    Point2D getTopLeft();

    /**
     * Obtém o ponto inferior direito do retângulo.
     * 
     * @return o ponto inferior direito do retângulo.
     */
    Point2D getBottomRight();

    /**
     * Obtém a altura do retângulo.
     * 
     * @return a altura do retângulo.
     */
    double height();

    /**
     * Obtém a largura do retângulo.
     * 
     * @return a largura do retângulo.
     */
    double width();

}
