package shapes.transform.impl;

import shapes.Point2D;
import shapes.Spline;

/**
 * Classe responsável por aplicar transformações em Splines.
 */
public class SplineTransformer extends DefaultShapeTransformer<Spline> {

    @Override
    public void translate(Spline shape, double x, double y) {
        prepareTranslation(x, y);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

    @Override
    public void rotate(Spline shape, double degrees) {
        prepareRotation(shape.getBoundingBox().getCenter(), degrees);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

    @Override
    public void scale(Spline shape, double scale) {
        prepareScale(shape.getBoundingBox().getCenter(), scale);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

}
