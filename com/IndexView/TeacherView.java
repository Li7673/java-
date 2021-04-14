package com.IndexView;

import com.Components.BgPanel;
import com.Components.RButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TeacherView  extends  JFrame{

    public static int screen_height= Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width=Toolkit.getDefaultToolkit().getScreenSize().width;
    public  int height,width;
    public TeacherView(){
        height=500;
        width=800;
        this.setTitle("教师主页");

        BgPanel bg=new BgPanel(new File("resource/sdu.gif"));

        bg.setSize(width,height);
        bg.setLocation(0,0);
        this.add(bg);
        setLayout(null);
        JLabel jl_information=new JLabel("姓名："+"    账号：");
        jl_information.setFont(new Font("华文楷体",1,30));
        jl_information.setSize(500,100);
        jl_information.setLocation(50,50);


        RButton jb_question_lib=new RButton("题库维护");
        RButton jb_paper_make=new RButton("出卷");
        RButton jb_paper_remark=new RButton("阅卷");
        jb_question_lib.setSize(200,200);
        jb_paper_remark.setSize(200,200);
        jb_paper_make.setSize(200,200);
        jb_question_lib.setLocation(50,220);
        jb_paper_make.setLocation(290,220);
        jb_paper_remark.setLocation(530,220);
        Font font=new Font("华文楷体",1,40);
        jb_question_lib.setFont(font);
        jb_paper_remark.setFont(font);
        jb_paper_make.setFont(new Font("华文楷体",1,40));
        bg.setLayout(null);


        bg.add(jl_information);
        bg.add(jb_question_lib);
        bg.add(jb_paper_make);
        bg.add(jb_paper_remark);
        this.setResizable(false);
        this.setSize(width,height);
        this.setLocation((screen_width-width)/2,(screen_height-height)/2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
