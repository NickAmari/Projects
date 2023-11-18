package application;

import javafx.scene.Scene;
import javafx.scene.control.ListView; //for appointments
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StaffCalendar extends GridPane{

	private Label date;
	private DatePicker apptDate;
	private LocalDate ld;
	private Button getAppts;
	private ListView<Appointment> apptsView;
	private ObservableList<Appointment> aO;
	public ArrayList<Appointment> appts;
	public static Appointment selected;

    public StaffCalendar() {

    	date = new Label("Appointment Date: ");
    	apptDate = new DatePicker();
    	getAppts = new Button("See Appointments");
    	appts = new ArrayList<Appointment>();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(date, 0, 0);
        gridPane.add(apptDate, 1, 0);

        apptsView = new ListView<Appointment>();

        getAppts.setOnAction(action -> {

            ld = apptDate.getValue();

            appts = StaffLogin.st.getTodaysAppointments(ld.toString());
            aO = FXCollections.observableArrayList(aO);
            apptsView = new ListView<Appointment>();
            apptsView.setItems(aO);
            gridPane.add(apptDate, 1, 0);

        });

        apptsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	selected = apptsView.getSelectionModel().getSelectedItem();
            	//go to appointment info page when clicked on specific appointment
            	AppointmentInfo root = new AppointmentInfo();
	            Scene scene = new Scene(root, 800,500);
	            Stage stage = new Stage();
	            stage.setScene(scene); // set the scene
	            stage.setTitle("Appointment Info: " + ld.toString());
	            stage.show();
            }
        });

        this.add(gridPane, 0, 0);

    }

}
