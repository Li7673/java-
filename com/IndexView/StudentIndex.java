package com.IndexView;

import com.Components.BgPanel;
//import com.Components.Paper;
import com.Components.RButton;
import until.ClientConf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class StudentIndex extends JFrame {
    public static int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height, width;
    private String id,class_id;
    public StudentIndex(String id,String class_id ) {
        height = 500;
        width = 600;
        this.setTitle("学生主页");
        this.id=id;
        BgPanel bg = new BgPanel(new File("resource/sdu.gif"));

        bg.setSize(width, height);
        bg.setLocation(0, 0);
        this.add(bg);
        setLayout(null);
        JLabel jl_information = new JLabel( "班级："+ class_id+ "    账号："+id);
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
        exam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Paper_choiceView paper_choiceView=new Paper_choiceView(getPapers(),id);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        bg.add(jl_information);
        bg.add(exam);
//        bg.add(guide);


        this.setResizable(false);
        this.setSize(width, height);
        this.setLocation((screen_width - width) / 2, (screen_height - height) / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public String[] getPapers() throws IOException{
        Socket socket;
        //客户端输出流，向服务器发消息
        socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //客户端输入流，接收服务器消息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷
        String msg ="~#code=2#";
        pw.println(msg); //发送给服务器端
        //输出服务器返回的消息
        String s1=br.readLine();
        System.out.println(s1);
        String[] strings=s1.split("~");
        socket.close();
        return  strings;
    }


}
