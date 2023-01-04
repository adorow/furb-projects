package shapes.transform;

import shapes.Shape;

/**
 * Interface contendo a definição de transformação de formas.
 * 
 * @param <T> o tipo de forma a ser transformada.
 */
public interface ShapeTransformer<T extends Shape> {

    /**
     * Aplica translação a uma determinada forma.
     * 
     * @param shape a forma a ser transformada.
     * @param dx o deslocamento em X a ser aplicado.
     * @param dy o deslocamento em Y a ser aplicado.
     */
    void translate(T shape, double dx, double dy);

    /**
     * Aplica rotação a uma determinada forma.
     * 
     * @param shape a forma a ser transformada.
     * @param degrees a quantidade de graus de rotação.
     */
    void rotate(T shape, double degrees);

    /**
     * Aplica escala a uma determinada forma.
     * 
     * @param shape a forma a ser transformada.
     * @param scale a escala a ser aplicada na forma.
     */
    void scale(T shape, double scale);

}
