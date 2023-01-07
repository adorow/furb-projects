package solarsystem.model.glut;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import solarsystem.model.common.DefaultPlanet;

public class Planet_AsSolidSphere extends DefaultPlanet {

	public Planet_AsSolidSphere(double distance) {
		super(distance);
	}
	
	public Planet_AsSolidSphere(double size, double distance, long orbitalPeriod) {
		super(size, distance, orbitalPeriod);
	}
    
	@Override
	protected void doDraw(GL2 gl, GLU glu, GLUT glut) {
	    gl.glColor4d(0.0, 1.0, 0.0, 1.0);
	    glut.glutSolidSphere(size(), 20, 20);
	}
}
