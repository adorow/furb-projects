package renderer;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

//import javax.media.opengl.GL;
//import javax.media.opengl.GLDrawable;
//import javax.media.opengl.GLEventListener;
//import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import view.DrawAction;

public interface Renderer extends GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    /**
     * @return a estrutura GL associada.
     */
    GL2 getGL();

    /**
     * @return a estrutura GLU associada.
     */
    GLU getGLU();

    /**
     * @return a estrutura GLDrawable associada, onde ser�o feitos os desenhos.
     */
    GLDrawable getDrawable();

    /**
     * Altera a a��o atual.
     * 
     * @param action a a��o de desenho a ser atribu�da.
     */
    void setAction(DrawAction action);

    /**
     * Altera a cor dos objetos selecionados no momento.
     * 
     * @param color a cor para a qual ser� alterado.
     */
    void setColor(Color color);

    /**
     * Rotaciona os objetos selecionados.
     * 
     * @param degrees a quantidade de graus que se far� a rota��o.
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
