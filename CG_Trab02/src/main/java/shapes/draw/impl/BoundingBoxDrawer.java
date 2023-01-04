package shapes.draw.impl;

import javax.media.opengl.GL;

import renderer.Renderer;
import shapes.BoundingBox;
import shapes.Point2D;
import shapes.draw.ShapeDrawer;
import color.Color;

/**
 * Classe responsável por desenhar uma BoundingBox.
 */
public class BoundingBoxDrawer implements ShapeDrawer<BoundingBox> {

    @Override
    public void draw(Renderer renderer, BoundingBox bbox) {
        GL gl = renderer.getGL();

        Color color = bbox.color();
        gl.glColor4d(color.red(), color.green(), color.blue(), color.alpha());

        drawBB(gl, bbox);
    }

    private void drawBB(GL gl, BoundingBox bbox) {
        final float pointSize = 5;

        Point2D topLeft = bbox.getTopLeft();
        Point2D bottomRight = bbox.getBottomRight();

        double topY = topLeft.getY();
        double leftX = topLeft.getX();
        double bottomY = bottomRight.getY();
        double rightX = bottomRight.getX();
        double midY = avg(topY, bottomY);
        double midX = avg(leftX, rightX);

        gl.glPointSize(pointSize);

        // cria os pontos da BBox
        gl.glBegin(GL.GL_POINTS);
        // top line
        gl.glVertex2d(leftX, topY);
        gl.glVertex2d(midX, topY);
        gl.glVertex2d(rightX, topY);

        // medium line
        gl.glVertex2d(leftX, midY);
        gl.glVertex2d(rightX, midY);

        // bottom line
        gl.glVertex2d(leftX, bottomY);
        gl.glVertex2d(midX, bottomY);
        gl.glVertex2d(rightX, bottomY);
        gl.glEnd();

        // cria as linhas da BBox
        double lineSize = 0.5;
        double y, x;
        gl.glBegin(GL.GL_LINES);
        // linha horizontal superior
        y = topY;
        x = leftX;
        while (x < rightX) {
            gl.glVertex2d(x, y);
            gl.glVertex2d(Math.min(x + lineSize, rightX), y);
            x += lineSize * 2;
        }
        // linha horizontal inferior
        y = bottomY;
        x = leftX;
        while (x < rightX) {
            gl.glVertex2d(x, y);
            gl.glVertex2d(Math.min(x + lineSize, rightX), y);
            x += lineSize * 2;
        }
        // linha vertical esquerda
        y = topY;
        x = leftX;
        while (y < bottomY) {
            gl.glVertex2d(x, y);
            gl.glVertex2d(x, Math.min(y + lineSize, bottomY));
            y += lineSize * 2;
        }
        // linha vertical direita
        y = topY;
        x = rightX;
        while (y < bottomY) {
            gl.glVertex2d(x, y);
            gl.glVertex2d(x, Math.min(y + lineSize, bottomY));
            y += lineSize * 2;
        }
        gl.glEnd();

    }

    private double avg(double a, double b) {
        return (a + b) / 2;
    }

}
