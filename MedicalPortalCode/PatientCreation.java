package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent; //***need to import
import javafx.scene.layout.VBox;

class PatientCreation extends BorderPane {
	//GUI components
	private Label welcome, firstNameLabel, lastNameLabel, DOBLabel;
	private TextField firstNameTextField, lastNameTextField;
	private Button enter;
	private DatePicker DOBPicker;
	private GridPane grid;
	private VBox vbox;
	private int countError;
	
	public PatientCreation() {
		
		BorderPane pane = new BorderPane();
		countError = 0;
		// Create a GridPane object and set its properties
	    grid = new GridPane();
	    grid.setAlignment(Pos.TOP_CENTER); //always place the GridPane in the center of scene
	    grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	    grid.setHgap(10); //set horizontal gap between columns
	    grid.setVgap(5.5); //set vertical gap between rows
	    
	    
	    //Create Welcome Label
	    welcome = new Label("Welcome to T1 Medical!");
	    welcome.setFont(Font.font("Arial", 24));
	    //add welcome label at column 0, row 0
	    pane.setTop(welcome);
	    
	    //Create First name text box label
	    firstNameLabel = new Label("First name:");
	    firstNameLabel.setFont(Font.font("Arial", 15));
	    //add first name label at column 1, row 10
	    grid.add(firstNameLabel, 0, 0);
	    GridPane.setHalignment(firstNameLabel, HPos.LEFT);
	    
	    //Create Last name text box label
	    lastNameLabel = new Label("Last name:");
	    lastNameLabel.setFont(Font.font("Arial", 15));
	    //add last name label at column 1, row 12
	    grid.add(lastNameLabel, 0, 1);
	    
	    //Create Last name text box label
	    DOBLabel = new Label("Date of Birth:");
	    DOBLabel.setFont(Font.font("Arial", 15));
	    //add DOB label at column 1, row 14
	    grid.add(DOBLabel, 0, 2);

	    //Create text box for firstNameLabel
	    firstNameTextField = new TextField();
	    firstNameTextField.setPromptText("e.g John");
	    //add text box next to first name 
	    grid.add(firstNameTextField, 1, 0);
	    
	    //Create text box for last name
	    lastNameTextField = new TextField();
	    lastNameTextField.setPromptText("e.g Doe");
	    //add text box next to last name 
	    grid.add(lastNameTextField, 1, 1);
	    
	    //Create text box for dob
	    DOBPicker = new DatePicker();
	    DOBPicker.setPromptText("e.g 01/01/2000");
	    //add text box next to dob
	    grid.add(DOBPicker, 1, 2);
	    
	    //Add a button to submit information and login
	    enter = new Button("Enter");
	    enter.setMinSize(70, 20);
	    grid.add(enter, 0, 3);
	    
	    vbox = new VBox();
	    vbox.getChildren().add(grid);
	    pane.setCenter(vbox);
	    vbox.setAlignment(Pos.CENTER);
	    BorderPane.setAlignment(vbox, Pos.TOP_CENTER);
	    //Add action listener to go to PatientCreate page page when create an account hyperlink is pressed
	    enter.setOnAction(e->{
	    	//primaryStage.close(); // you can close the first stage from the beginning

	    	if(firstNameTextField.getText().equals(null) || lastNameTextField.getText().equals(null) || DOBPicker.getValue() == null) {
	    		if (countError == 0) {
	    			Label error = new Label("**Please complete all fields before continuing.");
	    			error.setTextFill(Color.RED);
	    			vbox.getChildren().add(error);
	    			pane.setCenter(vbox);
	    			countError ++;
	    		}
	    			
	    	}
	    	else {
	    		PatientLogin.firstName = firstNameTextField.getText();
	    		PatientLogin.lastName = lastNameTextField.getText();
	    		PatientLogin.DOB = DOBPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    		PatientLogin.patient = new Patient(PatientLogin.firstName, PatientLogin.lastName, PatientLogin.DOB , false);
	    		 
	    		

	    		 // create the structure again for the second GUI
		    	PatientCreateAccountForm root3 = new PatientCreateAccountForm();
		    	Stage nextStage = new Stage();
	            Scene nextScene = new Scene(root3, 800,500);
	            nextStage = new Stage();
	            nextStage.setScene(nextScene); // set the scene
	            nextStage.setTitle("Patient Login");
	            nextStage.show();
	    	}
	    	 
	    	 
	    	
        });
	    
	    this.setCenter(pane);
	}

}
