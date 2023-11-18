#include "Calendar.h"
#include <iostream>
#include <string>
#include <vector>
#include "Day.h"
#include "Reminder.h"
#include "Appointment.h"
using namespace std;

Calendar::Calendar() {
	
	string title = "Calendar";
	this-> numDays = 30;
	this->startDay = "Monday";
	this->daysArray = new Day[numDays];
}

Calendar::Calendar(string title, int numDays, int startingDay) {

	this->title = title;
	this->numDays = numDays;
	switch (startingDay) {
		case 0: this->startDay = "Sunday";break;
		case 1: this->startDay = "Monday";break;
		case 2: this->startDay = "Tuesday";break;
		case 3: this->startDay = "Wednesday";break;
		case 4: this->startDay = "Thursday";break;
		case 5: this->startDay = "Friday";break;
		case 6: this->startDay = "Saturday";break;
	}	
	this->daysArray = new Day[numDays];
	int i;
	for (i = 0; i < numDays; i++) {
		daysArray[i].setDayNum(i + 1);
	}
	for (i = 0; i < numDays; i++) {
		daysArray[i].setDayName( ((i + startingDay) % 7) );
	}
}

void Calendar::displayAgenda(int day) {
	
	int i;
	cout << daysArray[day].getDayNum() << " - " << daysArray[day].getDayName() << ":" << endl;
	for (i = 0; (i < daysArray[day].getErrandsSize()); i++) {
		
		cout << "    " << (i+1) << " - " << daysArray[day].getErrandsAt(i)->toString() << endl;
	}
}

void Calendar::weekOutput() {
	
	int i;
	for (i = 0; i < numDays; i++) {
		cout << daysArray[i].getDayNum() << " - " << daysArray[i].getDayName() << ": " << daysArray[i].getErrandsSize() << " Errands" << endl;
	}
}

