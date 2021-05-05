package until;

import Sever.SeverMethod;

import javax.swing.*;

public class StringUtil {
          public static void AnsString(String s){
              String []question=s.split("~");
              for (String a : question){
                  if(s.equals("")){
                      System.out.println("无数据传入");
                      JFrame jFrame=new JFrame("通知讯息");
                      JLabel jLabel=new JLabel("操作错误，请重新操作");
                      jFrame.add(jLabel);
                      jFrame.setLocation(400,200);
                      jFrame.setVisible(true);
                      jFrame.setSize(500,300);
                  }else if(!s.equals("")){
                      int x=a.indexOf("#code=");
                      int code=Integer.parseInt(a.substring(x+6,x+7));
                      /*
                      code=1完成注册
                       */
                      if(code==1){
                          String id=a.substring(a.indexOf("#username=")+10,
                                  a.indexOf("#",a.indexOf("#username")+12));

                          String password=a.substring(a.indexOf("#password=")+10,
                                  a.indexOf("#",a.indexOf("#password")+12));
                          int m=a.indexOf("#is_teacher=");
                          int n=Integer.parseInt(a.substring(m+13,m+14));
                          SeverMethod.ManageRegister(id,password,n);

                      }
                      else if(code==2){
                      /*
                      code=2完成登录
                       */
                          String id=a.substring(a.indexOf("#username=")+10,
                                  a.indexOf("#",a.indexOf("#username=")+12));

                          String password=a.substring(a.indexOf("#password=")+10,
                                  a.indexOf("#",a.indexOf("#password")+12));
                          int m=a.indexOf("#is_teacher=");
                          int is_teacher=Integer.parseInt(a.substring(m+13,m+14));
                          SeverMethod.ManageLogin(id,password,is_teacher);
                      }
                      else if(code==3){
                          /*
                          code=3完成增加题目
                           */
                          int y= Integer.parseInt(a.substring(a.indexOf("#id=")+4,
                                  a.indexOf("#",a.indexOf("#id=")+4)));
                          int z=Integer.parseInt(a.substring(a.indexOf("#difficulty=")+12),
                                  a.indexOf("#",a.indexOf("#difficulty")+12));
                          int q=Integer.parseInt(a.substring(a.indexOf("#type=")+6),
                                  a.indexOf("#",a.indexOf("#type=")+6));
                          String description=a.substring(a.indexOf("#description=")+13
                                  ,a.indexOf("#",a.indexOf("#description=")+13));
                          String ans=a.substring(a.indexOf("#ans=")+5
                                  ,a.indexOf("#",a.indexOf("#ans=")+5));
                          SeverMethod.ManageAddQuestion(y,z,q,description,ans);
                      }
                      else if(code==4){
                          /*
                          code=4完成删除题目
                           */
                          int b= Integer.parseInt(a.substring(a.indexOf("#id=")+4,
                                  a.indexOf("#",a.indexOf("#id=")+4)));
                          int c=Integer.parseInt(a.substring(a.indexOf("#difficulty=")+12),
                                  a.indexOf("#",a.indexOf("#difficulty")+12));
                          int d=Integer.parseInt(a.substring(a.indexOf("#type=")+6),
                                  a.indexOf("#",a.indexOf("#type=")+6));
                          String description=a.substring(a.indexOf("#description=")+13
                                  ,a.indexOf("#",a.indexOf("#description=")+13));
                          String ans=a.substring(a.indexOf("#ans=")+5
                                  ,a.indexOf("#",a.indexOf("#ans=")+5));
                          SeverMethod.ManageDeleteQuestion(b,c,d,description,ans);
                      }

                  }
              }
          }
}
