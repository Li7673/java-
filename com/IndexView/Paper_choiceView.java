package com.IndexView;

import com.Components.BgPanel;
import com.Components.RButton;
import com.ExamView.ExamView;
import until.StringDeal;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Paper_choiceView extends JFrame {
 public Paper_choiceView(String strings[],String user_id){
     this.setTitle("试卷选择");
     this.setSize(500,800);
     Border border1 = BorderFactory.createLineBorder(new Color(0x039AD6), 2);
     BgPanel bgPanel=new BgPanel(new File("resource/bg2.png"));
     bgPanel.setSize(500,800);

     JScrollPane jScrollPane=new JScrollPane();
     JPanel jPanel=new JPanel();

     jPanel.setBorder(border1);

     for(String s:strings){

         RButton rButton=new RButton(StringDeal.queryString(s,"#name="));
         Font font=new Font("微软雅黑",0,50);
         rButton.setPreferredSize(new Dimension(350,100));
         rButton.setFont(font);
         rButton.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 ExamView examView=new ExamView(Integer.parseInt(StringDeal.queryString(s,"#id=")),user_id);
             }
         });
         jPanel.add(rButton);
     }
     jPanel.setPreferredSize(new Dimension(400, strings.length*150));
     jScrollPane.setLocation(40,100);

     jScrollPane.setSize(400,800);
     jScrollPane.setViewportView(jPanel);

     jScrollPane.setBorder(null);
     jScrollPane.repaint();
     jScrollPane.revalidate();
     jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
     bgPanel.add(jScrollPane);

     add(bgPanel);

     this.setVisible(true);
 }
}
