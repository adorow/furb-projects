import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*; // GLUT, FPSAnimator
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
//import java.nio.ByteOrder;

import java.util.Random;

class VertexArray {

  //////////////// Constants /////////////////////////

  // Number of Points in the array.
  final int nbPoints = 20000;
  // Bytes allocted in buffer to hold points or colors.
  final int nbValues = nbPoints * 3;

  // Two Vertex Buffers Objects (VBOs)n for points an color.
  final int nbVBO = 2;
  // Use VBO if available.
  boolean useVBO = false; //true;

  //////////////// Variables /////////////////////////

  // We use a buffer and an array for the vertex data
  // to more compatible with C.
  FloatBuffer points;
  float[] pointsData;
  FloatBuffer colors;
  float[] colorsData;
  
  Random random = new Random();

  // Array to hold Vertex Buffers Objects (VBOs).
  int[] VBO = new int[ nbVBO ];


  ///////////////// Functions /////////////////////////

  public void init( GL gl )
    {
      gl.glEnableClientState( GL.GL_VERTEX_ARRAY );
      gl.glEnableClientState( GL.GL_COLOR_ARRAY );
      //gl.glEnableClientState( GL.GL_NORMAL_ARRAY );

      if( useVBO ) { initVBO( gl  ); }
      initArrayData();
      createArrayData( gl );
      initDataPointers( gl );
    }


  public void deleteVBO( GL gl )
    {
      // Switch back to using normal VertexArrays.
      gl.glBindBuffer( GL.GL_ARRAY_BUFFER, 0 );
      gl.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, 0 );
      gl.glDeleteBuffers( nbVBO, VBO, 0 );
    }  


  public void initVBO ( GL gl )
    {
      // Check for VBO support.

      // Check version.
      String versionStr = gl.glGetString( GL.GL_VERSION );
      System.out.println( "GL version:"+versionStr ); //ddd
      versionStr = versionStr.substring( 0, 4);
      float version = new Float( versionStr ).floatValue();
      boolean versionOK = ( version >= 1.59f ) ? true : false;
      System.out.println( "GL version:"+versionStr+"  ->"+versionOK ); //ddd

      // Check if extension is available.
      boolean extensionOK = gl.isExtensionAvailable
	("GL_ARB_vertex_buffer_object");
      System.out.println( "VBO extension: "+extensionOK ); //ddd
      
      // Check for VBO functions.
      boolean functionsOK = 
	gl.isFunctionAvailable("glGenBuffersARB") &&
	gl.isFunctionAvailable("glBindBufferARB") &&
	gl.isFunctionAvailable("glBufferDataARB") &&
	gl.isFunctionAvailable("glDeleteBuffersARB");      
     System.out.println( "Functions: "+ functionsOK); //ddd

      if( ! extensionOK || ! functionsOK ) 
      {
         // VBO not supported.
	System.out.println( "VBOs not supported." ); //ddd 
	useVBO = false;
	return;
      }
      
      // We are fine.
      gl.glGenBuffersARB( nbVBO, VBO, 0 );
    }


  private void initArrayData()
    {
      // Create data points on the surface of a cube.
      //
      // Points.
      pointsData = new float[ nbValues ];
      points = BufferUtil.newFloatBuffer( nbValues );
      // Colors.
      colorsData = new float[ nbValues ];
      colors = BufferUtil.newFloatBuffer( nbValues );
    }


  private void createArrayData( GL gl )
    {
      for( int i=0; i < nbPoints; i++ )
      {
	pointsData[ i*3 ] = (float)Math.random();
	pointsData[ i*3+1 ] = (float)Math.random();
	pointsData[ i*3+2 ] = (float)Math.random();
	// Cast on random side surface.
	int sel = random.nextInt( 3 );
	pointsData [ i + sel ] = random.nextInt( 2 );
	//
	colorsData[ i*3 ] = (float)Math.random();
	colorsData[ i*3+1 ] = (float)Math.random();
	colorsData[ i*3+2 ] = (float)Math.random();
	colorsData [ i*3 + sel ] = 1f;
      }
      // Points.
      points.put( pointsData, 0, nbValues );
      points.rewind();
      // Colors.
      colors.put( colorsData, 0, nbValues );
      colors.rewind();
    }


  private void initDataPointers( GL gl )
    {
      if( useVBO ) 
      {
	// Points.
	gl.glBindBufferARB( GL.GL_ARRAY_BUFFER_ARB, VBO[0] );
	// Copy data to the server into the VBO.
	gl.glBufferDataARB( GL.GL_ARRAY_BUFFER_ARB,
			 nbValues, points,
			 GL.GL_STATIC_DRAW_ARB );
	// Colors.
	gl.glBindBuffer( GL.GL_ARRAY_BUFFER, VBO[1] );
	// Copy data to the server into the VBO.
	gl.glBufferData( GL.GL_ARRAY_BUFFER,
			 nbValues, colors,
			 GL.GL_STATIC_DRAW );
      } 
      else // Use normal VertexArrays.
      {
	// Points.
	gl.glVertexPointer( 3, GL.GL_FLOAT, 0, points );
	// Colors.
	gl.glColorPointer( 3, GL.GL_FLOAT, 0, colors );
      }
    }


  //////////////////////// draw /////////////////////////

  public void draw( GL gl )
    {
      gl.glColor3f( 0f, 1f, 0f ); 

//       gl.glBegin( GL.GL_POINTS ); {
// 	for( int i=0; i < nbPoints; i++ ) 
// 	{
// 	  gl.glVertex3fv( pointsData, i*3 );
// // 	  gl.glVertex3f( points.get( i*3 ),
// // 			 points.get( i*3 +1),
// // 			 points.get( i*3 +2) );

// 	  //gl.glArrayElement( i );
// 	}      
//       } gl.glEnd();

      if( useVBO ) 
      {
	gl.glBindBuffer( GL.GL_ARRAY_BUFFER_ARB, VBO[0] );
	gl.glVertexPointer( 3, GL.GL_FLOAT, 0, 0 );
	gl.glBindBuffer( GL.GL_ARRAY_BUFFER, VBO[1] );
	gl.glColorPointer( 3, GL.GL_FLOAT, 0, 0 );
      }
      gl.glDrawArrays( GL.GL_POINTS, 0, nbPoints );
    }

}
