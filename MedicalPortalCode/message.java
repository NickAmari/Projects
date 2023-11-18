import java.sql.*;
//SENDER 0 = Patient, 1 = Nurse, 2 = Doctor
public class Message {
	private int id;

	Message(String text, int patientID, String date, int sender) {
		Connection c = null;
		Statement statement = null;

		/* Create as a new entity in the relation */
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "INSERT INTO messages(MESSAGE_BODY, PATIENT_ID, DATE_LONG, SENDER) " +
						"VALUES(\'" + text + "\', \'" + patientID + "\', \'" + date +
						"\', " + sender + ");";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		// Create as an object using id
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT MAX(ID) FROM messages;";
			ResultSet rs = statement.executeQuery(sql);
			this.id = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	Message(int messageID) {
		this.id = messageID;
	}

	int getMessageID() {
		return this.id;
	}


	String getMessage() {
		Connection c = null;
		Statement statement = null;
		String text = "";

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT MESSAGE_BODY " +
					  	 "FROM messages " +
						 "WHERE ID = " + this.getMessageID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			text = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return text;
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
					  	 "FROM messages " +
						 "WHERE ID = " + this.getMessageID() + ";";
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

	String getDate() {
		Connection c = null;
		Statement statement = null;
		String date = "";

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT DATE_LONG " +
					  	 "FROM messages " +
						 "WHERE ID = " + this.getMessageID() + ";";
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

	int getSender() {
		Connection c = null;
		Statement statement = null;
		int sender = -1;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT SENDER " +
					  	 "FROM messages " +
						 "WHERE ID = " + this.getMessageID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			sender = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return sender;
	}

	void setMessage(String text) {// Probably will never be called, wont be changing messages once they've been set but here we are
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE messages " +
						 "SET MESSAGE_BODY = \'" + text + "\' " +
						 "WHERE ID = " + this.getMessageID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	void setdate(String date) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE messages " +
						 "SET DATE_LONG = \'" + date + "\' " +
						 "WHERE ID = " + this.getMessageID() + ";";
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
			String sql = "UPDATE messages " +
					  	 "SET PATIENT_ID = " + patientID + " " +
						 "WHERE ID = " + this.getMessageID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	void setSender(int sender) {
		Connection c = null;
		Statement statement = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE messages " +
					  	 "SET SENDER = " + sender + " " +
						 "WHERE ID = " + this.getMessageID() + ";";
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
