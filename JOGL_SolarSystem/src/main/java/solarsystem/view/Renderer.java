package solarsystem.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import solarsystem.model.Planet;
import solarsystem.model.Satellite;
import solarsystem.model.SolarSystems;
import solarsystem.model.Sun;
import solarsystem.model.blenderobjects.Planet_AsBlenderObj;
import solarsystem.model.glut.Planet_AsSolidSphere;
import solarsystem.model.glut.Satellite_AsSolidSphere;
import solarsystem.model.glut.Sun_AsSolidSphere;

import com.sun.opengl.util.GLUT;

public class Renderer implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private SolarSystems ss = new SolarSystems();
    
    private double oglStarSize(double size) {
        //return size / 6955.0;
        return size / 1000.0;
    }
    
    public Renderer() {
    	// Sol
        //Sun sun = new Sun_AsSolidSphere(6955.00);
        //Sun sun = new Sun_AsSolidSphere(oglStarSize(6955.00));
        Sun sun = new Sun_AsSolidSphere(oglStarSize(1000.00));
    	ss.addStar(sun);
    	
//    	// Planetas
//    	Planet mercury = new Planet_AsSolidSphere(oglStarSize(24.39), 1.2, 88);
//    	sun.addStar(mercury);
//    	Planet venus = new Planet_AsSolidSphere(oglStarSize(60.51), 1.5, 224);
//    	sun.addStar(venus);
//    	Planet earth = new Planet_AsSolidSphere(oglStarSize(63.71), 2.0, 365);
//    	sun.addStar(earth);
//    	Planet mars = new Planet_AsSolidSphere(oglStarSize(33.96), 2.7, 668);
//    	sun.addStar(mars);
//    	Planet jupiter = new Planet_AsSolidSphere(oglStarSize(714.92), 3.5, 4331);
//    	sun.addStar(jupiter);
//    	Planet saturn = new Planet_AsSolidSphere(oglStarSize(602.68), 4.5, 10759);
//    	sun.addStar(saturn);
//    	Planet uranus = new Planet_AsSolidSphere(oglStarSize(255.59), 5.5, 30799);
//    	sun.addStar(uranus);
//    	Planet neptune = new Planet_AsSolidSphere(oglStarSize(247.64), 6.0, 60190);
//    	sun.addStar(neptune);
//    	Planet pluto = new Planet_AsSolidSphere(oglStarSize(11.53), 6.4, 90613);
//    	sun.addStar(pluto);
    	// Planetas
        Planet mercury = new Planet_AsBlenderObj("mercury", oglStarSize(24.39), 1.2, 88);
        sun.addStar(mercury);
        Planet venus = new Planet_AsBlenderObj("venus", oglStarSize(60.51), 1.5, 224);
        sun.addStar(venus);
        Planet earth = new Planet_AsBlenderObj("earth", oglStarSize(63.71), 2.0, 365);
        sun.addStar(earth);
        Planet mars = new Planet_AsBlenderObj("mars", oglStarSize(33.96), 2.7, 668);
        sun.addStar(mars);
        Planet jupiter = new Planet_AsBlenderObj("Jupiter", oglStarSize(714.92), 3.5, 4331);
        sun.addStar(jupiter);
        Planet saturn = new Planet_AsSolidSphere(oglStarSize(602.68), 4.5, 10759);
        sun.addStar(saturn);
        Planet uranus = new Planet_AsBlenderObj("uranus", oglStarSize(255.59), 5.5, 30799);
        sun.addStar(uranus);
        Planet neptune = new Planet_AsBlenderObj("neptune", oglStarSize(247.64), 6.0, 60190);
        sun.addStar(neptune);
        Planet pluto = new Planet_AsBlenderObj("pluto", oglStarSize(11.53), 6.4, 90613);
        sun.addStar(pluto);
    	
//    	Planet mercury = new Planet_AsSolidSphere(0.2, 2.0, 88);
//        sun.addStar(mercury);
//        Planet venus = new Planet_AsSolidSphere(1.0, 2.0, 224);
//        sun.addStar(venus);
//        Planet earth = new Planet_AsSolidSphere(1.0, 3.0, 365);
//        sun.addStar(earth);
//        Planet mars = new Planet_AsSolidSphere(1.0, 4.0, 668);
//        sun.addStar(mars);
//        Planet jupiter = new Planet_AsSolidSphere(1.0, 5.0, 4331);
//        sun.addStar(jupiter);
//        Planet saturn = new Planet_AsSolidSphere(1.0, 6.0, 10759);
//        sun.addStar(saturn);
//        Planet uranus = new Planet_AsSolidSphere(1.0, 7.0, 30799);
//        sun.addStar(uranus);
//        Planet neptune = new Planet_AsSolidSphere(1.0, 8.0, 60190);
//        sun.addStar(neptune);
//        Planet pluto = new Planet_AsSolidSphere(1.0, 9.0, 90613);
//        sun.addStar(pluto);

//    	// Satelites
//    	Satellite moon = new Satellite_AsSolidSphere(17.37, 0.2, 27);
//    	earth.addStar(moon);
    	Satellite moon = new Satellite_AsSolidSphere(oglStarSize(17.37), 0.2, 27);
    	earth.addStar(moon);
    }
    
 // GUT and GLUT are global objects so that
    // they do not have to be newed in each frame.
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    
    // -- GLEventListener --
    
    @Override
    public void init(GLAutoDrawable drawable) {
    	GL gl = drawable.getGL();

        // Be carefull with debug, it can cause errors.
        //drawable.setGL( new DebugGL(drawable.getGL() ));
        //System.out.println("Init GL is " + gl.getClass().getName());

        // On some systems the reshape call does not seem to 
        // happen automatically on init.
        // Set the projection and viewport.
        //reshape( drawable, 0, 0, width, height );

        gl.glClearColor( 0f, 0f, 0f, 0.0f );
        //gl.glClearColor( 1f, 1f, 1f, 0.0f );

        // Really Nice Perspective Calculations
        //gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  
        // GL.GL_FRONT[_AND_BACK], GL.GL_LINE, GL.GL_FILL
        gl.glPolygonMode( GL.GL_FRONT_AND_BACK, GL.GL_3D );

        gl.glEnable( GL.GL_DEPTH_TEST );
        //gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glEnable( GL.GL_AUTO_NORMAL );
        //gl.glEnable(GL.GL_NORMALIZE);
        //gl.glShadeModel( GL.GL_FLAT );
        gl.glShadeModel( GL.GL_SMOOTH );
        
        //drawingBasics.initAliasingAndFog( gl, true, false );
        //blend.init( gl );
        //light.init( gl );
        //font.init( gl );      
        //solarSystem.initDisplayLists( gl, glu );
        //imaging.init( width, height );
        //imaging.loadImage( null ); // Load default image.
        //texturing.init( gl, glu );
        //texture3D.init( gl );
        //modelLoaderOBJ.init( gl );
        //particleSystem.init( gl );
        //vertexArray.init( gl );
    }
    
    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        

    // Clear Framebuffer and Z-Buffer.
    gl.glClear( GL.GL_COLOR_BUFFER_BIT | 
        GL.GL_DEPTH_BUFFER_BIT );
    // Default color.
    gl.glColor3f(1f, 1f, 1f);
    //gl.glColor3f(0f, 0f, 0f);

    gl.glMatrixMode(GL.GL_MODELVIEW);
    gl.glLoadIdentity();
  
    // Viewing Transformation.
    //view.setCamera(gl);
    //view.setCameraLookAt( glu );

    // Lights are treated as static geometry, 
    // thus thier positions have to be set together
    // with other geomety (not in init).
    //light.initPosition( gl ); // Mandatory when light is used.

    gl.glTranslated(0.0, 0.0, -15.0);
    
    ss.updateData();
    
    // Model Transformation and Draw Content.
    ss.draw(gl, glu, glut);

    printError(gl);

    gl.glFlush();

    //imaging.saveFrameAsPNG( gl, null );
        
    }
    
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glFrustum( -0.1f, 0.1f, -0.1f, 0.1f, 0.2f, 100.0f );
        //gl.glFrustum( -1.0f, 1.0f, -1.0f, 1.0f, 0.2f, 100.0f );
        //gl.glFrustum( -0.1f, 0.1f, -0.1f, 0.1f, 0.2f, 100.0f );
        //glu.gluOrtho2D(0f, (float)width, 0f, (float)height );
        //gl.glOrtho(0, 1, 0, 1, -1, 1);
        //gl.glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, 0f, 10.0f );
        //gl.glOrtho( 1.5f, -1.5f, -1.5f, 1.5f, 0f, 10.0f );
        //gl.glOrtho(-.8f, .8f, -0.8f, .5f, 0f, 10.0f );

        // If we do not have a rectangular viewport and 
        // want to avoid distortion we have to adjust
        // the viewing volume to the viewport.
        //
        double aspect = (double)width / (double)height;
        // Field ov view in y direction [0..180].
        final double fovy = 30.0f;
        //glu.gluPerspective( fovy, aspect, 1.2f, 20.0f );

        gl.glViewport( 0, 0, width, height );
        
        System.out.println ("reshape()");
    }
    
    @Override
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        System.out.println ("displayChanged()");
    }
    
    // -- KeyListener --
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    // -- MouseListener --
    
    @Override
    public void mouseClicked(MouseEvent event) {}
    
    @Override
    public void mousePressed(MouseEvent event) {}
    
    @Override
    public void mouseReleased(MouseEvent event) {}
    
    @Override
    public void mouseEntered(MouseEvent event) {}
    
    @Override
    public void mouseExited(MouseEvent event) {}
    
    // -- MouseMotionListener --
    
    @Override
    public void mouseDragged(MouseEvent event) {}
    
    @Override
    public void mouseMoved(MouseEvent event) {}
    
    // -- MouseWheelListener --
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {}
    
    // -- End of listener methods --
    
    private void printError(GL gl) {
        int errorCode = gl.glGetError();
        if (errorCode != 0) {
            String errorMsg = glu.gluErrorString( errorCode );
            //String errorStr = glu.gluErrorString( GL.GL_OUT_OF_MEMORY );
            //System.out.println( errorStr );
            //errorStr = gl.glGetString(GL.GL_PROGRAM_ERROR_STRING_ARB);
            System.out.printf("Ocorreu um erro: (%d) %s.%n", errorCode, errorMsg );
        }
    }
}
