package ClientSever;

import Util.StudentandTeacher;

public class ClientThread extends Thread {



    public void run(String s, StudentandTeacher studentandTeacher){
        while (true){
            if(s.equals("1")){
                ClientMethod.RegisterSend(s,studentandTeacher); }
            if(s.equals("2")){
                ClientMethod.LoginSend(s,studentandTeacher); }
            if(s.equals("3")){
                ClientMethod.AddSend(s,studentandTeacher); }
            if(s.equals("4")){
                ClientMethod.DeleteSend(s,studentandTeacher); }

        }
    }
}
