package application;

import javafx.scene.Scene;
import javafx.scene.control.ListView; //for appointments
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
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


public class PatientApptNotes extends BorderPane{

	private Label header, staffHeader, weight, height, bodyTemp, bP,officialDiagnosis, prescribed;
	private VBox vboxHeader, vboxBody;
	private GridPane body;
	
	
	public PatientApptNotes() {
		BorderPane pane = new BorderPane();
		header = new Label("Information from on: " + PatientAppointments.selected.getDate());
		staffHeader = new Label("Staff: " + "Dr. "+ PatientLogin.patient.getDoctor() + " Nurse: " + PatientLogin.patient.getNurse());
		vboxHeader = new VBox();
		vboxHeader.getChildren().addAll(header, staffHeader);
		
		
		
		weight = new Label("Weight: " + PatientAppointments.selected.getWeight());
		height = new Label("Height: " + PatientAppointments.selected.getHeight());
		bodyTemp = new Label("Body temperature: " + PatientAppointments.selected.getBodyTemp());
		bP = new Label("Blood Pressure: " + PatientAppointments.selected.getBloodPressure1());
		officialDiagnosis = new Label("Diagnosis: " + PatientAppointments.selected.getDiagnosis());
		
		if(PatientAppointments.selected.getPrescription() == null) {
			prescribed = new Label("No prescriptions were needed on this day.");
		}
		else if(PatientAppointments.selected.getSentToPharm()) {
			prescribed = new Label("Prescribed: " + PatientAppointments.selected.getPrescription() + ". This has been sent to the patient's preferred pharmacy.");
		} else {
			prescribed = new Label("Prescribed: " + PatientAppointments.selected.getPrescription() + ". This hasn't been sent to the patient's preferred pharmacy.");
		}
		
		if(PatientAppointments.selected.getDiagnosis() == null) {
			officialDiagnosis = new Label("The patient was not diagnosed on this day.");
		}
		
		body = new GridPane();
		//body.setPadding(new Insets(10, 10, 10, 10));
		//body.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		//body.setVgap(10); //vertical gap in pixels
		body.add(weight, 0, 0);
		body.add(height, 0, 2);
		body.add(bodyTemp, 1, 0);
		body.add(bP, 1, 2);
		
		
		vboxHeader.getChildren().addAll(body, officialDiagnosis, prescribed);
		pane.setLeft(vboxHeader);
		pane.setPadding(new Insets(10,10,10,10));
		this.setCenter(pane);
		
		
	}

}