

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.sql.*;
import java.util.StringTokenizer;

public class DatabaseConnection {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    String user = "exchange";
    String pass ="helloGuest";
    String url;
   // static String url = "jdbc:mariadb://192.168.239.24:3306/logins";
    String ip4 = "192.168.139.24";
    String ip6 = "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:eed]:3306/logins";
    static String remoteUrl= "jdbc:mariadb://130.226.19537:39024/logins";
    private Connection conn = null;
    private static Statement statement = null;
    private PreparedStatement prep = null;
    public DatabaseConnection(){

        try{
            String localhostname = InetAddress.getLocalHost().getHostName();
            //int number = localhostname.charAt(localhostname.length()-1);
//pseudocode to differentiate if we want a different target for connection.
            if(localhostname.contains("su")){
                // System.out.println(InetAddress.getLocalHost().getHostName());
                url = ip4;
            }else{
                url = ip6;
                System.out.println("Remote host detected:"+localhostname);
            }
        }catch (Exception ex){}


    }

    public Connection getConn(String schema){

        try{
            String samlet = "jdbc:mariadb://"+ip4+":3306/logins";
            String localurl = "localhost:3306/phoenixpoint?serverTimezone=Europe/Amsterdam&amp";

            String IP6Maskine4 =  "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:eed]:3306/su4";
            String IP4Maskine4= "jdbc:mariadb://192.168.239.24:3306/su4";
            //bare for at tage noget, der virker.
           // Class.forName("org.mariadb.jdbc.Driver");
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(IP4Maskine4, user, pass);
        }catch (Exception e){
           e.printStackTrace();
        }


        return conn;
    }



}
