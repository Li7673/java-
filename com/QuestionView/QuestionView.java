package com.QuestionView;

import com.Components.Blank_Question;
import com.Components.JPanel_shadowText;
import com.Components.Question;
import com.Components.RButton;
import until.ClientConf;
import until.Column_chart;
import until.QuestionDeal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class QuestionView extends JFrame {
    public static int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height, width;
    public JPanel jp_questions;
    public Vector<Question> questions;
    public Vector<Question_show_box> question_show_boxes=new Vector<>();
    public String s_questions="";
    public String paper_head;
    //传入形如"#1#2"字符串

    public void  update_question_show_paper(String s){
        jp_questions.removeAll();
        jp_questions.setBorder(null);
         s_questions=s;
        questions.forEach(question -> {
            String ss="#"+question.getId();
            Question_show_box questionShowBox = new Question_show_box(question);
            questionShowBox.setQuestionView(this);
            if(s.indexOf(ss)!=-1){
                questionShowBox.setIs_selected(true);

            }
            questionShowBox.setSize(1100, 100);
            jp_questions.add(questionShowBox);
            question_show_boxes.add(questionShowBox);

        });
        jp_questions.repaint();
    }

    public String getPaper_head() {
        return paper_head;
    }

    public void setPaper_head(String paper_head) {
        this.paper_head = paper_head;
    }

    public void setS_questions(String s){
        this.s_questions=s;
    }

    public String getS_questions() {
        return s_questions;
    }

    public void  update_jp_question(){
        jp_questions.removeAll();
        jp_questions.setBorder(null);
        questions.forEach(question -> {
            Question_show_box questionShowBox = new Question_show_box(question);
            questionShowBox.setQuestionView(this);
            questionShowBox.setSize(1100, 100);
            jp_questions.add(questionShowBox);
            question_show_boxes.add(questionShowBox);

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

    private void get_questions_from_server(){
        Socket socket;
        //客户端输出流，向服务器发消息

        try {
            socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //客户端输入流，接收服务器消息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(bw, true);
        String get="#code=6#";
        pw.println(get);
        String read=br.readLine();
        questions=QuestionDeal.toQuestionVector(read);
        socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    } }
    private void summit(){
        Socket socket;
        //客户端输出流，向服务器发消息

        try {
            socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //客户端输入流，接收服务器消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(bw, true);
            if(paper_head==null||paper_head.equals("")) {
                JOptionPane.showMessageDialog(null, "请先填试卷表头");
                socket.close();
             return;
            }
            String get="#code=10#"+paper_head+"#questions_id="+s_questions+"￥";
            System.out.println(get);
            pw.println(get);
            String read=br.readLine();
            JOptionPane.showMessageDialog(null,"提交成功");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  QuestionView(){
        get_questions_from_server();
        init();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
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
        //测试数据结束
        update_jp_question();

        //side
        JPanel jp_side = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 30));
        JLabel jl_side_top = new JLabel("   题目管理");
        jl_side_top.setPreferredSize(new Dimension(210, 50));
        jl_side_top.setFont(new Font("华文楷体", 1, 30));
        JButton jb_add = new RButton("增加题目");
        JButton jb_new_a_paper = new RButton("自动组卷");
        JButton jb_sum = new RButton("提交试卷");
        JButton jb_sortByType=new RButton("按类型统计");
        JButton jb_sortByDifficulty=new RButton("按难度统计");
        JButton jb_setPaperHead=new RButton("设计表头");

        jb_setPaperHead.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PaperView1(self);
            }
        });

        jb_new_a_paper.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PaperView1 paperView=new PaperView1(self);
            }
        });
        jb_sortByType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] strings={"单选题","多选题","主观题"};
                int[] ints=new int[3];
                //待修改
                ints[0]=QuestionDeal.countQuestion(0);
                ints[1]=QuestionDeal.countQuestion(1);
                ints[2]=QuestionDeal.countQuestion(2);
                Column_chart column_chart=new Column_chart(strings,ints);
            }
        });
        jb_sortByDifficulty.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[]strings={"1","2","3","4","5","6","7","8","9","10"};
                int[] ins=new int[10];
                for (int i=0;i<10;i++){
                  ins[i]=QuestionDeal.countQuestionByDifficulty(i);
                }
                Column_chart column_chart=new Column_chart(strings,ins);
            }
        });


        jb_add.setPreferredSize(new Dimension(210, 50));
        jb_new_a_paper.setPreferredSize(new Dimension(210, 50));
        jb_setPaperHead.setPreferredSize(new Dimension(210, 50));
        jb_sortByDifficulty.setPreferredSize(new Dimension(210, 50));
        jb_sortByType.setPreferredSize(new Dimension(210, 50));
        jb_sum.setPreferredSize(new Dimension(210, 50));
        jb_sum.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                summit();
            }
        });
        jb_add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Single_question_view single_question_view = new Single_question_view();
               // single_question_view.setFather_panel(self);
            }
        });
        jp_side.setSize(220, 600);
        jp_side.setLocation(20, 100);
        jp_side.add(jl_side_top);
        jp_side.add(jb_add);
        jp_side.add(jb_sortByType);
        jp_side.add(jb_sortByDifficulty);
        jp_side.add(jb_setPaperHead);
        jp_side.add(jb_new_a_paper);
        jp_side.add(jb_sum);
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

         if(questions!=null){
             questions.sort(Question::compareTo);
             update_jp_question();
         }

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
    Question_show_box self = this;
    JLabel jl_id, jl_description, jl_type, jl_difficulty;
    JPanel jp_button_box;
    boolean is_selected;
    JCheckBox jc_choice;
    QuestionView questionView;
    public boolean isIs_selected() {
        return is_selected;
    }
    private void  delete(){
        Socket socket;
        //客户端输出流，向服务器发消息
        try {
            socket = new Socket(ClientConf.server_host, ClientConf.port); //创建客户端套接字
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //客户端输入流，接收服务器消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(bw, true);
            String get="#code=7#id="+id+"#";
            pw.println(get);
            String read=br.readLine();
            System.out.println(read);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
        if(is_selected)
        {
            self.setBackground(new Color(0x30ACF8));
            addThisQuestion();

        }
        else {
            self.setBackground(Color.white);
            removeThisQuestion();
        }
    }

    public void setQuestionView(QuestionView questionView) {
        this.questionView = questionView;
    }
    public void addThisQuestion(){
        String s=questionView.getS_questions();
        if(s.indexOf("#"+id)==-1){
        questionView.setS_questions(s+"#"+id);
        }

    }
    public void removeThisQuestion(){
        String s=questionView.getS_questions();
        if(s.indexOf("#"+id)!=-1){
            questionView.setS_questions(s.replace("#"+id,""));
        }
        System.out.println(questionView.getS_questions());
    }
    public Question_show_box(Question question) {
        this.id=question.getId();

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
                //Single_question_view single_question_view=new Single_question_view(question);
                //single_question_view.setFather_panel(questionView);
               // single_question_view.setQuestion_show_box(self);
            }
        });
        jb_del.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                     if(JOptionPane.showConfirmDialog(null,"确定删除?","提示消息",JOptionPane.YES_NO_OPTION)==0) {
                         delete();
                         self.setVisible(false);
                     }}
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
                {
                    self.setBackground(new Color(0x30ACF8));
                    addThisQuestion();

                }
                else {
                    self.setBackground(Color.white);
                    removeThisQuestion();
                }

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