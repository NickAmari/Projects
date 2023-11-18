package application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class StaffLogin extends GridPane{
	//GUI components
		private Label welcome, username, password;
		private TextField UN, PW;
		private Button login;
		public static Staff st;
		//private GridPane pane;

		public StaffLogin() {

			// Create a GridPane object and set its properties
		    GridPane pane = new GridPane();
		    pane.setAlignment(Pos.CENTER); //always place the GridPane in the center of scene
		    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		    pane.setHgap(10); //set horizontal gap between columns
		    pane.setVgap(5.5); //set vertical gap between rows


		    //Create Welcome Label
		    welcome = new Label("Welcome to T1 Medical!");
		    welcome.setFont(Font.font("Arial", 24));
		    //add welcome label at column 0, row 0
		    pane.add(welcome, 0, 0);
		    GridPane.setHalignment(welcome, HPos.LEFT);

		    //Create First name text box label
		    username = new Label("Username:");
		    username.setFont(Font.font("Arial", 15));
		    //add username label at column 1, row 10
		    pane.add(username, 1, 10);
		    GridPane.setHalignment(username, HPos.LEFT);

		    //Create Last name text box label
		    password = new Label("Password:");
		    password.setFont(Font.font("Arial", 15));
		    //add password label at column 1, row 12
		    pane.add(password, 1, 12);

		    //Create text box for username
		    UN = new TextField();
		    //add text box next to username
		    pane.add(UN, 2, 10);

		    //Create text box for password
		    PW = new TextField();
		    //add text box next to last name
		    pane.add(PW, 2, 12);

		    //Add a button to submit information and login
		    login = new Button("Login");
		    login.setMinSize(70, 20);
		    pane.add(login, 1, 16);

		    this.add(pane, 0, 0);

			//Add action listener to go to StaffHome when login button is pressed
			login.setOnAction(e->{
					st = new Staff(" ", " ", UN.getText(), PW.getText(),true, 0, true);
					StaffView root = new StaffView();
					Scene scene = new Scene(root, 800,500);
					Stage stage = new Stage();
					stage.setScene(scene); // set the scene
					stage.setTitle("Staff View");
					stage.show();
			});
		}
}