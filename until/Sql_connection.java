package until;

import java.sql.*;

public class Sql_connection {
    Statement statement;
    Connection con;
    ResultSet response;
    public Sql_connection(){
    }
    public void sql_start() {
        try {
            Class.forName(NetConf.getDatabase_driverClassName);
            con = DriverManager.getConnection(NetConf.database_url, NetConf.database_user, NetConf.database_user);
            statement = con.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ResultSet sql_deal(String sql) throws SQLException{
        return statement.executeQuery(sql);
    }
    public void sql_end(){
        try {
            statement.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

