import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*; // GLUT

class GLUTdemo {

  ///////////////// Functions /////////////////////////

  public GLUTdemo() {}

  public void draw( GL gl, GLUT glut ) 
    {
      gl.glPushMatrix(); {
	gl.glTranslatef (0.8f, 0.3f, -0.3f); 
	glut.glutSolidSphere( 0.05f, 20, 20);
	glut.glutSolidTeapot( 1.0f, false );
      } gl.glPopMatrix(); 
    }  
}
