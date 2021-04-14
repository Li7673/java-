package QuestionView;

import com.Components.Blank_Question;
import com.Components.JPanel_shadowText;
import com.Components.Question;
import com.Components.RButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QuestionView extends JFrame {
    public static int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height, width;

    public QuestionView() {
        this.height = 800;
        this.width = 1500;
        this.setLayout(null);
        //定义头部

        JPanel_shadowText jp_top=new JPanel_shadowText("题库维护");
        jp_top.setSize(1100,100);
        jp_top.setLocation(250,10);
         int  question_x=250;
        //表头
        JPanel table_columns = new JPanel(null);
        JLabel jl_id = new JLabel("题目id");
        JLabel jl_description = new JLabel("题干");
        JLabel jl_difficulty = new JLabel("题目难度");
        JLabel jl_type = new JLabel("题目类型");
        JLabel jl_action=new JLabel("操作");

        Font font = new Font("微软黑体", 1, 18);
        jl_difficulty.setFont(font);
        jl_description.setFont(font);
        jl_type.setFont(font);
        jl_description.setFont(font);
        jl_id.setFont(font);
        jl_action.setFont(font);
        int inner_height=60;
        jl_action.setSize(80,inner_height);
        jl_id.setSize(80, inner_height);
        jl_description.setSize(200, inner_height);
        jl_type.setSize(80, inner_height);
        jl_difficulty.setSize(80, inner_height);
        jl_id.setLocation(30, 0);
        jl_type.setLocation(120, 0);
        jl_difficulty.setLocation(200, 0);
        jl_description.setLocation(500, 0);
        jl_action.setLocation(900,0);
        table_columns.add(jl_id);
        table_columns.add(jl_description);
        table_columns.add(jl_difficulty);
        table_columns.add(jl_type);
        table_columns.add(jl_action);
        table_columns.setSize(1150,60);
        table_columns.setLocation(question_x,100);
        table_columns.setBackground(Color.white);
        table_columns.setForeground(Color.GRAY);

        //测试数据
        Blank_Question c = new Blank_Question("李志豪难到不是蠢货，弱智吗，不会吧？不会吧？ 李志豪今天必死 啊就哦跑j opj o jpjopjop", 5);
        c.setDifficulty(51);
        Question_show_box question_show_box = new Question_show_box(c);

        question_show_box.setSize(1100, 100);

        Question_show_box question_show_box1 = new Question_show_box(c);

        JPanel jp_questions = new JPanel();


        jp_questions.add(question_show_box1);
        jp_questions.add(question_show_box);
        //测试数据结束


        //side
        JPanel jp_side=new JPanel();
        JLabel jl_side_top=new JLabel("题目管理");
        JButton jb_add=new RButton("增加题目");
        



        jp_questions.setPreferredSize(new Dimension(1120, 400));
        jp_questions.setSize(1150, 400);
        JScrollPane js_box = new JScrollPane(jp_questions);
        js_box.setBorder(null);
        js_box.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        js_box.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        js_box.setSize(1150, 600);
        js_box.setLocation(question_x, 180);
        add(table_columns);
        add(js_box);
        add(jp_top);
        this.setSize(width, height);
        this.setLocation((screen_width - width) / 2, (screen_height - height) / 2);
        this.setVisible(true);

    }
}

class Question_show_box extends JPanel {
    int id;
    JLabel jl_id, jl_description, jl_type, jl_difficulty;
    JPanel jp_button_box;

    public Question_show_box(Question question) {
        JPanel self = this;
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



        jl_id = new JLabel(String.valueOf(question.getId()));
        jl_description = new JLabel("<html><p>" + question.getDescription() + "</p></html>");
        jl_difficulty = new JLabel(String.valueOf(question.getDifficulty()));

        jl_type = new JLabel(question.getType() == 2 ? "主观题" : "客观题");
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
                self.setBackground(new Color(0xFF99BBFD, true));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
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