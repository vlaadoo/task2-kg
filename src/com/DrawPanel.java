package com;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    ArrayList<DrawObject> prims = new ArrayList<>();

    public void addNewPrimitive(DrawObject prim) {
        prims.add(prim);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < prims.size(); ++i) {
            prims.get(i).draw(g2);
        }
    }
}
