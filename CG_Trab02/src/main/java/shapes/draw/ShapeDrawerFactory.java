package shapes.draw;

import java.util.HashMap;
import java.util.Map;

import shapes.BoundingBox;
import shapes.Circle;
import shapes.ClosedPolygon;
import shapes.OpenPolygon;
import shapes.Shape;
import shapes.Spline;
import shapes.draw.impl.BoundingBoxDrawer;
import shapes.draw.impl.CircleDrawer;
import shapes.draw.impl.ClosedPolygonDrawer;
import shapes.draw.impl.OpenPolygonDrawer;
import shapes.draw.impl.SplineDrawer;

/**
 * Fábrica para buscar a classe responsável por desenhar um determinado tipo de forma geométrica.
 */
public final class ShapeDrawerFactory {

    private static final Map<Class, ShapeDrawer> MAP;

    static {
        MAP = new HashMap<Class, ShapeDrawer>();
        MAP.put(Circle.class, new CircleDrawer());
        MAP.put(Spline.class, new SplineDrawer());
        MAP.put(OpenPolygon.class, new OpenPolygonDrawer());
        MAP.put(ClosedPolygon.class, new ClosedPolygonDrawer());
        MAP.put(BoundingBox.class, new BoundingBoxDrawer());
    }

    /**
     * Busca a classe responsável por desenhar uma determinada forma geométrica.
     * 
     * @param <T> o formato a ser retornado.
     * @param shape a forma cuja classe de desenho será buscada.
     * @return a classe que é capaz de desenhar a forma informada.
     */
    public static <T extends Shape> ShapeDrawer<T> getDrawer(T shape) {
        return (ShapeDrawer<T>) get(shape.getClass());
    }

    private static <T extends Shape> ShapeDrawer<T> get(Class<T> clazz) {
        ShapeDrawer<T> drawer = MAP.get(clazz);
        if (drawer != null) {
            return drawer;
        }
        for (Class<?> oClazz : clazz.getInterfaces()) {
            if (Shape.class.isAssignableFrom(oClazz)) {
                drawer = get((Class<T>) oClazz);
                if (drawer != null) {
                    // adiciona no mapa para agilizar as próximas buscas de Drawers
                    MAP.put(clazz, drawer);
                    return drawer;
                }
            }
        }

        return null;
    }

}
