package shapes.transform.impl;

import shapes.Point2D;
import math.Matrix;

/**
 * Classe auxiliar para efetuar c�lculos de transforma��es com matrizes.
 */
public class Transformation2DMatrixBuilder {

    private Matrix tMatrix;

    /**
     * Cria uma nova matriz de transforma��o.
     */
    public void newTransformationMatrix() {
        tMatrix = newDefaultMatrix();
    }

    /**
     * Adiciona uma transla��o ao c�lculo.
     * 
     * @param dx o deslocamento em X.
     * @param dy o deslocamento em Y.
     */
    public void addTranslation(double dx, double dy) {
        Matrix temp = newDefaultMatrix();

        // deslocamento de x e y
        temp.set(2, 0, dx);
        temp.set(2, 1, dy);

        tMatrix = tMatrix.multiply(temp);
    }

    /**
     * Adiciona uma escala ao c�lculo.
     * 
     * @param scale a escala utilizada.
     */
    public void addScale(double scale) {
        Matrix temp = newDefaultMatrix();

        // escala de x e y
        temp.set(0, 0, scale);
        temp.set(1, 1, scale);

        tMatrix = tMatrix.multiply(temp);
    }

    /**
     * Adiciona uma rota��o ao c�lculo.
     * 
     * @param degrees a quantiade de graus da rota��o.
     */
    public void addRotation(double degrees) {
        Matrix temp = newDefaultMatrix();

        double radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        temp.set(0, 0, cos);
        temp.set(0, 1, -sin);
        temp.set(1, 0, sin);
        temp.set(1, 1, cos);

        tMatrix = tMatrix.multiply(temp);
    }

    /**
     * Aplica em um ponto 2D a transforma��o que foi preparada.
     * 
     * @param point o ponto onde ser� aplicada a transforma��o.
     */
    public void transform(Point2D point) {
        Matrix temp = new Matrix(1, 3);
        temp.set(0, 0, point.getX());
        temp.set(0, 1, point.getY());
        temp.set(0, 2, 1);

        Matrix resultVector = temp.multiply(tMatrix);
        point.setX(resultVector.get(0, 0));
        point.setY(resultVector.get(0, 1));
    }

    private Matrix newDefaultMatrix() {
        Matrix m = new Matrix(3, 3);
        m.makeIdentity();
        return m;
    }

}
