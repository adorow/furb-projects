import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.MemoryImageSource;
import java.awt.Point;
import java.awt.Robot;
import javax.media.opengl.GLCanvas;

// Take care of the (mouse) cursor.
// It may be requiered not to leave the window.
// The cursor may be hidden.
class InputDevice 
{

  //////////////// Variables /////////////////////////

  // Previour mouse position.
  int prevMouseX;
  int prevMouseY;
  boolean mousePosKnown = false; 

  ///////////////// Functions /////////////////////////

  public InputDevice() {}
  
  // Keep the cusor in the middle of the window.
  public void centerMouse( GLCanvas canvas )
    {
      //Applet applet = canvas.getParent();
      Point locOnScreen = canvas.getLocationOnScreen();
      // int middleX = locOnScreen.x + (applet.getWidth() / 2);
      // int middleY = locOnScreen.y + (applet.getHeight() / 2);
      int middleX = locOnScreen.x + (canvas.getWidth() / 2);
      int middleY = locOnScreen.y + (canvas.getHeight() / 2);
      try{
	Robot rob = new Robot();
	// Re-setting mouse coordinates.
	rob.mouseMove( middleX, middleY );
	prevMouseX = middleX;
	prevMouseY = middleY;
      } catch(Exception e){ System.out.println(e); }
    }

  public boolean isMouseAtBorder( GLCanvas canvas,
				  MouseEvent mouse )
    {
      int curMouseX = mouse.getX();
      int curMouseY = mouse.getY();

      if( curMouseX <= 0 || curMouseY <= 0 ) {
	return true; }
      if( curMouseX >=  canvas.getWidth()-1 || 
	  curMouseY >=  canvas.getHeight()-1 ) {
	return true; }

      return false;
    }
  
  public void processMouseMove( View view, 
				MouseEvent mouse )
    {
      int curMouseX = mouse.getX();
      int curMouseY = mouse.getY();

//       System.out.println ( "curMouseX:"+curMouseX); //ddd
//       System.out.println ( "curMouseY:"+curMouseY); //ddd
//       System.out.println ( "prevMouseX:"+prevMouseX); //ddd
//       System.out.println ( "prevMouseY:"+prevMouseY); //ddd
//       System.out.println ( "mousePosKnown:"+mousePosKnown); //ddd

      // Limit max mouse move.
      if( mousePosKnown )
      {
	final int maxStep = 20;

	int rightStep  = curMouseX - prevMouseX;
	if( rightStep > maxStep ) rightStep = maxStep;
	else if( rightStep < -maxStep ) rightStep = -maxStep;

	int upStep  = prevMouseY - curMouseY;
	if( upStep > maxStep ) upStep = maxStep;
	else if( upStep < -maxStep ) upStep = -maxStep;

	view.lookRight( rightStep );
	view.lookUp( upStep );

      } else { 
	mousePosKnown = true; // Called only once.
      }
      prevMouseX = curMouseX;
      prevMouseY = curMouseY;
    }


  public void setStandardCursor( Applet applet )
    {
      // Use some standard cursor.
      applet.setCursor( new Cursor (Cursor.CROSSHAIR_CURSOR) );
      //applet.setCursor( new Cursor (Cursor.WAIT_CURSOR) );      
    }


  public void setCustomCursor( Applet applet )
    {
      int w = 1;
      int h = 1;
      int pix[] = new int[w * h];

      // Define cursor.
      int red=0, green=0, blue=0;
      int index = 0;
      for (int y = 0; y < h; y++) {
	red = (y * 255) / (h - 1);
	for (int x = 0; x < w; x++) {
	  green = 255;
	  pix[index++] = (green << 24) | (red << 16) | blue;
	}
      }
      
      Image image = Toolkit.getDefaultToolkit().createImage(
	new MemoryImageSource(w, h, pix, 0, w));
      Cursor cursor =
	Toolkit.getDefaultToolkit().createCustomCursor
	(image, new Point(0, 0), "custom");
      
      applet.setCursor( cursor );
    }


  public void hideCursor( Applet applet )
    {
      int w = 1;
      int h = 1;
      int pix[] = new int[w * h];

      Image image = Toolkit.getDefaultToolkit().createImage(
	new MemoryImageSource(w, h, pix, 0, w));
      Cursor cursor =
	Toolkit.getDefaultToolkit().createCustomCursor
	(image, new Point(0, 0), "custom");
      
      applet.setCursor( cursor );
    }
}
