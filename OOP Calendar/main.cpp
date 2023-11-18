#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include "Calendar.h"
#include "Day.h"
#include "Reminder.h"
#include "Appointment.h"

using namespace std;

int main(int argc,char* argv[]) {

	int choice;
	string calendarTitle;
	int calendarDays;
	int startingDay;
	string fileName;
	ofstream newFile;

	//Main menu sequence
	cout << "Calender Maker Main Menu:" << endl;
	cout << "1. Build a new Calendar\n2. Exit" << endl;
	cin >> choice;
	if ( (choice == 2) ) {
		return 0;
	}
	if ( (choice == 1) ) {
		cout << "Building a new calendar:" << endl;
		cout << "Enter a title for your calendar" << endl;
		cin >> calendarTitle;
		
		cout << "How many days should this calendar have?" << endl;
		cin >> calendarDays;

		cout << "What day does your calendar start on? (Enter an integer)" << endl;
		cout << "0 = Sunday, 1 = Monday, ... 5 = Friday, 6 = Saturday" << endl;
		cin >> startingDay;
	}

	//creating a new calendar object
	Calendar* newCalendar = new Calendar(calendarTitle, calendarDays, startingDay);
	cout << "Calendar created." << endl;
	int inMenu = 1;
	//menu repeating after every selection is finished
	while ( (inMenu == 1) ) {
		
		cout << "\n";
		cout << "Select an action. (Enter an integer)" << endl;	
		cout << "1. Display Calendar\n2. Display a Single Day\n3. Add an errand\n4. Delete an errand\n5. Save to a file\n0. Exit" << endl;
		int action;
		string dummy;//used to catch newline characters on getlines
		cin >> action;
		switch (action) {
			case 1://display calendar
				cout << calendarTitle << endl;
				newCalendar->weekOutput();
				break;
			case 2://display specific day
				cout << "Select which day to display:" << endl;
				cout << "Day (1 - " << calendarDays << ") (0 - Back to menu)" << endl;
				int dayToDisplay;
				cin >> dayToDisplay;
				if ( (dayToDisplay == 0) ) {
					break;
				}
				else {
					newCalendar->displayAgenda(dayToDisplay-1);
				}
				break;
			case 3://adding errands
				cout << calendarTitle << endl;
				newCalendar->weekOutput();
				cout << "Select which day to add an Errand to:" << endl;
				cout << "Day (1 - " << calendarDays << ") (0 - Back to menu)" << endl;
				int dayForErrand;
				cin >> dayForErrand;
				if ( (dayForErrand == 0) ) {
					break;
				}
				else {
					cout << "What type of errand?" << endl;
					cout << "1. Reminder\n2. Appointment" << endl;
					int errandType;
					cin >> errandType;
					if ( (errandType == 1) ) {
						cout << "Enter reminder text:" << endl;
						string description;
						getline(cin, dummy);//catching newline
						getline(cin, description);
						newCalendar->daysArray[dayForErrand-1].addReminder( description );
						break;
					}
					else {
						cout << "Enter start time as 2 seperate numbers, first the hour, then minutes, (24 hour time)" << endl;
						int startH, startM, endH, endM;
						cin >> startH;
						cin >> startM;
						cout << "Enter end time, first the hour, then minutes, (24 hour time)" << endl;
						cin >> endH;
						cin >> endM;
						cout << "Enter reminder text:" << endl;
						string description2;
						getline(cin, dummy);//catching newline
						getline(cin, description2);
						newCalendar->daysArray[dayForErrand-1].addAppointment(startH, startM, endH, endM, description2 );
					}
				}
				break;
			case 4://removing errands
				cout << calendarTitle << endl;
				newCalendar->weekOutput();
				cout << "Select which day to remove an Errand from:" << endl;
				cout << "Day (1 - " << calendarDays << ") (0 - Back to menu)" << endl;
				int dayToRemove;
				int deleted;
				cin >> dayToRemove;
				if ( (dayToRemove == 0) ) {
					break;
				}
				else {
					cout << "Showing errands on the day you have selected" << endl;
					newCalendar->displayAgenda(dayToRemove-1);//displays the day
					cout << "Which errand shall be deleted?" << endl;
					cin >> deleted;//errand to be deleted
					newCalendar->daysArray[dayToRemove-1].remErrand(deleted);
					cout << "The errand has been deleted." << endl;
				}
				break;
			case 5://beings file I/O
				cout << "enter filename to store to" << endl;
				getline(cin, dummy);//catching newline
				getline(cin, fileName);
				
				newFile.open (fileName);
				int i;//printing every line of the calendar
				for (i = 0; i < calendarDays; i++) {
					newFile << to_string(newCalendar->daysArray[i].getDayNum()) << " - " << newCalendar->daysArray[i].getDayName() << ": " << to_string(newCalendar->daysArray[i].getErrandsSize()) << " Errands" << endl;
				}
				newFile.close();
				break;
			case 0://exits repeating menu sequence, finishing program
				inMenu = 0;
				break;
		}
	}
}
