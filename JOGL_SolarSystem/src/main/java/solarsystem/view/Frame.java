package solarsystem.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLCapabilities;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jogamp.opengl.util.FPSAnimator;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private FPSAnimator animator;
	private Renderer renderer = new Renderer();
	
	public Frame() {		
		// Cria o frame.
		super("Tela Principal");   
		setSize(800,800);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		/* Cria um objeto GLCapabilities para especificar 
		 * o numero de bits por pixel para RGBA
		 */
		GLProfile glProfile = GLProfile.get(GLProfile.GL2);
		GLCapabilities glCaps = new GLCapabilities(glProfile);
		//glCaps.setDoubleBuffered(true);
		glCaps.setRedBits(8);
		glCaps.setBlueBits(8);
		glCaps.setGreenBits(8);
		glCaps.setAlphaBits(8); 

		
		/* Cria um canvas, adiciona ao frame e objeto "ouvinte" 
		 * para os eventos Gl, de mouse e teclado
		 */
		final GLCanvas canvas = new GLCanvas(glCaps);
		add(canvas,BorderLayout.CENTER);
		canvas.addGLEventListener(renderer);        
		canvas.addKeyListener(renderer);
		canvas.addMouseListener(renderer);
		canvas.addMouseMotionListener(renderer);
		canvas.requestFocus();
		
		
	    addWindowListener( new WindowAdapter() {
	        // Canvas gets focus whenever frame is activated.
	        public void windowActivated( WindowEvent e ) {
	          canvas.requestFocus(); 
	          if (!animator.isAnimating()) {
	              animator.start();
	          }
	        }
	        
	        public void windowDeactivated(WindowEvent e) {
	            if (animator.isAnimating()) {
	                animator.stop();
	            }
	        };
	      } );

	    setVisible( true );
	    requestFocus();
	    canvas.requestFocusInWindow();
	    pack();

	    // After JOGL and window setup, start the animator.
	    animator = new FPSAnimator( canvas, 30 );
//	    animator.setRunAsFastAsPossible( false );
	    animator.start();
	}		
	
	public static void main(String[] args) {
		new Frame();
	}

	
}
