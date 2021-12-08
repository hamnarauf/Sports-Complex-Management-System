package Database;



import Classes.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbQuery {
    //"jdbc:mysql://hostname:portNumber/databaseName"
    private final static String FILE = "jdbc:mysql://root:33060/sportscomplex";
    
    private static Connection conn;
    private static Statement st;
    
    //general-purpose methods
    private static void setupDb() {
        //for making connection to the database
        String url;
        try {
            Connection connection = DriverManager.getConnection(FILE);
            DbQuery.conn = connection;
            Statement statement = connection.createStatement();
            DbQuery.st = statement;
        } catch (SQLException exc) {
            exc.toString();
        }
    }
    
    private static void tearDownDb() {
        //tears down connection to database
        try {
            DbQuery.st.close();
            DbQuery.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static User checkLoginDetails(String username, String password) throws SQLException,
            ClassNotFoundException {

        setupDb();
        User user = null;

        //login query
        final String query = "SELECT username, password FROM Users "
                + "WHERE username =\"" + username + "\" "
                + "AND password =\"" + password + "\";";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) { //valid credentials
            user = new User(rs.getString("username"), rs.getString("password"));
        }
        
        tearDownDb();
        return user;
    }
    
    public static boolean isCorrectUsername(String uname) throws SQLException{
        setupDb();
        boolean valid;
        
        final String query = "SELECT username FROM Users WHERE username = \"" + uname + "\"";
        
        ResultSet rs = st.executeQuery(query);
        valid = rs.next();  //if any record matches the provided username
        
        tearDownDb();
        return valid;
    }
    
    public static String getSecurityQs(String uname) throws SQLException{
        setupDb();
        String secQs = "";
        
        //retreive stored security question of the provided user
        final String query = "SELECT securityQues FROM Users WHERE username = \"" + uname + "\"";
        
        ResultSet rs = st.executeQuery(query);
        
        if(rs.next()){
            secQs = rs.getString("securityQues");
        }
        
        tearDownDb();
        return secQs;
    }
    
    public static boolean checkSecAns(String uname, String ans) throws SQLException{
        setupDb();
        boolean valid = false;
        
        //checks if the user has provided correct ans to the security question
        final String query = "SELECT securityAns FROM Users WHERE username = \"" + uname + "\"";
        
        ResultSet rs = st.executeQuery(query);
        
        if(rs.next()){
            if (ans.equals(rs.getString("securityAns"))){
                valid = true;
            }
        }
        
        tearDownDb();
        return valid;
    }
    
    public static void passwordRecovery(String uname, String passNew) throws SQLException{
        setupDb();
        
        //updates the password of the given user
        final String query = "UPDATE Users SET password = \"" + passNew + "\" WHERE username = \"" + uname + "\"";
        st.executeUpdate(query); 
        
        tearDownDb();
    } 
     
    public static void registerMember(Member mem) throws SQLException {
        setupDb();

        final String queryPer = "INSERT INTO Person (firstName, lastName, gender, dob, cnic, address," +
                                " contact, emerContact, email, bloodGroup) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        final String queryMem = "INSERT INTO Member VALUES (\"" + mem.getCnic() + "\");";

        java.sql.Date date = new java.sql.Date(mem.getDob().getTime());
        
        try (PreparedStatement statement = conn.prepareStatement(queryPer)) {
            statement.setString(1, mem.getFname());
            statement.setString(2, mem.getLname());
            statement.setString(3, mem.getGen().name());
            statement.setDate(4,date);
            statement.setString(5, mem.getCnic());
            statement.setString(6, mem.getAddress());
            statement.setString(7, mem.getContactNo());
            statement.setString(8, mem.getEmerContact());
            statement.setString(9, mem.getEmail());
            statement.setString(10, mem.getBloodGrp());
            statement.executeUpdate();
        }

        st.executeUpdate(queryMem);       
        tearDownDb();
    }

//    public static ArrayList<Time> getTime(String sport){
//        setupDb();
//        int sport_id;
//        int coach_id;
//
//        final String getSportQuery = "SELECT sport_id FROM Sport WHERE sport = \"" + sport + "\"";
//        final String getCoachQuery = "SELECT coach_id FROM Coach WHERE sport_id = \"" + sport_id + "\"";
//        final String getClassTimeQuery = "SELECT startTime FROM Class WHERE coach_id = \"" + coach_id + "\"";
//    }
//
//    public static void getSportsList(){
//
//    }


//    public static void registerTrainee(Trainee t1){
//        setupDb();
//
//        final String query =
//
//        final String query = "INSERT INTO Person (firstName, lastName, gender, dob, cnic, address," +
//                             " contact, emerContact, email, bloodGroup) \n" +
//                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//
//        
//    }
}
