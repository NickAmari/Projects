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


public class StaffMessaging extends BorderPane{

	private TextField sendMessageTextField;
	private Button sendMessageButton;

	private ArrayList<Patient> p;
	private ArrayList<String> pString;
	private ObservableList<String> pO;
 	private ListView<String> pList;
 	private Patient selectedP;

 	private ArrayList<Message> m;
 	private ArrayList<String> mString;
 	private ObservableList<String> mO;
	private ListView<String> mList;

	private HBox footer;
	private ScrollPane scrollP, scrollM;

	public StaffMessaging(){
		//initialize main pane
		BorderPane pane = new BorderPane();

	//patients scroll pane

		p = StaffLogin.st.getPatients();

		pString = new ArrayList<String>();

    	for(int i = 0; i < p.size(); i++)
    		pString.add(i, p.get(i).getFirstName() + " " +  p.get(i).getLastName());

		pO = FXCollections.observableArrayList(pString);
		pList = new ListView<String>();
		pList.setItems(pO);

		scrollP = new ScrollPane();
		scrollP.setContent(pList);
		this.setLeft(scrollP);

	//messages scroll pane

		mList = new ListView<String>();

		pList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	selectedP = p.get(pList.getSelectionModel().getSelectedIndex());
            	m = selectedP.getMessages();
            	mString = new ArrayList<String>();

            	for(int i = 0; i < m.size(); i++)
            	{
            		if(m.get(m.size()-1-i).getSender() == 0)
            			mString.add(i, "Patient: " + m.get(m.size()-1-i).getMessage());
            		else if (m.get(i).getSender() == 1)
            			mString.add(i, "Nurse: " + m.get(m.size()-1-i).getMessage());
            		else
            			mString.add(i, "Doctor: " + m.get(m.size()-1-i).getMessage());
            	}


            	mO = FXCollections.observableArrayList(mString);
            	mList = new ListView<String>();
            	mList.setItems(mO);

            	scrollM = new ScrollPane();
        		scrollM.setContent(mList);
        		pane.setCenter(scrollM);

        	//send message interface

        		footer = new HBox();

        		sendMessageTextField = new TextField();
        		sendMessageTextField.setPromptText("Type message to patient here");

        		sendMessageButton = new Button("Send");

        		footer.getChildren().addAll(sendMessageTextField, sendMessageButton);
        		pane.setBottom(footer);

        		sendMessageButton.setOnAction(e->{
        			StaffLogin.st.createMessage(sendMessageTextField.getText(), selectedP.getPatientID(), medicalMain.getCurrentDateTime());

        			if (selectedP.getMessages().get(0).getSender() == 2) {
        				mO.add(mO.size(), "Doctor: " + selectedP.getMessages().get(0).getMessage());
        				System.out.println("Doctor: " + selectedP.getMessages().get(0).getMessage());
        			}

        			else if(selectedP.getMessages().get(0).getSender() == 1) {
        				mO.add(mO.size(), "Nurse: " + selectedP.getMessages().get(0).getMessage());
        				System.out.println("Nurse: " + selectedP.getMessages().get(0).getMessage());
        			}

        			else {
        				mO.add(mO.size(), "Patient: " + selectedP.getMessages().get(0).getMessage());
        				System.out.println("Patient: " + selectedP.getMessages().get(0).getMessage());
        			}

        			mList.setItems(mO);
        		});
            }
        });
		this.setCenter(pane);
	}


}