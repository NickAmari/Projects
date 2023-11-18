package application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//This program draw fixed size yellow dots on screen
//It also show a counter label
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter; 

class PatientLogin extends BorderPane {
	//GUI components
	private Label welcome, firstNameLabel, lastNameLabel, DOBLabel, question;
	public Hyperlink newPatient;
	private TextField firstNameTextField, lastNameTextField;
	private DatePicker DOBPicker;
	private Button login;
	private Stage nextStage ;
	private GridPane grid;
	private VBox vbox;
	private int countError;
	
	
	public static String firstName, lastName, DOB;
	public static Patient patient;
	
	public PatientLogin() {
		
		
		BorderPane pane = new BorderPane();
		countError = 0;
		// Create a GridPane object and set its properties
	    grid = new GridPane();
	    grid.setAlignment(Pos.TOP_CENTER); //always place the Gridgrid in the center of scene
	    grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	    grid.setHgap(10); //set horizontal gap between columns
	    grid.setVgap(3); //set vertical gap between rows
	    
	    
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

	    //Create text box for firstname
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
	    //add text box next to dob
	    grid.add(DOBPicker, 1, 2);
	    
	    //Create question label that asks if they are a new patient
	    question = new Label("Are you a new patient? ");
	    question.setFont(Font.font("Arial", 15));
	    grid.add(question, 0, 3);
	    
	    //Prompt new patients to make a new account
	    newPatient = new Hyperlink("Create a new account!");
	    newPatient.setFont(Font.font("Arial", 15));
	    newPatient.setUnderline(true);
	    grid.add(newPatient, 1, 3);
	    
	    //Add a button to submit information and login
	    login = new Button("Login");
	    login.setMinSize(70, 20);
	    grid.add(login, 0, 4);
	    
	    vbox = new VBox();
	    vbox.getChildren().add(grid);
	    vbox.setAlignment(Pos.CENTER);
	    pane.setCenter(vbox);
	    BorderPane.setAlignment(vbox, Pos.TOP_CENTER);
	    //Add action listener to go to PatientCreate page page when create an account hyperlink is pressed
	    newPatient.setOnAction(e->{
	    	//primaryStage.close(); // you can close the first stage from the beginning

	    	// create the structure again for the second GUI
	    	PatientCreation root3 = new PatientCreation();
	   
            Scene nextScene = new Scene(root3, 800,500);
            nextStage = new Stage();
            nextStage.setScene(nextScene); // set the scene
            nextStage.setTitle("Patient Login");
            nextStage.show();
        });
	    
	 
	    //Add action listener to go to PatientCreate page page when create an account hyperlink is pressed
	    login.setOnAction(e->{

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
	    		firstName = firstNameTextField.getText();
	    		lastName = lastNameTextField.getText();
	    		DOB = DOBPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    	    patient = new Patient(firstName, lastName, DOB, true);
	    		
	    	    firstNameTextField.clear();
	    	    lastNameTextField.clear();
	    	    
	    		// create the structure again for the second GUI
	    		PatientView root3 = new PatientView();

	    		Scene nextScene = new Scene(root3, 800,500);
	    		Stage nextStage2 = new Stage();
	    		nextStage2.setScene(nextScene); // set the scene
	    		nextStage2.setTitle("Patient Info");
	    		nextStage2.show();
	    	}

	    });


	    
	    
	    this.setCenter(pane);
	}

}
