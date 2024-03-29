package solarsystem.model;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.ArrayList;
import java.util.List;

public class SolarSystems implements SolarSystemObject, Orbitable<Sun> {

    // lista com os astros existentes no sistema solar
    private List<Sun> stars = new ArrayList<Sun>();

    public void addStar(Sun star) {
        stars.add(star);
    }

    public void removeStar(Sun star) {
        stars.remove(star);
    }
    
    @Override
    public Star[] orbitingObjects() {
        Sun[] _ret = new Sun[stars.size()];
        stars.toArray(_ret);
        return _ret;
    }
    
    @Override
    public void updateData() {
        for (Star star : orbitingObjects()) {
            star.updateData();
        }
    }

    @Override
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        gl.glPushMatrix();
        
        for (Sun star : stars) {
            star.draw(gl, glu, glut);
        }
        
        gl.glPopMatrix();
    }
    
}
