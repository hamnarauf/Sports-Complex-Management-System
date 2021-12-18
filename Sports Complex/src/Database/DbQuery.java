package Database;

import Classes.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sana Zehra
 */

public class DbQuery {

    // "jdbc:mysql://hostname:portNumber/databaseName"
    private final static String FILE = "jdbc:mysql://localhost:3306/sportscomplex";
    private static Connection conn = null;
    private static Statement st;

    // GENERAL-PURPOSE METHODS
    private static void setupDb() throws ClassNotFoundException {
        // for making connection to the database
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportscomplex?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", "root", "root");
            DbQuery.conn = connection;
            Statement statement = connection.createStatement();
            DbQuery.st = statement;
        } catch (SQLException| ClassNotFoundException exc) {
            System.out.println(exc.toString());
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void tearDownDb() {
        // tears down connection to database
        try {
            DbQuery.st.close();
            DbQuery.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // LOGIN AND PASSWORD RECOVERY
    public static User checkLoginDetails(String username, String password) throws SQLException,
            ClassNotFoundException {

        setupDb();
        User user = null;

        // login query
        final String query = "SELECT username, password, Employee.dept_id FROM Users \n" +
                "INNER JOIN Employee On Users.emp_id = Employee.emp_id \n" +
                "WHERE username =\"" + username + "\" \n" +
                "AND password =\"" + password + "\";";

        ResultSet rs = st.executeQuery(query);
        
        if (rs.next()) { // valid credentials
            user = new User(rs.getString("username"), rs.getString("password"), 
            rs.getString("dept_id"));
        }

        tearDownDb();
        return user;
    }

    public static boolean isCorrectUsername(String uname) throws SQLException, ClassNotFoundException {
        setupDb();
        boolean valid;

        final String query = "SELECT username FROM Users WHERE username = \"" + uname + "\"";

        ResultSet rs = st.executeQuery(query);
        valid = rs.next(); // if any record matches the provided username

        tearDownDb();
        return valid;
    }

    public static String getSecurityQs(String uname) throws SQLException, ClassNotFoundException {
        setupDb();
        String ques_id = "";
        String secQs = "";

        // retreive stored security question of the provided user
        final String query = "SELECT ques_id FROM Users WHERE username = \"" + uname + "\"";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            ques_id = rs.getString("ques_id");
        }

        final String getQsQuery = "SELECT ques FROM security_qs WHERE ques_id = \"" + ques_id + "\"";

        rs = st.executeQuery(getQsQuery);

        if (rs.next()) {
            secQs = rs.getString("securityQues");
        }

        tearDownDb();
        return secQs;
    }

    public static boolean checkSecAns(String uname, String ans) throws SQLException, ClassNotFoundException {
        setupDb();
        boolean valid = false;

        // checks if the user has provided correct ans to the security question
        final String query = "SELECT securityAns FROM Users WHERE username = \"" + uname + "\"";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            if (ans.equals(rs.getString("securityAns"))) {
                valid = true;
            }
        }

        tearDownDb();
        return valid;
    }

    public static void passwordNew(String uname, String passNew) throws SQLException, ClassNotFoundException {
        setupDb();

        // updates the password of the given user
        final String query = "UPDATE Users SET password = \"" + passNew + "\" WHERE username = \"" + uname + "\"";
        st.executeUpdate(query);

        tearDownDb();
    }

    // REGISTRATION INTERFACE
    // utilities

    public static int getSportID(String sport) throws SQLException, ClassNotFoundException {
        setupDb();
        int sport_id = 0;

        final String getSportQuery = "SELECT sport_id FROM Sport WHERE sport = \"" + sport + "\"";
        ResultSet rs = st.executeQuery(getSportQuery);

        if (rs.next()) {
            sport_id = rs.getInt("sport_id");
        }

        tearDownDb();
        return sport_id;
    }

    public static ResultSet getCoachOfSport(int sport_id) throws SQLException, ClassNotFoundException {
        setupDb();

        final String getCoachQuery = "SELECT coach_id FROM Coach WHERE sport_id = \"" + sport_id + "\"";
        ResultSet rs = st.executeQuery(getCoachQuery);

        tearDownDb();
        return rs;
    }

    public static String getEmpCnic(String emp_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic;

        final String getCnicQuery = "SELECT cnic FROM Employee WHERE emp_id = \"" + emp_id + "\"";

        ResultSet rs = st.executeQuery(getCnicQuery);
        cnic = rs.getString("cnic");

        tearDownDb();
        return cnic;
    }

    public static String getEmpId(String cnic) throws SQLException, ClassNotFoundException {
        setupDb();
        String emp_id;

        final String query = "SELECT emp_id FROM Employee WHERE cnic = \"" + cnic + "\"";

        ResultSet rs = st.executeQuery(query);
        emp_id = rs.getString("emp_id");

        tearDownDb();
        return emp_id;
    }

    public static int getDeptID(String dept) throws SQLException, ClassNotFoundException {
        setupDb();
        int dept_id = 0;

        final String getDeptQuery = "SELECT dept_id FROM Department WHERE deptName = \"" + dept + "\"";
        ResultSet rs = st.executeQuery(getDeptQuery);

        if (rs.next()) {
            dept_id = rs.getInt("dept_id");
        }

        tearDownDb();
        return dept_id;
    }

    public static boolean isEmployee(String emp_id) throws SQLException, ClassNotFoundException {
        setupDb();
        boolean valid = false;

        final String query = "SELECT emp_id FROM Employee WHERE emp_id = \"" + emp_id + "\"";

        ResultSet rs = st.executeQuery(query);
        valid = rs.next();

        tearDownDb();
        return valid;
    }

    public static boolean isMember(String cnic) throws SQLException, ClassNotFoundException {
        setupDb();
        boolean valid;

        final String query = "SELECT member_id FROM Member WHERE cnic = \"" + cnic + "\"";

        ResultSet rs = st.executeQuery(query);
        valid = rs.next(); // if any record matches the provided username

        tearDownDb();
        return valid;
    }

    public static boolean isTeam(String team_id) throws SQLException, ClassNotFoundException {
        setupDb();
        boolean valid;
        final String query = "SELECT team_id FROM Team WHERE team_id = \"" + team_id + "\"";

        ResultSet rs = st.executeQuery(query);
        valid = rs.next(); // checks if the team exists

        tearDownDb();
        return valid;
    }

    public static String getMemberCnic(String member_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic;

        final String getCnicQuery = "SELECT cnic FROM Member WHERE member_id = \"" + member_id + "\"";

        ResultSet rs = st.executeQuery(getCnicQuery);
        cnic = rs.getString("cnic");

        tearDownDb();
        return cnic;
    }

    public static String getCoachOfTeam(Team team) throws SQLException, ClassNotFoundException {
        setupDb();
        String coach_id = "";

        final String query = "SELECT coach_id FROM ((Coach "
                + "INNER JOIN Class On Class.coach_id = Coach.coach_id)"
                + "INNER JOIN Team_Schedule On TeamSchedule.class_id = Class.class_id)"
                + "WHERE team_id = \"" + team.getTeam_id() + "\"";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            coach_id = rs.getString("coach_id");
        }

        tearDownDb();
        return coach_id;
    }

    public static void deletePerson(String cnic) throws SQLException, ClassNotFoundException {
        setupDb();
        final String query = "DELETE FROM Person WHERE cnic = \"" + cnic + "\"";
        st.executeUpdate(query);
        tearDownDb();
    }

    public static int getCountOfTraineeClasses(String trainee_id) throws SQLException, ClassNotFoundException {
        setupDb();
        int count = 0;

        final String query = "SELECT COUNT(*) FROM Trainee WHERE member_id = \"" + trainee_id + "\"";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }
        tearDownDb();
        return count;
    }

    public static int getCountOfTraineesOfCoach(String coach_id) throws SQLException, ClassNotFoundException {
        setupDb();
        int count = 0;

        final String query = "SELECT COUNT(*) FROM Trainee INNER JOIN CLASS "
                + "ON Trainee.class_id = Class.class_id WHERE coach_id = \"" + coach_id + "\"";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }
        tearDownDb();
        return count;
    }

    public static int getCountOfTeamsOfCoach(String coach_id) throws SQLException, ClassNotFoundException {
        setupDb();
        int count = 0;

        final String query = "SELECT COUNT(*) FROM Team_Schedule INNER JOIN CLASS "
                + "ON Team_Schedule.class_id = Class.class_id WHERE coach_id = \"" + coach_id + "\"";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }
        tearDownDb();
        return count;
    }

    // display list methods
    public static ArrayList<String> getSportsList() throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<String> sportName = new ArrayList<String>();

        final String query = "SELECT sportName FROM Sport;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            sportName.add(rs.getString("sportName"));
        }

        tearDownDb();
        return sportName;
    }

    public static ArrayList<Time> getTime(String sport, String day) throws SQLException, ClassNotFoundException {
        setupDb();
        String coach_id = "";
        ResultSet rs;
        ResultSet rs2;
        ArrayList<Time> startTime = new ArrayList<Time>();

        int sport_id = getSportID(sport);
        rs = getCoachOfSport(sport_id);

        while (rs.next()) {
            coach_id = rs.getString("coach_id");


            if ((getCountOfTeamsOfCoach(coach_id) <= 2) && (getCountOfTraineesOfCoach(coach_id) <= 5)) {
                final String getClassTimeQuery = "SELECT startTime FROM Class WHERE coach_id = \"" + coach_id + "\"" +
                        "AND day = \"" + day + "\"";


                rs2 = st.executeQuery(getClassTimeQuery);
                startTime.add(rs2.getTime("startTime"));
            }
        }
        tearDownDb();
        return startTime;
    }

    public static ArrayList<Member> displayMembers() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Member> memberList = new ArrayList<Member>();

        final String query = "SELECT Person.cnic, Person.firstName, Person.lastName, Person.dob, "
                + "Person.gender, Person.contact, Person.email, Member.member_id "
                + "FROM Person INNER JOIN Member ON Member.cnic = Person.cnic;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Member m = new Member(rs.getString("Person.firstName"), rs.getString("Person.lastName"),
                    gender.valueOf(rs.getString("Person.gender")), rs.getDate("Person.dob"),
                    rs.getString("Person.cnic"), rs.getString("Person.contact"),
                    rs.getString("Person.email"), rs.getString("Member.member_id"));

            memberList.add(m);
        }

        tearDownDb();
        return memberList;
    }

    public static ArrayList<String> getDeptList() throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<String> deptName = new ArrayList<String>();

        final String query = "SELECT deptName FROM Department;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            deptName.add(rs.getString("sportName"));
        }

        tearDownDb();
        return deptName;
    }

    public static ArrayList<String> getQsList() throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<String> qs = new ArrayList<String>();

        final String query = "SELECT ques FROM security_qs;";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            qs.add(rs.getString("ques"));
        }

        tearDownDb();
        return qs;
    }

    public static ArrayList<Coach> displayCoachList() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Coach> coachList = new ArrayList<Coach>();

        final String query = "SELECT Coach.coach_id, Person.firstName, Person.lastName, Person.cnic, "
                + "Person.gender, Person.dob, Person.contact, Person.email, Sport.sportName "
                + "FROM (((Coach INNER JOIN Employee ON Coach.coach_id = Employee.emp_id) "
                + "INNER JOIN Person On Person.cnic = Employee.cnic) "
                + "INNER JOIN Sport On Sport.sport_id = Coach.coach_id)";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Coach c = new Coach(rs.getString("Person.firstName"), rs.getString("Person.lastName"),
                    gender.valueOf(rs.getString("Person.gender")), rs.getDate("Person.dob"),
                    rs.getString("Person.cnic"), rs.getString("Person.contact"), "",
                    rs.getString("Person.email"), "", rs.getString("Coach.coach_id"),
                    rs.getString("Sport.sportName"));

            coachList.add(c);
        }
        tearDownDb();
        return coachList;
    }

    public static ArrayList<Employee> displayEmployeeList() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Employee> empList = new ArrayList<Employee>();

        final String query = "SELECT Employee.emp_id, Person.firstName, Person.lastName, Person.cnic, "
                + "Person.gender, Person.dob, Person.contact, Person.email, Departement.deptName "
                + "FROM (((Employee INNER JOIN Person ON Employee.cnic = Person.cnic) "
                + "INNER JOIN Department On Department.dept_id = Employee.dept_id);";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Employee emp = new Employee(rs.getString("Person.firstName"), rs.getString("Person.lastName"),
                    gender.valueOf(rs.getString("Person.gender")), rs.getDate("Person.dob"),
                    rs.getString("Person.cnic"), rs.getString("Person.contact"), "",
                    rs.getString("Person.email"), "", rs.getString("Coach.coach_id"),
                    rs.getString("Sport.sportName"));

            empList.add(emp);
        }
        tearDownDb();
        return empList;
    }

    public static ArrayList<Team> displayTeamList() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Team> teamList = new ArrayList<Team>();


        final String query = "SELECT Team.team_id, Team.package, Sport.teamMember, Person.firstName FROM (Team " +
                "LEFT JOIN ((((Team_Schedule ts INNER JOIN Class c ON ts.class_id = c.class-id) " +
                "INNER JOIN Coach ON Coach.coach_id = c.coach_id) " +
                "INNER JOIN Employee e ON e.emp_id = Coach.coach_id) " +
                "INNER JOIN Person p ON p.cnic = e.cnic) " +
                "ON Team.team_id = Team_Schedule.team_id) LEFT JOIN Sport ON Sport.sport_id = Team.sport_id";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Team team = new Team(rs.getString("Team.team_id"), rs.getInt("Sport.teamMember"),
                    rs.getString("Team.package"), rs.getString("Person.firstName"));

            teamList.add(team);
        }

        tearDownDb();
        return teamList;
    }

    // registration methods

    public static void registerMember(Member mem) throws SQLException, ClassNotFoundException {
        setupDb();

        final String queryPer = "INSERT INTO Person (firstName, lastName, gender, dob, cnic, address,"
                + " contact, emerContact, email, bloodGroup) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        final String queryMem = "INSERT INTO Member VALUES (\"" + mem.getCnic() + "\");";

        java.sql.Date date = new java.sql.Date(mem.getDob().getTime());

        try (PreparedStatement statement = conn.prepareStatement(queryPer)) {
            statement.setString(1, mem.getFname());
            statement.setString(2, mem.getLname());
            statement.setString(3, mem.getGen().name());
            statement.setDate(4, date);
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

    public static void registerTrainee(Trainee t1) throws SQLException, ClassNotFoundException {
        setupDb();

        int sport_id = getSportID(t1.getSport());
        int class_id = 0;

        final String getClassQuery = "SELECT Class.class_id FROM Class "
                + "INNER JOIN Coach ON Coach.coach_id = Class.coach_id "
                + "WHERE sport_id = \"" + sport_id + "\" AND startTime = \"" + t1.getStartTime() + "\""
                + "AND day = \"" + t1.getDay() + "\"";

        ResultSet rs = st.executeQuery(getClassQuery);

        if (rs.next()) {
            class_id = rs.getInt("Class.class_id");
        }

        final String regTraineeQuery = "INSERT INTO Trainee VALUES (" + t1.getMember_id() + ", " + class_id + ")";

        st.executeUpdate(regTraineeQuery);
        tearDownDb();
    }

    public static void registerGuest(Guest g) throws SQLException, ClassNotFoundException {
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

    public static void registerEmployee(Employee emp) throws SQLException, ClassNotFoundException {
        setupDb();

        final String queryPer = "INSERT INTO Person (firstName, lastName, gender, dob, cnic, address,"
                + " contact, emerContact, email, bloodGroup) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        final String queryEmp = "INSERT INTO Employee (cnic, dept_id) VALUES (\"" + emp.getCnic() + "\", "
                + emp.getDept_id() + ");";

        java.sql.Date date = new java.sql.Date(emp.getDob().getTime());

        try (PreparedStatement statement = conn.prepareStatement(queryPer)) {
            statement.setString(1, emp.getFname());
            statement.setString(2, emp.getLname());
            statement.setString(3, emp.getGen().name());
            statement.setDate(4, date);
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

    public static void registerUser(User user) throws SQLException, ClassNotFoundException {
        setupDb();
        registerEmployee(user);
        String emp_id = getEmpId(user.getCnic());
        user.setEmp_id(emp_id);

        final String query = "INSERT INTO Users (emp_id, securityQues, securityAns) \n" +
                "VALUES (\"" + user.getEmp_id() + "\", \"" + user.getSecQs() + "\", \"" +
                user.getSecAns() + "\")";

        st.executeUpdate(query);
        tearDownDb();
    }

    public static void registerTeam(Team team) throws SQLException, ClassNotFoundException {
        setupDb();
        int sport_id = getSportID(team.getSport());

        final String query = "INSERT INTO Team (sport_id, package) VALUES (?, ?);";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, sport_id);
            statement.setString(2, team.getPack());
            statement.executeUpdate();
        }
        tearDownDb();
    }

    public static void registerTeamforTraining(Team t1) throws SQLException, ClassNotFoundException {
        setupDb();

        int sport_id = getSportID(t1.getSport());
        int class_id = 0;

        final String getClassQuery = "SELECT Class.class_id FROM Class "
                + "INNER JOIN Coach ON Coach.coach_id = Class.coach_id "
                + "WHERE sport_id = \"" + sport_id + "\" AND startTime = \"" + t1.getStartTime() + "\"";

        ResultSet rs = st.executeQuery(getClassQuery);

        if (rs.next()) {
            class_id = rs.getInt("Class.class_id");
        }

        final String regTeamQuery = "INSERT INTO Team_Schedule VALUES (\"" + t1.getTeam_id()
                + "\", \"" + class_id + "\")";

        st.executeUpdate(regTeamQuery);
        tearDownDb();
    }

    // deletion methods

    public static Person removeMemberDetails(String member_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic = getMemberCnic(member_id);
        Person p = null;

        final String getDetailsQuery = "SELECT firstName, lastName, contact, dob, address, email \n"
                + "FROM Person where cnic = \"" + cnic + "\"";

        ResultSet rs = st.executeQuery(getDetailsQuery);

        if (rs.next()) {
            p = new Person(rs.getString("firstName"), rs.getString("lastName"),
                    gender.m, rs.getDate("dob"), cnic, rs.getString("contact"), "", rs.getString("email"),
                    rs.getString("address"));
        }

        tearDownDb();
        return p;
    }

    public static Person removeEmployeeDetails(String emp_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic = getEmpCnic(emp_id);
        Person p = null;

        final String getDetailsQuery = "SELECT firstName, lastName, contact, dob, address, email \n" +
                "FROM Person where cnic = \"" + cnic + "\"";

        ResultSet rs = st.executeQuery(getDetailsQuery);

        if (rs.next()) {
            p = new Person(rs.getString("firstName"), rs.getString("lastName"),
                    gender.m, rs.getDate("dob"), cnic, rs.getString("contact"), "", rs.getString("email"),
                    rs.getString("address"));
        }

        tearDownDb();
        return p;
    }

    public static Team removeTeamDetails(String team_id) throws SQLException, ClassNotFoundException {
        setupDb();
        Team team = null;

        final String query = "SELECT Team.team_id, Team.package, Person.firstName FROM (Team " +
                "LEFT JOIN ((((Team_Schedule ts " +
                "INNER JOIN Class c ON ts.class_id = c.class-id) " +
                "INNER JOIN Coach ON Coach.coach_id = c.coach_id) " +
                "INNER JOIN Employee e ON e.emp_id = Coach.coach_id) " +
                "INNER JOIN Person p ON p.cnic = e.cnic) " +
                "ON Team.team_id = Team_Schedule.team_id " +
                "WHERE Team.team_id = \"" + team_id + "\"";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            team = new Team(rs.getString("Team.team_id"), rs.getInt("Sport.teamMember"),
                    rs.getString("Team.package"), rs.getString("Person.firstName"));
        }
        tearDownDb();
        return team;
    }

    public static void removeMember(String member_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic = getMemberCnic(member_id);
        deletePerson(cnic);
        tearDownDb();
    }

    public static void removeEmp(String emp_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic = getMemberCnic(emp_id);
        deletePerson(cnic);
        tearDownDb();
    }

    public static void removeTeam(String team_id) throws SQLException, ClassNotFoundException {
        setupDb();
        final String query = "DELETE FROM Team WHERE team_id = \"" + team_id + "\"";
        st.executeUpdate(query);
        tearDownDb();
    }

    // other methods

    public static void editWorkingHrs(CoachSchedule[] cs) throws SQLException, ClassNotFoundException {
        setupDb();

        final String query = "INSERT INTO Class (coach_id, day, startTime, endTime) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < 6; i++) {
                statement.setString(1, cs[i].getCoach_id());
                statement.setString(2, cs[i].getDay());
                statement.setTime(3, cs[i].getStartTime());
                statement.setTime(4, cs[i].getEndTime());
                statement.executeUpdate();
            }
        }
        tearDownDb();
    }

    // COACH INTERFACE
    public static ArrayList<CoachSchedule> getCoachSchedule(String coach_id) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<CoachSchedule> schedule = new ArrayList<CoachSchedule>();
        CoachSchedule cs;

        final String query = "SELECT c.day, c.startTime, c.endTime, s.sportName, COUNT(t.member_id) FROM Class c\n"
                + "INNER JOIN Coach ON c.coach_id = Coach.coach_id \n"
                + "INNER JOIN Sport s ON Coach.coach_id = s.sport_id \n"
                + "INNER JOIN Trainee t ON c.class_id = t.class_id \n"
                + "WHERE Coach.coach_id = \"" + coach_id + "\" \n"
                + "GROUP BY c.day;";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            cs = new CoachSchedule(coach_id, rs.getString("c.day"), rs.getTime("c.startTime"),
                    rs.getTime("c.endTime"), rs.getString("s.sportName"), rs.getInt("COUNT(t.member_id)"));
            schedule.add(cs);
        }

        tearDownDb();
        return schedule;
    }

    public static ArrayList<Trainee> viewTrainees(String coach_id) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<Trainee> traineeList = new ArrayList<Trainee>();
        Trainee t;

        final String query = "SELECT t.member_id, p.firstName, p.lastName, Sport.sportName \n"
                + "FROM Trainee t, Member m, Person p, Class c, Coach, Sport s \n"
                + "WHERE t.member_id = m.member_id    AND \n"
                + "m.cnic = p.cnic    AND \n"
                + "t.class_id = c.class_id    AND \n"
                + "c.coach_id = Coach.coach_id    AND \n"
                + "Coach.sport_id = s.sport_id    AND \n"
                + "Coach.coach_id = \"" + coach_id + "\"";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            t = new Trainee(rs.getString("t.member_id"), rs.getString("Sport.sportName"),
                    rs.getString("p.firstName"), rs.getString("p.lastName"));
            traineeList.add(t);
        }

        tearDownDb();
        return traineeList;
    }

    // finance
    public static ArrayList<Transaction> viewTransSummary() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Transaction> transList = new ArrayList<Transaction>();

        final String query = "select * from transactions;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"), rs.getString("transactions.type"),
                    rs.getString("transactions.amount"));

            transList.add(trans);
        }

        tearDownDb();
        return transList;
    }

    public static double getSummaryTransTotal() throws SQLException, ClassNotFoundException {
        setupDb();
        double total = 0;

        final String query = "select sum(amount) from transactions;";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            total = rs.getDouble("COUNT(*)");
        }
        tearDownDb();
        return total;
    }

    public static ArrayList<Transaction> viewTransBills() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Transaction> transList = new ArrayList<Transaction>();
        final String query = "select * from transactions where type like \"%bill%\"";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"), rs.getString("transactions.type"),
                    rs.getString("transactions.amount"));
            transList.add(trans);
        }
        tearDownDb();
        return transList;
    }

    public static ArrayList<Transaction> viewTransFunds() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Transaction> transList = new ArrayList<Transaction>();
        final String query = "select * from transactions where type like \"%fund%\"";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"), rs.getString("transactions.type"),
                    rs.getString("transactions.amount"));
            transList.add(trans);
        }
        tearDownDb();
        return transList;
    }

    public static ArrayList<Transaction> viewTransExtras() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Transaction> transList = new ArrayList<Transaction>();
        final String query = "select repair_id, purpose, amount from repairs where status = \"Allocated\"";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"), rs.getString("transactions.type"),
                    rs.getString("transactions.amount"));
            transList.add(trans);
        }
        tearDownDb();
        return transList;
    }

    public static ArrayList<Employee> viewTransEmp() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Employee> transList = new ArrayList<Employee>();
        final String query = "select emp_id, firstname, lastname, deptName, salary \n"
                + "from employee join person using (cnic) join department using (dept_id);";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setEmp_id(rs.getString("Employee.emp_id"));
            emp.setFname(rs.getString("firstName"));
            emp.setLname(rs.getString("lastName"));
            emp.setDeptName(rs.getString("deptName"));
//            emp.setSalary(rs.getString("salary"));
            transList.add(emp);
        }
        tearDownDb();
        return transList;
    }

    public static double getBillsTransTotal() throws SQLException, ClassNotFoundException {
        setupDb();
        double total = 0;

        final String query = "select sum(amount) from transactions where type like \"%bill%\";";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            total = rs.getDouble("COUNT(*)");
        }
        tearDownDb();
        return total;
    }

    public static double getFundsTransTotal() throws SQLException, ClassNotFoundException {
        setupDb();
        double total = 0;

        final String query = "select sum(amount) from transactions where type like \"%fund%\";";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            total = rs.getDouble("COUNT(*)");
        }
        tearDownDb();
        return total;
    }

    public static double getExtraTransTotal() throws SQLException, ClassNotFoundException {
        setupDb();
        double total = 0;

        final String query = "select sum(amount) from transactions where type like \"%extra%\";";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            total = rs.getDouble("COUNT(*)");
        }
        tearDownDb();
        return total;
    }

    public static ArrayList<Repair> getRepairs() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Repair> repairsList = new ArrayList<Repair>();
        final String query = "select purpose, sportName, amount from repairs join sport using (sport_id)\n"
                + "where status = \"Pending\"";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Repair rep = new Repair(rs.getString("purpose"), rs.getString("sportName"), rs.getString("amount"));
            repairsList.add(rep);
        }

        tearDownDb();
        return repairsList;
    }

    public static boolean hasReqRepairs() throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<Repair> repairsList = new ArrayList<Repair>();
        final String query = "select purpose, sportName, amount from repairs join sport using (sport_id)\n"
                + "where status = \"Pending\"";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            tearDownDb();
            return true;
        }
        tearDownDb();
        return false;

    }

    public static void allocateFunds(Repair r) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<Repair> repairsList = new ArrayList<Repair>();
        final String query = "update repairs\n"
                + "set status = \"Allocated\" where purpose = \""+r.getPurpose()+"\"";

        ResultSet rs = st.executeQuery(query);
        tearDownDb();
    }
    
    public static void refuseFunds(Repair r) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<Repair> repairsList = new ArrayList<Repair>();
        final String query = "update repairs\n"
                + "set status = \"Refused\" where purpose = \""+r.getPurpose()+"\"";

        ResultSet rs = st.executeQuery(query);
        tearDownDb();
    }

}
