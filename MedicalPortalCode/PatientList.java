package application;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
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
import java.util.ArrayList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientList extends GridPane{

	private ArrayList<Patient> patients;
	private ArrayList<String> pString;
	private ObservableList<String> pO;
	private ListView<String> pList;
	private ScrollPane pS;
	public static Patient patient;

	public PatientList() {

		GridPane pane = new GridPane();

		patients = StaffLogin.st.getPatients();
		pString = new ArrayList<String>();

		for(int i = 0; i < patients.size(); i++)
			pString.add(i, patients.get(i).getFirstName() + " " + patients.get(i).getLastName());

		pO = FXCollections.observableArrayList(pString);
		pList = new ListView<String>();
		pList.setItems(pO);

		pS = new ScrollPane();
		pS.setContent(pList);

		pane.add(pS, 0, 0);

		pList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	patient = patients.get(pList.getSelectionModel().getSelectedIndex());
                //go to appointment info page when clicked on specific appointment
            	PatientHistory root = new PatientHistory();
	            Scene scene = new Scene(root, 800,500);
	            Stage stage = new Stage();
	            stage.setScene(scene); // set the scene
	            stage.setTitle(patient.getFirstName() + patient.getLastName() + "'s History");
	            stage.show();
            }
        });

        this.add(pane, 0, 0);
	}
}