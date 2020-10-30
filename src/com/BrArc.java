package com;

import java.awt.*;

/**
 * Задается эллипс со стартовым и конечным углами, значения которых
 * задаются в диапазоне от 0 до 2*PI
 */
public class BrArc extends DrawObject {

    int x1;
    int y1;
    int x2;
    int y2;
    double angleStart;
    double angleFinish;
    Color color;

    public BrArc(int x1, int y1, int x2, int y2, double angleStart, double angleFinish, int d, Color c) {

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

        this.angleStart = angleStart;
        this.angleFinish = angleFinish;
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

        double cx = 0.5 * (this.x1 + x2);
        double cy = 0.5 * (y2 + this.y1);

        a *= 8 * a;
        b1 = 8 * b * b;

        do {

            double theta = -Math.atan2(y0 - cy, x1 - cx);
            if (theta < 0) {
                theta += Math.PI * 2;
            }
            if (theta >= angleStart && theta <= angleFinish) {
                putPixel(g, x1, y0, color); /* 1 четверть */
            }
            theta = -Math.atan2(y0 - cy, x0 - cx);
            if (theta < 0) {
                theta += Math.PI * 2;
            }
            if (theta >= angleStart && theta <= angleFinish) {
                putPixel(g, x0, y0, color); /*  2 четверть */
            }
            theta = -Math.atan2(y1 - cy, x0 - cx);
            if (theta < 0) {
                theta += Math.PI * 2;
            }
            if (theta >= angleStart && theta <= angleFinish) {
                putPixel(g, x0, y1, color); /* 3 четверть */
            }
            theta = -Math.atan2(y1 - cy, x1 - cx);
            if (theta < 0) {
                theta += Math.PI * 2;
            }
            if (theta >= angleStart && theta <= angleFinish) {
                putPixel(g, x1, y1, color); /* 4 четверть */
            }
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

        while (y0 - y1 < b) {  /* если эллипс плоский a=1 */
            putPixel(g, x0 - 1, y0, color);
            putPixel(g, x1 + 1, y0++, color);
            putPixel(g, x0 - 1, y1, color);
            putPixel(g, x1 + 1, y1--, color);
        }
    }
}
