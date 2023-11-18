#include "Day.h"
#include "Reminder.h"
#include "Appointment.h"

Day::Day() {

	this->currentDayNumber = 1;
	this->currentDayName = "Monday";
}

Day::Day(int currDayNum, int currDayName) {

	this->currentDayNumber = currDayNum;
	switch (currDayName) {
		case 0: this->currentDayName = "Sunday";break;
		case 1: this->currentDayName = "Monday";break;
		case 2: this->currentDayName = "Tuesday";break;
		case 3: this->currentDayName = "Wednesday";break;
		case 4: this->currentDayName = "Thursday";break;
		case 5: this->currentDayName = "Friday";break;
		case 6: this->currentDayName = "Saturday";break;
	}	
	
}

void Day::addReminder(string desc) {

	Reminder* reminder = new Reminder(desc);
	this->errands.push_back(reminder);
	cout << "Reminder added." << endl;
}

void Day::addAppointment(int startH, int startM, int endH, int endM, string desc) {
	
	string startTime = to_string(startH) + ":" + to_string(startM);
	string endTime = to_string(endH) + ":" + to_string(endM);

	Appointment* appointment = new Appointment(startTime, endTime);
	appointment->setDescription(desc);
	this->errands.push_back(appointment);
	cout << "Appointment added." << endl;
	
}

void Day::remErrand(int rem) {
	//promp for which reminder/appointment it is from the list, take that integer and pass it
	this->errands.erase(errands.begin()+(rem-1));	
}

void Day::setDayName(int dayNumber) {

	switch (dayNumber) {
		case 0: this->currentDayName = "Sunday";break;
		case 1: this->currentDayName = "Monday";break;
		case 2: this->currentDayName = "Tuesday";break;
		case 3: this->currentDayName = "Wednesday";break;
		case 4: this->currentDayName = "Thursday";break;
		case 5: this->currentDayName = "Friday";break;
		case 6: this->currentDayName = "Saturday";break;
	}	

}

void Day::setDayNum(int dayNumber) {

	this->currentDayNumber = dayNumber;
}

int Day::getDayNum() {

	return this->currentDayNumber;

}

string Day::getDayName() {
	
	return this->currentDayName;

}

int Day::getErrandsSize() {

	return this->errands.size();
}

Reminder* Day::backErrands() {

	return this->errands.back();
}

Reminder* Day::getErrandsAt(int i) {

	return this->errands.at(i);
}

string Day::toString() {
	return "TODO";
}

