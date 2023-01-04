package shapes.impl;

import shapes.BoundingBox;
import shapes.Circle;
import shapes.Point2D;

public class CircleImpl extends DefaultShape implements Circle {

    private Point2D center = new Point2D();
    private double radius = 0.0;

    @Override
    public Point2D getCenterPoint() {
        return center;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean contains(Point2D point) {
        double distance = Math.sqrt(Math.pow(center.getX() - point.getX(), 2) + Math.pow(center.getY() - point.getY(), 2));
        return distance <= radius;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new CircleBoundingBox();
    }

    private class CircleBoundingBox extends DefaultBoundingBox implements BoundingBox {

        @Override
        public Point2D getTopLeft() {
            return new Point2D(center.getX() - radius, center.getY() - radius);
        }

        @Override
        public Point2D getBottomRight() {
            return new Point2D(center.getX() + radius, center.getY() + radius);
        }

    }

}
