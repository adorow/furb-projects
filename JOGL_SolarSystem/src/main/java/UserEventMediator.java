import java.awt.event.*;
//import java.awt.Frame;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLAutoDrawable;

// The UserEventMediator take user input and calls 
// the corresponding functions from local module, 
// e.g. , View, Light, etc.
//
// We encourage the following basic mapping
// for keys and mouse movement:
//
// Mousemove + leftclick -> look: left/right up/down
//
// Keys -> move:
//                                 
//              i,e->up               
//                             
//  j,s->right   k,d->back    l,f->left        
//                             
//             ",",c->down         
//                             
//        SPACE->forward                            
//
public class UserEventMediator implements 
KeyListener, MouseListener, MouseMotionListener 
{
  //////////////// Variables /////////////////////////

  // Modules for command mediation.
  JOGLbase base;
  InputDevice inputDevice;
  View view; 
  Light light;
  // Canvas to redisplay.
  GLCanvas canvas;

  // state of the last mouse pressed/released event
  private int _button;


  ///////////////// Functions /////////////////////////

  public UserEventMediator
  ( JOGLbase base, 
    InputDevice inputDevice,
    View view, 
    Light light, 
    GLCanvas canvas ) 
    {
      this.base  = base;
      this.inputDevice  = inputDevice;
      this.view   = view;
      this.light  = light;
      this.canvas = canvas;
    }

  /////////////// Key Events /////////////

  public void keyPressed( KeyEvent e ) 
    {
      // Not ALT and not CNTL
      if ( ! e.isAltDown() && ! e.isControlDown() )
      {

	//////// Arrows to move ///////

	if (e.getKeyCode() == KeyEvent.VK_UP) {
	  view.moveFwd();
	}
	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	  view.moveBack();
	}
	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	  view.moveLeft();
	}
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	  view.moveRight();
	}
	
	/////////// move ///////////////

	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	  view.moveFwd();
	}	
 	if (e.getKeyChar() == 'k' || e.getKeyChar() == 'd') {
	  view.moveBack();
	}	
 	if (e.getKeyChar() == 'j' || e.getKeyChar() == 's') {
	  view.moveLeft();
	}	
 	if (e.getKeyChar() == 'l' || e.getKeyChar() == 'f') {
	  view.moveRight();
	}	
 	if (e.getKeyChar() == 'i' || e.getKeyChar() == 'e') {
	  view.moveUp();
	}	
 	if (e.getKeyChar() == ',' || e.getKeyChar() == 'c') {
	  view.moveDown();
	}	

	////////// look /////////////////

	if (e.getKeyChar() == 'u' || e.getKeyChar() == 'r') {
	  view.lookUp();
	}
	if (e.getKeyChar() == 'o' || e.getKeyChar() == 'w') {
	  view.lookDown();
	}
	if (e.getKeyChar() == '.' || e.getKeyChar() == 'v') {
	  view.lookRight();
	}
	if (e.getKeyChar() == 'm' || e.getKeyChar() == 'x') {
	  view.lookLeft();
	}

	if (e.getKeyChar() == 'z' || e.getKeyChar() == 't') {
	  view.roll();
	}
	if (e.getKeyChar() == 'p' || e.getKeyChar() == 'q') {
	  view.rollNeg();
	}	
	
      }

      // Only CNTL
      if ( ! e.isAltDown() && e.isControlDown() )
      {
	//////// Arrows to move ///////

	if (e.getKeyCode() == KeyEvent.VK_UP) {
	  view.moveUp();
	}
	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	  view.moveDown();
	}
      } 

      // Only ALT
      if ( e.isAltDown() && ! e.isControlDown() )
      {
	if (e.getKeyCode() == KeyEvent.VK_UP) {
	  light.moveLightZ( false );
	}
	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	  light.moveLightZ( true );
	}
	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	  light.moveLightX( false );
	}
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	  light.moveLightX( true );
	}
      }	     

      // ALT and CNTL
      if ( e.isAltDown() &&  e.isControlDown() )
      {
	if (e.getKeyCode() == KeyEvent.VK_UP) {
	  light.moveLightY( true );
	}
	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	  light.moveLightY( false );
	}
      }
    }


  public void keyTyped( KeyEvent e ) 
    {
//       System.out.println
//       ("Key pressed; code = "+e.getKeyCode()); //ddd
//       System.out.println
// 	("Key pressed; char = "+e.getKeyChar()); //ddd
      //String.valueOf(e.getKeyCode())); //ddd

      /////////// animatios start/stop /////////////////

      if (e.getKeyChar() == 'H' || e.getKeyChar() == 'h' ) 
      {
	base.stopAnimator();
      }

      if (e.getKeyChar() == 'G' || e.getKeyChar() == 'g' ) 
      {
	base.startAnimator();
      }

      if (e.getKeyChar() == 'Q' || e.getKeyChar() == 'q') 
      {
	base.exit();
      }
    }

  
  public void keyReleased(KeyEvent e) { }

  /////////// Mouse Events //////////////////////

  // This event does not occur in fullscreen mode.
  public void mouseExited(MouseEvent mouse) 
    { 
      // Keep the mouse in the window.
      //inputDevice.centerMouse( canvas );
    }
  
  public void mouseClicked(MouseEvent mouse) { }
  public void mouseEntered(MouseEvent mouse) { }

  public void mousePressed(MouseEvent mouse) 
    {
//        System.out.println ( //ddd
//  	"Mouse click detected at coordinates x="
//  	+ String.valueOf(mouse.getX())
//  	+ " and y="
// 	+ String.valueOf(mouse.getY()));
	
      if (mouse.getButton() == MouseEvent.BUTTON1) {
      }
	
      if (mouse.getButton() == MouseEvent.BUTTON3) {
      }
    }

  public void mouseReleased(MouseEvent mouse) {}
  public void mouseDragged(MouseEvent mouse) 
    {
      inputDevice.processMouseMove( view, mouse );

      // Center mouse in fullscreen mode if trapped
      // at a border.
      if( base.enableFullscreen) {
	if( inputDevice.isMouseAtBorder( canvas, mouse ) ) 
	{
	  inputDevice.centerMouse( canvas ); 
	}
      }
    }

  public void mouseMoved(MouseEvent mouse)
    {
    }

  // Save state of the last 
  // mouse pressed/released event
  private void setButton(int button) 
    {
      //System.out.println("Setting button to " + button);//ddd
      this._button = button;
    }

  // Return the state of the last 
  // mouse pressed/released event
  private int getButton() 
    {
      return this._button;
    }

  ////////////////////////////////////////////////

  // Redisplay must be called if no animator is used.
  // Hence when the display is not updated regularly,
  // but an update should be triggered on some event.
  private void redisplay()
    {
      canvas.display();
    }

}
