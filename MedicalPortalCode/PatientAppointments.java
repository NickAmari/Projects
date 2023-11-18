package application;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class PatientAppointments extends SplitPane{
	private VBox oldDateStack, oldTimeStack, newDateStack, newTimeStack, notes, upcomingStack, pastStack;
	private Label newAppt, oldAppt, oldDate, oldTime, newDate, newTime, newNotes;
	private ScrollPane upcoming, past;
	private ListView<String> futureApptsViewDate, futureApptsViewTime, pastApptsView, notesApptsView;
	private ObservableList<String> futureApptObservDate, futureApptObservTime, pastApptObserv, notesApptObserv;
	public ArrayList<Appointment> futureApptsDate,futureApptsTime, pastAppts, notesAppts;
	private ArrayList<String> futureStringDate, futureStringTime,pastString, notesString;
	
	public static Appointment selected;
	public PatientAppointments() {
		//Create multiple splitPanes to hold everything
		SplitPane pane = new SplitPane();
		pane.setPadding(new Insets(5,15,15,20));
		pane.setMaxHeight(800);
		pane.setMaxWidth(800);
		HBox newDateTime = new HBox();
		newDateTime.setMinHeight(800);
		newDateTime.setMinWidth(400);
		HBox oldDateTime = new HBox();
		oldDateTime.setMinHeight(800);
		oldDateTime.setMinWidth(400);
		
		
		
		//Label for heading
		newAppt = new Label("Upcoming Appointments");
		newAppt.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		newAppt.setFont(Font.font("Arial", 18));
		newAppt.setMaxHeight(800);
		newAppt.setMaxWidth(400);
		oldAppt = new Label("Past Appointments");
		oldAppt.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		oldAppt.setFont(Font.font("Arial", 18));
		oldAppt.setMaxHeight(800);
		oldAppt.setMaxWidth(400);
		
		//Add to Stack
		upcomingStack = new VBox();
		upcomingStack.setMaxWidth(400);
		upcomingStack.setMaxHeight(800);
		upcomingStack.getChildren().add(newAppt);
		pastStack = new VBox();
		pastStack.setMaxWidth(400);
		pastStack.setMaxHeight(800);
		pastStack.getChildren().add(oldAppt);
		
		
		//Date and Time columns for Upcoming Appointments
		newDateStack = new VBox();
		newDateStack.setMaxWidth(200);
		newDateStack.setMinWidth(200);
		newDateStack.setMaxHeight(800);
		//newDateStack.setMinHeight(800);
		newTimeStack = new VBox();
		newTimeStack.setMaxWidth(200);
		newTimeStack.setMinWidth(200);
		newTimeStack.setMaxHeight(800);
		
		//Headers
		newDate = new Label("Date");
		newDate.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		newDate.setFont(Font.font("Arial", 18));
		newDate.setMaxWidth(400);
		newDate.setMaxHeight(800);
		newTime = new Label("Time");
		newTime.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		newTime.setFont(Font.font("Arial", 18));
		newTime.setMaxWidth(200);
		
		newDateStack.getChildren().add(newDate);
		//*** ADD DATES OF UPCOMING APPOINTMENTS TO THIS STACK *****///
		futureApptsDate = PatientLogin.patient.getFutureAppointments();
        futureStringDate = new ArrayList<String>();

        for(int i = 0; i < futureApptsDate.size(); i++) {
        	futureStringDate.add(i, futureApptsDate.get(i).getDate().split(" ")[0]);
        }
        	

        futureApptObservDate = FXCollections.observableArrayList(futureStringDate);
        futureApptsViewDate = new ListView<String>();
        futureApptsViewDate.setItems(futureApptObservDate);
		
        newDateStack.getChildren().add(futureApptsViewDate);
		newTimeStack.getChildren().add(newTime);
		//*** ADD TIMES OF UPCOMING APPOINTMENTS TO THIS STACK *****///
		futureStringTime = new ArrayList<String>();

        for(int i = 0; i < futureApptsDate.size(); i++) {
        	futureStringTime.add(i, futureApptsDate.get(i).getDate().split(" ")[1]);
        }
        	

        futureApptObservTime = FXCollections.observableArrayList(futureStringTime);
        futureApptsViewTime = new ListView<String>();
        futureApptsViewTime.setItems(futureApptObservTime);
        newTimeStack.getChildren().add(futureApptsViewTime);
		
		newDateTime.getChildren().addAll(newDateStack, newTimeStack);
		newDateTime.setMaxWidth(400);
		
		//Initialize scroll pane for upcoming appointments
		upcoming = new ScrollPane();
		upcoming.setMaxWidth(400);
		upcoming.setMaxHeight(800);
		upcoming.setMinHeight(800);
		upcoming.setMinWidth(400);
		upcoming.setContent(newDateTime);
		upcomingStack.getChildren().add(upcoming);
		
		pane.getItems().add(upcomingStack);
		
		//Date and Time columns for Past Appointments
		oldDateStack = new VBox();
		oldDateStack.setMaxWidth(133);
		oldDateStack.setMinWidth(133);
		oldDateStack.setMaxHeight(800);
		//newDateStack.setMinHeight(800);
		oldTimeStack = new VBox();
		oldTimeStack.setMaxWidth(133);
		oldTimeStack.setMinWidth(133);
		oldTimeStack.setMaxHeight(800);
		
		notes = new VBox();
		notes.setMaxWidth(133);
		notes.setMinWidth(133);
		notes.setMaxHeight(800);

		//Headers Past Date
		oldDate = new Label("Date");
		oldDate.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		oldDate.setFont(Font.font("Arial", 18));
		oldDate.setMaxWidth(266);
		oldDate.setMaxHeight(800);
		
		//Past Time
		oldTime = new Label("Time");
		oldTime.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		oldTime.setFont(Font.font("Arial", 18));
		oldTime.setMaxWidth(266);
		
		//Past Notes
		newNotes = new Label("Notes");
		newNotes.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		newNotes.setFont(Font.font("Arial", 18));
		newNotes.setMaxWidth(266);
		
		oldDateStack.getChildren().add(oldDate);
		//*** ADD DATES OF PAST APPOINTMENTS TO THIS STACK *****///
		pastAppts = PatientLogin.patient.getPastAppointments();
        pastString = new ArrayList<String>();

        for(int i = 0; i < pastAppts.size(); i++) {
        	pastString.add(i, pastAppts.get(i).getDate().split(" ")[0]);
        }
        	

        pastApptObserv = FXCollections.observableArrayList(pastString);
        pastApptsView = new ListView<String>();
        pastApptsView.setItems(pastApptObserv);
		
        oldDateStack.getChildren().add(pastApptsView);

		oldTimeStack.getChildren().add(oldTime);
		//*** ADD TIMES OF PAST APPOINTMENTS TO THIS STACK *****///
		pastString = new ArrayList<String>();

        for(int i = 0; i < pastAppts.size(); i++) {
        	pastString.add(i, pastAppts.get(i).getDate().split(" ")[1]);
        }
        	

        pastApptObserv = FXCollections.observableArrayList(pastString);
        pastApptsView = new ListView<String>();
        pastApptsView.setItems(pastApptObserv);
		
        oldTimeStack.getChildren().add(pastApptsView);
		
		notes.getChildren().add(newNotes);
		//*** ADD NOTES OF PAST APPOINTMENTS TO THIS STACK *****///
		notesString = new ArrayList<String>();

        for(int i = 0; i < pastAppts.size(); i++) {
        	notesString.add(i, "Notes");
        }
        	

        notesApptObserv = FXCollections.observableArrayList(notesString);
        notesApptsView = new ListView<String>();
        notesApptsView.setItems(notesApptObserv);
        notes.getChildren().add(notesApptsView);


        //selected = notesAppts.get(notesApptsView.getSelectionModel().getSelectedIndex());
        
        notesApptsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	selected = pastAppts.get(notesApptsView.getSelectionModel().getSelectedIndex());
            	//go to appointment info page when clicked on specific appointment
            	PatientApptNotes root = new PatientApptNotes();
	            Scene scene = new Scene(root, 800,500);
	            Stage stage = new Stage();
	            stage.setScene(scene); // set the scene
	            stage.setTitle("Appointment Info: " );
	            stage.show();
            }
        });        
		
        oldDateTime.getChildren().addAll(oldDateStack, oldTimeStack, notes);
		oldDateTime.setMaxWidth(400);

		//Initialize scroll pane for upcoming appointments
		past = new ScrollPane();
		past.setMaxWidth(400);
		past.setMaxHeight(800);
		past.setMinHeight(800);
		past.setMinWidth(400);
		past.setContent(oldDateTime);
		pastStack.getChildren().add(past);

		pane.getItems().add(pastStack);
		
		this.getItems().add(pane);
		
	}
}
