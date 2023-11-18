package application;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent; //***need to import
import javafx.scene.input.*; //***need to import


public class PatientView extends BorderPane {
	
	private PatientInfo info;
	private PatientMessaging messaging;
	private PatientCreateAppt create;
	private PatientAppointments appt;
	private TabPane tabPane;
	private Label header, patientName;
	
	public PatientView() {
		//Create main pane
		BorderPane pane = new BorderPane();
		BorderPane helper = new BorderPane();
		
		//new Labels
		header = new Label("T1 Medical");
		patientName = new Label("Your doctor is: " + PatientLogin.patient.getDoctor() +"  Patient Name: " + PatientLogin.firstName + " " + PatientLogin.lastName);
		
		helper.setLeft(header);
		helper.setRight(patientName);
		
		//Initialize tabpane
		tabPane = new TabPane();
		
		//Create tabs for tabpane
		Tab patientInfo = new Tab();
		patientInfo.setText("Patient Information");
		tabPane.getTabs().add(patientInfo);
		
		Tab appointments = new Tab();
		appointments.setText("Appointments");
		tabPane.getTabs().add(appointments);
		
		Tab createAppt = new Tab();
		createAppt.setText("Create Appointment");
		tabPane.getTabs().add(createAppt);
		
		Tab message = new Tab();
		message.setText("Messaging");
		tabPane.getTabs().add(message);
		
		helper.setBottom(tabPane);
		
		pane.setTop(helper);
		
		if (patientInfo.isSelected()) {
			info = new PatientInfo();
			pane.setCenter(info);
		}
		
		patientInfo.setOnSelectionChanged (e -> {
			if (patientInfo.isSelected()) 
				info = new PatientInfo();
				pane.setCenter(info);
			}
		);
		
		createAppt.setOnSelectionChanged (e -> {
			if (createAppt.isSelected()) 
				create = new PatientCreateAppt();
				pane.setCenter(create);
				
			}
		);
		
		appointments.setOnSelectionChanged (e -> {
			if (appointments.isSelected()) 
				appt = new PatientAppointments();
				pane.setCenter(appt);
			}
		);
		
		message.setOnSelectionChanged (e -> {
			if (message.isSelected()) 
				messaging = new PatientMessaging();
				pane.setCenter(messaging);
			}
		);
		
		
		
		this.setCenter(pane);
		
	}
	
	
}
