package solarsystem.model.blenderobjects;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import solarsystem.model.blenderobjects.core.BlenderOBJ;
import solarsystem.model.common.DefaultPlanet;

import com.sun.opengl.util.GLUT;

public class Planet_AsBlenderObj extends DefaultPlanet {

    private BlenderOBJ blenderObj;
    private String filename;
    
    public Planet_AsBlenderObj(String filename, double distance) {
        super(distance);
        this.filename = filename;
    }
    
    public Planet_AsBlenderObj(String filename, double size, double distance, long orbitalPeriod) {
        super(size, distance, orbitalPeriod);
        this.filename = filename;
    }
    
    private BlenderOBJ getBlenderObj(GL gl) {
        if (blenderObj == null) {
            //blenderObj = new BlenderOBJ("files/" + filename, 1.5f, gl, true);
            blenderObj = new BlenderOBJ("files/" + filename, (float) size(), gl, true);
        }
        return blenderObj;
    }

    protected void doDraw(GL gl, GLU glu, GLUT glut) {
        gl.glColor3d(1.0, 1.0, 1.0);
        getBlenderObj(gl).draw(gl);
    }
}
