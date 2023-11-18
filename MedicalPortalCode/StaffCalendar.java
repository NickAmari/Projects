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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;

public class StaffCalendar extends GridPane{

	private Label date;
	private DatePicker apptDate;
	private String ld;
	private Button getAppts;
	private ListView<String> apptsView;
	private ObservableList<String> aO;
	public ArrayList<Appointment> appts;
	private ArrayList<String> aString;
	private ScrollPane sp;

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
        gridPane.add(getAppts, 2, 0);

        getAppts.setOnAction(action -> {

            ld = apptDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            appts = StaffLogin.st.getTodaysAppointments(ld);
            aString = new ArrayList<String>();

            for(int i = 0; i < appts.size(); i++) {
            	aString.add(i, appts.get(i).getDate() + " " + appts.get(i).getPatientName());
            }

            aO = FXCollections.observableArrayList(aString);
            apptsView = new ListView<String>();
            apptsView.setItems(aO);
            sp = new ScrollPane();
            sp.setContent(apptsView);

            gridPane.add(sp, 1, 1);

            apptsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	selected = appts.get(apptsView.getSelectionModel().getSelectedIndex());
                	//go to appointment info page when clicked on specific appointment
                	AppointmentInfo root = new AppointmentInfo();
    	            Scene scene = new Scene(root, 800,500);
    	            Stage stage = new Stage();
    	            stage.setScene(scene); // set the scene
    	            stage.setTitle("Appointment Info: " + ld.toString());
    	            stage.show();
                }
            });

        });



        this.add(gridPane, 0, 0);

    }

}