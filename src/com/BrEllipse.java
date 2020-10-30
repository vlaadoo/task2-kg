package com;

import java.awt.*;

/**
 * Эллипс алгоритмом Брезенхема
 */
public class BrEllipse extends DrawObject {

    int x1;
    int y1;
    int x2;
    int y2;
    Color color;

    public BrEllipse(int x1, int y1, int x2, int y2, int d, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        color = c;

        if (this.x1 > this.x2) {
            int t = this.x1;
            this.x1 = this.x2;
            this.x2 = t;
        }
        if (this.y2 < this.y1) {
            int t = this.y2;
            this.y2 = this.y1;
            this.y1 = t;
        }
    }

    @Override
    public void draw(Graphics2D g) {


        int a = Math.abs(x2 - x1);
        int b = Math.abs(y2 - y1);
        int b1 = b & 1;
        long dx = 4 * (1 - a) * b * b;
        long dy = 4 * (b1 + 1) * a * a;
        long err = dx + dy + b1 * a * a;
        long e2 = 0;


        int y0 = y1 + (b + 1) / 2;
        int y1 = y0 - b1;
        int x0 = x1;
        int x1 = x2;

        a *= 8 * a;
        b1 = 8 * b * b;

        do {
            putPixel(g, x1, y0, color); /* 1 четверть */
            putPixel(g, x0, y0, color); /* 2 четверть */
            putPixel(g, x0, y1, color); /* 3 четверть */
            putPixel(g, x1, y1, color); /* 4 четверть */
            e2 = 2 * err;
            if (e2 <= dy) {
                y0++;
                y1--;
                err += dy += a;
            }  /* шаг y */
            if (e2 >= dx || 2 * err > dy) {
                x0++;
                x1--;
                err += dx += b1;
            } /* шаг x */
        } while (x0 <= x1);

        while (y0 - y1 < b) {
            putPixel(g, x0 - 1, y0, color);
            putPixel(g, x1 + 1, y0++, color);
            putPixel(g, x0 - 1, y1, color);
            putPixel(g, x1 + 1, y1--, color);
        }
    }
}
