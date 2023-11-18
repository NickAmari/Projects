package application;
//home page for Doctor and Nurse

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;

public class StaffView extends BorderPane
{
	//GUI components
	//GUI has a Calendar, Patient List, and Message Tab
	private StaffCalendar cal;
	private PatientList pList;
	private StaffMessaging msg;
	private TabPane tabPane;
	private Label header;

	public StaffView()
	{
		//creation of main pane
		BorderPane pane = new BorderPane();
		BorderPane helper = new BorderPane();

		//label for the header of the file
		header = new Label("T1 Medical");
		helper.setLeft(header);

		//initialize the tabpane
		tabPane = new TabPane();

		//creation of tabs in the tabpane
		Tab calendar = new Tab();	//the calendar tab
		calendar.setText("Calendar");
		tabPane.getTabs().add(calendar);

		Tab patientList = new Tab();	//the patient list tab
		patientList.setText("Patient List");
		tabPane.getTabs().add(patientList);

		Tab message = new Tab();	//the messaging portal tab
		message.setText("Messaging");
		tabPane.getTabs().add(message);

		helper.setBottom(tabPane);

		pane.setTop(helper);

		//what is displayed on the screen when a tab is selected
		if(calendar.isSelected())
		{
			cal = new StaffCalendar();
			pane.setCenter(cal);
		}

		//allows the user to switch between the tabs 
		calendar.setOnSelectionChanged (e -> {
			if (calendar.isSelected())
				cal = new StaffCalendar();
			pane.setCenter(cal);
		}
				);

		patientList.setOnSelectionChanged (e -> {
			if (patientList.isSelected())
				pList = new PatientList();
			pane.setCenter(pList);
		}
				);

		message.setOnSelectionChanged (e -> {
			if (message.isSelected())
				msg = new StaffMessaging();
			pane.setCenter(msg);
		}
				);

		this.setCenter(pane);

	}


}