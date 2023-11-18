#ifndef CALENDAR_H
#define CALENDAR_H
#include <vector>
#include <string>
#include <iostream>
#include "Day.h"
using namespace std;

class Calendar {
	
	private:
		string title;
		int numDays;
		string startDay;
	public:
		Day* daysArray;
		Calendar();
		Calendar(string title, int numDays, int startDay);
		void displayAgenda(int day);
		void weekOutput();

};

#endif

