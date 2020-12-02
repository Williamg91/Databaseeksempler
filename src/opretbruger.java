import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.StringTokenizer;

public class opretbruger {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static String ip4 = "jdbc:mariadb://192.168.239.24:3306/logins";
    static String ip6 = "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:eed]:3306/logins";
    static String url="";
    String addresse = "jdbc:mariadb://[ip6]:3306/schemanavn";
    private static Connection conn = null;
    private static Statement statement = null;
    private PreparedStatement prep = null;

    static String inputfraCGI = null;

    static String[] credentials;
// here we use DBComm as a classname, if you use this method, make sure to change it to whatever class you use the
    // logger from


    public static void main(String[] args) {
        showHead();


        try {

            if(InetAddress.getLocalHost().getHostName().contains("su")){
                // System.out.println(InetAddress.getLocalHost().getHostName());
                url = ip4;
            }else{
                url = ip6;
                System.out.println("Remote host detected, from url:"+url);
            }
//java -cp ".:/usr/local/lib/java/mariadb-java-client-2.6.2.jar:" opretbruger
            try {
                credentials= parseArgs(args);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {

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


            } else {
                System.out.println("<p> connection not made </p>");
            }

String mail = credentials[0];
            String password=credentials[1];
            insertUser(mail,password);


            //db.getHomeData();
        } catch (Exception   e) {
           // ip4 = "jdbc:mariadb://192.168.239.24:3306/logins";

            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();
            System.out.println(errstring);

        }




        showTail();



    }

    private static String[] parseArgs(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] data = { in.readLine() };

        inputfraCGI= data[0];
        String[] split = inputfraCGI.split("&");
        //deler url op ?name=vaerdi&name=vaerdi2 i et array med 0 og 1 pladsen:
        String[] mailsplit = split[0].split("=",1);

        String[] passwordsplit = split[1].split("=",1);
        //

        String password=passwordsplit[0].substring(passwordsplit[0].indexOf("=")+1,passwordsplit[0].length());
        String mail=mailsplit[0].substring(mailsplit[0].indexOf("=")+1,mailsplit[0].length());

        String[] credentials= new String[2];
        credentials[0] = mail;
        credentials[1] =password;
        return credentials;
    }


    private static void insertUser(String mail,String password){

        mail.replace("%40","@");
                //replace %40 with @
        try {
            String SQLQuery = "insert into loginoplysninger(mail,password) values (?,?);";

            PreparedStatement prep = conn.prepareStatement(SQLQuery);
            prep.setString(1,mail);
            prep.setString(2,password);
            prep.executeUpdate();
showSuccessMessage(mail,password);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            StringWriter errors = new StringWriter();
            throwables.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();

            showError(errstring);
        }

    }

    private static void showHead() {
        System.out.println("Content-Type: text/html");
        System.out.println();
        System.out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">");
        System.out.println("<HTML>");
        System.out.println("<HEAD>");
        System.out.println("<TITLE>user created!</TITLE>");
        System.out.println("<META http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
        System.out.println("<META http-equiv=\"Pragma\" content=\"no-cache\">");
        System.out.println("<META http-equiv=\"expires\" content=\"0\">");
        System.out.println("</HEAD>");
        System.out.println("<BODY>");

    }

    private static void showSuccessMessage(String mail, String password){

        System.out.println("Success, created user with the mail:" +mail);

    }

    public static void showError(String error ) {
        System.out.println("" +
                "<script>\n" +
                "function myFunction() {\n" +
                "  alert(\" Fejl, bruger findes allerede\");\n" +
                "}\n" +
                "</script>");
        System.out.println(error);

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
