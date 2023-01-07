package solarsystem.model.common;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import solarsystem.model.Planet;
import solarsystem.model.Satellite;
import solarsystem.model.Star;
import solarsystem.model.util.MathUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultPlanet implements Planet {

    private long orbitalPeriod;
    private double orbitalAngle = 0;
    
	private double size;
	private double distance;
	
    private List<Satellite> satellites;

    public DefaultPlanet(double distance) {
        this(0.3, distance, 365);
    }
    
    public DefaultPlanet(double size, double distance, long orbitalPeriod) {
        this.satellites = new ArrayList<Satellite>(0);
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
    public void addStar(Satellite satellite) {
        this.satellites.add(satellite);
    }
    
    @Override
    public void removeStar(Satellite satellite) {
        this.satellites.remove(satellite);
    }
    
    @Override
    public Star[] orbitingObjects() {
        Star[] _ret = new Star[satellites.size()];
        satellites.toArray(_ret);
        return _ret;
    }
    
    @Override
    public void updateData() {
        orbitalAngle = MathUtils.calculateNextAngle(orbitalAngle, orbitalPeriod);
        
        for (Star star : orbitingObjects()) {
            star.updateData();
        }
    }
    
    @Override
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        gl.glPushMatrix();
        
        gl.glRotated(orbitalAngle(), 0.0, 1.0, 0.0); // rotacao (TODO: pr�prio eixo? ou em torno do sol?)
        gl.glTranslated(distanceToCenterOfOrbit(), 0.0, 0.0);// distancia do sol

        System.out.printf("period: %d, angle: %f, distance: %f.%n", orbitalPeriod(), orbitalAngle(), distanceToCenterOfOrbit());
        
        doDraw(gl, glu, glut);
        
        for (Star star : orbitingObjects()) {
            star.draw(gl, glu, glut);
        }
        
        gl.glPopMatrix();
    }

    protected abstract void doDraw(GL2 gl, GLU glu, GLUT glut);

}
