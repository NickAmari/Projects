package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane; //***
import javafx.scene.layout.StackPane; //***
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.text.*;

public class WelcomePage extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Create a GridPane object and set its properties
		    GridPane pane = new GridPane();
		    pane.setAlignment(Pos.CENTER); //always place the GridPane in the center of scene
		    pane.setPadding(new Insets(11.5, 300, 200, 14.5));
		    pane.setHgap(10); //set horizontal gap between columns
		    pane.setVgap(5.5); //set vertical gap between rows


		    //Create 2 labels
		    Label welcome = new Label("Welcome to T1 Medical!");
		    welcome.setFont(Font.font("Arial", 24));
		    Label prompt = new Label("Are you a patient or a member of staff?");
		    prompt.setFont(Font.font("Arial", 15));

		    //add welcome label at column 0, row 0
		    pane.add(welcome, 0, 0);
		    GridPane.setHalignment(welcome, HPos.LEFT);

		    //add prompt at column 0, row 1
		    pane.add(prompt, 0, 6);
		    GridPane.setHalignment(prompt, HPos.LEFT);

		    //add Patient button
		    Button btPatient = new Button("Patient");
		    btPatient.setMinSize(100, 50);
		    pane.add(btPatient, 4, 20);

		    //add Staff button
		    Button btStaff = new Button("Staff");
		    btStaff.setMinSize(100, 50);
		    pane.add(btStaff, 4, 27);

		    //set button alignment
		    GridPane.setHalignment(btPatient, HPos.RIGHT);
		    GridPane.setHalignment(btStaff, HPos.RIGHT);

		    // Create a scene and place it in the stage
		    Scene scene = new Scene(pane, 800, 500);
		    primaryStage.setTitle("Welcome Page"); // Set the stage title
		    primaryStage.setScene(scene); // Place the scene in the stage
		    primaryStage.show(); // Display the stage



		    //Add action listener to go to PatientLogin page when Patient button is pressed
		    btPatient.setOnAction(e->{
	            //primaryStage.close(); // you can close the first stage from the beginning

	            // create the structure again for the second GUI
	            PatientLogin root2 = new PatientLogin();

	            Scene secondScene = new Scene(root2, 800,500);
	            Stage secondStage = new Stage();
	            secondStage.setScene(secondScene); // set the scene
	            secondStage.setTitle("Patient Login");
	            secondStage.show();
	            primaryStage.close(); // close the first stage (Window)
	        });


		  //Add action listener to go to StaffLogin page when Staff button is pressed
		    btStaff.setOnAction(e->{
	            //primaryStage.close(); // you can close the first stage from the beginning

	            // create the structure again for the second GUI
	            StaffLogin root3 = new StaffLogin();

	            Scene thirdScene = new Scene(root3, 800,500);
	            Stage thirdStage = new Stage();
	            thirdStage.setScene(thirdScene); // set the scene
	            thirdStage.setTitle("Staff Login");
	            thirdStage.show();
	        });

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
