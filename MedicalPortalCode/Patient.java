import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Patient {
	private int id;
	
	Patient(String firstName, String lastName, String birthDate, boolean loggingIn) {
		Connection c = null;
		Statement statement = null;
		int patientID = -1;
		int rand = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM patients " +
							"WHERE FIRST_NAME = \'" + firstName + "\' AND " +
							"LAST_NAME = \'" + lastName + "\' AND " + 
							"BIRTH_DATE = \'" + birthDate + "\';";
			ResultSet rs = statement.executeQuery(sql);


			int resultCount = 0;

			while( rs.next() ) {
				resultCount++;
				patientID = rs.getInt(resultCount);
			}

						statement.close();
		 	c.commit();
		 	c.close();
		 	
			if(resultCount == 1 && loggingIn) {
				this.id = patientID;
			} else if(resultCount == 0 && !loggingIn) {
				/* Create the object as a new entity in the relation */
				try {
					c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
					c.setAutoCommit(false);
					statement = c.createStatement();
					sql = 	"INSERT INTO patients(FIRST_NAME, LAST_NAME, BIRTH_DATE, DOCTOR_ID) " + 
									"VALUES(\'" + firstName + "\', \'" + lastName + "\', \'" + birthDate + "\', " + rand + ");";
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
		 			sql = "SELECT MAX(ID) FROM patients;";
		 			rs = statement.executeQuery(sql);
		 			id = rs.getInt(1);
		 			statement.close();
		 			c.commit();
		 			c.close();
		 		} catch(Exception e) {
		 			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		 			System.exit(0);
		 		}
		 	} else {
		 		throw new Exception("Error: invalid conditions for login / creation. If logging in, patient does not exist. If creating, patient already exists. Or there may be more than one entity with that information at the moment.");
		 	}


		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}			
		
	}

	Patient(int patientID)
	{
		this.id = patientID;
	}
	
	int getPatientID() {
		return this.id;
	}

	ArrayList<Message> getMessages() {
		Connection c = null;
		Statement statement = null;
		ArrayList<Message> messages = new ArrayList<Message>(); // Create an arrayList for our messages
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM messages " + 
							"WHERE PATIENT_ID = " + this.getPatientID() + " " + 
							"ORDER BY DATE_LONG DESC;";
			ResultSet rs = statement.executeQuery(sql);

			int i = 1;
			while( rs.next() ) {
				messages.add(new Message(rs.getInt("ID")));
				i++;
			}

			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return messages;
	}

	ArrayList<Appointment> getPastAppointments() {
		Connection c = null;
		Statement statement = null;
		ArrayList<Appointment> appointments = new ArrayList<Appointment>(); // Create an arrayList for our messages
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM appointments " + 
							"WHERE PATIENT_ID = " + this.getPatientID() + " AND " +
							"PAST_BOOL = 1 " + 
							"ORDER BY DATE_LONG DESC;";
			ResultSet rs = statement.executeQuery(sql);

			int i = 1;
			while( rs.next() ) {
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

	ArrayList<Appointment> getFutureAppointments() {
		Connection c = null;
		Statement statement = null;
		ArrayList<Appointment> appointments = new ArrayList<Appointment>(); // Create an arrayList for our messages
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = 	"SELECT ID " +
							"FROM appointments " + 
							"WHERE PATIENT_ID = " + this.getPatientID() + " AND " +
							"PAST_BOOL = 0 " + 
							"ORDER BY DATE_LONG ASC;";
			ResultSet rs = statement.executeQuery(sql);

			int i = 1;
			while( rs.next() ) {
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
		
		return lastName;
	}
	
	String getBirthDate() {
		Connection c = null;
		Statement statement = null;
		String birthDate = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT BIRTH_DATE " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			birthDate = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return birthDate;
	}
	
	int getPhoneNumber() {
		Connection c = null;
		Statement statement = null;
		int phoneNumber = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PHONE_NUM " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			phoneNumber = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return phoneNumber;
	}
	
	String getEmail() {
		Connection c = null;
		Statement statement = null;
		String email = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT EMAIL " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			email = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return email;
	}
	
	String getAddressLine1() {
		Connection c = null;
		Statement statement = null;
		String address = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT ADDRESS_LINE1 " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			address = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return address;
	}
	
	String getAddressLine2() {
		Connection c = null;
		Statement statement = null;
		String address = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT ADDRESS_LINE2 " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			address = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return address;
	}
	
	String getAddressState() {
		Connection c = null;
		Statement statement = null;
		String state = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT ADDRESS_STATE " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			state = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return state;
	}
	
	String getAddressCountry() {
		Connection c = null;
		Statement statement = null;
		String country = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT ADDRESS_COUNTRY " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			country = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return country;
	}
	
	int getAddressZip() {
		Connection c = null;
		Statement statement = null;
		int zip = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT ADDRESS_ZIP " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			zip = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return zip;
	}
	
	String getInsuranceCompany() {
		Connection c = null;
		Statement statement = null;
		String insurance = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT INSURANCE " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			insurance = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return insurance;
	}
	
	String getInsuranceID() {
		Connection c = null;
		Statement statement = null;
		String insuranceID = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT INSURANCE_ID " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			insuranceID = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return insuranceID;
	}
	
	String getInsuranceGroup() {
		Connection c = null;
		Statement statement = null;
		String insuranceGroup = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT INSURANCE_GROUP " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			insuranceGroup = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return insuranceGroup;
	}
	
	String getPharmacyName() {
		Connection c = null;
		Statement statement = null;
		String pharmacyName = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PHARM_NAME " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			pharmacyName = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return pharmacyName;
	}
	
	String getPharmacyAddress() {
		Connection c = null;
		Statement statement = null;
		String pharmacyAddress = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PHARM_ADDRESS " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			pharmacyAddress = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return pharmacyAddress;
	}
	
	String getPharmacyState() {
		Connection c = null;
		Statement statement = null;
		String pharmacyState = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PHARM_STATE " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			pharmacyState = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return pharmacyState;
	}
	
	String getPharmacyCountry() {
		Connection c = null;
		Statement statement = null;
		String pharmacyCountry = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PHARM_COUNTRY " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			pharmacyCountry = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return pharmacyCountry;
	}
	
	int getPharmacyZip() {
		Connection c = null;
		Statement statement = null;
		int zip = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT PHARM_ZIP " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			zip = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return zip;
	}
	
	String getHealthHistory() {
		Connection c = null;
		Statement statement = null;
		String healthHistory = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT HEALTH_HISTORY " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			healthHistory = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return healthHistory;
	}
	
	String getMedHistory() {
		Connection c = null;
		Statement statement = null;
		String medHistory = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT MEDICATION_HISTORY " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			medHistory = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return medHistory;
	}
	
	String getImmuneHistory() {
		Connection c = null;
		Statement statement = null;
		String immuneHistory = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT IMMUNE_HISTORY " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			immuneHistory = rs.getString(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return immuneHistory;
	}

	int getDoctorID() {
		Connection c = null;
		Statement statement = null;
		int id = -1;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT DOCTOR_ID " +
			"FROM patients " + 
			"WHERE ID = " + this.getPatientID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			id = rs.getInt(1);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return id;
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
			"WHERE ID = " + this.getDoctorID() + ";";
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
	
	String getDoctor() {
		Connection c = null;
		Statement statement = null;
		String docName = "";
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "SELECT FIRST_NAME, LAST_NAME " +
			"FROM staff " + 
			"WHERE ID = " + this.getDoctorID() + ";";
			ResultSet rs = statement.executeQuery(sql);
			int resultCount = 0;
			while( rs.next() ) {
				resultCount++;
				String first_name = rs.getString("FIRST_NAME");
				String last_name = rs.getString("LAST_NAME");
				docName = first_name + " " + last_name;
			}
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return docName;
	}
	
	String getNurse() {
		Connection c = null;
		Statement statement = null;
		String nurseName = "";
			
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();

			String sql = "SELECT FIRST_NAME, LAST_NAME " +
			"FROM staff " + 
			"WHERE TEAM_ID = " + this.getTeamID() + " AND " + 
			"DOCTOR = 'NO';";
			ResultSet rs = statement.executeQuery(sql);
			int resultCount = 0;
			while( rs.next() ) {
				resultCount++;
				String first_name = rs.getString("FIRST_NAME");
				String last_name = rs.getString("LAST_NAME");
				nurseName = first_name + " " + last_name;
			}
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return nurseName;
	}
	
	void setFirstName(String firstName) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET FIRST_NAME = \'" + firstName + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
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
			String sql = "UPDATE patients " +
			"SET LAST_NAME = \'" + lastName + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setBirthDate(String birthDate) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET BIRTH_DATE = \'" + birthDate + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPhoneNumber(int phoneNumber) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET PHONE_NUM = " + phoneNumber + " " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setEmail(String email) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET EMAIL = \'" + email + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAddressLine1(String addressLine1) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET ADDRESS_LINE1 = \'" + addressLine1 + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAddressLine2(String addressLine2) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET ADDRESS_LINE2 = \'" + addressLine2 + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAddressState(String addressState) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET ADDRESS_STATE = \'" + addressState + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAddressCountry(String addressCountry) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET ADDRESS_COUNTRY = \'" + addressCountry + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setAddressZip(int zip) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET ADDRESS_ZIP = " + zip + " " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setInsurance(String insurance) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET INSURANCE = \'" + insurance + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setInsuranceID(String insuranceID) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET INSURANCE_ID = \'" + insuranceID + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setInsuranceGroup(String insuranceGroup) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET INSURANCE_GROUP = \'" + insuranceGroup + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPharmName(String pharmacyName) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET PHARM_NAME = \'" + pharmacyName + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPharmacyAddress(String pharmacyAddress) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET PHARM_ADDRESS = \'" + pharmacyAddress + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPharmacyState(String pharmacyState) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET PHARM_STATE = \'" + pharmacyState + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPharmacyCountry(String pharmacyCountry) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET PHARM_COUNTRY = \'" + pharmacyCountry + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setPharmacyZip(int zip) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET PHARM_ZIP = " + zip + " " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setDocterID(int doctorID) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET DOCTOR_ID = " + doctorID + " " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setHealthHistory(String healthHistory) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET HEALTH_HISTORY = \'" + healthHistory + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setMedHistory(String medHistory) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET MEDICATION_HISTORY = \'" + medHistory + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	void setImmuneHistory(String immuneHistory) {
		Connection c = null;
		Statement statement = null;
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:medical-records.db");
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "UPDATE patients " +
			"SET IMMUNE_HISTORY = \'" + immuneHistory + "\' " +
			"WHERE ID = " + this.getPatientID() + ";";
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
