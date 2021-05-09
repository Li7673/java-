package Util;

public class Transfer {
    private static String s;
    //将按钮中的文字转换为数字，给ClientThread使用
    public Transfer(String s){
        Transfer.s =s;
    }
    public String ActionTransfer(){
        if(s.equals("注册")){ s="1"; }
        if(s.equals("登录")){ s="2"; }
        if(s.equals("增加题目")){ s="3"; }
        if(s.equals("删除")){ s="4"; }
        if(s.equals("修改题目")){s="5"; }
        return s;
    }
    //转换方式
//Transfer a=new Transfer(" ")
//String s=a.ActionTransfer();

}
