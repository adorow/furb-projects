import javax.media.opengl.*;
import javax.media.opengl.glu.*;

// 2D and 3D Bezier curves (and surfaces).
// Analytical curves.
// NURBS curves are not supproted yet
// (maybe they go in here later).
//
class Bezier {

  //////////////// Variables /////////////////////////

  // To switch the directin of an amimation.
  float animSign = 0.3f;

  //GLUnurbs theNurb;

  ///////////////// Functions /////////////////////////

  public Bezier() {}


  public void drawSpiral( GL gl ) 
    {
      float r=0.5f;
      float x, y, z;
      gl.glBegin(GL.GL_POINTS); {
	for( float a = 0;  a <= 1440; a+=5 ) {
	  r -= 0.5f / 300;
	  float ang = (float) Math.toRadians( a );
	  gl.glVertex3f(  
	    (float) (r*Math.cos( ang )),
	    (float) a/1440f -0.5f,
	    (float) (r*Math.sin( ang ))
	    );
	}
      } gl.glEnd();
    }

  public void draw2DBezier( GL gl ) 
    {
      final int nbCtrlPoints = 6;
      final int sizeCtrlPoints = nbCtrlPoints * 3;
	
      float a = 1.0f;
      float b = 0.f;
      float ctrlPoints[] = {
	0f, 0f, 0f,  //ddd
	-a, -a, 0f,  
	-b, +a, 0f, 
	+b, -a, 0f, //ddd
	+a, +a, 0f,
	+b, -a, 0f 

      };
	
      // Check if the ctrl points array has a legal size.
      if( ctrlPoints.length != sizeCtrlPoints ) {
	System.out.println("ERROR ctrlPoints\n"); };

      gl.glMap1f( GL.GL_MAP1_VERTEX_3, 
		  0.0f, 1.0f, 3, 
		  nbCtrlPoints, ctrlPoints, 0 );

      gl.glEnable( GL.GL_MAP1_VERTEX_3 );


      // Draw ctrlPoints.
      gl.glBegin(GL.GL_POINTS); {
	for( int i=0; i < sizeCtrlPoints; i+=3 ) {
	  gl.glVertex3f( ctrlPoints[i],
			 ctrlPoints[i+1],
			 ctrlPoints[i+2] );
	}
      } gl.glEnd();


      gl.glMapGrid1f( 20, 0f, 1f);
      gl.glEvalMesh1( GL.GL_POINT, 0, 20 );
      /*
	
      // Draw courve.
      gl.glBegin(GL.GL_POINTS); {
      for( float v=0; v <= 1; v += 0.01 ) {
      gl.glEvalCoord1f( v );
      }
      } gl.glEnd();

      */
    }


//     public void draw3DNURBS( GL gl, GLU glu ) 
//     {
// 	int nbSamplePts = 10;
// 	gl.glMapGrid2f( nbSamplePts, 0f, 1f,
// 			nbSamplePts, 0f, 1f);
// 	gl.glEvalMesh2( GL.GL_FILL, 
// 			0, nbSamplePts, 
// 			0, nbSamplePts );
// 	float knots[] = {
// 	    0.0, 0.0, 0.0, 0.0, 
// 	    1.0, 1.0, 1.0, 1.0
// 	};
// 	//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
// 	//    glPushMatrix();
// 	//      glRotatef(330.0, 1.,0.,0.);
// 	//  glScalef (0.5, 0.5, 0.5);
//         glu.gluBeginSurface(theNurb);
//         glu.gluNurbsSurface
// 	    (theNurb, 
// 	     8, knots,
// 	     8, knots,
// 	     4 * 3,
// 	     3,
// 	     // &ctlpoints[0][0][0], 
// 	     ctrlPoints, 
// 	     4, 4,
// 	     GL_MAP2_VERTEX_3 );
//         glu.gluEndSurface(theNurb);
//     }


  //////////////////////// NURBS /////////////////////

// ... one day there will be NURBS !
//     public void initNURBS( GL gl, GLU glu )
//     {
// 	//init_surface();
// 	//theNurb = glu.gluNewNurbsRenderer();
// 	glu.gluNurbsProperty( theNurb, 
// 			      glu.GLU_SAMPLING_TOLERANCE, 
// 			      25.0);
// 	glu.gluNurbsProperty(theNurb, GLU_DISPLAY_MODE, GLU_FILL);
//     }


  //////////////////////// data /////////////////////

  final int nbCtrlPoints = 25;
  final float s = 2f;
  float ctrlPoints[] = {

    -2f*s,-2f*s, 0f*s,  
    -1f*s,-2f*s, 0f*s,  
    0f*s,-2f*s, 0f*s,  
    1f*s,-2f*s, 0f*s,  
    2f*s,-2f*s, 0f*s,  

    -2f*s,-1f*s, 0f*s,  
    -1f*s,-1f*s, 0f*s,  
    0f*s,-1f*s, 0f*s,  
    1f*s,-1f*s, 0f*s,  
    2f*s,-1f*s, 0f*s,  

    -2f*s, 0f*s, 0f*s,  
    -1f*s, 0f*s, 0f*s,  
    0f*s, 0f*s, 0f*s,  
    1f*s, 0f*s, 0f*s,  
    2f*s, 0f*s, 0f*s,  

    -2f*s,+1f*s, 0f*s,  
    -1f*s,+1f*s, 0f*s,  
    0f*s,+1f*s, 0f*s,  
    1f*s,+1f*s, 0f*s,  
    2f*s,+1f*s, 0f*s,  

    -2f*s,+2f*s, 0f*s,  
    -1f*s,+2f*s, 0f*s,  
    0f*s,+2f*s, 0f*s,  
    1f*s,+2f*s, 0f*s,  
    2f*s,+2f*s, 0f*s,  
  };

  float ctrlPointsOrg[] = {
	
    0f, 0f, 0f,  
    1f, 0f, 0f,  
    2f, 0f, 0f,  
    3f, 0f, 0f,  

    0f, 0f, 1f,  
    1f, 1f, 1f,  
    2f, 1f, 1f,  
    3f, 0f, 1f,  

    0f, 0f, 2f,  
    1f, 1f, 2f,  
    2f, 1f, 2f,  
    3f, 0f, 2f,  

    0f, 0f, 3f,  
    1f, 0f, 3f,  
    2f, 0f, 3f,  
    3f, 0f, 3f,  
  };

}
