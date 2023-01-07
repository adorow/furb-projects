package shapes.draw;

import renderer.Renderer;
import shapes.Shape;

/**
 * Interface que define um objeto que sabe desenhar uma forma geometrica especifica.
 * 
 * @param <T> o tipo de forma geometrica que a implementacao desta interface deve saber desenhar.
 */
public interface ShapeDrawer<T extends Shape> {

    /**
     * Desenha a forma geometrica.
     * 
     * @param renderer o local onde deve ser desenhada a forma geometrica.
     * @param shape a forma geometrica aser desenhada.
     */
    void draw(Renderer renderer, T shape);

}
