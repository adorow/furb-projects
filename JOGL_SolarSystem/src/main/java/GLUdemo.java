import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

class GLUdemo 
{

  ///////////////// Functions /////////////////////////

  public GLUdemo() {}

  public void draw(GL2 gl, GLU glu )
   {
      GLUquadric qobj0 = glu.gluNewQuadric();
      glu.gluQuadricDrawStyle( qobj0, GLU.GLU_FILL );
      glu.gluQuadricNormals( qobj0, GLU.GLU_SMOOTH );

      glu.gluSphere( qobj0, 1.0f, 30, 30) ;
      //glu.gluCylinder( qobj0, 1.0f, 0.3f, 1.0f,10, 5 );
      //glu.gluDisk( qobj0, 0.8f, 1.5f, 20, 5 );
      //glu.gluPartialDisk( qobj0, 0.8f, 1.4f, 20, 10, 560, 240 );

      glu.gluDeleteQuadric( qobj0 );
  }
}
