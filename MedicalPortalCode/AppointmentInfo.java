package application;

import javafx.scene.Scene;
import javafx.scene.control.ListView; //for appointments
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javafx.event.ActionEvent;


public class AppointmentInfo extends VBox{

	private GridPane patientInfo, nurseInfo, doctorInfo;
	private Label patientTitle, fN, lN, dob, email, phone;
	private Text fNT, lNT, dobT, emailT, phoneT;
	private Label nurseTitle, weight, height, temp, bp;
	private TextField weightT, heightT, tempT, bpT;
	private Label doctorTitle, pharm;
	private TextArea diagnosis, prescription, history;

	private Button complete;
	private CheckBox sendtoPharm;
	private boolean over12;
	private Patient p;

	public AppointmentInfo() {

		VBox pane = new VBox();

	//Patient Info Section

		patientInfo = new GridPane();

		//get info from patient (how?)
		p = new Patient(StaffCalendar.selected.getPatientID());
		fNT = new Text(p.getFirstName());
		lNT = new Text(p.getLastName());
		dobT = new Text(p.getBirthDate());
		emailT = new Text(p.getEmail());
		phoneT = new Text(Integer.toString(p.getPhoneNumber()));

		patientTitle = new Label(fNT.getText() + " " + lNT.getText() + "'s Info");
			patientTitle.setStyle("-fx-font: 24 arial;");
		fN = new Label("First Name: ");
		lN = new Label("Last Name: ");
		dob = new Label("Date of Birth: ");
		email = new Label("E-mail: ");
		phone = new Label("Phone Number: ");

		patientInfo.add(patientTitle, 0, 0);
		patientInfo.add(fN, 0, 1);
		patientInfo.add(fNT, 1, 1);
		patientInfo.add(lN, 3, 1);
		patientInfo.add(lNT, 4, 1);
		patientInfo.add(dob, 6, 1);
		patientInfo.add(dobT, 7, 1);
		patientInfo.add(email, 0, 2);
		patientInfo.add(emailT, 1, 2);
		patientInfo.add(phone, 3, 2);
		patientInfo.add(phoneT, 4, 2);
		pane.setPadding(new Insets(10,10,10,10));

		pane.getChildren().add(patientInfo);

	//Nurse's Section

		nurseInfo = new GridPane();

		//check if patient is under 12 years old
		if(Integer.parseInt(dobT.getText().substring(0,3)) > 2009)
			over12 = false;
		else
			over12 = true;

		//initiate labels
		nurseTitle = new Label("For Nurse");
			nurseTitle.setStyle("-fx-font: 24 arial;");
		weight = new Label("Weight: ");
		height = new Label("Height: ");
		temp = new Label("Body Temperature: ");
		bp = new Label("Blood Pressure: ");

		//initiate text fields, if under 12 then "N/A" for these sections
		if(over12)
		{
			weightT = new TextField();
			heightT = new TextField();
			tempT = new TextField();
			bpT = new TextField();
		}
		else
		{
			weightT = new TextField("N/A");
			heightT = new TextField("N/A");
			tempT = new TextField("N/A");
			bpT = new TextField("N/A");
		}

		//add all to the gridpane
		nurseInfo.add(nurseTitle, 0, 4);
		nurseInfo.add(weight, 0, 5);
		nurseInfo.add(weightT, 1, 5);
		nurseInfo.add(height, 3, 5);
		nurseInfo.add(heightT, 4, 5);
		nurseInfo.add(temp, 0, 6);
		nurseInfo.add(tempT, 1, 6);
		nurseInfo.add(bp, 3, 6);
		nurseInfo.add(bpT, 4, 6);

		//add gridpane to the vbox pane
		pane.getChildren().add(nurseInfo);

	//Doctor's Section

		doctorInfo = new GridPane();

		//initialize all objects
		doctorTitle = new Label("For Doctor: ");
			doctorTitle.setStyle("-fx-font: 24 arial;");
		pharm = new Label("Send prescription to patient's pharmacy?");

		history = new TextArea();
		history.setPromptText("Patient's current health history: " + p.getHealthHistory() + "- Type here to change");

		diagnosis = new TextArea();
		diagnosis.setPromptText("Enter diagnosis here");
		prescription = new TextArea();
		prescription.setPromptText("Enter official prescription");

		sendtoPharm = new CheckBox();
		complete = new Button("Complete Appointment");

		//add objects to gridpane
		doctorInfo.add(history, 0, 8);
		doctorInfo.add(doctorTitle, 0, 9);
		doctorInfo.add(diagnosis, 0, 10);
		doctorInfo.add(prescription, 0, 11);
		doctorInfo.add(sendtoPharm, 0, 12);
		doctorInfo.add(pharm, 1, 12);
		doctorInfo.add(complete, 0, 13);

		//add gridpane to the vbox pane
		pane.getChildren().add(doctorInfo);

		this.getChildren().add(pane);

		//send

		complete.setOnAction(action -> {
			//update appt info for database

			 p.setHealthHistory(diagnosis.getText());
			 StaffCalendar.selected.setWeight(Double.parseDouble(weightT.getText()));
			 StaffCalendar.selected.setHeight(Double.parseDouble(heightT.getText()));
			 StaffCalendar.selected.setBodyTemp(Double.parseDouble(tempT.getText()));
			 StaffCalendar.selected.setBloodPressure1(Integer.parseInt(bpT.getText()));
			 StaffCalendar.selected.setPrescription(prescription.getText());
			 StaffCalendar.selected.setDiagnosis(diagnosis.getText());
			 StaffCalendar.selected.setAppointmentPast(true);

			 if (!history.getText().equals(""))
				p.setHealthHistory(history.getText());

			 if (sendtoPharm.isSelected())
				StaffCalendar.selected.setSentToPharm(true);

			 //go back to calendar page
			 StaffView home = new StaffView();
		     Scene scene = new Scene(home, 800,500);
		     Stage stage = new Stage();
		     stage.setScene(scene); // set the scene
		     stage.setTitle("Staff View");
		     stage.show();
		});
	}

}