package com.QuestionView;

import com.Components.Blank_Question;
import com.Components.JPanel_shadowText;
import com.Components.Question;
import com.Components.RButton;
import until.QuestionDeal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class QuestionView extends JFrame {
    public static int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height, width;
    public JPanel jp_questions;
    public Vector<Question> questions;
    public void  update_jp_question(){
        jp_questions.removeAll();
        jp_questions.setBorder(null);
        questions.forEach(question -> {
            Question_show_box questionShowBox = new Question_show_box(question);
            questionShowBox.setQuestionView(this);
            questionShowBox.setSize(1100, 100);
            jp_questions.add(questionShowBox);

        });
        jp_questions.repaint();
    }

    public void  up_jp_question (Question question ) {
        Question_show_box questionShowBox = new Question_show_box(question);
        questionShowBox.setQuestionView(this);
        questionShowBox.setSize(1100, 100);
        jp_questions.add(questionShowBox);
        jp_questions.repaint();
    }

    public QuestionView() {
        this.height = 800;
        this.width = 1500;
        this.setLayout(null);
        //定义头部
         QuestionView self=this;
        JPanel_shadowText jp_top = new JPanel_shadowText("题库维护");
        jp_top.setSize(1100, 100);
        jp_top.setLocation(250, 10);
        int question_x = 250;
        //表头
        JPanel table_columns = new JPanel(null);
        JLabel jl_id = new JLabel("题目id");
        JLabel jl_description = new JLabel("题干");
        JLabel jl_difficulty = new JLabel("题目难度");
        JLabel jl_type = new JLabel("题目类型");
        JLabel jl_action = new JLabel("操作");

        Font font = new Font("微软黑体", 1, 18);
        jl_difficulty.setFont(font);
        jl_description.setFont(font);
        jl_type.setFont(font);
        jl_description.setFont(font);
        jl_id.setFont(font);
        jl_action.setFont(font);
        int inner_height = 60;
        jl_action.setSize(80, inner_height);
        jl_id.setSize(80, inner_height);
        jl_description.setSize(200, inner_height);
        jl_type.setSize(80, inner_height);
        jl_difficulty.setSize(80, inner_height);
        jl_id.setLocation(30, 0);
        jl_type.setLocation(120, 0);
        jl_difficulty.setLocation(200, 0);
        jl_description.setLocation(500, 0);
        jl_action.setLocation(900, 0);
        table_columns.add(jl_id);
        table_columns.add(jl_description);
        table_columns.add(jl_difficulty);
        table_columns.add(jl_type);
        table_columns.add(jl_action);
        table_columns.setSize(1150, 60);
        table_columns.setLocation(question_x, 100);
        table_columns.setBackground(Color.white);
        table_columns.setForeground(Color.GRAY);
        jp_questions = new JPanel();


        //测试数据
        Blank_Question c = new Blank_Question("李志豪难到不是蠢货，弱智吗，不会吧？不会吧？ 李志豪今天必死 啊就哦跑j opj o jpjopjop", 5);
        c.setDifficulty(1);
        Question_show_box question_show_box = new Question_show_box(c);
        String question1 = QuestionDeal.Question_DataBase_to_String(1);
        String question2 = QuestionDeal.Question_DataBase_to_String(2);
        String question3 = QuestionDeal.Question_DataBase_to_String(3);
        String question4 = QuestionDeal.Question_DataBase_to_String(3);

        questions = QuestionDeal.toQuestionVector(question1 + question2 + question3 + question4 + question1 + question1 + question1);
        update_jp_question();



        //测试数据结束


        //side
        JPanel jp_side = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 30));
        JLabel jl_side_top = new JLabel("   题目管理");
        jl_side_top.setPreferredSize(new Dimension(210, 50));
        jl_side_top.setFont(new Font("华文楷体", 1, 30));
        JButton jb_add = new RButton("增加题目");
        JButton jb_new_a_paper = new RButton("自动组卷");
        JButton jb_sum = new RButton("提交试卷");


        jb_add.setPreferredSize(new Dimension(210, 50));
        jb_new_a_paper.setPreferredSize(new Dimension(210, 50));
        jb_add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Single_question_view single_question_view = new Single_question_view();
                single_question_view.setFather_panel(self);
            }
        });
        jp_side.setSize(220, 600);
        jp_side.setLocation(20, 100);
        jp_side.add(jl_side_top);
        jp_side.add(jb_add);
        jp_side.add(jb_new_a_paper);
        jp_side.setBackground(Color.white);


        jp_questions.setPreferredSize(new Dimension(1120, questions.size() * 120));

        JScrollPane js_box = new JScrollPane(jp_questions);
        js_box.setBorder(null);
        System.out.println(js_box.getUI());

        js_box.setMaximumSize(new Dimension(1150, 500));
        js_box.setSize(1150, 500);
        js_box.setLocation(question_x, 180);
        add(table_columns);
        add(js_box);
        add(jp_top);
        add(jp_side);
        this.setSize(width, height);
        this.setLocation((screen_width - width) / 2, (screen_height - height) / 2);
        this.setVisible(true);

    }

    private void QuestionSort() {
         jp_questions.removeAll();

    }

    private void QuestionTypeFitter(int type) {
        jp_questions.removeAll();
        if (questions != null) {
            questions.forEach(question -> {
                if (question.getType() == type) {
                    Question_show_box questionShowBox = new Question_show_box(question);
                    questionShowBox.setSize(1100, 100);
                    jp_questions.add(questionShowBox);
                }
            });
        }
        jp_questions.repaint();
    }

}
class Question_show_box extends JPanel {
    int id;
    JLabel jl_id, jl_description, jl_type, jl_difficulty;
    JPanel jp_button_box;
    boolean is_selected;
    JCheckBox jc_choice;
    QuestionView questionView;
    public boolean isIs_selected() {
        return is_selected;
    }

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }

    public void setQuestionView(QuestionView questionView) {
        this.questionView = questionView;
    }

    public Question_show_box(Question question) {
        Question_show_box self = this;
        Border border = BorderFactory.createLineBorder(new Color(0x039AD6), 2);
        this.setBorder(border);
        this.setLayout(null);
        int inner_height = 90;
        setSize(1100, 100);


        JButton jb_del = new Inner_button("resource/del.png", new Color(0xEEFF0202, true));
        jb_del.createToolTip();
        jb_del.setToolTipText("删除");

        JButton jb_change = new Inner_button("resource/change.png", new Color(0x209506));
        Dimension d_size = new Dimension(40, 40);
        jb_change.setPreferredSize(d_size);
        jb_del.setPreferredSize(d_size);
        jb_change.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Single_question_view single_question_view=new Single_question_view(question);
                single_question_view.setFather_panel(questionView);
                single_question_view.setQuestion_show_box(self);
            }
        });
        jb_del.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                     self.setVisible(false);

                    }
                }
        );


        jl_id = new JLabel(String.valueOf(question.getId()));
        jl_description = new JLabel("<html><p>" + question.getDescription() + "</p></html>");
        jl_difficulty = new JLabel(String.valueOf(question.getDifficulty()));

        jl_type = new JLabel(question.getType() == 2 ? "主观题" : (question.getType()==0?"单选题":"多选题"));
        Font font = new Font("微软黑体", 1, 15);


        jl_difficulty.setFont(font);
        jl_description.setFont(font);
        jl_type.setFont(font);
        jl_description.setFont(font);
        jl_id.setFont(font);


        jl_id.setSize(50, inner_height);
        jl_description.setSize(600, inner_height);
        jl_type.setSize(50, inner_height);
        jl_difficulty.setSize(50, inner_height);
        jb_change.setSize(d_size);
        jb_del.setSize(d_size);



        jl_id.setLocation(30, 0);
        jl_type.setLocation(100, 0);
        jl_difficulty.setLocation(170, 0);
        jl_description.setLocation(240, 0);
        jb_change.setLocation(860, 20);
        jb_del.setLocation(930, 20);


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(!is_selected)
                self.setBackground(new Color(0xFF99BBFD, true));

            }
            @Override
            public  void mouseClicked(MouseEvent e){
                is_selected=!is_selected;
                if(is_selected)
                    self.setBackground(new Color(0x30ACF8));
                else self.setBackground(Color.white);


            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
               if(!is_selected)
               self.setBackground(Color.white);

            }
        });



        add(jl_id);
        add(jl_type);
        add(jl_description);
        add(jl_difficulty);
        add(jb_change);
        add(jb_del);

        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(1100, 100));


    }
}
class IconBtn extends JButton{
    BufferedImage img;
    String text;
    public  IconBtn(String file_path,String text){
        this.text=text;
        try {
            File file=new File(file_path);
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void  paint(Graphics g)
    {
        int h = this.getHeight();
        int w = this.getWidth();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

}

class Inner_button extends JButton {
    BufferedImage img;
    Color c1;

    public Inner_button(String file_path, Color c) {
        this.c1 = c;
        File file = new File(file_path);
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        int h = this.getHeight();
        int w = this.getWidth();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(c1);
        g.fillRoundRect(0, 0, w, h, 10, 10);
        setBorder(null);
        g2d.drawImage(img, 0, 0, w, h, null);

    }
}