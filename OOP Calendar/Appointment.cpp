#include "Appointment.h"

Appointment::Appointment() {

	this->beginTime = "undetermined";
	this->endTime = "undetermined";
}

Appointment::Appointment(string begin, string end) {

	this->beginTime = begin;
	this->endTime = end;
}

void Appointment::setBeginTime(string time) {

	this->beginTime = time;
}

void Appointment::setEndTime(string time) {

	this->endTime = time;
}

void Appointment::setDescription(string desc) {

	Reminder::setDesc(desc);
}

string Appointment::getBeginTime() {

	return this->beginTime;
}

string Appointment::getEndTime() {

	return this->endTime;
}

string Appointment::toString() {

	string newString = Reminder::getDesc() + " " + beginTime + " - " + endTime;

	return "Appointment - " + newString;
}

