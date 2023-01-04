package solarsystem.model.glut;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import solarsystem.model.common.DefaultPlanet;

import com.sun.opengl.util.GLUT;

public class Planet_AsSolidSphere extends DefaultPlanet {

	public Planet_AsSolidSphere(double distance) {
		super(distance);
	}
	
	public Planet_AsSolidSphere(double size, double distance, long orbitalPeriod) {
		super(size, distance, orbitalPeriod);
	}
    
	@Override
	protected void doDraw(GL gl, GLU glu, GLUT glut) {
	    gl.glColor4d(0.0, 1.0, 0.0, 1.0);
	    glut.glutSolidSphere(size(), 20, 20);
	}
}
