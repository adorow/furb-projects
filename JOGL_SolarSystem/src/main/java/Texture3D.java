import com.jogamp.opengl.GL2;

import java.nio.ByteBuffer;

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
   
  public void init( GL2 gl )
    {
      gl.glDisable( GL2.GL_BLEND );
      gl.glShadeModel( GL2.GL_FLAT );

      gl.glEnable( GL2.GL_TEXTURE_3D );

      // Texture environment: 
      // Set how are textures merged into (colored) fragments.
      gl.glTexEnvf( GL2.GL_TEXTURE_ENV,
		    GL2.GL_TEXTURE_ENV_MODE,
		    //GL.GL_MODULATE );
		    //GL.GL_BLEND );
		    GL2.GL_REPLACE );
                    //GL.GL_DECAL );

      // Add texture highlights.
      gl.glLightModeli( GL2.GL_LIGHT_MODEL_COLOR_CONTROL,
			GL2.GL_SINGLE_COLOR );
      //GL.GL_SEPARATE_SPECULAR_COLOR );

      gl.glGenTextures( nbTexture, textureId, 0 );

      // Bind only texture.
      gl.glBindTexture( GL2.GL_TEXTURE_3D, textureId[0] );

      setTextureParameter( gl ); 

      createData();

      loadData( gl );
    }


  private void setTextureParameter( GL2 gl )
    {
      // Filtering.
      gl.glTexParameteri( GL2.GL_TEXTURE_3D,
			  GL2.GL_TEXTURE_MIN_FILTER,
			  GL2.GL_LINEAR );
                          //GL.GL_NEAREST );
      
      gl.glTexParameteri( GL2.GL_TEXTURE_3D,
			  GL2.GL_TEXTURE_MAG_FILTER,
			  GL2.GL_LINEAR );
                          //GL.GL_NEAREST );	
      // Wrap.
      gl.glTexParameterf( GL2.GL_TEXTURE_3D,
			  GL2.GL_TEXTURE_WRAP_S,
			  //GL.GL_REPEAT );
			  GL2.GL_CLAMP );
      gl.glTexParameterf( GL2.GL_TEXTURE_3D,
			  GL2.GL_TEXTURE_WRAP_T,
			  //GL.GL_REPEAT );
			  GL2.GL_CLAMP );
      gl.glTexParameterf( GL2.GL_TEXTURE_3D,
			  GL2.GL_TEXTURE_WRAP_R,
			  //GL.GL_REPEAT );
			  GL2.GL_CLAMP );
    }
  

  private void createData()
    {
//      data = BufferUtil.newByteBuffer
      data = ByteBuffer.allocate
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


  private void loadData( GL2 gl )
    {

      gl.glTexImage3D( 
	GL2.GL_TEXTURE_3D,
	0, // Mipmap level.
	GL2.GL_RGBA,// GL.GL_RGBA, // Internal Texel Format,
	width, height,depth,
	0, //Border
	GL2.GL_RGBA, // External format from image,
	GL2.GL_UNSIGNED_BYTE,
	data.rewind() // Imagedata as ByteBuffer
	);
    }

  float tick = 0;
  
  public void draw( GL2 gl )
    {
      tick += 0.05;
      
      if( tick > 1f) { tick = 0f; }

      gl.glEnable( GL2.GL_TEXTURE_3D );
      gl.glBindTexture( GL2.GL_TEXTURE_3D, textureId[0] );

	gl.glBegin( GL2.GL_QUADS ); {
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
