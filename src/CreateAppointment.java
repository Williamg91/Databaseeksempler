import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;

//import much of the recent classes
public class CreateAppointment {
    /*
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static String ip4 = "jdbc:mariadb://192.168.239.24:3306/logins";
    static String ip6 = "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:eed]:3306/logins";
    static String url="";
    String addresse = "jdbc:mariadb://[ip6]:3306/schemanavn";
    private static Connection conn = null;
    private static Statement statement = null;
    private PreparedStatement prep = null;
*/
    //static String inputfraCGI = null;

    //static String[] credentials;

    //  UseDB database = new UseDB();
    public static void main(String[] args) {

        //bruger en GET metode, derfor  kan meget genbruges
        //
        //parse this string aftaledato=2020-12-03T14%3A31
        // showHead();
        //String values = "+date=2021-01-08&time=11%3A05";
        //placeholder for creating conversion algorithm

        //use 24 hour format.

        try {
           // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //String[] data = { in.readLine() };
            //String values = data[0];

            //String cookie = data[1];
            showHead();
            String values = "date=1991-01-27&time=08%3A09";
            String[] buffer = parseargs(values);
            System.out.println(Arrays.toString(buffer));
            java.sql.Date date = createDate(buffer[0]);
            //with the YY-MM-DD
            Time time =createTime(buffer[1]);

            CreateJavaDate(buffer[0],buffer[1]);
          // sendAppointment(time,date);

          //  System.out.println("<p> +"+cookie+" </p>");
            //showSucces(values);
            showTail();
        } catch(Exception ioe) {
            ioe.printStackTrace();
            //System.out.println("<P>IOException reading POST data: "+ioe+"</P>");
        }





    }
    private static java.sql.Date createDate(String s) {
        java.sql.Date dateout = null;
        //based on https://www.javatpoint.com/java-sql-date

        dateout = java.sql.Date.valueOf(s);
        System.out.println("Your date is:"+dateout);
        return dateout;
    }

    private static Time createTime(String s) {
        //logic based on https://www.tutorialspoint.com/java-sql-time-valueof-method-with-example

        java.sql.Time timeout = null;
        timeout = Time.valueOf(s);
        System.out.println("Your time is:"+timeout);
        return timeout;
    }

    private static Date CreateJavaDate(String date,String time){
        //this is necessary in order to make the UseDB
        System.out.println("time:"+time);
        System.out.println("date"+date);
        //https://www.javatpoint.com/java-string-to-date based on this.
        SimpleDateFormat YYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       //Year, month,date, Hour,Minute,second

        Date out = null;
        //if we get a Null, this is where it goes.
        try {
            out = YYMMDDHHMMSS.parse(date+" "+time);
            //add a " " to make input match format.
            System.out.println(out);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return out;
    }

    static private String[] parseargs(String args){
        //vores vaerdi kommer ind som dette her:
//+date=2021-01-08&time=11%3A05
        //We can either make the time be divided according to the substrings or by regexes
        args=args.replace("%3A",":");
        args=args.replace("&time","");

        String[] splitargs = args.split("=");
        //gives us a YY-MM-DD argument, with the Date being at position 1, the time at position 2.
        System.out.println(splitargs[1]+" and "+ splitargs[2]);
        String[] out =new String[2];
        out[0] = splitargs[1];
        out[1]= splitargs[2]+":00";
        //time, added an 00:00 for seconds

        return out;
    }
/*
    private static void sendAppointment(Time time, java.sql.Date date){
        database.insertAppointment(1,date,time);

    }
    */


    private static void showHead() {
        System.out.println("Content-Type: text/html");
        System.out.println();
        System.out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">");
        System.out.println("<HTML>");
        System.out.println("<HEAD>");
        System.out.println("<script>\n" +
                "        setTimeout(\"location.href = 'http://130.226.195.37:39084/';\",5500);\n" +
                "    </script>");
        System.out.println("<TITLE>The CGIpost application</TITLE>");
        System.out.println("<META http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">");
        System.out.println("<META http-equiv=\"Pragma\" content=\"no-cache\">");
        System.out.println("<META http-equiv=\"expires\" content=\"0\">");
        System.out.println("</HEAD>");
        System.out.println("<BODY>");

    }

    private static void showSucces(String dato){
        System.out.println("<p> Du har lavet en aftale og bliver sendt tilbage til oversigten om lidt</p>");
        System.out.println("v&aelig; rdierne er taget fra"+dato);


    }

    private static void showTail(){
        System.out.println("</BODY>\n</HTML>");
    }



}
