package renderer;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import view.DrawAction;

public interface Renderer extends GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    /**
     * @return a estrutura GL associada.
     */
    GL getGL();

    /**
     * @return a estrutura GLU associada.
     */
    GLU getGLU();

    /**
     * @return a estrutura GLDrawable associada, onde serão feitos os desenhos.
     */
    GLDrawable getDrawable();

    /**
     * Altera a ação atual.
     * 
     * @param action a ação de desenho a ser atribuída.
     */
    void setAction(DrawAction action);

    /**
     * Altera a cor dos objetos selecionados no momento.
     * 
     * @param color a cor para a qual será alterado.
     */
    void setColor(Color color);

    /**
     * Rotaciona os objetos selecionados.
     * 
     * @param degrees a quantidade de graus que se fará a rotação.
     */
    void rotate(double degrees);

    /**
     * Efetua a escala nos objetos selecionados.
     * 
     * @param scale a escala a ser aplicada.
     */
    void scale(double scale);

    /**
     * Faz zoom-in no canvas.
     */
    void zoomIn();

    /**
     * Faz zoom-out no canvas.
     */
    void zoomOut();

}
