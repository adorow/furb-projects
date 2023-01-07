import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

class SolarSystem {

  //////////////// Variables /////////////////////////

  // Solar system.
  float hourofday = 0f;
  float dayofyear = 0f;
  float dayofmonth = 10f;

  // Var for displaylists.
  int sun, planet1, planet2;
  int moon11, moon12, moon21, spaceship;

  FPSAnimator animator;
  Light light;

  ///////////////// Functions /////////////////////////
  public SolarSystem( FPSAnimator animator,
		      Light light )
    {
      this.animator = animator;
      this.light = light;
    }


 public void draw(GL2 gl, GLU glu, GLUT glut )
    {
       //GLUquadric qobj0 = glu.gluNewQuadric();
       //glu.gluQuadricDrawStyle( qobj0, GLU.GLU_LINE );
       //glu.gluQuadricNormals( qobj0, GLU.GLU_SMOOTH );

      // Take a photo:
      //if( dayofyear > 3 ) animator.stop();
      
      float angleDay = ( hourofday / 24f ) * 360f;
      float angleYear = ( dayofyear / 365f ) * 360f;
      float angleMonth = ( dayofmonth / 28f ) * 360f;
      // Clocktick (unit is 1 hour): step time.
      final float clocktick = 20f;
      hourofday = (hourofday+clocktick) % 24f;
      dayofmonth = (dayofmonth+(clocktick/24f)) % 28f;
      dayofyear = (dayofyear+(clocktick/24f)) % 365f;

//       System.out.println( "Day: " + angleDay +
// 			  "  Month: " + angleMonth +
// 			  "  Year: " + angleYear );//ddd

      gl.glPushMatrix(); {
	// Sun
	gl.glColor4f( 1f, 0f, 0f, 1f );
	//glu.gluSphere( qobj0, 0.8f, 10, 10) ;      
	glut.glutSolidSphere( 0.8, 20, 20) ;      

	gl.glPushMatrix(); {
	    System.out.printf("angle: %f%n", angleYear);
	  // Planet 1 
	  gl.glRotatef( angleYear, 0.0f, 1.0f, 0.0f ); 
	  gl.glTranslatef ( 3.0f, 0.0f, 0.0f ); 
	  gl.glColor4f( 0f, 1f, 0f, 1f );
	  //glu.gluSphere( qobj0, 0.3f, 10, 10) ;      
	  glut.glutSolidSphere( 0.3, 20, 20) ;      

	  // Moon 11
	  gl.glPushMatrix(); {
	      gl.glColor4f( 1f, 1f, 0f, 1f );
	    gl.glRotatef( angleMonth, 0.0f, 1.0f, 0.0f ); 
	    gl.glTranslatef (0.8f, 0.0f, 0.0f ); 
	    //glu.gluSphere( qobj0, 0.1f, 10, 10 ) ;      
	    glut.glutSolidSphere( 0.1, 10, 10) ;      
	  } gl.glPopMatrix(); 
	
	} gl.glPopMatrix(); // Planet 1 

      } gl.glPopMatrix(); // sun

      // glu.gluDeleteQuadric( qobj0 );
    }


}
