//package ClientSever;
//
//import java.io.IOException;
//import java.net.Socket;
//
//public class ClientSever {
//    private static String localhost="127.0.0.1";
//    private static int PORT=8080;
//
//    public static void main(String[] args) {
//        try {
//            Socket socket=new Socket(localhost,PORT);
//
//            new ClientThread().start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
