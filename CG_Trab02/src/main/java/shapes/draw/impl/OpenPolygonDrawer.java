package shapes.draw.impl;

import javax.media.opengl.GL;

import renderer.Renderer;
import shapes.OpenPolygon;
import shapes.Point2D;
import shapes.draw.ShapeDrawer;
import color.Color;

/**
 * Classe responsável por desenhar um polígono fechado.
 */
public class OpenPolygonDrawer implements ShapeDrawer<OpenPolygon> {

    @Override
    public void draw(Renderer renderer, OpenPolygon oPolygon) {
        GL gl = renderer.getGL();

        Color color = oPolygon.color();
        gl.glColor4d(color.red(), color.green(), color.blue(), color.alpha());

        drawOpenPolygon(gl, oPolygon.getPoints());
    }

    private void drawOpenPolygon(GL gl, Point2D[] points) {
        //Desenho poligono.
        gl.glBegin(GL.GL_POLYGON);
        for (Point2D point : points) {
            gl.glVertex2d(point.getX(), point.getY());
        }
        gl.glEnd();

        //Desenha contorno.
        gl.glColor4d(0, 0, 0, 1);
        gl.glBegin(GL.GL_LINE_STRIP);
        for (Point2D point : points) {
            gl.glVertex2d(point.getX(), point.getY());
        }
        gl.glEnd();

    }

}
