import com.jogamp.opengl.GL2;

class Blend {

  //////////////// Variables /////////////////////////

  DrawingBasics drawingBasics = new DrawingBasics();
  
  Light light = new Light();

  ///////////////// Functions /////////////////////////
  
  public Blend() {}

  public void init( GL2 gl ) 
    {
      //gl.glDisable(GL2.GL_BLEND);
      gl.glEnable(GL2.GL_BLEND);

      gl.glBlendFunc(
	GL2.GL_SRC_ALPHA, 
	GL2.GL_DST_ALPHA
	//GL2.GL_ONE_MINUS_SRC_ALPHA
	);
    }


  public void drawBlendPlane( GL2 gl, Bezier bezier ) 
    {
      
      gl.glEnable(GL2.GL_BLEND);
      gl.glBlendFunc(
	//GL2.GL_ONE_MINUS_DST_ALPHA,
	GL2.GL_SRC_ALPHA, 
	GL2.GL_DST_ALPHA
	//GL2.GL_ONE_MINUS_SRC_ALPHA
	);
      gl.glColor4f( 0.3f, 0.3f, 0.3f, 0.5f );
      light.setSomeGrayMaterial( gl, GL2.GL_FRONT_AND_BACK );

      // Draw plane.
      final int s=4;
      gl.glRecti( -s, -s, s, s ); 

      gl.glDisable(GL2.GL_BLEND);
    }


  public void drawColredCircles( GL2 gl)
    {
      gl.glShadeModel( GL2.GL_SMOOTH );

//       gl.glBlendFunc(
// 	//GL2.GL_SRC_ALPHA, 
// 	//GL2.GL_DST_ALPHA
// 	//GL2.GL_ONE_MINUS_SRC_ALPHA
// 	);

      gl.glBlendFuncSeparate(
	//GL2.GL_SRC_ALPHA, 
	GL2.GL_ONE_MINUS_DST_ALPHA,
	GL2.GL_DST_ALPHA,
	//GL2.GL_ONE_MINUS_SRC_ALPHA,
	GL2.GL_ONE, 
	//GL2.GL_DST_ALPHA
	GL2.GL_ZERO
	);

      gl.glBlendFuncSeparate(
	//GL2.GL_SRC_ALPHA, 
	GL2.GL_SRC_ALPHA,
	GL2.GL_DST_ALPHA,
	//GL2.GL_ONE_MINUS_SRC_ALPHA,
	GL2.GL_ONE, 
	//GL2.GL_DST_ALPHA
	GL2.GL_ONE
	);

      gl.glColor4f( 0f, 1f, 0f, 1f ); // green
      drawingBasics.drawCircle( gl, -0.25f, 0.25f, 0f );
      gl.glColor4f( 0f, 0f, 1f, 1f ); //blue
      drawingBasics.drawCircle( gl, 0.0f, -0.25f, 0.1f );
      gl.glColor4f( 1f, 0f, 0f, 1f ); //red
      drawingBasics.drawCircle( gl, 0.25f, 0.25f, -0.1f );

/*
  gl.glBlendFuncSeparate(
  //GL2.GL_SRC_ALPHA, 
  GL2.GL_SRC_ALPHA,
  GL2.GL_DST_ALPHA,
  //GL2.GL_ONE_MINUS_SRC_ALPHA,
  GL2.GL_ONE, 
  //GL2.GL_DST_ALPHA
  GL2.GL_ONE
  );

  gl.glColor4f( 0f, 1f, 0f, 0.5f ); // green
  drawingBasics.drawCircle( gl, -0.25f, 0.25f, 0f );
  gl.glColor4f( 0f, 0f, 1f, 0.5f ); //blue
  drawingBasics.drawCircle( gl, 0.0f, -0.25f, 0.1f );
  gl.glColor4f( 1f, 0f, 0f, 0.5f ); //red
  drawingBasics.drawCircle( gl, 0.25f, 0.25f, -0.1f );
*/
//       gl.glColor4f( 1f, 0f, 0f, 0.5f ); //red
//       drawingBasics.drawCircle( gl, 0.25f, -0.5f, 0.2f );
//       gl.glColor4f( 0f, 1f, 0f, 0.5f ); // green
//       drawingBasics.drawCircle( gl, -0.25f, -0.5f, 0.3f );
    }

}
