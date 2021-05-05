package MarkView;

import com.Components.RTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MarkView extends JFrame {
 int height=900,width=1800;
 public MarkView(){
     MarkView self=this;
     this.setSize(width,height);
     Border border = BorderFactory.createLineBorder(new Color(0x039AD6), 2);
     int left=30;
     int top=25;
     JLabel jl_yuanti=new JLabel();
     JLabel jl_yuanti_ans=new JLabel();
     JLabel jl_teacher=new JLabel("打分");
     JLabel jl_stu=new JLabel();
     jl_stu.setBorder(border);
     jl_yuanti.setBorder(border);
     jl_yuanti_ans.setBorder(border);
     jl_teacher.setBorder(border);
     Font font=new Font("华文楷体",0,23);
     jl_stu.setFont(font);
     jl_teacher.setFont(font);
     jl_yuanti.setFont(font);
     jl_yuanti_ans.setFont(font);
     jl_yuanti.setSize(1200,200);
     jl_yuanti_ans.setSize(1200,380);
     JTextField jt_grade=new RTextField();



     add(jl_stu);
     add(jl_teacher);
     add(jl_yuanti);
     add(jl_yuanti_ans);
 }

}
