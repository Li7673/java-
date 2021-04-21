package com.ExamView;
import com.Components.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;


public class ExamView extends JFrame {
    JPanel current;
    public static int screen_height= Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width=Toolkit.getDefaultToolkit().getScreenSize().width;
    private  int width,height;//自己的高宽
    LittleButton current_lbtn;//通过绑定这个方法
    Vector<LittleButton> arr_choice_btn,arr_blank_btn,arr_mutiple_btn;
    Vector<Ans> arr_ans=new Vector<>(50);
    Vector<Question> questions=new Vector<>(50);
    JPanel jp_top;
    JPanel jp_sidebar;
    JPanel jp_question_area,jp_timer,jp_bottom;
    JLabel jl_question;
    public  int question_number=50;
    public  int current_question_id;
    public ExamView(){
        super();
        JFrame self=this;
        Container jp=this.getContentPane();
        jp.setLayout(null);
        setLayout(null);
        this.setTitle("答题界面");
        height=960;
        width=1920;
        this.setSize(width,height);
        for (int i=0;i<question_number;i++)
        {   Question question=new Question();
         question.setType(-1);
         question.setId(i);
            questions.add(question);
            arr_ans.add(new Ans(i));
        }
        jp_question_area=new BgPanel( new File("resource/moonbg.jpg"));
        jp_question_area.setLayout (new FlowLayout(FlowLayout.CENTER,0,50));
        jp_sidebar=new JPanel();
        jp_top=new JPanel_shadowText("JAVA考试答题界面");

        jp_timer=new JPanel();
        jp_bottom=new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));
        JLabel jl_timer=new JLabel();
        jl_timer.setFont(new Font("微软黑体",1 ,50 ));
        RTimer timer=new RTimer(jl_timer,1,2 ,"剩余时间: ");
        jp_timer.add(jl_timer);
        jp_top.setBackground(Color.white);
        //top
        jp_top.setSize(1200,100);
        jp_top.setBorder (BorderFactory.createMatteBorder(0,0,5,0,new Color(0x0000)));
        jp_sidebar.setBorder(BorderFactory.createMatteBorder(0,3,0,0,new Color(0x0000)));
        jp_timer.setBorder(BorderFactory.createMatteBorder(0,0,3,0,Color.BLACK));
        jp_timer.setSize(1200,100);
        jp_timer.setLocation(0,100);
        jp_timer.setBackground(Color.white);



        String  te=  "必死对Graphics2像素，，并与设备剪贴区组合，后者是通过窗口可见性和设备范围定义的。（[ 来源：https://aisia.moe/java6api-cn/java/awt/Graphics.html]用户剪贴区和设备剪贴区的组合定义复合剪贴区，复合剪贴区确定最终的剪贴区域。）通过这样,我可以画出圆角的Jbutton（";
        String  te1="dajodjaodjagiurehuawifhevuirgekhueawifgeuirwagufgrveg4uqiwegrfy4gweiaufgbjg bdsfhdajSVfyguidhbliosfnfwefbev";
        String  te2="ton(自己写的Jbutton子类组件），控制自己的状态以求题目改变时变色，但是发现这样耦合度过高，所以在ExamView这个大类内设置一个变量进行控制，监听者注册时读入这个量然后再进行操";
        ArrayList<String> choice_ans=new ArrayList<>();
        choice_ans.add("dadsada");
        choice_ans.add("dasdad8a41d56a4d");
        choice_ans.add("widjadoadadjiaodj");
        ArrayList<String> choice_ans2=new ArrayList<>();
        choice_ans2.add("mo");
        choice_ans2.add("dad");
        choice_ans2.add("dasd");

        int ids[]=new int[3];
        ids[0]=1;
        ids[2]=3;
        Choice_Question ch1=new Choice_Question(te,ids[0],0,choice_ans);
        Blank_Question ch2=new Blank_Question(te1,ids[2]);

        System.out.println(questions.get(2).getType());
        System.out.println(questions.get(2).getDescription());
        questions.add(ch1.getId(), ch1);
        questions.add(ch2.getId(), ch2);
        Choice_QuestionBox q=new Choice_QuestionBox(ch1,arr_ans);
        current_question_id=ch1.getId();
        MultipleChoice_Question ch3=new MultipleChoice_Question(te2,2,choice_ans2);
        questions.add(2,ch3);

        System.out.println(questions.get(2).getDescription());


        updateQuestionArea(1);
        jp_question_area.setSize(1200,550);
        jp_question_area.setLocation(0,200);
        jp_question_area.setLayout(null);
        //side

        JLabel  jl_top=new JLabel("         题目选择");jl_top.setFont(new Font("华文楷体",1,40));
        Font jl_font=new Font("华文楷体",1,25);
        JLabel jl_choice=new JLabel("一、选择题");jl_choice.setFont(jl_font);
        JLabel jl_blank=new JLabel("三、主观题");jl_blank.setFont(jl_font);
        JLabel jl_mutiple=new JLabel("二、多选题");jl_mutiple.setFont(jl_font);
        jl_blank.setPreferredSize(new Dimension(400,40));
        jl_top.setPreferredSize(new Dimension(400,40));
        jl_mutiple.setPreferredSize(new Dimension(400,40));
        jl_choice.setPreferredSize(new Dimension(400,40));
        RButton jb_submit=new RButton("提交试卷");jb_submit.setPreferredSize(new Dimension(315,50));

        JPanel jp_side_choice=new JPanel(new FlowLayout(FlowLayout.LEFT ,20,20));
        JPanel jp_side_blank=new JPanel(new FlowLayout(FlowLayout.LEFT ,20,20));
        JPanel jp_side_mutiple=new JPanel(new FlowLayout(FlowLayout.LEFT ,20,20));
        JScrollPane jsp_side_choice=new JScrollPane(jp_side_choice);
        JScrollPane jsp_side_blank=new JScrollPane(jp_side_blank);
        JScrollPane jsp_side_mutiple=new JScrollPane(jp_side_mutiple);
        jsp_side_blank.setPreferredSize(new Dimension(315,180));
        jsp_side_choice.setPreferredSize(new Dimension(315,180));
        jsp_side_mutiple.setPreferredSize(new Dimension(315,180));;
        jsp_side_blank.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        jsp_side_mutiple.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        jsp_side_choice.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        jp_sidebar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp_sidebar.setSize(490,960);
        jp_sidebar.setLocation(1200,0);






        //side logic

        arr_mutiple_btn=new Vector<>();
        arr_blank_btn=new Vector<>();
        arr_choice_btn=new Vector<>();



        questions.forEach(question -> {
            if(question!=null)
            switch (question.getType()){
                case  0:{
                    LittleButton  littleButton=new LittleButton(String.valueOf(question.getId()));
                    littleButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if(current_lbtn!=null) current_lbtn.setChoice(0);
                            current_lbtn=littleButton;
                            current_lbtn.setChoice(2);
                            updateQuestionArea(Integer.valueOf(littleButton.getText()));
                        }
                    });
                    jp_side_choice.add(littleButton);
                    arr_choice_btn.add(littleButton); break;
                }
                case 1:{
                    System.out.println("sd");
                    LittleButton  littleButton=new LittleButton(String.valueOf(question.getId()));
                    System.out.println(questions.get(Integer.valueOf(littleButton.getText())));
                    System.out.println();
                    littleButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if(current_lbtn!=null)
                                current_lbtn.setChoice(0);
                            current_lbtn=littleButton;
                            current_lbtn.setChoice(2);
                            updateQuestionArea(Integer.valueOf(littleButton.getText()));
                        }
                    });
                    arr_mutiple_btn.add(littleButton);jp_side_mutiple.add(littleButton);
                    break;
                }
                case 2:{
                    LittleButton  littleButton=new LittleButton(String.valueOf(question.getId()));
                    littleButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if(current_lbtn!=null)
                                current_lbtn.setChoice(0);
                            current_lbtn=littleButton;
                            current_lbtn.setChoice(2);
                            updateQuestionArea(Integer.parseInt(littleButton.getText()));
                        }
                    });
                    jp_side_blank.add(littleButton);
                    arr_blank_btn.add(littleButton);
                    break;
                }

            }
        });



        jp_sidebar.add(jl_top);
        if(!arr_choice_btn.isEmpty()){
            jp_sidebar.add(jl_choice);
            jp_sidebar.add(jsp_side_choice);
        }
        if(!arr_mutiple_btn.isEmpty()){
            jp_sidebar.add(jl_mutiple);
            jp_sidebar.add(jsp_side_mutiple);
        }
        if(!arr_blank_btn.isEmpty()){
            jp_sidebar.add(jl_blank);
            jp_sidebar.add(jsp_side_blank);
        }

        jp_sidebar.setBackground(Color.white);
        jp_sidebar.add(jb_submit);
        //sidebar end

        RButton   last=new RButton("上一题");
        RButton   next=new RButton("下一题");
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(current_question_id!=0)
                updateQuestionArea(current_question_id-1);

            }
        });
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(current_question_id!=50)
                    updateQuestionArea(current_question_id+1);

            }
        });
        last.setPreferredSize(new Dimension(250,50));
        next.setPreferredSize(new Dimension(250,50));
        jp_bottom.setSize(1200,100);
        jp_bottom.setLocation(0,760);
        jp_bottom.add(last);
        jp_bottom.add(next);
        jp_bottom.setBackground(Color.white);


        timer.start();

        add(jp_top);
        add(jp_timer);
        add(jp_sidebar);
        add(jp_question_area);
        add(jp_bottom);
        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    private void  updateQuestionArea(int id){
        jp_question_area.removeAll();
        jp_question_area.setSize(1200,550);
        jp_question_area.setLocation(0,200);
        jp_question_area.setLayout(null);
        jp_question_area.repaint();
        current_question_id=id;
        System.out.println("update: " + id);
        System.out.println(questions.get(id).getType());
        if(questions.get(id)!=null && questions.get(id).getDescription()!=null) {
            switch (questions.get(id).getType()) {
                case 0:{
                    Choice_Question choice_question= (Choice_Question) questions.get(id);
                    Choice_QuestionBox choice_questionBox=new Choice_QuestionBox(choice_question,arr_ans);
                    current=choice_questionBox;
                    jp_question_area.add(choice_questionBox);
                    break;
                }
                case 1:{
                    MultipleChoice_Question multipleChoice_question=(MultipleChoice_Question) questions.get(id);
                    Multiple_ChoiceBox multiple_choiceBox=new Multiple_ChoiceBox(multipleChoice_question,arr_ans);
                    current=multiple_choiceBox;
                    jp_question_area.add(multiple_choiceBox);
                    break;
                }
                case 2:{
                    Blank_Question blank_question=(Blank_Question) questions.get(id);
                    Blank_QuestionBox blank_questionBox=new Blank_QuestionBox(blank_question,arr_ans);
                    current=blank_questionBox;
                    jp_question_area.add(blank_questionBox);
                    break;
                }
            }
        }
    }}

class Multiple_ChoiceBox extends JPanel{
    LittleButton current_choice = null;
    ArrayList<String> choice_ans;
    protected int id;
    protected   String description;
    protected JLabel jl_question;

    protected int type;
    public Multiple_ChoiceBox( MultipleChoice_Question question,Vector<Ans> arr_ans){
        description=question.getDescription();
        choice_ans=question.getChoice_box();
        id=question.getId();
        type=1;
        System.out.println("yes");
        jl_question=new JLabel("<html><p>"+description+"</p></html>");
        jl_question.setFont(new Font("微软黑体",1,20));
        GradientPaint gp_qs=new GradientPaint(0,0,Color.RED,0,80f,new Color(0xC8FF00));
        jl_question.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createDashedBorder(gp_qs,3.0f,1.0F,0,true),"选择题"));
        jl_question.setPreferredSize(new Dimension(1100,
                ((int)(description.length()/1.5))>100?((int)(description.length()/1.5)):100));
        add(jl_question);
        ArrayList<LittleButton> choice_ans_box=new ArrayList<>();
        if(type==1){
            choice_ans.forEach(s -> {
                LittleButton littleButton=new LittleButton(s);
                System.out.println(arr_ans.get(id));
                if(arr_ans.get(id).getQuestion_id()!=-1)
                    if(arr_ans.get(id).getAns()!=null &&arr_ans.get(id).getAns().indexOf(s)!=-1)
                    {
                        littleButton.setChoice(2);
                    }
                choice_ans_box.add(littleButton);

            });}
        choice_ans_box.forEach(b ->{
            b.setPreferredSize(new Dimension(600,
                    50));
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                       if(b.getChoice()==2)
                       {
                           b.setChoice(0);
                           arr_ans.get(id).setAns(arr_ans.get(id).getAns().replace(b.getText(),""));
                       }
                       else {
                           b.setChoice(2);
                          if( arr_ans.get(id).getAns()==null||arr_ans.get(id).getAns().equals("")){
                              arr_ans.add(id,new Ans(b.getText(),id,1));

                          }
                          else { arr_ans.get(id).setAns(b.getText()+"-"+arr_ans.get(id).getAns());

                          }}

                       }});
                        //答案写入答案数组
                        JLabel jl_empty=new JLabel();
                      jl_empty.setPreferredSize(new Dimension(600,25));
                      add(jl_empty);
                      add(b);
                });
        setSize(1200,500);

}
}
class Choice_QuestionBox extends JPanel{
    LittleButton current_choice = null;
    ArrayList<String> choice_ans;
    protected int id;
    protected   String description;
    protected JLabel jl_question;
    protected Ans current_ans;
    protected int type;

    public  Choice_QuestionBox(Choice_Question question,Vector<Ans> arr_ans){
        description=question.getDescription();
        choice_ans=question.getChoice_box();
        id=question.getId();
        current_ans=arr_ans.get(id);
        type=question.type;
        jl_question=new JLabel("<html><p>"+description+"</p></html>");
        jl_question.setFont(new Font("微软黑体",1,20));
        GradientPaint gp_qs=new GradientPaint(0,0,Color.RED,0,80f,new Color(0xC8FF00));
        jl_question.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createDashedBorder(gp_qs,3.0f,1.0F,0,true),"选择题"));
        jl_question.setPreferredSize(new Dimension(1100,
                ((int)(description.length()/1.5))>100?((int)(description.length()/1.5)):100));
        add(jl_question);
        //点击变色
        //单选题
        ArrayList<LittleButton> choice_ans_box=new ArrayList<>();
        if(question.type==0){
            choice_ans.forEach(s -> {
                LittleButton littleButton=new LittleButton(s);
                 if(current_ans.getQuestion_id()!=-1)
                     if(current_ans.getAns()!=null &&current_ans.getAns().equals(s))
                     {  littleButton.setChoice(2);
                     current_choice=littleButton;}
                choice_ans_box.add(littleButton);

            });}


        choice_ans_box.forEach(b ->{
                    b.setPreferredSize(new Dimension(600,
                            50));
                    b.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (type==0) {
                                if (current_choice != null)
                                    current_choice.setChoice(0);
                                current_choice = b;
                                current_choice.setChoice(2);
                               current_ans=new Ans(b.getText(), id,0);
                                //答案写入答案数组
                                arr_ans.add(id,current_ans);
                            }

                        }
                    });

                    JLabel jl_empty=new JLabel();
                    jl_empty.setPreferredSize(new Dimension(600,25));
                    add(jl_empty);
                    add(b);
                }

        );

        setSize(1200,500);
    }

}
class QuestionBox extends JLabel{

    protected   String description;
    protected JLabel jl_question;
    protected Ans current_ans;
    protected int type;
    public   QuestionBox(){
        super();
    }



    public String getDescription() {
        return description;
    }

}

class  Blank_QuestionBox extends JPanel{
    JTextArea current_Text;
    public int id;

    protected String description;
    protected JLabel jl_question;
    protected Ans current_ans;
    protected int type;
    public Blank_QuestionBox(Blank_Question question,Vector<Ans> arr_ans){
        System.out.println(arr_ans);
        type=2;
        description=question.getDescription();
        id= question.getId();
        current_ans=arr_ans.get(id);
        current_ans.setType(2);
        jl_question=new JLabel("<html><p>"+description+"</p></html>");
        jl_question.setFont(new Font("微软黑体",1,20));
        GradientPaint gp_qs=new GradientPaint(0,0,Color.RED,0,80f,new Color(0xC8FF00));
        jl_question.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createDashedBorder(gp_qs,3.0f,1.0F,0,true),"主观题"));
        jl_question.setPreferredSize(new Dimension(1100,
                ((int)(description.length()/1.5))>100?((int)(description.length()/1.5)):100));
        add(jl_question);
        current_Text=new RTextArea();
        current_Text.setLineWrap( true);
        current_Text.setWrapStyleWord(true);
        current_Text.setPreferredSize(new Dimension(1100,300));
        current_Text.setFont(new Font("微软黑体",1,20));

        System.out.println("bl"+current_ans);
        if(current_ans.getQuestion_id()!=-1) {
            current_Text.setText(current_ans.getAns());
        }
        else {
            current_ans = new Ans(id);
            arr_ans.add(id, current_ans);
        }

        add(current_Text);
        setSize(1200,500);
        current_Text.addCaretListener(e->{
            current_ans.setAns(current_Text.getText());
        });

    }
}
class RTimer extends  Thread{
    int min,second;
    JLabel jl;
    String text;
    public RTimer(JLabel jl,int Minutes,int Second ,String text){
        min=Minutes;
        second=Second;
        this.jl=jl;
        this.text=text;
        jl.setText( text+String.valueOf(min)+":"+String.valueOf(second) );
    }
    public void run(){
        while (true)
            if(min>=0){
                if(min<1){
                    jl.setForeground(Color.RED);
                }
                if(second>0) {
                    try {
                        sleep(1000);
                        second--;
                        jl.setText(text+ min +":"+ second);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(second==0){
                    try {
                        sleep(1000);
                        if (min>0)
                            min--;
                        second=59;
                        jl.setText(text+ min +":"+ second);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jl.setText(text+ min +":"+ second);
                }
                if (min==0 &&second==0) {
                    break;}
            }
    }
}
