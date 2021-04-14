package com.IndexView;

import com.Components.BgPanel;
import com.Components.RButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class StudentIndex extends JFrame {
    public static int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height, width;

    public StudentIndex() {
        height = 500;
        width = 600;
        this.setTitle("学生主页");

        BgPanel bg = new BgPanel(new File("resource/sdu.gif"));

        bg.setSize(width, height);
        bg.setLocation(0, 0);
        this.add(bg);
        setLayout(null);
        JLabel jl_information = new JLabel("姓名：" + "    班级：" + "    账号：");
        jl_information.setFont(new Font("华文楷体", 1, 30));
        jl_information.setSize(500, 100);
        jl_information.setLocation(50, 50);


        RButton exam = new RButton("考试");
        RButton guide = new RButton("成绩查询");
        exam.setSize(200, 200);
        guide.setSize(200, 200);
        exam.setLocation(50, 220);
        guide.setLocation(330, 220);
        Font font = new Font("华文楷体", 1, 50);
        exam.setFont(font);
        guide.setFont(new Font("华文楷体", 1, 40));
        bg.setLayout(null);


        bg.add(jl_information);
        bg.add(exam);
        bg.add(guide);


        this.setResizable(false);
        this.setSize(width, height);
        this.setLocation((screen_width - width) / 2, (screen_height - height) / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
