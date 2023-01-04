package solarsystem.model.glut;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import solarsystem.model.common.DefaultSun;

import com.sun.opengl.util.GLUT;

public class Sun_AsSolidSphere extends DefaultSun {

    public Sun_AsSolidSphere() {
        super();
    }
    
    public Sun_AsSolidSphere(double size) {
        super(size);
    }


    @Override
    protected void doDraw(GL gl, GLU glu, GLUT glut) {
        gl.glColor4d(1.0, 1.0, 0.0, 1.0);// Yellow Sun
        glut.glutSolidSphere(size(), 20, 20) ;
    }

}
