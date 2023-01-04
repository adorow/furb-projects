package shapes;

/**
 * Interface que representa um círculo.
 */
public interface Circle extends Shape {

    /**
     * Obtém o ponto central deste círculo.
     * 
     * @return o ponto central deste círculo.
     */
    Point2D getCenterPoint();

    /**
     * Obtém o raio deste círculo.
     * 
     * @return o raio deste círculo.
     */
    double getRadius();

    /**
     * Atribui um novo raio a este círculo.
     * 
     * @param radius o novo raio deste círculo.
     */
    void setRadius(double radius);

}
