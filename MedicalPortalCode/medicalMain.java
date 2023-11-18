import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class medicalMain {

	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = now.format(format);
		return formattedDate;
	}

	public static void main( String args[] ) {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:medicalMain.db");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		try {
			System.out.println("Creating patient");
			Patient testPatient = new Patient("HGJF", "def", "2000-01-13", false);
			/*
			testPatient.setPhoneNumber(1234567);
			testPatient.setEmail("email@server.com");
			testPatient.setAddressLine1("AddressLine1");
			testPatient.setAddressState("AZ");
			testPatient.setAddressCountry("USA");
			testPatient.setAddressZip(85280);
			testPatient.setInsurance("InsuranceCo");
			testPatient.setInsuranceID("InsuranceID");
			testPatient.setInsuranceGroup("InsuranceGroup");
			testPatient.setPharmName("PharmacyName");
			testPatient.setPharmacyAddress("PharmAddress");
			testPatient.setPharmacyState("AZ");
			testPatient.setPharmacyCountry("USA");
			testPatient.setPharmacyZip(85280);
			testPatient.setHealthHistory("HealthHistory");
			testPatient.setMedHistory("MedHistory");
			testPatient.setImmuneHistory("ImmuneHistory");
			int idToPrint = testPatient.getPatientID();
			System.out.println(idToPrint);
			String fnToPrint = testPatient.getFirstName();
			System.out.println(fnToPrint);
			String lnToPrint = testPatient.getLastName();
			System.out.println(lnToPrint);
			String bdToPrint = testPatient.getBirthDate();
			System.out.println(bdToPrint);
			int pnToPrint = testPatient.getPhoneNumber();
			System.out.println(pnToPrint);
			String emToPrint = testPatient.getEmail();
			System.out.println(emToPrint);
			String al1ToPrint = testPatient.getAddressLine1();
			System.out.println(al1ToPrint);
			String al2ToPrint = testPatient.getAddressLine2();
			System.out.println(al2ToPrint);
			String asToPrint = testPatient.getAddressState();
			System.out.println(asToPrint);
			String acToPrint = testPatient.getAddressCountry();
			System.out.println(acToPrint);
			int azToPrint = testPatient.getAddressZip();
			System.out.println(azToPrint);
			String icToPrint = testPatient.getInsuranceCompany();
			System.out.println(icToPrint);
			String iiToPrint = testPatient.getInsuranceID();
			System.out.println(iiToPrint);
			String igToPrint = testPatient.getInsuranceGroup();
			System.out.println(igToPrint);
			String pharmNameToPrint = testPatient.getPharmacyName();
			System.out.println(pharmNameToPrint);
			String paToPrint = testPatient.getPharmacyAddress();
			System.out.println(paToPrint);
			String psToPrint = testPatient.getPharmacyState();
			System.out.println(psToPrint);
			String pcToPrint = testPatient.getPharmacyCountry();
			System.out.println(pcToPrint);
			int pzToPrint = testPatient.getPharmacyZip();
			System.out.println(pzToPrint);
			String hhToPrint = testPatient.getHealthHistory();
			System.out.println(hhToPrint);
			String mhToPrint = testPatient.getMedHistory();
			System.out.println(mhToPrint);
			String ihToPrint = testPatient.getImmuneHistory();
			System.out.println(ihToPrint);
			int diToPrint = testPatient.getDoctorID();
			System.out.println(diToPrint); */
			System.out.println("getDoctor");
			String dToPrint = testPatient.getDoctor();
			System.out.println(dToPrint);
			System.out.println("getNurse");
			String nToPrint = testPatient.getNurse();
			System.out.println(nToPrint);
			/*
			Message testMessage = new Message("test", testPatient.getPatientID(), getCurrentDateTime(), 0);
			System.out.println(testMessage.getMessage());
			System.out.println(testMessage.getPatientID());
			System.out.println(testMessage.getDate());
			System.out.println(testMessage.getSender());
			testMessage.setMessage("test1");
			Message testMessage2 = new Message("test6", testPatient.getPatientID(), getCurrentDateTime(), 0);
			Message testMessage3 = new Message("test7", testPatient.getPatientID(), getCurrentDateTime(), 0);
			Message testMessage4 = new Message("test8", testPatient.getPatientID(), getCurrentDateTime(), 0);
			Message testMessage5 = new Message("test9", testPatient.getPatientID(), getCurrentDateTime(), 0);
			ArrayList<Message> testMessages = new ArrayList<Message>();
			testMessages = testPatient.getMessages();
			testMessages.forEach((m) -> System.out.println(m.getMessage()));
			*/

			/*
			Appointment testAppointment = new Appointment(getCurrentDateTime(), false, testPatient.getPatientID());
			System.out.println(testAppointment.getAppointmentID());
			System.out.println(testAppointment.getDate());
			System.out.println(testAppointment.getAppointmentPast());
			System.out.println(testAppointment.getBodyTemp());
			System.out.println("testAppointment.setDate(getCurrentDateTime());");
			testAppointment.setDate(getCurrentDateTime());
			System.out.println("testAppointment.setAppointmentPast(true);");
			testAppointment.setAppointmentPast(true);
			System.out.println("testAppointment.setBodyTemp(98.6);");
			testAppointment.setBodyTemp(98.6);
			System.out.println("testAppointment.setWeight(200);");
			testAppointment.setWeight(200);
			System.out.println("testAppointment.setHeight(180);");
			testAppointment.setHeight(180);
			System.out.println("testAppointment.setBloodPressure1(120);");
			testAppointment.setBloodPressure1(120);
			System.out.println("testAppointment.setBloodPressure2(30);");
			testAppointment.setBloodPressure2(30);
			System.out.println("testAppointment.setAllergies(allergies);");
			testAppointment.setAllergies("allergies");
			System.out.println("testAppointment.setDiagnosis");
			testAppointment.setDiagnosis("diagnosis");
			System.out.println("testAppointment.setPrescription(");
			testAppointment.setPrescription("Prescription");
			System.out.println("testAppointment.setSentToPharm(");
			testAppointment.setSentToPharm(true);
			System.out.println("testAppointment.setPatientID(testPatient.getPatientID());");
			testAppointment.setPatientID(testPatient.getPatientID());

			System.out.println(testAppointment.getAppointmentID());
			System.out.println(testAppointment.getDate());
			System.out.println(testAppointment.getAppointmentPast());
			System.out.println(testAppointment.getBodyTemp());
			System.out.println(testAppointment.getWeight());
			System.out.println(testAppointment.getHeight());
			System.out.println(testAppointment.getBloodPressure1());
			System.out.println(testAppointment.getBloodPressure2());
			System.out.println(testAppointment.getAllergies());
			System.out.println(testAppointment.getDiagnosis());
			System.out.println(testAppointment.getPrescription());
			System.out.println(testAppointment.getSentToPharm());
			System.out.println(testAppointment.getPatientID());
			*/
			//Appointment testAppointment = new Appointment(getCurrentDateTime(), false, testPatient.getPatientID());
			/*
			ArrayList<Appointment> testPastAppointments = new ArrayList<Appointment>();
			testPastAppointments = testPatient.getPastAppointments();
			testPastAppointments.forEach((m) -> System.out.println(m.getDate()));

			System.out.println("Break");

			ArrayList<Appointment> testFutureAppointments = new ArrayList<Appointment>();
			testFutureAppointments = testPatient.getFutureAppointments();
			testFutureAppointments.forEach((m) -> System.out.println(m.getDate()));
			*/
			System.out.println("testStaff");
			Staff testStaff = new Staff("", "", "AmyCook", "passwort369", true, -1, true);
			
			System.out.println("System.out.println(testStaff.getStaffID());");
			System.out.println(testStaff.getStaffID());
			System.out.println("System.out.println(testStaff.getFirstName());");
			System.out.println(testStaff.getFirstName());
			System.out.println("System.out.println(testStaff.getLastName());");
			System.out.println(testStaff.getLastName());
			System.out.println("System.out.println(testStaff.getUserName());");
    		System.out.println(testStaff.getUserName());
    		System.out.println("System.out.println(testStaff.getPassword());");
    		System.out.println(testStaff.getPassword());
    		System.out.println("System.out.println(testStaff.isDoctor());");
    		System.out.println(testStaff.isDoctor());
    		System.out.println("System.out.println(testStaff.getTeamID());");
    		System.out.println(testStaff.getTeamID());

    		System.out.println("testPatientList ");
    		ArrayList<Patient> testPatientList = new ArrayList<Patient>();
			testPatientList = testStaff.getPatients();
			testPatientList.forEach((m) -> System.out.println(m.getLastName()));
			
			Appointment testAppointment = new Appointment(getCurrentDateTime(), false, testPatient.getPatientID());

			System.out.println("testDailyAppointments  ");
			ArrayList<Appointment> testDailyAppointments = new ArrayList<Appointment>();
			testDailyAppointments = testStaff.getTodaysAppointments("2021-11-14");
			testDailyAppointments.forEach((m) -> System.out.println(m.getDate()));

			System.out.println("Done!");
		} catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
}