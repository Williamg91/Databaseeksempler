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

    //java -cp ".:/usr/local/lib/java/mariadb-java-client-2.6.2.jar:" UseDB
    public void insertAppointment(int userID, Date date, Time time){
        //make sure we convert -
        String SQL = "insert into appointments(dato,tidspunkt,patientid) values (?,?,?);";
        try {


            PreparedStatement AppointmentPrep = connection.prepareStatement(SQL);
            prep.setDate(1,date);
            prep.setTime(2,time);
            prep.setInt(3,userID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
