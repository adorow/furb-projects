package shapes.transform;

import shapes.Point2D;
import shapes.Shape;
import shapes.transform.impl.Transformation2DMatrixBuilder;

/**
 * Classe de auxílio a aplicação de transformações em formas geométricas.
 */
public class TransformationHelper {

    /**
     * Aplica translação a um determinado ponto.
     * 
     * @param point o ponto a ser transformado.
     * @param translateX o deslocamento em X a ser aplicado.
     * @param translateY o deslocamento em Y a ser aplicado.
     */
    public static void translateSinglePoint(Point2D point, double translateX, double translateY) {
        Transformation2DMatrixBuilder builder = new Transformation2DMatrixBuilder();
        builder.newTransformationMatrix();
        builder.addTranslation(translateX, translateY);
        builder.transform(point);
    }

    /**
     * Aplica translação a uma determinada forma.
     * 
     * @param shape a forma a ser transformada.
     * @param translateX o deslocamento em X a ser aplicado.
     * @param translateY o deslocamento em Y a ser aplicado.
     */
    public static <S extends Shape> void translate(S shape, double translateX, double translateY) {
        ShapeTransformer<S> transformer = (ShapeTransformer<S>) ShapeTransformerFactory.getTransformer(shape.getClass());
        if (transformer != null) {
            transformer.translate(shape, translateX, translateY);
        }
    }

    /**
     * Aplica rotação a uma determinada forma.
     * 
     * @param shape a forma a ser transformada.
     * @param degrees a quantidade de graus de rotação.
     */
    public static <S extends Shape> void rotate(S shape, double degrees) {
        ShapeTransformer<S> transformer = (ShapeTransformer<S>) ShapeTransformerFactory.getTransformer(shape.getClass());
        if (transformer != null) {
            transformer.rotate(shape, degrees);
        }
    }

    /**
     * Aplica escala a uma determinada forma.
     * 
     * @param shape a forma a ser transformada.
     * @param scale a escala a ser aplicada na forma.
     */
    public static <S extends Shape> void scale(S shape, double scale) {
        ShapeTransformer<S> transformer = (ShapeTransformer<S>) ShapeTransformerFactory.getTransformer(shape.getClass());
        if (transformer != null) {
            transformer.scale(shape, scale);
        }
    }

}
