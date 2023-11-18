package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;


public class PatientCreateAccountForm extends VBox {
	private GridPane finalPatientInfo;
	private Label fN, lN, dob, phone, addr1, addr2, email, insur, memID, grpNum, pharmName, pharmAddr, zipPat, healthHist, prescription, immunHist, zipPharm, firstName, lastName, dobDate;
	private GridPane intialPatientInfo;
	private TextField phoneNum, addressLine1, addressLine2, zipcodePatientAddress, emailAddr, healthHistText, prescriptionText, immunHistText, insurText, memIDText, grpNumText,pharmNameText, pharmAddrText, zipcodePharm;
	private Button save;

	private ComboBox<String> statesPatient, statesPharm;



	public PatientCreateAccountForm() {

		//Initialize pane to put the whole patient information page on 
		VBox pane = new VBox();

		//create first GridPane
		intialPatientInfo = new GridPane();
		//intialPatientInfo.setAlignment(Pos.CENTER); //always place the GridPane in the center of scene
		intialPatientInfo.setPadding(new Insets(10, 10, 10, 10));
		intialPatientInfo.setHgap(5); //horizontal gap in pixels => that's what you are asking for
		intialPatientInfo.setVgap(10); //vertical gap in pixels

		//Create State Combo Box
		statesPatient = new ComboBox<String>();
		statesPatient.setPromptText("State");
		statesPatient.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
				"FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
				"LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
				"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK",
				"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");

		//Create labels for first GridPane
		fN = new Label("First Name: ");
		lN = new Label("Last Name: ");
		dob = new Label("Date of Birth: ");
		phone = new Label("Phone #: ");
		addr1 = new Label("Address Line 1: ");
		addr2 = new Label("Address Line 2:");
		email = new Label("E-mail Address: ");
		zipPat = new Label("Zipcode: ");


		//Add labels to first GridPane
		intialPatientInfo.add(fN, 0, 0);
		intialPatientInfo.add(lN, 0, 1);
		intialPatientInfo.add(dob, 0, 2);
		intialPatientInfo.add(phone, 0, 3);
		intialPatientInfo.add(addr1, 4, 0);
		intialPatientInfo.add(addr2, 4, 1);
		intialPatientInfo.add(statesPatient, 6, 2);
		intialPatientInfo.add(email, 4, 3);
		intialPatientInfo.add(zipPat, 4, 2);

		//Create Textfields and labels for first GridPane
		firstName = new Label(PatientLogin.firstName);
		lastName = new Label(PatientLogin.lastName);
		phoneNum = new TextField();
		phoneNum.setPromptText("Enter your phone number here");
		addressLine1 = new TextField();
		addressLine1.setPromptText("Enter your address here");
		addressLine2 = new TextField();
		addressLine2.setPromptText("Enter your second line of address here (if applicable)");
		dobDate = new Label(PatientLogin.DOB);
		emailAddr = new TextField();
		emailAddr.setPromptText("Enter your email address here");
		zipcodePatientAddress = new TextField();
		zipcodePatientAddress.setPromptText("Enter your zipcode here");


		//Add text field to first GridPane
		intialPatientInfo.add(firstName, 1, 0);
		intialPatientInfo.add(lastName, 1, 1);
		intialPatientInfo.add(dobDate, 1, 2);
		intialPatientInfo.add(phoneNum, 1, 3);
		intialPatientInfo.add(addressLine1, 5, 0);
		intialPatientInfo.add(addressLine2, 5, 1);
		intialPatientInfo.add(emailAddr, 5, 3);
		intialPatientInfo.add(zipcodePatientAddress, 5, 2);

		//add first GridPane to VBox
		pane.getChildren().add(intialPatientInfo);

		//Create center Labels and text fields and add to VBox
		healthHist = new Label("Health History:");
		healthHist.setPadding(new Insets(10));

		pane.getChildren().add(healthHist);
		healthHistText = new TextField();
		healthHistText.setPrefWidth(750);
		healthHistText.setPrefHeight(80);
		healthHistText.setMaxWidth(750);
		healthHistText.setMaxHeight(800);
		healthHistText.setPadding(new Insets(10));
		pane.getChildren().add(healthHistText);

		//Create center Labels and text fields and add to VBox
		prescription = new Label("Prescribed Medication History:");
		prescription.setPadding(new Insets(10));

		pane.getChildren().add(prescription);
		prescriptionText = new TextField();
		prescriptionText.setPrefWidth(750);
		prescriptionText.setPrefHeight(80);
		prescriptionText.setMaxWidth(750);
		prescriptionText.setMaxHeight(800);
		prescriptionText.setPadding(new Insets(10));
		pane.getChildren().add(prescriptionText );

		//Create center Labels and text fields and add to VBox
		immunHist = new Label("Immunization History:");
		immunHist.setPadding(new Insets(10));


		pane.getChildren().add(immunHist);
		immunHistText = new TextField();
		immunHistText = new TextField();
		immunHistText.setPrefWidth(750);
		immunHistText.setPrefHeight(80);
		immunHistText.setMaxWidth(750);
		immunHistText.setMaxHeight(800);
		immunHistText.setPadding(new Insets(10));
		pane.getChildren().add(immunHistText );


		//Create State Combo Box
		statesPharm = new ComboBox<String>();
		statesPharm.setPromptText("State");
		statesPharm.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
				"FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
				"LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
				"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK",
				"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");



		//Create labels for second gridPane
		insur = new Label("Insurance Carrier: ");
		memID = new Label("Member ID: ");
		grpNum = new Label("Group Number: ");
		pharmName = new Label("Pharmacy Name: ");
		pharmAddr = new Label("Pharmacy Address: ");
		zipPharm = new Label("Zipcode: ");


		finalPatientInfo = new GridPane();
		finalPatientInfo.setPadding(new Insets(10, 10, 10, 10));
		finalPatientInfo.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		finalPatientInfo.setVgap(10); //vertical gap in pixels

		//Add labels to second GridPane
		finalPatientInfo.add(insur, 0, 0);
		finalPatientInfo.add(memID, 0, 1);
		finalPatientInfo.add(grpNum, 0, 2);
		finalPatientInfo.add(pharmName, 3, 0);
		finalPatientInfo.add(pharmAddr, 3, 1);
		finalPatientInfo.add(statesPharm, 5, 2);
		finalPatientInfo.add(zipPharm, 3, 2);


		//Create Text fields for second GridPane
		insurText = new TextField();
		insurText.setPromptText("Enter insurance carrier here");
		memIDText = new TextField();
		memIDText.setPromptText("Enter member ID here");
		grpNumText = new TextField();
		grpNumText.setPromptText("Enter group number here");
		pharmNameText= new TextField();
		pharmNameText.setPromptText("Enter your preferred pharmacy's name here");
		pharmAddrText = new TextField();
		pharmAddrText.setPromptText("Enter your preferred pharmacy's address here");
		zipcodePharm = new TextField();
		zipcodePharm.setPromptText("Enter your preferred pharmacy's zipcode here");

		//Add labels to second GridPane
		finalPatientInfo.add(insurText, 1, 0);
		finalPatientInfo.add(memIDText, 1, 1);
		finalPatientInfo.add(grpNumText, 1, 2);
		finalPatientInfo.add(pharmNameText, 4, 0);
		finalPatientInfo.add(pharmAddrText, 4, 1);
		finalPatientInfo.add(zipcodePharm, 4, 2);


		//Create Save and Continue button
		save = new Button("Save and Continue");
		finalPatientInfo.add(save, 6, 2);

		pane.getChildren().add(finalPatientInfo);

		save.setOnAction(e->{
			//Get all the text from the patient creating their account

			//Cannot parse a null
			if (phoneNum.getText() != null || !phoneNum.getText().equals("")) {
				//int phonenumber = Integer.parseInt(phoneNum.getText());
				PatientLogin.patient.setPhoneNumber(Integer.parseInt(phoneNum.getText()));
			}
			else {
				
			}
			//Cannot parse a null
			if (zipcodePatientAddress.getText() != null || !zipcodePatientAddress.getText().equals("")) {
				PatientLogin.patient.setAddressZip(Integer.parseInt(zipcodePatientAddress.getText()));
			}
			else {
				
			}
			//Cannot parse a null
			if (zipcodePharm.getText() != null || !zipcodePharm.getText().equals("")) {
				PatientLogin.patient.setPharmacyZip(Integer.parseInt(zipcodePharm.getText()));
			}
			else {
				
			}
			
			PatientLogin.patient.setEmail(emailAddr.getText());
			PatientLogin.patient.setAddressLine1(addressLine1.getText());
			PatientLogin.patient.setAddressLine2(addressLine2.getText());
			PatientLogin.patient.setAddressState(statesPatient.getValue());
			PatientLogin.patient.setInsurance(insur.getText());
			PatientLogin.patient.setInsuranceID(memID.getText());
			PatientLogin.patient.setInsuranceGroup(grpNum.getText());
			PatientLogin.patient.setPharmName(pharmNameText.getText());
			PatientLogin.patient.setPharmacyAddress(pharmAddrText.getText());
			PatientLogin.patient.setPharmacyState(statesPharm.getValue());
			PatientLogin.patient.setHealthHistory(healthHistText.getText());
			PatientLogin.patient.setMedHistory(prescriptionText.getText());
			PatientLogin.patient.setImmuneHistory(immunHistText.getText());




			// create the structure again for the second GUI
			PatientView root3 = new PatientView();
			Stage nextStage = new Stage();
			Scene nextScene = new Scene(root3, 800,500);
			nextStage = new Stage();
			nextStage.setScene(nextScene); // set the scene
			nextStage.setTitle("Patient Login");
			nextStage.show();




		});

		this.getChildren().add(pane);
	}


}


