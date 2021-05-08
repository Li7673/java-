package until;

public class StringDeal {
    //在字符串中查找
    public  static String queryString(String s,String t){
        int init_index=s.indexOf(t);
        int l=t.length();
        if(s.length()>l){
       return  s.substring(init_index+l,s.indexOf("#",init_index+l+1) );
    }else return "";
    }
   public  static int countString(String s,String t){
        int count=0;
        int location=0;
        while (s.indexOf(t,location)!=-1){
            location=s.indexOf(t,location)+t.length();
            count++;
        }
    return count;
    }

}
