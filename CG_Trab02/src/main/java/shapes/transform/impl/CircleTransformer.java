package shapes.transform.impl;

import shapes.Circle;

/**
 * Classe responsável por aplicar transformações em círculos.
 */
public class CircleTransformer extends DefaultShapeTransformer<Circle> {

    @Override
    public void translate(Circle shape, double x, double y) {
        prepareTranslation(x, y);
        transform(shape.getCenterPoint());
    }

    @Override
    public void rotate(Circle shape, double degrees) {
        // não altera nada com a rotação
    }

    @Override
    public void scale(Circle shape, double scale) {
        shape.setRadius(shape.getRadius() * scale);
    }

}
