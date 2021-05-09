package MarkView;

import com.Components.RButton;
import com.Components.RTextField;
import com.Components.ScrollableLabel;
import until.ClientConf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class MarkView extends JFrame {
 int height=900,width=1800;
     JTextField jt_grade;
    JLabel jl_question_description,jl_question_right_ans,jl_stu_name,jl_stu_ans;

    public static void main(String[] args) {
        new MarkView();

    }

 public void update_stu_ans() {
  Socket socket;
  //客户端输出流，向服务器发消息
  try {
   socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   //客户端输入流，接收服务器消息
   BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   PrintWriter pw = new PrintWriter(bw, true);
   pw.println("#code=12#");
  String read=br.readLine();

  } catch (IOException e) {
  e.printStackTrace();
 }
 }
private  void addFen_ans(){
     jl_stu_ans.setText(jl_stu_ans.getText()+"-----------------------------------------------------------------------------------------------------------------------------------");
}

 public MarkView(){

      MarkView self=this;
     this.setTitle("批阅主观题");
     this.setSize(width,height);
     this.setLayout(null);
     Border border1 = BorderFactory.createLineBorder(new Color(0x039AD6), 2);
     Border border2=BorderFactory.createLineBorder(new Color(0xF3682D), 2);
     int left=30;
     int top=25;
     Font font=new Font("华文楷体",0,20);
     Dimension dimension=new Dimension(100,50);
     JLabel jl_teacher_tip=new JLabel("老师打分");
     JLabel jl_question_tip=new JLabel("所有题目");
     JLabel jl_question_right_ans_tip=new JLabel("正确答案");
     JLabel jl_question_stu_ans=new JLabel("学生答案");

     jl_question_right_ans_tip.setSize(dimension);
     jl_question_stu_ans.setSize(dimension);
     jl_question_tip.setSize(dimension);
     jl_teacher_tip.setSize(dimension);
     jl_question_right_ans_tip.setFont(font);
     jl_question_stu_ans.setFont(font);
     jl_question_tip.setFont(font);
     jl_teacher_tip.setFont(font);
     jl_question_right_ans_tip.setBorder(border2);
     jl_question_stu_ans.setBorder(border2);
     jl_question_tip.setBorder(border2);
     jl_teacher_tip.setBorder(border2);

     jl_stu_name=new ScrollableLabel();
     jl_stu_ans=new ScrollableLabel();
     jl_question_description=new ScrollableLabel();;
     jl_question_right_ans=new ScrollableLabel();

     jl_stu_name.setBorder(border1);
     jl_stu_ans.setBorder(border1);
     jl_question_description.setBorder(border1);
     jl_question_right_ans.setBorder(border1);
        jl_stu_name.setBackground(Color.white);
        jl_stu_ans.setBackground(Color.white);
        jl_question_description.setBackground(Color.white);
        jl_question_right_ans.setBackground(Color.white);

     jl_question_description.setSize(900,200);
     jl_question_right_ans.setSize(900,300);
     jl_stu_ans.setSize(400,600);
     jl_stu_ans.setLocation(left+1000,top+100);
     jl_question_right_ans.setLocation(left,top+400);
     jl_question_description.setLocation(left,top+100);
     jl_stu_name.setLocation(left+200,top);
     jl_question_tip.setLocation(left,top+40);
     jl_question_right_ans_tip.setLocation(left,top+340);
     jl_question_stu_ans.setLocation(left+1000,top+40);
     jt_grade=new RTextField();
     jt_grade.setSize(100,50);
     jt_grade.setLocation(left+200,top+710);
     jl_teacher_tip.setLocation(left+50,top+710);
     JButton jb_next=new RButton("下一个");
     jb_next.setSize(200,50);
     jb_next.setLocation(left+1100,top+710);
     jb_next.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
             super.mouseClicked(e);
             if(jt_grade.getText().equals("")){
                 System.out.print("成绩为空");
             }else if(!jt_grade.getText().equals("")){
                 try {
                    scord();
                 } catch (IOException ioException) {
                     ioException.printStackTrace();
                 }
                 jt_grade.setText("");
             }

         }
     });

      addFen_ans();

     add(jb_next);
     add(jt_grade);
     add(jl_stu_name);
     add(jl_stu_ans);
     add(jl_question_description);
     add(jl_question_right_ans);
     add(jl_teacher_tip);
     add(jl_question_tip);
     add(jl_question_right_ans_tip);
     add(jl_question_stu_ans);
     this.setVisible(true);
 }

 public void scord ()throws IOException{
     Socket socket;
     //客户端输出流，向服务器发消息
     socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
     //客户端输入流，接收服务器消息
    // BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷

     String msg="~#code=12#"+jt_grade.getText()+"#";
     pw.println(msg);
     //String s=br.readLine();
     //System.out.println(s);

     //br.close();
     pw.close();
     socket.close();
 }



}
