import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.io.IOException;

class Texturing {

  ///////////////// Constants /////////////////////////

  // Todo: Java 1.5 supports enums for texture names.
  // Texture files.
  String[] textureFilename = {
    "texture/wood_Bg.bmp", 
    "texture/Wand.png",
    "texture/whiteMarbleBg.jpg",
    "texture/sky.bmp",
    "texture/fuji.jpg",
    "texture/NeHe.png"
  };
    
  // Number of textures.
  final int nbTexture = textureFilename.length;

  final boolean enableMipmapping = true;
  // Hardware accelerated mipmaps (if supported)
  // are auto-created when textures are loaded.
  // If this is enabled gluBuild2DMipmaps should 
  // not be called.
  final boolean enableHardwareAcceleratedMipmaps = false;
  // Decide if we have to build mipmapps.
  final boolean buildMipmaps = 
  enableMipmapping && ! enableHardwareAcceleratedMipmaps;

  // Some planes for automatic texture coordinates.
  final float xPlane[] = { 1f, 0, 0, 0 };
  final float yPlane[] = { 0, 1f, 0, 0 };
  final float zPlane[] = { 0, 0, 1f, 0 };
  final float noPlane[] = { 0, 0, 0, 0 };

  //////////////// Variables /////////////////////////

  // Texture IDs for opengl.
  int[] textureId = new int[nbTexture];

  ///////////////// Functions /////////////////////////
  
  public Texturing() {}

  public void init(GL2 gl, GLU glu )
    {
      gl.glDisable( GL2.GL_BLEND );
      gl.glShadeModel( GL2.GL_SMOOTH );

      gl.glEnable( GL2.GL_TEXTURE_2D );

      // Texture environment: 
      // Set how are textures merged into (colored) fragments.
      gl.glTexEnvf( GL2.GL_TEXTURE_ENV, 
		    GL2.GL_TEXTURE_ENV_MODE, 
		    GL2.GL_MODULATE );
		     //GL2.GL_BLEND );
		     //GL2.GL_REPLACE );
                     //GL2.GL_DECAL );

      // Add texture highlights.
      gl.glLightModeli( GL2.GL_LIGHT_MODEL_COLOR_CONTROL, 
			 //GL2.GL_SINGLE_COLOR );
			 GL2.GL_SEPARATE_SPECULAR_COLOR );

      // Get unused openGL texture IDs.
      gl.glGenTextures( nbTexture, textureId, 0 );

      // Texture-object instances with buffer to read and 
      // transform textures only used for init().
      TextureReader.Texture texture = null;

      // Loop over image texture files.
      for( int tID = 0; tID < nbTexture; tID++ ) 
      {
	texture = readTextureFile( textureFilename[tID] );
	printDebug( textureFilename[tID], texture );//ddd
	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[tID] ); // Bind current texture.
	loadTextureIntoOpenGL( gl, glu, texture, GL2.GL_TEXTURE_2D );
	setTextureParameter( gl ); // Set para for current texture.
      }
    }


  // Read texture form image from file with
  // formats like bmp, png, jpg, etc.
  private TextureReader.Texture  readTextureFile( String filename ) 
    {	
      TextureReader.Texture texture = null;
      try {
	texture = TextureReader.readTexture( filename );
      } catch( IOException e ) { 
	System.out.println(e); 
	e.printStackTrace();
	throw new RuntimeException(e);
      }
      //printDebug( filename, texture );//ddd
      return texture;
    }


  // Load texture data into openGL texturememory.
  private void loadTextureIntoOpenGL
  (GL2 gl, GLU glu, TextureReader.Texture texture, int target ) 
    {
      if( buildMipmaps ) 
      {
	// Create texture with all mipmap levels.
	glu.gluBuild2DMipmaps( 
	  target,
	  GL2.GL_RGB8, // Internal Texel Format, 
	  texture.getWidth(), texture.getHeight(), 
	  GL2.GL_RGB, // External format from image, 
	  GL2.GL_UNSIGNED_BYTE, 
	  texture.getPixels() // Imagedata
	  );
      } 
      else
      {
	gl.glTexImage2D( 
	  target, 
	  0, // Mipmap level.
	  GL2.GL_RGBA,// GL2.GL_RGBA, // Internal Texel Format, 
	  texture.getWidth(), texture.getHeight(), 
	  0, //Border
	  GL2.GL_RGB, // External format from image, 
	  GL2.GL_UNSIGNED_BYTE, 
	  texture.getPixels() // Imagedata
	  );
      }
    }


    private void setTextureParameter( GL2 gl )
      {

	if( enableHardwareAcceleratedMipmaps ) {
	  gl.glTexParameterf( GL2.GL_TEXTURE_2D, 
			      GL2.GL_GENERATE_MIPMAP, 
			      GL2.GL_TRUE ); 
	}

	// Texture mapping.
	//
	gl.glTexParameteri( GL2.GL_TEXTURE_2D,
			      GL2.GL_TEXTURE_MAG_FILTER,
			      //GL2.GL_LINEAR );
			      GL2.GL_NEAREST );
	//
	if( enableMipmapping )
	{
	  gl.glTexParameteri( GL2.GL_TEXTURE_2D,
			      GL2.GL_TEXTURE_MIN_FILTER,
			      GL2.GL_NEAREST_MIPMAP_NEAREST );
	  //GL2.GL_LINEAR_MIPMAP_LINEAR );
	}
	else // No mipmapping.
	{
	  gl.glTexParameteri( GL2.GL_TEXTURE_2D,
			      GL2.GL_TEXTURE_MIN_FILTER,
			      //GL2.GL_LINEAR );
			      GL2.GL_NEAREST );
	}

	// Wrap.
	gl.glTexParameterf( GL2.GL_TEXTURE_2D, 
			    GL2.GL_TEXTURE_WRAP_S, 
			    GL2.GL_REPEAT );
	gl.glTexParameterf( GL2.GL_TEXTURE_2D, 
			    GL2.GL_TEXTURE_WRAP_T, 
			    GL2.GL_REPEAT );

	// Enable anisotropic texture filtering extension.
	if( gl.isExtensionAvailable
	    ("GL_EXT_texture_filter_anisotropic") ) 
	{
	  float max[] = new float[1];
	  gl.glGetFloatv( GL2.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, max, 0 );
	  System.out.println("GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT = "+max[0]); //ddd
	  gl.glTexParameterf( GL2.GL_TEXTURE_2D, 
			      GL2.GL_TEXTURE_MAX_ANISOTROPY_EXT, 
			      max[0] );
	}
	
      }


    public void deleteAndDisable( GL2 gl )
      { 
	gl.glDeleteTextures( nbTexture, textureId, 0 );
	gl.glDisable( GL2.GL_TEXTURE_2D );
      }

  private void printDebug
  ( String filename, TextureReader.Texture texture ) 
    {
      System.out.println( "file:"+filename); 
      System.out.println( "texture:"+texture); 
      System.out.println( "height:"+texture.getHeight()); 
      System.out.println( "width:"+texture.getWidth()); 
      System.out.println( "pixels:"+texture.getPixels()); 
    }

    ////////////////////////// draw /////////////////

    public void drawBackground( GL2 gl) 
      {
	gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glPushMatrix();
	gl.glOrtho(0, 1, 0, 1, 0, 1);

	gl.glMatrixMode(GL2.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	// Position background pic relative to foreground
	gl.glTranslatef( 0.01f, -0.115f, 0.0f );
	
	// No depth buffer writes for background.
	gl.glDepthMask( false );

	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[4] );
	gl.glBegin( GL2.GL_QUADS ); {
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex2f( 0, 0 );
	  gl.glTexCoord2f( 0f, 1f );
	  gl.glVertex2f( 0, 1f );
	  gl.glTexCoord2f( 1f, 1f );
	  gl.glVertex2f( 1f, 1f );
	  gl.glTexCoord2f( 1f, 0f );
	  gl.glVertex2f( 1f, 0 );
	} gl.glEnd();

	gl.glDepthMask( true );

	gl.glPopMatrix();
	gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glPopMatrix();
	gl.glMatrixMode(GL2.GL_MODELVIEW);
      }

    public void drawSkyAndEarthBox( GL2 gl) 
      {
	// distances.
	final float ground =  0.0f; // neg. y
	final float sky    = +2.0f;  // pos. y
	final float west   = -2.0f;  // neg. x
	final float east   = +2.0f;  // pos. x
	final float north  = -2.0f;  // neg. z
	final float south  = +2.0f;  // pos. z
	final float westToEast = east - west;
	final float northToSouth = south - north;

	gl.glPolygonMode( GL2.GL_FRONT, GL2.GL_FILL );
	gl.glPolygonMode( GL2.GL_BACK, GL2.GL_LINE );

	gl.glColor4f( 1f, 1f, 1f, 1.0f );

	// Texture.
	gl.glEnable( GL2.GL_TEXTURE_2D );

	// Sky Texture.
	// (clockwise to point normal down).
	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[3] );
	gl.glBegin( GL2.GL_QUADS ); {
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex3f( east, sky,  north );
	  gl.glTexCoord2f( 0f, 1f );
	  gl.glVertex3f( east, sky,  south );	    
	  gl.glTexCoord2f( 1f, 1f );
	  gl.glVertex3f( west, sky,  south );
	  gl.glTexCoord2f( 1f, 0f );
	  gl.glVertex3f( west, sky,  north );
	} gl.glEnd();

	// Ground Texture.
	// (anticlockwise to point normal up).
	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[2] );
	gl.glBegin( GL2.GL_QUADS ); {
	  //gl.glColor4f(0.8f, 0.3f, 0.3f, 1f);
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex3f( west, ground, north );
	  gl.glTexCoord2f( 0f, northToSouth );
	  gl.glVertex3f( west, ground, south );
	  gl.glTexCoord2f( westToEast, northToSouth );
	  gl.glVertex3f( east, ground, south );	
	  gl.glTexCoord2f( westToEast, 0f );
	  gl.glVertex3f( east, ground, north );	    
	} gl.glEnd();

	// East Texture.
	// (anticlockwise to point normal west).
	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[4] );
	gl.glBegin( GL2.GL_QUADS ); {
	  //gl.glColor4f(0.3f, 0.6f, 0.3f, 1f);
	  gl.glTexCoord2f( 0f, 1f );
	  gl.glVertex3f( east, sky, north );
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex3f( east, ground, north );
	  gl.glTexCoord2f( 1f, 0f );
	  gl.glVertex3f( east, ground, south );	
	  gl.glTexCoord2f( 1f, 1f );
	  gl.glVertex3f( east, sky, south);	    
	} gl.glEnd();

	// West Texture.
  	// (clockwise to point normal east).
	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[4] );
	gl.glBegin( GL2.GL_QUADS ); {
	  gl.glTexCoord2f( 0f, 1f );
	  gl.glVertex3f( west, sky, south);	    
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex3f( west, ground, south );	
	  gl.glTexCoord2f( 1f, 0f );
	  gl.glVertex3f( west, ground, north );
	  gl.glTexCoord2f( 1f, 1f );
	  gl.glVertex3f( west, sky, north );
	} gl.glEnd();

	// North Bg Texture.
	// (anticlockwise to point normal south).
	gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[4] );
	gl.glBegin( GL2.GL_QUADS ); {
	  gl.glTexCoord2f( 0f, 1f );
	  gl.glVertex3f( west, sky, north);	    
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex3f( west, ground, north );	
	  gl.glTexCoord2f( 1f, 0f );
	  gl.glVertex3f( east, ground, north );
	  gl.glTexCoord2f( 1f, 1f );
	  gl.glVertex3f( east, sky, north );
	} gl.glEnd();

	gl.glDisable( GL2.GL_TEXTURE_2D );
      }


    public void drawTextureTrinangle
      ( GL2 gl, int textureNb, float posX,
	float R, float G, float B )
      {
	//gl.glColor4f( R, G, B, 1.0f );	
	gl.glColor4f( 1f, 1f, 1f, 1.0f );	

	// Textures
	gl.glEnable( GL2.GL_TEXTURE_2D );

	gl.glBindTexture( GL2.GL_TEXTURE_2D, 
			  textureId[textureNb] );

	gl.glBegin( GL2.GL_POLYGON ); {
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex3f(  posX -1.f, -1.0f, 0.0f );
	  gl.glTexCoord2f( 0.2f, 0f );
	  gl.glVertex3f(  posX +1.0f, -1.0f, 0.0f );
	  gl.glTexCoord2f( 0.1f, 0.35f );
	  gl.glVertex3f(  posX +0.0f,  1.f, 0.0f );	    
	} gl.glEnd();

	gl.glDisable( GL2.GL_TEXTURE_2D );
      }



  public void drawSphereMap( GL2 gl, GLU glu, GLUT glut )
    {

       gl.glEnable( GL2.GL_TEXTURE_2D );
       gl.glDisable(GL2.GL_BLEND);
      
       gl.glEnable( GL2.GL_TEXTURE_GEN_S);
       gl.glEnable( GL2.GL_TEXTURE_GEN_T);

       gl.glTexGeni( GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_OBJECT_LINEAR );
       gl.glTexGeni( GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_OBJECT_LINEAR );
       gl.glTexGenfv( GL2.GL_S, GL2.GL_OBJECT_PLANE, xPlane, 0 );
       gl.glTexGenfv( GL2.GL_T, GL2.GL_OBJECT_PLANE, yPlane, 0 );
      
       gl.glTexGeni( GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_SPHERE_MAP );
       gl.glTexGeni( GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_SPHERE_MAP );

      gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[2] );

      gl.glPolygonMode( GL2.GL_FRONT, GL2.GL_FILL );

      gl.glPushMatrix(); {
	
	gl.glTranslatef( 0.4f, 0.9f, -0.3f );

//       GLUquadric qobj0 = glu.gluNewQuadric();
//       glu.gluQuadricDrawStyle( qobj0, GLU.GLU_FILL );
//       glu.gluQuadricNormals( qobj0, GLU.GLU_SMOOTH );
//       glu.gluSphere( qobj0, 0.4f, 30, 30) ;

	glut.glutSolidSphere( 0.1f, 40, 40);

      } gl.glPopAttrib();
    }


//   public void drawCubeMap( GL2 gl, GLU glu, GLUT glut )
//     {

//       gl.glDisable(GL2.GL_BLEND);
//       gl.glDisable( GL2.GL_TEXTURE_2D );
//       gl.glEnable( GL2.GL_TEXTURE_CUBE_MAP );
//       gl.glBindTexture( GL2.GL_TEXTURE_CUBE_MAP, textureId[0] );
      
// //       gl.glEnable( GL2.GL_TEXTURE_GEN_S);
// //       gl.glEnable( GL2.GL_TEXTURE_GEN_T);
// //       gl.glEnable( GL2.GL_TEXTURE_GEN_R);
      
//       gl.glTexGeni( GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_REFLECTION_MAP );
//       gl.glTexGeni( GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_REFLECTION_MAP );
//       gl.glTexGeni( GL2.GL_R, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_REFLECTION_MAP );


//       gl.glPolygonMode( GL2.GL_FRONT, GL2.GL_FILL );

//       gl.glPushMatrix(); {
	
// 	gl.glTranslatef( 0f, 0.4f, 0f );

// 	glut.glutSolidSphere( 0.2f, 40, 40);

//       } gl.glPopAttrib();
//     }


  private int teaturn;
  public void drawTeapot(GL2 gl, GLU glu, GLUT glut, Light light)
    {
//       gl.glEnable( GL2.GL_LIGHTING );
//       gl.glDisable( GL2.GL_LIGHT0 );
//       gl.glEnable( GL2.GL_LIGHT1 );
//       gl.glDisable( GL2.GL_LIGHT2 );
//       gl.glEnable( GL2.GL_LIGHT3 );
      light.setSomeGrayMaterial( gl, GL2.GL_FRONT_AND_BACK );
      //light.setSomeDarkGrayMaterial( gl, GL2.GL_FRONT_AND_BACK );

       gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[2] );
       
       gl.glPushMatrix();
       //gl.glTranslatef( 0f, 0.0f, -5.0f );
       gl.glRotatef( teaturn++, .00f, 1f, -0.00f );
       gl.glRotatef( -90f, 1f, 0f, 0f );

       //gl.glTranslatef( 0f, 0f, -2.5f );
       //gl.glRotatef( teaturn++, .2f, 0.6f, -0.1f );
       //gl.glRotatef( -90f, 1f, 0f, 0f );
       glut.glutSolidTeapot( 0.6f, false );       
       gl.glPopMatrix();
    }

  public void drawWall( GL2 gl, float width, float height )
    {
	gl.glBegin( GL2.GL_QUADS ); {
	  gl.glTexCoord2f( 0f, 0f );
	  gl.glVertex2f( 0, 0 );
	  gl.glTexCoord2f( 0, height );
	  gl.glVertex2f( 0, height );
	  gl.glTexCoord2f( width, height );
	  gl.glVertex2f( width, height );
	  gl.glTexCoord2f( width, 0 );
	  gl.glVertex2f( width, 0 );
	} gl.glEnd();
    }

  public void drawTunnel( GL2 gl, Light light)
    {
      gl.glEnable( GL2.GL_LIGHTING );
      gl.glEnable( GL2.GL_LIGHT0 );
      gl.glDisable( GL2.GL_LIGHT1 );
      gl.glEnable( GL2.GL_LIGHT2 );
      gl.glEnable( GL2.GL_LIGHT3 );
      
       light.setSomeWhiteMaterial( gl, GL2.GL_FRONT_AND_BACK );
       //light.setSomeGrayMaterial( gl, GL2.GL_FRONT_AND_BACK );
       //light.setSomeDarkGrayMaterial( gl, GL2.GL_FRONT_AND_BACK );
       
       // west
       gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[0] );       
       gl.glPushMatrix(); {
	 gl.glTranslatef( -0.5f, 0f, 0f );
	 gl.glRotatef( 90f, 0f, 1f, 0f );
	 drawWall( gl, 20f, 1f );
       } gl.glPopMatrix();

       // east
       gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[1] );
       gl.glPushMatrix(); {
	 gl.glTranslatef( 0.5f, 0f, 0f );
	 gl.glRotatef( 90f, 0f, 1f, 0f );
	 drawWall( gl, 20f, 1f );
       } gl.glPopMatrix();

       // Floor.
       gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[2] );
       gl.glPushMatrix(); {
	 gl.glTranslatef( 0.5f, 0f, 0f );
	 gl.glRotatef( 90f, 0f, 0f, 1f );
	 gl.glRotatef( 90f, 0f, 1f, 0f );
	 drawWall( gl, 20f, 1f );
       } gl.glPopMatrix();

       // Sky.
       gl.glBindTexture( GL2.GL_TEXTURE_2D, textureId[3] );
       gl.glPushMatrix(); {
	 gl.glTranslatef( 0.5f, 1f, 0f );
	 gl.glRotatef( 90f, 0f, 0f, 1f );
	 gl.glRotatef( 90f, 0f, 1f, 0f );
	 drawWall( gl, 20f, 1f );
       } gl.glPopMatrix();
    }


  /////////////////////////////////////////////////////////

  public void draw( GL2 gl, GLU glu, GLUT glut, Light light)
      {
	gl.glPushAttrib(GL2.GL_TEXTURE_BIT);
	gl.glPushMatrix(); {

	  //drawBackground( gl );      
	  //drawSkyAndEarthBox( gl );

	  //draw3DHouseWithTexture( gl );

	  //drawSphereMap( gl, glu, glut );

	  //drawTeapot( gl, glu, glut, light );
	  //drawTunnel( gl, light );

	  //drawCubeMap( gl, glu, glut );

	  //gl.glTranslatef( 0.0f, 0.0f, 0.0f );
	  //drawTextureTrinangle( gl, 0, 0f, 0f, 0f, 0f );

	} gl.glPopMatrix(); 
	gl.glPopAttrib();
      }

  }
