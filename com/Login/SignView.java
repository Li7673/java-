package com.Login;

import com.Components.RButton;
import com.Components.RPasswordField;
import com.Components.RTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignView extends JFrame {
    public static int screen_height= Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screen_width=Toolkit.getDefaultToolkit().getScreenSize().width;
    JTextField jt_user_ID,jt_class_name;
    JPasswordField jt_password;
    JComboBox jc_identity;
    JPanel  jp_sign_center,jp_sign_bottom;
    JLabel  jl_informatoion,jl_warning;
    RButton login_btn,sign_btn;
    public  SignView(){
        super();
        JFrame self=this;
        this.setTitle("注册");
        this.setSize(350,600);
        JPanel jp=new JPanel();
        setLayout(new BorderLayout());
        jp_sign_center=new JPanel();

        jp_sign_center.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        GradientPaint gp2=new GradientPaint(1.0F, 40F, new Color(0xFF0000), 1.0F,
                80, new Color(0x0044F0), true);
        Border b2=BorderFactory.createDashedBorder(gp2,3.0F,1.0F,0.0F,false);


        Font lfont=new Font("华文楷体",1,23);
        jt_user_ID=new RTextField();jt_user_ID.setPreferredSize(new Dimension(200,30));
        jt_password=new RPasswordField();jt_password.setPreferredSize(new Dimension(200,30));
        jt_class_name=new RTextField();jt_class_name.setPreferredSize(new Dimension(200,30));
        RPasswordField jt_repassord=new RPasswordField();
        jt_repassord.setPreferredSize(new Dimension(200,30));
        JLabel jl1=new JLabel("账号");jl1.setPreferredSize(new Dimension(80,40));jl1.setFont(lfont);
        JLabel jl2=new JLabel("密码");jl2.setPreferredSize(new Dimension(80,40));jl2.setFont(lfont);
        JLabel jl5=new JLabel("确认密码");jl2.setPreferredSize(new Dimension(80,40));
        jl5.setFont(new Font("华文楷体",1,19));
        JLabel jl3=new JLabel("班级");jl3.setPreferredSize(new Dimension(80,40));jl3.setFont(lfont);
        JLabel jl4=new JLabel("身份选择");jl4.setPreferredSize(new Dimension(80,40));jl4.setFont(lfont);
        jl4.setFont(new Font("华文楷体",1,19));
        Border padding1=BorderFactory.createEmptyBorder(0,20,0,0);
        Border border1=BorderFactory.createTitledBorder(b2,"备注");
        Border border2=BorderFactory.createCompoundBorder(border1,padding1);
        jl_informatoion=new JLabel("<html><p>账号需6-20位，支持大小写字母，数字。<br>密码6-20位，支持大小写字母</p><html>");
        jl_informatoion.setForeground(Color.blue);jl_informatoion.setPreferredSize(new Dimension(310,60));
        jl_informatoion.setBorder(border2);
        jl_warning=new JLabel();jl_warning.setPreferredSize(new Dimension(310,40));
        jl_warning.setForeground(Color.RED);

        jc_identity=new JComboBox();jc_identity.setPreferredSize(new Dimension(200,40));
        jc_identity.addItem("学生");jc_identity.setFont(lfont);
        jc_identity.addItem("教师");
        jc_identity.setBorder(b2);
        Border padding2=BorderFactory.createEmptyBorder(0,20,0,0);
        login_btn=new RButton("返回登录");
        login_btn.setPreferredSize(new Dimension(300,60));
        login_btn.setBorder(padding2);
        login_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                self.dispose();
            }
        });

        JPanel jp1=new JPanel();
        jp1.setPreferredSize(new Dimension(315,80));
        jp1.add(login_btn,"Center");

        jp_sign_center.add(jl1);
        jp_sign_center.add(jt_user_ID);
        jp_sign_center.add(jl2);
        jp_sign_center.add(jt_password);
        jp_sign_center.add(jl5);
        jp_sign_center.add(jt_repassord);
        jp_sign_center.add(jl3);
        jp_sign_center.add(jt_class_name);
        jp_sign_center.add(jl4);
        jp_sign_center.add(jc_identity);
        jp_sign_center.add(jl_informatoion);
        jp_sign_center.add(jl_warning);
        jp_sign_center.add(jp1);

        jp_sign_bottom=new JPanel();
        sign_btn=new RButton("注册");
        sign_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(!jt_repassord.getPassword().equals(jt_password))
                    jl_warning.setText("两次密码需要相同");
                if(jt_password.getPassword().length<8||jt_password.getPassword().length>20){
                    jl_warning.setText("密码长度应该在8到20位");
                }
            }
        });

        sign_btn.setPreferredSize(new Dimension(300,60));

        jp_sign_bottom.add(sign_btn);


        JLabel top=new JLabel(new ImageIcon("resource/Signtop.png"));
        add(top,"North");
        add(jp_sign_center,BorderLayout.CENTER);
        add(jp_sign_bottom,BorderLayout.SOUTH);
        this.setResizable(false);
        this.setLocation((screen_width-350)/2,(screen_height-600)/2);
        this.setVisible(true);

    }
    private  void sign( ){

    }
    public RButton getLogin_btn() {
        return login_btn;
    }

    public RButton getSign_btn() {
        return sign_btn;
    }
}

