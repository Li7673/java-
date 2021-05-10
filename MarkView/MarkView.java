package MarkView;

import com.Components.RButton;
import com.Components.RTextField;
import com.Components.ScrollableLabel;
import until.ClientConf;
import until.StringDeal;

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
    JLabel    jl_stu_name=new ScrollableLabel();
    JLabel    jl_stu_ans=new ScrollableLabel();
    JLabel    jl_question_description=new ScrollableLabel();;
    JLabel    jl_question_right_ans=new ScrollableLabel();
    JLabel  jl_fen=new JLabel();
  public void update_user_ans(){
      Socket socket;
      //客户端输出流，向服务器发消息
      try {
          socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
          //客户端输入流，接收服务器消息
          BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter pw = new PrintWriter(bw, true);
          pw.println("#code=14#");
          String read=br.readLine();
          String result="";
          System.out.println(read);
          jl_stu_ans.setText("");
          jl_stu_ans.repaint();
          String []strings=read.split("~");
          for ( String s:
                  strings  ) {
              if(!s.equals("")){
                  System.out.println(s);
                  if(2==Integer.parseInt(StringDeal.queryString(s,"#type=")))
                  jl_stu_ans.setText(jl_stu_ans.getText()+StringDeal.queryString(s,"#ans=")+"    下一题   ");
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      System.out.println(jl_stu_ans.getText());
  }
 public void update_question() {
  Socket socket;
  //客户端输出流，向服务器发消息
  try {
      socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   //客户端输入流，接收服务器消息
   BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   PrintWriter pw = new PrintWriter(bw, true);
   pw.println("#code=13#");
   String read=br.readLine();
   String result="";

   if(read.equals("")||read==null)
   {
       JOptionPane.showMessageDialog(null,"无题需要批阅");
       this.dispose();
   }
   System.out.println(read);
   jl_question_right_ans.setText("");
   jl_question_description.setText("");
   jl_fen.setText("");
   String []strings=read.split("~");jl_fen.setText("题目总分数是"+StringDeal.queryString(read,"#blank_grade=").replace("~",""));
      for ( String s:
         strings  ) {
          if(!s.equals("")&&s.indexOf("#ans=")!=-1){
              System.out.println(s);
              jl_question_right_ans.setText(jl_question_right_ans.getText()+StringDeal.queryString(s,"#ans="));
              jl_question_description.setText(jl_question_description.getText()+StringDeal.queryString(s,"#description=")+"  下一题  ");
          }
      }
  } catch (IOException e) {
     e.printStackTrace();
 }
   jl_question_description.repaint();
   jl_fen.repaint();
   jl_question_right_ans.repaint();
     System.out.println(jl_fen.getText());
 }
    private  void addFen_ans(){
    System.out.println(jl_stu_ans.getText());
     jl_stu_ans.setText(jl_stu_ans.getText()+"------------------------------------------------------------------------------------------------------ ");
   }

    public void  sendScore ()throws IOException{
        Socket socket;
        //客户端输出流，向服务器发消息
        socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //客户端输入流，接收服务器消息
        // BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷
        String msg="~#code=12"+"#blank="+jt_grade.getText()+"#";
        pw.println(msg);
        //String s=br.readLine();
        //System.out.println(s);
        //br.close();
        pw.close();
        socket.close();
    }
 public  MarkView(){
     update_question();
     update_user_ans();
     init();
 }
 public void  init(){
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
     jl_fen.setSize(dimension);
     jl_fen.setLocation(left+450,top+710);
     jb_next.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
             super.mouseClicked(e);
             if(jt_grade.getText().equals("")){
              JOptionPane.showMessageDialog(null,   "成绩为空");
             }else if(!jt_grade.getText().equals("")){
                 try {
                      sendScore();
                      update_question();
                      update_user_ans();
                 } catch (IOException ioException) {
                     ioException.printStackTrace();
                 }
                 jt_grade.setText("");
             }

         }
     });
     add(jl_fen);
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

}
