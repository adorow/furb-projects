package shapes.transform.impl;

import shapes.ClosedPolygon;
import shapes.Point2D;

/**
 * Classe responsável por aplicar transformações em polígonos fechados.
 */
public class ClosedPolygonTransformer extends DefaultShapeTransformer<ClosedPolygon> {

    @Override
    public void translate(ClosedPolygon shape, double x, double y) {
        prepareTranslation(x, y);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

    @Override
    public void rotate(ClosedPolygon shape, double degrees) {
        prepareRotation(shape.getBoundingBox().getCenter(), degrees);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

    @Override
    public void scale(ClosedPolygon shape, double scale) {
        prepareScale(shape.getBoundingBox().getCenter(), scale);
        for (Point2D point : shape.getPoints()) {
            transform(point);
        }
    }

}
