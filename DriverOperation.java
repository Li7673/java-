package DataBase;
import Util.StudentandTeacher;

import java.sql.*;

public class DriverOperation{


    public static int insert(StudentandTeacher studentandteacher) {
        Connection conn = Driver.getConn();
        int i = 0;
        String sql = "insert into studentandteacher(username,password,is_teacher) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, studentandteacher.getUsername());
            pstmt.setString(2, studentandteacher.getPassword());
            pstmt.setString(3, studentandteacher.getIs_teacher());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int update(StudentandTeacher studentandteacher) {
        Connection conn = Driver.getConn();
        int i = 0;
        String sql = "update studentandteacher set username='" + studentandteacher.getUsername() + "' where password='" + studentandteacher.getPassword() + "'";
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

    public static Integer getAll() {
        Connection conn = Driver.getConn();
        String sql = "select * from studentandteacher";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 16)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int delete(String name) {
        Connection conn = Driver.getConn();
        int i = 0;
        String sql = "delete from studentandteacher where username='" + name + "'";
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



}
