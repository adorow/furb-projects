package shapes.transform;

import java.util.HashMap;
import java.util.Map;

import shapes.Circle;
import shapes.ClosedPolygon;
import shapes.OpenPolygon;
import shapes.Shape;
import shapes.Spline;
import shapes.transform.impl.CircleTransformer;
import shapes.transform.impl.ClosedPolygonTransformer;
import shapes.transform.impl.OpenPolygonTransformer;
import shapes.transform.impl.SplineTransformer;

/**
 * F�brica para buscar a classe respons�vel por aplicar transforma��es a um determinado tipo de forma geom�trica.
 */
public final class ShapeTransformerFactory {

    private static final Map<Class, ShapeTransformer> MAP;

    static {
        MAP = new HashMap<Class, ShapeTransformer>();
        MAP.put(Circle.class, new CircleTransformer());
        MAP.put(Spline.class, new SplineTransformer());
        MAP.put(OpenPolygon.class, new OpenPolygonTransformer());
        MAP.put(ClosedPolygon.class, new ClosedPolygonTransformer());
    }

    /**
     * Busca a classe respons�vel por aplicar transforma��es a uma determinada forma geom�trica.
     * 
     * @param <T> o formato a ser retornado.
     * @param clazz a classe do formato informado.
     * @return a classe que � capaz de aplicar transforma��es a forma informada.
     */
    public static <T extends Shape> ShapeTransformer<T> getTransformer(Class<T> clazz) {
        return get(clazz);
    }

    private static <T extends Shape> ShapeTransformer<T> get(Class<T> clazz) {
        ShapeTransformer<T> transformer = MAP.get(clazz);
        if (transformer != null) {
            return transformer;
        }
        for (Class<?> oClazz : clazz.getInterfaces()) {
            if (Shape.class.isAssignableFrom(oClazz)) {
                transformer = get((Class<T>) oClazz);
                if (transformer != null) {
                    // adiciona no mapa para agilizar as pr�ximas buscas de Transformers
                    MAP.put(clazz, transformer);
                    return transformer;
                }
            }
        }

        return null;
    }

}
