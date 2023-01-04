package solarsystem.model;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

public interface Drawable {

    void draw(GL gl, GLU glu, GLUT glut);
    
}
