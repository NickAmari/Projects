#ifndef APPOINTMENT_H
#define APPOINTMENT_H 
#include <iostream>
#include <string>
#include <vector>
#include "Reminder.h"
using namespace std;

class Appointment: public Reminder
{
	public:
		Appointment();
		Appointment(string begin, string end);
		void setBeginTime(string time);
		void setEndTime(string time);
		void setDescription(string desc);
		string getBeginTime();
		string getEndTime();
		string toString();
	private:
		string beginTime;
		string endTime;
};

#endif
