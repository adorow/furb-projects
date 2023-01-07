package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.media.opengl.GLCanvas;
//import javax.media.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLCapabilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import renderer.Renderer;
import renderer.RendererImpl;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;

    private Renderer renderer = new RendererImpl();

    // Tool Bar
    private JToolBar toolBar;
    private JColorChooser colorsPalette;
    private JToggleButton selection;
    private JToggleButton pan;
    private JToggleButton circle;
    private JToggleButton spline;
    private JToggleButton openPolygon;
    private JToggleButton closedPolygon;
    private JToggleButton toggledButton;
    private JButton rotateRight, rotateLeft;
    private JButton scaleUp, scaleDown;
    private JButton zoomIn, zoomOut;

    private ButtonActionListener buttonListener;

    public Frame() {
        // Cria o frame.
        super("Tela Principal");
        setBounds(50, 100, 675, 500);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        /*
         * Cria um objeto GLCapabilities para especificar o n�mero de bits por
         * pixel para RGBA
         */
        GLProfile glProfile = GLProfile.get(GLProfile.GL3);
        GLCapabilities glCaps = new GLCapabilities(glProfile); // fixme: original: new GLCapabilities()
        glCaps.setRedBits(8);
        glCaps.setBlueBits(8);
        glCaps.setGreenBits(8);
        glCaps.setAlphaBits(8);

        /*
         * Cria um canvas, adiciona ao frame e objeto "ouvinte" para os eventos
         * Gl, de mouse e teclado
         */
        GLCanvas canvas = new GLCanvas(glCaps);
        add(canvas, BorderLayout.CENTER);
        canvas.addGLEventListener(renderer);
        canvas.addKeyListener(renderer);
        canvas.addMouseListener(renderer);
        canvas.addMouseMotionListener(renderer);
        canvas.addMouseWheelListener(renderer);
        canvas.requestFocus();

        // Desenha a barra de ferramentas
        drawToolBar();
    }

    public void drawToolBar() {
        toolBar = new JToolBar("Tools");
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false);
        toolBar.add(new JLabel("      Tools"));
        toolBar.addSeparator(new Dimension(40, 30));
        add(toolBar, BorderLayout.NORTH);

        buttonListener = new ButtonActionListener();

        selection = new JToggleButton();
        selection.setToolTipText("Selection");
        selection.setIcon(new ImageIcon("icons/select.png"));
        selection.addActionListener(buttonListener);
        toolBar.add(selection);

        pan = new JToggleButton();
        pan.setToolTipText("Pan");
        pan.setIcon(new ImageIcon("icons/pan.png"));
        pan.addActionListener(buttonListener);
        toolBar.add(pan);

        toolBar.addSeparator(new Dimension(40, 30));

        circle = new JToggleButton();
        circle.setToolTipText("Circle");
        circle.setIcon(new ImageIcon("icons/circle.png"));
        circle.addActionListener(buttonListener);
        toolBar.add(circle);

        spline = new JToggleButton();
        spline.setToolTipText("Spline");
        spline.setIcon(new ImageIcon("icons/spline.png"));
        spline.addActionListener(buttonListener);
        toolBar.add(spline);

        openPolygon = new JToggleButton();
        openPolygon.setToolTipText("Open Polygon");
        openPolygon.setIcon(new ImageIcon("icons/open-polygon.png"));
        openPolygon.addActionListener(buttonListener);
        toolBar.add(openPolygon);

        closedPolygon = new JToggleButton();
        closedPolygon.setToolTipText("Closed Polygon");
        closedPolygon.setIcon(new ImageIcon("icons/closed-polygon.png"));
        closedPolygon.addActionListener(buttonListener);
        toolBar.add(closedPolygon);

        toolBar.addSeparator(new Dimension(40, 30));

        rotateLeft = new JButton();
        rotateLeft.setToolTipText("Rotate Left");
        rotateLeft.setIcon(new ImageIcon("icons/rotate-left.png"));
        rotateLeft.addActionListener(buttonListener);
        toolBar.add(rotateLeft);

        rotateRight = new JButton();
        rotateRight.setToolTipText("Rotate Right");
        rotateRight.setIcon(new ImageIcon("icons/rotate-right.png"));
        rotateRight.addActionListener(buttonListener);
        toolBar.add(rotateRight);

        toolBar.addSeparator(new Dimension(40, 30));

        scaleUp = new JButton(new ImageIcon("icons/expand.png"));
        scaleUp.setToolTipText("Scale up");
        // scaleUp.setIcon(new ImageIcon("icons/scale-up.png"));
        scaleUp.addActionListener(buttonListener);
        toolBar.add(scaleUp);

        scaleDown = new JButton(new ImageIcon("icons/contract.png"));
        scaleDown.setToolTipText("Scale down");
        // scaleDown.setIcon(new ImageIcon("icons/scale-down.png"));
        scaleDown.addActionListener(buttonListener);
        toolBar.add(scaleDown);

        toolBar.addSeparator(new Dimension(40, 30));

        zoomIn = new JButton(new ImageIcon("icons/zoom-in.png"));
        zoomIn.setToolTipText("zoom-in");
        zoomIn.addActionListener(buttonListener);
        toolBar.add(zoomIn);

        zoomOut = new JButton(new ImageIcon("icons/zoom-out.png"));
        zoomOut.setToolTipText("zoom-out");
        zoomOut.addActionListener(buttonListener);
        toolBar.add(zoomOut);

        colorsPalette = new JColorChooser();
        ColorSelectionModel model = colorsPalette.getSelectionModel();
        model.addChangeListener(buttonListener);
        add(colorsPalette.getChooserPanels()[2], BorderLayout.EAST);

        toggledButton = selection;
        selection.setSelected(true);
    }

    private class ButtonActionListener implements ActionListener, ChangeListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (toggledButton == e.getSource()) {
                toggledButton.setSelected(true);
                return;
            }

            if (e.getSource() instanceof JToggleButton) {
                toggledButton.setSelected(false);
            }

            if (e.getSource() == selection) {
                renderer.setAction(DrawAction.SELECTION);
                toggledButton = selection;
            } else if (e.getSource() == pan) {
                renderer.setAction(DrawAction.PAN);
                toggledButton = pan;
            } else if (e.getSource() == circle) {
                renderer.setAction(DrawAction.CIRCLE);
                toggledButton = circle;
            } else if (e.getSource() == spline) {
                renderer.setAction(DrawAction.SPLINE);
                toggledButton = spline;
            } else if (e.getSource() == openPolygon) {
                renderer.setAction(DrawAction.OPEN_POLYGON);
                toggledButton = openPolygon;
            } else if (e.getSource() == closedPolygon) {
                renderer.setAction(DrawAction.CLOSED_POLYGON);
                toggledButton = closedPolygon;
            } else if (e.getSource() == rotateLeft) {
                renderer.rotate(-10);
            } else if (e.getSource() == rotateRight) {
                renderer.rotate(+10);
            } else if (e.getSource() == scaleUp) {
                renderer.scale(1.2);
            } else if (e.getSource() == scaleDown) {
                renderer.scale(0.83);
            } else if (e.getSource() == zoomIn) {
                renderer.zoomIn();
            } else if (e.getSource() == zoomOut) {
                renderer.zoomOut();
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            renderer.setColor(colorsPalette.getColor());
        }

    }

    public static void main(String[] args) {
        Runnable runLookAndFeel = new Runnable() {

            public void run() {
//                try {
//                    // UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
//                    // UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
//                    // UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
//                    UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, "Look And Feel Nao suportado pelo seu Java.\nTente atualiza o seu Java e executar o programa novamente.");
//                    e.printStackTrace();
//                }
//                JFrame.setDefaultLookAndFeelDecorated(true);
                new Frame().setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runLookAndFeel);
    }

}
