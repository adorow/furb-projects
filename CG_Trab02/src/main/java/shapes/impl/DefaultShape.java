package shapes.impl;

import shapes.Shape;
import color.Color;
import color.impl.ColorImpl;

public abstract class DefaultShape implements Shape {

    private Color color = ColorImpl.gray;
    
    @Override
    public Color color() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    
}
