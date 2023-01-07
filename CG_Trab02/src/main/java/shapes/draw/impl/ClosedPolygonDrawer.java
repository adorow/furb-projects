package shapes.draw.impl;

import color.Color;
import com.jogamp.opengl.GL2;
import renderer.Renderer;
import shapes.ClosedPolygon;
import shapes.Point2D;
import shapes.draw.ShapeDrawer;

/**
 * Classe respons�vel por desenhar um pol�gono fechado.
 */
public class ClosedPolygonDrawer implements ShapeDrawer<ClosedPolygon> {

    @Override
    public void draw(Renderer renderer, ClosedPolygon cPolygon) {
        GL2 gl = renderer.getGL();

        Color color = cPolygon.color();
        gl.glColor4d(color.red(), color.green(), color.blue(), color.alpha());

        drawClosedPolygon(gl, cPolygon.getPoints());
    }

    private void drawClosedPolygon(GL2 gl, Point2D[] points) {
        //Desenho poligono.
        gl.glBegin(GL2.GL_POLYGON);
        for (Point2D point : points) {
            gl.glVertex2d(point.getX(), point.getY());
        }
        gl.glEnd();

        //Desenha contorno.
        gl.glColor4d(0, 0, 0, 1);
        gl.glBegin(GL2.GL_LINE_LOOP);
        for (Point2D point : points) {
            gl.glVertex2d(point.getX(), point.getY());
        }
        gl.glEnd();
    }
}
