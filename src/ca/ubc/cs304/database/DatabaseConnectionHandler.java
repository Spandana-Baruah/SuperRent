package ca.ubc.cs304.database;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.ReservationModel;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    //	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // createAndPopulateDatabase("resources/drop_tables_SuperRent.sql");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public void deleteReservation(int branchId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
            ps.setInt(1, branchId);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertReservation(ReservationModel model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Reservation VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, model.getLocation());
            ps.setString(2, model.getVtname());
            ps.setString(3, model.getName());
            ps.setLong(4, model.getCellphone());
            ps.setDate(5, model.getFromDate());
            ps.setTimestamp(6, model.getFromTime());
            ps.setDate(7, model.getToDate());
            ps.setTimestamp(8, model.getToTime());
            if (model.getCellphone() == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setLong(4, model.getCellphone());
            }

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public BranchModel[] getBranchInfo() {
        ArrayList<BranchModel> result = new ArrayList<BranchModel>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservation");

    		// get info on ResultSet
    		ResultSetMetaData rsmd = rs.getMetaData();

    		System.out.println(" ");

    		// display column names;
    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
    			// get column name and print it
    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
    		}

            while (rs.next()) {
                BranchModel model = new BranchModel(rs.getString("branch_addr"),
                        rs.getString("branch_city"),
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getInt("branch_phone"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new BranchModel[result.size()]);
    }

    public void updateReservation(int id, String name) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
            ps.setString(1, name);
            ps.setInt(2, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
            runSQL(connection, "resources/create_tables_SuperRent.sql");
            runSQL(connection, "resources/populate_tables_SuperRent.sql");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }


    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------------------------------------
    // NOTE: referred to this page for learning how to execute sql files:
    // https://coderanch.com/t/306966/databases/Execute-sql-file-java

    public static void runSQL(Connection connection, String filePath) {

        try (FileReader fr = new FileReader(new File(filePath));
             BufferedReader br = new BufferedReader(fr)) {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            String[] SQLstatements = sb.toString().split(";");
            Statement stmt = connection.createStatement();

            for (String SQLstatement : SQLstatements) {

                if (!SQLstatement.trim().equals("")) {
                    stmt.execute(SQLstatement.trim());
                }
            }
            System.out.println("SUCCESS in executing SQL file!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
