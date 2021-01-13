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


    public static void main(String[] args) {
        //bruger en GET metode, derfor  kan meget genbruges
        //
        //parse this string aftaledato=2020-12-03T14%3A31
        // showHead();
        //String values = "+date=2021-01-08&time=11%3A05";
        //placeholder for creating conversion algorithm

        //use 24 hour format.

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String[] data = { in.readLine() };
            String values = data[0];

            //String cookie = data[1];
            showHead();

            String[] buffer = parseargs(values);
            System.out.println(Arrays.toString(buffer));
            createDate(buffer[0]);
            //with the YY-MM-DD
            createTime(buffer[1]);

          //  System.out.println("<p> +"+cookie+" </p>");
            showSucces(values);
            showTail();
        } catch(IOException ioe) {
            System.out.println("<P>IOException reading POST data: "+ioe+"</P>");
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
        s = s+":00";
        java.sql.Time timeout = null;
        timeout = Time.valueOf(s);
        System.out.println("Your time is:"+timeout);
        return timeout;
    }
/*
    static void createAppointment(String[] workwiththis){

        String date,hour,minute,time;
                date= workwiththis[0];
                hour=workwiththis[1];
                minute=workwiththis[2];
                time  = workwiththis[3];


        try{
            String sql = "insert into aftaler(dato,tidspunkt,patientid) values (?,?,?);";
            PreparedStatement prep = conn.prepareStatement(sql);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date mydate = new Date(String.valueOf(format.parse(date)));
            java.sql.Date sqldate = new java.sql.Date(mydate.getTime());
           // System.out.println(sql);



        }

        catch (Exception E){
            E.printStackTrace();
        }

    }

 */


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
        out[1]= splitargs[2];
//returner plads 0,1,2 som hhv date, time og minutter
       // String inputstring = args[0];
        //String date = yyyy-mm-dd
        //string tid = Ttt-%3Amm vhor tt og mm er timer og minutter
      /*  String date = inputstring.substring(inputstring.indexOf("=")+1,inputstring.indexOf("T"));
        int indexofT = inputstring.indexOf("T");
        String timerogminutter = inputstring.substring(indexofT+1);
        String tidCuttet = timerogminutter.replaceFirst("%3A",":");

        String hourcut = tidCuttet.substring(0,2);
        String minuteCut = tidCuttet.substring(tidCuttet.indexOf(":")+1);

        out[0] = date;
        out[1] = hourcut;
        out[2] = minuteCut;
        out[3] = tidCuttet;
*/



        return out;
    }


    private static void showHead() {
        System.out.println("Content-Type: text/html");
        System.out.println();
        System.out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">");
        System.out.println("<HTML>");
        System.out.println("<HEAD>");
        System.out.println("<script>\n" +
                "        setTimeout(\"location.href = 'index.html';\",5500);\n" +
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
