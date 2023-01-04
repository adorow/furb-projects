package shapes.draw;

import renderer.Renderer;
import shapes.Shape;

/**
 * Interface que define um objeto que sabe desenhar uma forma geom�trica espec�fica.
 * 
 * @param <T> o tipo de forma geom�trica que a implementa��o desta interface deve saber desenhar.
 */
public interface ShapeDrawer<T extends Shape> {

    /**
     * Desenha a forma geom�trica.
     * 
     * @param renderer o local onde deve ser desenhada a forma geom�trica.
     * @param shape a forma geom�trica aser desenhada.
     */
    void draw(Renderer renderer, T shape);

}
