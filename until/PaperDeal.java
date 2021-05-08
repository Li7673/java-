package until;

import com.Components.Paper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
   public static String automaticCreatePaper(int diff,int num){
       Sql_connection sql_connection=new Sql_connection();
       sql_connection.sql_start();
       int i=0;
       int dif=diff;
       String string="";
       String result="";
       boolean is=false;
       try {
           do {
               String sql = "select * from " + NetConf.questions_table + " where difficulty=" + dif;
               ResultSet resultSet = sql_connection.sql_deal(sql);
               while (resultSet.next()) {
                   result += "#" + resultSet.getString("id");
                   i++;
                   if (i == num) break;
               }
               if(dif>2) {
                   dif--;
               }
               if(dif==1) is=true;
               if(is) break;
           }while (i<num);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return result;
   }

}
