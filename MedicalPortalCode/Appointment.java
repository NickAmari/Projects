package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {
	private int id;
	
	Appointment(String date, boolean past, int patientID) {
		Connection c = null;
		Statement statement = null;
		int past_num = -1;
		if(past) {
			past_num = 1;
		}
		else {
			past_num = 0;
		}
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "INSERT INTO appointments(DATE_LONG, PAST_BOOL, PATIENT_ID)" + 
						"VALUES(\'" + date + "\', " + past_num + ", \'" + patientID + "\');";
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
			String sql = "SELECT MAX(ID) FROM appointments;";
			ResultSet rs = statement.executeQuery(sql);
			id = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	Appointment(int appointmentID) {
		this.id = appointmentID;
	}
	
	int getAppointmentID() {
        return this.id;
	}
	
	String getDate() {
		Connection c = null;
		Statement statement = null;
		String date = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT DATE_LONG " +
					  	 "FROM appointments " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			date = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return date;
	}
	
	boolean getAppointmentPast() {
		Connection c = null;
		Statement statement = null;
		boolean past = true;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PAST_BOOL " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			past = rs.getBoolean(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return past;
	}
	
	double getBodyTemp() {
		Connection c = null;
		Statement statement = null;
		double bodyTemp = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT BODY_TEMP " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			bodyTemp = rs.getDouble(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return bodyTemp;
	}
	
	double getWeight() {
		Connection c = null;
		Statement statement = null;
		double weight = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT WEIGHT " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			weight = rs.getDouble(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return weight;
	}
	
	double getHeight() {
		Connection c = null;
		Statement statement = null;
		double height = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT HEIGHT " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			height = rs.getDouble(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return height;
	}
	
	int getBloodPressure1() {
		Connection c = null;
		Statement statement = null;
		int bloodPressure = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT BLOOD_PRESSURE1 " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			bloodPressure = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return bloodPressure;
	}
	
	int getBloodPressure2() {
		Connection c = null;
		Statement statement = null;
		int bloodPressure = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT BLOOD_PRESSURE2 " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			bloodPressure = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return bloodPressure;
	}
	
	String getAllergies() {
		Connection c = null;
		Statement statement = null;
		String allergies = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT ALLERGIES " +
					  	 "FROM appointments " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			allergies = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return allergies;
	}
	
	String getDiagnosis() {
		Connection c = null;
		Statement statement = null;
		String diagnosis = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT DIAGNOSIS " +
					  	 "FROM appointments " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			diagnosis = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return diagnosis;
	}
	
	String getPrescription() {
		Connection c = null;
		Statement statement = null;
		String prescription = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PRESCRIPTION " +
					  	 "FROM appointments " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			prescription = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return prescription;
	}
	
	boolean getSentToPharm() {
		Connection c = null;
		Statement statement = null;
		boolean sent = true;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT SENT_TO_PHARM " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			sent = rs.getBoolean(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return sent;
	}
	
	int getPatientID() {
		Connection c = null;
		Statement statement = null;
		int patientID = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PATIENT_ID " +
					  	 "FROM APPOINTMENTS " + 
						 "WHERE ID = " + this.getAppointmentID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			patientID = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return patientID;
	}
	
	void setDate(String date) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET DATE_LONG = \'" + date + "\' " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAppointmentPast(boolean appointmentPast) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET PAST_BOOL = " + appointmentPast + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setBodyTemp(double bodyTemp) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET BODY_TEMP = " + bodyTemp + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setWeight(double weight) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET WEIGHT = " + weight + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setHeight(double height) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET HEIGHT = " + height + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setBloodPressure1(int bloodPressure1) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET BLOOD_PRESSURE1 = " + bloodPressure1 + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setBloodPressure2(int bloodPressure2) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET BLOOD_PRESSURE2 = " + bloodPressure2 + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAllergies(String allergies) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET ALLERGIES = \'" + allergies + "\' " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setDiagnosis(String diagnosis) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET DIAGNOSIS = \'" + diagnosis + "\' " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPrescription(String prescription) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET PRESCRIPTION = \'" + prescription + "\' " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setSentToPharm(boolean sent) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET SENT_TO_PHARM = " + sent + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPatientID(int patientID) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE appointments " +
					  	 "SET PATIENT_ID = " + patientID + " " +
						 "WHERE ID = " + this.getAppointmentID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	String getPatientName() {
		Connection c = null;
		Statement statement = null;
		String patientName = "";
		String firstName = "";
		String lastName = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT FIRST_NAME " +
					  	 "FROM patients " + 
						 "WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			firstName = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT LAST_NAME " +
					  	 "FROM patients " + 
						 "WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			lastName = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		patientName = firstName + lastName;
		
		return patientName;
	}
}