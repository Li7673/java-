package until;

import com.Components.Blank_Question;
import com.Components.Choice_Question;
import com.Components.MultipleChoice_Question;
import com.Components.Question;

import java.util.ArrayList;
import java.util.Vector;

public class QuestionDeal {

    /*
 问题字符串结构
 ~表示一个题目开始
 #表示结束(题目开头结尾都要有）
 ￥表示选择题目答案的分割
 */
    //问题字符串转Vector
    public static Vector<Question> toQuestionVector(String text){
        Vector<Question> questions=new Vector<>();
        String[]question=text.split("~");
        int count=-1;
        for (String s : question) {
            if(!s.equals(""))
            {   int x=s.indexOf("type=");
                int type =Integer.parseInt(s.substring(x + 5, x + 6));
                if(1==type)
                {
                    MultipleChoice_Question multipleChoice_question=new MultipleChoice_Question();
                    String  choice= s.substring(  s.indexOf("#choice_ans=")+12,
                            s.indexOf("#",s.indexOf("#choice_ans=")+12));
                    String[]choices1= choice.split("￥");
                    ArrayList<String> choices2=new ArrayList<>();
                    int i=0;
                    for (String s1s :choices1)
                    {
                        if(!s1s.equals(""))
                            choices2.add(s1s);
                    }
                    multipleChoice_question.setChoice_box(choices2);
                    questions.add(multipleChoice_question);

                }
                if (0==type)
                {
                    Choice_Question choice_question=new Choice_Question();
                    String  choice= s.substring(  s.indexOf("#choice_ans=")+12,
                            s.indexOf("#",s.indexOf("#choice_ans=")+12));
                    String[]choices1= choice.split("￥");
                    ArrayList<String> choices2=new ArrayList<>();
                    int i=0;
                    for (String s1s :choices1)
                    {
                        if(!s1s.equals(""))
                            choices2.add(s1s);
                    }
                    choice_question.setChoice_box(choices2);
                    questions.add(choice_question);

                }
                if(2==type) {
                    Blank_Question blank_question=new Blank_Question();
                    questions.add(blank_question);
                }
                count++;

                try{
                    questions.get(count).setDifficulty(Integer.parseInt(
                            s.substring(s.indexOf("#difficulty=")+12,
                                    s.indexOf("#",s.indexOf("#difficulty=")+13))));
                    questions.get(count).setId(Integer.parseInt(
                            s.substring(s.indexOf("#id=")+4,s.indexOf("#",s.indexOf("#id=")+5))));
                    questions.get(count).setDescription(
                            s.substring(s.indexOf("#description=")+13,s.indexOf("#",s.indexOf("#description=")+14)));
                }catch (NullPointerException e)
                {
                    e.getStackTrace();

                }
            }
        }
        return  questions;
    }
}
