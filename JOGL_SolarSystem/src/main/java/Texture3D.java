import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ByteOrder;
import com.sun.opengl.util.BufferUtil;
import java.awt.image.*;
import javax.imageio.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*; // GLUT

class Texture3D {

  ///////////////// Constants /////////////////////////

  final int bytesPerPixel = 4;
  final int width  = 16;
  final int height = 8;
  final int depth  = 4;

  final int nbTexture = 1;  

  //////////////// Variables /////////////////////////

  ByteBuffer data;

  int[] textureId = new int[nbTexture];
  
  ///////////////// Functions /////////////////////////
  
  public Texture3D() {}
   
  public void init( GL gl )
    {
      gl.glDisable( GL.GL_BLEND );
      gl.glShadeModel( GL.GL_FLAT );

      gl.glEnable( GL.GL_TEXTURE_3D );

      // Texture environment: 
      // Set how are textures merged into (colored) fragments.
      gl.glTexEnvf( GL.GL_TEXTURE_ENV, 
		    GL.GL_TEXTURE_ENV_MODE, 
		    //GL.GL_MODULATE );
		    //GL.GL_BLEND );
		    GL.GL_REPLACE );
                    //GL.GL_DECAL );

      // Add texture highlights.
      gl.glLightModeli( GL.GL_LIGHT_MODEL_COLOR_CONTROL, 
			GL.GL_SINGLE_COLOR );
      //GL.GL_SEPARATE_SPECULAR_COLOR );

      gl.glGenTextures( nbTexture, textureId, 0 );

      // Bind only texture.
      gl.glBindTexture( GL.GL_TEXTURE_3D, textureId[0] );

      setTextureParameter( gl ); 

      createData();

      loadData( gl );
    }


  private void setTextureParameter( GL gl )
    {
      // Filtering.
      gl.glTexParameteri( GL.GL_TEXTURE_3D,
			  GL.GL_TEXTURE_MIN_FILTER,
			  GL.GL_LINEAR );
                          //GL.GL_NEAREST );
      
      gl.glTexParameteri( GL.GL_TEXTURE_3D,
			  GL.GL_TEXTURE_MAG_FILTER,
			  GL.GL_LINEAR );
                          //GL.GL_NEAREST );	
      // Wrap.
      gl.glTexParameterf( GL.GL_TEXTURE_3D, 
			  GL.GL_TEXTURE_WRAP_S, 
			  //GL.GL_REPEAT );
			  GL.GL_CLAMP );
      gl.glTexParameterf( GL.GL_TEXTURE_3D, 
			  GL.GL_TEXTURE_WRAP_T, 
			  //GL.GL_REPEAT );
			  GL.GL_CLAMP );
      gl.glTexParameterf( GL.GL_TEXTURE_3D, 
			  GL.GL_TEXTURE_WRAP_R, 
			  //GL.GL_REPEAT );
			  GL.GL_CLAMP );
    }
  

  private void createData()
    {
      data = BufferUtil.newByteBuffer
	( width * height * depth * bytesPerPixel ); 

      final int fw = 255/width;
      final int fh = 255/height;
      final int fd = 255/depth;

      for( int d=0; d < depth; d++ ) {
	for( int h=0; h < height; h++ ) {
	  for( int w=0; w < width; w++ ) {
	    
	    data.put( (byte) (w * fw) ); // r
	    data.put( (byte) (h * fh) ); // g
	    data.put( (byte) (d * fd) ); // b
	    data.put( (byte) 1 ); // a
	  }
	}
      }

      //System.out.println( "data:"+data); //ddd
    }


  private void loadData( GL gl )
    {

      gl.glTexImage3D( 
	GL.GL_TEXTURE_3D, 
	0, // Mipmap level.
	GL.GL_RGBA,// GL.GL_RGBA, // Internal Texel Format, 
	width, height,depth,
	0, //Border
	GL.GL_RGBA, // External format from image, 
	GL.GL_UNSIGNED_BYTE, 
	data.rewind() // Imagedata as ByteBuffer
	);
    }

  float tick = 0;
  
  public void draw( GL gl )
    {
      tick += 0.05;
      
      if( tick > 1f) { tick = 0f; }

      gl.glEnable( GL.GL_TEXTURE_3D );
      gl.glBindTexture( GL.GL_TEXTURE_3D, textureId[0] );

	gl.glBegin( GL.GL_QUADS ); {
	  gl.glTexCoord3f( 0f, 0f, tick );
	  gl.glVertex3f( 0, 0, 0f );
	  gl.glTexCoord3f( 0f, 1f, tick );
	  gl.glVertex3f( 0, 1f, 0f );
	  gl.glTexCoord3f( 1f, 1f, tick );
	  gl.glVertex3f( 1f, 1f, 0f );
	  gl.glTexCoord3f( 1f, 0f, tick );
	  gl.glVertex3f( 1f, 0, 0f );
	} gl.glEnd();      
    }

}
