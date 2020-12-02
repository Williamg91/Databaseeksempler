import java.sql.*;
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
        String test= "1445";
        System.out.println(test.substring(0,2));
        System.out.println(test.substring(2));



    }
    private static Date CreateDate(String input[]){

        Timestamp time =Timestamp.from(Instant.now());
        Date dato = new Date(2222,12,13);
        Time time = new Time()
        return dato;
    }

    static private String[] parseargs(String[] args){
        String[] out =new String[3];
//returner plads 0,1,2 som hhv date, time og minutter
        String inputstring = args[0];
        //String date = yyyy-mm-dd
        //string tid = Ttt-%3Amm vhor tt og mm er timer og minutter
        String date = inputstring.substring(inputstring.indexOf("=")+1,inputstring.indexOf("T"));
        int indexofT = inputstring.indexOf("T");
        String timerogminutter = inputstring.substring(indexofT+1);
        String tidCuttet = timerogminutter.replaceFirst("%3A","");
        String timecut = tidCuttet.substring(0,2);
        String minutCut = tidCuttet.substring(2);

        out[0] = date;
        out[1] = timecut;
        out[2] = minutCut;



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
