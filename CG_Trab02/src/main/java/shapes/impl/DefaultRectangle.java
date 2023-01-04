package shapes.impl;

import shapes.Point2D;
import shapes.Rectangle;

/**
 * Implementação padrão para retângulos.
 */
public abstract class DefaultRectangle extends DefaultShape implements Rectangle {

    @Override
    public final boolean contains(Point2D point) {
        Point2D topLeft = getTopLeft();
        Point2D bottomRight = getBottomRight();
        double x = point.getX();
        double y = point.getY();
        if (x < topLeft.getX() || x > bottomRight.getX()) return false;
        if (y < topLeft.getY() || y > bottomRight.getY()) return false;
        // dentro da BoundingBox
        return true;
    }

    @Override
    public double height() {
        return Math.abs(getTopLeft().getY() - getBottomRight().getY());
    }

    @Override
    public double width() {
        return Math.abs(getTopLeft().getX() - getBottomRight().getX());
    }

}
