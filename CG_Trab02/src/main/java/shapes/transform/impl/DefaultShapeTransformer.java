package shapes.transform.impl;

import shapes.Point2D;
import shapes.Shape;
import shapes.transform.ShapeTransformer;

public abstract class DefaultShapeTransformer<T extends Shape> implements ShapeTransformer<T> {

    private Transformation2DMatrixBuilder builder = new Transformation2DMatrixBuilder();
    
    protected void prepareTranslation(double dx, double dy) {
        builder.newTransformationMatrix();
        builder.addTranslation(dx, dy);
    }
    
    protected void prepareScale(Point2D center, double scale) {
        builder.newTransformationMatrix();
        builder.addTranslation(-center.getX(), -center.getY());
        builder.addScale(scale);
        builder.addTranslation(+center.getX(), +center.getY());
    }

    protected void prepareRotation(Point2D center, double degrees) {
        builder.newTransformationMatrix();
        builder.addTranslation(-center.getX(), -center.getY());
        builder.addRotation(degrees);
        builder.addTranslation(+center.getX(), +center.getY());
    }
    
    protected void transform(Point2D point) {
        builder.transform(point);
    }
    
}
