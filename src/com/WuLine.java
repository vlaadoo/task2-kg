package com;

import java.awt.*;

/**
 * Линия алгоритмом Ву со сглаживанием
 */
public class WuLine extends DrawObject {

    double x1;
    double y1;
    double x2;
    double y2;

    Color color;

    public WuLine(double x1, double y1, double x2, double y2, int d, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        color = c;
    }

    int clip(double x) {

        int k = (int) Math.round(x);
        if (k < 0) return 0;
        if (k > 255) return 255;
        return k;
    }

    int ipart(double x) {
        return (int) Math.floor(x);
    }

    int iround(double x) {
        return ipart(x + 0.5);
    }

    double fpart(double x) {
        return x - Math.floor(x);
    }

    double rfpart(double x) {
        return 1.0 - fpart(x);
    }

    @Override
    public void draw(Graphics2D g) {

        boolean st = Math.abs(y2 - y1) > Math.abs(x2 - x1);

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();


        if (st) {
            double t = x1;
            x1 = y1;
            y1 = t;
            t = x2;
            x2 = y2;
            y2 = t;
        }

        if (x1 > x2) {
            double t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        double dx = x2 - x1;
        double dy = y2 - y1;
        double gradient = 0;

        if (dx == 0) {
            gradient = 1.0;
        } else {
            gradient = 1.0 * dy / dx;
        }

        int xend = iround(x1);
        double yend = y1 + gradient * (xend - x1);
        double xgap = rfpart(x1 + 0.5);
        int xpxl1 = xend;
        int ypxl1 = ipart(yend);
        if (st) {

            Color c1 = new Color(red, green, blue, clip(255 * rfpart(yend) * xgap));
            putPixel(g, ypxl1, xpxl1, c1);
            Color c2 = new Color(red, green, blue, clip(255 * fpart(yend) * xgap));
            putPixel(g, ypxl1 + 1, xpxl1, c2);
        } else {
            Color c1 = new Color(red, green, blue, clip(255 * rfpart(yend) * xgap));
            putPixel(g, xpxl1, ypxl1, c1);
            Color c2 = new Color(red, green, blue, clip(255 * fpart(yend) * xgap));
            putPixel(g, xpxl1, ypxl1 + 1, c2);
        }

        double intery = yend + gradient;

        xend = iround(x2);
        yend = y2 + gradient * (xend - x2);
        xgap = fpart(x2 + 0.5);
        int xpxl2 = xend;
        int ypxl2 = ipart(yend);
        if (st) {
            Color c1 = new Color(red, green, blue, clip(255 * rfpart(yend) * xgap));
            putPixel(g, ypxl2, xpxl2, c1);
            Color c2 = new Color(red, green, blue, clip(255 * fpart(yend) * xgap));
            putPixel(g, ypxl2 + 1, xpxl2, c2);
        } else {
            Color c1 = new Color(red, green, blue, clip(255 * rfpart(yend) * xgap));
            putPixel(g, xpxl2, ypxl2, c1);
            Color c2 = new Color(red, green, blue, clip(255 * fpart(yend) * xgap));
            putPixel(g, xpxl2, ypxl2 + 1, c2);
        }

        if (st) {
            for (int x = xpxl1 + 1; x <= xpxl2 - 1; ++x) {
                Color c1 = new Color(red, green, blue, clip(255 * rfpart(intery)));
                putPixel(g, ipart(intery), x, c1);
                Color c2 = new Color(red, green, blue, clip(255 * fpart(intery)));
                putPixel(g, ipart(intery) + 1, x, c2);

                intery = intery + gradient;
            }
        } else {
            for (int x = xpxl1 + 1; x <= xpxl2 - 1; ++x) {
                Color c1 = new Color(red, green, blue, clip(255 * rfpart(intery)));
                putPixel(g, x, ipart(intery), c1);
                Color c2 = new Color(red, green, blue, clip(255 * fpart(intery)));
                putPixel(g, x, ipart(intery) + 1, c2);

                intery += gradient;
            }
        }
    }
}
