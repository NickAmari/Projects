package application;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.DatePicker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PatientCreateAppt extends BorderPane{
	private ComboBox<String> time, timeOfDay;
	private DatePicker date;
	public Button createAppt;
	private GridPane grid;
	//private ArrayList<Appointment> futureAppt;
	//private ArrayList<Appointment> pastAppt;
	private Appointment newAppt;

	public PatientCreateAppt() {
		//Create BorderPane to hold all objects
		BorderPane pane = new BorderPane();

		//Create Grid to hold date, time, and createAppt button
		grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		grid.setVgap(10); //vertical gap in pixels

		//Create Date Picker object
		date = new DatePicker();
		date.setPromptText("Date of Appointment");
		grid.add(date, 0, 0);



		//Create time drop down menu
		time = new ComboBox<String>();
		time.setPromptText("Time of Appointment");
		time.getItems().addAll("12:00", "12:30", "01:00", "1:30", "2:00",
				"2:30", "3:00", "3:30", "4:00", "4:30", "5:00",
				"5:30", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30",
				"9:00", "9:30", "10:00", "10:30", "11:00",
				"11:30");
		grid.add(time, 1, 0);

		//Create an am or pm combo box
		timeOfDay = new ComboBox<String>();
		timeOfDay.setPromptText("AM or PM?");
		timeOfDay.getItems().addAll("AM", "PM");
		grid.add(timeOfDay, 2, 0);

		//Create Button
		createAppt = new Button("Create Appointment");
		grid.add(createAppt, 0, 1);

		pane.setLeft(grid); 

		createAppt.setOnAction(e->{
			if(timeOfDay.getValue() != null && date.getValue() != null) {
				int isoTimeFormatHours = Integer.parseInt(time.getValue().split(":")[0]);
				String isoTimeFormatMinutes = time.getValue().split(":")[1];
				if (timeOfDay.getValue() == "PM" && isoTimeFormatHours != 12) {
					isoTimeFormatHours = isoTimeFormatHours + 12;
				}
				else if(timeOfDay.getValue() == "AM" && isoTimeFormatHours == 12) {
					isoTimeFormatHours = 00;
				}
				String isoTimeFormat = String.valueOf(isoTimeFormatHours) + ":" + isoTimeFormatMinutes + ":" + "00";

				newAppt = new Appointment(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + isoTimeFormat, false, PatientLogin.patient.getPatientID());
				
				
			}
			else {
				Label error = new Label("**Please complete all fields before continuing.");
    			error.setTextFill(Color.RED);
    			pane.setBottom(error);
    			BorderPane.setAlignment(error, Pos.TOP_LEFT);
			}

		});

		this.setCenter(pane);

	}
}
