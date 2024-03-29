package renderer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

//import javax.media.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLAutoDrawable;

import view.DrawAction;

public abstract class RendererStub implements Renderer {

    // m�todo definido na interface GLEventListener.
    // "render" feito depois que a janela foi redimensionada.
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    // m�todo definido na interface GLEventListener.
    // "render" feito quando o modo ou dispositivo de exibi��o associado foi alterado.
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
    }

    public void display(GLAutoDrawable drawable) {
    }

    public void init(GLAutoDrawable drawable) {
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent arg0) {
    }

    public void mouseMoved(MouseEvent arg0) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    public void setAction(DrawAction action) {
    }

}
