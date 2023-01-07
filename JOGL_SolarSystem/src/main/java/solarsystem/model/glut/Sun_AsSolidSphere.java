package solarsystem.model.glut;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import solarsystem.model.common.DefaultSun;

public class Sun_AsSolidSphere extends DefaultSun {

    public Sun_AsSolidSphere() {
        super();
    }
    
    public Sun_AsSolidSphere(double size) {
        super(size);
    }


    @Override
    protected void doDraw(GL2 gl, GLU glu, GLUT glut) {
        gl.glColor4d(1.0, 1.0, 0.0, 1.0);// Yellow Sun
        glut.glutSolidSphere(size(), 20, 20) ;
    }

}
