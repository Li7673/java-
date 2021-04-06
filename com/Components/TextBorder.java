package com.Components;

//自己写的textBorder

//public class TextBorder extends AbstractBorder {
//
//    int thickness;
//    boolean is_round;
//  public TextBorder (Color color,int thickness,Boolean is_round){
//
//         this.thickness=thickness;
//         this.is_round=is_round;
//
//  }
//    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
//    {
//        Graphics2D g2d=(Graphics2D) g;
//        RenderingHints r2=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setRenderingHints(r2);
//
//        for (int i=0;i<thickness;i++)
//        {   Color ti=new Color(0,0,0,i*20<255?i*20:255);
//            g2d.setColor(ti);
//            g2d.drawRoundRect(x + i, y + i, width - i - i - 1, height - i - i - 1, 5, 5);
//        }
//
//    }
//}
