package shapes.draw.impl;

import color.Color;
import com.jogamp.opengl.GL2;
import renderer.Renderer;
import shapes.OpenPolygon;
import shapes.Point2D;
import shapes.draw.ShapeDrawer;

/**
 * Classe respons�vel por desenhar um pol�gono fechado.
 */
public class OpenPolygonDrawer implements ShapeDrawer<OpenPolygon> {

    @Override
    public void draw(Renderer renderer, OpenPolygon oPolygon) {
        GL2 gl = renderer.getGL();

        Color color = oPolygon.color();
        gl.glColor4d(color.red(), color.green(), color.blue(), color.alpha());

        drawOpenPolygon(gl, oPolygon.getPoints());
    }

    private void drawOpenPolygon(GL2 gl, Point2D[] points) {
        //Desenho poligono.
        gl.glBegin(GL2.GL_POLYGON);
        for (Point2D point : points) {
            gl.glVertex2d(point.getX(), point.getY());
        }
        gl.glEnd();

        //Desenha contorno.
        gl.glColor4d(0, 0, 0, 1);
        gl.glBegin(GL2.GL_LINE_STRIP);
        for (Point2D point : points) {
            gl.glVertex2d(point.getX(), point.getY());
        }
        gl.glEnd();

    }

}
