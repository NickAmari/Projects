import java.sql.*;
import java.util.*;

public class Staff {
	private int id;

	Staff(String firstName, String lastName, String username, String password, boolean isDoctor, int teamID, boolean loggingIn) {
		Connection c = null;
		Statement statement = null;
		int staffID = -1;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM staff " +
							"WHERE USERNAME = \'" + username + "\' AND " +
							"PASSWORD = \'" + password + "\';";
			ResultSet rs = statement.executeQuery(sql);
			int resultCount = 0;

			while(rs.next()) {
				resultCount++;
				staffID = rs.getInt("ID");
			}

			statement.close();
		 	c.commit();
		 	c.close();

			if(resultCount == 1 && loggingIn) {
				this.id = staffID;
			} else if(resultCount == 0 && !loggingIn) {
				/* Create the object as a new entity in the relation */
				try {
					c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
					c.setAutoCommit(false);
					statement = c.createStatement();

					String isDoctorString = "";
					if(isDoctor) {
						isDoctorString = "YES";
					} else {
						isDoctorString = "NO";
					}

					sql = 	"INSERT INTO staff(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, DOCTOR, TEAM_ID)" +
									"VALUES(\'" + firstName + "\', \'" + lastName + "\', \'" + username + "\', \'" + password + "\', \'" + teamID  + "\', \'" + isDoctorString + "\');";
					statement.executeUpdate(sql);
					statement.close();
					c.commit();
					c.close();
				} catch(Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
					System.exit(0);
				}

				/* Define the primary key for the object just created.
		 		* This will be the max from primary key table, as it is automatically assigned as one larger than the
		 		* current largest primary key.
		 		*/
		 		try {
		 			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
		 			c.setAutoCommit(false);
		 			statement = c.createStatement();
		 			sql = "SELECT MAX(ID) FROM staff;";
		 			rs = statement.executeQuery(sql);
		 			this.id = rs.getInt(1);
		 			statement.close();
		 			c.commit();
		 			c.close();
		 		} catch(Exception e) {
		 			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		 			System.exit(0);
		 		}
		 	} else {
		 		throw new Exception("Error: invalid conditions for login / creation. If logging in, staff member does not exist. If creating, staff member already exists. Or there may be more than one entity with that information at the moment.");
		 	}
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	int getStaffID() {
		return this.id;
	}

	ArrayList<Patient> getPatients() {
		Connection c = null;
		Statement statement = null;

		ArrayList<Patient> patients = new ArrayList<Patient>(); // Create an arrayList for our messages

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM patients " +
							"WHERE DOCTOR_ID = " +
								"(SELECT ID " +
								"FROM staff " +
								"WHERE TEAM_ID = " + this.getTeamID() + " AND " +
								"DOCTOR = \'YES\') " +
							"ORDER BY LAST_NAME ASC;";
			ResultSet rs = statement.executeQuery(sql);

			int i = 1;

			while(rs.next()) {
				patients.add(new Patient(rs.getInt("ID")));
				i++;
			}

			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return patients;
	}

	ArrayList<Appointment> getTodaysAppointments(String date) {//pass in yyyy-MM-DD
		Connection c = null;
		Statement statement = null;

		ArrayList<Appointment> appointments = new ArrayList<Appointment>(); // Create an arrayList for our messages

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM appointments " +
							"WHERE DATE_LONG LIKE \'" + date + "%\'" + " AND " +
							"PATIENT_ID IN " +
							"(SELECT ID FROM patients WHERE DOCTOR_ID = (SELECT ID FROM staff WHERE TEAM_ID = " + this.getTeamID() + " AND DOCTOR = \'YES\')) " +
							"AND PAST_BOOL = 0 " +
							"ORDER BY DATE_LONG ASC;";
			ResultSet rs = statement.executeQuery(sql);

			int i = 1;
			while(rs.next()) {
				appointments.add(new Appointment(rs.getInt("ID")));
				i++;
			}

			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return appointments;
	}

	String getFirstName() {
		Connection c = null;
		Statement statement = null;
		String firstName = "";

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT FIRST_NAME " +
					  	 "FROM staff " +
						 "WHERE ID = " + this.getStaffID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			firstName = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return firstName;
	}

	String getLastName() {
		Connection c = null;
		Statement statement = null;
		String lastName = "";

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT LAST_NAME " +
					  	 "FROM staff " +
						 "WHERE ID = " + this.getStaffID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			lastName = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return lastName;
	}

	String getUserName() {
		Connection c = null;
		Statement statement = null;
		String userName = "";

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT USERNAME " +
					  	 "FROM staff " +
						 "WHERE ID = " + this.getStaffID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			userName = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return userName;
	}

	String getPassword() {
		Connection c = null;
		Statement statement = null;
		String passWord = "";

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PASSWORD " +
					  	 "FROM staff " +
						 "WHERE ID = " + this.getStaffID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			passWord = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return passWord;
	}

	boolean isDoctor() {
		Connection c = null;
		Statement statement = null;
		String doctor = "";
		boolean isDoctor = false;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT DOCTOR " +
					  	 "FROM staff " +
						 "WHERE ID = " + this.getStaffID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			doctor = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		if (doctor.equals("YES")) {
			isDoctor = true;
		}
		if (doctor.equals("NO")) {
			isDoctor = false;
		}

		return isDoctor;
	}

	int getTeamID() {
		Connection c = null;
		Statement statement = null;
		int teamID = -1;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT TEAM_ID " +
					  	 "FROM staff " +
						 "WHERE ID = " + this.getStaffID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			teamID = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return teamID;
	}

	void setFirstName(String firstName) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE staff " +
						 "SET FIRST_NAME = \'" + firstName + "\' " +
						 "WHERE ID = " + this.getStaffID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	void setLastName(String lastName) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE staff " +
						 "SET LAST_NAME = \'" + lastName + "\' " +
						 "WHERE ID = " + this.getStaffID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	void setUserName(String userName) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE staff " +
						 "SET USERNAME = \'" + userName + "\' " +
						 "WHERE ID = " + this.getStaffID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	void setPassword(String passWord) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE staff " +
						 "SET PASSWORD = \'" + passWord + "\' " +
						 "WHERE ID = " + this.getStaffID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	void createMessage(String text, int patientID, String date) {// usage: createMessage("string", patient.getPatientID() )
		// date in full ISO format
		// assume staff is a nurse unless isDoctor is truee
		int sender = 1;
		if(this.isDoctor()) {
			sender = 2;
		}
		Message newMessage = new Message(text, patientID, date, sender);
	}


	void setTeamID(int teamID) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE staff " +
						 "SET TEAM_ID = " + teamID + " " +
						 "WHERE ID = " + this.getStaffID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}
