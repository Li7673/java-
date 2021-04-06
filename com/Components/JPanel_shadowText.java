package com.Components;

import javax.swing.*;
import java.awt.*;

public class JPanel_shadowText extends JPanel {
    String text;
    public JPanel_shadowText(String text){
        super();
        this.text=text;

    }
    public void paint(Graphics g) {

        int x = 400;
        int y = 60;
        Font font = new Font("宋体",Font.BOLD,54);
        g.setFont(font);
        g.setColor(Color.GRAY);
        int i = 0;
        while(i<8) {
            g.drawString(text, x, y);
            x += 1;
            y += 1;
            i++;
        }
        g.setColor(Color.BLACK);
        g.drawString(text, x, y);

        Graphics2D g2d=(Graphics2D)g;
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawLine(0,100,1200,100);

    }

}