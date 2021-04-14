package com.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BgPanel extends JPanel {
    Image img=null;
    public BgPanel (File file){
        super();
        try{
            img = ImageIO.read(file);
        }catch (IOException e){
            e.getStackTrace();
            System.out.println(e.toString());
        }}
    public void paintComponent(Graphics g) {
        int w=this.getWidth();
        int h=this.getHeight();
        g.clearRect(0,0,w,h);
        g.drawImage(img,0,0,w,h,null);
        g.setColor(new Color(255,255,255, 200));
        g.fillRect(0,0,w,h);
    }

}
