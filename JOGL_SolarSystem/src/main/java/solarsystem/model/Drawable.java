package solarsystem.model;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public interface Drawable {

    void draw(GL2 gl, GLU glu, GLUT glut);
    
}
