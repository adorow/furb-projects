import javax.media.opengl.*;

class Blend {

  //////////////// Variables /////////////////////////

  DrawingBasics drawingBasics = new DrawingBasics();
  
  Light light = new Light();

  ///////////////// Functions /////////////////////////
  
  public Blend() {}

  public void init( GL gl ) 
    {
      //gl.glDisable(GL.GL_BLEND);
      gl.glEnable(GL.GL_BLEND);

      gl.glBlendFunc(
	GL.GL_SRC_ALPHA, 
	GL.GL_DST_ALPHA
	//GL.GL_ONE_MINUS_SRC_ALPHA
	);
    }


  public void drawBlendPlane( GL gl, Bezier bezier ) 
    {
      
      gl.glEnable(GL.GL_BLEND);
      gl.glBlendFunc(
	//GL.GL_ONE_MINUS_DST_ALPHA,
	GL.GL_SRC_ALPHA, 
	GL.GL_DST_ALPHA
	//GL.GL_ONE_MINUS_SRC_ALPHA
	);
      gl.glColor4f( 0.3f, 0.3f, 0.3f, 0.5f );
      light.setSomeGrayMaterial( gl, GL.GL_FRONT_AND_BACK );

      // Draw plane.
      final int s=4;
      gl.glRecti( -s, -s, s, s ); 

      gl.glDisable(GL.GL_BLEND);
    }


  public void drawColredCircles( GL gl) 
    {
      gl.glShadeModel( GL.GL_SMOOTH );

//       gl.glBlendFunc(
// 	//GL.GL_SRC_ALPHA, 
// 	//GL.GL_DST_ALPHA
// 	//GL.GL_ONE_MINUS_SRC_ALPHA
// 	);

      gl.glBlendFuncSeparate(
	//GL.GL_SRC_ALPHA, 
	GL.GL_ONE_MINUS_DST_ALPHA,
	GL.GL_DST_ALPHA,
	//GL.GL_ONE_MINUS_SRC_ALPHA,
	GL.GL_ONE, 
	//GL.GL_DST_ALPHA
	GL.GL_ZERO
	);

      gl.glBlendFuncSeparate(
	//GL.GL_SRC_ALPHA, 
	GL.GL_SRC_ALPHA,
	GL.GL_DST_ALPHA,
	//GL.GL_ONE_MINUS_SRC_ALPHA,
	GL.GL_ONE, 
	//GL.GL_DST_ALPHA
	GL.GL_ONE
	);

      gl.glColor4f( 0f, 1f, 0f, 1f ); // green
      drawingBasics.drawCircle( gl, -0.25f, 0.25f, 0f );
      gl.glColor4f( 0f, 0f, 1f, 1f ); //blue
      drawingBasics.drawCircle( gl, 0.0f, -0.25f, 0.1f );
      gl.glColor4f( 1f, 0f, 0f, 1f ); //red
      drawingBasics.drawCircle( gl, 0.25f, 0.25f, -0.1f );

/*
  gl.glBlendFuncSeparate(
  //GL.GL_SRC_ALPHA, 
  GL.GL_SRC_ALPHA,
  GL.GL_DST_ALPHA,
  //GL.GL_ONE_MINUS_SRC_ALPHA,
  GL.GL_ONE, 
  //GL.GL_DST_ALPHA
  GL.GL_ONE
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
