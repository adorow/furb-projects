package shapes.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shapes.BoundingBox;
import shapes.Point2D;
import shapes.Polygon;

public abstract class DefaultPolygon extends DefaultShape implements Polygon {

    private List<Point2D> points = new ArrayList<Point2D>(8);

    @Override
    public Point2D addNewPoint() {
        Point2D newPoint = new Point2D();
        points.add(newPoint);
        return newPoint;
    }

    @Override
    public boolean removePoint(Point2D point) {
        return points.remove(point);
    }

    @Override
    public Point2D[] getPoints() {
        return points.toArray(new Point2D[0]);
    }

    @Override
    public boolean contains(Point2D point) {
        double yc = point.getY();
        double xc = point.getX();

        Point2D[] points = getPoints();
        int countLeftIntersects = 0;
        for (int i = 0; i < points.length; i++) {
            Point2D p1 = points[i];
            double y1 = p1.getY();
            double x1 = p1.getX();
            Point2D p2 = points[(i + 1) % points.length];
            double y2 = p2.getY();
            double x2 = p2.getX();

            double ti = (yc - y1) / (y2 - y1);
            // intersectou?
            if (ti >= 0 && ti <= 1) {
                double xi = x1 + (x2 - x1) * ti;
                // conta apenas os que estao a esquerda
                if (xi < xc) countLeftIntersects++;
            }
        }
        // e impar?
        return (countLeftIntersects & 1) == 1;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new PolygonBoundingBox();
    }

    private class PolygonBoundingBox extends DefaultBoundingBox implements BoundingBox {

        @Override
        public Point2D getTopLeft() {
            Iterator<Point2D> iter = points.iterator();
            if (!iter.hasNext()) {
                return new Point2D();// retorna o ponto 'zero', pois nao ha nenhum ponto aqui nesse poligono
            }

            Point2D point = iter.next();
            double topY = point.getY();
            double leftX = point.getX();

            while (iter.hasNext()) {
                point = iter.next();
                double x = point.getX();
                double y = point.getY();

                if (x < leftX) leftX = x;
                if (y < topY) topY = y;
            }

            return new Point2D(leftX, topY);
        }

        @Override
        public Point2D getBottomRight() {
            Iterator<Point2D> iter = points.iterator();
            if (!iter.hasNext()) {
                return new Point2D();// retorna o ponto 'zero', pois nao ha nenhum ponto aqui nesse poligono
            }

            Point2D point = iter.next();
            double bottomY = point.getY();
            double rightX = point.getX();

            while (iter.hasNext()) {
                point = iter.next();
                double x = point.getX();
                double y = point.getY();

                if (x > rightX) rightX = x;
                if (y > bottomY) bottomY = y;
            }

            return new Point2D(rightX, bottomY);
        }

    }

}
