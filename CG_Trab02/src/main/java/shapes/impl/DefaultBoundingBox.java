package shapes.impl;

import color.Color;
import color.impl.ColorImpl;
import shapes.BoundingBox;
import shapes.Point2D;

/**
 * Classe que contem implementacoes que sao comuns entre todo tipo de BoundingBox.
 */
public abstract class DefaultBoundingBox extends DefaultRectangle implements BoundingBox {

    @Override
    public Color color() {
        return ColorImpl.black;// a cor padr�o de uma BoundingBox � preto.
    }

    public Point2D getCenter() {
        double topY = getTopLeft().getY();
        double bottomY = getBottomRight().getY();
        double leftX = getTopLeft().getX();
        double rightX = getBottomRight().getX();

        return new Point2D((leftX + rightX) / 2, (topY + bottomY) / 2);
    }

    @Override
    public final BoundingBox getBoundingBox() {
        return this;// este objeto ja e uma BoundingBox
    }

}
