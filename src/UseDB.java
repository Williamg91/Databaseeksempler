import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UseDB {
    private static Statement statement;
    PreparedStatement prep;

    //Connection connection;
    static Connection conn;
    //Statement statement = null;
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
    public void deleteAppointment(int aftaleID){

        try{
            String delete = "delete from su4.appointments where ID="+aftaleID+";";
            Statement stmt = conn.createStatement();
            stmt.execute(delete);


        }catch (SQLException ex){

        }

    }



    public ArrayList findUserAppointments(int id){
        //search according to the patientID OR CPR.
ArrayList output = new ArrayList();
        String findaftaler = "\n" +
                "\n" +
                "SELECT dato,tidspunkt,afdeling,hospital,ID FROM su4.appointments" +
                " where patientid=" + id + ";";

        //https://theopentutorials.com/examples/java/util/date/how-to-convert-java-util-date-to-mysql-date-format/
        //in case we want to show the
        try {
            ResultSet aftaler = statement.executeQuery(findaftaler);
            while (aftaler.next()) {
                //for each row in the database
                output.add(new String[]
                        //build a new string array for our ArrayList

                        {
                                aftaler.getDate(1).toString(),
                                aftaler.getTime(2).toString(),
                                aftaler.getString(3).toString(),
                                aftaler.getString(4).toString(),
                                aftaler.getString(5)
                                // aftaler.getInt("patientid")+""

                        });
                //now we have built the data for each row, lets print it out as HTML
                String HTML = "";

                //we want something clever, to write out as many table data as we have fields returned
                //for now we just will settle for the 5 columns returned

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        if (output != null) {
            return output;
        } else {
            output.add(new String[]{"No patient found"});
            return output;

        }

    }

    //java -cp ".:/usr/local/lib/java/mariadb-java-client-2.6.2.jar:" UseDB
    public void insertAppointment(int userID, java.sql.Date date, java.sql.Time time, java.util.Date javadate){

        SimpleDateFormat YYMMDDHHMMSS = new SimpleDateFormat("HH:mm");

        //make sure we convert -
        System.out.println("I got...");
        System.out.println(time.getTime()+" and " +date);
        String SQL = "insert into appointments(dato,tidspunkt,patientid) values (?,?,?);";
        try {
//based on this https://www.thecrazyprogrammer.com/2016/02/how-to-insert-date-and-time-in-mysql-using-java.html

            PreparedStatement AppointmentPrep = conn.prepareStatement(SQL);
            AppointmentPrep.setDate(1, date);
            AppointmentPrep.setTime(2,time);
            AppointmentPrep.setInt(3,userID);
            AppointmentPrep.executeUpdate();
            //System.out.println(AppointmentPrep.executeUpdate()+" Indsat");
            //AppointmentPrep.close();
            conn.close();
            // AppointmentPrep.execute();

        } catch (Exception throwables) {
            StringWriter errors = new StringWriter();
            throwables.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();
            System.out.println(errstring);

        }


    }

    public static void createUser(String mail,String password,String CPR){

        try{
            String sqlCreate = "insert into su4.loginoplysninger(mail,password,cpr)" +
                    " values(?,?,?);";

            PreparedStatement AppointmentPrep = conn.prepareStatement(sqlCreate);
            AppointmentPrep.setString(1,mail);
            AppointmentPrep.setString(2,password);
            AppointmentPrep.setString(3,CPR);
           // AppointmentPrep.setString(4,type);
            AppointmentPrep.executeUpdate();
            //System.out.println(AppointmentPrep.executeUpdate()+" Indsat");
            //AppointmentPrep.close();
            conn.close();


        }catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();
            System.out.println(errstring);
        }

    }

    public static String findUser(String mail, String Password) {
        String userCPR = null;

        String sqlFindUser = "select idloginoplysninger,cpr,mail from loginoplysninger where password ='" + Password + "'and mail ='" + mail + "';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlFindUser);
            rs.next();
            int id = rs.getInt(1);
            String cpr = rs.getString(2);
            String email = rs.getString(3);
            System.out.println("Id:" + id);
            System.out.println("cpr:" + cpr);
            System.out.println("mail:" + email);
        } catch (SQLException throwables) {
            StringWriter errors = new StringWriter();
            throwables.printStackTrace(new PrintWriter(errors));
            String errstring = errors.toString();
            System.out.println(errstring);

        }

        return userCPR;
    }

}
