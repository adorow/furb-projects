package shapes.draw;

import renderer.Renderer;
import shapes.Shape;

/**
 * Interface que define um objeto que sabe desenhar uma forma geométrica específica.
 * 
 * @param <T> o tipo de forma geométrica que a implementação desta interface deve saber desenhar.
 */
public interface ShapeDrawer<T extends Shape> {

    /**
     * Desenha a forma geométrica.
     * 
     * @param renderer o local onde deve ser desenhada a forma geométrica.
     * @param shape a forma geométrica aser desenhada.
     */
    void draw(Renderer renderer, T shape);

}
