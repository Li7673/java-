package DataBase;
import Util.StudentandTeacher;

import java.sql.*;

public class DriverOperation{


    public static int insert(String id,String password,int is_teacher) {
        Connection conn = Driver.getConn();
        int i = 0;
        String sql = "insert into studentandteacher(username,password,is_teacher) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2, password);
            pstmt.setString(3, String.valueOf(is_teacher));
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public static int insertquestion(int id,int difficulty,int type,String description,String ans){
              Connection conn=Driver.getConn();
              int i=0;
              String sql="insert into exam_java(id,difficulty,type,description,ans)values(?,?,?,?,?)";
              PreparedStatement psmt;
              try {
                  psmt = (PreparedStatement) conn.prepareStatement(sql);
                  psmt.setInt(1, id);
                  psmt.setInt(2, difficulty);
                  psmt.setInt(3, type);
                  psmt.setString(4, description);
                  psmt.setString(5, ans);
                  i = psmt.executeUpdate();
                  psmt.close();
                  conn.close();
              }catch(SQLException e){
                  e.printStackTrace();
        }
              return i;
    }

    public static int update(StudentandTeacher studentandteacher) {
        Connection conn = Driver.getConn();
        int i = 0;
        String sql = "update studentandteacher set username='" +
                studentandteacher.getUsername() + "' where password='" +
                studentandteacher.getPassword() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public static int updateQuestion(int id,int difficulty,int type,String description,String ans) {
        Connection conn = Driver.getConn();
        int i = 0;
        String sql = "update exam_java set id='" +
                id+ "' where difficulty='" +
                difficulty + "' where type='"+type+"'where description='"+description;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static String  getAll(String id,String password,int is_teacher) {
        Connection conn = Driver.getConn();
        String str=null;
        String sql = "select * from studentandteacher";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("============================");
            while (rs.next()) {
                if(id.equals(rs.getString("username"))&&
                        password.equals(rs.getString("password"))&&
                        String.valueOf(is_teacher).equals(rs.getString("is_teacher")))
                {
                    str="2";
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str ;
    }
  //  public static String getAllQuestion(int id,int difficulty,int type,String description,String ans){
  //      Connection conn = Driver.getConn();
  //      String str=null;
  //      String sql = "select * from exam_java";
  //  }

    public static String delete(String name) {
        Connection conn = Driver.getConn();
      String s=null;
        String sql = "delete from studentandteacher where username='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            s="3";
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static String deleteQuestion(int id,int difficulty,int type,String description,String ans){
        Connection conn = Driver.getConn();
        String s=null;
        String sql = "delete from exam_java where id='" + id + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            s="4";
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }



}
