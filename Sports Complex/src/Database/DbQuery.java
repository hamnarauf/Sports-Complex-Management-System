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
    
    public static void passwordNew(String uname, String passNew) throws SQLException{
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

    public static ArrayList<String> getSportsList() throws SQLException{
        setupDb();
        ArrayList<String> sportName = new ArrayList<String>();
        
        final String query = "SELECT sportName FROM Sport;";
        
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            sportName.add(rs.getString("sportName"));
        }
        
        tearDownDb();
        return sportName;
    }
    
    public static int getSportID(String sport) throws SQLException{
        setupDb();
        int sport_id = 0;

        final String getSportQuery = "SELECT sport_id FROM Sport WHERE sport = \"" + sport + "\"";
        ResultSet rs = st.executeQuery(getSportQuery);
        
        if(rs.next()){
            sport_id = rs.getInt("sport_id");
        }

        tearDownDb();
        return sport_id;
    }

    public static ResultSet getCoachOfSport(int sport_id) throws SQLException{
        setupDb();
        
        final String getCoachQuery = "SELECT coach_id FROM Coach WHERE sport_id = \"" + sport_id + "\"";
        ResultSet rs = st.executeQuery(getCoachQuery);

        tearDownDb();
        return rs;
    }

    public static ArrayList<Time> getTime(String sport) throws SQLException{
        setupDb();

        int coach_id = 0;
        ResultSet rs;
        ResultSet rs2;
        ArrayList<Time> startTime = new ArrayList <Time>();
        
        int sport_id = getSportID(sport);
        rs = getCoachOfSport(sport_id);
        
        while(rs.next()){
            coach_id = rs.getInt("coach_id");
            
            final String getClassTimeQuery = "SELECT startTime FROM Class WHERE coach_id = \"" + coach_id + "\"";
            rs2 = st.executeQuery(getClassTimeQuery);
            
            startTime.add(rs2.getTime("startTime"));
        }
        
        tearDownDb();
        return startTime;
    }

    public static boolean isMember(String cnic) throws SQLException{
        setupDb();
        boolean valid;
        
        final String query = "SELECT member_id FROM Member WHERE cnic = \"" + cnic + "\"";
        
        ResultSet rs = st.executeQuery(query);
        valid = rs.next();  //if any record matches the provided username
        
        tearDownDb();
        return valid;
    }
    
    public static void registerTrainee(Trainee t1) throws SQLException{
        setupDb();

        int sport_id = getSportID(t1.getSport());
        int class_id = 0;
        
        final String getClassQuery = "SELECT Class.class_id FROM Class " +
        "INNER JOIN Coach ON Coach.coach_id = Class.coach_id " + 
        "WHERE sport_id = \"" + sport_id + "\" AND startTime = \"" + t1.getStartTime() + "\"";

        ResultSet rs = st.executeQuery(getClassQuery);
        
        if (rs.next()){
            class_id = rs.getInt("Class.class_id");
        }
            
        final String regTraineeQuery = "INSERT INTO Trainee VALUES (" + t1.getMember_id() + ", " + class_id + ")";

        st.executeUpdate(regTraineeQuery);
        tearDownDb();
    }

    public static ArrayList<Member> displayMembers() throws SQLException{
        setupDb();

        ArrayList<Member> memberList = new ArrayList<Member>();

        final String query = "SELECT Person.cnic, Person.firstName, Person.lastName, Person.dob, " +
        "Person.gender, Person.contact, Person.email, Member.member_id " +
        "FROM Person INNER JOIN Member ON Member.cnic = Person.cnic;";

        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
           Member m = new Member(rs.getString("Person.firstName"), rs.getString("Person.lastName"), 
                    gender.valueOf(rs.getString("Person.gender")), rs.getDate("Person.dob"), 
                    rs.getString("Person.cnic"), rs.getString("Person.contact"), 
                    rs.getString("Person.email"), rs.getString("Member.member_id"));

            memberList.add(m);
        }

        tearDownDb();
        return memberList;
    }

    public static void registerGuest(Guest g) throws SQLException{
        setupDb();

        final String query = "INSERT INTO Guest (cnic, member_id, firstName, lastName) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, g.getCnic());
            statement.setString(2, g.getMember_id());
            statement.setString(3, g.getFirstName());
            statement.setString(4, g.getLastName());
            statement.executeUpdate();
        }

        tearDownDb();
    }

    public static String getMemberCnic(String member_id) throws SQLException{
        setupDb();
        String cnic;
        
        final String getCnicQuery = "SELECT cnic FROM Member WHERE member_id = \"" + member_id + "\"";
        
        ResultSet rs = st.executeQuery(getCnicQuery);
        cnic = rs.getString("cnic");

        tearDownDb();
        return cnic;
    }

    public static Person removeMemberDetails(String member_id) throws SQLException{
        setupDb();
        String cnic = getMemberCnic(member_id);
        Person p = null;

        final String getDetailsQuery = "SELECT firstName, lastName, contact, dob, address, email \n" +
            "FROM Person where cnic = \"" + cnic + "\"";
        
        ResultSet rs = st.executeQuery(getDetailsQuery);

        if(rs.next()){
            p = new Person(rs.getString("firstName"), rs.getString("lastName"), 
            gender.m, rs.getDate("dob"), cnic, rs.getString("contact"), "", rs.getString("email"),
            rs.getString("address"));
        }

        tearDownDb();
        return p;
    }

    public static void removeMember(String member_id) throws SQLException{
        setupDb();
        String cnic = getMemberCnic(member_id);
        deletePerson(cnic);
        tearDownDb();
    }

    public static void deletePerson(String cnic) throws SQLException{
        setupDb();
        final String query = "DELETE FROM Person WHERE cnic = \"" + cnic + "\"" ;
        st.executeUpdate(query);
        tearDownDb();
    }

    public static boolean isTeam(String team_id) throws SQLException{
        setupDb();
        boolean valid;
        final String query = "SELECT team_id FROM Team WHERE team_id = \"" + team_id + "\"";
        
        ResultSet rs = st.executeQuery(query);
        valid = rs.next();  //checks if the team exists
        
        tearDownDb();
        return valid;
    }

    public static void registerEmployee(Employee emp) throws SQLException {
        setupDb();

        final String queryPer = "INSERT INTO Person (firstName, lastName, gender, dob, cnic, address," +
                                " contact, emerContact, email, bloodGroup) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        final String queryEmp = "INSERT INTO Employee VALUES (\"" + emp.getCnic() + "\", " + 
                                 emp.getDept_id() + ", " + emp.getSalary()+ ", " + emp.getRole() + ");";

        java.sql.Date date = new java.sql.Date(emp.getDob().getTime());
        
        try (PreparedStatement statement = conn.prepareStatement(queryPer)) {
            statement.setString(1, emp.getFname());
            statement.setString(2, emp.getLname());
            statement.setString(3, emp.getGen().name());
            statement.setDate(4,date);
            statement.setString(5, emp.getCnic());
            statement.setString(6, emp.getAddress());
            statement.setString(7, emp.getContactNo());
            statement.setString(8, emp.getEmerContact());
            statement.setString(9, emp.getEmail());
            statement.setString(10, emp.getBloodGrp());
            statement.executeUpdate();
        }

        st.executeUpdate(queryEmp);       
        tearDownDb();
    }

    public static ArrayList<String> getDeptList() throws SQLException{
        setupDb();
        ArrayList<String> deptName = new ArrayList<String>();
        
        final String query = "SELECT deptName FROM Department;";
        
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            deptName.add(rs.getString("sportName"));
        }
        
        tearDownDb();
        return deptName;
    }

    public static int getDeptID(String dept) throws SQLException{
        setupDb();
        int dept_id = 0;

        final String getDeptQuery = "SELECT dept_id FROM Department WHERE deptName = \"" + dept + "\"";
        ResultSet rs = st.executeQuery(getDeptQuery);
        
        if(rs.next()){
            dept_id = rs.getInt("dept_id");
        }

        tearDownDb();
        return dept_id;
    }


}
