package Util;

public class StringUtil {

    public static boolean isEmpty(String str){
        boolean decide=true;
        if(str==null||"".equals(str.trim()))
            decide=true;
        if(str==null||"".equals(str.trim()))
            decide=false;
        return decide;
    }
}
