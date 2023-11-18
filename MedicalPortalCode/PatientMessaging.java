package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ListView;
import java.util.ArrayList;

//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message_1_0;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientMessaging extends BorderPane{

	private TextField sendMessageTextField;
	private Button sendMessageButton;
	private VBox messageStack;
	private HBox footer;
	private ScrollPane scrollPane;

	private ArrayList<Message> m;
 	private ArrayList<String> mString;
 	private ObservableList<String> mO;
	private ListView<String> mList;

	public PatientMessaging(){
		//initialize main pane
		BorderPane pane = new BorderPane();

		//create vertical stack to hold messages
		messageStack = new VBox();

		//scroll pane to hold message stack
		scrollPane = new ScrollPane();
		mList = new ListView<String>();



		m = PatientLogin.patient.getMessages();
		mString = new ArrayList<String>();

		for(int i = 0; i < m.size(); i++)
		{
			if(m.get(m.size()-1-i).getSender() == 0) {
				mString.add(i, "Patient: " + m.get(m.size()-1-i).getMessage());
				//System.out.println("Patient: " + m.get(m.size()-1-i).getMessage());
			}

    		else if (m.get(i).getSender() == 1) {
    			mString.add(i, "Nurse: " + m.get(m.size()-1-i).getMessage());
    			//System.out.println("Nurse: " + m.get(m.size()-1-i).getMessage());
    		}

    		else {
    			mString.add(i, "Doctor: " + m.get(m.size()-1-i).getMessage());
    			//System.out.println("Doctor: " + m.get(m.size()-1-i).getMessage());
    		}
		}

		mO = FXCollections.observableArrayList(mString);
		mList = new ListView<String>();
		mList.setItems(mO);

		scrollPane.setContent(mList);
		pane.setCenter(scrollPane);




		//create hbox to hold button and text box at the bottom of the pane
		footer = new HBox();

		sendMessageTextField = new TextField();
		sendMessageTextField.setPromptText("Type message to staff here");
		sendMessageTextField.setPrefWidth(750);
		sendMessageTextField.setPrefHeight(80);
		sendMessageTextField.setMaxWidth(750);
		sendMessageTextField.setMaxHeight(800);

		sendMessageButton = new Button("Send");

		footer.getChildren().addAll(sendMessageTextField, sendMessageButton);
		pane.setBottom(footer);

		sendMessageButton.setOnAction(e->{
			Message newMessage = new Message(sendMessageTextField.getText(), PatientLogin.patient.getPatientID(), medicalMain.getCurrentDateTime(), 0);

			if (PatientLogin.patient.getMessages().get(0).getSender() == 0)
				mO.add(mO.size(), "Patient :" + PatientLogin.patient.getMessages().get(0).getMessage());
			else if(PatientLogin.patient.getMessages().get(0).getSender() == 1)
				mO.add(mO.size(), "Nurse :" + PatientLogin.patient.getMessages().get(0).getMessage());
			else
				mO.add(mO.size(), "Doctor :" + PatientLogin.patient.getMessages().get(0).getMessage());
			mList.setItems(mO);
		});

		this.setCenter(pane);

	}


}
