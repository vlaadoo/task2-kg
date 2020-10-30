package com;

import java.awt.*;

/**
 * Линия по алгоритму Брезенхема
 */
public class BrLine extends DrawObject {

    int x1;
    int y1;
    int x2;
    int y2;

    Color color;

    public BrLine(int x1, int y1, int x2, int y2, int d, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        color = c;
    }

    @Override
    public void draw(Graphics2D g) {

        int dx = Math.abs(x2 - x1);
        int sx = (x1 < x2) ? 1 : -1;
        int dy = -Math.abs(y2 - y1);
        int sy = y1 < y2 ? 1 : -1;
        int err = dx + dy;
        int e2 = 0;


        int curX = x1;
        int curY = y1;

        for (; ; ) {
            putPixel(g, curX, curY, color);

            if (curX == x2 && curY == y2) break;
            e2 = 2 * err;
            if (e2 >= dy) {
                err += dy;
                curX += sx;
            }
            if (e2 <= dx) {
                err += dx;
                curY += sy;
            }
        }
    }
}
