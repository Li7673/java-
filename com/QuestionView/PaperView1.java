package com.QuestionView;

import com.Components.JPanel_shadowText;
import com.Components.RButton;
import com.Components.RTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public  class PaperView1 extends JFrame {
    JTextField jt_name,jt_num,jt_choice,jt_blank,jt_difficulty,jt_time;
    QuestionView fatherQuestionView;
    public PaperView1(QuestionView questionView){
        setSize(550,700);
        this.fatherQuestionView=questionView;
        this.setTitle("组卷表头");
        setLayout(null);
        int left_margin=30;
        int top_margin=50;
        int inner_jl_height=80;
        Dimension dimension=new Dimension(200,70);
        Font f1=new Font("微软雅黑",0,23);
        int nap=20;
        JPanel_shadowText jp_title=new JPanel_shadowText("自动组卷");
        JLabel jl_name=new JLabel("名字");
        JLabel jl_num=new JLabel("题目数");
        JLabel jl_choice=new JLabel("每个选择题分数");
        JLabel jl_bl=new JLabel("每个主观题题分数");
        JLabel jl_di=new JLabel("难度系数");
        JLabel jl_time=new JLabel("时间(xx:xx:xx形式)");

        jl_bl.setFont(f1);
        jl_choice.setFont(f1);
        jl_name.setFont(f1);
        jl_di.setFont(f1);
        jl_time.setFont(f1);
        jl_bl.setFont(f1);
        jl_num.setFont(f1);
        jl_bl.setSize(dimension);
        jl_choice.setSize(dimension);;
        jl_di.setSize(dimension);
        jl_name.setSize(dimension);
        jl_time.setSize(dimension);
        jl_num.setSize(dimension);
        jl_name.setLocation(left_margin,top_margin);
        jl_num.setLocation(left_margin,top_margin+inner_jl_height*1);
        jl_time.setLocation(left_margin,top_margin+inner_jl_height*2);
        jl_di.setLocation(left_margin,top_margin+inner_jl_height*3);
        jl_choice.setLocation(left_margin,top_margin+inner_jl_height*4);
        jl_bl.setLocation(left_margin,top_margin+inner_jl_height*5);

        jt_name=new RTextField();
        jt_blank=new RTextField();
        jt_difficulty=new RTextField();
        jt_choice=new RTextField();
        jt_num=new RTextField();
        jt_time=new RTextField();
        jt_blank.setSize(dimension);
        jt_choice.setSize(dimension);
        jt_difficulty.setSize(dimension);
        jt_name.setSize(dimension);
        jt_num.setSize(dimension);
        jt_time.setSize(dimension);

        jt_name.setLocation(left_margin+dimension.width+nap,top_margin);
        jt_num.setLocation(left_margin+dimension.width+nap,top_margin+inner_jl_height*1);
        jt_time.setLocation(left_margin+dimension.width+nap,top_margin+inner_jl_height*2);
        jt_difficulty.setLocation(left_margin+dimension.width+nap,top_margin+inner_jl_height*3);
        jt_choice.setLocation(left_margin+dimension.width+nap,top_margin+inner_jl_height*4);
        jt_blank.setLocation(left_margin+dimension.width+nap,top_margin+inner_jl_height*5);
        RButton sum=new RButton("确定");
        sum.setSize(dimension);
        sum.setLocation( (left_margin+dimension.width+nap)/2,top_margin+inner_jl_height*6);


        sum.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(sum);
        add(jl_name);
        add(jl_bl);
        add(jl_choice);

        add(jl_time);
        add(jt_time);
        add(jt_name);
        add(jt_blank);
        add(jt_choice);

        setVisible(true);
    }
    private  void summit(){
        if(jt_name.getText().equals("")||jt_blank.getText().equals("")
                ||jt_time.getText().equals("")||jt_choice.getText().equals("")){
            JOptionPane.showMessageDialog(null,"请输入完整信息");
        }
       else {
      fatherQuestionView.setPaper_head("~#name="+jt_name.getText()+"#time"+jt_time.getText()
              +"#choice_grade="+jt_choice.getText()+"#blank_grade="+jt_blank.getText());
            System.out.println(fatherQuestionView.getPaper_head());
    }}
}