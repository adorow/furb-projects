package shapes;

/**
 * Interface que representa um c�rculo.
 */
public interface Circle extends Shape {

    /**
     * Obt�m o ponto central deste c�rculo.
     * 
     * @return o ponto central deste c�rculo.
     */
    Point2D getCenterPoint();

    /**
     * Obt�m o raio deste c�rculo.
     * 
     * @return o raio deste c�rculo.
     */
    double getRadius();

    /**
     * Atribui um novo raio a este c�rculo.
     * 
     * @param radius o novo raio deste c�rculo.
     */
    void setRadius(double radius);

}
