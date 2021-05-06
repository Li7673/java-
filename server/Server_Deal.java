package server;

import com.Components.RButton;
import until.NetConf;
import until.QuestionDeal;
import until.Sql_connection;
import until.StringDeal;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Server_Deal extends Thread{
        //用于处理字符串
        public int  server_type;
        public  String out;
        private Socket socket;
        private  BufferedReader br;
        private BufferedWriter bw;
        private PrintWriter pw;
       public Server_Deal( Socket socket) {
           System.out.println("socket传入");
           this.socket=socket;
           try {
               br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//输出流，向客户端写信息
               bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
               pw = new PrintWriter(bw, true);
               this.start();
           }catch (IOException e){
               e.printStackTrace();

           }
        }
         @Override
        public  void  run(){
            System.out.println("进入run");
            try {
                String read=null;
                while ((read= br.readLine())!=null){
                    System.out.println(read);
                    server_type=Integer.parseInt( StringDeal.queryString(read,"#code="));
                switch (server_type) {
                case 0: {

                }
                case 1:{

                }
                case 2:{
                pw.println(login_deal(read));
            }
                }

        }}catch (IOException e){
                e.printStackTrace();}
        finally {
                try {
                    bw.close();
                    pw.close();
                    br.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
    //登陆状态码,0,1,2分别表示成功，无此账户，密码错误
    public  static String  login_deal(String s){
          String result="err";
        Sql_connection sql_connection=new Sql_connection();
        sql_connection.sql_start();
        String sql= "select * from "+ NetConf.account_table + " where user_id=" +
                (StringDeal.queryString(s,"#user_id=").equals("")?-1: StringDeal.queryString(s,"#user_id=")) ;
        try {
            ResultSet resultSet=sql_connection.sql_deal(sql);
            if(resultSet.next()){
              if(  resultSet.getString("password").equals(StringDeal.queryString(s,"#password="))){
                  int i=Integer.valueOf(  resultSet.getString("is_teacher"));
                  Date date=new Date();
                  String token=s+date.toString();
                  token=String.valueOf( token.hashCode());
                  String sql2="update "+NetConf.account_table+" set token= "+token+" where user_id= "+StringDeal.queryString(s,"#user_id=");
                  sql_connection.sql_dealCh(sql2);
                  result= "~#state=0"+"~#is_teacher="+i+"#token="+token+"#";
              }
              else result= "~#state=2";
            }
            else {
                result= "~#state=1";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            sql_connection.sql_end();
        }
        sql_connection.sql_end();
        return result;
    }
    public static String sign_deal(String s){
        String result="err";
        Sql_connection sql_connection=new Sql_connection();
        sql_connection.sql_start();
        String sql= "select * from "+ NetConf.account_table + " where user_id=" +
                (StringDeal.queryString(s,"#user_id=").equals("")?-1: StringDeal.queryString(s,"#user_id=")) ;
        String user=StringDeal.queryString(s,"#user_id=");
        String passwd=StringDeal.queryString(s,"#password=");
        String classname=StringDeal.queryString(s,"#class_name=");
        String is_teacher=StringDeal.queryString(s,"#is_teacher=");
        try {
            ResultSet resultSet=sql_connection.sql_deal(sql);
            if(resultSet.next()){
                result="已有该账号";
            }
            else {
               String sql2=" insert into "+NetConf.account_table+" (user_id, password, is_teacher, class_name) "
                       +" values ( '"+user+"','"+passwd+"','"+is_teacher+ "','" +classname+"')";
               sql_connection.sql_dealCh(sql2);
               result="注册成功";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            sql_connection.sql_end();
        }
        sql_connection.sql_end();
        return result;
    }
    public static boolean check_token(String s){
        Sql_connection sql_connection=new Sql_connection();
        sql_connection.sql_start();
        String token= StringDeal.queryString(s,"#token=");
        String sql="select * from "+NetConf.account_table+" where token="+token;
        try {
           ResultSet resultSet=sql_connection.sql_deal(sql);
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static String  getPapers_Deal( ){
        Sql_connection sql_connection=new Sql_connection();
        sql_connection.sql_start();
        String sql="select * from "+NetConf.paper_table;
        String paper="";
        try {
            ResultSet resultSet=sql_connection.sql_deal(sql);
            while (resultSet.next())
            {
                paper+="~#name="+resultSet.getString("name")+"#id="+resultSet.getString("paper_id")
                        +"#time="+resultSet.getString("time")+"#";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paper;
    }
   public static String getPaper_head( int id ){
       Sql_connection sql_connection=new Sql_connection();
       sql_connection.sql_start();
       String sql="select * from "+NetConf.paper_table+" where paper_id="+id;
       String result="";
       try {
          ResultSet resultSet=sql_connection.sql_deal(sql);
           if (resultSet.next())
           {
               result="~#name="+resultSet.getString("name")+"#id="+resultSet.getString("paper_id")
                       +"#questions="+resultSet.getString("questions")
                       +"#time="+resultSet.getString("time")+"#number="+resultSet.getString("number")+"#";
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       sql_connection.sql_end();
      return result;
   }
   public static String getPaper_inner(int id){
       Sql_connection sql_connection=new Sql_connection();
       sql_connection.sql_start();
       String sql="select * from "+NetConf.paper_table+" where paper_id="+id;
       String result="";
       String questions_id;
       try {
           ResultSet resultSet=sql_connection.sql_deal(sql);
           if (resultSet.next())
           {   int num=Integer.parseInt(resultSet.getString("number"));
                int i=0;
               questions_id=resultSet.getString("questions");
               String[] strings=questions_id.split("#");
               for (String s:strings){
                   if (s!=null&&!s.equals("")) {
                   result+=QuestionDeal.Question_DataBase_to_String(Integer.parseInt(s),i++);
                   }
               }
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       sql_connection.sql_end();
       return result;
   }
   public static void summit_Ans(String s){

   }


}




