package solarsystem.model.common;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import solarsystem.model.Satellite;
import solarsystem.model.util.MathUtils;

public abstract class DefaultSatellite implements Satellite {

    private long orbitalPeriod;
    private double orbitalAngle = 0;
    
    private double size;
    private double distance;

    public DefaultSatellite(double distance) {
        this(0.1, distance, 27);
    }
    
    public DefaultSatellite(double size, double distance, long orbitalPeriod) {
        this.size = size;
        this.distance = distance;
        this.orbitalPeriod = orbitalPeriod;
    }
    
    public double size() {
        return size;
    }
    
    @Override
    public double distanceToCenterOfOrbit() {
    	return distance;
    }
    
    @Override
    public double orbitalAngle() {
        return orbitalAngle;
    }

    @Override
    public long orbitalPeriod() {
        return orbitalPeriod;
    }

    @Override
    public long apihelion() {
        return 0;
    }

    @Override
    public long perihelion() {
        return 0;
    }
    
    @Override
    public void updateData() {
        orbitalAngle = MathUtils.calculateNextAngle(orbitalAngle, orbitalPeriod);
    }
    
    @Override
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        gl.glPushMatrix();
        
        gl.glRotated(orbitalAngle(), 0.0, 1.0, 0.0); // rotacao (TODO: pr�prio eixo? ou em torno do planeta?)
        gl.glTranslated(distanceToCenterOfOrbit(), 0.0, 0.0);// distancia do sol
           
        doDraw(gl, glu, glut);
        
        gl.glPopMatrix();
    }

    protected abstract void doDraw(GL2 gl, GLU glu, GLUT glut);

}
