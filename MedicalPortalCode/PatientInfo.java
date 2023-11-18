package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class PatientInfo extends VBox{
	
	private GridPane intialPatientInfo, finalPatientInfo;
	private Label fN, lN, dob, phone, addr1, addr2, email, insur, memID, grpNum, pharmName, pharmAddr, zipPat,zipcodePatient, zipInsur, zipcodePharm,healthHist, prescription, immunHist, firstName, lastName, phoneNum, addressLine1, addressLine2, emailAddr, healthHistText, prescriptionText, immunHistText, insurText, memIDText, grpNumText,pharmNameText, pharmAddrText, dobDatePicker, statePatientLabel, statePharmLabel, statePatient, statePharm;
	
	
	public PatientInfo() {
		
		//Initialize pane to put the whole patient information page on 
		VBox pane = new VBox();

		//create first GridPane
		intialPatientInfo = new GridPane();
		//intialPatientInfo.setAlignment(Pos.CENTER); //always place the GridPane in the center of scene
		intialPatientInfo.setPadding(new Insets(10, 10, 10, 10));
		intialPatientInfo.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		intialPatientInfo.setVgap(10); //vertical gap in pixels
		
		//Create labels for first GridPane
		fN = new Label("First Name: ");
		lN = new Label("Last Name: ");
		dob = new Label("Date of Birth: ");
		phone = new Label("Phone #: ");
		addr1 = new Label("Address Line 1: ");
		addr2 = new Label("Address Line 2:");
		email = new Label("E-mail Address: ");
		zipPat = new Label("Zip: ");
		statePatient = new Label(PatientLogin.patient.getAddressState());
		
		//Add labels to first GridPane
		intialPatientInfo.add(fN, 0, 0);
		intialPatientInfo.add(lN, 0, 1);
		intialPatientInfo.add(dob, 0, 2);
		intialPatientInfo.add(phone, 0, 3);
		intialPatientInfo.add(addr1, 3, 0);
		intialPatientInfo.add(addr2, 3, 1);
		intialPatientInfo.add(email, 3, 3);
		intialPatientInfo.add(zipPat, 4, 2);
		
		//Create Textfields and datepicker for first GridPane
		firstName = new Label( PatientLogin.patient.getFirstName() );
		lastName = new Label( PatientLogin.patient.getLastName() );
		phoneNum = new Label(String.valueOf(PatientLogin.patient.getPhoneNumber()));
		addressLine1 = new Label( PatientLogin.patient.getAddressLine1() );
		addressLine2 = new Label( PatientLogin.patient.getAddressLine2() );
		dobDatePicker = new Label( PatientLogin.patient.getBirthDate() );
		emailAddr = new Label( PatientLogin.patient.getEmail() );
		zipcodePatient = new Label(String.valueOf(PatientLogin.patient.getAddressZip()));
		
		//Add text field to first GridPane
		intialPatientInfo.add(firstName, 1, 0);
		intialPatientInfo.add(lastName, 1, 1);
		intialPatientInfo.add(dobDatePicker, 1, 2);
		intialPatientInfo.add(phoneNum, 1, 3);
		intialPatientInfo.add(addressLine1, 4, 0);
		intialPatientInfo.add(statePatient, 5, 0);
		intialPatientInfo.add(addressLine2, 4, 1);
		intialPatientInfo.add(emailAddr, 4, 3);
		intialPatientInfo.add(zipcodePatient, 5, 2);
		
		//add first GridPane to VBox
		pane.getChildren().add(intialPatientInfo);
		
		//Create center Labels and text fields and add to VBox
		healthHist = new Label("Health History:");
		pane.getChildren().add(healthHist);
		healthHistText = new Label(PatientLogin.patient.getHealthHistory() );
		healthHistText.setPrefWidth(800);
		healthHistText.setPrefHeight(80);
		healthHistText.setMaxWidth(800);
		healthHistText.setMaxHeight(800);
		pane.getChildren().add(healthHistText);
		
		//Create center Labels and text fields and add to VBox
		prescription = new Label("Prescribed Medication History:");
		pane.getChildren().add(prescription);
		prescriptionText = new Label( PatientLogin.patient.getMedHistory() );
		prescriptionText.setPrefWidth(800);
		prescriptionText.setPrefHeight(80);
		prescriptionText.setMaxWidth(800);
		prescriptionText.setMaxHeight(800);
		pane.getChildren().add(prescriptionText );
		
		//Create center Labels and text fields and add to VBox
		immunHist = new Label("Immunization History:");
		pane.getChildren().add(immunHist);
		immunHistText = new Label( PatientLogin.patient.getImmuneHistory() );
		immunHistText.setPrefWidth(800);
		immunHistText.setPrefHeight(80);
		immunHistText.setMaxWidth(800);
		immunHistText.setMaxHeight(800);
		pane.getChildren().add(immunHistText );
		
		
		//Create labels for second gridPane
		insur = new Label("Insurance Carrier: ");
		memID = new Label("Member ID: ");
		grpNum = new Label("Group Number: ");
		pharmName = new Label("Pharmacy Name: ");
		pharmAddr = new Label("Pharmacy Address: ");
		zipcodePharm = new Label("Zipcode: ");
		
		
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
		finalPatientInfo.add(zipcodePharm, 3, 2);



		//Create Textfields for second GridPane
		insurText = new Label(PatientLogin.patient.getInsuranceCompany());
		memIDText = new Label( PatientLogin.patient.getInsuranceID() );
		grpNumText = new Label( PatientLogin.patient.getInsuranceGroup() );
		pharmNameText= new Label( PatientLogin.patient.getPharmacyName() );
		pharmAddrText = new Label( PatientLogin.patient.getPharmacyAddress() );
		zipInsur = new Label( String.valueOf(PatientLogin.patient.getPharmacyZip()));
		statePharm = new Label(PatientLogin.patient.getPharmacyState());

		//Add labels to second GridPane
		finalPatientInfo.add(insurText, 1, 0);
		finalPatientInfo.add(memIDText, 1, 1);
		finalPatientInfo.add(grpNumText, 1, 2);
		finalPatientInfo.add(pharmNameText, 4, 0);
		finalPatientInfo.add(pharmAddrText, 4, 1);
		finalPatientInfo.add(zipInsur, 4, 2);
		finalPatientInfo.add(statePharm, 5, 1);
		
		pane.getChildren().add(finalPatientInfo);
		
		this.getChildren().add(pane);
	}
	
	
}
