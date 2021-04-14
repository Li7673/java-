package server;

import com.Components.Paper;
import com.Components.Question;
import until.NetConf;

import java.net.ServerSocket;
import java.sql.*;
import java.util.Vector;

public class ExamServer extends Thread {

    @Override
    public void run() {
        super.run();
        sql_connection();
    }

    public  void  sql_connection (){
        Connection con;
        try {
            Class.forName(NetConf.getDatabase_driverClassName);
            con= DriverManager.getConnection(NetConf.database_url,NetConf.database_user,NetConf.database_user);
            Statement statement=con.createStatement();
            String sql="select * from questions";
            ResultSet response=statement.executeQuery(sql);
            while (response.next())
            {
                if  (Integer.valueOf( response.getString(3))==0){

            }


            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }


}
