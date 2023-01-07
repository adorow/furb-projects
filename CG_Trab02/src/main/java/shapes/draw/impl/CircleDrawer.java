package shapes.draw.impl;

import color.Color;
import com.jogamp.opengl.GL2;
import renderer.Renderer;
import shapes.Circle;
import shapes.Point2D;
import shapes.draw.ShapeDrawer;

/**
 * Classe respons�vel por desenhar um c�rculo.
 */
public class CircleDrawer implements ShapeDrawer<Circle> {

    @Override
    public void draw(Renderer renderer, Circle circle) {
        GL2 gl = renderer.getGL();

        Color color = circle.color();
        gl.glColor4d(color.red(), color.green(), color.blue(), color.alpha());

        Point2D center = circle.getCenterPoint();
        double radius = circle.getRadius();

        drawCircle(gl, center.getX(), center.getY(), radius);
    }

    private void drawCircle(GL2 gl, double xa, double ya, double radius) {
        final double PI = Math.PI;
        double TWO_PI = PI * 2;
        double segmnt = TWO_PI / 36;

        //Desenho poligono.
        gl.glBegin(GL2.GL_POLYGON);

        for (double theta = 0; theta < TWO_PI; theta += segmnt) {
            double y = (ya + (Math.cos(theta) * radius));
            double x = (xa + (Math.sin(theta) * radius));
            gl.glVertex2d(x, y);
        }
        gl.glEnd();

        //Desenha contorno.
        gl.glColor4d(0, 0, 0, 1);
        gl.glBegin(GL2.GL_LINE_LOOP);

        for (double theta = 0; theta < TWO_PI; theta += segmnt) {
            double y = (ya + (Math.cos(theta) * radius));
            double x = (xa + (Math.sin(theta) * radius));
            gl.glVertex2d(x, y);
        }

        gl.glEnd();
    }

}
