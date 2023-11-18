package application;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;

//Patient History displays essentially the title ("Patient History")
// also displays a text box that is filled with the information of the Patient

public class PatientHistory extends GridPane
{
  private Label title;
  private Label fN, lN, weight, height, temp, bp, pharm;
  private Label healthHistText;

  public PatientHistory()
  {
    GridPane pane = new GridPane();

    title = new Label(PatientList.patient.getFirstName() + " " + PatientList.patient.getLastName() + "'s Information");

    //adding label to GridPane
    pane.add(title, 0, 0);

    healthHistText = new Label("Health History: " + PatientList.patient.getHealthHistory());
    fN = new Label("First Name: " + PatientList.patient.getFirstName());
    lN = new Label("Last Name: " + PatientList.patient.getLastName());
    pharm = new Label("Pharmacy: " + PatientList.patient.getPharmacyName());

    healthHistText.setPrefWidth(800);
    healthHistText.setPrefHeight(80);
    healthHistText.setMaxWidth(800);
    healthHistText.setMaxHeight(800);
    pane.add(fN, 0, 1);
    pane.add(lN, 1, 1);
    pane.add(pharm, 0, 2);
    pane.add(healthHistText, 1, 2);

    if(PatientList.patient.getPastAppointments().size() > 0)
    {
        weight = new Label("Weight: " + PatientList.patient.getPastAppointments().get(0).getWeight());
        height = new Label("Height: " + PatientList.patient.getPastAppointments().get(0).getHeight());
        temp = new Label("Temperature: " + PatientList.patient.getPastAppointments().get(0).getBodyTemp());
        bp = new Label("Blood Pressure: " + PatientList.patient.getPastAppointments().get(0).getBloodPressure1());
        pane.add(weight, 0, 4);
        pane.add(height, 1, 4);
        pane.add(temp, 0, 5);
        pane.add(bp, 1, 5);
    }

    pane.setMinWidth(2000);

    this.add(pane, 0, 0);
  }

}