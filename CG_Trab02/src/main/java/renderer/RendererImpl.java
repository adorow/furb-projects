package renderer;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.glu.GLU;

import math.GeometryMath;
import math.NDC;
import shapes.BoundingBox;
import shapes.Circle;
import shapes.ClosedPolygon;
import shapes.OpenPolygon;
import shapes.Point2D;
import shapes.Polygon;
import shapes.Shape;
import shapes.Spline;
import shapes.draw.ShapeDrawer;
import shapes.draw.ShapeDrawerFactory;
import shapes.impl.CircleImpl;
import shapes.impl.ClosedPolygonImpl;
import shapes.impl.OpenPolygonImpl;
import shapes.impl.SplineImpl;
import shapes.transform.TransformationHelper;
import view.DrawAction;
import color.impl.ColorImpl;

/**
 * Implementa��o do render.
 */
public class RendererImpl extends RendererStub {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;

    private double size = 30.0f;
    private double x = 0, y = 0;
    private double pixelX = 0, pixelY = 0;
    private double proportion;

    private DrawAction action = DrawAction.SELECTION;
    private List<Shape> shapes = new ArrayList<Shape>();
    private List<Shape> selectedShapes = new ArrayList<Shape>();
    private List<Point2D> selectedPoints = new ArrayList<Point2D>();
    private Shape currentShape;
    private int counterAux = 0;

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        proportion = (double) glDrawable.getWidth() / (double) glDrawable.getHeight();
    }

    // "render" feito logo ap�s a inicializa��o do contexto OpenGL.
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    // m�todo definido na interface GLEventListener. "render" feito pelo cliente
    // OpenGL.
    @SuppressWarnings("unchecked")
    public void display(GLAutoDrawable arg0) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        // configurar window
        glu.gluOrtho2D(x - (size * (proportion)), x + (size * (proportion)), y - size, y + size);

        //fazer um esquema pra poder identificar que uma forma ta selecionada; botar um "boolean isSelected" ja ta valendo...;

        // Desenha as formas nao selecionadas
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            ShapeDrawer drawer = ShapeDrawerFactory.getDrawer(shape);
            drawer.draw(this, shape);
        }

        // Desenha formas selecionadas
        for (int i = 0; i < selectedShapes.size(); i++) {
            Shape shape = selectedShapes.get(i);
            ShapeDrawer drawer = ShapeDrawerFactory.getDrawer(shape);
            drawer.draw(this, shape);

            BoundingBox bbox = shape.getBoundingBox();
            drawer = ShapeDrawerFactory.getDrawer(bbox);
            drawer.draw(this, bbox);
        }

        for (int i = 0; i < selectedPoints.size(); i++) {
            Point2D point = selectedPoints.get(i);
            gl.glColor4d(0.0, 0.0, 0.0, 1.0);
            gl.glPointSize(5);

            // cria os pontos da BBox
            gl.glBegin(GL.GL_POINTS);

            // top line
            gl.glVertex2d(point.getX(), point.getY());

            gl.glEnd();
        }

        // flush
        gl.glFlush();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyChar()) {
            case '+':
                zoomIn();
                break;
            case '-':
                zoomOut();
                break;
        }

        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_DELETE:
                removeSelectedPoints();
                removeSelectedShapes();
                break;
            default:
                break;
        }

        glDrawable.display();
    }

    private void removeSelectedPoints() {
        if (selectedPoints.isEmpty()) return;
        removeSelectedPoints(shapes);
        // n�o haver�o shapes selecionados
        //removeSelectedPointsFromPolygons(selectedShapes);
    }

    private void removeSelectedPoints(List<Shape> shapeList) {
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            Shape shape = shapeList.get(i);
            if (shape instanceof Polygon) {
                Polygon polygon = (Polygon) shape;
                for (int j = selectedPoints.size() - 1; j >= 0; j--) {
                    Point2D point = selectedPoints.get(j);
                    if (polygon.removePoint(point)) {
                        selectedPoints.remove(point);
                        if (polygon.getPoints().length < 2) {
                            shapeList.remove(polygon);
                        }
                    }
                }
            }
            if (shape instanceof Spline) {
                Spline spline = (Spline) shape;
                for (int spi = 0; spi < spline.getPoints().length; spi++) {
                    // cont�m algum ponto da Spline?
                    if (selectedPoints.contains(spline.getPoint(spi))) {
                        // ent�o remove a Spline...
                        shapeList.remove(spline);
                        // ... e todos os pontos da Spline tamb�m
                        for (Point2D point : spline.getPoints()) {
                            selectedPoints.remove(point);
                        }
                    }
                }
            }
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                if (selectedPoints.contains(circle.getCenterPoint())) {
                    // ent�o remove o C�rculo...
                    shapeList.remove(circle);
                    // ... e o ponto dele tamb�m
                    selectedPoints.remove(circle.getCenterPoint());
                }
            }
        }
    }

    private void removeSelectedShapes() {
        selectedShapes.clear();
    }

    /**
     * Efetua "zoom-in" no Canvas do OpenGL.
     */
    public void zoomIn() {
        zoomIn(1);

        glDrawable.display();
    }

    /**
     * Efetua o "zoom-in".
     * 
     * @param amount a quantidade de 'pontos' do OpenGL em que ser� incrementado o zoom.
     */
    private void zoomIn(int amount) {
        if (size > 1) {
            // 1 � o m�nimo
            size = Math.max(1, size - amount);
        }
    }

    /**
     * Efetua "zoom-out" no Canvas do OpenGL.
     */
    public void zoomOut() {
        zoomOut(1);

        glDrawable.display();
    }

    /**
     * Efetua o "zoom-out".
     * 
     * @param amount a quantidade de 'pontos' do OpenGL em que sera decrementado o zoom.
     */
    private void zoomOut(int amount) {
        if (size < 300) {
            // 300 � o m�ximo
            size = Math.min(300, size + amount);
        }
    }

    /**
     * Obt�m todos os objetos selecionados neste momento, e move-os um pouco para a esquerda.
     */
    private void moveLeft() {
        for (Shape shape : selectedShapes) {
            TransformationHelper.translate(shape, -1.0, 0.0);
        }
    }

    /**
     * Obt�m todos os objetos selecionados neste momento, e move-os um pouco para a direita.
     */
    private void moveRight() {
        for (Shape shape : selectedShapes) {
            TransformationHelper.translate(shape, +1.0, 0.0);
        }
    }

    /**
     * Obt�m todos os objetos selecionados neste momento, e move-os um pouco para cima.
     */
    private void moveUp() {
        for (Shape shape : selectedShapes) {
            TransformationHelper.translate(shape, 0.0, +1.0);
        }
    }

    /**
     * Obt�m todos os objetos selecionados neste momento, e move-os um pouco para baixo.
     */
    private void moveDown() {
        for (Shape shape : selectedShapes) {
            TransformationHelper.translate(shape, 0.0, -1.0);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int wheelRotation = e.getWheelRotation();

        // faz zoom
        if (wheelRotation < 0) {
            zoomIn(-wheelRotation);
        }
        if (wheelRotation > 0) {
            zoomOut(wheelRotation);
        }

        glDrawable.display();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //       asdasd
        //        glDrawable.display();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pixelX = e.getX();
        pixelY = e.getY();

        switch (action) {
            case SELECTION:
                Point2D selectedPoint = new Point2D(getOglX(e.getX()), getOglY(e.getY()));
                boolean controlDown = e.isControlDown();

                // se ha shapes tenta seleciona-los antes, caso contrario tenta selecionar pontos antes..
                if (!selectedShapes.isEmpty()) {
                    // se selecionar um shape nao precisa selecionar uma forma
                    if (!selectShape(selectedPoint, controlDown)) {
                        // se nao ha shapes, ou o control nao esta pressionado, remove os shapes selecionados e tenta selecionar pontos
                        if (selectedShapes.isEmpty() || !controlDown) {
                            unselectAllShapes();

                            // tenta selecionar um ponto
                            selectPoint(selectedPoint, controlDown);
                        }
                    }
                } else {
                    // se selecionou ponto, nao precisa selecionar forma
                    if (!selectPoint(selectedPoint, controlDown)) {
                        // se nao houverem pontos selecionados, ou o control nao estiver pressionado, tenta selecionar shapes
                        if (selectedPoints.isEmpty() || !controlDown) {
                            selectedPoints.clear();

                            // tenta selecionar um shape
                            selectShape(selectedPoint, controlDown);
                        }
                    } else {
                        unselectAllShapes();
                    }
                }

                break;
            case CIRCLE:
                if (currentShape == null) {
                    Circle circle = new CircleImpl();
                    circle.getCenterPoint().setXY(getOglX(e.getX()), getOglY(e.getY()));
                    currentShape = circle;
                } else {
                    Circle circle = (Circle) currentShape;
                    circle.setRadius(GeometryMath.distance(circle.getCenterPoint(), new Point2D(getOglX(e.getX()), getOglY(e.getY()))));
                    if (!shapes.contains(circle)) {
                        shapes.add(circle);
                    }
                    currentShape = null;
                }
                unselectAllShapes();
                unselectAllPoints();
                break;
            case SPLINE:
                if (currentShape == null) {
                    Spline spline = new SplineImpl();
                    Point2D[] points = spline.getPoints();
                    // poe todos os pontos no mesmo lugar para que a Spline nao
                    // fique toda estranha com pontos em (0,0)
                    for (int i = 0; i < points.length; i++) {
                        points[i].setXY(getOglX(e.getX()), getOglY(e.getY()));
                    }
                    currentShape = spline;
                    if (!shapes.contains(spline)) {
                        shapes.add(spline);
                    }
                    counterAux = 1;
                } else {
                    Spline spline = (Spline) currentShape;
                    spline.getPoints()[counterAux++].setXY(getOglX(e.getX()), getOglY(e.getY()));
                    if (counterAux == 4) {
                        currentShape = null;
                    }
                }
                unselectAllShapes();
                unselectAllPoints();
                break;
            case OPEN_POLYGON:
                if (currentShape == null) {
                    OpenPolygon openPolygon = new OpenPolygonImpl();

                    Point2D point1 = openPolygon.addNewPoint();
                    point1.setXY(getOglX(e.getX()), getOglY(e.getY()));

                    Point2D point2 = openPolygon.addNewPoint();
                    point2.setXY(getOglX(e.getX()), getOglY(e.getY()));

                    if (!shapes.contains(openPolygon)) {
                        shapes.add(openPolygon);
                    }
                    currentShape = openPolygon;
                } else {
                    OpenPolygon openPolygon = (OpenPolygon) currentShape;
                    if (e.getClickCount() == 2) {
                        Point2D[] points = openPolygon.getPoints();
                        // remove o �ltimo ponto
                        openPolygon.removePoint(points[points.length - 1]);
                        currentShape = null;
                    } else {
                        Point2D point = openPolygon.addNewPoint();
                        point.setXY(getOglX(e.getX()), getOglY(e.getY()));
                    }
                }
                unselectAllShapes();
                unselectAllPoints();
                break;
            case CLOSED_POLYGON:
                if (currentShape == null) {
                    ClosedPolygon closedPolygon = new ClosedPolygonImpl();
                    Point2D point1 = closedPolygon.addNewPoint();
                    point1.setXY(getOglX(e.getX()), getOglY(e.getY()));

                    Point2D point2 = closedPolygon.addNewPoint();
                    point2.setXY(getOglX(e.getX()), getOglY(e.getY()));

                    if (!shapes.contains(closedPolygon)) {
                        shapes.add(closedPolygon);
                    }
                    currentShape = closedPolygon;
                } else {
                    ClosedPolygon closedPolygon = (ClosedPolygon) currentShape;
                    if (e.getClickCount() == 2) {
                        Point2D[] points = closedPolygon.getPoints();
                        // remove o �ltimo ponto
                        closedPolygon.removePoint(points[points.length - 1]);
                        currentShape = null;
                    } else {
                        Point2D point = closedPolygon.addNewPoint();
                        point.setXY(getOglX(e.getX()), getOglY(e.getY()));
                    }
                }
                unselectAllShapes();
                unselectAllPoints();
                break;
            default:
                unselectAllShapes();
                unselectAllPoints();
                break;
        }

        glDrawable.display();
    }

    private void unselectAllPoints() {
        selectedPoints.clear();
    }

    private void unselectAllShapes() {
        for (Shape selectedShape : selectedShapes) {
            if (!shapes.contains(selectedShape)) {
                shapes.add(selectedShape);
            }
        }
        selectedShapes.clear();
    }

    private boolean selectPoint(Point2D selectedPoint, boolean controlDown) {
        double maxDist = 1;

        for (int i = selectedPoints.size() - 1; i >= 0; i--) {
            Point2D point = selectedPoints.get(i);
            if (GeometryMath.distance(selectedPoint, point) < maxDist) {
                if (controlDown) {
                    selectedPoints.remove(point);
                    // deselect done
                } else {
                    // just select this
                    selectedPoints.clear();
                    selectedPoints.add(point);
                    // select done
                }
                return true;
            }
        }

        ArrayList<Shape> allShapes = new ArrayList<Shape>();
        allShapes.addAll(shapes);
        allShapes.addAll(selectedShapes);

        for (Shape shape : allShapes) {
            Point2D[] points = null;
            if (shape instanceof Polygon) {
                points = ((Polygon) shape).getPoints();
            }
            if (shape instanceof Spline) {
                points = ((Spline) shape).getPoints();
            }
            if (shape instanceof Circle) {
                points = new Point2D[] { ((Circle) shape).getCenterPoint() };
            }
            if (points != null) {
                for (int i = points.length - 1; i >= 0; i--) {
                    if (GeometryMath.distance(selectedPoint, points[i]) < maxDist) {
                        if (!controlDown) {
                            selectedPoints.clear();
                        }
                        selectedPoints.add(points[i]);
                        return true;// select done
                    }
                }
            }
        }

        return false;
    }

    private boolean selectShape(Point2D selectedPoint, boolean controlDown) {
        // retira a selecao de shapes selecionados
        for (int i = selectedShapes.size() - 1; i >= 0; i--) {
            Shape shape = selectedShapes.get(i);
            if (shape.contains(selectedPoint)) {
                if (controlDown) {
                    selectedShapes.remove(shape);
                    if (!shapes.contains(shape)) {
                        shapes.add(shape);
                    }
                } else {
                    if (!selectedShapes.contains(shape)) {
                        selectedShapes.add(shape);
                    }
                }
                return true;// deselect done
            }
        }

        // seleciona shapes nao selecionados
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(selectedPoint)) {
                if (!controlDown) {
                    unselectAllShapes();
                }
                shapes.remove(shape);
                if (!selectedShapes.contains(shape)) {
                    selectedShapes.add(shape);
                }
                return true;// select done
            }
        }

        return false;
    }

    public void mouseDragged(MouseEvent e) {
        int px, py;
        px = e.getX();
        py = e.getY();
        double oglX = getOglX(px);
        double oglY = getOglY(py);
        double oglPixelX = getOglX((int) pixelX);
        double oglPixelY = getOglY((int) pixelY);

        switch (action) {
            case SELECTION:
                double transX = 0;
                double transY = 0;

                transX = (oglX - oglPixelX);
                transY = (oglY - oglPixelY);

                for (Point2D point : selectedPoints) {
                    TransformationHelper.translateSinglePoint(point, transX, transY);
                }
                for (Shape shape : selectedShapes) {
                    TransformationHelper.translate(shape, transX, transY);
                }
                break;

            case PAN:
                if (pixelX < px) {
                    x -= getDiffX() * (px - pixelX);
                } else if (pixelX > px) {
                    x += getDiffX() * (pixelX - px);
                }
                if (pixelY < py) {
                    y -= getDiffY() * (py - pixelY);
                } else if (pixelY > py) {
                    y += getDiffY() * (pixelY - py);
                }
                break;
            default:
                break;
        }

        pixelX = px;
        pixelY = py;

        glDrawable.display();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (action) {
            case CIRCLE:
                if (currentShape != null) {
                    Circle circle = (Circle) currentShape;
                    circle.setRadius(GeometryMath.distance(circle.getCenterPoint(), new Point2D(getOglX(e.getX()), getOglY(e.getY()))));
                    if (!shapes.contains(circle)) {
                        shapes.add(circle);
                    }
                }
                break;
            case SPLINE:
                if (currentShape != null) {
                    Spline spline = (Spline) currentShape;
                    int length = spline.getPoints().length;

                    double newX = getOglX(e.getX());
                    double newY = getOglY(e.getY());

                    // altera todos os pontos restantes da Spline para o ponto
                    // atual, para que os ultimos pontos nao fiquem no ponto (0,0),
                    // o que deixa a Splina meio estranha para o usuario durante o
                    // desenho
                    for (int i = counterAux; i < length; i++) {
                        Point2D point = spline.getPoint(i);
                        point.setXY(newX, newY);
                    }
                }
                break;
            case OPEN_POLYGON: {
                if (currentShape != null) {
                    OpenPolygon openPolygon = (OpenPolygon) currentShape;
                    Point2D[] points = openPolygon.getPoints();
                    points[points.length - 1].setXY(getOglX(e.getX()), getOglY(e.getY()));
                }
                break;
            }
            case CLOSED_POLYGON: {
                if (currentShape != null) {
                    ClosedPolygon closedPolygon = (ClosedPolygon) currentShape;
                    Point2D[] points = closedPolygon.getPoints();
                    points[points.length - 1].setXY(getOglX(e.getX()), getOglY(e.getY()));
                }
                break;
            }
            default:
                break;
        }
        if (glDrawable != null) {
            glDrawable.display();
        }
    }

    private double getDiffX() {
        float w_frame = glDrawable.getWidth();

        double half = size * proportion;
        return NDC.getDiff(0, w_frame, x - (half), x + (half));
    }

    private double getDiffY() {
        float h_frame = glDrawable.getHeight();

        return NDC.getDiff(0, h_frame, y + size, y - size);
    }

    /**
     * Retorna a posi��o no Orto do valor passado
     */
    private double getOglX(int x_frame) {
        float w_frame = glDrawable.getWidth();

        double half = size * proportion;
        return NDC.translate(0, w_frame, x - (half), x + (half), x_frame);
    }

    /**
     * Retorna a posicao no Orto do valor passado
     */
    private double getOglY(int y_frame) {
        float h_frame = glDrawable.getHeight();
        return NDC.translate(0, h_frame, y + size, y - size, y_frame);
    }

    public GL getGL() {
        return gl;
    }

    public GLU getGLU() {
        return glu;
    }

    public GLDrawable getDrawable() {
        return glDrawable;
    }

    public void setAction(DrawAction action) {
        if (this.action != action) {
            this.action = action;
            currentShape = null;
        }
    }

    @Override
    public void setColor(Color color) {
        for (Shape shape : selectedShapes) {
            shape.setColor(new ColorImpl(color));
        }
        glDrawable.display();
    }

    @Override
    public void rotate(double degrees) {
        for (Shape shape : selectedShapes) {
            TransformationHelper.rotate(shape, degrees);
        }
        glDrawable.display();
    }

    @Override
    public void scale(double scale) {
        for (Shape shape : selectedShapes) {
            TransformationHelper.scale(shape, scale);
        }
        glDrawable.display();
    }

}
