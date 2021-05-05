package until;

import com.Components.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class QuestionDeal {

    public  static  int countQuestion(int type){
        Sql_connection sql_connection=new Sql_connection();
        sql_connection.sql_start();
        int  count=0;
        String sql="select * from "+NetConf.questions_table;
        try {
            ResultSet resultSet= sql_connection.sql_deal(sql);
            while (resultSet.next()){
                if(Integer.parseInt( resultSet.getString("type"))==type)
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         return count;
    }
    public static int countQuestionByDifficulty(int di){
        Sql_connection sql_connection=new Sql_connection();
        sql_connection.sql_start();
        int  count=0;
        String sql="select * from "+NetConf.questions_table;
        try {
            ResultSet resultSet= sql_connection.sql_deal(sql);
            while (resultSet.next()){
                if(Integer.parseInt( resultSet.getString("difficulty"))==di)
                    count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public static String toAnsString(Vector<Ans> ansVector) {
        String s_ans = "";
        for (Ans ans : ansVector)
            s_ans += "~#type=" + ans.getType() + "#id=" + ans.getQuestion_id() + "#ans=" + ans.getAns();
        s_ans += "#";
        return s_ans;
    }
     public static  boolean questionDelete(int data_Id)
     {    boolean is=false;
         Sql_connection sql_connection = new Sql_connection();
         sql_connection.sql_start();
         String sql = "delete from " + NetConf.questions_table + " where id=" + data_Id;
         try {
            is=sql_connection.sql_dealCh(sql);
         } catch (SQLException throwables) {
             throwables.printStackTrace();
             sql_connection.sql_end();
         }
         sql_connection.sql_end();
         return is;
     }
    public static  void questionInsert(String question)
    {    boolean is=false;
        Sql_connection sql_connection = new Sql_connection();
        sql_connection.sql_start();
        String sql="INSERT INTO "+NetConf.questions_table+"(`description`, `type`, `ans`, `difficulty` ) values ("
                +"'"+question.substring(question.indexOf("#description=")+13,question.indexOf("#",question.indexOf("#description=")+14))+"','"
                +Integer.parseInt( question.substring(question.indexOf("#type=")+6,question.indexOf("#",question.indexOf("#type=")+7)))+"','"
                +question.substring(question.indexOf("#ans=")+5,question.indexOf("#",question.indexOf("#ans=")+6))+"','"
                +question.substring(question.indexOf("#difficulty=")+12,question.indexOf("#",question.indexOf("#difficulty=")+13))+"')";

        try {
            is=sql_connection.sql_dealCh(sql);
            System.out.println(is);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            sql_connection.sql_end();
        }
        sql_connection.sql_end();

    }
    //注意s_ans中id是数据库的id
    public static int choiceQuestionCheck(String s_ans) {
        int count = 0;
        Sql_connection sql_connection = new Sql_connection();
        sql_connection.sql_start();
        String[] arr_ans = s_ans.split("~");
        for (String s : arr_ans) {
            try {
                if (!s.equals("")) {
                    int x = s.indexOf("#type=");
                    int type = Integer.parseInt(s.substring(x + 6, x + 7));
                    if (type == 0) {
                        String id = s.substring(s.indexOf("#id=") + 4, s.indexOf("#", s.indexOf("#id")+ 5) );
                        String sql = "select * from " + NetConf.questions_table + " where id=" + id;
                        ResultSet resultSet = sql_connection.sql_deal(sql);
                        if (resultSet.next()) {
                            String data_ans = resultSet.getString("ans");
                            String right_ans = data_ans.substring(data_ans.indexOf("@"),
                                    data_ans.indexOf("￥", data_ans.indexOf("@")));
                            String ans = s.substring(s.indexOf("#ans=") + 5,
                                    s.indexOf("#", s.indexOf("#ans=") + 6));
                            if (right_ans.replace("@", "").equals(ans))
                            count++;
                        }
                    }
                    if (type == 1) {
                        String id = s.substring(s.indexOf("#id=") + 4, s.indexOf("#", s.indexOf("#id") + 5));
                        ResultSet resultSet = sql_connection.sql_deal("select * from " + NetConf.questions_table + " where id=" + id);
                        if (resultSet.next()) {
                            String data_ans = resultSet.getString("ans");
                            String[] ar_ans = data_ans.split("￥");
                            String ans_s1 = "";
                            String ans = s.substring(s.indexOf("#ans=") + 5,
                                    s.indexOf("#", s.indexOf("#ans=") + 6));
                            int ri = 0;
                            for (String s1 : ar_ans) {
                                if (!s1.equals("")) {
                                    if (s1.contains("@")) {
                                        ans_s1 += s1;
                                        ri++;
                                    }
                                }
                            }
                            int zi = 0;
                            String[] now_ar = ans.split("￥");
                            for (String s1 : now_ar) {
                                if (ans_s1.contains("@"+s1))
                                    zi++;
                            }
                            if (zi == ri)
                                count++;
                        }
                    }

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        sql_connection.sql_end();
        return count;
    }


    /*
 问题字符串结构
 ~表示一个题目开始
 #表示结束(题目开头结尾都要有）
 ￥表示选择题目答案的分割
 */
    //问题字符串转Vector
    public static Vector<Question> toQuestionVector(String text) {
        Vector<Question> questions = new Vector<>();
        String[] question = text.split("~");
        int count = -1;
        for (String s : question) {
            if (!s.equals("")) {
                int x = s.indexOf("#type=");
                int type = Integer.parseInt(s.substring(x + 6, x + 7));
                if (1 == type) {
                    MultipleChoice_Question multipleChoice_question = new MultipleChoice_Question();
                    String choice = s.substring(s.indexOf("#choice_ans=") + 12,
                            s.indexOf("#", s.indexOf("#choice_ans=") + 12));
                    String[] choices1 = choice.split("￥");
                    ArrayList<String> choices2 = new ArrayList<>();
                    int i = 0;
                    for (String s1s : choices1) {
                        if (!s1s.equals(""))
                            choices2.add(s1s);
                    }
                    multipleChoice_question.setChoice_box(choices2);
                    questions.add(multipleChoice_question);

                }
                if (0 == type) {
                    Choice_Question choice_question = new Choice_Question();
                    String choice = s.substring(s.indexOf("#choice_ans=") + 12,
                            s.indexOf("#", s.indexOf("#choice_ans=") + 12));
                    String[] choices1 = choice.split("￥");
                    ArrayList<String> choices2 = new ArrayList<>();
                    int i = 0;
                    for (String s1s : choices1) {
                        if (!s1s.equals(""))
                            choices2.add(s1s);
                    }
                    choice_question.setChoice_box(choices2);
                    questions.add(choice_question);

                }
                if (2 == type) {
                    Blank_Question blank_question = new Blank_Question();
                    questions.add(blank_question);
                }
                count++;

                try {
                    questions.get(count).setDifficulty(Integer.parseInt(
                            s.substring(s.indexOf("#difficulty=") + 12,
                                    s.indexOf("#", s.indexOf("#difficulty=") + 13))));
                    questions.get(count).setId(Integer.parseInt(
                            s.substring(s.indexOf("#id=") + 4, s.indexOf("#", s.indexOf("#id=") + 5))));
                    questions.get(count).setDescription(
                            s.substring(s.indexOf("#description=") + 13, s.indexOf("#", s.indexOf("#description=") + 14)));
                } catch (NullPointerException e) {
                    e.getStackTrace();
                }
            }
        }
        return questions;
    }

    //id从数据库中抓题目
    public static String Question_DataBase_to_String(int database_id) {
        Sql_connection sql_connection = new Sql_connection();
        sql_connection.sql_start();
        String text = null;
        try {
            ResultSet set = sql_connection.sql_deal("select * from " + NetConf.questions_table + " where id=" + database_id);
            while (set.next()) {
                int type = Integer.parseInt(set.getString("type"));
                if (type == 0 || type == 1) {
                    text = "~#id=" + database_id + "#type=" + type + "#description=" + set.getString("description")
                            + "#difficulty=" + set.getString("difficulty") + "#choice_ans="
                            + set.getString("ans").replace("@", "")
                            + "#";
                } else {
                    text = "~#id=" + database_id + "#type=" + type + "#description=" + set.getString("description")
                            + "#difficulty=" + set.getString("difficulty") +
                            "#ans" + set.getString("ans")
                            + "#";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sql_connection.sql_end();
        return text;
    }

    public static String Question_DataBase_to_String(int database_id, int now_id) {
        Sql_connection sql_connection = new Sql_connection();
        sql_connection.sql_start();
        String text = null;
        try {
            ResultSet set = sql_connection.sql_deal("select * from questions where id=" + database_id);
            while (set.next()) {
                int type = Integer.valueOf(set.getString("type"));
                if (type == 0 || type == 1) {
                    text = "~#id=" + now_id + "#type=" + type + "#description=" + set.getString("description")
                            + "#difficulty=" + set.getString("difficulty") + "#choice_ans="
                            + set.getString("ans").replace("@", "")
                            + "#";
                } else {
                    text = "~#id=" + now_id + "#type=" + type + "#description=" + set.getString("description")
                            + "#difficulty=" + set.getString("difficulty") +
                            "#ans" + set.getString("ans")
                            + "#";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sql_connection.sql_end();
        return text;
    }

}
