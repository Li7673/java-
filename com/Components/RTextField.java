package com.Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RTextField extends JTextField {
    private  int width;
    private  int height;
    public RTextField(){
        super();
        setFont(new Font("system", Font.PLAIN, 24));
        GradientPaint gp1 = new GradientPaint(1.0F, 40F, new Color(0x37FF0000, true), 1.0F,
                80, new Color(0x250044F0, true), true);
        GradientPaint gp2=new GradientPaint(1.0F, 40F, new Color(0xFF0000), 1.0F,
                80, new Color(0x0044F0), true);
        GradientPaint gp3=new GradientPaint(1.0F, 40F, new Color(0xFFFF0077, true), 1.0F,
                80, new Color(0xFFF00000, true), true);
        Border b1=BorderFactory.createDashedBorder(gp1,3.0F,1.0F,0.0F,false);

        Border b2=BorderFactory.createDashedBorder(gp2,3.0F,1.0F,0.0F,false);
        Border b3=BorderFactory.createDashedBorder(gp3,3.0F,1.0F,0.0F,false);
        this.setBorder(b2);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setBorder(b3);
            }
           public void mouseEntered(MouseEvent e){
          setBorder(b1);
        } public void mouseExited(MouseEvent e) {
           setBorder(b2);
            }}
            );
    }
}
