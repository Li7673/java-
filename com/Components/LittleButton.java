package com.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LittleButton extends JButton {
    boolean hover ,selected;
    public Color BUTTON_COLOR1=new  Color(0x53B880);//未选中填充颜色
    public   Color BUTTON_COLOR2=new Color(0x0E552E);// 选中填充颜色
    public   Color BUTTON_COLOR3=new Color(0xFCC7BD00, true);
    public  Color  BUTTON_COLOR4=new Color(0x005DFE);
    public   Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
    int choice=0;//0 未选中，1选中未点击，2点中
    String name;
    public int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LittleButton(String name,int id){
        super(name);
        this.id=id;
        this.name=name;
        this.hover=false;
        setForeground(Color.white);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(50,50));
        setBackground(BUTTON_COLOR1);
        addMouseListener(new MouseAdapter() {
            @Override
            public  void mouseClicked(MouseEvent e){
//                if(choice!=2) choice=2;
//                else choice=1;
//                repaint();
            }
            public void mouseEntered(MouseEvent e) {
                if (choice!=2)choice=1;
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (choice!=2)choice=0;
                repaint();
            }
        });
    }
    public LittleButton(String name){
        super(name);
        this.id=id;
        this.name=name;
        this.hover=false;
        setForeground(Color.white);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(50,50));
        setBackground(BUTTON_COLOR1);
        addMouseListener(new MouseAdapter() {
            @Override
            public  void mouseClicked(MouseEvent e){
//                if(choice!=2) choice=2;
//                else choice=1;
//                repaint();
            }
            public void mouseEntered(MouseEvent e) {
                if (choice!=2)choice=1;
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (choice!=2)choice=0;
                repaint();
            }
        });
    }
    public void setBUTTON_COLOR1(Color c){
        BUTTON_COLOR1=c;
    }
    public void setBUTTON_COLOR2(Color c){
        BUTTON_COLOR2=c;
    }
    public void setBUTTON_COLOR3(Color c){
        BUTTON_COLOR3=c;
    }
    public void setChoice( int i){
         if (i>3) i=3;

        if(i<0) i=0;
        this.choice=i;
        repaint();
    }
    public int getChoice(){
        return choice;
    }

    public  void paintComponent(Graphics g){
        int h=getHeight();
        int w=getWidth();
        float tran;
        Graphics2D g2d=(Graphics2D)g;
        GradientPaint gp=new GradientPaint(0f,0f,BUTTON_COLOR2,0F,h,BUTTON_COLOR3,true);
        if (choice==0)
        {
            tran=0.3f;
            g2d.setColor(Color.black);
            Font font=new Font("微软黑体",1,35);
            g2d.setFont(font);
            if(name.length()==1)
                g2d.drawString(name,15,38);
            else g2d.drawString(name,5,38);
        } else if(choice==1){
            tran=1f;
        }
        else if(choice==2) {
            tran=1f;
            gp=new GradientPaint(0f,0f,BUTTON_COLOR1,0F,h,BUTTON_COLOR1,true);
        }else {
            tran=1f;
            gp=new GradientPaint(0f,0f,BUTTON_COLOR4,0F,h,BUTTON_COLOR4,true);
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
                h - 1, 10, 10);
        Shape clip = g2d.getClip();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                tran));
        g2d.clip(r2d);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0,0,w,h,20,20);
        g2d.setClip(clip);
        g2d.setColor(Color.white);
        Font font=new Font("微软黑体",1,35);
        g2d.setFont(font);
        if(name.length()==1)
        g2d.drawString(name,15,35);
        else g2d.drawString(name,5,35);
            g2d.dispose();

    }
}