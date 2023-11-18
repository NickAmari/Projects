package application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Assignment3 extends BorderPane{
	private Label title, firstNameL, lastNameL, customerIDL, phoneNumberL, addressL, saveAcctL, checkAcctL;
	public TextField firstNameT, lastNameT, customerIDT, phoneNumberT, addressT, saveAcctT, checkAcctT;
	public Button save, cancel;
	private GridPane gridPane;
	private HBox footer;
	
	public Assignment3() {
		BorderPane contain = new BorderPane();
		
		contain.setPrefSize(800, 500);
		title = new Label("Credit Card Application Form for SunDevil Bank:");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		contain.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);
		//grid pane
		gridPane = new GridPane();
		
		//Labels
		firstNameL = new Label("First Name: ");
		lastNameL = new Label("Last Name: ");
		customerIDL = new Label("Customer ID: ");
		phoneNumberL = new Label("Phone Number: ");
		addressL = new Label("Address: ");
		saveAcctL = new Label("Savings Account Number: ");
		checkAcctL = new Label("Checking Account Number: ");
		
		//Create text fields
		firstNameT = new TextField();
		lastNameT = new TextField();
		customerIDT = new TextField();
		phoneNumberT = new TextField();
		addressT = new TextField();
		saveAcctT = new TextField();
		checkAcctT = new TextField();
		//Add Text fields
		gridPane.add(firstNameT, 1, 0);
		gridPane.add(customerIDT, 1, 1);
		gridPane.add(addressT, 1, 2);
		gridPane.add(saveAcctT, 1, 5);
		gridPane.add(lastNameT, 3, 0);
		gridPane.add(phoneNumberT, 3, 1);
		gridPane.add(checkAcctT, 3, 5);
		gridPane.setHgap(40);
	    gridPane.setVgap(40);	
	    //Add Labels to grid
		gridPane.add(firstNameL, 0, 0);
		gridPane.add(customerIDL, 0, 1);
		gridPane.add(addressL, 0, 2);
		gridPane.add(saveAcctL, 0, 5);
		gridPane.add(lastNameL, 2, 0);
		gridPane.add(phoneNumberL, 2, 1);
		gridPane.add(checkAcctL, 2, 5);
		
	    //add to border pane
	    contain.setCenter(gridPane);
	    BorderPane.setMargin(gridPane, new Insets(25));
	    
	    //create footer to hold buttons
	    footer = new HBox(150);
	    
	    //create buttons and add to footer
	    cancel = new Button("Cancel");
	    save = new Button("Save");
	    
	    footer.getChildren().addAll(cancel, save);
	    
	    //add to pane
	    contain.setBottom(footer);
	    BorderPane.setMargin(footer, new Insets(175));
	    BorderPane.setAlignment(footer, Pos.TOP_CENTER);
	    
	    cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				firstNameT.clear();
				lastNameT.clear();
				customerIDT.clear();
				phoneNumberT.clear();
				addressT.clear();
				saveAcctT.clear();
				checkAcctT.clear();
			}
	    	
	    });
	    
	    save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String first_name = firstNameT.getText();
				String last_name = lastNameT.getText();
				String ID = customerIDT.getText();
				String number = phoneNumberT.getText();
				String address = addressT.getText();
				String saveAcct = saveAcctT.getText();
				String checkAcct = checkAcctT.getText();
				if (ID.length() > 4) {
					Text t = new Text();
					t.setText("Customer ID must be length of 4");
					t.setFont(Font.font("Verdana", 10));
					t.setFill(Color.RED);
					footer.getChildren().add(t);
					customerIDT.clear();
				}
				else {
					//save the shit
					try {
						BufferedWriter writer = new BufferedWriter( new FileWriter("C:\\Application.txt"));
						System.out.print("Saved to C:\\");
						String output = first_name + " " + last_name + " " + ID + " " + number + " " + address + " " + saveAcct + " " + checkAcct;
						writer.write(output);
						writer.close();
					}
					catch (IOException e) {

						e.printStackTrace();
					}
				}
				
			}
	    	
	    	
	    });
	    
	    this.setCenter(contain);
	}
}
