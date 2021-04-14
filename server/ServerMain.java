package server;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class ServerMain {
    static ServerSocket serverSocket;
    private static final int PORT=8888; //端口

    static Connection con;
    public static <ClassReader> void main(String[] args) throws IOException {
        InetAddress ia=InetAddress.getLocalHost(); //获得本机IP地址情况
        System.out.println("主机名："+ ia.getHostName());  //得到主机名
        System.out.println("主机地址："+ ia.getHostAddress()); //得到主机地址
//
//
//
//        InetAddress ia4=InetAddress.getByName("www.google.com" ); //根据域名到DNS查询Ip
//        System.out.println("163 IP:"+ia4.getHostAddress());
//

        Socket socket=null;
        while(true){
            try {
                serverSocket=new ServerSocket(PORT);
                socket = serverSocket.accept(); //等待并取出用户连接，并创建套接字
                System.out.println("新连接，连接地址："+socket.getInetAddress()+"："+socket.getPort()); //客户端信息
//输入流，读取客户端信息
                BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//输出流，向客户端写信息
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                PrintWriter pw=new PrintWriter(bw,true); //装饰输出流，true,每写一行就刷新输出缓冲区，不用flush
                String info=null; //接收用户输入的信息
                while((info=br.readLine())!=null){
                    System.out.println(info); //输出用户发送的消息
                    pw.println("you said:"+info); //向客户端返回用户发送的消息，println输出完后会自动刷新缓冲区
                    if(info.equals("quit")){ //如果用户输入“quit”就退出
                        break;
                    }
                }
            } //如果客户端断开连接，则应捕获该异常，但不应中断整个while循环，使得服务器能继续与其他客户端通信
            catch (IOException e) {e.printStackTrace();}finally{
                if(null!=socket){try {socket.close(); //断开连接
                } catch (IOException e) {e.printStackTrace();}}
            }
        }

//        ServerSocket socket=

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_java?useSSL=false&serverTimezone=UTC","root","Li7673");
//            Statement statement=con.createStatement();
//            String sql="select * from account ";
//            ResultSet response=statement.executeQuery(sql);
//            while (response.next())
//            {  String user_id=response.getString(2);
//                String password=response.getString(3);
//                System.out.println(user_id+"  "+password);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (SQLException throwables) {
//        throwables.printStackTrace();
//    }
    }
}
