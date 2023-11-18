#include "Reminder.h"

Reminder::Reminder() {
	
	this->description = "needs description";
}

Reminder::Reminder(string desc) {

	this->description = desc;
}

void Reminder::setDesc(string desc) {
	
	this->description = desc;
}

string Reminder::getDesc() {

	return this->description;
}

string Reminder::toString() {

	return "Reminder - " + this->description;
}
