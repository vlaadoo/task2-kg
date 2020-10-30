package com;

import java.awt.*;

public class DDA extends DrawObject {

    int x1;
    int y1;
    int x2;
    int y2;

    Color color;

    public DDA(int x1, int y1, int x2, int y2, int d, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        color = c;
    }

    @Override
    public void draw(Graphics2D g) {
        double dx, dy, steps, x, y, k;
        double xc, yc;
        dx = x2 - x1;
        dy = y2 - y1;
        if (Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);
        else
            steps = Math.abs(dy);
        xc = (dx / steps);
        yc = (dy / steps);
        x = x1;
        y = y1;
        for (k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            putPixel(g, (int) x, (int) y, color);

        }
    }
}

