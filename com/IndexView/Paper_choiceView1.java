package com.IndexView;

import com.Components.BgPanel;
import com.Components.RButton;
import com.ExamView.ExamView;
import until.ClientConf;
import until.Column_chart;
import until.QuestionDeal;
import until.StringDeal;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class Paper_choiceView1 extends JFrame {
    public Paper_choiceView1(){
        this.setTitle("试卷选择");
        String[]strings= new String[0];
        try {
            strings = StudentIndex.getPapers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(500,800);
        Border border1 = BorderFactory.createLineBorder(new Color(0x039AD6), 2);
        BgPanel bgPanel=new BgPanel(new File("resource/bg2.png"));
        bgPanel.setSize(500,800);

        JScrollPane jScrollPane=new JScrollPane();
        JPanel jPanel=new JPanel();

        jPanel.setBorder(border1);

        for(String s:strings){
            if(!s.equals("")){
                RButton rButton=new RButton(StringDeal.queryString(s,"#name="));
                Font font=new Font("微软雅黑",0,50);
                rButton.setPreferredSize(new Dimension(350,100));
                rButton.setFont(font);
                rButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    printColumn(StringDeal.queryString(s,"#id="));
                    }
                });
                jPanel.add(rButton);
            }}
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

    public void printColumn(String id){
        Socket socket;
        //客户端输出流，向服务器发消息
        try {
            socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //客户端输入流，接收服务器消息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(bw, true); //装饰输出流，及时刷
        String msg ="~#code=15#id="+id+"#";
        pw.println(msg); //发送给服务器端
        //输出服务器返回的消息

        String s1=br.readLine(); System.out.println(s1);
         int[] ids=new int[5];
         int i=0;
         String[]strings= StringDeal.queryString(s1,"#arr=").split("￥");
            for (String s :
                strings ) {
                if(!s.equals(""))
                ids[i++]=Integer.parseInt(s);
            }
          int total=Integer.parseInt( StringDeal.queryString(s1,"#total="));
            String []strings1={ ""+total+"~"+total*4/5,""+total*4/5+"~"+total*3/5,""+total*3/5+"~"+total*2/5,""+total*2/5+"~"+total*1/5,""+total*1/5+"~0"};
            new Column_chart(strings1,ids);
        } catch (IOException e) {
        e.printStackTrace();
    }
    }

}

