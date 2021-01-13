package Database;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;

public class CGIDBValidate {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
   // static String url = "jdbc:mariadb://192.168.239.24:3306/logins";
    static String url = "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:eed]:3306/logins";
    static String remoteUrl= "jdbc:mariadb://130.226.19537:39024/logins";
    String addressCGIDBValidatee = "jdbc:mariadb://[ip6]:3306/schemanavn";
    private static Connection conn = null;
    private static Statement statement = null;
    private PreparedStatement prep = null;

    static String inputfraCGI = null;

// here we use DBComm as a classname, if you use this method, make sure to change it to whatever class you use the
    // logger from


    public static void main(String[] args) {
        showHead();


        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String[] data = { in.readLine() };

            inputfraCGI= data[0];
         //   System.out.println(data[0]);
           // showBody(new StringTokenizer(data[0],"&\n\r"));

            Class.forName("org.mariadb.jdbc.Driver");

            //mysql skal  ndres senere til MariaDB, localhost til en IPaddresse -
            String user, pass;
            user = "william";
            pass = "budgieboi";
            // url="jdbc:mysql://localhost:3306/phoenixpoint?serverTimezone=Europe/Amsterdam&amp";

            // Skal man fx. bruge 127.0.0.1 til en remote maskine?
//Connection connection =
// DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword");
            //T nk jer om - kan man opn  mariadb forbindelse til en anden maskine uden at  ndre denne her?


            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {

                System.out.println("<p> Im in </p>");
            } else {
                System.out.println("<p> connection not made </p>");
            }

            //find out which columns are in current table:
            statement = conn.createStatement();
            String sql = "select * from loginoplysninger;";
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            // get the column names; column indexes start from 1
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                // Get the name of the column's table name
                String tableName = rsMetaData.getTableName(i);
                System.out.println("<p> column name:=" + columnName +"</p>");
            }

            //
            String finduser = findUser("wilge@dtu.dk","henning");


//            showHead();
  //          showBody();
            //db.getHomeData();
        } catch (Exception   e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();
            System.out.println(errstring);

        }


        showTail();



    }

    private static void showHead() {
        System.out.println("Content-Type: text/html");
        System.out.println();
        System.out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">");
        System.out.println("<HTML>");
        System.out.println("<HEAD>");
        System.out.println("<TITLE>Loginvalidation application</TITLE>");
        System.out.println("<META http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">");
        System.out.println("<META http-equiv=\"Pragma\" content=\"no-cache\">");
        System.out.println("<META http-equiv=\"expires\" content=\"0\">");
        System.out.println("</HEAD>");
        System.out.println("<BODY>");

    }
    private static void showBody(StringTokenizer stringTokenizer){

    }

    private static void showTail(){
        System.out.println("</BODY>\n</HTML>");
    }

    private static String findUser(String mail,String Password){
      String userCPR =null;

      String sqlFindUser = "select idloginoplysninger,cpr,mail from loginoplysninger where password ='" +Password+ "'and mail ='"+ mail +"';";
        try {
            ResultSet rs = statement.executeQuery(sqlFindUser);
            rs.next();
            int id = rs.getInt(1);
            String cpr = rs.getString(2);
            String email = rs.getString(3);
            System.out.println("Id:"+id);
            System.out.println("cpr:"+cpr);
            System.out.println("mail:"+email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userCPR;
    }
}
