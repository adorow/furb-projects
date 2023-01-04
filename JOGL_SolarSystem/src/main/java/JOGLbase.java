import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*; // GLUT, FPSAnimator

// The base application is an Applet including a main function.
// The inner class JOGLFrame serves as wrapper for the applet.
// In this way JOGLbase may run as applet 
// or as application within an AWT Frame (JOGLFrame).
// Look at the main function to understand this.
//
public class JOGLbase extends Applet
  implements GLEventListener
{
  //////////////// Variables /////////////////////////

  // Viewing Window Frame size.
  static final int defaultWidth = 600;
  static final int defaultHeight = 600;
  int width = defaultWidth;
  int height = defaultHeight;
  final boolean enableFullscreen = false;
  final boolean hideCursor = false;

  // Global canvas for event handling.
  GLCanvas canvas;

  // Vars for animation.
  FPSAnimator animator;

  // GUT and GLUT are global objects so that
  // they do not have to be newed in each frame.
  GLUT glut = new GLUT();
  GLU glu = new GLU();

  // Functionality from local modules.

  View view = new View();
  Fullscreen fullscreen = new Fullscreen();
  InputDevice inputDevice = new InputDevice();
  Light light = new Light();
  DrawingBasics drawingBasics = new DrawingBasics();
  // StipplePattern stipplePattern = new StipplePattern();
  // Blend blend = new Blend();
  // GLUdemo gludemo = new GLUdemo();
  // GLUTdemo glutdemo = new GLUTdemo();
   SolarSystem solarSystem = new SolarSystem( animator, light );
//  MovingSpheres movingSpheres = new MovingSpheres();
  // Imaging imaging = new Imaging();
   Font font = new Font();
  //
   Texturing texturing = new Texturing();
  // Texture3D texture3D = new Texture3D();
  //
  //ModelLoaderOBJ modelLoaderOBJ = new ModelLoaderOBJ();
  //
  // Bezier bezier = new Bezier();
  //
  // ParticleSystem particleSystem  = new ParticleSystem();
  // VertexArray vertexArray = new VertexArray();


  /////////////////////// JOGLFrame //////////////////

  // JOGLframe launch the applet as application.
  class JOGLframe extends Frame {

    public JOGLframe( JOGLbase base ) 
      {
	setTitle("JOGL Applet in a Frame");

	// Use at least all screen for this frame
	// in case fullscreen does not work.
	if( enableFullscreen ) {
	  width = fullscreen.getWidth();
	  height = fullscreen.getHeight();
	  setSize( width, height );
	  fullscreen.init( this ); 
	} else {
	  setSize( width, height );
	}

	base.init();
	add( base );

	addWindowListener( new WindowAdapter() {
	    public void windowClosing( WindowEvent e ) {
	      System.exit(0); } 
	    // Canvas gets focus whenever frame is activated.
	    public void windowActivated( WindowEvent e ) {
	      //canvas.requestFocusInWindow(); 
	    }
	  } );

	setLocationRelativeTo(null);      
	setVisible( true );
	requestFocus();
	canvas.requestFocusInWindow();
	pack();

	// After JOGL and window setup, start the animator.
	animator = new FPSAnimator( canvas, 30 );
	animator.setRunAsFastAsPossible( false );
	animator.start();
      }
  }

  /////////////////////// main ///////////////////////

  public static void main( String[] args ) 
    {
      JOGLbase base = new JOGLbase();	
      JOGLbase.JOGLframe frame = base.new JOGLframe( base );
  }


  ///////////////// Functions /////////////////////////

  public void init()
    {
      setFocusable( true );
      if( hideCursor ) { inputDevice.hideCursor( this ); }
      else { inputDevice.setStandardCursor( this ); }

      setLayout(new BorderLayout());

      initJOGL();

      setVisible( true );
      requestFocus();
      canvas.requestFocusInWindow();
    }


  private void initJOGL()
    {
      GLCapabilities caps = new GLCapabilities();
      caps.setDoubleBuffered( true );
      caps.setHardwareAccelerated( true );

      canvas = new GLCanvas( caps );
      canvas.addGLEventListener( this );

      add( canvas, BorderLayout.CENTER );

      // UserEventListener is tied to this canvas.
      UserEventMediator listener = new UserEventMediator
 	( this, inputDevice, view, light, canvas );
      canvas.addKeyListener( listener );
      canvas.addMouseListener( listener );
      canvas.addMouseMotionListener( listener );

      canvas.requestFocusInWindow();
    }

  
  ////////// Methods defined by GLEventListener ///////////

  public void init( GLAutoDrawable drawable )
    {
      GL gl = drawable.getGL();

      // Be carefull with debug, it can cause errors.
      //drawable.setGL( new DebugGL(drawable.getGL() ));
      //System.out.println("Init GL is " + gl.getClass().getName());

      // On some systems the reshape call does not seem to 
      // happen automatically on init.
      // Set the projection and viewport.
      reshape( drawable, 0, 0, width, height );

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
      light.init( gl );
      font.init( gl );      
      //solarSystem.initDisplayLists( gl, glu );
      //imaging.init( width, height );
      //imaging.loadImage( null ); // Load default image.
      //texturing.init( gl, glu );
      //texture3D.init( gl );
      //modelLoaderOBJ.init( gl );
      //particleSystem.init( gl );
      //vertexArray.init( gl );
    }

  public void reshape(GLAutoDrawable drawable,
		      int x, int y, int width, int height) 
    { 
      //System.out.println("reshape()");

      GL gl = drawable.getGL();

      gl.glMatrixMode(GL.GL_PROJECTION);
      gl.glLoadIdentity();

      gl.glFrustum( -0.1f, 0.1f, -0.1f, 0.1f, 0.2f, 100.0f );
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
    }


  public void displayChanged( GLAutoDrawable drawable, 
			      boolean modeChanged, 
			      boolean deviceChanged ) 
    { 
      System.out.println ("displayChanged()");
    }


  public void display(GLAutoDrawable drawable)
    {
      //System.out.println("display");//ddd

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
      view.setCamera( gl );
      //view.setCameraLookAt( glu );

      // Lights are treated as static geometry, 
      // thus thier positions have to be set together
      // with other geomety (not in init).
      //light.initPosition( gl ); // Mandatory when light is used.

      // Model Transformation and Draw Content.
      draw( gl );

      //getAndPrintError( gl );

      gl.glFlush();

      //imaging.saveFrameAsPNG( gl, null );
    }

  /////////////////// Error and Exit //////////////////

  int getAndPrintError( GL gl ) 
    {
      int errorCode = gl.glGetError();
      //String errorStr = new String();
      //errorStr = glu.gluErrorString( errorCode );
      //String errorStr = glu.gluErrorString( GL.GL_OUT_OF_MEMORY );
      //System.out.println( errorStr );
      //errorStr = gl.glGetString(GL.GL_PROGRAM_ERROR_STRING_ARB);
      System.out.println( errorCode );
      return errorCode;
    }

  void startAnimator() 
    {
      if( ! animator.isAnimating() ) 
      { 
	animator.start();
      }
    }

  void stopAnimator() 
    {
      if( animator.isAnimating() ) 
      { 
	animator.stop();
      }
    }

  // Called via user event and on window closing.
  void exit()
    {
      stopAnimator();
      if( enableFullscreen ) { fullscreen.exit(); }
      System.exit( 0 ); 
    }

  /////////////////////// Drawings ////////////////////

  void draw( GL gl  )
    {
//       drawingBasics.drawAxis( gl, false );      
//       drawingBasics.drawPoints( gl );
//       drawingBasics.draw2DPoints( gl );
//       drawingBasics.drawTriangle( gl );
//       drawingBasics.drawColorTriangle( gl );
//       drawingBasics.drawWhiteTriangle( gl );
//       drawingBasics.drawHouse( gl );
//       drawingBasics.draw3DHouseLines( gl );
//       drawingBasics.drawCircle( gl, 0f, 0f, 0f );
//       drawingBasics.drawHouseNoInnerEdge( gl );
//       drawingBasics.drawCube( gl, 1.0f );
//       drawingBasics.drawSphere( gl, 0f, 0f, 0f, 0.5f, true );
//todo   drawingBasics.animSphere( gl );
//todo   drawingBasics.drawSphereHighlightLines( gl, 0f, 0f, 0f, 0.5f );

//       stipplePattern.drawStippledPolygon( gl );

//      blend.drawColredCircles( gl );
//      blend.drawBlendPlane( gl, bezier );  
//todo  blend.draw3DHousePolygonWithBlending( gl );

//todo 
      solarSystem.draw( gl, glu, glut );
       //movingSpheres.draw( gl, drawingBasics );
      //gludemo.draw( gl, glu );
      //glutdemo.draw( gl, glut );
  
      light.drawLight( gl, glu, glut );
      //light.animate( gl, glu, glut );
      //light.setSomeWhiteMaterial( gl, GL.GL_FRONT_AND_BACK );
      //light.drawTeaPotWithLight( gl, glut );
//todo  light.drawTeaPotWithLightCutOff( gl, glut );
      //light. drawLight( gl, glu, glut );

      //imaging.draw( gl );
      
       //font.draw( gl );
//      font.drawFontWithGLUT( gl, glut );
//      font.drawStringWithGLUTfont( gl, glut, 10, 20, "JOGL Demo", GLUT.BITMAP_HELVETICA_18 );
     font.drawStringWithGLUTfont ( gl, glut, 10, 5, 
 	  "i,e->up j,s->right k,d->back l,f->left ,,c->down SPACE->forward",
 	  GLUT.BITMAP_HELVETICA_12 );

      //texturing.drawBackground( gl);
      //texturing.drawTunnel( gl, light );
      //texturing.drawTeapot( gl, glu, glut, light );
//todo texturing.draw3DHouseWithTexture( gl );
      //texturing.draw( gl, glu, glut, light );

      // modelLoaderOBJ.draw( gl );

      //texture3D.draw( gl );

//      bezier.drawSpiral( gl );
//      bezier.draw2DBezier( gl );
//      bezier.draw3DBezier( gl );
//       ////bezier.drawNURBS( gl, glu ); // not yet.

      //particleSystem.draw( gl );

      //vertexArray.draw( gl );
      
    }

}
