package Database;

import Classes.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Time;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.spi.DirStateFactory.Result;

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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sportscomplex?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC",
                    "root", "root");
            DbQuery.conn = connection;
            Statement statement = connection.createStatement();
            DbQuery.st = statement;
        } catch (SQLException | ClassNotFoundException exc) {
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
        final String query = "SELECT username, password, Employee.dept_id, Employee.emp_id FROM Users \n"
                + "INNER JOIN Employee On Users.emp_id = Employee.emp_id \n"
                + "WHERE username =\"" + username + "\" \n"
                + "AND password =\"" + password + "\";";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) { // valid credentials
            user = new User(rs.getString("username"), rs.getString("password"), rs.getString("dept_id"),
                    rs.getString("emp_id"));
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
        String secQs = "";

        // retreive stored security question of the provided user
        final String query = "SELECT ques FROM security_qs WHERE ques_id = "
                + "(SELECT security_qs_id FROM Users WHERE username = \"" + uname + "\");";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            secQs = rs.getString("ques");
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

        final String getSportQuery = "SELECT sport_id FROM Sport WHERE sportName = \"" + sport + "\"";
        ResultSet rs = st.executeQuery(getSportQuery);

        if (rs.next()) {
            sport_id = rs.getInt("sport_id");
        }

        tearDownDb();
        return sport_id;
    }

    public static ArrayList<String> getCoachOfSport(int sport_id) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<String> coach_id = new ArrayList();

        final String getCoachQuery = "SELECT coach_id FROM Coach WHERE sport_id = \"" + sport_id + "\"";
        ResultSet rs = st.executeQuery(getCoachQuery);

        while (rs.next()) {
            coach_id.add(rs.getString("coach_id"));
        }

        tearDownDb();
        return coach_id;
    }

    public static String getUsername(String emp_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String uname;

        final String query = "SELECT username FROM Users WHERE emp_id = \"" + emp_id + "\"";

        ResultSet rs = st.executeQuery(query);
        uname = rs.getString("username");

        tearDownDb();
        return uname;
    }

    public static String getEmpCnic(String emp_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String cnic = "";

        final String getCnicQuery = "SELECT cnic FROM Employee WHERE emp_id = \"" + emp_id + "\"";

        ResultSet rs = st.executeQuery(getCnicQuery);
        if (rs.next()) {
            cnic = rs.getString("cnic");
        }

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

    public static String getDeptID(String dept) throws SQLException, ClassNotFoundException {
        setupDb();
        String dept_id = "";

        final String getDeptQuery = "SELECT dept_id FROM Department WHERE deptName = \"" + dept + "\"";
        ResultSet rs = st.executeQuery(getDeptQuery);

        if (rs.next()) {
            dept_id = rs.getString("dept_id");
        }

        tearDownDb();
        return dept_id;
    }

    public static String getDeptName(String dept_id) throws SQLException, ClassNotFoundException {
        setupDb();
        String deptName = "";

        final String query = "SELECT deptName FROM Department WHERE dept_id = \"" + dept_id + "\"";
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            deptName = rs.getString("deptName");
        }
        tearDownDb();
        return deptName;
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
        String cnic = "";

        final String getCnicQuery = "SELECT cnic FROM Member WHERE member_id = \"" + member_id + "\"";

        ResultSet rs = st.executeQuery(getCnicQuery);
        if (rs.next()) {
            cnic = rs.getString("cnic");
        }
        tearDownDb();
        return cnic;
    }

    public static String getCoachOfTeam(Team team) throws SQLException, ClassNotFoundException {
        setupDb();
        String coach_id = "";

        final String query = "SELECT class.coach_id FROM ((Coach "
                + "INNER JOIN Class On Class.coach_id = Coach.coach_id)"
                + "INNER JOIN Team_Schedule On Team_Schedule.class_id = Class.class_id)"
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
        String coach_id;
        ArrayList<String> coach_ids;
        ResultSet rs2;
        ArrayList<Time> startTime = new ArrayList<Time>();

        int sport_id = getSportID(sport);
        coach_ids = getCoachOfSport(sport_id);

        for (int i = 0; i < coach_ids.size(); i++) {
            coach_id = coach_ids.get(i);
            if ((getCountOfTeamsOfCoach(coach_id) <= 2) && (getCountOfTraineesOfCoach(coach_id) <= 5)) {
                final String getClassTimeQuery = "SELECT startTime FROM Class WHERE coach_id = \"" + coach_id + "\""
                        + "AND day = \"" + day + "\"";

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
        Member m;

        final String query = "SELECT Person.cnic, Person.firstName, Person.lastName, Person.dob, "
                + "Person.gender, Person.contact, Person.email, Member.member_id "
                + "FROM Person INNER JOIN Member ON Member.cnic = Person.cnic;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            m = new Member(rs.getString("firstName"), rs.getString("lastName"),
                    gender.valueOf(rs.getString("gender")), rs.getDate("dob"),
                    rs.getString("cnic"), rs.getString("contact"),
                    rs.getString("email"), rs.getString("member_id"));

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
            deptName.add(rs.getString("deptName"));
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

        final String query = "select Coach.coach_id, Person.firstName, Person.lastName, Person.cnic, \n"
                + "Person.gender, Person.dob, Person.contact, Person.email, Sport.sportName \n"
                + "from coach \n"
                + "inner join employee on coach.coach_id = employee.emp_id \n"
                + "inner join person on employee.cnic = person.cnic \n"
                + "inner join sport on coach.sport_id = sport.sport_id;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Coach c = new Coach(rs.getString("firstName"), rs.getString("lastName"),
                    gender.valueOf(rs.getString("gender")), rs.getDate("dob"),
                    rs.getString("cnic"), rs.getString("contact"), "",
                    rs.getString("email"), "", rs.getString("coach_id"),
                    rs.getString("sportName"));

            coachList.add(c);
        }
        tearDownDb();
        return coachList;
    }

    public static ArrayList<Employee> displayEmployeeList() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Employee> empList = new ArrayList<Employee>();

        final String query = "SELECT Employee.emp_id, firstName, lastName, Person.cnic, "
                + "gender, dob, contact, email, Department.deptName "
                + "FROM (((Employee INNER JOIN Person ON Employee.cnic = Person.cnic) "
                + "INNER JOIN Department On Department.dept_id = Employee.dept_id));";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Employee emp = new Employee(rs.getString("firstName"), rs.getString("lastName"),
                    gender.valueOf(rs.getString("gender")), rs.getDate("dob"),
                    rs.getString("cnic"), rs.getString("contact"), "",
                    rs.getString("email"), "", rs.getString("emp_id"),
                    rs.getString("deptName"));

            empList.add(emp);
        }
        tearDownDb();
        return empList;
    }

    public static ArrayList<Team> displayTeamList() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Team> teamList = new ArrayList<Team>();

        final String query = "SELECT Team.team_id, package, teamMember, firstName \n"
                + "FROM (Team \n"
                + "LEFT JOIN (((Team_Schedule INNER JOIN Class ON team_schedule.class_id = class.class_id \n"
                + "INNER JOIN Coach ON Coach.coach_id = class.coach_id) \n"
                + "INNER JOIN Employee ON employee.emp_id = Coach.coach_id) \n"
                + "INNER JOIN Person ON person.cnic = employee.cnic) \n"
                + "ON Team.team_id = Team_Schedule.team_id) LEFT JOIN Sport ON Sport.sport_id = Team.sport_id";

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

        final String query = "INSERT INTO Users (emp_id, securityQues, securityAns) \n"
                + "VALUES (\"" + user.getEmp_id() + "\", \"" + user.getSecQs() + "\", \""
                + user.getSecAns() + "\")";

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
    public static Person removeMemberDetails(String cnic) throws SQLException, ClassNotFoundException {
        setupDb();
//        String cnic = getMemberCnic(member_id);
        Person p = null;

        final String getDetailsQuery = "SELECT firstName, lastName, contact, dob, address, email \n"
                + "FROM Person where cnic = \"" + cnic + "\"";

        ResultSet rs = st.executeQuery(getDetailsQuery);

        if (rs.next()) {
            p = new Person(rs.getString("firstName"), rs.getString("lastName"),
                    gender.M, rs.getDate("dob"), cnic, rs.getString("contact"), "", rs.getString("email"),
                    rs.getString("address"));
        }

        tearDownDb();
        return p;
    }

    public static Person removeEmployeeDetails(String cnic) throws SQLException, ClassNotFoundException {
        setupDb();
//        String cnic = getEmpCnic(emp_id);
//        System.out.println(cnic);
        Person p = null;

        final String getDetailsQuery = "SELECT firstName, lastName, contact, dob, address, email \n"
                + "FROM Person where cnic = \""  + cnic + "\"";

        ResultSet rs = st.executeQuery(getDetailsQuery);

        if (rs.next()) {
            p = new Person(rs.getString("firstName"), rs.getString("lastName"),
                    gender.M, rs.getDate("dob"), cnic, rs.getString("contact"), "", rs.getString("email"),
                    rs.getString("address"));
        }

        tearDownDb();
        return p;
    }

    public static Team removeTeamDetails(String team_id) throws SQLException, ClassNotFoundException {
        setupDb();
        Team team = null;

        final String query = "SELECT package, sportName, teamMember FROM team inner join sport on team.sport_id = sport.sport_id\n"
                + "where team_id=\""  + team_id + "\"";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            team = new Team("", rs.getString("sportName"),rs.getInt("Sport.teamMember"),
                    rs.getString("Team.package"), new Time(3,4,5));
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
    public static ArrayList<CoachSchedule> getCoachSchedule(String coach_id)
            throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<CoachSchedule> schedule = new ArrayList<CoachSchedule>();
        CoachSchedule cs;

        final String query = "SELECT day, startTime, endTime, count(trainee.member_id) \n"
                + "FROM Class INNER JOIN coach ON class.coach_id = coach.coach_id \n"
                + "INNER JOIN trainee ON class.class_id = trainee.class_id \n"
                + "WHERE class.coach_id = \"" + coach_id + "\"\n"
                + "GROUP BY class.day;";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            cs = new CoachSchedule(coach_id, rs.getString("day"), rs.getTime("startTime"),
                    rs.getTime("endTime"), rs.getInt("count(trainee.member_id)"));
            schedule.add(cs);
        }

        tearDownDb();
        return schedule;
    }

    public static ArrayList<Trainee> viewTrainees(String coach_id) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<Trainee> traineeList = new ArrayList<Trainee>();
        Trainee t;

        final String query = "SELECT trainee.member_id, person.firstName, person.lastName \n"
                + "FROM Trainee, Member, Person, Class, Coach \n"
                + "WHERE trainee.member_id = member.member_id    AND \n"
                + "member.cnic = person.cnic    AND \n"
                + "trainee.class_id = class.class_id    AND \n"
                + "class.coach_id = Coach.coach_id    AND \n"
                + "Coach.coach_id = \"" + coach_id + "\";";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            t = new Trainee(rs.getString("member_id"), rs.getString("firstName"), rs.getString("lastName"));
            traineeList.add(t);
        }

        tearDownDb();
        return traineeList;
    }

    // FINANCE INTERFACE
    public static ArrayList<Transaction> viewTransSummary() throws SQLException, ClassNotFoundException {
        setupDb();

        ArrayList<Transaction> transList = new ArrayList<Transaction>();

        final String query = "select * from transactions;";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"),
                    rs.getString("transactions.type"),
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
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"),
                    rs.getString("transactions.type"),
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
            Transaction trans = new Transaction(rs.getString("transactions.transaction_id"),
                    rs.getString("transactions.type"),
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
            Transaction trans = new Transaction(rs.getString("repair_id"),
                    rs.getString("purpose"),
                    rs.getString("amount"));
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
            // emp.setSalary(rs.getString("salary"));
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
        final String query = "select purpose, sportName, amount, status from repairs join sport using (sport_id)\n"
                + "where status = \"Pending\"";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Repair rep = new Repair(rs.getString("purpose"), rs.getString("sportName"), rs.getString("amount"), "");
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
                + "set status = \"Allocated\" where purpose = \"" + r.getPurpose() + "\"";

        ResultSet rs = st.executeQuery(query);
        tearDownDb();
    }

    public static void refuseFunds(Repair r) throws SQLException, ClassNotFoundException {
        setupDb();
        ArrayList<Repair> repairsList = new ArrayList<Repair>();
        final String query = "update repairs\n"
                + "set status = \"Refused\" where purpose = \"" + r.getPurpose() + "\"";

        ResultSet rs = st.executeQuery(query);
        tearDownDb();
    }

    // MANAGER
    public static ArrayList<Schedule> displaySchedule() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Schedule> sch = new ArrayList<>();
        Schedule schedule;

        final String query = "SELECT * FROM Schedule";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            schedule = new Schedule(rs.getString("event_id"), rs.getString("eventName"),
                    rs.getDate("date"), rs.getTime("time"), rs.getString("venue"));

            sch.add(schedule);
        }

        tearDownDb();
        return sch;
    }

    public static ArrayList<Report> displayComplaints() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Report> compList = new ArrayList<>();
        Report r;

        final String query = "SELECT details FROM report WHERE type = \"complaint\" AND status = \"unaddressed\"";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            r = new Report(rs.getString("details"));
            compList.add(r);
        }

        tearDownDb();
        return compList;
    }

    public static ArrayList<Report> displaySuggestions() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Report> suggList = new ArrayList<>();
        Report r;

        final String query = "SELECT details, status FROM report WHERE type = \"suggestion\" AND status = \"unaddressed\"";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            r = new Report(rs.getString("details"));
            suggList.add(r);
        }

        tearDownDb();
        return suggList;
    }

    public static String getReportID(String report) throws ClassNotFoundException, SQLException {
        setupDb();
        String report_id = "";

        final String query = "SELECT report_id FROM Report WHERE details = \"" + report + "\";";

        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            report_id = rs.getString("report_id");
        }

        tearDownDb();
        return report_id;
    }

    public static void deleteReport(String report_id) throws ClassNotFoundException, SQLException {
        setupDb();
        final String query = "DELETE FROM Report WHERE report_id = \"" + report_id + "\";";
        st.executeUpdate(query);
        tearDownDb();
    }

    public static void addressReport(String report_id) throws ClassNotFoundException, SQLException {
        setupDb();

        final String query = "UPDATE Report \n"
                + "SET status = addressed \n"
                + "WHERE report_id = \"" + report_id + "\";";

        st.executeUpdate(query);

        tearDownDb();
    }

    public static ArrayList<Repair> displayRepairs() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Repair> repList = new ArrayList<>();
        Repair r;

        final String query = "SELECT purpose, sportName, amount, status \n"
                + "FROM repairs INNER JOIN sport ON repairs.sport_id = sport.sport_id;";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            r = new Repair(rs.getString("purpose"), rs.getString("sportName"), rs.getString("amount"),
                    rs.getString("status"));
            repList.add(r);
        }

        tearDownDb();
        return repList;
    }

    public static void issueNotice(Notice notice) throws ClassNotFoundException, SQLException {
        setupDb();

        final String query = "INSERT INTO Notice (title, text, date) \n"
                + "VALUES (?,?,?)";

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, notice.getTitle());
            statement.setString(2, notice.getText());
            statement.setDate(3, date);
            statement.executeUpdate();
        }

        tearDownDb();
    }

    public static ArrayList<Emergency> displayEmergency() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Emergency> emgList = new ArrayList<>();
        Emergency emg;

        final String query = "SELECT patient_id, concat(firstName, \" \", lastName) AS name, problem, itemName, status \n"
                + "FROM emergency \n"
                + "INNER JOIN member ON emergency.patient_id = member.member_id \n"
                + "INNER JOIN person ON member.cnic = person.cnic \n"
                + "INNER JOIN medical_log ON emergency.emer_id = medical_log.emer_id \n"
                + "INNER JOIN inventory ON medical_log.item_id = inventory.item_id; \n";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            emg = new Emergency(rs.getString("patient_id"), rs.getString("name"),
                    rs.getString("problem"), rs.getString("itemName"), rs.getString("status"));
            emgList.add(emg);
        }

        tearDownDb();
        return emgList;
    }

    public static ArrayList<Attendance> displayAttendance() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Attendance> attList = new ArrayList<>();
        Attendance att;

        final String query = "SELECT Attendance.emp_id, date, status FROM attendance \n"
                + "INNER JOIN employee ON attendance.emp_id = employee.emp_id \n"
                + "WHERE employee.emp_id in (SELECT supervisor_id FROM department);";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            att = new Attendance(rs.getString("emp_id"), rs.getDate("date"), rs.getString("status"));
            attList.add(att);
        }

        tearDownDb();
        return attList;
    }

    // INVENTORY INTERFACE
    public static ArrayList<InventoryItem> displayIssuedItems() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<InventoryItem> invList = new ArrayList<>();
        InventoryItem item;

        final String query = "SELECT issued_items.member_id, concat(firstName, \" \", lastName) AS name, itemName, issued_items.quantity, time \n"
                + "FROM issued_items \n"
                + "INNER JOIN member ON issued_items.member_id = member.member_id \n"
                + "INNER JOIN person ON member.cnic = person.cnic \n"
                + "INNER JOIN inventory ON issued_items.item_id = inventory.item_id;";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            item = new InventoryItem(rs.getString("member_id"), rs.getString("name"),
                    rs.getString("itemName"), rs.getInt("quantity"), rs.getTime("time"));
            invList.add(item);
        }

        tearDownDb();
        return invList;
    }

    public static InventoryItem showDeleteItemDetails(String itemName) throws ClassNotFoundException, SQLException {
        setupDb();
        InventoryItem item = null;

        final String query = "SELECT sportName, quantity FROM inventory \n"
                + "INNER JOIN sport ON inventory.sport_id = sport.sport_id \n"
                + "WHERE itemName = \"" + itemName + "\";";

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            item = new InventoryItem(rs.getString("sportName"), rs.getInt("quantity"));
        }

        tearDownDb();
        return item;
    }

    public static int getQuantity(String itemName) throws ClassNotFoundException, SQLException {
        setupDb();
        int qty = 0;

        final String query = "SELECT quantity FROM inventory WHERE itemName = \"" + itemName + "\"";
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            qty = rs.getInt("quantity");
        }
        tearDownDb();
        return qty;
    }

    public static void deleteItem(String itemName) throws ClassNotFoundException, SQLException {
        setupDb();

        final String query = "UPDATE inventory \n"
                + "SET quantity = " + (getQuantity(itemName) - 1) + "\n"
                + "WHERE itemName = \"" + itemName + "\";";

        st.executeUpdate(query);
        tearDownDb();
    }

    public static void addItem(String itemName, int qty) throws ClassNotFoundException, SQLException {
        setupDb();

        final String query = "UPDATE inventory \n"
                + "SET quantity = " + (getQuantity(itemName) + qty) + "\n"
                + "WHERE itemName = \"" + itemName + "\";";

        st.executeUpdate(query);
        tearDownDb();
    }

    public static ArrayList<InventoryItem> displayHistory() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<InventoryItem> logList = new ArrayList<>();
        InventoryItem itemLog;

        final String query = "SELECT issued_items.member_id, concat(firstName, \" \", lastName) AS name, itemName, issued_items.quantity, borrowedTime, returnedTime, damaged \n"
                + "FROM issued_items \n"
                + "INNER JOIN inventory_log ON issued_items.issue_id = inventory_log.issue_id \n"
                + "INNER JOIN member on member.member_id = issued_items.member_id \n"
                + "INNER JOIN person on member.cnic = person.cnic \n"
                + "INNER JOIN inventory on issued_items.item_id = inventory.item_id \n" 
                + "WHERE SUBSTRING(itemName, 0, 4) != \"Med_\";";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            itemLog = new InventoryItem(rs.getString("member_id"), rs.getString("name"),
                    rs.getString("itemName"), rs.getInt("quantity"), rs.getTime("borrowedTime"),
                    rs.getTime("returnedTime"), rs.getInt("damaged"));
            logList.add(itemLog);
        }

        tearDownDb();
        return logList;
    }

    public static String getItemID(String itemName) throws ClassNotFoundException, SQLException {
        setupDb();
        String item_id = "";

        final String query = "SELECT item_id FROM inventory WHERE itemName = \"" + itemName + "\";";
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            item_id = rs.getString("item_id");
        }

        tearDownDb();
        return item_id;
    }

    public static void issueItem(InventoryItem item) throws ClassNotFoundException, SQLException {
        setupDb();

        final String query = "INSERT INTO issued_items (member_id, item_id, time, quantity) \n"
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, item.getMember_id());
            statement.setString(2, getItemID(item.getItemName()));
            statement.setTime(3, item.getTime());
            statement.setInt(4, item.getQuantity());
            statement.executeUpdate();
        }
        tearDownDb();
    }

    // public static void returnItem (InventoryLog log) throws
    // ClassNotFoundException, SQLException{
    // setupDb();
    // tearDownDb();
    // }
    public static ArrayList<AvailableItem> displayAvailableItems() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<AvailableItem> itemList = new ArrayList<>();
        AvailableItem item;

        final String query = "SELECT itemName, (inventory.quantity - issued_items.quantity) AS quantity \n" +
                "FROM inventory \n" +
                "INNER JOIN issued_items ON inventory.item_id = issued_items.item_id \n" +
                "WHERE SUBSTRING(itemName, 0, 4) != \"Med_\";";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            item = new AvailableItem(rs.getString("itemName"), rs.getInt("quantity"));
            itemList.add(item);
        }

        final String queryNotIssued = "SELECT itemName, quantity \n" +
                "FROM inventory \n" +
                "WHERE item_id NOT IN (SELECT item_id FROM issued_items) AND \n" +
                "SUBSTRING(itemName, 0, 4) != \"Med_\";";

        rs = st.executeQuery(queryNotIssued);

        while (rs.next()) {
            item = new AvailableItem(rs.getString("itemName"), rs.getInt("quantity"));
            itemList.add(item);
        }

        tearDownDb();
        return itemList;
    }

    // EMERGENCY INTERFACE
    public static void registerPatient(Emergency e) throws ClassNotFoundException, SQLException {
        setupDb();

        final String query = "INSERT INTO Emergency (patient_id, problem, date, time, status) \n"
                + "VALUES (?, ?, ?, ?, ?);";

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        java.sql.Time time = new java.sql.Time(millis);

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, e.getPatient_id());
            statement.setString(2, e.getProblem());
            statement.setDate(3, date);
            statement.setTime(4, time);
            statement.setString(5, e.getStatus());
            statement.executeUpdate();
        }
        tearDownDb();
    }

    public static ArrayList<Person> getMedicalDetails() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<Person> detailsList = new ArrayList<>();
        Person p;

        final String query = "SELECT Person.cnic, firstName, lastName, gender, bloodGroup, allergy, contact, emerContact \n"
                +
                "FROM Person \n" +
                "LEFT JOIN allergies on person.cnic = allergies.cnic";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            p = new Person(rs.getString("firstName"), rs.getString("lastName"), gender.valueOf(rs.getString("gender")),
                    rs.getString("cnic"), rs.getString("contact"), rs.getString("emerContact"),
                    rs.getString("bloodGroup"), rs.getString("allergy"));

            detailsList.add(p);
        }

        tearDownDb();
        return detailsList;
    }

    public static ArrayList<InventoryItem> getMedicalEquipment() throws ClassNotFoundException, SQLException {
        setupDb();
        ArrayList<InventoryItem> equList = new ArrayList<>();
        InventoryItem item;

        final String query = "SELECT distinct(SUBSTRING(itemName, 5)), inventory.quantity, medical_log.quantity, \n" +
                "(inventory.quantity - medical_log.quantity) AS available \n" +
                "FROM inventory \n" +
                "LEFT JOIN medical_log ON inventory.item_id = medical_log.item_id \n" +
                "WHERE SUBSTRING(itemName, 1, 3) = \"Med_\";";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            item = new InventoryItem(rs.getString("itemName"), rs.getInt("inventory.quantity"),
            rs.getInt("medical_log.quantity"), rs.getInt("available"));

            equList.add(item);
        }

        tearDownDb();
        return equList;
    }

    public static void maintainMedicalLog(String item_id) throws SQLException, ClassNotFoundException {
        setupDb();
        ResultSet rs;

        final String query = "SELECT count(*) FROM emergency;";
        rs = st.executeQuery(query);

        int emer_id = rs.getInt("count(*)");

        final String queryLog = "INSERT INTO medical_log (emer_id, item_id, quantity) \n"
                + "VALUES (?, ?, ?);";

        try (PreparedStatement statement = conn.prepareStatement(queryLog)) {
            statement.setInt(1, emer_id);
            statement.setString(2, item_id);
            statement.setInt(3, 1);
            statement.executeUpdate();
        }
        tearDownDb();
    }

    //ATTENDANT
    public static void markAttendance(Attendance att) throws SQLException, ClassNotFoundException {
        setupDb();

        final String queryLog = "INSERT INTO attendance (emp_id, date, status) \n"
                + "VALUES (?, ?, ?);";

        try (PreparedStatement statement = conn.prepareStatement(queryLog)) {
            statement.setString(1, att.getEmp_id());
            statement.setDate(2, att.getDate());
            statement.setString(3, att.getAttendance());
            statement.executeUpdate();
        }
        tearDownDb();
    }

    //MAINTENANCE INTERFACE

    public static void registerRepair(Repair r) throws SQLException, ClassNotFoundException{
        setupDb();

        final String query = "INSERT INTO repairs (sport_id, purpose, amount) \n"
                + "VALUES (?, ?, ?);";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, getSportID(r.getSport()));
            statement.setString(2, r.getPurpose());
            statement.setString(3, r.getAmount());
            statement.executeUpdate();
        }
        tearDownDb();
    }

}
