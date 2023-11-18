#ifndef DAY_H
#define DAY_H
#include <iostream>
#include <vector>
#include <string>
#include "Reminder.h"
using namespace std;

class Day {
	
	private:
		int currentDayNumber;
		string currentDayName;
		vector<Reminder*> errands;
	public:
		Day();
		Day(int currDayNum, int currDayName);
		void addReminder(string desc);
		void addAppointment(int stH, int stM, int eH, int eM, string desc);
		void remErrand(int rem);
		void setDayName(int dayNumber);
		void setDayNum(int dayNumber);
		int getDayNum();
		int getErrandsSize();
		Reminder* backErrands();
		Reminder* getErrandsAt(int i);
		string getDayName();
		string toString();
};

#endif
