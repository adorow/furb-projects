package shapes.transform.impl;

import shapes.OpenPolygon;
import shapes.Point2D;

/**
 * Classe responsável por aplicar transformações em polígonos abertos.
 */
public class OpenPolygonTransformer extends DefaultShapeTransformer<OpenPolygon> {

    @Override
    public void translate(OpenPolygon shape, double x, double y) {
        prepareTranslation(x, y);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

    @Override
    public void rotate(OpenPolygon shape, double degrees) {
        prepareRotation(shape.getBoundingBox().getCenter(), degrees);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

    @Override
    public void scale(OpenPolygon shape, double scale) {
        prepareScale(shape.getBoundingBox().getCenter(), scale);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

}
