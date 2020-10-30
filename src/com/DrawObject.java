package com;

import java.awt.*;

public abstract class DrawObject {

    int d;

    public abstract void draw(Graphics2D g);

    public void putPixel(Graphics2D g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x * d, y * d, d, d);
    }
}
