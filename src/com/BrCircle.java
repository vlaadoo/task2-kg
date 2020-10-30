package com;

import java.awt.*;

/**
 * Круг алгоритмом Брезенхема
 * Задаются x и y как центр окружности
 */
public class BrCircle extends DrawObject {

    int cen_x;
    int cen_y;
    int radius;

    Color color;

    public BrCircle(int x, int y, int r, int d, Color c) {

        cen_x = x;
        cen_y = y;
        radius = r;
        this.d = d;
        color = c;
    }

    @Override
    public void draw(Graphics2D g) {

        int x = -radius;
        int y = 0;
        int err = 2 - 2 * radius; /* 2 четверть */

        do {
            putPixel(g, cen_x - x, cen_y + y, color); /* 1 четверть */
            putPixel(g, cen_x - y, cen_y - x, color); /* 2 четверть */
            putPixel(g, cen_x + x, cen_y - y, color); /* 3 четверть */
            putPixel(g, cen_x + y, cen_y + x, color); /* 4 четверть */

            int t = err;
            if (t <= y) err += ++y * 2 + 1;
            if (t > x || err > y) err += ++x * 2 + 1;
        } while (x < 0);
    }
}
