import javax.media.opengl.*;
//import java.lang.Math.*;

class DrawingBasics {

  //////////////// Variables /////////////////////////

  ///////////////// Functions /////////////////////////

  DrawingBasics() {}

  public void initAliasingAndFog
  ( GL gl, boolean aliasing, boolean fog ) 
    {
      if( aliasing ) {
	//gl.glEnable( GL.GL_POINT_SMOOTH );
	gl.glEnable( GL.GL_LINE_SMOOTH );
	//gl.glDisable( GL.GL_LINE_SMOOTH );
      }
      
      if( fog ) {
	gl.glEnable( GL.GL_FOG );
	gl.glHint( GL.GL_FOG_HINT, GL.GL_NICEST );
	//gl.glHint( GL.GL_FOG_HINT, GL.GL_FASTEST );
	
	//gl.glFogi( GL.GL_FOG_MODE, GL.GL_EXP );
	//gl.glFogi( GL.GL_FOG_MODE, GL.GL_EXP2 );
	//gl.glFogi( GL.GL_FOG_MODE, GL.GL_LINEAR );
	
	gl.glFogf( GL.GL_FOG_DENSITY, 1.0f );
	gl.glFogf( GL.GL_FOG_START, 0 );
	gl.glFogf( GL.GL_FOG_END, 4);
	float fogColor[] = {0, 0, 0, 0.5f };
	gl.glFogfv( GL.GL_FOG_COLOR, fogColor, 0 );

	// Select how fog distance is calculated.
	//gl.glFogi( GL.GL_FOG_COORD_SRC, GL.GL_FOG_COORD );
	gl.glFogi( GL.GL_FOG_COORD_SRC, GL.GL_FRAGMENT_DEPTH );
      }
    }

  public void drawPoints( GL gl ) 
    {
      float val[] = new float[2];
      gl.glGetFloatv( GL.GL_POINT_SIZE_RANGE, val, 0 );
      //System.out.println("min="+val[0]+" max="+val[1]);

      gl.glPointSize( 100.0f );
      gl.glBegin(GL.GL_POINTS);

      gl.glVertex3f(  0.0f,   -0.f, 0);
      gl.glVertex3f( 0.25f,  0.25f, 0);
      gl.glVertex3f( 0.25f, -0.25f, 0);
      gl.glVertex3f(-0.25f,  0.25f, 0);
      gl.glVertex3f(-0.25f, -0.25f, 0);

      gl.glEnd();
    }

  public void draw2DPoints( GL gl ) 
    {
      gl.glBegin (GL.GL_POINTS);
      gl.glVertex2i (1,0);
      gl.glVertex2i (0,1);
      gl.glVertex2i (0,0);
      gl.glVertex2i (1,1);
      gl.glEnd ();
    }

  public void drawTriangle( GL gl ) 
    {
      gl.glBegin(GL.GL_TRIANGLES); {
	gl.glVertex3f(0.25f, 0.25f, 0);
	gl.glVertex3f(0.5f, 0.25f, 0);
	gl.glVertex3f(0.25f, 0.5f, 0);
      } gl.glEnd();
    }


  public void drawColorTriangle( GL gl ) 
    {
      gl.glBegin(GL.GL_TRIANGLES);

      gl.glColor3f(1, 0, 0);
      gl.glVertex3f(0.25f, 0.25f, 0);

      gl.glColor3f(0, 1, 0);
      gl.glVertex3f(0.5f, 0.25f, 0);

      gl.glColor3f(0, 0, 1);
      gl.glVertex3f(0.25f, 0.5f, 0);

      gl.glEnd();
    }

  public void drawWhiteTriangle( GL gl ) 
    {
      gl.glColor3f(1, 1, 1);
      drawTriangle( gl );
    }

  public void drawTriangle
  ( GL gl, float xc, float yc, float zc ) 
    {
      gl.glPushMatrix();
      gl.glTranslatef( xc, yc, zc );
      drawWhiteTriangle( gl );
      gl.glPopMatrix();
    }


  public void drawHouse( GL gl ) 
    {
      short stripple = (short)0x33cc;
      final float thickLineWidth = 1.0f;
      final float thinLineWidth  = 1.0f;
      gl.glLineStipple( 1, stripple );
      gl.glEnable( GL.GL_LINE_STIPPLE );

      float a = 0.25f;
      float b = 0.45f;
      gl.glLineWidth( 6.0f );
      //gl.glBegin(GL.GL_LINES);
      //gl.glBegin(GL.GL_LINE_STRIP);
      gl.glBegin(GL.GL_LINE_LOOP); {
	gl.glVertex3f(-a, -a, 0);
	gl.glVertex3f( a, -a, 0);
	gl.glVertex3f(-a,  a, 0);
	gl.glVertex3f( a,  a, 0);
	gl.glVertex3f( 0,  b, 0);
	gl.glVertex3f(-a,  a, 0);
	gl.glVertex3f(-a, -a, 0);
	gl.glVertex3f( a,  a, 0);
	gl.glVertex3f( a, -a, 0);
      } gl.glEnd();

      gl.glDisable( GL.GL_LINE_STIPPLE );
    }



  public void draw3DHouseLines( GL gl ) 
    {
      float a = 0.25f;
      float b = 0.45f;
      float c = 1.0f;
      gl.glLineWidth( 1.0f );
      gl.glColor3f(1, 1, 1);

      // Front and Back
      for( float z=-c; z<=0; z+=c ) {
	gl.glBegin(GL.GL_LINE_LOOP); {
	  gl.glVertex3f(-a, -a, z);
	  gl.glVertex3f( a, -a, z);
	  gl.glVertex3f( a,  a, z);
	  gl.glVertex3f( 0,  b, z);
	  gl.glVertex3f(-a,  a, z);
	} gl.glEnd();
      }
      // Front-back connections.
      gl.glBegin(GL.GL_LINES); {
	gl.glVertex3f(-a, -a, -c);
	gl.glVertex3f(-a, -a, 0);
	gl.glVertex3f( a, -a, -c);
	gl.glVertex3f( a, -a, 0);
	gl.glVertex3f( a,  a, -c);
	gl.glVertex3f( a,  a, 0);
	gl.glVertex3f( 0,  b, -c);
	gl.glVertex3f( 0,  b, 0);
	gl.glVertex3f(-a,  a, -c);
	gl.glVertex3f(-a,  a, 0);
      } gl.glEnd();
      
    }




  public void drawCube( GL gl, float a ) 
    {

      final float thickLineWidth = 1.0f;
      final float thinLineWidth  = 1.0f;

      gl.glBegin(GL.GL_LINE_STRIP);

      gl.glVertex3f(-a, -a, a);
      gl.glVertex3f(-a, +a, a);
      gl.glVertex3f(+a, +a, a);
      gl.glVertex3f(+a, -a, a);
      gl.glVertex3f(-a, -a, a);

      gl.glVertex3f(-a, -a, -a);
      gl.glVertex3f(-a, +a, -a);
      gl.glVertex3f(+a, +a, -a);
      gl.glVertex3f(+a, -a, -a);
      gl.glVertex3f(-a, -a, -a);

      gl.glEnd();

      gl.glBegin(GL.GL_LINES);

      gl.glVertex3f(-a, -a, +a);
      gl.glVertex3f(-a, -a, -a);

      gl.glVertex3f(-a, +a, +a);
      gl.glVertex3f(-a, +a, -a);

      gl.glVertex3f(+a, +a, +a);
      gl.glVertex3f(+a, +a, -a);

      gl.glVertex3f(+a, -a, +a);
      gl.glVertex3f(+a, -a, -a);

      gl.glEnd();
    }


  public void drawCircle
  ( GL gl, float xc, float yc, float zc ) 
    {
      double r = 0.9f; // Radius.

      //gl.glBegin( GL.GL_TRIANGLE_STRIP ); {
      //gl.glPolygonMode( GL.GL_FRONT_AND_BACK, GL.GL_FILL );
      //gl.glBegin( GL.GL_POLYGON ); {
      gl.glBegin( GL.GL_TRIANGLE_FAN ); {
	//gl.glColor4f( 0f, 0f, 1f, 0.5f );
	gl.glVertex3f( xc, yc, zc ); // Center.
	//gl.glColor4f( 1f, 1f, 0f, 0.5f );
	for( float a = 0;  a <= 360; a+=10 ) {
	  float ang = (float) Math.toRadians( a );
	  float x = xc + (float) (r*Math.cos( ang ));
	  float y = yc + (float) (r*Math.sin( ang ));
	  float z = zc + 0f;
	  //System.out.println( "x:"+x+" y:"+y+" z:"+z );//ddd
	  gl.glVertex3f( x, y, z );
	}

	//gl.glEdgeFlag( true );
      } gl.glEnd();

    }


  public void drawHouseNoInnerEdge( GL gl ) 
    {
      float a = 0.25f;
      float b = 0.45f;

      gl.glPolygonMode( GL.GL_FRONT_AND_BACK, GL.GL_LINE );

      gl.glBegin(GL.GL_POLYGON);
      gl.glEdgeFlag( true );
      gl.glVertex3f(-a, -a, 0);
      gl.glEdgeFlag( false );
      gl.glVertex3f( a, -a, 0);
      gl.glVertex3f(-a,  a, 0);
      gl.glEdgeFlag( true );
      gl.glVertex3f( a,  a, 0);
      gl.glVertex3f( 0,  b, 0);
      gl.glVertex3f(-a,  a, 0);
      gl.glEdgeFlag( false );
      gl.glVertex3f(-a, -a, 0);
      gl.glEdgeFlag( true );
      gl.glVertex3f( a,  a, 0);
      gl.glVertex3f( a, -a, 0);
      gl.glEnd();

      double r = 0.3f; // Radius.
    }


////////////////// Sphere ///////////////////////


  public void drawSphere
  ( GL gl, 
    float xc, float yc, float zc, // Center.
    final float r, // Radius.
    boolean useCurrentColor ) 
    {
      final float dA = 10f; // Delta Alpha.
      final float dP = 10f; // Delta Phi.
      float x,y,z;
      float red, green, blue;
      final boolean randomColor = false;
      
      //gl.glPolygonMode( gl.GL_FRONT_AND_BACK, gl.GL_LINE );
      gl.glBegin( gl.GL_QUAD_STRIP ); {
	
	for( float phi = -90;  phi <= 90; phi += dP ) {

	  float p = (float) Math.toRadians( phi );
	  float sinP = (float)Math.sin( p );
	  float cosP = (float)Math.cos( p );
	  float pdP = (float) Math.toRadians( phi+dP );
	  float sinPdP = (float)Math.sin( pdP );
	  float cosPdP = (float)Math.cos( pdP );

	  for( float alpha=0;  alpha <= 360; alpha += dA ) {

	    if( randomColor ) {
	      //...todo
	    } else {
	      //...todo
	    }

	    if( ! useCurrentColor ) {
	      //...todo
	    }

	    float a = (float) Math.toRadians( alpha );
	    float sinA = (float)Math.sin( a );
	    float cosA = (float)Math.cos( a );
	    
	    x = cosP * cosA;
	    y = sinP;
	    z = cosP * sinA;
	    gl.glNormal3f( x, y, z );	    
	    x = xc + r * x;
	    y = yc + r * y;
	    z = zc + r * z;
	    gl.glVertex3f( x, y, z );
	    //System.out.println( "x:"+x+" y:"+y+" z:"+z );//ddd

	    x = cosPdP * cosA;
	    y = sinPdP;
	    z = cosPdP * sinA;
	    gl.glNormal3f( x, y, z );
	    x = xc + r * x;
	    y = yc + r * y;
	    z = zc + r * z;
	    gl.glVertex3f( x, y, z );
	    //System.out.println( "x:"+x+" y:"+y+" z:"+z );//ddd

	  }
	}
      } gl.glEnd();
    }


/////////////////////////////////////////////////////

  public void drawAxis( GL gl, boolean withGrid ) 
    {
      final float length = 0.5f;
      final float gridStep = 0.4f;
      short stripple = (short)0xa2cd;
      short strippleGrid = (short)0xaaaa;
      final float thickLineWidth = 3.0f;
      final float thinLineWidth  = 1.0f;

      gl.glLineStipple( 1, stripple );

      gl.glEnable( GL.GL_LINE_STIPPLE );
      // X.
      gl.glColor3f(1, 0, 0);
      gl.glLineWidth( thinLineWidth );
      gl.glEnable( GL.GL_LINE_STIPPLE );
      gl.glBegin(GL.GL_LINES); {
	gl.glVertex3f( -length, 0f, 0f );
	gl.glVertex3f( 0f, 0f, 0f );
      } gl.glEnd();
      gl.glDisable( GL.GL_LINE_STIPPLE );
      //
      gl.glColor3f(0.9f, 0.1f, 0.1f);
      gl.glLineWidth( thickLineWidth );
      gl.glBegin(GL.GL_LINES);{
	gl.glVertex3f( 0f, 0f, 0f );
	gl.glVertex3f( length, 0f, 0f );
      } gl.glEnd();

      // Y.
      gl.glColor3f(0.1f, 0.8f, 0.1f);
      gl.glLineWidth( thinLineWidth );
      gl.glEnable( GL.GL_LINE_STIPPLE );
      gl.glBegin(GL.GL_LINES); {
	gl.glVertex3f( 0f, -length, 0f );
	gl.glVertex3f( 0f, 0f, 0f );
      } gl.glEnd();
      gl.glDisable( GL.GL_LINE_STIPPLE );
      gl.glColor3f(0, 1, 0);
      gl.glLineWidth( thickLineWidth );
      gl.glBegin(GL.GL_LINES);{
	gl.glVertex3f( 0f, 0f, 0f );
	gl.glVertex3f( 0f, length, 0f );
      } gl.glEnd();
      // Z.
      gl.glColor3f(0.1f, 0.1f, 0.8f);
      gl.glLineWidth( thinLineWidth );	
      gl.glEnable( GL.GL_LINE_STIPPLE );
      gl.glLineStipple( 1, stripple );
      gl.glBegin(GL.GL_LINES); {
	gl.glVertex3f( 0f, 0f, -length );
	gl.glVertex3f( 0f, 0f, 0f );
      } gl.glEnd();
      gl.glDisable( GL.GL_LINE_STIPPLE );
      gl.glColor3f(0, 0, 1);
      gl.glLineWidth( thickLineWidth );
      gl.glBegin(GL.GL_LINES);{
	gl.glVertex3f( 0f, 0f, 0f );
	gl.glVertex3f( 0f, 0f, length );
      } gl.glEnd();

      gl.glLineWidth( thinLineWidth );

      if( withGrid ) 
      {
	// Draw grid.
	gl.glEnable( GL.GL_LINE_STIPPLE );
	gl.glLineStipple( 1, strippleGrid );
	gl.glBegin(GL.GL_LINES);
	for( float s=-length; s<=length; s+=gridStep )
	{
	  // Do not draw grid near axis.
	  if( Math.abs( s ) < gridStep ) { continue; }

	  gl.glColor3f(1, 0, 0); // X
	  gl.glVertex3f( s, -length, 0f );
	  gl.glVertex3f( s, +length, 0f );
	  gl.glVertex3f( s, 0f, -length );
	  gl.glVertex3f( s, 0f, +length );
	  gl.glColor3f(0, 1, 0); // Y
	  gl.glVertex3f( -length, s, 0f );
	  gl.glVertex3f( +length, s, 0f );
	  gl.glVertex3f( 0f, s, -length );
	  gl.glVertex3f( 0f, s, +length );
	  gl.glColor3f(0, 0, 1); // Z
	  gl.glVertex3f( -length, 0f, s );
	  gl.glVertex3f( +length, 0f, s );
	  gl.glVertex3f( 0f, -length, s );
	  gl.glVertex3f( 0f, +length, s );
	}
	gl.glEnd();
	gl.glDisable( GL.GL_LINE_STIPPLE );

      }

      gl.glLineWidth( 1.0f );

    }

//////////////////////////////////////////////////////

}
