package until;

import com.Components.Paper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperDeal {
    public static String getPaperFromDataBase (int id) {
     Sql_connection sql_connection=new Sql_connection();
     sql_connection.sql_start();
     String S_paper="";

       String sql="select * from "+NetConf.paper_table+" where paper_id="+id;
        ResultSet  resultSet= null;
        try {
            resultSet = sql_connection.sql_deal(sql);
            if (resultSet.next()){
                S_paper="~#name="+resultSet.getString("name")+"#number="+resultSet.getString("number")
                        + "#quesiton_id="+resultSet.getString("questions")
                        +"#";
        }} catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    sql_connection.sql_end();
    return S_paper;
    }
//    public  static Paper toPaper(String text){
//        String name=text.substring(text.indexOf("#"))
//        Paper paper=new Paper();
//
//    }
}
