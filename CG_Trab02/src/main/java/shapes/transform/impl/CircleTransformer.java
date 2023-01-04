package shapes.transform.impl;

import shapes.Circle;

/**
 * Classe respons�vel por aplicar transforma��es em c�rculos.
 */
public class CircleTransformer extends DefaultShapeTransformer<Circle> {

    @Override
    public void translate(Circle shape, double x, double y) {
        prepareTranslation(x, y);
        transform(shape.getCenterPoint());
    }

    @Override
    public void rotate(Circle shape, double degrees) {
        // n�o altera nada com a rota��o
    }

    @Override
    public void scale(Circle shape, double scale) {
        shape.setRadius(shape.getRadius() * scale);
    }

}
