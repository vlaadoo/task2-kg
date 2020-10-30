package com;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    static final int W = 800;
    static final int H = 800;
    static final int D = 2;

    DrawPanel panel = new DrawPanel();

    Main() {

        // Добавление объектов

        panel.addNewPrimitive(new BrLine(20, 15, 90, 100, D, Color.RED));
        panel.addNewPrimitive(new WuLine(150, 15, 110, 70, D, Color.magenta));
        panel.addNewPrimitive(new DDA(200, 15, 265, 80, D, Color.CYAN));

        panel.addNewPrimitive(new BrCircle(40, 140, 30, D, Color.YELLOW));
        panel.addNewPrimitive(new BrEllipse(100, 140, 160, 170, D, Color.MAGENTA));

        panel.addNewPrimitive(new BrArc(60, 200, 90, 250, 0, Math.PI * 1.5, D, Color.GREEN));
        panel.addNewPrimitive(new BrArc(80, 280, 110, 300, 0, Math.PI, D, Color.ORANGE));


        // Снежинка с помощью алгоритма Ву
        double cx = 300;
        double cy = 300;
        double r1 = 80;
        double r2 = r1 * 0.25;
        double r3 = r1 * 0.5;
        for (int i = 0; i < 12; i++) {

            double a1 = 1.0 * i * Math.PI / 6;
            panel.addNewPrimitive(new WuLine(cx, cy, cx + r1 * Math.cos(a1), cy + r1 * Math.sin(a1), D, Color.WHITE));

            double a2 = a1 + Math.PI / 12;
            double a3 = a1 - Math.PI / 12;
            double a4 = a1 + Math.PI / 8;
            double a5 = a1 - Math.PI / 8;

            double cx2 = cx + 0.75 * r1 * Math.cos(a1);
            double cy2 = cy + 0.75 * r1 * Math.sin(a1);

            double cx3 = cx + 0.25 * r1 * Math.cos(a1);
            double cy3 = cy + 0.25 * r1 * Math.sin(a1);

            panel.addNewPrimitive(new WuLine(cx2, cy2, cx2 + r2 * Math.cos(a2), cy2 + r2 * Math.sin(a2), D, Color.WHITE));
            panel.addNewPrimitive(new WuLine(cx2, cy2, cx2 + r2 * Math.cos(a3), cy2 + r2 * Math.sin(a3), D, Color.WHITE));

            panel.addNewPrimitive(new WuLine(cx3, cy3, cx3 + r3 * Math.cos(a4), cy3 + r3 * Math.sin(a4), D, Color.WHITE));
            panel.addNewPrimitive(new WuLine(cx3, cy3, cx3 + r3 * Math.cos(a5), cy3 + r3 * Math.sin(a5), D, Color.WHITE));
        }


        this.getContentPane().setLayout(null);
        this.getContentPane().add(panel);
        panel.setBounds(0, 0, W, H);
        this.setPreferredSize(new Dimension(W, H));
        this.setMinimumSize(new Dimension(W, H));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Task 2");
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new Main();
    }
}
