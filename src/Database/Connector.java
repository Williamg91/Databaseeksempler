import java.sql.*;
/*
public abstract class Connector implements Connection {

    private Connection connection = null;
    private
    Statement statement = null;
    private PreparedStatement prep = null;
    private String sql;
    private ResultSet resultSet;

    public Connector(String url, String Password, String login) {
        //to another target.
//todo write a Configuration XML file that contains necessary information about logins, schemas.
    }

    public Connector() {
        //default values
        final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String url2 = "jdbc:mariadb://192.168.239.24:3306/logins";
        String url = "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:eed]:3306/logins";
        String addresse = "jdbc:mariadb://[ip6]:3306/schemanavn";
        try {
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


          //  connection = DriverManager.getConnection(url, user, pass);
            if (connection != null) {

                System.out.println("Im in");
            } else {
                System.out.println("connection not made");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } //catch (SQLException throwables) {
           // throwables.printStackTrace();
        }
    }
}


 */