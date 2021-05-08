package server;

import com.Components.Ans;
import com.Components.Question;
import com.Components.RButton;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import until.*;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.Date;
import java.util.Vector;

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
                String read=br.readLine();
                {
                    System.out.println(read);
                    server_type=Integer.parseInt( StringDeal.queryString(read,"#code="));
                switch (server_type) {
                case 0: {
                    pw.println(login_deal(read));break;
                }
                case 1:{
                   pw.println(sign_deal(read));break;
                }
                case 2:{
                 pw.println(getPapers_Deal());break;
                }
                //3获得卷头
                case 3:{
                  pw.println(getPaper_head(Integer.parseInt(StringDeal.queryString(read,"#paper_id="))));break;
                    }
                 //4获得卷体
                    case 4:{
                  pw.println(getPaper_inner(Integer.parseInt(StringDeal.queryString(read,"#paper_id="))));break;
                    }
                    //5是试卷提交
                    case 5:{
                  pw.println(summit_Ans(read));break;
                    }
                    //获取题目
                    case 6:{
                  pw.println(request_questions());break;
                    }
                    //删除题目
                    case 7:{
                   QuestionDeal.questionDelete(Integer.parseInt(StringDeal.queryString(read,"#id=")));
                   pw.println("删除成功");break;
                    }
                    //增加题目
                    case 8:{
                        QuestionDeal.questionInsert(read);
                        pw.println("提交成功");break;
                    }
                    //修改题目
                    case 9:{
                        QuestionDeal.changeQuestion(read);
                        pw.println("修改成功");break;
                    }
                    //存储卷子
                    case 10:{
                        summit_paper(read);
                        pw.println("提交成功");break;
                    }
                    //自动组卷的返回
                    case 11:{
                        int difficulty=Integer.parseInt( StringDeal.queryString(read,"#difficulty="));
                        int num=Integer.parseInt(StringDeal.queryString(read,"#num="));
                        pw.println(PaperDeal.automaticCreatePaper(difficulty,num));
                    }
                    //批阅试卷
                    case 12:{

                    }
                }

        }}catch (IOException e){

                e.printStackTrace();}
           finally {
                 try { socket.close();
                 } catch(IOException e) {
                     System.err.println("Socket not closed");
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
                int i=Integer.valueOf(  resultSet.getString("is_teacher"));
                String password=resultSet.getString("password");
                String classname= resultSet.getString("class_name");
              if(  password.equals(StringDeal.queryString(s,"#password="))){

                  Date date=new Date();
                  String token=s+date.toString();
                  token=String.valueOf( token.hashCode());
                  String sql2="update "+NetConf.account_table+" set token= "+token+" where user_id= "+StringDeal.queryString(s,"#user_id=");
                  sql_connection.sql_dealCh(sql2);
                  result= "~#state=0"+"~#is_teacher="+i+"#token="+token+"#class_name="+classname+"#";
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
                   result+=QuestionDeal.Question_DataBase_to_String_exam(Integer.parseInt(s),i++);
                   }
               }
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       sql_connection.sql_end();
       return result;
   }
   //核心将卷子题目Id转为试卷ids;
    public static int summit_Ans(String s){
       Sql_connection sql_connection=new Sql_connection();
       sql_connection.sql_start();
       String s3="";
       String paper_id=StringDeal.queryString(s,"#paper_id=");
       String user_id=StringDeal.queryString(s,"#user_id=");
       String sql="select * from "+NetConf.paper_table+" where paper_id="+paper_id;
       int count=0;
       try {
           ResultSet resultSet=sql_connection.sql_deal(sql);
           int []ids=new int[1000];

           if(resultSet.next()) {
               String questions_id=resultSet.getString("questions");
               String choice_grade=resultSet.getString("choice_grade");
               String blank_grade=resultSet.getString("blank_grade");
               String [] arr_s =questions_id.split("#");
               int i=0;
               for ( String  ss :arr_s){
                   if(!ss.equals("")){
                       ids[i++]=Integer.parseInt(ss);
                   }
               }
               i=0;

            String[] s_ans=s.split("~");
               for (String s2:s_ans ) {
                   if(!s2.equals("")&&s2.indexOf("#id=")!=-1){
                       s2=s2.replace("#id="+i,"#id="+ids[i]);
                       i++;
                       s3+="~"+s2+"#";
                   }
               }
               System.out.println(s3);
                count=QuestionDeal.choiceQuestionCheck(s3);
            String sql3="Insert into "+NetConf.paper_ans_table+ " (`paper_id`, `stu_id`, `ans`, `choice_grade`)"
                    +" values ( '"+ paper_id+"' ,'"+user_id+"','"+s3+"','"+Integer.parseInt(choice_grade)*count+" ')";
               sql_connection.sql_dealCh(sql3);
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
     return count;
   }
   public static void summit_paper(String s){
       Sql_connection sql_connection=new Sql_connection();
       sql_connection.sql_start();

       String questions=s.substring(s.indexOf("#questions_id=")+14,s.indexOf("￥",s.indexOf("#questions_id=")+14));
       String[]strings=questions.split("#");
       int number=0;
       for (String s1:strings
            ) {
           if(!s1.equals(""))
               number++;
       }
       String time=StringDeal.queryString(s,"#time=");
       String choice=StringDeal.queryString(s,"#choice_grade=");
       String blank=StringDeal.queryString(s,"#blank_grade=");
       String num=StringDeal.queryString(s,"#num=");
       String sql="Insert into "+NetConf.paper_table+" ( `questions`, `name`, `time`,`number`,`choice_grade`, `blank_grade` ) "
               +" values ( '"+questions+"','"+StringDeal.queryString(s,"#name=")+"','"+time+"','"+number+"','"+choice+"','"+blank+"' ) ";
       System.out.println(sql);
       try {
           sql_connection.sql_dealCh(sql);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       sql_connection.sql_end();
   }

   public  static  String request_questions(){
       Sql_connection sql_connection=new Sql_connection();
       sql_connection.sql_start();
       String result="";
       String sql="select * from "+NetConf.questions_table;
       try {
           ResultSet set=sql_connection.sql_deal(sql);
           while (set.next()){

                   int type = Integer.parseInt(set.getString("type"));
                   if (type == 0 || type == 1) {
                       result += "~#id=" + set.getString("id") + "#type=" + type + "#description=" + set.getString("description")
                               + "#difficulty=" + set.getString("difficulty") + "#choice_ans="
                               + set.getString("ans").replace("@", "")
                               + "#";
                   } else {
                       result += "~#id=" + set.getString("id") + "#type=" + type + "#description=" + set.getString("description")
                               + "#difficulty=" + set.getString("difficulty") +
                               "#ans=" + set.getString("ans")
                               + "#";
                   }
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return result;
   }


    }






