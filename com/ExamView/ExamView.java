package com.ExamView;
import com.Components.LittleButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class ExamView extends JFrame {
    public static int screen_height= Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width=Toolkit.getDefaultToolkit().getScreenSize().width;
    private  int width,height;//自己的高宽
    LittleButton current_lbtn;//通过绑定这个方法
    ArrayList<LittleButton> arr_lbtn;
    JPanel jp_timer;
    JPanel jp_sidebar;
    JPanel jp_question_area;
    public ExamView(){
        super();
        JFrame self=this;
        Container jp=this.getContentPane();
        jp.setLayout(null);
        setLayout(null);
        this.setTitle("答题界面");
        arr_lbtn=new ArrayList<>();
        height=960;
        width=1920;
        this.setSize(width,height);


        Border b1=BorderFactory.createLineBorder(Color.black,2);

        jp_question_area=new JPanel();
        jp_sidebar=new JPanel();
        jp_timer=new JPanel();
        jp_timer.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0x0000)));
        jp_sidebar.setBorder(b1);
        jp_question_area.setBorder(b1);


        jp_timer.setSize(1200,100);


        //finished
        JLabel  jl_top=new JLabel("         题目选择");jl_top.setFont(new Font("华文楷体",1,40));
        Font jl_font=new Font("华文楷体",1,25);
        JLabel jl_choice=new JLabel("一、选择题");jl_choice.setFont(jl_font);
        JLabel jl_blank=new JLabel("二、填空题");jl_blank.setFont(jl_font);
        JLabel jl_subjective=new JLabel("三、主观题");jl_subjective.setFont(jl_font);
        jl_blank.setPreferredSize(new Dimension(400,60));
        jl_top.setPreferredSize(new Dimension(400,60));
        jl_subjective.setPreferredSize(new Dimension(400,60));
        jl_choice.setPreferredSize(new Dimension(400,60));
        JPanel jp_choice=new JPanel(new FlowLayout(FlowLayout.LEFT ,20,10));
        JPanel jp_blank=new JPanel(new FlowLayout(FlowLayout.LEFT ,30,30));
        JPanel jp_subjective=new JPanel(new FlowLayout(FlowLayout.LEFT ,30,10));
        jp_blank.setOpaque(false);
        jp_choice.setOpaque(false);
        jp_subjective.setOpaque(false);
        jp_sidebar.setSize(400,960);
        jp_sidebar.setLocation(1200,0);
        jp_sidebar.setBounds(1200,0,400,960);
        arr_lbtn.forEach(littleButton -> {
            littleButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(current_lbtn!=null)
                        current_lbtn.setChoice(0);
                    current_lbtn=littleButton;
                }
            });
        });
        jp_sidebar.add(jl_top);
        jp_sidebar.add(jl_choice);
        jp_sidebar.add(jl_blank);
        jp_sidebar.add(jp_blank);
        jp_sidebar.add(jl_subjective);
        jp_sidebar.add(jp_subjective);
        jp_sidebar.setBackground(Color.white);





        //
        add(jp_timer);
        add(jp_sidebar);
        add(jp_question_area);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //通过设置Choice的值0,1,2来改变jbutton的底色

    }

class RTimer extends  Thread{
    int min,second;
    JLabel jl;
    String text;
    public RTimer(JLabel jl,int Minutes,int Second ,String text){
        min=Minutes;
        second=Second;
        this.jl=jl;
        this.text=text;
        jl.setText( text+String.valueOf(min)+":"+String.valueOf(second) );
    }
    public void run(){
        while (true)
            if(min>=0){
                if(min<1){
                    jl.setForeground(Color.RED);
                }
                if(second>0) {
                    try {
                        sleep(1000);
                        second--;

                        jl.setText(text+String.valueOf(min)+":"+String.valueOf(second) );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(second==0){
                    try {
                        sleep(1000);
                        if (min>0)
                            min--;
                        second=59;
                        jl.setText(text+String.valueOf(min)+":"+String.valueOf(second) );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jl.setText(text+String.valueOf(min)+":"+String.valueOf(second) );
                }
                if (min==0 &&second==0) {
                    break;}
            }
    }
}