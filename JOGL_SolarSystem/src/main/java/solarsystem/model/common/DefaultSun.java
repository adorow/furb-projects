package solarsystem.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import solarsystem.model.Planet;
import solarsystem.model.Star;
import solarsystem.model.Sun;

import com.sun.opengl.util.GLUT;

public abstract class DefaultSun implements Sun {

    private double size;
    private List<Planet> planets;

    public DefaultSun() {
        this(0.8);
    }
    
    public DefaultSun(double size) {
        this.size = size;
        this.planets = new ArrayList<Planet>(0);
    }
    
    public double size() {
        return size;
    }
    
    public double distanceToCenterOfOrbit() {
    	return 0;
    }
    
    @Override
    public double orbitalAngle() {
        return 0;
    }
    
    @Override
    public long orbitalPeriod() {
        return 0;
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
    public void addStar(Planet planet) {
        this.planets.add(planet);
    }
    
    @Override
    public void removeStar(Planet planet) {
        this.planets.remove(planet);
    }
    
    @Override
    public Star[] orbitingObjects() {
        Star[] _ret = new Star[planets.size()];
        planets.toArray(_ret);
        return _ret;
    }
    
    @Override
    public void updateData() {
        // updates nothing in the sun.
        
        for (Star star : orbitingObjects()) {
            star.updateData();
        }
    }

    @Override
    public void draw(GL gl, GLU glu, GLUT glut) {
        gl.glPushMatrix();
        
        doDraw(gl, glu, glut);
        
        for (Star star : orbitingObjects()) {
            star.draw(gl, glu, glut);
        }
        
        gl.glPopMatrix();
    }
    
    protected abstract void doDraw(GL gl, GLU glu, GLUT glut);
    
}
