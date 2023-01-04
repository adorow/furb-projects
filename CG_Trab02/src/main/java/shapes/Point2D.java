package shapes;

/**
 * Representa��o de um ponto 2D.
 */
public class Point2D {

    private double x, y;

    /**
     * Cria um novo ponto com coordenadas (0,0).
     */
    public Point2D() {
        this(0.0, 0.0);
    }

    /**
     * Cria um novo ponto com coordenadas (x,y).
     * 
     * @param x o ponto X da coordenada.
     * @param y o ponto Y da coordenada.
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Atribui um novo X � coordenada.
     * 
     * @param x o novo ponto X.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Atribui um novo Y � coordenada.
     * 
     * @param y o novo ponto Y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Atribui novos pontos � coordenada.
     * 
     * @param x o novo ponto X.
     * @param y o novo ponto Y.
     */
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obt�m o ponto X da coordenada.
     * 
     * @return o ponto X da coordenada.
     */
    public double getX() {
        return x;
    }

    /**
     * Obt�m o ponto Y da coordenada.
     * 
     * @return o ponto Y da coordenada.
     */
    public double getY() {
        return y;
    }

}
