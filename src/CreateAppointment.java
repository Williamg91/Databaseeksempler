import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;

//import much of the recent classes
public class CreateAppointment {
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


    public static void main(String[] args) {
        //bruger en GET metode, derfor  kan meget genbruges
        //
        //parse this string aftaledato=2020-12-03T14%3A31
        String[] dummy = new String[1];

        dummy[0]="aftaledato=2020-12-03T14%3A31";


        String[] workwiththis = parseargs(dummy);
        for (String i: workwiththis){
            System.out.println(i);
        }

        try {

            if(InetAddress.getLocalHost().getHostName().contains("su")){
                // System.out.println(InetAddress.getLocalHost().getHostName());
                url = ip4;
            }else{
                url = ip6;
                System.out.println("Remote host detected, from url:"+url);
            }

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


            }

            //find out which columns are in current table:
            statement = conn.createStatement();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        createAppointment(workwiththis);

        //Date aftaledato = CreateDate(workwiththis);





    }

    static void createAppointment(String[] workwiththis){

        String date,hour,minute,time;
                date= workwiththis[0];
                hour=workwiththis[1];
                minute=workwiththis[2];
                time  = workwiththis[3];


        try{
            String sql = "insert into aftaler(dato,tidspunkt,patientid) values (?,?,?);";
            PreparedStatement prep = conn.prepareStatement(sql);
//vi skal indsætte et MySQL
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date mydate = new Date(String.valueOf(format.parse(date)));
            java.sql.Date sqldate = new java.sql.Date(mydate.getTime());
           // System.out.println(sql);



        }

        catch (Exception E){
            E.printStackTrace();
        }

    }


    private static Date CreateDate(String input[]){

       int year,month,day;
        System.out.println(input[0]);
       //input[0] = yyyy-mm-dd
        year =Integer.parseInt(input[0].substring(0,4));
        month =Integer.parseInt(input[0].substring(5,7).toString()); //hvis under 10 bliver det til 0X
        day =Integer.parseInt(input[0].substring(8,10)); //samme
        System.out.println(year+month+day);

        String date = input[0];

        Date dato = null;
        try {
            dato = new SimpleDateFormat("dd-MM-yyyyThh:mm" ).parse(date+input[1]+":"+input[2]);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(dato.toString());
        return dato;
    }

    static private String[] parseargs(String[] args){
        String[] out =new String[4];
//returner plads 0,1,2 som hhv date, time og minutter
        String inputstring = args[0];
        //String date = yyyy-mm-dd
        //string tid = Ttt-%3Amm vhor tt og mm er timer og minutter
        String date = inputstring.substring(inputstring.indexOf("=")+1,inputstring.indexOf("T"));
        int indexofT = inputstring.indexOf("T");
        String timerogminutter = inputstring.substring(indexofT+1);
        String tidCuttet = timerogminutter.replaceFirst("%3A",":");

        String hourcut = tidCuttet.substring(0,2);
        String minuteCut = tidCuttet.substring(tidCuttet.indexOf(":")+1);

        out[0] = date;
        out[1] = hourcut;
        out[2] = minuteCut;
        out[3] = tidCuttet;




        return out;
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

    private static void showSucces(){
        System.out.println();


    }

    private static void showTail(){
        System.out.println("</BODY>\n</HTML>");
    }



}
