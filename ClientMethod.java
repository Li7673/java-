//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//public class ClientMethod {
//    public static String LoginSend(String s, StudentandTeacher studentandTeacher){
//        Socket socket=new Socket();
//        try {
//            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
//            oos.writeObject(studentandTeacher);
//            oos.writeObject(s);
//
//
//            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
//
//            String str=(String)ois.readObject();
//            if(str.equals("2")){
//                System.out.println("登陆成功");
//            }else {
//                System.out.println("登录失败");
//            }
//        } catch (IOException|ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "6";
//    }
//
//    public static String RegisterSend(String s, StudentandTeacher studentandTeacher){
//        Socket socket=new Socket();
//        try {
//            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
//            oos.writeObject(studentandTeacher);
//            oos.writeObject(s);
//
//
//            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
//
//            String str=(String)ois.readObject();
//            if(str.equals("1")){
//                System.out.println("注册成功");
//            }else {
//                System.out.println("注册失败");
//            }
//        } catch (IOException|ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "7";
//    }
//
//    public static String AddSend(String s, StudentandTeacher studentandTeacher){
//        Socket socket=new Socket();
//        try {
//            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
//            oos.writeObject(studentandTeacher);
//            oos.writeObject(s);
//
//
//            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
//
//            String str=(String)ois.readObject();
//            if(str.equals("3")){
//                System.out.println("添加成功");
//            }else {
//                System.out.println("添加失败");
//            }
//        } catch (IOException|ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "8";
//    }
//
//    public  static  String DeleteSend(String s, StudentandTeacher studentandTeacher){
//        Socket socket=new Socket();
//        try {
//            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
//            oos.writeObject(studentandTeacher);
//            oos.writeObject(s);
//
//
//            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
//
//            String str=(String)ois.readObject();
//            if(str.equals("4")){
//                System.out.println("删除成功");
//            }else {
//                System.out.println("删除失败");
//            }
//        } catch (IOException|ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "9";
//    }
//}
