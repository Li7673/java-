package until;

import java.sql.*;
import java.util.ArrayList;

public class PreQuery {
    String[] columnName;//字段值
    //String [][]record ;//读取记录保存在二维数组中
    Connection con;
    PreparedStatement preSql;
    ResultSet rs;//结果集
    private static   String t;
    public static String gett(){
        return t;
    }

    public static void main(String[] args) {
        PreQuery preQuery=new PreQuery();
        preQuery.startQuery(1);
    }
    public String[] getColumnName()
    {
        return columnName;
    }
    // public String[][] getRecord()
    //{
    //    return record;
    // }
    public void startQuery(int difficulty){
        try {
            Connection conn=null;
            conn = Driver.getConn();

            if(conn!=null){
                String sql = "select * from questions where difficulty = '"+difficulty+"'";
                preSql = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs=preSql.executeQuery();//将执行结果放入结果集
                ResultSetMetaData metaData=rs.getMetaData();

                int ziduanNum=metaData.getColumnCount();//得到字段个数
                columnName=new String[ziduanNum];
                for(int i=1;i<=ziduanNum;i++)
                {
                    columnName[i-1]=metaData.getColumnName(i);
                }
                rs.last();
                int dataNum=rs.getRow();//得到表中共有多少行记录

                rs.beforeFirst();
                int i=0;
                //record=new String[dataNum][ziduanNum];
                String o="";String l="";

                while(rs.next())
                {
                    int id= rs.getInt("id");
                    //System.out.println(id);
                    ArrayList<Integer> sd=new ArrayList<Integer>();
                    sd.add(id);
                    for(int p=0;p<sd.toArray().length;p++){


                        // System.out.println(sd.get(p));
                        String k="#"+sd.get(p);
                        String m="";
                        o= k.concat(m);
                        //w.append(k);
                        l=o.concat("");
                    }

                    System.out.print(l);
                    //System.out.print(w+"");
                    // CallableStatement stmt=con.prepareCall("SELECT CONCAT_WS(1,2,3)AS ConcatenatedString");

                    //PaperUtil paperUtil=new PaperUtil(rs.getInt("id"),rs.getInt("type")
                    // ,rs.getInt("difficulty"),rs.getString("description")
                    // ,rs.getString("ans"));

                    // System.out.print(paperUtil.getId());
                    // System.out.println(paperUtil.getDifficulty());
                    // System.out.print(paperUtil.getAns());

                    //  record.add(paperUtil);

                    // System.out.println(record);
                    //   for(int j=1;j<=ziduanNum;j++)
                    //    {
                    //       record[i][j-1]= (String) rs.getObject(j);//读取所有记录
                    //   }
                    //  i++;
                }

                t=l.concat("#");
                System.out.print(t);
                rs.close();
                preSql.close();
                if(conn!=null){
                    con.close();}else if(conn==null){
                    System.out.println("有问题");
                }
            }
            else{
                System.out.println("连接失败");
            }

        }catch (SQLException |NullPointerException e){
            System.out.println(e);
        }
    }
}