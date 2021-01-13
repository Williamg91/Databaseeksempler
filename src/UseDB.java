import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

public class UseDB {
    PreparedStatement prep;
    Statement statement;
    Connection connection;
    static Connection conn;
    public static void main(String[] args) {
        DatabaseConnection DBC = new DatabaseConnection();
         conn = DBC.getConn("logins");
        if (conn!=null){
            System.out.println("im in");
        }
    }
    public UseDB(){
        DatabaseConnection DBC = new DatabaseConnection();
        conn = DBC.getConn("logins");


    }

    //java -cp ".:/usr/local/lib/java/mariadb-java-client-2.6.2.jar:" UseDB
    public void insertAppointment(int userID, java.sql.Date date, java.sql.Time time){
        //make sure we convert -
        System.out.println("I got...");
        System.out.println(time+" and " +date);
        String SQL = "insert into appointments(dato,tidspunkt,patientid) values (?,?,?);";
        try {
//based on this https://www.thecrazyprogrammer.com/2016/02/how-to-insert-date-and-time-in-mysql-using-java.html

            PreparedStatement AppointmentPrep = conn.prepareStatement(SQL);
            prep.setDate(0, date);
            prep.setTime(1,time);
            prep.setInt(2,userID);
          //  AppointmentPrep.executeUpdate();
            System.out.println(AppointmentPrep.executeUpdate()+" Indsat");

            conn.close();
            // AppointmentPrep.execute();

        } catch (Exception throwables) {
            StringWriter errors = new StringWriter();
            throwables.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();
            System.out.println(errstring);
            //throwables.printStackTrace();
        }


    }

}
